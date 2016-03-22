package org.geosdi.geoplatform.experimental.openam.api.model.groups;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMGroup extends Serializable {

    /**
     * @return {@link String}
     */
    String getGroupName();

    /**
     * @param theGroupName
     */
    void setGroupName(String theGroupName);

    /**
     * @return {@link List<String>}
     */
    List<String> getUniqueMember();

    /**
     * @param theUniqueMember
     */
    void setUniqueMember(List<String> theUniqueMember);
}
