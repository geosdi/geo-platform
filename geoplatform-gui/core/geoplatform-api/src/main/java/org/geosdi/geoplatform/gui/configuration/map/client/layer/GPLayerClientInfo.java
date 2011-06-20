/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.gui.configuration.map.client.layer;

import java.io.Serializable;

import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public abstract class GPLayerClientInfo implements Serializable,
        Comparable<IGPFolderElements>, IGPFolderElements {

    /**
     *
     */
    private static final long serialVersionUID = 2098509654335891064L;
    private String dataSource;
    private String abstractText;
    private String layerName;
    private String title;
    private String crs;
    private BboxClientInfo bbox;
    private GPLayerType layerType;
    private int zIndex;
    private long id;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the dataSource
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource
     *            the dataSource to set
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return the crs
     */
    public String getCrs() {
        return crs;
    }

    public String getLayerName() {
        return this.layerName;
    }

    public void setLayerName(String name) {
        this.layerName = name;
    }

    /**
     * @param crs
     *            the crs to set
     */
    public void setCrs(String crs) {
        this.crs = crs;
    }

    /**
     * @return the bbox
     */
    public BboxClientInfo getBbox() {
        return bbox;
    }

    /**
     * @param bbox
     *            the bbox to set
     */
    public void setBbox(BboxClientInfo bbox) {
        this.bbox = bbox;
    }

    /**
     * @return the layerType
     */
    public GPLayerType getLayerType() {
        return layerType;
    }

    /**
     * @param layerType
     *            the layerType to set
     */
    public void setLayerType(GPLayerType layerType) {
        this.layerType = layerType;
    }

    /**
     * @return the zIndex
     */
    @Override
    public int getzIndex() {
        return zIndex;
    }

    /**
     * @param zIndex
     *            the zIndex to set
     */
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GPLayerClientInfo [dataSource=" + dataSource + ", crs=" + crs
                + ", bbox=" + bbox + ", layerType=" + layerType + ", zIndex="
                + zIndex + "]";
    }

    @Override
    public int compareTo(IGPFolderElements o) {
        // TODO Auto-generated method stub
        return o.getzIndex() - getzIndex();
    }

    /**
     * @return the abstractText
     */
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * @param abstractText the abstractText to set
     */
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
