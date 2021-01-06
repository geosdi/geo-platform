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
package org.geosdi.geoplatform.gui.client.form.binding;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleMessages;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail.GPUserManageDetailKeyValue;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUserKeyValue;
import org.geosdi.geoplatform.gui.regex.GPRegEx;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class UserPropertiesBinding extends GeoPlatformBindingWidget<GPUserManageDetail> {

    private GPSecureStringTextField nameField;
    private GPSecureStringTextField emailField;
    private GPSecureStringTextField usernameField;
    private GPSecureStringTextField passwordField;
    private GPSecureStringTextField passwordRepeatField;
    private CheckBox enabledField;
    private LabelField creationDateLabelField;
    private CheckBox temporaryField;
    private LabelField expiredLabelField;
    private SimpleComboBox<String> userRoleComboBox;
    private RoleComboBinding roleComboBinding;
    private SimpleComboBox<GPTrustedLevel> trustedLevelComboBox;
    private TrustedLevelComboBinding trustedLevelComboBinding;
    //
    private FormButtonBinding formButtonBinding; // Monitors the valid state of a form and enabled / disabled all buttons.
    private Button buttonBinding;
    //
    private GPUserManageDetail userOriginal = new GPUserManageDetail();
    private boolean updateName;
    private boolean updateEmail;
    private boolean updatePassword;
    private boolean updateEnabled;
    private boolean updateTemporary;
    private boolean updateRole;
    private boolean updateTrustedLevel;

    public UserPropertiesBinding(ListStore<GPUserManageDetail> store,
            Button buttonBinding) {
        super();
        super.formBinding.setStore(store);

        this.formButtonBinding = new FormButtonBinding(super.getWidget());
        this.formButtonBinding.addButton(this.buttonBinding = buttonBinding);
    }

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);
        fp.setBodyBorder(Boolean.FALSE);

        this.nameField = new GPSecureStringTextField();
        this.nameField.setId(GPSimpleUserKeyValue.NAME.toString());
        this.nameField.setFieldLabel(UserModuleConstants.INSTANCE.
                nameFieldText());
        this.nameField.setEmptyText(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_nameFieldEmptyText());
        this.nameField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_nameFieldToolTipText());
        this.nameField.setAutoValidate(true);

        this.emailField = new GPSecureStringTextField();
        this.emailField.setId(GPSimpleUserKeyValue.EMAIL.toString());
        this.emailField.setFieldLabel(UserModuleConstants.INSTANCE.
                emailFieldText());
        this.emailField.setEmptyText(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_emailFieldEmptyText());
        this.emailField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_emailFieldToolTipText());
        this.emailField.setAutoValidate(true);

        this.usernameField = new GPSecureStringTextField();
        this.usernameField.setId(GPSimpleUserKeyValue.USERNAME.toString());
        this.usernameField.setFieldLabel(UserModuleConstants.INSTANCE.
                usernameFieldText());
        this.usernameField.setEmptyText(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_usernameFieldEmptyText());
        this.usernameField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_usernameFieldToolTipText());
        this.usernameField.setAllowBlank(false);
        this.usernameField.setAutoValidate(true);
        this.usernameField.setMinLength(4);

        this.passwordField = new GPSecureStringTextField();
        this.passwordField.setPassword(true);
        this.passwordField.setFieldLabel(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_passwordFieldText());
        this.passwordField.setValidator(this.validatorPassword());

        this.passwordRepeatField = new GPSecureStringTextField();
        this.passwordRepeatField.setPassword(true);
        this.passwordRepeatField.setFieldLabel(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_repeatPasswordFieldText());
        this.passwordRepeatField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_repeatPasswordFieldTooltipText());

        this.enabledField = new CheckBox();
        this.enabledField.setId(GPUserManageDetailKeyValue.ENABLED.toString());
        this.enabledField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_enableFieldTooltipText());
        this.enabledField.addListener(Events.Change,
                new Listener<FieldEvent>() {
                    @Override
                    public void handleEvent(FieldEvent be) {
                        Boolean check = (Boolean) be.getValue();
                        if (check.booleanValue() != userOriginal.isEnabled()) {
                            updateEnabled(true);
                        } else {
                            updateEnabled(false);
                        }
                    }
                });

        this.creationDateLabelField = new LabelField();

        MultiField enabledAndCreationFields = new MultiField();
        enabledAndCreationFields.setFieldLabel(UserModuleConstants.INSTANCE.
                enabledFieldLabelText());
        enabledAndCreationFields.add(enabledField);
        enabledAndCreationFields.add(new LabelField(
                "<span class='spacer'>&nbsp;</span>"));
        enabledAndCreationFields.add(creationDateLabelField);

        this.temporaryField = new CheckBox();
        this.temporaryField.setId(
                GPUserManageDetailKeyValue.TEMPORARY.toString());
        this.temporaryField.addListener(Events.Change,
                new Listener<FieldEvent>() {
                    @Override
                    public void handleEvent(FieldEvent be) {
                        Boolean temporary = (Boolean) be.getValue();
                        if (temporary.booleanValue() != userOriginal.isTemporary()) {
                            updateTemporary(true);
                        } else {
                            updateTemporary(false);
                        }
                    }
                });

        this.expiredLabelField = new LabelField();

        MultiField tempAndExpiredFields = new MultiField();
        tempAndExpiredFields.setFieldLabel(UserModuleConstants.INSTANCE.temporaryFieldLabelText());
        tempAndExpiredFields.add(temporaryField);
        tempAndExpiredFields.add(new LabelField(
                "<span class='spacer'>&nbsp;</span>"));
        tempAndExpiredFields.add(expiredLabelField);

        this.userRoleComboBox = new SimpleComboBox<String>() {
            @Override
            protected void onSelect(SimpleComboValue<String> model, int index) {
                super.onSelect(model, index);
                roleComboBinding.updateModel();
            }
        };
        this.userRoleComboBox.setId(GPSimpleUserKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.setFieldLabel(UserModuleConstants.INSTANCE.
                userRoleLabelText());
        this.userRoleComboBox.setEmptyText(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_userRoleEmptyText());
        this.userRoleComboBox.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_userRoleTooltipText());
        this.userRoleComboBox.setEditable(false);
        this.userRoleComboBox.setTypeAhead(true);
        this.userRoleComboBox.setAllowBlank(false);
        this.userRoleComboBox.setMaxHeight(150);
        this.userRoleComboBox.setTriggerAction(TriggerAction.ALL);

        this.trustedLevelComboBox = new SimpleComboBox<GPTrustedLevel>();
        this.trustedLevelComboBox.setId(
                GPSimpleUserKeyValue.TRUSTED_LEVEL.toString());
        this.trustedLevelComboBox.setFieldLabel(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_trustedLevelLabelText());
        this.trustedLevelComboBox.setEmptyText(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_trustedLevelEmptyText());
        this.trustedLevelComboBox.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_trustedLevelTooltipText());
        this.trustedLevelComboBox.setEditable(false);
        this.trustedLevelComboBox.setTypeAhead(true);
        this.trustedLevelComboBox.setAllowBlank(false);
        this.trustedLevelComboBox.setMaxHeight(200);
        this.trustedLevelComboBox.setTriggerAction(TriggerAction.ALL);
        this.trustedLevelComboBox.add(Arrays.asList(GPTrustedLevel.values()));

        fp.add(this.nameField);
        fp.add(this.emailField);
        fp.add(this.usernameField);
        fp.add(this.passwordField);
        fp.add(this.passwordRepeatField);
        fp.add(enabledAndCreationFields);
        fp.add(tempAndExpiredFields);
        fp.add(this.userRoleComboBox);
        fp.add(this.trustedLevelComboBox);
        fp.setBorders(Boolean.FALSE);
        return fp;
    }

    public void resetFields() {
        this.nameField.reset();
        this.emailField.reset();
        this.usernameField.reset();
        this.passwordField.reset();
        this.passwordRepeatField.reset();
        this.enabledField.reset();
        this.creationDateLabelField.reset();
        this.temporaryField.reset();
        this.expiredLabelField.reset();
        this.userRoleComboBox.reset();
        this.trustedLevelComboBox.reset();
    }

    @Override
    public void addFieldsBinding() {
        this.roleComboBinding = new RoleComboBinding();
        this.trustedLevelComboBinding = new TrustedLevelComboBinding(); // TODO Only iff necessary
        this.formBinding.addFieldBinding(this.roleComboBinding);
        this.formBinding.addFieldBinding(this.trustedLevelComboBinding);

        this.formBinding.addFieldBinding(new NameFieldBinding());
        this.formBinding.addFieldBinding(new EmailFieldBinding());
        this.formBinding.addFieldBinding(new UsernameFieldBinding());
        this.formBinding.addFieldBinding(new EnabledFieldBinding());
        this.formBinding.addFieldBinding(new TemporaryFieldBinding());
    }

    @Override
    public void bindModel(GPUserManageDetail user) {
        super.bindModel(user);

        this.buttonBinding.disable();
        if (user.getId() == null) { // INSERT USER
            this.handleFiledsInsertUser();
        } else { // UPDATE USER
            // Copy the changeable fields
            userOriginal.setName(user.getName());
            userOriginal.setEmail(user.getEmail());
            userOriginal.setEnabled(user.isEnabled());
            userOriginal.setTemporary(user.isTemporary());
            userOriginal.setAuthority(user.getAuthority());
            userOriginal.setTrustedLevel(user.getTrustedLevel());
            //
            this.formButtonBinding.stopMonitoring(); // NOTE FormButtonBinding is always start auto-magically
            this.handleFieldsUpdateUser();
        }
    }

    private void handleFiledsInsertUser() {
        this.nameField.setValidator(this.validatorInsertName());
        this.nameField.setAllowBlank(false);

        this.emailField.setValidator(this.validatorInsertEmail());
        this.emailField.setAllowBlank(false);

        this.usernameField.setValidator(this.validatorInsertUsername());
        this.usernameField.enable();

        this.passwordField.setFieldLabel(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_passwordFieldText());
        this.passwordField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_passwordTooltipText());
        this.passwordField.setAllowBlank(false);

        this.passwordRepeatField.disable();
        this.passwordRepeatField.setValidator(
                this.validatorInsertConfirmPassword());
        this.passwordRepeatField.setAllowBlank(false);

        // TODO Check default enabled check box
