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
package org.geosdi.geoplatform.request;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PutAccountsProjectRequest implements Serializable {

    private static final long serialVersionUID = 757052456271185185L;
    //
    private Long projectID;
    @XmlElementWrapper(name = "accountsIDS")
    @XmlElement(name = "accountID")
    private Map<Long, Integer> accountIDsProject;

    public PutAccountsProjectRequest() {
    }

    /**
     * @param projectID
     * @param accountIDsProject
     */
    public PutAccountsProjectRequest(Long projectID, Map<Long, Integer> accountIDsProject) {
        this.projectID = projectID;
        this.accountIDsProject = accountIDsProject;
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
     * @return the accountIDsProject
     */
    public Map<Long, Integer> getAccountIDsProject() {
        return accountIDsProject;
    }

    /**
     * @param accountIDsProject the accountIDsProject to set
     */
    public void setAccountIDsProject(Map<Long, Integer> accountIDsProject) {
        this.accountIDsProject = accountIDsProject;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + "projectID = " + projectID
                + ", accountIDsProject = " + accountIDsProject + '}';
    }

}
