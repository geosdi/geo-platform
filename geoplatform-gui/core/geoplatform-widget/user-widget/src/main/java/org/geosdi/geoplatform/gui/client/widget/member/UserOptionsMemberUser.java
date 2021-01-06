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
package org.geosdi.geoplatform.gui.client.widget.member;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldSetEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.client.service.UserRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.users.member.UserOptionsMember;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.regex.GPRegEx;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

public class UserOptionsMemberUser extends UserOptionsMember {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final UserRemoteAsync userRemote = UserRemote.Util.getInstance();
    //
    private FormPanel formPanel;
    //
    private GPSecureStringTextField usernameField;
    private GPSecureStringTextField roleField;
    private GPSecureStringTextField nameField;
    //
    private GPSecureStringTextField emailField;
    //
    private GPSecureStringTextField oldPasswordField;
    private GPSecureStringTextField newPasswordField;
    private GPSecureStringTextField newRePasswordField;
    //
    private String newName;
    private String newEmail;
    private String newPlainPassword;
    //
    private boolean validName;
    private boolean validEmail;
    private boolean validPassword;

    public UserOptionsMemberUser() {
        super(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_userText());
    }

    @Override
    protected void creteLayoutData(ContentPanel panel) {
        this.formPanel = new FormPanel();
        this.formPanel.setSize(420, 350);
        this.formPanel.setHeaderVisible(false);

        this.formPanel.add(this.createPropertiesSetting());
        this.formPanel.add(this.createEmailSetting(), new VBoxLayoutData(
                new Margins(20, 0, 0, 0)));
        this.formPanel.add(this.createPasswordSetting(), new VBoxLayoutData(
                new Margins(20, 0, 0, 0)));

        panel.add(formPanel);
    }

    private FieldSet createPropertiesSetting() {
        FieldSet userFieldSet = new FieldSet();
        userFieldSet.setHeadingHtml(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_userText());
        userFieldSet.setSize(400, 110);
        userFieldSet.setLayout(this.getFormLayoutTemplate());

        usernameField = new GPSecureStringTextField();
        usernameField.setFieldLabel(
                UserModuleConstants.INSTANCE.usernameFieldText());
        usernameField.setEnabled(false);

        userFieldSet.add(usernameField);

        roleField = new GPSecureStringTextField();
        roleField.setFieldLabel(UserModuleConstants.INSTANCE.
                userRoleLabelText());
        roleField.setEnabled(false);

        userFieldSet.add(roleField);

        nameField = new GPSecureStringTextField();
        nameField.setFieldLabel(UserModuleConstants.INSTANCE.nameFieldText());
        nameField.setToolTip(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_nameFieldTooltipText());
        nameField.setSelectOnFocus(true);
        nameField.setAllowBlank(false);
        nameField.setAutoValidate(true);
        nameField.setValidator(this.validatorUpdateName());
        userFieldSet.add(nameField);

        return userFieldSet;
    }

    private FieldSet createEmailSetting() {
        FieldSet emailResultSet = new FieldSet();
        emailResultSet.setHeadingHtml(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_changeEmailHeadingText());
        emailResultSet.setSize(400, 50);
        emailResultSet.setCheckboxToggle(true);
        emailResultSet.setExpanded(false);
        emailResultSet.setLayout(this.getFormLayoutTemplate());

        emailField = new GPSecureStringTextField();
        emailField.setFieldLabel(UserModuleConstants.INSTANCE.emailFieldText());
        emailField.setToolTip(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_emailFieldTooltipText());
        emailField.setAutoValidate(true);
        emailField.setAllowBlank(false);
        emailField.setValidator(validatorUpdateEmail());
        emailResultSet.add(emailField);

        emailResultSet.addListener(Events.Collapse,
                new Listener<FieldSetEvent>() {

                    @Override
                    public void handleEvent(FieldSetEvent be) {
                        updateEmail(null, true);

                        emailField.setValue(user.getEmail());
                    }
                });

        return emailResultSet;
    }

