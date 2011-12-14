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

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldSetEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.regex.GPRegEx;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class UserOptionsMemberUser extends UserOptionsMember {

    private FormPanel formPanel;
    //
    private TextField<String> usernameField;
    private TextField<String> roleField;
    private TextField<String> nameField;
    //
    private TextField<String> emailField;
    //
    private TextField<String> oldPasswordField;
    private TextField<String> newPasswordField;
    private TextField<String> newRePasswordField;
    //
    private String newName;
    private String newEmail;
    private String newPassword;

    public UserOptionsMemberUser(LayoutContainer container) {
        super("User", container);
    }

    @Override
    protected void creteLayoutData(ContentPanel panel) {
        this.formPanel = new FormPanel();
        this.formPanel.setSize(420, 350);
        this.formPanel.setHeaderVisible(false);

        this.formPanel.add(this.createPropertiesSetting());
        this.formPanel.add(this.createEmailSetting(), new VBoxLayoutData(new Margins(20, 0, 0, 0)));
        this.formPanel.add(this.createPasswordSetting(), new VBoxLayoutData(new Margins(20, 0, 0, 0)));

        panel.add(formPanel);
    }

    private FieldSet createPropertiesSetting() {
        FieldSet userFieldSet = new FieldSet();
        userFieldSet.setHeading("User");
        userFieldSet.setSize(400, 110);
        userFieldSet.setLayout(this.getFormLayoutTemplate());

        usernameField = new TextField<String>();
        usernameField.setFieldLabel("Username");
        usernameField.setToolTip("Your username");
        usernameField.setReadOnly(true);
//        usernameField.setShadow(true);
//        usernameField.setShadowPosition(ShadowPosition.FRAME);
        userFieldSet.add(usernameField);

        roleField = new TextField<String>();
        roleField.setFieldLabel("Role");
        roleField.setToolTip("Your role");
        roleField.setReadOnly(true);
//        roleField.setShadow(true);
//        roleField.setShadowPosition(ShadowPosition.FRAME);
        userFieldSet.add(roleField);

        nameField = new TextField<String>();
        nameField.setFieldLabel("Name");
        nameField.setToolTip("Your complete name");
        nameField.setSelectOnFocus(true);
        nameField.setAllowBlank(false);
        nameField.setAutoValidate(true);
        nameField.setValidator(this.validatorUpdateName());
        userFieldSet.add(nameField);

        return userFieldSet;
    }

    private FieldSet createEmailSetting() {
        FieldSet emailResultSet = new FieldSet();
        emailResultSet.setHeading("Change email");
        emailResultSet.setSize(400, 50);
        emailResultSet.setCheckboxToggle(true);
        emailResultSet.setExpanded(false);
        emailResultSet.setLayout(this.getFormLayoutTemplate());

        emailField = new TextField<String>();
        emailField.setFieldLabel("Email");
        emailField.setToolTip("Your email");
        emailField.setAutoValidate(true);
        emailField.setAllowBlank(false);
        emailField.setValidator(validatorUpdateEmail());
        emailResultSet.add(emailField);

        emailResultSet.addListener(Events.Collapse, new Listener<FieldSetEvent>() {

            @Override
            public void handleEvent(FieldSetEvent be) {
                emailField.setValue(user.getEmail());
            }
        });

        return emailResultSet;
    }

    private FieldSet createPasswordSetting() {
        FieldSet passwordFieldSet = new FieldSet();
        passwordFieldSet.setHeading("Change password");
        passwordFieldSet.setSize(400, 100);
        passwordFieldSet.setCheckboxToggle(true);
        passwordFieldSet.setExpanded(false);
        passwordFieldSet.setLayout(this.getFormLayoutTemplate());

        oldPasswordField = new TextField<String>();
        oldPasswordField.setFieldLabel("Current");
        oldPasswordField.setToolTip("Your current password");
        oldPasswordField.setPassword(true);
        oldPasswordField.setAutoValidate(true);
        oldPasswordField.setAllowBlank(false);
        oldPasswordField.setValidator(this.validatorPassword());
        passwordFieldSet.add(oldPasswordField);

        newPasswordField = new TextField<String>();
        newPasswordField.setFieldLabel("New");
        newPasswordField.setToolTip("Enter a new password");
        newPasswordField.setPassword(true);
        newPasswordField.setAutoValidate(true);
        newPasswordField.setAllowBlank(false);
        newPasswordField.setValidator(this.validatorUpdatePassword());
        newPasswordField.setEnabled(false);
        passwordFieldSet.add(newPasswordField);

        newRePasswordField = new TextField<String>();
        newRePasswordField.setFieldLabel("Retype new");
        newRePasswordField.setToolTip("Retype the new password");
        newRePasswordField.setPassword(true);
        newRePasswordField.setAutoWidth(true);
        newRePasswordField.setAllowBlank(false);
        newRePasswordField.setValidator(this.validatorUpdateConfirmPassword());
        newRePasswordField.setEnabled(false);
        passwordFieldSet.add(newRePasswordField);

        passwordFieldSet.addListener(Events.Collapse, new Listener<FieldSetEvent>() {

            @Override
            public void handleEvent(FieldSetEvent be) {
                oldPasswordField.reset();
                newPasswordField.reset();
                newRePasswordField.reset();

                newPasswordField.setEnabled(false);
                newRePasswordField.setEnabled(false);

                updatePassword(null);
            }
        });

        return passwordFieldSet;
    }

    @Override
    public void saveOptions() {
        this.updateUserProperties();

        String currentPlainPassword = oldPasswordField.getValue();


        UserRemoteImpl.Util.getInstance().updateOwnUser(user,
                currentPlainPassword, newPassword,
                new AsyncCallback<Long>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage("Error", caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Long result) {
                        saveButton.disable();

                        GeoPlatformMessage.infoMessage("User successfully modify",
                                "<ul><li>" + user.getUsername() + "</li></ul>");
                    }
                });
    }

    private FormLayout getFormLayoutTemplate() {
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(75);
        layout.setDefaultWidth(200);
        return layout;
    }

    @Override
    protected void manageUserData() {
        usernameField.setValue(user.getUsername());
        roleField.setValue(user.getAuthority().toString());
        nameField.setValue(user.getName());

        emailField.setValue(user.getEmail());

        oldPasswordField.reset();
        newPasswordField.reset();
        newRePasswordField.reset();
    }

    private Validator validatorUpdateName() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(user.getName())) {
                    updateName(null);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_COMPLETE_NAME.test(value)) {
                    updateName(null);
                    return "Complete name is not valid (example: John Steam)";
                }
                updateName(value);
                return null;
            }
        };
    }

    private Validator validatorUpdateEmail() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(user.getEmail())) {
                    updateEmail(null);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_EMAIL.test(value)) {
                    updateEmail(null);
                    return "Email is not valid (example: any@foo.org)";
                }
                updateEmail(value);
                return null;
            }
        };
    }

    private Validator validatorPassword() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.length() < 4) {
                    newPasswordField.setEnabled(false);
                    newRePasswordField.setEnabled(false);
                    return "The minimun lenght for old password is 4";
                }
                newPasswordField.setEnabled(true);
                return null;
            }
        };
    }

    private Validator validatorUpdatePassword() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.length() < 4) {
                    newRePasswordField.reset();
                    newRePasswordField.setEnabled(false);
                    return "The minimun lenght for new password is 4";
                } else if (value.equals(oldPasswordField.getValue())) {
                    newRePasswordField.reset();
                    newRePasswordField.setEnabled(false);
                    return "The new password must be different from the old password";
                }
                newRePasswordField.setEnabled(true);
                return null;
            }
        };
    }

    private Validator validatorUpdateConfirmPassword() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(newPasswordField.getValue())) {
                    updatePassword(value);
                    return null;
                }
                updatePassword(null);
                return "Retyped new password don't match";
            }
        };
    }

    private void updateName(String newName) {
        this.newName = newName;
        this.manageSaveButton();
    }

    private void updateEmail(String newEmail) {
        this.newEmail = newEmail;
        this.manageSaveButton();
    }

    private void updatePassword(String newPassword) {
        this.newPassword = newPassword;
        this.manageSaveButton();
    }

    private void manageSaveButton() {
        if (newName != null || newEmail != null || newPassword != null) {
            this.saveButton.enable();
        } else {
            this.saveButton.disable();
        }
    }

    private void updateUserProperties() {
        if (newName != null) {
            user.setName(newName);
        }
        if (newEmail != null) {
            user.setEmail(newEmail);
        }
    }
}
