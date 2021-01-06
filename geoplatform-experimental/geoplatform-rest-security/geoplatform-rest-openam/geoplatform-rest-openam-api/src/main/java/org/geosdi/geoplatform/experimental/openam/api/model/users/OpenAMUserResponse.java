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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMUserResponse implements IOpenAMUserResponse {

    private static final long serialVersionUID = 8468444058982256559L;
    //
    @JsonProperty(value = "username")
    private String userName;
    private String realm;
    private List<String> uid;
    private List<String> mail;
    private List<String> sn;
    @JsonProperty(value = "userpassword")
    private List<String> userPassword;
    private List<String> cn;
    @JsonProperty(value = "inetUserStatus")
    private List<String> inetUserStatus;
    private List<String> dn;
    @JsonProperty(value = "universalid")
    private List<String> universalId;
    private List<String> createTimestamp;
    private List<String> modifyTimestamp;
    @JsonProperty(value = "memberOf")
    private List<String> groups;

    /**
     * @return {@link String}
     */
    @Override
    public String getUserName() {
        return this.userName;
    }

    /**
     * @param theUserName
     */
    @Override
    public void setUserName(String theUserName) {
        this.userName = theUserName;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getRealm() {
        return this.realm;
    }

    /**
     * @param theRealm
     */
    @Override
    public void setRealm(String theRealm) {
        this.realm = theRealm;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getUid() {
        return this.uid;
    }

    /**
     * @param theUid
     */
    @Override
    public void setUid(List<String> theUid) {
        this.uid = theUid;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getMail() {
        return this.mail;
    }

    /**
     * @param theMail
     */
    @Override
    public void setMail(List<String> theMail) {
        this.mail = theMail;
    }

    /**
     * @return {@link String}
     */
    @Override
    public List<String> getSn() {
        return this.sn;
    }

    /**
     * @param theSn
     */
    @Override
    public void setSn(List<String> theSn) {
        this.sn = theSn;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getUserPassword() {
        return this.userPassword;
    }

    /**
     * @param theUserPassword
     */
    @Override
    public void setUserPassword(List<String> theUserPassword) {
        this.userPassword = theUserPassword;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getCn() {
        return this.cn;
    }

    /**
     * @param theCn
     */
    @Override
    public void setCn(List<String> theCn) {
        this.cn = theCn;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getInetUserStatus() {
        return this.inetUserStatus;
    }

    /**
     * @param theInetUserStatus
     */
    @Override
    public void setInetUserStatus(List<String> theInetUserStatus) {
        this.inetUserStatus = theInetUserStatus;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getDn() {
        return this.dn;
    }

    /**
     * @param theDn
     */
    @Override
    public void setDn(List<String> theDn) {
        this.dn = theDn;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getUniversalId() {
        return this.universalId;
    }

    /**
     * @param theUniversalId
     */
    @Override
    public void setUniversalId(List<String> theUniversalId) {
        this.universalId = theUniversalId;
    }

    /**
     * @return {@link String}
     */
    @Override
    public List<String> getCreateTimestamp() {
        return this.createTimestamp;
    }

    /**
     * @param theCreateTimestamp
     */
    @Override
    public void setCreateTimestamp(List<String> theCreateTimestamp) {
        this.createTimestamp = theCreateTimestamp;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getModifyTimestamp() {
        return this.modifyTimestamp;
    }

    /**
     * @param theModifyTimestamp
     */
    @Override
    public void setModifyTimestamp(List<String> theModifyTimestamp) {
        this.modifyTimestamp = theModifyTimestamp;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getGroups() {
        return this.groups;
    }

    /**
     * @param theGroups
     */
    @Override
    public void setGroups(List<String> theGroups) {
        this.groups = theGroups;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isGroupsSet() {
        return this.groups != null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "userName='" + userName + '\'' +
                ", realm='" + realm + '\'' +
                ", uid=" + uid +
                ", mail=" + mail +
                ", sn=" + sn +
                ", userPassword=" + userPassword +
                ", cn=" + cn +
                ", inetUserStatus=" + inetUserStatus +
                ", dn=" + dn +
                ", universalId=" + universalId +
                ", createTimestamp=" + createTimestamp +
                ", modifyTimestamp=" + modifyTimestamp +
                ", groups=" + groups +
                '}';
    }
}
