/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.featureinfo.widget.factory;

import com.extjs.gxt.ui.client.Registry;
import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.enumeration.GlobalRegistryEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.gwtopenmaps.openlayers.client.control.GetFeatureInfoVendorParam;
import org.gwtopenmaps.openlayers.client.control.WMSGetFeatureInfo;
import org.gwtopenmaps.openlayers.client.control.WMSGetFeatureInfoOptions;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureInfoControlFactory {

    FeatureInfoControlFactory() {
    }

    /**
     *
     * @param layer
     * @return
     */
    public static WMSGetFeatureInfo createControl(Layer layer) {
        WMSGetFeatureInfoOptions options = new WMSGetFeatureInfoOptions();
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        String authKeyValue = accountDetail.getAuthkey();
        GetFeatureInfoVendorParam param = new GetFeatureInfoVendorParam();
        if (authKeyValue != null && !authKeyValue.equals("")) {
            param.setParameter(GlobalRegistryEnum.AUTH_KEY.getValue(), authKeyValue);
        }
        WMS wms = WMS.narrowToLayer(layer.getJSObject());
        WMSParams params = wms.getParams();
        List<String> propertyNameList = Lists.<String>newArrayList(params.getJSObject().getPropertyNames().split(","));
        List<String> propertyValueList = Lists.<String>newArrayList(params.getJSObject().getPropertyValues().split(","));
        int i = 0;
        for (String propertyName : GPSharedUtils.safeList(propertyNameList)) {
            String propertyValue = null;
            if (i < propertyValueList.size()) {
                propertyValue = propertyValueList.get(i);
            }
            if (propertyName != null && !propertyName.isEmpty() && !propertyName.equalsIgnoreCase("REQUEST")
                    && propertyValue != null && !propertyValue.isEmpty() && !propertyValue.equalsIgnoreCase("null")) {
                param.setParameter(propertyName, propertyValue);
//                System.out.println("Setted parameter: " + propertyName + " - "
//                        + propertyValue);
            }
            i++;
        }
        options.setVendorParams(param);
        options.setLayers(new WMS[]{wms});
        options.setTitle("Query visible layers");
        options.setQueryVisible(true);
        return new WMSGetFeatureInfo(options);
    }

}
