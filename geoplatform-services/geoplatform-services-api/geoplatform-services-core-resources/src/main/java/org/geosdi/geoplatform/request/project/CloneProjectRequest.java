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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CloneProjectRequest implements Serializable {

    private static final long serialVersionUID = 5586917396739817679L;
    //
    private Long gpProjectID;
    private Long accountID;
    private String nameProject;

    public CloneProjectRequest() {
    }

    public CloneProjectRequest(Long gpProjectID,
            Long theAccountID,String nameProject) {
        this.gpProjectID = gpProjectID;
        this.accountID = theAccountID;
        this.nameProject = nameProject;
    }

    /**
     *
     * @return the gpProjectID
     */
    public Long getGpProjectID() {
        return gpProjectID;
    }

    /**
     *
     * @param gpProjectID
     */
    public void setGpProjectID(Long gpProjectID) {
        this.gpProjectID = gpProjectID;
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
     *
     * @return
     */
    public String getNameProject() {
        return nameProject;
    }

    /**
     *
     * @param nameProject
     */
    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    @Override
    public String toString() {
        return "CloneProjectRequest{" +
                "gpProjectID='" + gpProjectID + '\'' +
                ", accountID=" + accountID +
                ", nameProject='" + nameProject + '\'' +
                '}';
    }
}
