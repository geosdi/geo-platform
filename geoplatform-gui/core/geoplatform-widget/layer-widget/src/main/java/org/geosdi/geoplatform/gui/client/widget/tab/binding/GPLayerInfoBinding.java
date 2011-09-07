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

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import org.geosdi.geoplatform.gui.client.puregwt.decorator.event.TreeChangeLabelEvent;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel.GPLayerKeyValue;
import org.geosdi.geoplatform.gui.puregwt.layers.decorator.event.GPTreeLabelEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPLayerInfoBinding extends GeoPlatformBindingWidget<GPLayerBean> {

    private TextField<String> titleField;
    private TextField<String> abstractField;
    private TextField<String> aliasField;
    private TextField<String> serverField;
    //
    private GPTreeLabelEvent labelEvent = new TreeChangeLabelEvent();

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);

        titleField = new TextField<String>();
        titleField.setId(GPLayerKeyValue.TITLE.toString());
        titleField.setName(GPLayerKeyValue.TITLE.toString());
        titleField.setFieldLabel("Title");

        titleField.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                titleField.setValue((String) be.getOldValue());
            }
        });

        fp.add(titleField);

        abstractField = new TextField<String>();
        abstractField.setId(GPLayerKeyValue.ABSTRACT.toString());
        abstractField.setName(GPLayerKeyValue.ABSTRACT.toString());
        abstractField.setFieldLabel("Abstract");

        abstractField.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent be) {
                abstractField.setValue((String) be.getOldValue());
            }
        });

        fp.add(abstractField);

        aliasField = new TextField<String>();
        aliasField.setName(GPLayerKeyValue.ALIAS.toString());
        aliasField.setFieldLabel("Alias");
        
        fp.add(aliasField);

        serverField = new TextField<String>();
        serverField.setId(GPLayerKeyValue.SERVER.toString());
        serverField.setName(GPLayerKeyValue.SERVER.toString());
        serverField.setFieldLabel("Server");

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
        this.formBinding.addFieldBinding(new GPLayerAliasFieldBinding(aliasField,
                GPLayerKeyValue.ALIAS.toString()));
    }

    /**
     * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
     * @email  giuseppe.lascaleia@geosdi.org
     * 
     * Internal Class GPLayerAliasFieldBinding to map bi-directional Binding
     * 
     */
    private class GPLayerAliasFieldBinding extends GPFieldBinding {

        public GPLayerAliasFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            ((GPLayerBean) model).setAlias(val != null ? (String) val : "");
            WidgetPropertiesHandlerManager.fireEvent(labelEvent);
        }
    }
}
