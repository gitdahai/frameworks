package lib.framework.hollo.umengshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by orson on 16/3/1.
 */
public class Utils {

    /**
     * 打开分享对话框，进行分享操作
     * @param context
     * @param shareValue
     * @param platforms
     */
    public static void openShareDialog(Context context, UmengShareValue shareValue, UmengSharePlatform... platforms){
        if (context == null || shareValue == null)
            return;

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.SHARE_VALUE, shareValue);

        //如果期望显示的分享平台不为null，则设置分享的平台
        if (platforms != null && platforms.length != 0){
            ArrayList<Integer> platformPos = new ArrayList<Integer>();

            for (int i=0; i<platforms.length; i++)
                platformPos.add(platforms[i].ordinal());

            bundle.putIntegerArrayList(Constant.SHARE_PLATFORM, platformPos);
        }

        Intent intent = new Intent(UmengShareActions.UMENG_SHARE_OPEN);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
