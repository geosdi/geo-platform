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
package org.geosdi.geoplatform.gui.model.tree;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.configuration.composite.GPTreeCompositeType;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerClientInfo;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.state.IGPLayerTreeState;
import org.geosdi.geoplatform.gui.observable.Observable;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

import java.util.ArrayList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public abstract class GPLayerTreeModel extends GPBeanTreeModel implements GPLayerBean {

    private static final long serialVersionUID = -6964624685883651246L;

    public enum GPLayerKeyValue {

        TITLE("title"), ABSTRACT("abstractText"),
        ALIAS("alias"), SERVER("dataSource");
        //
        private String value;

        GPLayerKeyValue(String theValue) {
            this.value = theValue;
        }

        @Override
        public String toString() {
            return this.value;
        }

    }

    private String title;
    private String name;
    private String alias;
    private String abstractText;
    private String dataSource;
    private String crs;
    private BBoxClientInfo bbox;
    private GPLayerType layerType;
    private ArrayList<GPStyleStringBeanModel> styles = Lists.<GPStyleStringBeanModel>newArrayList();
    private String cqlFilter;
    private String timeFilter;
    private String variableTimeFilter;
    protected IGPLayerTreeState state;
    //
    private ObservableFolderTreeNode observable = new ObservableFolderTreeNode();

    protected GPLayerTreeModel() {
    }

    protected GPLayerTreeModel(GPLayerClientInfo layer) {
        super(layer.getId(), layer.getzIndex(), layer.isChecked());
        setTitle(layer.getTitle());
        this.name = layer.getLayerName();
        setAbstractText(layer.getAbstractText());
        setDataSource(layer.getDataSource());
        setAlias(layer.getAlias());
        this.crs = layer.getCrs();
        this.bbox = layer.getBbox();
        this.layerType = layer.getLayerType();
        this.setCqlFilter(layer.getCqlFilter());
        this.variableTimeFilter = layer.getVariableTimeFilter();
        this.setTimeFilter(layer.getTimeFilter());
    }

    /**
     * @return the title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    @Override
    public final void setTitle(String title) {
        this.title = title;
        super.set(GPLayerKeyValue.TITLE.toString(), this.title);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setStyles(ArrayList<GPStyleStringBeanModel> styles) {
        this.styles = styles;
    }

    @Override
    public ArrayList<GPStyleStringBeanModel> getStyles() {
        return this.styles;
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
    public final void setAlias(String alias) {
        this.alias = alias;
        super.set(GPLayerKeyValue.ALIAS.toString(), this.alias);
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
    public final void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
        super.set(GPLayerKeyValue.ABSTRACT.toString(), ((this.abstractText != null) ? this.abstractText : ""));
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
    public final void setDataSource(String dataSource) {
        this.dataSource = dataSource;
        super.set(GPLayerKeyValue.SERVER.toString(), this.dataSource);
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
        return variableTimeFilter;
    }

    @Override
    public void setVariableTimeFilter(String variableTimeFilter) {
        this.variableTimeFilter = variableTimeFilter;
    }

    @Override
    public void setCqlFilter(String cqlFilter) {
        this.cqlFilter = cqlFilter;
    }

    @Override
    public String getTimeFilter() {
        return this.timeFilter;
    }

    @Override
    public void setTimeFilter(String timeFilter) {
        this.timeFilter = timeFilter;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
        observable.setChanged();
        observable.notifyObservers(id);
    }

    @Override
    public GPTreeCompositeType getTreeCompositeType() {
        return GPTreeCompositeType.LEAF;
    }

    @Override
    public String getLabel() {
        return ((getAlias() != null) && (!getAlias().trim().isEmpty())) ? getAlias() : super.getLabel();
    }

    /**
     * @param state
     */
    public void setState(IGPLayerTreeState state) {
        this.state = state;
    }

    public abstract IGPLayerTreeState getState();

    public abstract void setRefreshTime(int refreshTime);

    public ObservableFolderTreeNode getObservable() {
        return observable;
    }

    public void setObservable(ObservableFolderTreeNode observable) {
        this.observable = observable;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", title = " + title
                + ", name = " + name
                + ", alias = " + alias
                + ", abstractText = " + abstractText
                + ", dataSource = " + dataSource
                + ", crs = " + crs
                + ", bbox = " + bbox
                + ", layerType = " + layerType
                + ", cqlFilter = " + cqlFilter
                + ", timeFilter = " + timeFilter
                + ", variableTimeFilter = " + variableTimeFilter;
    }

    public class ObservableFolderTreeNode extends Observable {

        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

    }
}
