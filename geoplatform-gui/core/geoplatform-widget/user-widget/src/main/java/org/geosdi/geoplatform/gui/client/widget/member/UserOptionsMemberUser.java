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
package org.geosdi.geoplatform.gui.client.widget.member;

import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetailKeyValue;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class UserOptionsMemberUser extends UserOptionsMember {

    private TextField<String> oldPasswordField;
    private TextField<String> newPasswordField;
    private TextField<String> newRePasswordField;
    private TextField<String> nameField;
    private TextField<String> textField;
    private LabelField usernameField;
    private LabelField roleField;
    //
    private FormPanel form;

    public UserOptionsMemberUser(IGPUserManageDetail user, LayoutContainer container) {
        super(user, "User", container);
    }

    @Override
    protected void creteLayoutData(ContentPanel panel) {
        form = new FormPanel();
        form.setHeaderVisible(false);
        form.setBorders(false);

        form.add(this.createPropertiesSetting());
        form.add(this.createEmailSetting(), new VBoxLayoutData(new Margins(20, 0, 0, 0)));
        form.add(this.createPasswordSetting(), new VBoxLayoutData(new Margins(25, 0, 0, 0)));

        panel.add(form);
    }

    private FieldSet createPropertiesSetting() {
        usernameField = new LabelField("Username:\t");
        usernameField.setName(GPUserManageDetailKeyValue.USERNAME.toString());

        roleField = new LabelField("Role:\t");
        roleField.setName(GPUserManageDetailKeyValue.AUTORITHY.toString());

        nameField = new TextField<String>();
        nameField.setName("name");
        nameField.setFieldLabel("Name:");

        FieldSet userFieldSet = new FieldSet();
        userFieldSet.setHeading("User");
        userFieldSet.setSize(400, 100);
        userFieldSet.add(usernameField);
        userFieldSet.add(roleField);
        userFieldSet.add(nameField);

        return userFieldSet;
    }

    private FieldSet createEmailSetting() {
        textField = new TextField<String>();
        textField.setName(GPUserManageDetailKeyValue.EMAIL.toString());
        textField.setFieldLabel("Email");

        FieldSet emailResultSet = new FieldSet();
        emailResultSet.setHeading("Change email");
        emailResultSet.setSize(400, 50);
        emailResultSet.setCheckboxToggle(true);
        emailResultSet.setExpanded(false);
        emailResultSet.add(textField);

        return emailResultSet;
    }

    private FieldSet createPasswordSetting() {
        FieldSet passwordFieldSet = new FieldSet();
        passwordFieldSet.setHeading("Change password");
        passwordFieldSet.setSize(400, 95);
        passwordFieldSet.setCheckboxToggle(true);
        passwordFieldSet.setExpanded(false);

        oldPasswordField = new TextField<String>();
        oldPasswordField.setFieldLabel("label");
        oldPasswordField.setName("password");
        oldPasswordField.setPassword(true);
        oldPasswordField.setAllowBlank(false);
        oldPasswordField.setMinLength(6);
        oldPasswordField.setSelectOnFocus(true);

        newPasswordField = new TextField<String>();
        newPasswordField.setName("newPassword");
        newPasswordField.setFieldLabel("Password");
        newPasswordField.setPassword(true);
        newPasswordField.setAllowBlank(false);
        newPasswordField.setMinLength(6);

        newRePasswordField = new TextField<String>();
        newRePasswordField.setName("newRePassword");
        newRePasswordField.setFieldLabel("RePassword");
        newRePasswordField.setPassword(true);
        newRePasswordField.setAllowBlank(false);
        newRePasswordField.setMinLength(6);

        passwordFieldSet.add(oldPasswordField);
        passwordFieldSet.add(newPasswordField);
        passwordFieldSet.add(newRePasswordField);

        return passwordFieldSet;
    }

    private class NameFieldBinding extends GPFieldBinding {

        public NameFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail user = (GPUserManageDetail) model;
            user.setName(val.toString());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(property, val);
        }
    }

    @Override
    public void saveOptions() {
        GeoPlatformMessage.infoMessage("ToDo", "<ul><li>Work in progress</li></ul>");
    }
}