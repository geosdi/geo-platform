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
package org.geosdi.geoplatform.gui.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface UserModuleConstants extends Constants {

    public UserModuleConstants INSTANCE = GWT.create(UserModuleConstants.class);

    @DefaultStringValue("Username")
    String usernameFieldText();

    @DefaultStringValue("Enabled")
    String enabledFieldLabelText();

    @DefaultStringValue("Role")
    String userRoleLabelText();

    @DefaultStringValue("Name")
    String nameFieldText();

    @DefaultStringValue("Email")
    String emailFieldText();

    @DefaultStringValue("Temporary")
    String temporaryFieldLabelText();

    @DefaultStringValue("Complete name is not valid (example: Andy Power)")
    String errorValidatingCompleteNameUpdateText();

    @DefaultStringValue("Email is not valid (example: andy@foo.com)")
    String errorValidatingEmailUpdateText();

    @DefaultStringValue("The minimun lenght for password is 4")
    String errorValidatingPasswordUpdateText();

    @DefaultStringValue("User successfully modified")
    String infoUserSuccesfullyModifiedText();

    @DefaultStringValue("User successfully added")
    String infoUserSuccesfullyAddedText();

    /**
     * Start UserPropertiesWidget
     */
    @DefaultStringValue("User Properties")
    String UserPropertiesWidget_headingText();

    /**
     * Start UserPropertiesManagerWidget
     */
    @DefaultStringValue("User Properties Management")
    String UserPropertiesManagerWidget_headingText();

    /**
     * Start UserOptionsWidget
     */
    @DefaultStringValue("User Options")
    String UserOptionsWidget_headingText();

    /**
     * Start ManageRolesWidget
     */
    @DefaultStringValue("There are unsaved permissions, are you sure you want to exit?")
    String ManageRolesWidget_unsavedPermissionsWarningText();

    @DefaultStringValue("Select a role or creates one...")
    String ManageRolesWidget_statusSelectOrCreateRoleText();

    @DefaultStringValue("Icon")
    String ManageRolesWidget_iconText();

    @DefaultStringValue("Permissions")
    String ManageRolesWidget_permissionsText();

    @DefaultStringValue("Component ID")
    String ManageRolesWidget_componentIDText();

    @DefaultStringValue("GeoPlatform Roles Management")
    String ManageRolesWidget_headingText();

    @DefaultStringValue("Role permissions")
    String ManageRolesWidget_rolePermissionsText();

    @DefaultStringValue("Change role permissions")
    String ManageRolesWidget_rolePermissionsTooltipText();

    @DefaultStringValue("All permissions to")
    String ManageRolesWidget_allPermissionsToText();

    @DefaultStringValue("Copy permissions from")
    String ManageRolesWidget_copyPermissionsText();

    @DefaultStringValue("Copy permissions from an existent role")
    String ManageRolesWidget_copyPermissionsTooltipText();

    @DefaultStringValue("New Role")
    String ManageRolesWidget_newRoleText();

    @DefaultStringValue("Role name already exists")
    String ManageRolesWidget_roleNameAlreadyExistsText();

    @DefaultStringValue("Enter the new role name")
    String ManageRolesWidget_enterNewRoleNameText();

    @DefaultStringValue("Create new role")
    String ManageRolesWidget_newRoleTooltipText();

    @DefaultStringValue("Set up role permissions to a single permission")
    String ManageRolesWidget_rolePermissionsToASinglePermissionText();

    /**
     * Start ManageUsersPagWidget
     */
    @DefaultStringValue("Retrieve roles")
    String ManageUsersPagWidget_statusRetrievingRolesText();

    @DefaultStringValue("Error retrieving roles")
    String ManageUsersPagWidget_statusErrorRetrievingRolesText();

    @DefaultStringValue("GeoPlatform Users Management")
    String ManageUsersPagWidget_headingText();

    @DefaultStringValue("Modify User")
    String ManageUsersPagWidget_modifyUserText();

    @DefaultStringValue("Find User")
    String ManageUsersPagWidget_findUserText();

    @DefaultStringValue("Add User")
    String ManageUsersPagWidget_addUserText();

    @DefaultStringValue("Trustes")
    String ManageUsersPagWidget_trustedFieldLabelText();

    /**
     * Start DeleteUserRenderer
     */
    @DefaultStringValue("Delete User")
    String DeleteUserRenderer_deleteUserTitleText();

    @DefaultStringValue("User successfully deleted")
    String DeleteUserRenderer_infoUserSuccesfullyDeletedText();

    /**
     * Start UserOptionsMemberWidgets
     */
    @DefaultStringValue("Widgets")
    String UserOptionsMemberWidgets_titleText();

    @DefaultStringValue("Active WIDGETS")
    String UserOptionsMemberWidgets_activeWidgetsLabelText();

    /**
     * Start UserOptionsMemberView
     */
    @DefaultStringValue("View options saved succesfully.")
    String UserOptionsMemberView_statusSaveOptionSuccesfullyText();

    @DefaultStringValue("Error saving view options.")
    String UserOptionsMemberView_statusErrorSavingText();

    @DefaultStringValue("View")
    String UserOptionsMemberView_titleText();

    @DefaultStringValue("Load expanded folders at start-up")
    String UserOptionsMemberView_startupStrategyLabelText();

    /**
     * Start UserOptionsMemberUser
     */
    @DefaultStringValue("The new password must be different from the old password")
    String UserOptionsMemberUser_errorValidatingNewPasswordDifferenceUpdateText();

    @DefaultStringValue("Retyped new password does not match")
    String UserOptionsMemberUser_errorValidatingRetypedPasswordUpdateText();

    @DefaultStringValue("Change email")
    String UserOptionsMemberUser_changeEmailHeadingText();

    @DefaultStringValue("Change password")
    String UserOptionsMemberUser_changePasswordHeadingText();

    @DefaultStringValue("User")
    String UserOptionsMemberUser_userText();

    @DefaultStringValue("Retype new")
    String UserOptionsMemberUser_newRePasswordLabelText();

    @DefaultStringValue("Retype the new password")
    String UserOptionsMemberUser_newRePasswordTooltipText();

    @DefaultStringValue("Enter a new password")
    String UserOptionsMemberUser_newPasswordTooltipText();

    @DefaultStringValue("Current")
    String UserOptionsMemberUser_oldPasswordLabelText();

    @DefaultStringValue("Your current password")
    String UserOptionsMemberUser_oldPasswordTooltipText();

    @DefaultStringValue("Your complete name")
    String UserOptionsMemberUser_nameFieldTooltipText();

    @DefaultStringValue("Your email")
    String UserOptionsMemberUser_emailFieldTooltipText();

    /**
     * Start UserOptionsMemberDisk
     */
    @DefaultStringValue("Disk")
    String UserOptionsMemberDisk_titleText();

    @DefaultStringValue("Set QUOTA")
    String UserOptionsMemberDisk_quotaLabelText();

    /**
     * Start UserPropertiesBinding
     */
    @DefaultStringValue("Retyped reset password don't match")
    String UserPropertiesBinding_errorValidatingConfirmPasswordUpdateText();

    @DefaultStringValue("Enter a complete name (example: Andy Power)")
    String UserPropertiesBinding_errorValidatingCompleteNameInsertText();

    @DefaultStringValue("Enter a valid email (example: andy@foo.org)")
    String UserPropertiesBinding_errorValidatingEmailInsertText();

    @DefaultStringValue("Enter a valid username (example: foo.3_BE-1)")
    String UserPropertiesBinding_errorValidatingUsernameInserText();

    @DefaultStringValue("Retyped password don't match")
    String UserPropertiesBinding_errorValidatingConfirmPasswordInsertText();

    @DefaultStringValue("Reset password")
    String UserPropertiesBinding_resetPasswordText();

    @DefaultStringValue("Reset password of the user")
    String UserPropertiesBinding_resetPasswordTooltipText();

    @DefaultStringValue("Trusted Level")
    String UserPropertiesBinding_trustedLevelLabelText();

    @DefaultStringValue("Select a trusted level... (required)")
    String UserPropertiesBinding_trustedLevelEmptyText();

    @DefaultStringValue("Trusted level of the execution permissions for a use case")
    String UserPropertiesBinding_trustedLevelTooltipText();

    @DefaultStringValue("Select a role... (required)")
    String UserPropertiesBinding_userRoleEmptyText();

    @DefaultStringValue("Role of the user")
    String UserPropertiesBinding_userRoleTooltipText();

    @DefaultStringValue("Check if the user is temporary (will be disabled in 10 days)")
    String UserPropertiesBinding_temporaryFieldChooseTooltipText();

    @DefaultStringValue("Checked if the user is temporary")
    String UserPropertiesBinding_temporaryFieldCheckedTooltipText();

    @DefaultStringValue("Dechecked and the user will not be temporary")
    String UserPropertiesBinding_temporaryFieldDecheckedTooltipText();

    @DefaultStringValue("Check for enable the user")
    String UserPropertiesBinding_enableFieldTooltipText();

    @DefaultStringValue("Will be disabled in 10 days")
    String UserPropertiesBinding_expireLabelText();

    @DefaultStringValue("User expired")
    String UserPropertiesBinding_userExpiredText();

    @DefaultStringValue("Password")
    String UserPropertiesBinding_passwordFieldText();

    @DefaultStringValue("Password of the user")
    String UserPropertiesBinding_passwordTooltipText();

    @DefaultStringValue("Confirm password")
    String UserPropertiesBinding_repeatPasswordFieldText();

    @DefaultStringValue("Confirm the password of the user")
    String UserPropertiesBinding_repeatPasswordFieldTooltipText();

    @DefaultStringValue("Enter a username (required)")
    String UserPropertiesBinding_usernameFieldEmptyText();

    @DefaultStringValue("Username of the user")
    String UserPropertiesBinding_usernameFieldToolTipText();

    @DefaultStringValue("Enter a complete name (required)")
    String UserPropertiesBinding_nameFieldEmptyText();

    @DefaultStringValue("Complete name of the user")
    String UserPropertiesBinding_nameFieldToolTipText();

    @DefaultStringValue("Enter an email (required)")
    String UserPropertiesBinding_emailFieldEmptyText();

    @DefaultStringValue("Email of the user")
    String UserPropertiesBinding_emailFieldToolTipText();

    /**
     * Start UserOptionsMenuAction
     */
    @DefaultStringValue("Options")
    String UserOptionsMenuAction_titleText();

    /**
     * Start ManageUsersMenuAction
     */
    @DefaultStringValue("Manage Users")
    String ManageUsersMenuAction_titleText();

    /**
     * Start ManageRolesMenuAction
     */
    @DefaultStringValue("Manage Roles")
    String ManageRolesMenuAction_titleText();

    @DefaultStringValue("Error retrieving roles")
    String ManageRolesMenuAction_errorRetrievingRoleTitleText();

    @DefaultStringValue("Error retrieving permissions IDs")
    String ManageRolesMenuAction_errorRetrievingPermissionsIDsTitleText();
}
