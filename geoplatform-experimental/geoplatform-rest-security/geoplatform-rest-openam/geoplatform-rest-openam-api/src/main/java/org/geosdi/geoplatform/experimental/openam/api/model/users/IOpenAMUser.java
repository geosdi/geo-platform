package org.geosdi.geoplatform.experimental.openam.api.model.users;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMUser extends Serializable {

    /**
     * @return {@link String}
     */
    String getFirstName();

    /**
     * @param theFirstName
     */
    void setFirstName(String theFirstName);

    /**
     * @return {@link String}
     */
    String getLastName();

    /**
     * @param theLastName
     */
    void setLastName(String theLastName);

    /**
     * @return {@link String}
     */
    String getFullName();

    /**
     * @param theFullName
     */
    void setFullName(String theFullName);

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
    String getUserPassword();

    /**
     * @param theUserPassword
     */
    void setUserPassword(String theUserPassword);

    /**
     * @return {@link String}
     */
    String getMail();

    /**
     * @param theMail
     */
    void setMail(String theMail);

    /**
     * @return {@link List<String>}
     */
    List<String> getGroups();

    /**
     * @param theIsMemberOf
     */
    void setGroups(List<String> theIsMemberOf);

    /**
     * @return {@link Boolean}
     */
    Boolean isGroupsSet();

    /**
     * @param theGroup
     */
    void addGroup(String theGroup);

    /**
     * @return {@link List<String>}
     */
    List<String> getInetUserStatus();

    /**
     * @param theInetUserStatus
     */
    void setInetUserStatus(List<String> theInetUserStatus);
}
