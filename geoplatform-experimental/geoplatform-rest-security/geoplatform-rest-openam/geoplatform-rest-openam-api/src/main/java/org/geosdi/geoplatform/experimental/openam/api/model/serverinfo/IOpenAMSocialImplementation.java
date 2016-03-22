package org.geosdi.geoplatform.experimental.openam.api.model.serverinfo;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMSocialImplementation extends Serializable {

    /**
     * @return {@link String}
     */
    String getIconPath();

    /**
     * @param theIconPath
     */
    void setIconPath(String theIconPath);

    /**
     * @return {@link String}
     */
    String getAuthnChain();

    /**
     * @param theAuthnChain
     */
    void setAuthnChain(String theAuthnChain);

    /**
     * @return {@link String}
     */
    String getDisplayName();

    /**
     * @param theDisplayName
     */
    void setDisplayName(String theDisplayName);

    /**
     * @return {@link Boolean}
     */
    Boolean isValid();

    /**
     * @param theValid
     */
    void setValid(Boolean theValid);
}
