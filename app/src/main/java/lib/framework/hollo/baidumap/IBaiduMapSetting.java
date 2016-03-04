package lib.framework.hollo.baidumap;


import android.graphics.Point;
import android.graphics.Rect;

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
import com.baidu.mapapi.map.TileOverlay;
import com.baidu.mapapi.map.TileOverlayOptions;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.List;

/**
 * Created by orson on 16/3/3.
 */
public interface IBaiduMapSetting {
    /**
     * 百度地图显示模式类型
     * MAP_TYPE_NORMAL ：    普通地图模式
     * MAP_TYPE_SATELLITE ： 卫星地图
     * MAP_TYPE_NONE ：空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。
     * 使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
     */
    public static enum BaiduMapType{
        MAP_TYPE_NORMAL,
        MAP_TYPE_SATELLITE,
        MAP_TYPE_NONE
    }

    /**
     * 设置地图显示模式
     * 百度地图Android SDK为您提供了3种类型的地图资源（普通矢量地图、卫星图和空白地图），
     * 开发者可以利用BaiduMap中的mapType()方法来设置地图类型。核心代码如下：
     * @param maptype
     */
    public void setMapType(BaiduMapType maptype);

    /**
     * 开启实时交通图
     * 当前，全国范围内已支持多个城市实时路况查询，且会陆续开通其他城市。在地图上打开实时路况的核心代码如下
     * @param enabledable
     */
    public void setTrafficEnabled(boolean enabledable);

    /**
     * 开启百度城市热力图
     * 百度地图SDK继为广大开发者开放热力图本地绘制能力之后，再次进一步开放百度自有数据的城市热力图层，帮助开发者构建形式更加多样的移动端应用。
     百度城市热力图的性质及使用与实时交通图类似，只需要简单的接口调用，即可在地图上展现样式丰富的百度城市热力图。
     * @param enabledable
     */
    public void setBaiduHeatMapEnabled(boolean enabledable);

