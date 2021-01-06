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
package org.geosdi.geoplatform.experimental.openam.api.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMUserResponse extends Serializable {

    /**
     * @return {@link String}
     */
    String getUserName();

    /**
     * @param theUserName
     */
    void setUserName(String theUserName);

    /**
     * @return {@link String}
     */
    String getRealm();

    /**
     * @param theRealm
     */
    void setRealm(String theRealm);

    /**
     * @return {@link List<String>}
     */
    List<String> getUid();

    /**
     * @param theUid
     */
    void setUid(List<String> theUid);

    /**
     * @return {@link List<String>}
     */
    List<String> getMail();

    /**
     * @param theMail
     */
    void setMail(List<String> theMail);

    /**
     * @return {@link String}
     */
    List<String> getSn();

    /**
     * @param theSn
     */
    void setSn(List<String> theSn);

    /**
     * @return {@link List<String>}
     */
    List<String> getUserPassword();

    /**
     * @param theUserPassword
     */
    void setUserPassword(List<String> theUserPassword);

    /**
     * @return {@link List<String>}
     */
    List<String> getCn();

    /**
     * @param theCn
     */
    void setCn(List<String> theCn);

    /**
     * @return {@link List<String>}
     */
    List<String> getInetUserStatus();

    /**
     * @param theInetUserStatus
     */
    void setInetUserStatus(List<String> theInetUserStatus);

    /**
     * @return {@link List<String>}
     */
    List<String> getDn();

    /**
     * @param theDn
     */
    void setDn(List<String> theDn);

    /**
     * @return {@link List<String>}
     */
    List<String> getUniversalId();

    /**
     * @param theUniversalId
     */
    void setUniversalId(List<String> theUniversalId);

    /**
     * @return {@link List<String>}
     */
    List<String> getCreateTimestamp();

    /**
     * @param theCreateTimestamp
     */
    void setCreateTimestamp(List<String> theCreateTimestamp);

    /**
     * @return {@link List<String>}
     */
    List<String> getModifyTimestamp();

    /**
     * @param theModifyTimestamp
     */
    void setModifyTimestamp(List<String> theModifyTimestamp);

    /**
     * @return {@link List<String>}
     */
    List<String> getGroups();

    /**
     * @param theGroups
     */
    void setGroups(List<String> theGroups);

    /**
     * @return {@link Boolean}
     */
    @JsonIgnore
    Boolean isGroupsSet();
}
