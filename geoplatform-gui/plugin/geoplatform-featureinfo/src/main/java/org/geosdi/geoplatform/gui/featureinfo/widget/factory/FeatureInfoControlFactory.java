/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.gui.featureinfo.widget.factory;

import com.extjs.gxt.ui.client.Registry;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.enumeration.GlobalRegistryEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.gwtopenmaps.openlayers.client.control.GetFeatureInfoVendorParam;
import org.gwtopenmaps.openlayers.client.control.WMSGetFeatureInfo;
import org.gwtopenmaps.openlayers.client.control.WMSGetFeatureInfoOptions;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureInfoControlFactory {

    /**
     *
     * @param urlServer
     * @return
     */
    public static WMSGetFeatureInfo createControl(String urlServer) {
        WMSGetFeatureInfoOptions options = new WMSGetFeatureInfoOptions();

        /**
         * FIX MEEEEEEEEEEEEEEEEEE *
         */
        if (urlServer.equalsIgnoreCase("http://10.220.154.25/geowebcache/service/wms")) {
            options.setURL("http://10.220.154.25/geoserver/wms");
        } else {
            IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
            String authKeyValue = accountDetail.getAuthkey();
            if (authKeyValue != null && !authKeyValue.equals("")) {
                GetFeatureInfoVendorParam param = new GetFeatureInfoVendorParam();
                param.setParameter(GlobalRegistryEnum.AUTH_KEY.getValue(), authKeyValue);
                options.setVendorParams(param);
            }
            options.setURL(urlServer);
        }
        options.setTitle("Query visible layers");
        options.setQueryVisible(true);
        return new WMSGetFeatureInfo(options);
    }
}