    /**
     * 地图Logo不允许遮挡,可以设置地图边界区域，来避免UI遮挡
     * 其中参数paddingLeft、paddingTop、paddingRight、paddingBottom
     * 参数表示距离屏幕边框的左、上、右、下边距的距离，单位为屏幕坐标的像素密度。
     */
    public void setLogoPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom);

    /**
     * 指南针默认为开启状态，可以关闭显示 。
     * @param endable
     */
    public void setCompassEndable(boolean endable);

    /**
     * 设置指南针显示的位置,在 onMapLoadFinish 后生效
     * @param point
     */
    public void setCompassPosition(Point point);

    /**
     * 设置是否允许所有手势操作
     * @param enabledable
     */
    public void setAllGesturesEnabled(boolean enabledable);

    /**
     * 设置是否允许俯视手势
     * @param enabledable
     */
    public void setOverlookingGesturesEnabled(boolean enabledable);

    /**
     * 设置是否允许旋转手势
     * @param enabledable
     */
    public void setRotateGesturesEnabled(boolean enabledable);

    /**
     * 设置是否允许拖拽手势
     * @param enabledable
     */
    public void setScrollGesturesEnabled(boolean enabledable);

    /**
     * 设置是否允许缩放手势
     * @param enabledable
     */
    public void setZoomGesturesEnabled(boolean enabledable);

    /**
     * 获取是否允许指南针
     * @return
     */
    public boolean isCompassEnabled();

    /**
     * 获取是否允许俯视手势
     * @return
     */
    public boolean isOverlookingGesturesEnabled();

    /**
     * 获取是否允许旋转手势
     * @return
     */
    public boolean isRotateGesturesEnabled();

    /**
     * 获取是否允许拖拽手势
     * @return
     */
    public boolean isScrollGesturesEnabled();

    /**
     * 获取是否允许缩放手势
     * @return
     */
    public boolean isZoomGesturesEnabled();

    /**
     * 添加热力图
     * @param heatmap
     */
    public void addHeatMap(HeatMap heatmap);

    /**
     * 向地图添加一个 Overlay
     * @param options
     * @return
     */
    public Overlay addOverlay(OverlayOptions options);

    /**
     * 向地图添加多个 Overlay
     * @param options
     * @return
     */
    public List<Overlay> addOverlays(List<OverlayOptions> options);

    /**
     * 向地图添加一个TileOverlay覆盖物
     * @param overlayOptions
     * @return
     */
    public TileOverlay addTileLayer(TileOverlayOptions overlayOptions);

    /**
     * 以动画方式更新地图状态，动画耗时 300 ms
     * @param update
     */
    public void	animateMapStatus(MapStatusUpdate update);

    /**
     * 以动画方式更新地图状态
     * @param update
     * @param durationMs
     */
    public void	animateMapStatus(MapStatusUpdate update, int durationMs);

    /**
     * 清空地图所有的 Overlay 覆盖物以及 InfoWindow
     */
    public void	clear();

    /**
     * 获取屏幕坐标系下指南针位置
     * @return
     */
    public Point getCompassPosition();

    /**
     * 获取定位图层配置信息
     * @return
     */
    public MyLocationConfiguration getLocationConfigeration();

    /**
     * 获取定位数据
     * @return
     */
    public MyLocationData getLocationData();

    /**
     * 获取地图的当前状态
     * @return
     */
    public MapStatus getMapStatus();

    /**
     * 获取地图可移动区域
     * @return
     */
    public LatLngBounds getMapStatusLimit();

    /**
     * 获取地图当前的模式，空白地图、普通地图或者卫星图
     * @return
     */
    public BaiduMapType	getMapType();

    /**
     * 获取指定区域内所有的Marker点
     * @param bounds
     * @return
     */
    public List<Marker>	getMarkersInBounds(LatLngBounds bounds);

    /**
     * 获取地图最大缩放级别
     * @return
     */
    public float getMaxZoomLevel();

    /**
     * 获取地图最小缩放级别
     * @return
     */
    public float getMinZoomLevel();

    /**
     * 获取地图投影坐标转换器, 当地图初始化完成之前返回 null，
     * 在 OnMapLoadedCallback.onMapLoaded() 之后才能正常
     * @return
     */
    public Projection getProjection();

    /**
     * 隐藏当前 InfoWindow
     */
    public void	hideInfoWindow();

    /**
     * 获取是否打开百度热力图层(百度自有数据图层)
     * @return
     */
    public boolean isBaiduHeatMapEnabled();

    /**
     * 获取是否允许楼块效果
     * @return
     */
    public boolean	isBuildingsEnabled();

    /**
     * 获取是否允许定位图层
     * @return
     */
    public boolean	isMyLocationEnabled();

    /**
     * 查询当前图区是否支持百度热力图
     * @return
     */
    public boolean	isSupportBaiduHeatMap();

    /**
     * 获取是否打开交通图层
     * @return
     */
    public boolean	isTrafficEnabled();

    /**
     * 移除一个地图 Marker 覆盖物点击事件监听者
     * @param listener
     */
    public void	removeMarkerClickListener(BaiduMap.OnMarkerClickListener listener);

    /**
     * 改变地图状态
     * @param update
     */
    public void	setMapStatus(MapStatusUpdate update);

    /**
     * 设置地图的可移动区域，只有在 OnMapLoadedCallback.onMapLoaded() 之后设置才生效
     * @param bounds
     */
    public void	setMapStatusLimits(LatLngBounds bounds);

    /**
     * 设置地图最大以及最小缩放级别，地图支持的最大最小级别分别为[3-21]
     * @param max
     * @param min
     */
    public void	setMaxAndMinZoomLevel(float max, float min);

    /**
     * 设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效，
     * 参见 setMyLocationEnabled(boolean)
     * @param configeration
     */
    public void	setMyLocationConfigeration(MyLocationConfiguration configeration);

    /**
     * 设置定位数据, 只有先允许定位图层后设置数据才会生效，
     * 参见 setMyLocationEnabled(boolean)
     * @param data
     */
    public void	setMyLocationData(MyLocationData data);

    /**
     * 设置是否允许定位图层
     * @param enabled
     */
    public void	setMyLocationEnabled(boolean enabled);

    /**
     * 设置地图单击事件监听者
     * @param listener
     */
    public void	setOnMapClickListener(BaiduMap.OnMapClickListener listener);

    /**
     * 设置地图双击事件监听者
     * @param listener
     */
    public void	setOnMapDoubleClickListener(BaiduMap.OnMapDoubleClickListener listener);

    /**
     * 设置百度地图在每一帧绘制时的回调接口，该接口在绘制线程中调用
     * @param callback
     */
    public void	setOnMapDrawFrameCallback(BaiduMap.OnMapDrawFrameCallback callback);

    /**
     * 设置地图加载完成回调
     * @param callback
     */
    public void	setOnMapLoadedCallback(BaiduMap.OnMapLoadedCallback callback);

    /**
     * 设置地图长按事件监听者
     * @param listener
     */
    public void	setOnMapLongClickListener(BaiduMap.OnMapLongClickListener listener);

    /**
     * 设置地图状态监听者
     * @param listener
     */
    public void	setOnMapStatusChangeListener(BaiduMap.OnMapStatusChangeListener listener);

    /**
     * 设置触摸地图事件监听者
     * @param listener
     */
    public void	setOnMapTouchListener(BaiduMap.OnMapTouchListener listener);

    /**
     * 设置地图 Marker 覆盖物点击事件监听者,自3.4.0版本起可设置多个监听对象，
     * 停止监听时调用removeMarkerClickListener移除监听对象
     * @param listener
     */
    public void	setOnMarkerClickListener(BaiduMap.OnMarkerClickListener listener);

    /**
     * 设置 Marker 拖拽事件监听者
     * @param listener
     */
    public void	setOnMarkerDragListener(BaiduMap.OnMarkerDragListener listener);


    /**
     * 设置定位图标点击事件监听者
     * @param listener
     */
    public void	setOnMyLocationClickListener(BaiduMap.OnMyLocationClickListener listener);

    /**
     * 设置地图 Polyline 覆盖物点击事件监听者
     * @param listener
     */
    public void setOnPolylineClickListener(BaiduMap.OnPolylineClickListener listener);

    /**
     * 显示 InfoWindow
     * @param infoWindow
     */
    public void	showInfoWindow(InfoWindow infoWindow);

    /**
     * 控制是否显示底图默认标注
     * @param isShow
     */
    public void	showMapPoi(boolean isShow);

    /**
     * 发起截图请求
     * @param callback
     */
    public void	snapshot(BaiduMap.SnapshotReadyCallback callback);

    /**
     * 发起区域截图请求 注： Rect为null，截全屏
     * @param rect
     * @param callback
     */
    public void	snapshotScope(Rect rect, BaiduMap.SnapshotReadyCallback callback);
}
