package lib.framework.hollo.baidumap;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.map.TileOverlay;
import com.baidu.mapapi.map.TileOverlayOptions;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.List;

/**
 * Created by orson on 16/3/3.
 * 目前百度地图SDK所提供的地图等级为3-21级，所包含的信息有建筑物、道路、河流、学校、公园等内容
 * 地图支持缩放至21级，暂不支持卫星图、热力图、交通路况图层的21级显示，打开以上类型图层，地图会自动缩放到20级。
 * 所有叠加或覆盖到地图的内容，我们统称为地图覆盖物。如标注、矢量图形元素（包括：折线、多边形和圆等）、定位图标等。
 * 覆盖物拥有自己的地理坐标，当您拖动或缩放地图时，它们会相应的移动。
 *
 * 百度地图SDK为广大开发者提供的基础地图和上面的各种覆盖物元素，具有一定的层级压盖关系，具体如下（从下至上的顺序）：
 1、基础底图（包括底图、底图道路、卫星图等）；
 2、瓦片图层（TileOverlay）；
 3、地形图图层（GroundOverlay）；
 4、热力图图层（HeatMap）；
 5、实时路况图图层（BaiduMap.setTrafficEnabled(true);）；
 6、百度城市热力图（BaiduMap.setBaiduHeatMapEnabled(true);）；
 7、底图标注（指的是底图上面自带的那些POI元素）；
 8、几何图形图层（点、折线、弧线、圆、多边形）；
 9、标注图层（Marker），文字绘制图层（Text）；
 10、指南针图层（当地图发生旋转和视角变化时，默认出现在左上角的指南针）；
 11、定位图层（BaiduMap.setMyLocationEnabled(true);）；
 12、弹出窗图层（InfoWindow）；
 13、自定义View（MapView.addView(View);）；

 百度地图Android SDK为您提供了3种类型的地图资源（普通矢量地图、卫星图和空白地图），
 开发者可以利用BaiduMap中的mapType()方法来设置地图类型


 */
