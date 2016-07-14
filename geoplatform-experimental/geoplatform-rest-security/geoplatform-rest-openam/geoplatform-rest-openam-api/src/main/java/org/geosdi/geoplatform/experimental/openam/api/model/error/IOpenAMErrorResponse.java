package org.geosdi.geoplatform.experimental.openam.api.model.error;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMErrorResponse extends Serializable {

    /**
     * @return {@link String}
     */
    String getCode();

    /**
     * @param theCode
     */
    void setCode(String theCode);

    /**
     * @return {@link String}
     */
    String getReason();

    /**
     * @param theReason
     */
    void setReason(String theReason);

    /**
     * @return {@link String}
     */
    String getMessage();

    /**
     * @param theMessage
     */
    void setMessage(String theMessage);
}
