package lib.framework.hollo.umengshare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.umeng.socialize.Config;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by orson on 16/3/1.
 */
public class UMShareParentActivity extends Activity {
    private UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShareAPI = UMShareAPI.get(this);

        //在Android SDK v5.0.2版本中可以使用下面的方法修改默认的progress dialog
        ProgressDialog dialog =  new ProgressDialog(this);
        dialog.setTitle("11");
        dialog.setMessage("222");
        Config.dialog = dialog;

        //是否开启分享编辑页
        //注意只有网页分享时分享编辑页才会有效，故只有豆瓣、人人网有效
        Config.OpenEditor = false;

        //关闭log和toast
        //Log.LOG = false;
        //Config.IsToastTip = false;
        
    }

    /**
     *
     * @return
     */
    protected UMShareAPI getUMShareAPI(){
        return mShareAPI;
    }
}
