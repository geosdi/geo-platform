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

import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProjectKey;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.projects.binding.ProjectExternalPublicFieldBinding;
import org.geosdi.geoplatform.gui.client.widget.form.projects.binding.ProjectInternalPublicFieldBinding;
import org.geosdi.geoplatform.gui.client.widget.form.projects.binding.ProjectSharedFieldBinding;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.model.tree.IGPRootTreeNode;

import static org.geosdi.geoplatform.gui.client.model.projects.GPClientProjectKey.*;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPProjectPropertiesBinding extends GeoPlatformBindingWidget<IGPRootTreeNode> {

    private GPSecureStringTextField projectNameField;
    private GPSecureStringTextField projectElementsField;
    private GPSecureStringTextField projectVersionField;
    private GPSecureStringTextField creationDateField;
    private CheckBox internalPublic;
    private CheckBox externalPublic;
    private CheckBox sharedCheck;

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(100);
        layout.setLabelPad(5);
        fp.setBorders(false);
        fp.setHeaderVisible(false);
        fp.setBodyBorder(false);
        fp.setLayout(layout);

        projectNameField = new GPSecureStringTextField();
        projectNameField.setId(GPClientProjectKey.PROJECT_NAME.toString());
        projectNameField.setName(GPClientProjectKey.PROJECT_NAME.toString());
        projectNameField.setFieldLabel(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_listViewNameText());
        projectNameField.setReadOnly(true);
        fp.add(projectNameField);

        projectElementsField = new GPSecureStringTextField();
        projectElementsField.setId(GPClientProjectKey.PROJECT_ELEMENTS.toString());
        projectElementsField.setName(GPClientProjectKey.PROJECT_ELEMENTS.toString());
        projectElementsField.setFieldLabel(LayerModuleConstants.INSTANCE.
                GPProjectSearchPanel_listViewPropertiesText());
        projectElementsField.setReadOnly(true);
        fp.add(projectElementsField);

        projectVersionField = new GPSecureStringTextField();
        projectVersionField.setId(GPClientProjectKey.PROJECT_VERSION.toString());
        projectVersionField.setName(GPClientProjectKey.PROJECT_VERSION.toString());
        projectVersionField.setFieldLabel(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_listViewVersionText());
        projectVersionField.setReadOnly(true);
        fp.add(projectVersionField);

        creationDateField = new GPSecureStringTextField();
        creationDateField.setId(GPClientProjectKey.CREATION_DATE.toString());
        creationDateField.setName(GPClientProjectKey.CREATION_DATE.toString());
        creationDateField.setFieldLabel(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_listViewCreationDateText());
        creationDateField.setEnabled(true);
        fp.add(creationDateField);

        CheckBoxGroup checkGroupVisibility = new CheckBoxGroup();
        checkGroupVisibility.setFieldLabel(LayerModuleConstants.INSTANCE.GPClientProject_checkGroupVisibilityLabelText());

        internalPublic = new CheckBox();
        internalPublic.setId(PROJECT_INTERNAL_PUBLIC.toString());
        internalPublic.setName(PROJECT_INTERNAL_PUBLIC.toString());
        internalPublic.setBoxLabel(LayerModuleConstants.INSTANCE.GPClientProject_internalVisibilityLabelText());
        internalPublic.setReadOnly(true);
        checkGroupVisibility.add(internalPublic);

        externalPublic = new CheckBox();
        externalPublic.setId(PROJECT_EXTERNAL_PUBLIC.toString());
        externalPublic.setName(PROJECT_EXTERNAL_PUBLIC.toString());
        externalPublic.setBoxLabel(LayerModuleConstants.INSTANCE.GPClientProject_externalVisibilityLabelText());
        externalPublic.setReadOnly(true);
        checkGroupVisibility.add(externalPublic);
        fp.add(checkGroupVisibility);

        sharedCheck = new CheckBox();
        sharedCheck.setId(PROJECT_SHARED.toString());
        sharedCheck.setName(PROJECT_SHARED.toString());
        sharedCheck.setBoxLabel(LayerModuleConstants.INSTANCE.GPClientProject_sharedLabelText());
        sharedCheck.setReadOnly(true);
        CheckBoxGroup checkGroup = new CheckBoxGroup();
        checkGroup.setFieldLabel(LayerModuleConstants.INSTANCE.ProjectBindingWidget_checkBoxGroupLabelText());
        checkGroup.add(this.sharedCheck);
        fp.add(checkGroup);
        return fp;
    }

    /**
     * Add Bindings Manually
     */
    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(new ProjectInternalPublicFieldBinding(internalPublic, PROJECT_INTERNAL_PUBLIC.toString()));
        this.formBinding.addFieldBinding(new ProjectExternalPublicFieldBinding(externalPublic, PROJECT_EXTERNAL_PUBLIC.toString()));
        this.formBinding.addFieldBinding(new ProjectSharedFieldBinding(sharedCheck, PROJECT_SHARED.toString()));
    }
}