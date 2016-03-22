package org.geosdi.geoplatform.experimental.openam.api.model.serverinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMServerInfo implements IOpenAMServerInfo {

    private static final long serialVersionUID = 3916553903580159346L;
    //
    private List<String> domains;
    private List<String> protectedUserAttributes;
    private String cookieName;
    private Boolean secureCookie;
    private Boolean forgotPassword;
    private Boolean forgotUsername;
    private Boolean kbaEnabled;
    private Boolean selfRegistration;
    private String lang;
    private String successfulUserRegistrationDestination;
    private List<OpenAMSocialImplementation> socialImplementations;
    private Boolean referralsEnabled;
    private String realm;
    private Boolean xuiUserSessionValidationEnabled;
    @JsonProperty(value = "FQDN")
    private String fqnd;

    public OpenAMServerInfo() {
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getDomains() {
        return this.domains;
    }

    /**
     * @param theDomains
     */
    @Override
    public void setDomains(List<String> theDomains) {
        this.domains = theDomains;
    }

    /**
     * @return {@link List <String>}
     */
    @Override
    public List<String> getProtectedUserAttributes() {
        return this.protectedUserAttributes;
    }

    /**
     * @param theProtectedUserAttributes
     */
    @Override
    public void setProtectedUserAttributes(List<String> theProtectedUserAttributes) {
        this.protectedUserAttributes = theProtectedUserAttributes;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getCookieName() {
        return this.cookieName;
    }

    /**
     * @param theCookieName
     */
    @Override
    public void setCookieName(String theCookieName) {
        this.cookieName = theCookieName;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSecureCookie() {
        return this.secureCookie;
    }

    /**
     * @param theSecureCookie
     */
    @Override
    public void setSecureCookie(Boolean theSecureCookie) {
        this.secureCookie = theSecureCookie;
    }

    /**
     * @return
     */
    @Override
    public Boolean isForgotPassword() {
        return this.forgotPassword;
    }

    /**
     * @param theForgotPassword
     */
    @Override
    public void setForgotPassword(Boolean theForgotPassword) {
        this.forgotPassword = theForgotPassword;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isForgotUsername() {
        return this.forgotUsername;
    }

    /**
     * @param theForgotUsername
     */
    @Override
    public void setForgotUsername(Boolean theForgotUsername) {
        this.forgotUsername = theForgotUsername;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isKbaEnabled() {
        return this.kbaEnabled;
    }

    /**
     * @param theKbaEnabled
     */
    @Override
    public void setKbaEnabled(Boolean theKbaEnabled) {
        this.kbaEnabled = theKbaEnabled;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSelfRegistration() {
        return this.selfRegistration;
    }

    /**
     * @param theSelfRegistration
     */
    @Override
    public void setSelfRegistration(Boolean theSelfRegistration) {
        this.selfRegistration = theSelfRegistration;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getLang() {
        return this.lang;
    }

    /**
     * @param theLang
     */
    @Override
    public void setLang(String theLang) {
        this.lang = theLang;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public String isSuccessfulUserRegistrationDestination() {
        return this.successfulUserRegistrationDestination;
    }

    /**
     * @param theSuccessfulUserRegistrationDestination
     */
    @Override
    public void setSuccessfulUserRegistrationDestination(String theSuccessfulUserRegistrationDestination) {
        this.successfulUserRegistrationDestination = theSuccessfulUserRegistrationDestination;
    }

    /**
     * @return {@link List < OpenAMSocialImplementation >}
     */
    @Override
    public List<OpenAMSocialImplementation> getSocialImplementations() {
        return this.socialImplementations;
    }

    /**
     * @param theSocialImplementations
     */
    @Override
    public void setSocialImplementations(List<OpenAMSocialImplementation> theSocialImplementations) {
        this.socialImplementations = theSocialImplementations;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isReferralsEnabled() {
        return this.referralsEnabled;
    }

    /**
     * @param theReferralsEnabled
     */
    @Override
    public void setReferralsEnabled(Boolean theReferralsEnabled) {
        this.referralsEnabled = theReferralsEnabled;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getRealm() {
        return this.realm;
    }

    /**
     * @param theRealm
     */
    @Override
    public void setRealm(String theRealm) {
        this.realm = theRealm;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isXuiUserSessionValidationEnabled() {
        return this.xuiUserSessionValidationEnabled;
    }

    /**
     * @param theXuiUserSessionValidationEnabled
     */
    @Override
    public void setXuiUserSessionValidationEnabled(Boolean theXuiUserSessionValidationEnabled) {
        this.xuiUserSessionValidationEnabled = theXuiUserSessionValidationEnabled;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getFQDN() {
        return this.fqnd;
    }

    /**
     * @param theFQDN
     */
    @Override
    public void setFQDN(String theFQDN) {
        this.fqnd = theFQDN;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " domains = " + domains +
                ", protectedUserAttributes = " + protectedUserAttributes +
                ", cookieName = '" + cookieName + '\'' +
                ", secureCookie = " + secureCookie +
                ", forgotPassword = " + forgotPassword +
                ", forgotUsername = " + forgotUsername +
                ", kbaEnabled = " + kbaEnabled +
                ", selfRegistration = " + selfRegistration +
                ", lang = '" + lang + '\'' +
                ", successfulUserRegistrationDestination = '" + successfulUserRegistrationDestination + '\'' +
                ", socialImplementations = " + socialImplementations +
                ", referralsEnabled = " + referralsEnabled +
                ", realm = '" + realm + '\'' +
                ", xuiUserSessionValidationEnabled = " + xuiUserSessionValidationEnabled +
                ", fqnd = '" + fqnd + '\'' +
                '}';
    }
}
