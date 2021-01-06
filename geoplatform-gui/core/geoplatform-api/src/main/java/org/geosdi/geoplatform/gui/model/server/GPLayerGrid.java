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
package org.geosdi.geoplatform.gui.model.server;

import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.geosdi.geoplatform.gui.model.UUIDGenerator;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

import java.util.ArrayList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPLayerGrid extends GeoPlatformBeanModel implements GPLayerBean {

    private static final long serialVersionUID = 7746607426284214904L;

    public enum GPLayerBeanKeyValue {

        GPLAYER_NAME("name"),
        GPLAYER_ABSTRACT_TEXT("abstractText"),
        GPLAYER_LABEL("label");

        private String value;

        GPLayerBeanKeyValue(String theValue) {
            this.value = theValue;
        }

        public String getValue() {
            return value;
        }
    }
    private String UUID = UUIDGenerator.uuid();
    private Long id;
    private String name;
    private String alias;
    private String label;
    private String title;
    private String abstractText;
    private String dataSource;
    private String crs;
    private BBoxClientInfo bbox;
    private GPLayerType layerType;
    private ArrayList<GPStyleStringBeanModel> styles;
    private String cqlFilter;
    private String timeFilter;
    private String variableTimeFilter;

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
        super.set(GPLayerBeanKeyValue.GPLAYER_NAME.getValue(), this.name);
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
     * @return the label
     */
    @Override
    public String getLabel() {
        return this.label;
    }

    /**
     * @param label the label to set
     */
    @Override
    public void setLabel(String label) {
        this.label = label;
        super.set(GPLayerBeanKeyValue.GPLAYER_LABEL.getValue(), this.label);
    }

    /**
     * @return the title
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title the title to set
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the abstractText
     */
    @Override
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * @param abstractText the abstractText to set
     */
    @Override
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
        super.set(GPLayerBeanKeyValue.GPLAYER_ABSTRACT_TEXT.getValue(), this.abstractText);
    }

    /**
     * @return the dataSource
     */
    @Override
    public String getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource the dataSource to set
     */
    @Override
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return the crs
     */
    @Override
    public String getCrs() {
        return crs;
    }

    /**
     * @param crs the crs to set
     */
    @Override
    public void setCrs(String crs) {
        this.crs = crs;
    }

    /**
     * @return the bbox
     */
    @Override
    public BBoxClientInfo getBbox() {
        return bbox;
    }

    /**
     * @param bbox the bbox to set
     */
    @Override
    public void setBbox(BBoxClientInfo bbox) {
        this.bbox = bbox;
    }

    @Override
    public ArrayList<GPStyleStringBeanModel> getStyles() {
        return this.styles;
    }

    @Override
    public void setStyles(ArrayList<GPStyleStringBeanModel> styles) {
        this.styles = styles;
    }

    /**
     * @return the layerType
     */
    @Override
    public GPLayerType getLayerType() {
        return layerType;
    }

    /**
     * @param layerType the layerType to set
     */
    @Override
    public void setLayerType(GPLayerType layerType) {
        this.layerType = layerType;
    }

    @Override
    public String getCqlFilter() {
        return cqlFilter;
    }

    @Override
    public String getVariableTimeFilter() {
        return this.variableTimeFilter;
    }

    @Override
    public void setVariableTimeFilter(String variableTimeFilter) {
        this.variableTimeFilter = variableTimeFilter;
    }

    @Override
    public String getTimeFilter() {
        return timeFilter;
    }

    @Override
    public void setTimeFilter(String timeFilter) {
        this.timeFilter = timeFilter;
    }

    @Override
    public void setCqlFilter(String cqlFilter) {
        this.cqlFilter = cqlFilter;
    }

    @Override
    public int getzIndex() {
        return 0;
    }

    /**
     * @return the UUID
     */
    @Override
    public String getUUID() {
        return UUID;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "id = " + id
                + ", name = " + name
                + ", abstractText = " + abstractText
                + ", dataSource = " + dataSource
                + ", crs = " + crs
                + ", bbox = " + bbox
                + ", layerType = " + layerType;
    }
}