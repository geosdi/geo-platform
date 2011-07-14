/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.json.client.JsonReader;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class WMSPreview {

    public interface WMSPreviewReader extends JsonReader<WMSPreview> {
    }
    public static final WMSPreviewReader JSON = GWT.create(WMSPreviewReader.class);
    private String crs;
    private String layerName;
    private String url;
    private String workspace;
    private double lowerX;
    private double lowerY;
    private double upperX;
    private double upperY;

    @Override
    public String toString() {
        return "WMSPreview{" + "crs=" + getCrs() + ", layerName=" + getLayerName() + ", url=" + getUrl() + ", workspace=" + getWorkspace() + ", lowerX=" + getLowerX() + ", lowerY=" + getLowerY() + ", upperX=" + getUpperX() + ", upperY=" + getUpperY() + '}';
    }

    /**
     * @return the crs
     */
    public String getCrs() {
        return crs;
    }

    /**
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the workspace
     */
    public String getWorkspace() {
        return workspace;
    }

    /**
     * @return the lowerX
     */
    public double getLowerX() {
        return lowerX;
    }

    /**
     * @return the lowerY
     */
    public double getLowerY() {
        return lowerY;
    }

    /**
     * @return the upperX
     */
    public double getUpperX() {
        return upperX;
    }

    /**
     * @return the upperY
     */
    public double getUpperY() {
        return upperY;
    }
}