    private FieldSet createPasswordSetting() {
        FieldSet passwordFieldSet = new FieldSet();
        passwordFieldSet.setHeadingHtml(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_changePasswordHeadingText());
        passwordFieldSet.setSize(400, 100);
        passwordFieldSet.setCheckboxToggle(true);
        passwordFieldSet.setExpanded(false);
        passwordFieldSet.setLayout(this.getFormLayoutTemplate());

        oldPasswordField = new GPSecureStringTextField();
        oldPasswordField.setFieldLabel(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_oldPasswordLabelText());
        oldPasswordField.setToolTip(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_oldPasswordLabelText());
        oldPasswordField.setPassword(true);
        oldPasswordField.setAutoValidate(true);
        oldPasswordField.setAllowBlank(false);
        oldPasswordField.setValidator(this.validatorPassword());
        passwordFieldSet.add(oldPasswordField);

        newPasswordField = new GPSecureStringTextField();
        newPasswordField.setFieldLabel(ButtonsConstants.INSTANCE.newText());
        newPasswordField.setToolTip(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_newPasswordTooltipText());
        newPasswordField.setPassword(true);
        newPasswordField.setAutoValidate(true);
        newPasswordField.setAllowBlank(false);
        newPasswordField.setValidator(this.validatorUpdatePassword());
        newPasswordField.setEnabled(false);
        passwordFieldSet.add(newPasswordField);

        newRePasswordField = new GPSecureStringTextField();
        newRePasswordField.setFieldLabel(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_newRePasswordLabelText());
        newRePasswordField.setToolTip(UserModuleConstants.INSTANCE.
                UserOptionsMemberUser_newRePasswordTooltipText());
        newRePasswordField.setPassword(true);
        newRePasswordField.setAutoValidate(true);
        newRePasswordField.setAllowBlank(false);
        newRePasswordField.setValidator(this.validatorUpdateConfirmPassword());
        newRePasswordField.setEnabled(false);
        passwordFieldSet.add(newRePasswordField);

        passwordFieldSet.addListener(Events.Collapse,
                new Listener<FieldSetEvent>() {

                    @Override
                    public void handleEvent(FieldSetEvent be) {
                        updatePassword(null, true);

                        oldPasswordField.reset();
                        newPasswordField.reset();
                        newRePasswordField.reset();

                        newPasswordField.setEnabled(false);
                        newRePasswordField.setEnabled(false);
                    }
                });
        passwordFieldSet.addListener(Events.Expand,
                new Listener<FieldSetEvent>() {

                    @Override
                    public void handleEvent(FieldSetEvent be) {
                        updatePassword(null, false);
                    }
                });

        return passwordFieldSet;
    }

    @Override
    public void saveOptions() {
        this.updateUserProperties();

        final String currentPlainPassword = oldPasswordField.getValue();

        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                try {
                    throw caught;
                } catch (RpcTokenException e) {
                    // Can be thrown for several reasons:
                    //   - duplicate session cookie, which may be a sign of a cookie
                    //     overwrite attack
                    //   - XSRF token cannot be generated because session cookie isn't
                    //     present
                } catch (Throwable e) {
                    // unexpected
                }
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) userRemote).setRpcToken(token);
                userRemote.updateOwnUser(user, currentPlainPassword,
                        newPlainPassword,
                        new AsyncCallback<Long>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                GeoPlatformMessage.errorMessage(
                                        WindowsConstants.INSTANCE.errorTitleText(),
                                        caught.getMessage());
                            }

                            @Override
                            public void onSuccess(Long result) {
                                saveButton.disable();

                                GeoPlatformMessage.infoMessage(
                                        UserModuleConstants.INSTANCE.
                                        infoUserSuccesfullyModifiedText(),
                                        "<ul><li>" + user.getUsername() + "</li></ul>");
                            }
                        });
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
        roleField.setValue(user.getAuthority());
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
                    updateName(null, true);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_COMPLETE_NAME.test(value)) {
                    updateName(null, false);
                    return UserModuleConstants.INSTANCE.errorValidatingCompleteNameUpdateText();
                }
                updateName(value, true);
                return null;
            }
        };
    }

    private Validator validatorUpdateEmail() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(user.getEmail())) {
                    updateEmail(null, true);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_EMAIL.test(value)) {
                    updateEmail(null, false);
                    return UserModuleConstants.INSTANCE.errorValidatingEmailUpdateText();
                }
                updateEmail(value, true);
                return null;
            }
        };
    }

    private Validator validatorPassword() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (value.length() < 4) {
                    updatePassword(null, false);

                    newPasswordField.reset();
                    newRePasswordField.reset();

                    newPasswordField.setEnabled(false);
                    newRePasswordField.setEnabled(false);
                    return UserModuleConstants.INSTANCE.errorValidatingPasswordUpdateText();
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
                    updatePassword(null, false);
                    newRePasswordField.reset();
                    newRePasswordField.setEnabled(false);
                    return UserModuleConstants.INSTANCE.errorValidatingPasswordUpdateText();
                } else if (value.equals(oldPasswordField.getValue())) {
                    updatePassword(null, false);
                    newRePasswordField.reset();
                    newRePasswordField.setEnabled(false);
                    return UserModuleConstants.INSTANCE.
                            UserOptionsMemberUser_errorValidatingNewPasswordDifferenceUpdateText();
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
                    updatePassword(value, true);
                    return null;
                }
                updatePassword(null, false);
                return UserModuleConstants.INSTANCE.
                        UserOptionsMemberUser_errorValidatingRetypedPasswordUpdateText();
            }
        };
    }

    private void updateName(String newName, boolean validName) {
        this.newName = newName;
        this.validName = validName;
        this.manageSaveButton();
    }

    private void updateEmail(String newEmail, boolean validEmail) {
        this.newEmail = newEmail;
        this.validEmail = validEmail;
        this.manageSaveButton();
    }

    private void updatePassword(String newPassword, boolean validPassword) {
        this.newPlainPassword = newPassword;
        this.validPassword = validPassword;
        this.manageSaveButton();
    }

    private void manageSaveButton() {
        if (validName && validEmail && validPassword
                && (newName != null || newEmail != null || newPlainPassword != null)) {
            this.saveButton.enable();
        } else {
            this.saveButton.disable();
        }
    }

    private void updateUserProperties() {
        if (newName != null) {
            Dispatcher.forwardEvent(GeoPlatformEvents.USER_UPDATE_HIS_NAME,
                    newName);
            user.setName(newName);
        }
        if (newEmail != null) {
            user.setEmail(newEmail);
        }
    }
}
