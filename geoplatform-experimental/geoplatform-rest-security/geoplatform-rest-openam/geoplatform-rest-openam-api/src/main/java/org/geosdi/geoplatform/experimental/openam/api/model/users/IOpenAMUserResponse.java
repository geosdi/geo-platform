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
