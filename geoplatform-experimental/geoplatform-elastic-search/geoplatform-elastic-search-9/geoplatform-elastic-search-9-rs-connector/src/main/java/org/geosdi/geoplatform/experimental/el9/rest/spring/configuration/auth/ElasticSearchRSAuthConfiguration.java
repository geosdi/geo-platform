/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el9.rest.spring.configuration.auth;

import lombok.Getter;
import net.jcip.annotations.Immutable;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;

import javax.annotation.Nullable;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.hc.core5.http.HttpHeaders.AUTHORIZATION;

/**
 * Implementazione di {@link GPElasticSearchRSAuthConfiguration}.
 * <p>L'header Authorization va con ogni richiesta, senza aspettare la risposta 401 di ElasticSearch: il client
 * Rest5 consiglia così e si risparmia un giro.</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Getter
class ElasticSearchRSAuthConfiguration implements GPElasticSearchRSAuthConfiguration {

    private static final long serialVersionUID = -3437364040012584417L;
    //
    private final String userName;
    private final String password;

    /**
     * @param theUserName l'utente; null vuol dire nessuna autenticazione
     * @param thePassword la password; null vuol dire nessuna autenticazione
     */
    ElasticSearchRSAuthConfiguration(@Nullable String theUserName, @Nullable String thePassword) {
        this.userName = theUserName;
        this.password = thePassword;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetAuth() {
        return ((this.userName != null) && !(this.userName.trim().isEmpty())) && ((this.password != null) && !(this.password.trim().isEmpty()));
    }

    /**
     * @return {@link Header}
     */
    @Override
    public Header toAuthorizationHeader() {
        if (!isSetAuth()) {
            return null;
        }
        String credentials = Base64.getEncoder().encodeToString((this.userName + ":" + this.password).getBytes(UTF_8));
        return new BasicHeader(AUTHORIZATION, "Basic " + credentials);
    }

    /**
     * La password non va nei log.
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " {userName = " + this.userName + ", password = " + ((this.password != null) ? "[nascosta]" : null) + "}";
    }
}
