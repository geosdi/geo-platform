package org.geosdi.geoplatform.experimental.openam.api.model.delete;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMDeleteResponse extends Serializable {

    /**
     * @return {@link Boolean}
     */
    Boolean isSuccess();

    /**
     * @param theValue
     */
    void setSuccess(Boolean theValue);
}
