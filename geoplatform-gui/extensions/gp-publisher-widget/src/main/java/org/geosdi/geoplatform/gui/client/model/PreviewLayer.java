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
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.persistence.Transient;
import name.pehl.piriti.json.client.JsonReader;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType;
import org.geosdi.geoplatform.gui.model.GPLayerBean;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class PreviewLayer implements GPLayerBean, Serializable {

    private static final long serialVersionUID = 1223233615230469683L;

    public interface PreviewLayerReader extends JsonReader<PreviewLayer> {
    }
    public static final PreviewLayerReader JSON = GWT.create(PreviewLayerReader.class);
    private String crs;
    private String title;
    private String dataSource;
    private String workspace;
    private double lowerX;
    private double lowerY;
    private double upperX;
    private double upperY;
    private String message;
    @Transient
    private long id;
    @Transient
    private String label;
    @Transient
    private String name;
    @Transient
    private String abstractText;
    @Transient
    private BboxClientInfo bbox;
    @Transient
    private GPLayerType layerType;

    public PreviewLayer() {
    }

    public PreviewLayer(String label, String title, String name, String abstractText, String dataSource, String crs, BboxClientInfo bbox, GPLayerType layerType) {
        this.label = label;
        this.title = title;
        this.name = name;
        this.abstractText = abstractText;
        this.dataSource = dataSource;
        this.crs = crs;
        this.bbox = bbox;
        this.layerType = layerType;
    }
    
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getName() {
        return this.workspace + ":" + this.title;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAbstractText() {
        return this.abstractText;
    }

    @Override
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    @Override
    public String getDataSource() {
        return this.dataSource;
    }

    @Override
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getCrs() {
        return this.crs;
    }

    @Override
    public void setCrs(String crs) {
        this.crs = crs;
    }

    @Override
    public BboxClientInfo getBbox() {
        if (this.bbox == null) {
            this.bbox = new BboxClientInfo(this.lowerX, this.lowerY, this.upperX, this.upperY);
        }
        return this.bbox;
    }

    @Override
    public void setBbox(BboxClientInfo bbox) {
        this.bbox = bbox;
    }

    @Override
    public GPLayerType getLayerType() {
        return this.layerType;
    }

    @Override
    public void setLayerType(GPLayerType layerType) {
        this.layerType = layerType;
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

    /**
     * @param workspace the workspace to set
     */
    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    /**
     * @param lowerX the lowerX to set
     */
    public void setLowerX(double lowerX) {
        this.lowerX = lowerX;
    }

    /**
     * @param lowerY the lowerY to set
     */
    public void setLowerY(double lowerY) {
        this.lowerY = lowerY;
    }

    /**
     * @param upperX the upperX to set
     */
    public void setUpperX(double upperX) {
        this.upperX = upperX;
    }

    /**
     * @param upperY the upperY to set
     */
    public void setUpperY(double upperY) {
        this.upperY = upperY;
    }

    @Override
    public int getzIndex() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <X> X get(String property) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> getProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<String> getPropertyNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <X> X remove(String property) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <X> X set(String property, X value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
