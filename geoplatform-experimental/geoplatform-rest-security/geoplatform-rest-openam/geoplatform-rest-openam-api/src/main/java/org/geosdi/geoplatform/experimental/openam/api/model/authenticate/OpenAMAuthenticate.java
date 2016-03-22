package org.geosdi.geoplatform.experimental.openam.api.model.authenticate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMAuthenticate implements IOpenAMAuthenticate {

    private static final long serialVersionUID = -6196195762266980245L;
    //
    private String tokenId;
    private String successUrl;

    public OpenAMAuthenticate() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getTokenId() {
        return this.tokenId;
    }

    /**
     * @param theTokenId
     */
    @Override
    public void setTokenId(String theTokenId) {
        this.tokenId = theTokenId;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getSuccessUrl() {
        return this.successUrl;
    }

    /**
     * @param theSuccessUrl
     */
    @Override
    public void setSuccessUrl(String theSuccessUrl) {
        this.successUrl = theSuccessUrl;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  tokenId = '" + tokenId + '\'' +
                ", successUrl = '" + successUrl + '\'' +
                '}';
    }
}
