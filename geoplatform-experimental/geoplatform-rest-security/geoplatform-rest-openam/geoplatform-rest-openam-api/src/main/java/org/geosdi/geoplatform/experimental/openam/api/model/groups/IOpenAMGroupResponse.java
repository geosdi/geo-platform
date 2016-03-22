package org.geosdi.geoplatform.experimental.openam.api.model.groups;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMGroupResponse extends Serializable {

    /**
     * @return {@link String}
     */
    String getGroupName();

    /**
     * @param theGroupName
     */
    void setGroupName(String theGroupName);

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
    List<String> getUniqueMember();

    /**
     * @param theUniqueMember
     */
    void setUniqueMember(List<String> theUniqueMember);

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
    List<String> getDn();

    /**
     * @param theDn
     */
    void setDn(List<String> theDn);

    /**
     * @return {@link List<String>}
     */
    List<String> getObjectClass();

    /**
     * @param theObjectClass
     */
    void setObjectClass(List<String> theObjectClass);

    /**
     * @return {@link List<String>}
     */
    List<String> getUniversalId();

    /**
     * @param theUniversalId
     */
    void setUniversalId(List<String> theUniversalId);
}
