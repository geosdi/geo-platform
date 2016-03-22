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
