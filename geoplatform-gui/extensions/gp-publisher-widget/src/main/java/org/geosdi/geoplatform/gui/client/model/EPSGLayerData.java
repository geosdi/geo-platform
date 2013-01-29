/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.model;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class EPSGLayerData extends GeoPlatformBeanModel {

    public static final String NAME = "name";
    public static final String CRS = "crs";
    public static final String IS_SHAPE = "isShape";
    public static final String STYLE_NAME = "styleName";
    private static final long serialVersionUID = 3153334351994515962L;

    public EPSGLayerData() {
    }

    public EPSGLayerData(String featureName, String epsgCode, String styleName,
            boolean isShape) {
        this.setFeatureName(featureName);
        this.setEpsgCode(epsgCode);
        this.setStyleName(styleName);
        this.setIsShape(isShape);
    }

    public String getEpsgCode() {
        return super.get(EPSGLayerData.CRS);
    }

    public final void setEpsgCode(String epsgCode) {
        super.set(EPSGLayerData.CRS, epsgCode);
    }

    public String getFeatureName() {
        return super.get(EPSGLayerData.NAME);
    }

    public final void setFeatureName(String featureName) {
        super.set(EPSGLayerData.NAME, featureName);
    }

    public Boolean isIsShape() {
        return super.get(EPSGLayerData.IS_SHAPE);
    }

    public final void setIsShape(boolean isShape) {
        super.set(EPSGLayerData.IS_SHAPE, isShape);
    }

    public String getStyleName() {
        return super.get(EPSGLayerData.STYLE_NAME);
    }

    public final void setStyleName(String styleName) {
        super.set(EPSGLayerData.STYLE_NAME, styleName);
    }
}
