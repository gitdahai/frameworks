package lib.framework.hollo.umengshare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.umeng.socialize.PlatformConfig;

import lib.framework.hollo.utils.MyLog;

/**
 * Created by orson on 16/2/29.
 * 友盟分享服务启动/停止广播接受器
 */
public class UmengShareInitReceiver extends BroadcastReceiver{
    public static final String UM_SHARE_START = "action.um.share.init";
    public static final String UM_SHARE_STOP = "action.um.share.destory";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (UM_SHARE_START.equals(intent.getAction())){
            MyLog.d("友盟分享服务进行初始化");

            //微信 appid appsecret
            PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");

            //新浪微博 appkey appsecret
            PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad");

            //QQ和Qzone appid appkey
            PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        }
    }
}
