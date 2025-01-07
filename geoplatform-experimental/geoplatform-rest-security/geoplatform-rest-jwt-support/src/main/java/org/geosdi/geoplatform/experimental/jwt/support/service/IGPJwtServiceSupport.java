/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.jwt.support.service;

import io.jsonwebtoken.Claims;
import org.geosdi.geoplatform.experimental.jwt.support.claims.GPJwtRoleClaim;
import org.geosdi.geoplatform.experimental.jwt.support.spring.configuration.IGPJwtConfiguration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Map;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJwtServiceSupport extends Serializable {

    /**
     * @param theToken
     * @return {@link Claims}
     * @throws Exception
     */
    Claims extractAllClaims(@Nonnull(when = NEVER) String theToken) throws Exception;

    /**
     * @param theToken
     * @return {@link Boolean}
     * @throws Exception
     */
    boolean isTokenExpired(@Nonnull(when = NEVER) String theToken) throws Exception;

    /**
     * @param theToken
     * @return {@link String}
     * @throws Exception
     */
    String extractUsername(@Nonnull(when = NEVER) String theToken) throws Exception;

    /**
     * @param theUsername
     * @return {@link String}
     * @throws Exception
     */
    default String generateToken(@Nonnull(when = NEVER) String theUsername) throws Exception {
        return this.generateToken(theUsername, Map.of());
    }

    /**
     * @param theUsername
     * @param theExtraClaims
     * @return {@link String}
     * @throws Exception
     */
    String generateToken(@Nonnull(when = NEVER) String theUsername, @Nullable Map<String, Object> theExtraClaims) throws Exception;

    /**
     * @param theUsername
     * @return {@link String}
     * @throws Exception
     */
    String generateRefreshToken(@Nonnull(when = NEVER) String theUsername) throws Exception;

    /**
     * @return {@link IGPJwtConfiguration}
     */
    IGPJwtConfiguration getJwtConfiguration();

    /**
     * @param theToken
     * @param theClaimType
     * @param theClaimKey
     * @param <T>
     * @return {@link T}
     * @throws Exception
     */
    <T> T extractClaim(@Nonnull(when = NEVER) String theToken, @Nonnull(when = NEVER) Class<T> theClaimType, @Nonnull(when = NEVER) String theClaimKey) throws Exception;

    /**
     * @param theToken
     * @return {@link GPJwtRoleClaim}
     * @throws Exception
     */
    GPJwtRoleClaim extractJwtRoleClain(@Nonnull(when = NEVER) String theToken) throws Exception;

    /**
     * @param theToken
     * @param theClaimFunction
     * @param <T>
     * @return {@link <></>}
     * @throws Exception
     */
     default <T> T extractClaim(@Nonnull(when = NEVER) String theToken, @Nonnull(when = NEVER) Function<Claims, T> theClaimFunction) throws Exception {
         checkArgument(theClaimFunction != null, "The Parameter claimFunction must not be null");
         return theClaimFunction.apply(extractAllClaims(theToken));
     }
}