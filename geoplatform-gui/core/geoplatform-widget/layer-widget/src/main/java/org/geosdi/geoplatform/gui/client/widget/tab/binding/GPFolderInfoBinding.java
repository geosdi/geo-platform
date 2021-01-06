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

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.event.dom.client.KeyCodes;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.puregwt.decorator.event.TreeChangeLabelEvent;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode.GPFolderKeyValue;
import org.geosdi.geoplatform.gui.puregwt.layers.decorator.event.GPTreeLabelEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPFolderInfoBinding extends GeoPlatformBindingWidget<FolderTreeNode> {

    private Label folderInfo;
    private GPSecureStringTextField labelField;
    private GPTreeLabelEvent labelEvent = new TreeChangeLabelEvent();

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);
        labelField = new GPSecureStringTextField();
        labelField.setName(GPFolderKeyValue.LABEL.toString());
        labelField.setFieldLabel(LayerModuleConstants.INSTANCE.GPFolderInfoBinding_labelFieldText());
        labelField.setFireChangeEventOnSetValue(true);
        labelField.addKeyListener(new KeyListener() {
            @Override
            public void componentKeyDown(ComponentEvent event) {
                super.componentKeyDown(event);
                if (event.getKeyCode() == KeyCodes.KEY_ENTER
                        && !labelField.getValue().isEmpty()) {
                    getModel().setLabel(labelField.getValue());
                }
            }
        });
        fp.add(labelField);
        folderInfo = new Label();
        folderInfo.setIntStyleAttribute("font-size", 8);
        fp.add(folderInfo);

        return fp;
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(new GPFolderNameFieldBinding(labelField,
                GPFolderKeyValue.LABEL.toString()));
    }

    /**
     * @author Nazzareno Sileno - CNR IMAA geoSDI Group
     * @email nazzareno.sileno@geosdi.org
     *
     * Internal Class GPFolderNameFieldBinding to map bi-directional Binding
     *
     */
    private class GPFolderNameFieldBinding extends GPFieldBinding {

        public GPFolderNameFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            //Copying the value on memento before changes
            IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
            AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties((FolderTreeNode) model);
            ((FolderTreeNode) model).setLabel(val != null ? (String) val : "");
            mementoSave.putOriginalPropertiesInCache(memento);
            WidgetPropertiesHandlerManager.fireEvent(labelEvent);
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            FolderTreeNode folder = (FolderTreeNode) model;
            labelField.setValue(folder.getLabel());
            folderInfo.setHtml(LayerModuleMessages.INSTANCE.
                    GPFolderNameFieldBinding_folderInfoHTMLMessage(folder.getNumberOfDescendants()));
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
        }
    }
}
