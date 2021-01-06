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
package org.geosdi.geoplatform.gui.client.model.projects;

import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.global.security.IGPUserSimpleDetail;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.geosdi.geoplatform.gui.model.project.IGPClientProject;

import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPClientProject extends GeoPlatformBeanModel implements IGPClientProject {

    private static final long serialVersionUID = 607645430816968379L;
    //
    private Long id;
    private boolean shared;
    private String message;
    private IGPUserSimpleDetail owner;
    private List<GPFolderClientInfo> rootFolders;

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return super.get(GPClientProjectKey.PROJECT_NAME.toString());
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        set(GPClientProjectKey.PROJECT_NAME.toString(), name);
    }

    /**
     * @return the image
     */
    @Override
    public String getImage() {
        return super.get(GPClientProjectKey.PROJECT_IMAGE.toString());
    }

    public void setImage(String image) {
        set(GPClientProjectKey.PROJECT_IMAGE.toString(), image);
    }

    /**
     * @return the numberOfElements
     */
    @Override
    public int getNumberOfElements() {
        Integer numberOfElements = (Integer) super.get(GPClientProjectKey.PROJECT_ELEMENTS.toString());
        return (numberOfElements != null) ? numberOfElements.intValue() : 0;
    }

    /**
     * @param numberOfElements the numberOfElements to set
     */
    public void setNumberOfElements(int numberOfElements) {
        set(GPClientProjectKey.PROJECT_ELEMENTS.toString(), numberOfElements);
    }

    /**
     * @return the version
     */
    @Override
    public int getVersion() {
        Integer version = (Integer) super.get(GPClientProjectKey.PROJECT_VERSION.toString());
        return (version != null) ? version.intValue() : 0;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        set(GPClientProjectKey.CREATION_DATE.toString(), creationDate);
    }

    /**
     * @return the creationDate
     */
    @Override
    public String getCreationDate() {
        return super.get(GPClientProjectKey.CREATION_DATE.toString());
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        set(GPClientProjectKey.PROJECT_VERSION.toString(), version);
    }

    /**
     * @param theInternalPublic
     */
    @Override
    public void setInternalPublic(boolean theInternalPublic) {
        set(GPClientProjectKey.PROJECT_INTERNAL_PUBLIC.toString(), theInternalPublic);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isInternalPublic() {
        return super.get(GPClientProjectKey.PROJECT_INTERNAL_PUBLIC.toString(), FALSE);
    }

    /**
     * @param theExternalPublic
     */
    @Override
    public void setExternalPublic(boolean theExternalPublic) {
        set(GPClientProjectKey.PROJECT_EXTERNAL_PUBLIC.toString(), theExternalPublic);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isExternalPublic() {
        return super.get(GPClientProjectKey.PROJECT_EXTERNAL_PUBLIC.toString(), FALSE);
    }

    @Override
    public IGPUserSimpleDetail getOwner() {
        return this.owner;
    }

    public void setOwner(IGPUserSimpleDetail owner) {
        this.owner = owner;
    }

    /**
     * @return the shared
     */
    @Override
    public Boolean isProjectShared() {
        return shared;
    }

    @Override
    public String getSharedLabel() {
        return shared ? "Shared" : "";
//        return shared ? LayerModuleConstants.INSTANCE.GPClientProject_sharedLabelText() : "";
    }

    /**
     * @param shared the shared to set
     */
    public void setProjectShared(boolean shared) {
        this.shared = shared;
        set(GPClientProjectKey.PROJECT_SHARED.toString(), this.getSharedLabel());
    }

    /**
     * @return the defaultProject
     */
    @Override
    public boolean isDefaultProject() {
        Boolean isDefault = (Boolean) super.get(GPClientProjectKey.DEFAULT_PROJECT.toString());
        return (isDefault == null) ? false : isDefault.booleanValue();
    }

    /**
     * @param defaultProject the defaultProject to set
     */
    public void setDefaultProject(boolean defaultProject) {
        set(GPClientProjectKey.DEFAULT_PROJECT.toString(), defaultProject);
        set(GPClientProjectKey.DEFAULT_PROJECT_KEY_MESSAGE.toString(),
                this.getDefaultProjectLabel());
    }

    //    public void setLabelDefaultProject(boolean defaultProject){
//        set(GPClientProjectKey.DEFAULT_PROJECT.toString(), defaultProject);
//        set(GPClientProjectKey.DEFAULT_PROJECT_KEY_MESSAGE.toString(),
//                this.getDefaultProjectLabel(defaultProject));
//    }
    @Override
    public String getDefaultProjectLabel() {
        return this.isDefaultProject() ? "DEFAULT PROJECT" : "";
    }

    @Override
    public List<GPFolderClientInfo> getRootFolders() {
        return rootFolders;
    }

    public void setRootFolders(List<GPFolderClientInfo> rootFolders) {
        this.rootFolders = rootFolders;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getPathImage() {
        return super.get(GPClientProjectKey.PROJECT_PATH_IMAGE.toString());
    }

    /**
     * @param thePathImage
     */
    public void setPathImage(String thePathImage) {
        super.set(GPClientProjectKey.PROJECT_PATH_IMAGE.toString(), thePathImage);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getDescription() {
        return super.get(GPClientProjectKey.PROJECT_DESCRIPTION.toString());
    }

    /**
     * @param theDescription
     */
    public void setDescription(String theDescription) {
        super.set(GPClientProjectKey.PROJECT_DESCRIPTION.toString(), theDescription);
    }

    //    /**
//     * @return the message
//     */
//    public String getMessage() {
//        return message;
//    }
//    /**
//     * @param message the message to set
//     */
//    public void setMessage(String message) {
//        this.message = message;
//        set(GPClientProjectKey.DEFAULT_PROJECT_KEY_MESSAGE.toString(), this.message);
//    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPClientProject other = (GPClientProject) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "GPClientProject {"
                + "id=" + id
                + ", name=" + this.getName()
                + ", image=" + this.getImage()
                + ", numberOfElements=" + this.getNumberOfElements()
                + ", version=" + this.getVersion()
                + ", shared=" + shared
                + ", defaultProject=" + this.isDefaultProject()
                + ", message=" + message
                + ", internalPublic=" + this.isInternalPublic()
                + ", externalPublic=" + this.isExternalPublic()
                + ", owner=" + owner
                + ", rootFolders=" + rootFolders + '}';
    }
}