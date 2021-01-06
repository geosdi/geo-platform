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

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.event.dom.client.KeyCodes;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.puregwt.decorator.event.TreeChangeLabelEvent;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel.GPLayerKeyValue;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.decorator.event.GPTreeLabelEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.event.ReloadLegendEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPLayerInfoBinding extends GeoPlatformBindingWidget<GPLayerBean> {

    private GPSecureStringTextField titleField;
    private GPSecureStringTextField abstractField;
    private GPSecureStringTextField aliasField;
    private GPSecureStringTextField serverField;
    //
    private final GPTreeLabelEvent labelEvent = new TreeChangeLabelEvent();

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);

        titleField = new GPSecureStringTextField();
        titleField.setId(GPLayerKeyValue.TITLE.toString());
        titleField.setName(GPLayerKeyValue.TITLE.toString());
        titleField.setFieldLabel(LayerModuleConstants.INSTANCE.GPLayerInfoBinding_titleLabelText());

        titleField.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                titleField.setValue((String) be.getOldValue());
            }

        });

        fp.add(titleField);

        abstractField = new GPSecureStringTextField();
        abstractField.setId(GPLayerKeyValue.ABSTRACT.toString());
        abstractField.setName(GPLayerKeyValue.ABSTRACT.toString());
        abstractField.setFieldLabel(LayerModuleConstants.INSTANCE.GPLayerInfoBinding_abstractLabelText());

        abstractField.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                abstractField.setValue((String) be.getOldValue());
            }

        });

        fp.add(abstractField);

        aliasField = new GPSecureStringTextField();
        aliasField.setName(GPLayerKeyValue.ALIAS.toString());
        aliasField.setFieldLabel(LayerModuleConstants.INSTANCE.
                GPLayerInfoBinding_aliasLabelText());
        aliasField.setFireChangeEventOnSetValue(true);
        aliasField.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyDown(ComponentEvent event) {
                super.componentKeyDown(event);
                if (event.getKeyCode() == KeyCodes.KEY_ENTER && !aliasField.getValue().isEmpty()) {
                    getModel().setAlias(aliasField.getValue());
                    LayerHandlerManager.fireEvent(new ReloadLegendEvent(getModel()));
                }
            }

        });

        fp.add(aliasField);

        serverField = new GPSecureStringTextField();
        serverField.setId(GPLayerKeyValue.SERVER.toString());
        serverField.setName(GPLayerKeyValue.SERVER.toString());
        serverField.setFieldLabel(LayerModuleConstants.INSTANCE.GPLayerInfoBinding_serverLabelText());

        serverField.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                serverField.setValue((String) be.getOldValue());
            }

        });

        fp.add(serverField);

        return fp;
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(new GPLayerAliasFieldBinding(aliasField, GPLayerKeyValue.ALIAS.toString()));
    }

    /**
     * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
     * @email giuseppe.lascaleia@geosdi.org
     * <p>
     * Internal Class GPLayerAliasFieldBinding to map bi-directional Binding
     */
    private class GPLayerAliasFieldBinding extends GPFieldBinding {

        public GPLayerAliasFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            //Copying the value on memento before changes
            IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
            AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties((GPLayerTreeModel) model);
            ((GPLayerBean) model).setAlias(val != null ? (String) val : "");
            mementoSave.putOriginalPropertiesInCache(memento);
            WidgetPropertiesHandlerManager.fireEvent(labelEvent);
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
        }
    }
}