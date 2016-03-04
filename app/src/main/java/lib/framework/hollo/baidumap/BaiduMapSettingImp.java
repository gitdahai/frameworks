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
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.List;

/**
 * Created by orson on 16/3/3.
 */
public class BaiduMapSettingImp implements IBaiduMapSetting{
    private BaiduMap mBaiduMap;
    private UiSettings mUiSettings;

    public BaiduMapSettingImp(BaiduMap baidumap){
        this.mBaiduMap = baidumap;

        if (mBaiduMap != null)
            mUiSettings = mBaiduMap.getUiSettings();
    }

    @Override
    public void setMapType(BaiduMapType maptype) {
        if (mBaiduMap == null)
            return;

        switch (maptype){
            case MAP_TYPE_NONE: mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);break;
            case MAP_TYPE_NORMAL:mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);break;
            case MAP_TYPE_SATELLITE:mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);break;
        }
    }

    @Override
    public void setTrafficEnabled(boolean enabledable) {
        if (mBaiduMap != null)
            mBaiduMap.setTrafficEnabled(enabledable);
    }

    @Override
    public void setBaiduHeatMapEnabled(boolean enabledable) {
        if (mBaiduMap != null)
            mBaiduMap.setBaiduHeatMapEnabled(enabledable);
    }

    @Override
    public void setLogoPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        if (mBaiduMap != null)
            mBaiduMap.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @Override
    public void setCompassEndable(boolean endable) {
        if (mBaiduMap != null)
            mBaiduMap.getUiSettings().setCompassEnabled(endable);
    }

    @Override
    public void setCompassPosition(Point point) {
        if (mBaiduMap != null)
            mBaiduMap.setCompassPosition(point);
    }

    @Override
    public void setAllGesturesEnabled(boolean enabledable) {
        if (mUiSettings != null)
            mUiSettings.setAllGesturesEnabled(enabledable);
    }

    @Override
    public void setOverlookingGesturesEnabled(boolean enabledable) {
        if (mUiSettings != null)
            mUiSettings.setOverlookingGesturesEnabled(enabledable);
    }

    @Override
    public void setRotateGesturesEnabled(boolean enabledable) {
        if (mUiSettings != null)
            mUiSettings.setRotateGesturesEnabled(enabledable);
    }

    @Override
    public void setScrollGesturesEnabled(boolean enabledable) {
        if (mUiSettings != null)
            mUiSettings.setScrollGesturesEnabled(enabledable);
    }

    @Override
    public void setZoomGesturesEnabled(boolean enabledable) {
        if (mUiSettings != null)
            mUiSettings.setZoomGesturesEnabled(enabledable);
    }

    @Override
    public boolean isCompassEnabled() {
        return mUiSettings == null ? false : mUiSettings.isCompassEnabled();
    }

    @Override
    public boolean isOverlookingGesturesEnabled() {
        return mUiSettings == null ? false : mUiSettings.isOverlookingGesturesEnabled();
    }

    @Override
    public boolean isRotateGesturesEnabled() {
        return mUiSettings == null ? false : mUiSettings.isRotateGesturesEnabled();
    }

    @Override
    public boolean isScrollGesturesEnabled() {
        return mUiSettings == null ? false : mUiSettings.isScrollGesturesEnabled();
    }

    @Override
    public boolean isZoomGesturesEnabled() {
        return mUiSettings == null ? false : mUiSettings.isZoomGesturesEnabled();
    }

    @Override
    public void addHeatMap(HeatMap heatmap) {

    }

    @Override
    public Overlay addOverlay(OverlayOptions options) {
        return mBaiduMap == null ? null : mBaiduMap.addOverlay(options);
    }

    @Override
    public List<Overlay> addOverlays(List<OverlayOptions> options) {
        return mBaiduMap == null ? null : mBaiduMap.addOverlays(options);
    }

    @Override
    public TileOverlay addTileLayer(TileOverlayOptions overlayOptions) {
        return mBaiduMap == null ? null : mBaiduMap.addTileLayer(overlayOptions);
    }

    @Override
    public void animateMapStatus(MapStatusUpdate update) {
        if (mBaiduMap != null)
            mBaiduMap.animateMapStatus(update);
    }

    @Override
    public void animateMapStatus(MapStatusUpdate update, int durationMs) {
        if (mBaiduMap != null)
            mBaiduMap.animateMapStatus(update, durationMs);
    }

    @Override
    public void clear() {
        if (mBaiduMap != null)
            mBaiduMap.clear();
    }

    @Override
    public Point getCompassPosition() {
        return mBaiduMap == null ? null : mBaiduMap.getCompassPosition();
    }

    @Override
    public MyLocationConfiguration getLocationConfigeration() {
        return mBaiduMap == null ? null : mBaiduMap.getLocationConfigeration();
    }

    @Override
    public MyLocationData getLocationData() {
        return mBaiduMap == null ? null : mBaiduMap.getLocationData();
    }

    @Override
    public MapStatus getMapStatus() {
        return mBaiduMap == null ? null : mBaiduMap.getMapStatus();
    }

    @Override
    public LatLngBounds getMapStatusLimit() {
        return mBaiduMap == null ? null : mBaiduMap.getMapStatusLimit();
    }

    @Override
    public BaiduMapType getMapType() {
        if (mBaiduMap == null)
            return null;

        BaiduMapType result = null;

        switch (mBaiduMap.getMapType()){
            case BaiduMap.MAP_TYPE_NONE:    result = BaiduMapType.MAP_TYPE_NONE;    break;
            case BaiduMap.MAP_TYPE_NORMAL:  result = BaiduMapType.MAP_TYPE_NORMAL;  break;
            case BaiduMap.MAP_TYPE_SATELLITE:   result = BaiduMapType.MAP_TYPE_SATELLITE; break;
        }

        return result;
    }

    @Override
    public List<Marker> getMarkersInBounds(LatLngBounds bounds) {
        return mBaiduMap == null ? null : mBaiduMap.getMarkersInBounds(bounds);
    }

    @Override
    public float getMaxZoomLevel() {
        return mBaiduMap == null ? null : mBaiduMap.getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        return mBaiduMap == null ? null : mBaiduMap.getMinZoomLevel();
    }

    @Override
    public Projection getProjection() {
        return mBaiduMap == null ? null : mBaiduMap.getProjection();
    }

    @Override
    public void hideInfoWindow() {
        if (mBaiduMap != null)
            mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean isBaiduHeatMapEnabled() {
        return mBaiduMap == null ? false : mBaiduMap.isBaiduHeatMapEnabled();
    }

    @Override
    public boolean isBuildingsEnabled() {
        return mBaiduMap == null ? false : mBaiduMap.isBuildingsEnabled();
    }

    @Override
    public boolean isMyLocationEnabled() {
        return mBaiduMap == null ? false : mBaiduMap.isMyLocationEnabled();
    }

    @Override
    public boolean isSupportBaiduHeatMap() {
        return mBaiduMap == null ? false : mBaiduMap.isSupportBaiduHeatMap();
    }

    @Override
    public boolean isTrafficEnabled() {
        return mBaiduMap == null ? false : mBaiduMap.isTrafficEnabled();
    }

    @Override
    public void removeMarkerClickListener(BaiduMap.OnMarkerClickListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.removeMarkerClickListener(listener);
    }

    @Override
    public void setMapStatus(MapStatusUpdate update) {
        if (mBaiduMap != null)
            mBaiduMap.setMapStatus(update);
    }

    @Override
    public void setMapStatusLimits(LatLngBounds bounds) {
        if (mBaiduMap != null)
            mBaiduMap.setMapStatusLimits(bounds);
    }

    @Override
    public void setMaxAndMinZoomLevel(float max, float min) {
        if (mBaiduMap != null)
            mBaiduMap.setMaxAndMinZoomLevel(max, min);
    }

    @Override
    public void setMyLocationConfigeration(MyLocationConfiguration configeration) {
        if (mBaiduMap != null)
            mBaiduMap.setMyLocationConfigeration(configeration);
    }

    @Override
    public void setMyLocationData(MyLocationData data) {
        if (mBaiduMap != null)
            mBaiduMap.setMyLocationData(data);
    }

    @Override
    public void setMyLocationEnabled(boolean enabled) {
        if (mBaiduMap != null)
            mBaiduMap.setMyLocationEnabled(enabled);
    }

    @Override
    public void setOnMapClickListener(BaiduMap.OnMapClickListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMapClickListener(listener);
    }

    @Override
    public void setOnMapDoubleClickListener(BaiduMap.OnMapDoubleClickListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMapDoubleClickListener(listener);
    }

    @Override
    public void setOnMapDrawFrameCallback(BaiduMap.OnMapDrawFrameCallback callback) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMapDrawFrameCallback(callback);
    }

    @Override
    public void setOnMapLoadedCallback(BaiduMap.OnMapLoadedCallback callback) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMapLoadedCallback(callback);
    }

    @Override
    public void setOnMapLongClickListener(BaiduMap.OnMapLongClickListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMapLongClickListener(listener);
    }

    @Override
    public void setOnMapStatusChangeListener(BaiduMap.OnMapStatusChangeListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMapStatusChangeListener(listener);
    }

    @Override
    public void setOnMapTouchListener(BaiduMap.OnMapTouchListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMapTouchListener(listener);
    }

    @Override
    public void setOnMarkerClickListener(BaiduMap.OnMarkerClickListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMarkerClickListener(listener);
    }

    @Override
    public void setOnMarkerDragListener(BaiduMap.OnMarkerDragListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMarkerDragListener(listener);
    }

    @Override
    public void setOnMyLocationClickListener(BaiduMap.OnMyLocationClickListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnMyLocationClickListener(listener);
    }

    @Override
    public void setOnPolylineClickListener(BaiduMap.OnPolylineClickListener listener) {
        if (mBaiduMap != null)
            mBaiduMap.setOnPolylineClickListener(listener);
    }

    @Override
    public void showInfoWindow(InfoWindow infoWindow) {
        if (mBaiduMap != null)
            mBaiduMap.showInfoWindow(infoWindow);
    }

    @Override
    public void showMapPoi(boolean isShow) {
        if (mBaiduMap != null)
            mBaiduMap.showMapPoi(isShow);
    }

    @Override
    public void snapshot(BaiduMap.SnapshotReadyCallback callback) {
        if (mBaiduMap != null)
            mBaiduMap.snapshot(callback);
    }

    @Override
    public void snapshotScope(Rect rect, BaiduMap.SnapshotReadyCallback callback) {
        if (mBaiduMap != null)
            mBaiduMap.snapshotScope(rect, callback);
    }
}
