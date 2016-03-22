package org.geosdi.geoplatform.experimental.openam.api.model.validate;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMValidateToken extends Serializable {

    /**
     * @return {@link Boolean}
     */
    boolean isValid();

    /**
     * @param value
     */
    void setValid(boolean value);

    /**
     * @return {@link String}
     */
    String getUid();

    /**
     * @param uid
     */
    void setUid(String uid);

    /**
     * @return {@link String}
     */
    String getRealm();

    /**
     * @param theRealm
     */
    void setRealm(String theRealm);
}
