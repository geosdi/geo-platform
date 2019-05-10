package org.geosdi.geoplatform.gui.client.widget.tab;

import com.extjs.gxt.ui.client.Registry;
import org.geosdi.geoplatform.gui.client.i18n.MapLiteModuleConstants;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.factory.map.GPApplicationMap;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class Utility {

    public static String generateMapLiteURL() {
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        GPClientProject clientProject = (GPClientProject) Registry.get(UserSessionEnum.CURRENT_PROJECT_ON_TREE.name());
        StringBuilder mapLiteURL = new StringBuilder();
        mapLiteURL.append(MapLiteModuleConstants.INSTANCE.MAP_LITE_APPLICATION_URL());
        mapLiteURL.append("?mapID=");
        mapLiteURL.append(clientProject.getId());
        mapLiteURL.append("-");
        mapLiteURL.append(accountDetail.getId());
        mapLiteURL.append("&x=");
        Map map = GPApplicationMap.getInstance().getApplicationMap().getMap();
        LonLat lonLat = map.getCenter();
        lonLat.transform(map.getProjection(), GPCoordinateReferenceSystem.WGS_84.getCode());
        mapLiteURL.append(lonLat.lon());
        mapLiteURL.append("&y=");
        mapLiteURL.append(lonLat.lat());
        mapLiteURL.append("&zoom=");
        mapLiteURL.append(map.getZoom());
        mapLiteURL.append("&baseMap=");
        mapLiteURL.append(accountDetail.getBaseLayer());
        return mapLiteURL.toString();
    }

}
