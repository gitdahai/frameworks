package lib.framework.hollo.baidumap;

/**
 * Created by orson on 16/3/3.
 * 百度地图控制接口
 */
public interface IBaiduMapController {
    /**
     * 百度地图的logo位置
     * LEFT_DOWN : 左下
     * MIDDLE_DOWN : 中下
     * RIGHT_DWON : 右下
     * LEFT_UP : 左上
     * MIDDLE_UP : 中上
     * RIGHT_UP : 右上
     */
    public static enum MapLogoPosition{
        LEFT_BOTTOM,
        CENTER_BOTTOM,
        RIGHT_BOTTOM,
        LEFT_TOP,
        CENTER_TOP,
        RIGHT_TOP
    }

    /**
     * 默认在左下角显示，不可以移除。
     通过mMapView.setLogoPosition(LogoPosition.logoPostionleftBottom);方法，
     使用枚举类型控制显示的位置，共支持6个显示位置(左下，中下，右下，左上，中上，右上)。
     */
    public void setLogoPosition(MapLogoPosition pos);
}
