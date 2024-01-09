/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.gui.client.model.SharingPermissionEnum.READ;
import static org.geosdi.geoplatform.gui.client.model.SharingPermissionEnum.WRITE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class ShareProjectPermissionWidget extends VerticalPanel {

    private final static String RADIO_KEY_VALUE = "keyValue";
    //
    private RadioGroup radioGroup;
    private Radio readRadioButton;
    private Radio writeRadioButton;
    private boolean initialized;
    private GPSimpleUser gpSimpleUser;


    public ShareProjectPermissionWidget() {
        this.initComponent();
    }

    /**
     *
     */
    private void initComponent() {
        if (!this.initialized) {
            this.setSpacing(10);
            FieldSet fieldSet = new FieldSet();
            fieldSet.setHeadingHtml(LayerModuleConstants.INSTANCE.ShareProjectWidget_radioButtonTitle());
            fieldSet.setWidth(GPProjectManagementWidget.COMPONENT_WIDTH - 20);
            FormLayout layout = new FormLayout();
            layout.setLabelWidth(250);
            fieldSet.setLayout(layout);
            this.add(fieldSet);
            this.radioGroup = new RadioGroup();
            this.readRadioButton = new Radio();
            this.writeRadioButton = new Radio();
            this.readRadioButton.setBoxLabel(READ.getPermission());
            this.readRadioButton.setHideLabel(true);
            this.readRadioButton.setData(RADIO_KEY_VALUE, READ.getCode());
            this.writeRadioButton.setBoxLabel(WRITE.getPermission());
            this.writeRadioButton.setData(RADIO_KEY_VALUE, WRITE.getCode());
            this.readRadioButton.setHideLabel(true);
            this.radioGroup.add(this.readRadioButton);
            this.radioGroup.add(this.writeRadioButton);
            BorderLayoutData data = new BorderLayoutData(Style.LayoutRegion.CENTER);
            data.setMargins(new Margins(5, 5, 5, 5));
            fieldSet.add(this.radioGroup, data);
            this.setVisible(FALSE);
            this.radioGroup.addListener(Events.Change, be -> {
                this.gpSimpleUser.setSharedPermission(this.radioGroup.getValue().getData(RADIO_KEY_VALUE));
            });
            this.initialized = true;
        }
    }

    /**
     * @param gpSimpleUser
     */
    public void bindUser(GPSimpleUser gpSimpleUser) {
        this.gpSimpleUser = gpSimpleUser;
        this.radioGroup.setFieldLabel((this.gpSimpleUser.getName()));
        this.radioGroup.setValue(this.gpSimpleUser.getSharedPermission() == (WRITE.getCode()) ? this.writeRadioButton :
                this.readRadioButton);
        this.setVisible(TRUE);
    }
}