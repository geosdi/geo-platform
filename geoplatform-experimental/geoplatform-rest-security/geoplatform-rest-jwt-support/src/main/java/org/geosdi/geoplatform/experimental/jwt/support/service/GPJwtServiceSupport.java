/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.jwt.support.service;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.jwt.support.spring.configuration.IGPJwtConfiguration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static io.jsonwebtoken.Jwts.ZIP.GZIP;
import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parser;
import static javax.annotation.meta.When.NEVER;
import static org.joda.time.DateTime.now;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@ToString
class GPJwtServiceSupport implements IGPJwtServiceSupport {

    private static final long serialVersionUID = 6730444116638398822L;
    //
    @Getter
    private final IGPJwtConfiguration jwtConfiguration;

    /**
     * @param theJwtConfiguration
     */
    GPJwtServiceSupport(@Nonnull(when = NEVER) IGPJwtConfiguration theJwtConfiguration) {
        checkArgument(theJwtConfiguration != null, "The Parameter jwtConfiguration must not be null.");
        this.jwtConfiguration = theJwtConfiguration;
    }

    /**
     * @param theToken
     * @return {@link Claims}
     * @throws Exception
     */
    @Override
    public Claims extractAllClaims(@Nonnull(when = NEVER) String theToken) throws Exception {
        checkArgument((theToken != null) && !(theToken.trim().isEmpty()), "The Parameter token must not be null or an empty string");
        return parser()
                .verifyWith(this.jwtConfiguration.getSecretKey())
                .build()
                .parseSignedClaims(theToken)
                .getPayload();
    }

    /**
     * @param theToken
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public boolean isTokenExpired(@Nonnull(when = NEVER) String theToken) throws Exception {
        return this.extractClaim(theToken, Claims::getExpiration).before(now().toDate());
    }

    /**
     * @param theToken
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String extractUsername(@Nonnull(when = NEVER) String theToken) throws Exception {
        return this.extractClaim(theToken, Claims::getSubject);
    }

    /**
     * @param theUsername
     * @param theExtraClaims
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String generateToken(@Nonnull(when = NEVER) String theUsername, @Nullable Map<String, Object> theExtraClaims) throws Exception {
        checkArgument((theUsername != null) && !(theUsername.trim().isEmpty()), "The Parameter username must not be null or an empty string");
        return builder()
                .claims(theExtraClaims != null ? theExtraClaims : Map.of())
                .subject(theUsername)
                .issuedAt(now().toDate())
                .expiration(now().plus(this.jwtConfiguration.getExpiration()).toDate())
                .signWith(this.jwtConfiguration.getSecretKey())
                .compressWith(GZIP)
                .compact();
    }

    /**
     * @param theUsername
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String generateRefreshToken(@Nonnull(when = NEVER) String theUsername) throws Exception {
        checkArgument((theUsername != null) && !(theUsername.trim().isEmpty()), "The Parameter username must not be null or an empty string");
        return builder()
                .subject(theUsername)
                .issuedAt(now().toDate())
                .expiration(now().plus(this.jwtConfiguration.getRefreshTokenExpiration()).toDate())
                .signWith(this.jwtConfiguration.getSecretKey())
                .compressWith(GZIP)
                .compact();
    }
}