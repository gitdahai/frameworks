package lib.framework.hollo.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.baidu.mapapi.SDKInitializer;

import lib.framework.hollo.utils.MyLog;

/**
 * Created by orson on 16/3/2.
 */
public class FrameworkApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //获取当前的进程名称
        String processName = getCurProcessName(this);

        //如果但前进程名称为“lib.framework.hollo”，则说明当前执行在主进程中
        //否则不是主进程，是第三方sdk启动的进行
        if ("lib.framework.hollo".equals(processName)){
            //显示log日志
            MyLog.isShow = true;

            //初始化友盟分享服务
            Intent umShereIntent = new Intent("action.um.share.init");
            this.sendBroadcast(umShereIntent);

            //初始化信鸽推送服务
            Intent xgPushIntent = new Intent("action.xg.push.init");
            this.sendBroadcast(xgPushIntent);

            //初始化百度地图服务
            SDKInitializer.initialize(getApplicationContext());
        }
    }

    String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return "未知";
    }
}
