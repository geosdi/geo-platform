package org.geosdi.geoplatform.experimental.openam.api.model.authenticate;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMLogout extends Serializable {

    /**
     * @return {@link String}
     */
    String getResult();

    /**
     * @param theResult
     */
    void setResult(String theResult);
}
