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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAmUser implements IOpenAMUser {

    private static final long serialVersionUID = -8703383372264522298L;
    //
    @JsonProperty(value = "givenName")
    private String firstName;
    @JsonProperty(value = "sn")
    private String lastName;
    @JsonProperty(value = "cn")
    private String fullName;
    @JsonProperty(value = "username")
    private String userName;
    @JsonProperty(value = "userpassword")
    private String userPassword;
    private String mail;
    @JsonProperty(value = "memberOf")
    private List<String> groups;
    @JsonProperty(value = "inetUserStatus")
    private List<String> inetUserStatus;

    public OpenAmUser() {
    }

    public OpenAmUser(String theFirstName, String theLastName, String theFullName,
            String theUserName, String theUserPassword, String theMail) {
        this.firstName = theFirstName;
        this.lastName = theLastName;
        this.fullName = theFullName;
        this.userName = theUserName;
        this.userPassword = theUserPassword;
        this.mail = theMail;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @param theFirstName
     */
    @Override
    public void setFirstName(String theFirstName) {
        this.firstName = theFirstName;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @param theLastName
     */
    @Override
    public void setLastName(String theLastName) {
        this.lastName = theLastName;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getFullName() {
        return this.fullName;
    }

    /**
     * @param theFullName
     */
    @Override
    public void setFullName(String theFullName) {
        this.fullName = theFullName;
    }

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
    public String getUserPassword() {
        return this.userPassword;
    }

    /**
     * @param theUserPassword
     */
    @Override
    public void setUserPassword(String theUserPassword) {
        this.userPassword = theUserPassword;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getMail() {
        return this.mail;
    }

    /**
     * @param theMail
     */
    @Override
    public void setMail(String theMail) {
        this.mail = theMail;
    }

    /**
     * @return {@link List <String>}
     */
    public List<String> getGroups() {
        return this.groups;
    }

    /**
     * @param theIsMemberOf
     */
    public void setGroups(List<String> theIsMemberOf) {
        this.groups = theIsMemberOf;
    }

    /**
     * @return {@link Boolean}
     */
    @JsonIgnore
    @Override
    public Boolean isGroupsSet() {
        return (this.groups != null);
    }

    /**
     * @param theGroup
     */
    @Override
    public void addGroup(String theGroup) {
        if (!isGroupsSet())
            this.groups = Lists.newArrayList();
        this.groups.add(theGroup);
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", fullName = '" + fullName + '\'' +
                ", userName = '" + userName + '\'' +
                ", userPassword = '" + userPassword + '\'' +
                ", mail = '" + mail + '\'' +
                ", groups = '" + groups + '\'' +
                ", inetUserStatus = '" + inetUserStatus + '\'' +
                '}';
    }
}
