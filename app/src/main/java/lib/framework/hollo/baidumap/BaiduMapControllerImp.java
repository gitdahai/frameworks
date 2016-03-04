package lib.framework.hollo.baidumap;

import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;

/**
 * Created by orson on 16/3/3.
 */
public class BaiduMapControllerImp implements IBaiduMapController {
    private MapView mMapView;

    public BaiduMapControllerImp(MapView mapview){
        this.mMapView = mapview;
    }

    @Override
    public void setLogoPosition(MapLogoPosition pos) {
        if (mMapView == null)
            return;

        switch (pos){
            case LEFT_BOTTOM:    mMapView.setLogoPosition(LogoPosition.logoPostionleftBottom); break;
            case CENTER_BOTTOM:  mMapView.setLogoPosition(LogoPosition.logoPostionCenterBottom);break;
            case RIGHT_BOTTOM:   mMapView.setLogoPosition(LogoPosition.logoPostionRightBottom);break;
            case LEFT_TOP:       mMapView.setLogoPosition(LogoPosition.logoPostionleftTop);break;
            case CENTER_TOP:     mMapView.setLogoPosition(LogoPosition.logoPostionCenterTop);break;
            case RIGHT_TOP:      mMapView.setLogoPosition(LogoPosition.logoPostionRightTop);break;
        }
    }
}
