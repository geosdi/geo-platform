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
package org.geosdi.geoplatform.services.development;

import java.util.List;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.springframework.security.acls.domain.BasePermission;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class EntityCorrectness {

    // =========================================================================
    // === Organization
    // =========================================================================
    public static void checkOrganization(GPOrganization organization) throws
            IllegalParameterFault {
        if (organization == null) {
            throw new IllegalParameterFault("Organization must be NOT NULL.");
        }
        if (EntityCorrectness.empty(organization.getName())) {
            throw new IllegalParameterFault(
                    "Organization \"name\" must be NOT NULL or NOT empty.");
        }
    }

    public static void checkOrganizationLog(GPOrganization organization) {
        try {
            EntityCorrectness.checkOrganization(organization);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    // =========================================================================
    // === Project
    // =========================================================================
    public static void checkProject(GPProject project) throws
            IllegalParameterFault {
        if (project == null) {
            throw new IllegalParameterFault("Project must be NOT NULL.");
        }
        if (EntityCorrectness.empty(project.getName())) {
            throw new IllegalParameterFault(
                    "Project \"name\" must be NOT NULL or NOT empty.");
        }
        if (project.getNumberOfElements() < 0) {
            throw new IllegalParameterFault(
                    "Project \"numberOfElements\" must be greater or equal 0.");
        }
    }

    public static void checkProjectLog(GPProject project) {
        try {
            EntityCorrectness.checkProject(project);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    public static void checkProjectListLog(List<GPProject> projects) {
        for (GPProject project : projects) {
            EntityCorrectness.checkProjectLog(project);
        }
    }

    // =========================================================================
    // === AccountProject
    // =========================================================================
    public static void checkAccountProject(GPAccountProject accountProject)
            throws IllegalParameterFault {
        if (accountProject == null) {
            throw new IllegalParameterFault("AccountProject must be NOT NULL.");
        }
        if (accountProject.getProject() == null) {
            throw new IllegalParameterFault(
                    "AccountProject \"project\" must be NOT NULL.");
        }
        if (accountProject.getAccount() == null) {
            throw new IllegalParameterFault(
                    "AccountProject \"account\" must be NOT NULL.");
        }
        if ((accountProject.getPermissionMask() < BasePermission.READ.getMask())
                || (accountProject.getPermissionMask() > BasePermission.ADMINISTRATION.getMask())) {
            throw new IllegalParameterFault("PermissionMask is incorrect.");
        }
    }

    public static void checkAccountProjectLog(GPAccountProject accountProject) {
        try {
            EntityCorrectness.checkAccountProject(accountProject);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    public static void checkAccountProjectListLog(
            List<GPAccountProject> accountProject) {
        for (GPAccountProject up : accountProject) {
            EntityCorrectness.checkAccountProjectLog(up);
        }
    }

    // =========================================================================
    // === Account and Authorities
    // =========================================================================
    public static void checkAccount(GPAccount account) throws
            IllegalParameterFault {
        if (account == null) {
            throw new IllegalParameterFault("Account must be NOT NULL.");
        }
        if (EntityCorrectness.empty(account.getNaturalID())) {
            throw new IllegalParameterFault(
                    "Account \"naturalID\" (i.e. \"username\" for User"
                    + " or \"appID\" for Application) must be NOT NULL or NOT empty.");
        }
        GPOrganization organization = account.getOrganization();
        if (organization == null) {
            throw new IllegalParameterFault(
                    "Account Organization must not be null.");
        }
        if (account instanceof GPUser) {
            EntityCorrectness.checkUser((GPUser) account);
        }
    }

    private static void checkUser(GPUser user) throws IllegalParameterFault {
        if (EntityCorrectness.empty(user.getName())) {
            throw new IllegalParameterFault(
                    "User \"name\" must be NOT NULL or NOT empty.");
        }
        if (EntityCorrectness.empty(user.getPassword())) {
            throw new IllegalParameterFault(
                    "User \"password\" must be NOT NULL or NOT empty.");
        }
    }

    public static void checkAuthority(List<GPAuthority> authorities) throws
            IllegalParameterFault {
        if (authorities == null || authorities.isEmpty()) {
            throw new IllegalParameterFault("Account must have at least a role.");
        }
        for (GPAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (EntityCorrectness.empty(role)) {
                throw new IllegalParameterFault(
                        "Authority is null or NOT empty.");
            }
            GPTrustedLevel trustedLevel = authority.getTrustedLevel();
            if (trustedLevel == null) {
                throw new IllegalParameterFault("Trusted Level is null.");
            }
        }
    }

    public static void checkAccountAndAuthority(GPAccount account) throws
            IllegalParameterFault {
        EntityCorrectness.checkAccount(account);
        EntityCorrectness.checkAuthority(account.getGPAuthorities());
    }

    public static void checkAccountLog(GPAccount account) {
        try {
            EntityCorrectness.checkAccount(account);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    public static void checkAccountListLog(List<GPAccount> accounts) {
        for (GPAccount account : accounts) {
            EntityCorrectness.checkAccountLog(account);
        }
    }

    public static void checkAuthorityLog(List<GPAuthority> authorities) {
        try {
            EntityCorrectness.checkAuthority(authorities);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    public static void checkAccountAndAuthorityLog(GPAccount account) {
        try {
            EntityCorrectness.checkAccountAndAuthority(account);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    // =========================================================================
    // === Viewport
    // =========================================================================
    public static void checkViewport(GPViewport viewport) throws
            IllegalParameterFault {
        if (viewport == null) {
            throw new IllegalParameterFault("Viewport must be NOT NULL.");
        }
        if (viewport.getAccountProject() == null) {
            throw new IllegalParameterFault(
                    "Viewport \"accountProject\" must be NOT NULL.");
        }
        if (EntityCorrectness.empty(viewport.getName())) {
            throw new IllegalParameterFault(
                    "Viewport \"name\" must be NOT NULL or NOT empty.");
        }
        if (viewport.getBbox() == null) {
            throw new IllegalParameterFault(
                    "Viewport \"BBOX\" must be NOT NULL or NOT empty.");
        }
    }

    public static void checkViewportLog(GPViewport viewport) {
        try {
            EntityCorrectness.checkViewport(viewport);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    // =========================================================================
    // === Folder
    // =========================================================================
    public static void checkFolder(GPFolder folder) throws IllegalParameterFault {
        if (folder == null) {
            throw new IllegalParameterFault("Folder must be NOT NULL.");
        }
        if (folder.getProject() == null) {
            throw new IllegalParameterFault(
                    "Folder \"project\" must be NOT NULL.");
        }
        if (EntityCorrectness.empty(folder.getName())) {
            throw new IllegalParameterFault(
                    "Folder \"name\" must be NOT NULL or NOT empty.");
        }
        if (folder.getNumberOfDescendants() < 0) {
            throw new IllegalParameterFault(
                    "Folder \"numberOfDescendants\" must be greater or equal 0.");
        }
        if (folder.getPosition() < 1) {
            throw new IllegalParameterFault(
                    "Folder \"position\" must be greater or equal 1.");
        }
    }

    public static void checkFolderLog(GPFolder folder) {
        try {
            EntityCorrectness.checkFolder(folder);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    // =========================================================================
    // === Layer
    // =========================================================================
    public static void checkLayerComplete(GPLayer layer) throws
            IllegalParameterFault {
        EntityCorrectness.checkLayer(layer);
        if (layer.getProject() == null) {
            throw new IllegalParameterFault(
                    "Layer \"project\" must be NOT NULL.");
        }
    }

    public static void checkLayerCompleteListLog(List<GPLayer> layers) {
        for (GPLayer layer : layers) {
            EntityCorrectness.checkLayerCompleteLog(layer);
        }
    }

    public static void checkLayerCompleteLog(GPLayer layer) {
        try {
            EntityCorrectness.checkLayerComplete(layer);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    public static void checkLayer(GPLayer layer) throws IllegalParameterFault {
        if (layer == null) {
            throw new IllegalParameterFault("Layer must be NOT NULL.");
        }
        if (layer.getFolder() == null) {
            throw new IllegalParameterFault("Layer \"folder\" must be NOT NULL.");
        }
        if (layer.getTitle() == null) {
            throw new IllegalParameterFault("Layer \"title\" must be NOT NULL.");
        }
        if (layer.getLayerType() == null) {
            throw new IllegalParameterFault(
                    "Layer \"layerType\" must be NOT NULL.");
        }
        if (layer.getPosition() < 1) {
            throw new IllegalParameterFault(
                    "Layer \"position\" must be greater or equal 1.");
        }
    }

    public static void checkLayerListLog(List<GPLayer> layers) {
        for (GPLayer layer : layers) {
            EntityCorrectness.checkLayerLog(layer);
        }
    }

    public static void checkLayerLog(GPLayer layer) {
        try {
            EntityCorrectness.checkLayer(layer);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    // =========================================================================
    // === Message
    // =========================================================================
    public static void checkMessage(GPMessage message) throws
            IllegalParameterFault {
        if (message == null) {
            throw new IllegalParameterFault("Message must be NOT NULL.");
        }
        if (message.getRecipient() == null) {
            throw new IllegalParameterFault(
                    "Message \"recipient\" must be NOT NULL.");
        }
        Long recipientID = message.getRecipient().getId();
        if (recipientID == null) {
            throw new IllegalParameterFault(
                    "Message \"recipient.id\" must be NOT NULL and.");
        }
        if (recipientID < 1) {
            throw new IllegalParameterFault(
                    "Message \"recipient.id\" must be greater or equal 1.");
        }
        if (message.getSender().getId() == null) {
            throw new IllegalParameterFault(
                    "Message \"sender.id\" must be NOT NULL.");
        }
        Long senderID = message.getSender().getId();
        if (senderID == null) {
            throw new IllegalParameterFault(
                    "Message \"sender\" must be NOT NULL.");
        }
        if (senderID < 1) {
            throw new IllegalParameterFault(
                    "Message \"sender.id\" must be greater or equal 1.");
        }
        if (message.getCreationDate() == null) {
            throw new IllegalParameterFault(
                    "Message \"creationDate\" must be NOT NULL.");
        }
        if (EntityCorrectness.empty(message.getSubject())) {
            throw new IllegalParameterFault(
                    "Message \"subject\" must be NOT NULL or NOT empty.");
        }
        if (EntityCorrectness.empty(message.getText())) {
            throw new IllegalParameterFault(
                    "Message \"text\" must be NOT NULL or NOT empty.");
        }
    }

    public static void checkMessageListLog(List<GPMessage> messages) {
        for (GPMessage message : messages) {
            EntityCorrectness.checkMessageLog(message);
        }
    }

    public static void checkMessageLog(GPMessage message) {
        try {
            EntityCorrectness.checkMessage(message);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    public static void checkInteger(Integer value, String field) throws
            IllegalParameterFault {
        if (checkValue(value)) {
            throw new IllegalParameterFault(
                    "\"" + field + "\" must be NOT NULL or less than 0.");
        }
    }

    private static boolean checkValue(Integer value) {
        return ((value == null) || (value < 0));
    }

    // =========================================================================
    // === String
    // =========================================================================    
    public static void ckeckString(String value, String field) throws
            IllegalParameterFault {
        if (EntityCorrectness.empty(value)) {
            throw new IllegalParameterFault(
                    "\"" + field + "\" must be NOT NULL or NOT empty.");
        }
    }

    public static void ckeckStringLog(String value, String field) {
        try {
            EntityCorrectness.ckeckString(value, field);
        } catch (IllegalParameterFault ex) {
            throw new EntityCorrectnessException(ex.getMessage());
        }
    }

    //
    // =========================================================================
    //
    private static boolean empty(String value) {
        if (value == null) {
            return true;
        }
        return value.trim().equals("");
    }
}
