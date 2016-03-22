package org.geosdi.geoplatform.experimental.openam.api.model.serverinfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOpenAMServerInfo extends Serializable {

    /**
     * @return {@link List<String>}
     */
    List<String> getDomains();

    /**
     * @param theDomains
     */
    void setDomains(List<String> theDomains);

    /**
     * @return {@link List<String>}
     */
    List<String> getProtectedUserAttributes();

    /**
     * @param theProtectedUserAttributes
     */
    void setProtectedUserAttributes(List<String> theProtectedUserAttributes);

    /**
     * @return {@link String}
     */
    String getCookieName();

    /**
     * @param theCookieName
     */
    void setCookieName(String theCookieName);

    /**
     * @return {@link Boolean}
     */
    Boolean isSecureCookie();

    /**
     * @param theSecureCookie
     */
    void setSecureCookie(Boolean theSecureCookie);

    /**
     * @return
     */
    Boolean isForgotPassword();

    /**
     * @param theForgotPassword
     */
    void setForgotPassword(Boolean theForgotPassword);

    /**
     * @return {@link Boolean}
     */
    Boolean isForgotUsername();

    /**
     * @param theForgotUsername
     */
    void setForgotUsername(Boolean theForgotUsername);

    /**
     * @return {@link Boolean}
     */
    Boolean isKbaEnabled();

    /**
     * @param theKbaEnabled
     */
    void setKbaEnabled(Boolean theKbaEnabled);

    /**
     * @return {@link Boolean}
     */
    Boolean isSelfRegistration();

    /**
     * @param theSelfRegistration
     */
    void setSelfRegistration(Boolean theSelfRegistration);

    /**
     * @return {@link String}
     */
    String getLang();

    /**
     * @param theLang
     */
    void setLang(String theLang);

    /**
     * @return {@link String}
     */
    String isSuccessfulUserRegistrationDestination();

    /**
     * @param theSuccessfulUserRegistrationDestination
     */
    void setSuccessfulUserRegistrationDestination(String theSuccessfulUserRegistrationDestination);

    /**
     * @return {@link List<OpenAMSocialImplementation>}
     */
    List<OpenAMSocialImplementation> getSocialImplementations();

    /**
     * @param theSocialImplementations
     */
    void setSocialImplementations(List<OpenAMSocialImplementation> theSocialImplementations);

    /**
     * @return {@link Boolean}
     */
    Boolean isReferralsEnabled();

    /**
     * @param theReferralsEnabled
     */
    void setReferralsEnabled(Boolean theReferralsEnabled);

    /**
     * @return {@link String}
     */
    String getRealm();

    /**
     * @param theRealm
     */
    void setRealm(String theRealm);

    /**
     * @return {@link Boolean}
     */
    Boolean isXuiUserSessionValidationEnabled();

    /**
     * @param theXuiUserSessionValidationEnabled
     */
    void setXuiUserSessionValidationEnabled(Boolean theXuiUserSessionValidationEnabled);

    /**
     * @return {@link String}
     */
    String getFQDN();

    /**
     * @param theFQDN
     */
    void setFQDN(String theFQDN);
}
