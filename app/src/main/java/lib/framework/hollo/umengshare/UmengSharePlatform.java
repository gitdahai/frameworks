package lib.framework.hollo.umengshare;

import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lib.framework.hollo.R;

/**
 * Created by orson on 16/3/1.
 * 友盟分享平台
 */
public enum UmengSharePlatform implements Serializable{
    WX_FRIEND_CIRCLE,   //微信朋友圈
    WX,                 //微信
    SMS,                //短信
    SN_WEIBO,           //新浪微博
    QQ,                 //腾讯qq
    QZONE;              //QQ空间

    /**
     * 返回该对象的索引
     * @return
     */
    public int pos(){
        int result = 0;

        switch (this){
            case WX_FRIEND_CIRCLE:  result = 0;     break;
            case WX:                result = 1;     break;
            case SMS:               result = 2;     break;
            case SN_WEIBO:          result = 3;     break;
            case QQ:                result = 4;     break;
            case QZONE:             result = 5;     break;
        }

        return result;
    }

    /**
     * 获取分享的media类型
     * @return
     */
    public SHARE_MEDIA getShareMediaType(){
        SHARE_MEDIA media = null;

        switch (this){
            case WX_FRIEND_CIRCLE:  media = SHARE_MEDIA.WEIXIN_CIRCLE;      break;
            case WX:                media = SHARE_MEDIA.WEIXIN;             break;
            case SMS:               media = SHARE_MEDIA.SMS;                break;
            case SN_WEIBO:          media = SHARE_MEDIA.SINA;               break;
            case QQ:                media = SHARE_MEDIA.QQ;                 break;
            case QZONE:             media = SHARE_MEDIA.QZONE;              break;
        }

        return media;
    }

    /**
     *
     * @param pos
     * @return
     */
    public static SHARE_MEDIA getShareMediaType(int pos){
        SHARE_MEDIA media = null;

        switch (pos){
            case 0:  media = SHARE_MEDIA.WEIXIN_CIRCLE;      break;
            case 1:  media = SHARE_MEDIA.WEIXIN;             break;
            case 2:  media = SHARE_MEDIA.SMS;                break;
            case 3:  media = SHARE_MEDIA.SINA;               break;
            case 4:  media = SHARE_MEDIA.QQ;                 break;
            case 5:  media = SHARE_MEDIA.QZONE;              break;
        }

        return media;
    }

    /**
     * 返回该对象的title
     * @return
     */
    public String title(){
        String result = "";

        switch (this){
            case WX_FRIEND_CIRCLE:  result = "朋友圈";     break;
            case WX:                result = "微信";       break;
            case SMS:               result = "短信";      break;
            case SN_WEIBO:          result = "微博";      break;
            case QQ:                result = "QQ";        break;
            case QZONE:             result = "QQ空间";    break;
        }

        return result;
    }

    /**
     * 返回该对象icon 资源ID
     * @return
     */
    public int iconId(){
        int result = 0;

        switch (this){
            case WX_FRIEND_CIRCLE:  result = R.mipmap.ic_shareic_wx_circle;   break;
            case WX:                result = R.mipmap.ic_shareic_wx;          break;
            case SMS:               result = R.mipmap.ic_shareic_sms;         break;
            case SN_WEIBO:          result = R.mipmap.ic_shareic_sina_weibo;  break;
            case QQ:                result = R.mipmap.ic_shareic_qq;          break;
            case QZONE:             result = R.mipmap.ic_shareic_qq_zone;     break;
        }

        return result;
    }



    /**
     *
     * @param id
     * @return
     */
    public static UmengSharePlatform find4Pos(int id){
        UmengSharePlatform platform = null;

        switch (id){
            case 0: platform = WX_FRIEND_CIRCLE;    break;
            case 1: platform = WX;                  break;
            case 2: platform = SMS;                 break;
            case 3: platform = SN_WEIBO;            break;
            case 4: platform = QQ;                  break;
            case 5: platform = QZONE;               break;

        }

        return platform;
    }

    /**
     * 返回所有的索引
     * @return
     */
    public static List<Integer> getPoses(){
        List<Integer> result = new ArrayList<Integer>();
        UmengSharePlatform[] platforms = UmengSharePlatform.values();

        for (int i=0; i<platforms.length; i++)
            result.add(platforms[i].ordinal());

        return result;
    }

}
