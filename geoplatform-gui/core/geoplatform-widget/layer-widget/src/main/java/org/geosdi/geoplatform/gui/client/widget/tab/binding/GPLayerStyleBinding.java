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
package org.geosdi.geoplatform.gui.client.widget.tab.binding;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.impl.map.event.LayerMapStyleRefreshEvent;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel.GPStyleStringKeyValue;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

import java.util.ArrayList;

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
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);
        fp.setFrame(true);

        this.comboBox = new ComboBox<GPStyleStringBeanModel>() {
            @Override
            protected void onSelect(GPStyleStringBeanModel model, int index) {
                super.onSelect(model, index);
                styleBinding.updateModel();
            }
        };

        this.comboBoxStore = new ListStore<GPStyleStringBeanModel>();
        this.comboBox.setWidth(200);
        this.comboBox.setStore(this.comboBoxStore);
        this.comboBox.setEmptyText(LayerModuleConstants.INSTANCE.GPLayerStyleBinding_comboEmptyText());
        this.comboBox.setTypeAhead(true);
        this.comboBox.setTriggerAction(TriggerAction.ALL);
        this.comboBox.setDisplayField(GPStyleStringKeyValue.StyleString.getValue());

        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(LayerModuleConstants.INSTANCE.GPLayerStyleBinding_fieldSetHeadingText());

        fieldSet.add(this.comboBox);
        fp.add(fieldSet);
        this.styleBinding = new GPRasterComboStyleBinding(this.comboBox,
                GPStyleStringKeyValue.StyleString.toString());
        return fp;
    }

    @Override
    public void bindModel(GPLayerBean model) {
        super.bindModel(model);
        RasterTreeNode raster = (RasterTreeNode) model;
        if ((raster.getStyles() != null) && (raster.getStyles().isEmpty())) {
            this.comboBox.setEnabled(false);
        } else {
            this.comboBox.setEnabled(true);
            this.comboBoxStore.add(raster.getStyles());
            this.comboBox.setValue(raster.getStyles().get(0));
        }
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(this.styleBinding);
    }

    private class GPRasterComboStyleBinding extends GPFieldBinding {

        private LayerMapStyleRefreshEvent styleEvent = new LayerMapStyleRefreshEvent();

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
                    IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
                    AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(raster);
                    ArrayList<GPStyleStringBeanModel> rasterList = raster.getStyles();
                    rasterList.remove(styleString);
                    rasterList.add(0, styleString);
                    raster.setStyles(rasterList);
                    mementoSave.putOriginalPropertiesInCache(memento);
                    comboBoxStore.removeAll();
                    comboBoxStore.add(raster.getStyles());
                    styleEvent.setStyle(styleString.getStyleString());
                    styleEvent.setLayerBean(raster);
                    GPHandlerManager.fireEvent(styleEvent);
                }
            }
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
        }
    }
}
