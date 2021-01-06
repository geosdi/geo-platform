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
package org.geosdi.geoplatform.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountProjectPropertiesDTO implements Serializable {

    private static final long serialVersionUID = 6624631694717779662L;
    //
    @XmlElement(required = true)
    private Long accountID;
    //
    @XmlElement(required = true)
    private Long projectID;
    private String projectName;
    private Integer projectVersion;
    private boolean defaultProject;
    private boolean shared;
    private boolean internalPublic;
    private boolean externalPublic;
    private String projectDescription;
    private String pathImage;

    /**
     * Default constructor
     */
    public AccountProjectPropertiesDTO() {
    }

    /**
     * @param theAccountID
     * @param theProjectID
     * @param theProjectName
     * @param theProjectVersion
     * @param isDefaultProject
     * @param isShared
     * @param isInternalPublic
     * @param isTheExternalPublic
     */
    public AccountProjectPropertiesDTO(Long theAccountID, Long theProjectID, String theProjectName,
            Integer theProjectVersion, boolean isDefaultProject, boolean isShared, boolean isInternalPublic,
            boolean isTheExternalPublic) {
        this.accountID = theAccountID;
        this.projectID = theProjectID;
        this.projectName = theProjectName;
        this.projectVersion = theProjectVersion;
        this.defaultProject = isDefaultProject;
        this.shared = isShared;
        this.internalPublic = isInternalPublic;
        this.externalPublic = isTheExternalPublic;
    }

    /**
     * @return the accountID
     */
    public Long getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the projectID
     */
    public Long getProjectID() {
        return projectID;
    }

    /**
     * @param projectID the projectID to set
     */
    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * @return the projectVersion
     */
    public Integer getProjectVersion() {
        return projectVersion;
    }

    /**
     * @param projectVersion the projectVersion to set
     */
    public void setProjectVersion(Integer projectVersion) {
        this.projectVersion = projectVersion;
    }

    /**
     * @return the defaultProject
     */
    public boolean isDefaultProject() {
        return defaultProject;
    }

    /**
     * @param defaultProject the defaultProject to set
     */
    public void setDefaultProject(boolean defaultProject) {
        this.defaultProject = defaultProject;
    }

    /**
     * @return {@link String}
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * @param theProjectDescription
     */
    public void setProjectDescription(String theProjectDescription) {
        this.projectDescription = theProjectDescription;
    }

    /**
     * @return {@link String}
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * @param thePathImage
     */
    public void setPathImage(String thePathImage) {
        this.pathImage = thePathImage;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isInternalPublic() {
        return internalPublic;
    }

    /**
     * @param internalPublic
     */
    public void setInternalPublic(boolean internalPublic) {
        this.internalPublic = internalPublic;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isExternalPublic() {
        return externalPublic;
    }

    /**
     * @param externalPublic
     */
    public void setExternalPublic(boolean externalPublic) {
        this.externalPublic = externalPublic;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" \n{");
        str.append(" accountID = ").append(accountID);
        str.append(", projectID = ").append(projectID);
        str.append(", projectName = ").append(projectName);
        str.append(", projectVersion = ").append(projectVersion);
        str.append(", defaultProject = ").append(defaultProject);
        str.append(", internalPublic = ").append(internalPublic);
        str.append(", externalPublic = ").append(externalPublic);
        return str.append("\n}").toString();
    }
}