//        getModel().setEnabled(true);
//        this.enabledField.setValue(true);
//        this.enabledField.setValueAttribute("true");
//        this.enabledField.setRawValue("true");
        this.creationDateLabelField.setTitle("");

        this.temporaryField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_temporaryFieldChooseTooltipText());
        this.temporaryField.setReadOnly(false);

        this.expiredLabelField.setTitle(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_expireLabelText());

        this.userRoleComboBox.setValidator(null);
        this.trustedLevelComboBox.setValidator(null);
    }

    private void handleFieldsUpdateUser() {
        GPUserManageDetail user = super.getModel();

        this.nameField.setValidator(this.validatorUpdateName());
        this.nameField.setAllowBlank(true);

        this.emailField.setValidator(this.validatorUpdateEmail());
        this.emailField.setAllowBlank(true);

        this.usernameField.disable();

        this.passwordField.setFieldLabel(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_resetPasswordText());
        this.passwordField.setToolTip(UserModuleConstants.INSTANCE.
                UserPropertiesBinding_resetPasswordTooltipText());
        this.passwordField.setAllowBlank(true);

        this.passwordRepeatField.disable();
        this.passwordRepeatField.setValidator(
                this.validatorUpdateConfirmPassword());
        this.passwordRepeatField.setAllowBlank(true);

        this.creationDateLabelField.setTitle(
                UserModuleMessages.INSTANCE.creationDateMessage(
                        DateTimeFormat.getFormat(
                                DateTimeFormat.PredefinedFormat.DATE_LONG).format(
                                user.getCreationDate())));

        if (!user.isTemporary()) {
            this.temporaryField.setReadOnly(false);
            this.temporaryField.setToolTip(UserModuleConstants.INSTANCE.
                    UserPropertiesBinding_temporaryFieldCheckedTooltipText());
        } else {
            this.temporaryField.setReadOnly(false);
            this.temporaryField.setToolTip(UserModuleConstants.INSTANCE.
                    UserPropertiesBinding_temporaryFieldDecheckedTooltipText());

            if (user.isExpired()) {
                this.expiredLabelField.setTitle(
                        "<span style='color:red'>" + UserModuleConstants.INSTANCE.
                        UserPropertiesBinding_userExpiredText() + "</span>");
            } else {
                Date today = new Date();
                CalendarUtil.addDaysToDate(today, -10);
                this.expiredLabelField.setTitle(UserModuleMessages.INSTANCE.
                        userBeetweenExpirationDateMessage(
                                CalendarUtil.getDaysBetween(today,
                                        user.getCreationDate())));
            }
        }

        this.userRoleComboBox.setValidator(this.validatorUpdateRole());
        this.trustedLevelComboBox.setValidator(
                this.validatorUpdateTrustedLevel());
    }

    /**
     * Validators for Update a user.
     */
    private Validator validatorUpdateName() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(userOriginal.getName())) {
                    updateName(false);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_COMPLETE_NAME.test(value)) {
                    updateName(false);
                    return UserModuleConstants.INSTANCE.
                            errorValidatingCompleteNameUpdateText();
                }
                updateName(true);
                return null;
            }
        };
    }

    private Validator validatorUpdateEmail() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(userOriginal.getEmail())) {
                    updateEmail(false);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_EMAIL.test(value)) {
                    updateEmail(false);
                    return UserModuleConstants.INSTANCE.
                            errorValidatingEmailUpdateText();
                }
                updateEmail(true);
                return null;
            }
        };
    }

    private Validator validatorPassword() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.length() < 4) {
                    passwordRepeatField.disable();
                    return UserModuleConstants.INSTANCE.
                            errorValidatingPasswordUpdateText();
                }
                passwordRepeatField.enable();
                return null;
            }
        };
    }

    private Validator validatorUpdateConfirmPassword() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(passwordField.getValue())) {
                    updatePassword(true);
                    return null;
                }
                updatePassword(false);
                return UserModuleConstants.INSTANCE.
                        UserPropertiesBinding_errorValidatingConfirmPasswordUpdateText();
            }
        };
    }

    private Validator validatorUpdateRole() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                String role = userOriginal.getAuthority();
                if (role != null && value.equals(role)) {
                    updateRole(false);
                    return null; // Pseudo-valid
                }
                updateRole(true);
                return null;
            }
        };
    }

    private Validator validatorUpdateTrustedLevel() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                GPTrustedLevel trustedLevel = userOriginal.getTrustedLevel();
                if (trustedLevel != null && value.equals(trustedLevel.name())) {
                    updateTrustedLevel(false);
                    return null; // Pseudo-valid
                }
                updateTrustedLevel(true);
                return null;
            }
        };
    }

    private void updateName(boolean updateName) {
        this.updateName = updateName;
        this.updateUser();
    }

    private void updateEmail(boolean updateEmail) {
        this.updateEmail = updateEmail;
        this.updateUser();
    }

    private void updatePassword(boolean updatePassword) {
        this.updatePassword = updatePassword;
        this.updateUser();
    }

    private void updateEnabled(boolean updateEnabled) {
        this.updateEnabled = updateEnabled;
        this.updateUser();
    }

    private void updateTemporary(boolean updateTemporary) {
        this.updateTemporary = updateTemporary;
        this.updateUser();
    }

    private void updateRole(boolean updateRole) {
        this.updateRole = updateRole;
        this.updateUser();
    }

    private void updateTrustedLevel(boolean updateTrustedLevel) {
        this.updateTrustedLevel = updateTrustedLevel;
        this.updateUser();
    }

    private void updateUser() {
        if (updateName || updateEmail || updatePassword || updateEnabled
                || updateTemporary || updateRole || updateTrustedLevel) {
            this.buttonBinding.enable();
        } else {
            this.buttonBinding.disable();
        }
    }

    /**
     * Validators for Insert a user.
     */
    private Validator validatorInsertName() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (!GPRegEx.RE_COMPLETE_NAME.test(value)) {
                    return UserModuleConstants.INSTANCE.
                            UserPropertiesBinding_errorValidatingCompleteNameInsertText();
                }
                return null;
            }
        };
    }

    private Validator validatorInsertEmail() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (!GPRegEx.RE_EMAIL.test(value)) {
                    return UserModuleConstants.INSTANCE.
                            UserPropertiesBinding_errorValidatingEmailInsertText();
                }
                return null;
            }
        };
    }

    private Validator validatorInsertUsername() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (!GPRegEx.RE_USERNAME.test(value)) {
                    return UserModuleConstants.INSTANCE.
                            UserPropertiesBinding_errorValidatingUsernameInserText();
                }
                return null;
            }
        };
    }

    private Validator validatorInsertConfirmPassword() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(passwordField.getValue())) {
                    return null;
                }
                return UserModuleConstants.INSTANCE.
                        UserPropertiesBinding_errorValidatingConfirmPasswordInsertText();
            }
        };
    }

    /**
     * @author Nazzareno Sileno - CNR IMAA geoSDI Group
     * @email nazzareno.sileno@geosdi.org
     *
     * Internal Class to map bi-directional Binding
     *
     * model-view binding: method updateField view-model binding: method
     * setModelProperty
     *
     */
    private class RoleComboBinding extends GPFieldBinding {

        public RoleComboBinding() {
            super(userRoleComboBox, GPSimpleUserKeyValue.AUTORITHY.toString());
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            if (userDetail.getAuthority() != null) {
                userRoleComboBox.setValue(userRoleComboBox.findModel(
                        userDetail.getAuthority()));
            }
        }

        @Override
        public void setModelProperty(Object val) {
            if (val != null && val instanceof SimpleComboValue) {
                SimpleComboValue<String> roleString = (SimpleComboValue) val;
                GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
                if (!roleString.getValue().equals(userDetail.getAuthority())) {
                    userDetail.setAuthority(roleString.getValue());
                }
            }
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, ((SimpleComboValue<String>) val).getValue());
        }
    }

    private class TrustedLevelComboBinding extends GPFieldBinding {

        public TrustedLevelComboBinding() {
            super(trustedLevelComboBox,
                    GPSimpleUserKeyValue.TRUSTED_LEVEL.toString());
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            if (userDetail.getTrustedLevel() != null) {
                trustedLevelComboBox.setValue(trustedLevelComboBox.findModel(
                        userDetail.getTrustedLevel()));
            }
        }

        @Override
        public void setModelProperty(Object val) {
            if (val != null && val instanceof SimpleComboValue) {
                SimpleComboValue<GPTrustedLevel> trustedLevel = (SimpleComboValue) val;
                GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
                if (trustedLevel.getValue() != userDetail.getTrustedLevel()) {
                    userDetail.setTrustedLevel(trustedLevel.getValue());
                }
            }
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property,
                    ((SimpleComboValue<GPTrustedLevel>) val).getValue());
        }
    }

    private class UsernameFieldBinding extends GPFieldBinding {

        public UsernameFieldBinding() {
            super(usernameField, GPSimpleUserKeyValue.USERNAME.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setUsername(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            usernameField.setValue(userDetail.getUsername());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class NameFieldBinding extends GPFieldBinding {

        public NameFieldBinding() {
            super(nameField, GPSimpleUserKeyValue.NAME.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setName(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            nameField.setValue(userDetail.getName());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class EmailFieldBinding extends GPFieldBinding {

        public EmailFieldBinding() {
            super(emailField, GPSimpleUserKeyValue.EMAIL.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setEmail(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            emailField.setValue(userDetail.getEmail());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class EnabledFieldBinding extends GPFieldBinding {

        public EnabledFieldBinding() {
            super(enabledField, GPUserManageDetailKeyValue.ENABLED.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setEnabled(val != null ? (Boolean) val : false);
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            enabledField.setValue(userDetail.isEnabled());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class TemporaryFieldBinding extends GPFieldBinding {

        public TemporaryFieldBinding() {
            super(temporaryField,
                    GPUserManageDetailKeyValue.TEMPORARY.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setTemporary(val != null ? (Boolean) val : false);
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            temporaryField.setValue(userDetail.isTemporary());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    public String getPassword() {
        return this.passwordField.getValue();
    }

    public void populateRoles(List<String> roles) {
        this.userRoleComboBox.removeAll();
        this.userRoleComboBox.add(roles);
    }
}
