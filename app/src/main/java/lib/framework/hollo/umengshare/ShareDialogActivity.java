package lib.framework.hollo.umengshare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.framework.hollo.R;

/**
 * Created by orson on 16/2/29.
 */
public class ShareDialogActivity extends UMShareParentActivity {
    private Animation animIn;
    private Animation animOut;
    private View sharePanelView;
    private View closeBtnView;
    private GridView platformView;
    private UmengShareValue mShareValue;
    private List<Integer> mPlatformPoses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.um_share_dialog_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //如果没有分享的内容，则关闭分享页面
        if (bundle == null || !bundle.containsKey(Constant.SHARE_VALUE)){
            this.finish();
            return;
        }
        else{
            mShareValue = (UmengShareValue)bundle.getSerializable(Constant.SHARE_VALUE);
            mPlatformPoses = bundle.getIntegerArrayList(Constant.SHARE_PLATFORM);

            if (mPlatformPoses == null || mPlatformPoses.size() == 0)
                mPlatformPoses = UmengSharePlatform.getPoses();
        }

        sharePanelView = this.findViewById(R.id.share_panel_view);
        closeBtnView = this.findViewById(R.id.share_close_btn);
        platformView = (GridView)findViewById(R.id.share_platform_view);

        animIn = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        animOut = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
        animOut.setAnimationListener(outAnimListener);

        closeBtnView.setOnClickListener(clickListener);
        platformView.setOnItemClickListener(itemClickListener);

        String[] from = {"icon", "title"};
        int[] to = {R.id.itemIcon, R.id.itemTitle};
        List<Map<String, Object>> listItems = createPlatforms(mShareValue, mPlatformPoses);
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.platform_item_view, from, to);
        platformView.setAdapter(adapter);

        //加载分享面板试图
        sharePanelView.startAnimation(animIn);
    }

    /**
     * 创建分享的平台
     * @param shareValue
     * @param platformIds
     * @return
     */
    private List<Map<String, Object>> createPlatforms(UmengShareValue shareValue, List<Integer> platformIds){
        List<Map<String, Object>> platformList = new ArrayList<Map<String, Object>>();

        Map<String, Object> platMap = null;
        UmengSharePlatform paltform = null;
        ShareAction shareAction = null;

        for (Integer id : platformIds){
            paltform = UmengSharePlatform.find4Pos(id);
            platMap = new HashMap<String, Object>();
            platMap.put("icon", paltform.iconId());
            platMap.put("title", paltform.title());
            platformList.add(platMap);
        }

        return platformList;
    }

    /**
     * 分享执行事件
     */
    private UMShareListener umShareListener = new UMShareListener(){
        public void onResult(SHARE_MEDIA share_media) {
            Log.i("====", "onResult==" + share_media.toString());
            //取消授权
            //getUMShareAPI().deleteOauth(ShareDialogActivity.this, share_media, umUMAuthListener);
        }

        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
           // Log.i("====", "onError==" + share_media.toString());
            //取消授权
            //getUMShareAPI().deleteOauth(ShareDialogActivity.this, share_media, umUMAuthListener);
        }

        public void onCancel(SHARE_MEDIA share_media) {
            //Log.i("====", "onCancel==" + share_media.toString());
            //取消授权
            //getUMShareAPI().deleteOauth(ShareDialogActivity.this, share_media, umUMAuthListener);
        }
    };

    /**
     * 试图退出动画监听器
     */
    private Animation.AnimationListener outAnimListener = new Animation.AnimationListener(){
        public void onAnimationStart(Animation animation) {}
        public void onAnimationRepeat(Animation animation) {}
        public void onAnimationEnd(Animation animation) {
            sharePanelView.setVisibility(View.GONE);
            ShareDialogActivity.this.finish();
        }
    };

    /**
     * 关闭分享面板
     */
    private View.OnClickListener clickListener = new View.OnClickListener(){
        public void onClick(View v) {
            sharePanelView.startAnimation(animOut);
        }
    };

    /**
     * 用户进行分享
     */
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Integer pos = mPlatformPoses.get(position);
            SHARE_MEDIA share_media = UmengSharePlatform.getShareMediaType(pos);

            //判断是否需要鉴权登录
            if (
                    SHARE_MEDIA.QZONE == share_media
                    //||SHARE_MEDIA.SINA == share_media
                    || SHARE_MEDIA.WEIXIN == share_media
                    || SHARE_MEDIA.WEIXIN_CIRCLE == share_media
                    || SHARE_MEDIA.QQ == share_media
                    || SHARE_MEDIA.QZONE == share_media){

                getUMShareAPI().doOauthVerify(ShareDialogActivity.this, share_media, umUMAuthListener);
            }
            //否则直接分享
            else
                startSharing(share_media, mShareValue);
        }
    };

    /**
     * 用户鉴权事件监听器
     */
    private UMAuthListener umUMAuthListener = new UMAuthListener(){
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Toast.makeText( getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();

            startSharing(share_media, mShareValue);
        }

        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 开始进行分享
     * @param platform
     * @param shareValue
     */
    private void startSharing(SHARE_MEDIA platform, UmengShareValue shareValue){
        //设置分享的平台
        ShareAction shareAction = new ShareAction(this);
        shareAction.setPlatform(platform);
        shareAction.setCallback(umShareListener);
        shareAction.withText("hello umeng video");
        shareAction.withTargetUrl("http://www.baidu.com");
        UMImage image = new UMImage(this, "http://www.umeng.com/images/pic/social/chart_1.png");
        shareAction.withMedia(image);
        shareAction.share();
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getUMShareAPI().onActivityResult(requestCode, resultCode, data);

        Log.i("====", "－－－－－－－－－－－－onActivityResult=="  + resultCode);
    }
}
