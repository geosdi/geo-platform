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
package org.geosdi.geoplatform.request.project;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.core.model.GPProject;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveProjectRequest implements Serializable {

    private static final long serialVersionUID = 7865892877630380192L;
    //
    private String accountNaturalID;
    private GPProject project;
    private boolean defaultProject;

    /**
     * Only FOR JAXB
     */
    public SaveProjectRequest() {
    }

    public SaveProjectRequest(String theAccountNaturalID, GPProject theProject,
            boolean theDefaultProject) {
        this.accountNaturalID = theAccountNaturalID;
        this.project = theProject;
        this.defaultProject = theDefaultProject;
    }

    /**
     * @return the accountNaturalID
     */
    public String getAccountNaturalID() {
        return accountNaturalID;
    }

    /**
     * @param accountNaturalID the accountNaturalID to set
     */
    public void setAccountNaturalID(String accountNaturalID) {
        this.accountNaturalID = accountNaturalID;
    }

    /**
     * @return the project
     */
    public GPProject getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(GPProject project) {
        this.project = project;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + "accountNaturalID = "
                + accountNaturalID + ", project = " + project
                + ", defaultProject = " + defaultProject + '}';
    }
}