public abstract class AbstractSupportMapFragment extends SupportMapFragment implements IBaiduMapSetting, IBaiduMapController{
    private IBaiduMapSetting mBaiduMapSetting;
    private IBaiduMapController mBaiduMapController;

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        mBaiduMapSetting = new BaiduMapSettingImp(getBaiduMap());
        mBaiduMapController = new BaiduMapControllerImp(getMapView());
    }

    /**
     * 设置百度地图显示模式，共有三种模式可以选择，分别为：
     * BaiduMapType.MAP_TYPE_NORMAL : 普通地图
     * BaiduMapType.MAP_TYPE_SATELLITE : 卫星地图模式
     * BaiduMapType.MAP_TYPE_NONE : 空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，
     * 将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
     *
     * @param maptype
     */
    public void setMapType(BaiduMapType maptype) {
        mBaiduMapSetting.setMapType(maptype);
    }

    /**
     * 开启实时交通图
     * 当前，全国范围内已支持多个城市实时路况查询，且会陆续开通其他城市。在地图上打开实时路况的核心代码如下
     * @param enabledable
     */
    public void setTrafficEnabled(boolean enabledable) {
        mBaiduMapSetting.setTrafficEnabled(enabledable);
    }

    /**
     * 开启百度城市热力图
     * 百度地图SDK继为广大开发者开放热力图本地绘制能力之后，再次进一步开放百度自有数据的城市热力图层，帮助开发者构建形式更加多样的移动端应用。
     * 百度城市热力图的性质及使用与实时交通图类似，只需要简单的接口调用，即可在地图上展现样式丰富的百度城市热力图。
     *
     * @param enabledable
     */
    public void setBaiduHeatMapEnabled(boolean enabledable) {
        mBaiduMapSetting.setBaiduHeatMapEnabled(enabledable);
    }

    /**
     * 地图Logo不允许遮挡,可以设置地图边界区域，来避免UI遮挡
     * 其中参数paddingLeft、paddingTop、paddingRight、paddingBottom
     * 参数表示距离屏幕边框的左、上、右、下边距的距离，单位为屏幕坐标的像素密度。
     */
    public void setLogoPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        mBaiduMapSetting.setLogoPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 指南针默认为开启状态，可以关闭显示 。
     * @param endable
     */
    public void setCompassEndable(boolean endable) {
        mBaiduMapSetting.setCompassEndable(endable);
    }

    /**
     * 设置指南针显示的位置,在 onMapLoadFinish 后生效
     * @param point
     */
    public void setCompassPosition(Point point) {
        mBaiduMapSetting.setCompassPosition(point);
    }

    /**
     * 设置是否允许所有手势操作
     * @param enabledable
     */
    public void setAllGesturesEnabled(boolean enabledable) {
        mBaiduMapSetting.setAllGesturesEnabled(enabledable);
    }

    /**
     * 设置是否允许俯视手势
     * @param enabledable
     */
    public void setOverlookingGesturesEnabled(boolean enabledable) {
        mBaiduMapSetting.setOverlookingGesturesEnabled(enabledable);
    }

    /**
     * 设置是否允许旋转手势
     * @param enabledable
     */
    public void setRotateGesturesEnabled(boolean enabledable) {
        mBaiduMapSetting.setRotateGesturesEnabled(enabledable);
    }

    /**
     * 设置是否允许拖拽手势
     * @param enabledable
     */
    public void setScrollGesturesEnabled(boolean enabledable) {
        mBaiduMapSetting.setScrollGesturesEnabled(enabledable);
    }

    /**
     * 设置是否允许缩放手势
     * @param enabledable
     */
    public void setZoomGesturesEnabled(boolean enabledable) {
        mBaiduMapSetting.setZoomGesturesEnabled(enabledable);
    }

    /**
     * 获取是否允许指南针
     * @return
     */
    public boolean isCompassEnabled() {
        return mBaiduMapSetting.isCompassEnabled();
    }

    /**
     * 设置是否允许俯视手势
     * @return
     */
    public boolean isOverlookingGesturesEnabled() {
        return mBaiduMapSetting.isOverlookingGesturesEnabled();
    }

    /**
     * 获取是否允许旋转手势
     * @return
     */
    public boolean isRotateGesturesEnabled() {
        return mBaiduMapSetting.isRotateGesturesEnabled();
    }

    /**
     * 获取是否允许拖拽手势
     * @return
     */
    public boolean isScrollGesturesEnabled() {
        return mBaiduMapSetting.isScrollGesturesEnabled();
    }

    /**
     * 获取是否允许缩放手势
     * @return
     */
    public boolean isZoomGesturesEnabled() {
        return mBaiduMapSetting.isZoomGesturesEnabled();
    }

    /**
     * 添加热力图
     * @param heatmap
     */
    public void addHeatMap(HeatMap heatmap) {
        mBaiduMapSetting.addHeatMap(heatmap);
    }

    /**
     * 向地图添加一个 Overlay
     * @param options
     * @return
     */
    public Overlay addOverlay(OverlayOptions options) {
        return mBaiduMapSetting.addOverlay(options);
    }

    /**
     * 向地图添加多个 Overlay
     * @param options
     * @return
     */
    public List<Overlay> addOverlays(List<OverlayOptions> options) {
        return mBaiduMapSetting.addOverlays(options);

    }

    /**
     * 向地图添加一个TileOverlay覆盖物
     * @param overlayOptions
     * @return
     */
    public TileOverlay addTileLayer(TileOverlayOptions overlayOptions) {
        return mBaiduMapSetting.addTileLayer(overlayOptions);

    }

    /**
     * 以动画方式更新地图状态，动画耗时 300 ms
     * @param update
     */
    public void animateMapStatus(MapStatusUpdate update) {
         mBaiduMapSetting.animateMapStatus(update);
    }

    /**
     * 以动画方式更新地图状态
     * @param update
     * @param durationMs
     */
    public void animateMapStatus(MapStatusUpdate update, int durationMs) {
        mBaiduMapSetting.animateMapStatus(update, durationMs);
    }

    /**
     * 清空地图所有的 Overlay 覆盖物以及 InfoWindow
     */
    public void clear() {
        mBaiduMapSetting.clear();
    }

    /**
     * 获取屏幕坐标系下指南针位置
     * @return
     */
    public Point getCompassPosition() {
        return mBaiduMapSetting.getCompassPosition();
    }

    /**
     * 获取定位图层配置信息
     * @return
     */
    public MyLocationConfiguration getLocationConfigeration() {
        return mBaiduMapSetting.getLocationConfigeration();
    }

    /**
     * 获取定位数据
     * @return
     */
    public MyLocationData getLocationData() {
        return mBaiduMapSetting.getLocationData();
    }

    /**
     * 获取地图的当前状态
     * @return
     */
    public MapStatus getMapStatus() {
        return mBaiduMapSetting.getMapStatus();
    }

    /**
     * 获取地图可移动区域
     * @return
     */
    public LatLngBounds getMapStatusLimit() {
        return mBaiduMapSetting.getMapStatusLimit();
    }

    /**
     * 获取地图当前的模式，空白地图、普通地图或者卫星图
     * @return
     */
    public BaiduMapType getMapType() {
        return mBaiduMapSetting.getMapType();
    }

    /**
     * 获取指定区域内所有的Marker点
     * @param bounds
     * @return
     */
    public List<Marker> getMarkersInBounds(LatLngBounds bounds) {
        return mBaiduMapSetting.getMarkersInBounds(bounds);
    }

    /**
     * 获取地图最大缩放级别
     * @return
     */
    public float getMaxZoomLevel() {
        return mBaiduMapSetting.getMaxZoomLevel();
    }

    /**
     * 获取地图最小缩放级别
     * @return
     */
    public float getMinZoomLevel() {
        return mBaiduMapSetting.getMinZoomLevel();
    }

    /**
     * 获取地图投影坐标转换器, 当地图初始化完成之前返回 null，
     * 在 OnMapLoadedCallback.onMapLoaded() 之后才能正常
     * @return
     */
    public Projection getProjection() {
        return mBaiduMapSetting.getProjection();
    }

    /**
     * 隐藏当前 InfoWindow
     */
    public void hideInfoWindow() {
        mBaiduMapSetting.hideInfoWindow();
    }

    /**
     * 获取是否打开百度热力图层(百度自有数据图层)
     * @return
     */
    public boolean isBaiduHeatMapEnabled() {
        return mBaiduMapSetting.isBaiduHeatMapEnabled();
    }

    /**
     * 获取是否允许楼块效果
     * @return
     */
    public boolean isBuildingsEnabled() {
        return mBaiduMapSetting.isBuildingsEnabled();
    }

    /**
     * 获取是否允许定位图层
     * @return
     */
    public boolean isMyLocationEnabled() {
        return mBaiduMapSetting.isMyLocationEnabled();
    }

    /**
     * 查询当前图区是否支持百度热力图
     * @return
     */
    public boolean isSupportBaiduHeatMap() {
        return mBaiduMapSetting.isSupportBaiduHeatMap();
    }

    /**
     * 获取是否打开交通图层
     * @return
     */
    public boolean isTrafficEnabled() {
        return mBaiduMapSetting.isTrafficEnabled();
    }

    /**
     * 移除一个地图 Marker 覆盖物点击事件监听者
     * @param listener
     */
    public void removeMarkerClickListener(BaiduMap.OnMarkerClickListener listener) {
        mBaiduMapSetting.removeMarkerClickListener(listener);
    }

    /**
     * 改变地图状态
     * @param update
     */
    public void setMapStatus(MapStatusUpdate update) {
        mBaiduMapSetting.setMapStatus(update);
    }

    /**
     * 设置地图的可移动区域，只有在 OnMapLoadedCallback.onMapLoaded() 之后设置才生效
     * @param bounds
     */
    public void setMapStatusLimits(LatLngBounds bounds) {
        mBaiduMapSetting.setMapStatusLimits(bounds);
    }

    /**
     * 设置地图最大以及最小缩放级别，地图支持的最大最小级别分别为[3-21]
     * @param max
     * @param min
     */
    public void setMaxAndMinZoomLevel(float max, float min) {
        mBaiduMapSetting.setMaxAndMinZoomLevel(max, min);
    }

    /**
     * 设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效，
     * 参见 setMyLocationEnabled(boolean)
     * @param configeration
     */
    public void setMyLocationConfigeration(MyLocationConfiguration configeration) {
        mBaiduMapSetting.setMyLocationConfigeration(configeration);
    }

    /**
     * 设置定位数据, 只有先允许定位图层后设置数据才会生效，
     * 参见 setMyLocationEnabled(boolean)
     * @param data
     */
    public void setMyLocationData(MyLocationData data) {
        mBaiduMapSetting.setMyLocationData(data);
    }

    /**
     * 设置是否允许定位图层
     * @param enabled
     */
    public void setMyLocationEnabled(boolean enabled) {
        mBaiduMapSetting.setMyLocationEnabled(enabled);
    }

    /**
     * 设置地图单击事件监听者
     * @param listener
     */
    public void setOnMapClickListener(BaiduMap.OnMapClickListener listener) {
        mBaiduMapSetting.setOnMapClickListener(listener);
    }

    /**
     * 设置地图双击事件监听者
     * @param listener
     */
    public void setOnMapDoubleClickListener(BaiduMap.OnMapDoubleClickListener listener) {
        mBaiduMapSetting.setOnMapDoubleClickListener(listener);
    }

    /**
     * 设置百度地图在每一帧绘制时的回调接口，该接口在绘制线程中调用
     * @param callback
     */
    public void setOnMapDrawFrameCallback(BaiduMap.OnMapDrawFrameCallback callback) {
        mBaiduMapSetting.setOnMapDrawFrameCallback(callback);
    }

    /**
     * 设置地图加载完成回调
     * @param callback
     */
    public void setOnMapLoadedCallback(BaiduMap.OnMapLoadedCallback callback) {
        mBaiduMapSetting.setOnMapLoadedCallback(callback);
    }

    /**
     * 设置地图长按事件监听者
     * @param listener
     */
    public void setOnMapLongClickListener(BaiduMap.OnMapLongClickListener listener) {
        mBaiduMapSetting.setOnMapLongClickListener(listener);
    }

    /**
     * 设置地图状态监听者
     * @param listener
     */
    public void setOnMapStatusChangeListener(BaiduMap.OnMapStatusChangeListener listener) {
        mBaiduMapSetting.setOnMapStatusChangeListener(listener);
    }

    /**
     * 设置触摸地图事件监听者
     * @param listener
     */
    public void setOnMapTouchListener(BaiduMap.OnMapTouchListener listener) {
        mBaiduMapSetting.setOnMapTouchListener(listener);
    }

    /**
     * 设置地图 Marker 覆盖物点击事件监听者,自3.4.0版本起可设置多个监听对象，
     * 停止监听时调用removeMarkerClickListener移除监听对象
     * @param listener
     */
    public void setOnMarkerClickListener(BaiduMap.OnMarkerClickListener listener) {
        mBaiduMapSetting.setOnMarkerClickListener(listener);
    }

    /**
     * 设置 Marker 拖拽事件监听者
     * @param listener
     */
    public void setOnMarkerDragListener(BaiduMap.OnMarkerDragListener listener) {
        mBaiduMapSetting.setOnMarkerDragListener(listener);
    }

    /**
     * 设置定位图标点击事件监听者
     * @param listener
     */
    public void setOnMyLocationClickListener(BaiduMap.OnMyLocationClickListener listener) {
        mBaiduMapSetting.setOnMyLocationClickListener(listener);
    }

    /**
     * 设置地图 Polyline 覆盖物点击事件监听者
     * @param listener
     */
    public void setOnPolylineClickListener(BaiduMap.OnPolylineClickListener listener) {
        mBaiduMapSetting.setOnPolylineClickListener(listener);
    }

    /**
     * 显示 InfoWindow
     * @param infoWindow
     */
    public void showInfoWindow(InfoWindow infoWindow) {
        mBaiduMapSetting.showInfoWindow(infoWindow);
    }

    /**
     * 控制是否显示底图默认标注
     * @param isShow
     */
    public void showMapPoi(boolean isShow) {
        mBaiduMapSetting.showMapPoi(isShow);
    }

    /**
     * 发起截图请求
     * @param callback
     */
    public void snapshot(BaiduMap.SnapshotReadyCallback callback) {
        mBaiduMapSetting.snapshot(callback);
    }

    /**
     * 发起区域截图请求 注： Rect为null，截全屏
     * @param rect
     * @param callback
     */
    public void snapshotScope(Rect rect, BaiduMap.SnapshotReadyCallback callback) {
        mBaiduMapSetting.snapshotScope(rect, callback);
    }

    /**
     * 设置地图Logo的位置
     * 默认在左下角显示，不可以移除。使用枚举类型控制显示的位置，共支持6个显示位置(左下，中下，右下，左上，中上，右上)。
     * 具体类型有：
     * MapLogoPosition.LEFT_DOWN :      左下
     * MapLogoPosition.MIDDLE_DOWN :    中下
     * MapLogoPosition.RIGHT_DWON :     右下
     * MapLogoPosition.LEFT_UP :        左上
     * MapLogoPosition.MIDDLE_UP :      中上
     * MapLogoPosition.RIGHT_UP :       右上
     * @param pos
     */
    public void setLogoPosition(MapLogoPosition pos) {
        mBaiduMapController.setLogoPosition(pos);
    }
}


