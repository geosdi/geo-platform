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
package org.geosdi.geoplatform.gui.client.model;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.json.client.JsonReader;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.gui.shared.publisher.LayerPublishAction;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class PreviewLayer implements GPLayerBean, Serializable {

    private static final long serialVersionUID = -4293623361000442644L;

    public interface PreviewLayerReader extends JsonReader<PreviewLayer> {
    }
    public static final PreviewLayerReader JSON = GWT.create(PreviewLayerReader.class);
    //
    private String crs;
    private String title;
    private String dataSource;
    private String workspace;
    private double lowerX;
    private double lowerY;
    private double upperX;
    private double upperY;
    private String message;
    private String fileName;
    private String styleName;
    private boolean isShape;
    private List<LayerPublishAction> alreadyExists;
    @Transient
    private Long id;
    @Transient
    private String alias;
    @Transient
    private String label;
    @Transient
    private String name;
    @Transient
    private String abstractText;
    @Transient
    private BBoxClientInfo bbox;
    @Transient
    private GPLayerType layerType;

    public PreviewLayer() {
    }

    public PreviewLayer(String label, String title, String name, String abstractText,
            String dataSource, String crs, BBoxClientInfo bbox, GPLayerType layerType,
            String styleName, boolean isShape, List<LayerPublishAction> alreadyExists) {
        this.label = label;
        this.title = title;
        this.name = name;
        this.abstractText = abstractText;
        this.dataSource = dataSource;
        this.crs = crs;
        this.bbox = bbox;
        this.layerType = layerType;
        this.styleName = styleName;
        this.isShape = isShape;
        this.alreadyExists = alreadyExists;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isIsShape() {
        return isShape;
    }

    public void setIsShape(boolean isShape) {
        this.isShape = isShape;
    }

    public List<LayerPublishAction> getAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(List<LayerPublishAction> alreadyExists) {
        this.alreadyExists = alreadyExists;
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
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the alias
     */
    @Override
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the styleName
     */
    public String getStyleName() {
        return styleName;
    }

    /**
     * @param styleName the styleName to set
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @Override
    public ArrayList<GPStyleStringBeanModel> getStyles() {
        ArrayList<GPStyleStringBeanModel> styleList = new ArrayList<GPStyleStringBeanModel>();
        GPStyleStringBeanModel style = new GPStyleStringBeanModel();
        style.setStyleString(this.styleName);
        styleList.add(style);
        return styleList;
    }

    @Override
    public String getLabel() {
        return (this.label != null ? this.label : this.title);
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
        return (this.abstractText != null ? this.abstractText : this.title);
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
    public BBoxClientInfo getBbox() {
        if (this.bbox == null) {
            this.bbox = new BBoxClientInfo(this.lowerX, this.lowerY, this.upperX,
                    this.upperY);
        }
        return this.bbox;
    }

    @Override
    public void setBbox(BBoxClientInfo bbox) {
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
        return 0;
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

    @Override
    public void setStyles(ArrayList<GPStyleStringBeanModel> styles) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCqlFilter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCqlFilter(String cqlFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTimeFilter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setTimeFilter(String timeFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUUID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getVariableTimeFilter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setVariableTimeFilter(String variableTimeFilter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
