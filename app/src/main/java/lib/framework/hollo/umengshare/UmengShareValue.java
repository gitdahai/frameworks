package lib.framework.hollo.umengshare;

import java.io.Serializable;

/**
 * Created by orson on 16/3/1.
 * 分享时的内容
 */
public class UmengShareValue implements Serializable{
    private String link;            //分享之后的链接
    private String imgUrl;          //分享时显示的图像
    private String message;         //分享时显示的消息内容

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
