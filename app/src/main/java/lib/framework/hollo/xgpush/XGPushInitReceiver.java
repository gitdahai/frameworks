package lib.framework.hollo.xgpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import lib.framework.hollo.utils.MyLog;

/**
 * Created by orson on 16/3/2.
 * 本版本使用的信鸽sdk的版本号为v2.42
 */
public class XGPushInitReceiver extends BroadcastReceiver {
    public static final String XG_PUSH_START = "action.xg.push.init";
    public static final String XG_PUSH_STOP = "action.xg.push.destory";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (XG_PUSH_START.equals(action)){
            MyLog.d("信鸽推送服务进行初始化");

            // 开启logcat输出，方便debug，发布时请关闭
            XGPushConfig.enableDebug(context, true);

            // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
            // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
            // 具体可参考详细的开发指南
            // 传递的参数为ApplicationContext
            XGPushManager.registerPush(context.getApplicationContext(), xgiOperateCallback);

            // 2.36（不包括）之前的版本需要调用以下2行代码
            //Intent service = new Intent(context, XGPushService.class);
            //context.startService(service);

            // 其它常用的API：
            // 绑定账号（别名）注册：registerPush(context,account)或registerPush(context,account, XGIOperateCallback)，其中account为APP账号，可以为任意字符串（qq、openid或任意第三方），业务方一定要注意终端与后台保持一致。
            // 取消绑定账号（别名）：registerPush(context,"*")，即account="*"为取消绑定，解绑后，该针对该账号的推送将失效
            // 反注册（不再接收消息）：unregisterPush(context)
            // 设置标签：setTag(context, tagName)
            // 删除标签：deleteTag(context, tagName)

        }
        else if (XG_PUSH_STOP.equals(action)){
            MyLog.d("信鸽推送服务进行销毁操作");
            XGPushManager.unregisterPush(context.getApplicationContext(), xgiOperateCallback);
        }
    }

    /**
     * 信鸽操作
     */
    private XGIOperateCallback xgiOperateCallback = new XGIOperateCallback(){
        public void onSuccess(Object data, int i) {
            Log.d("TPush", "注册成功，设备token为：" + data);
        }

        public void onFail(Object data, int errCode, String msg) {
            Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
        }
    };
}
