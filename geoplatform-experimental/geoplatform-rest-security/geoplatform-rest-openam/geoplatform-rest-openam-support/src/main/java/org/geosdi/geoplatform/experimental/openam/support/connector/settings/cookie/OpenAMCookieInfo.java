package org.geosdi.geoplatform.experimental.openam.support.connector.settings.cookie;

import org.geosdi.geoplatform.experimental.openam.api.connector.cookie.IOpenAMCookieInfo;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMCookieInfo implements IOpenAMCookieInfo {

    private static final long serialVersionUID = 7264022990445717519L;
    //
    private String openAMCookie;

    public OpenAMCookieInfo() {
    }

    public OpenAMCookieInfo(String theOpenAMCookie) {
        this.openAMCookie = theOpenAMCookie;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getOpenAMCookie() {
        return this.openAMCookie;
    }

    /**
     * @param theOpenAMCookie
     */
    @Override
    public void setOpenAMCookie(String theOpenAMCookie) {
        this.openAMCookie = theOpenAMCookie;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " openAMCookie = '" + openAMCookie + '\'' +
                '}';
    }
}
