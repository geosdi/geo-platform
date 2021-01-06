/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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
