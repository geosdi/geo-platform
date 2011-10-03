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
package org.geosdi.geoplatform.gui.client.widget.tab.binding;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.GPMementoSaveCache;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.impl.map.event.StyleLayerMapEvent;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel.GPStyleStringKeyValue;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPLayerStyleBinding extends GeoPlatformBindingWidget<GPLayerBean> {

    private ComboBox<GPStyleStringBeanModel> comboBox;
    private ListStore<GPStyleStringBeanModel> comboBoxStore;
    private GPRasterComboStyleBinding styleBinding;

    @Override
    public FormPanel createFormPanel() {
        this.comboBox = new ComboBox<GPStyleStringBeanModel>() {

            @Override
            protected void onSelect(GPStyleStringBeanModel model, int index) {
                super.onSelect(model, index);
                styleBinding.updateModel();
            }
        };
        this.comboBoxStore = new ListStore<GPStyleStringBeanModel>();
        this.comboBox.setStore(this.comboBoxStore);
        this.comboBox.setEmptyText("Select a style...");
        this.comboBox.setTypeAhead(true);
        this.comboBox.setTriggerAction(TriggerAction.ALL);
        this.comboBox.setDisplayField(GPStyleStringKeyValue.StyleString.getValue());
        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading("Select a style");
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);
        fp.setFrame(true);
        fieldSet.setLayout(new FlowLayout());
        fieldSet.add(this.comboBox, new BorderLayoutData(LayoutRegion.EAST));
        fp.add(fieldSet);
        this.styleBinding = new GPRasterComboStyleBinding(this.comboBox,
                GPStyleStringKeyValue.StyleString.toString());
        return fp;
    }

    @Override
    public void bindModel(GPLayerBean model) {
        super.bindModel(model);
        RasterTreeNode raster = (RasterTreeNode) model;
        if (raster.getStyles().isEmpty()) {
            this.comboBox.setEnabled(false);
        } else {
            this.comboBoxStore.add(raster.getStyles());
            this.comboBox.setValue(raster.getStyles().get(0));
        }
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(this.styleBinding);
    }

    private class GPRasterComboStyleBinding extends GPFieldBinding {

        private StyleLayerMapEvent styleEvent = new StyleLayerMapEvent();

        public GPRasterComboStyleBinding(Field field, String property) {
            super(field, property);
        }

        @Override //From model to view
        public void updateField(boolean updateOriginalValue) {
//            System.out.println("Updating view");
            RasterTreeNode raster = ((RasterTreeNode) model);
            if (!raster.getStyles().isEmpty()) {
                comboBoxStore.removeAll();
                comboBoxStore.add(raster.getStyles());
                comboBox.setValue(raster.getStyles().get(0));
            }
        }

        @Override //From view to model
        public void setModelProperty(Object val) {
//            System.out.println("Updating model");
            if (val != null && val instanceof GPStyleStringBeanModel) {
                GPStyleStringBeanModel styleString = (GPStyleStringBeanModel) val;
                RasterTreeNode raster = (RasterTreeNode) model;
                if (!styleString.equals(raster.getStyles().get(0))) {
                    GPMementoSaveCache.getInstance().copyOriginalProperties((GPLayerTreeModel) model);
                    ArrayList<GPStyleStringBeanModel> rasterList = raster.getStyles();
                    rasterList.remove(styleString);
                    rasterList.add(0, styleString);
                    raster.setStyles(rasterList);
                    comboBoxStore.removeAll();
                    comboBoxStore.add(raster.getStyles());
                    styleEvent.setStyle(styleString.getStyleString());
                    styleEvent.setLayerBean(raster);
                    GPHandlerManager.fireEvent(styleEvent);
                }
            }
        }
    }
}
