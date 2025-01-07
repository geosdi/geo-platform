/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.fasterxml.jackson.datatype.joda.JodaModule;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.jwt.support.claims.GPJwtRoleClaim;
import org.geosdi.geoplatform.experimental.jwt.support.spring.configuration.IGPJwtConfiguration;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SignatureException;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static io.jsonwebtoken.Jwts.ZIP.GZIP;
import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parser;
import static java.lang.Boolean.TRUE;
import static java.util.Map.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAKARTA;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
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
    private static final GPJacksonSupport jacksonSupport = new GPJacksonSupport(JAKARTA, UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL)
            .registerModule(new JodaModule())
            .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE);
    private static final JacksonSerializer jacksonSerializer = new JacksonSerializer<>(jacksonSupport.getDefaultMapper());
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
        try {
            return this.extractClaim(theToken, Claims::getExpiration).before(now().toDate());
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            return TRUE;
        }
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
                .json(jacksonSerializer)
                .claims(theExtraClaims != null ? theExtraClaims : of())
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
                .json(jacksonSerializer)
                .subject(theUsername)
                .issuedAt(now().toDate())
                .expiration(now().plus(this.jwtConfiguration.getRefreshTokenExpiration()).toDate())
                .signWith(this.jwtConfiguration.getSecretKey())
                .compressWith(GZIP)
                .compact();
    }

    /**
     * @param theToken
     * @param theClaimType
     * @param theClaimKey
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public <T> T extractClaim(@Nonnull(when = NEVER) String theToken, @Nonnull(when = NEVER) Class<T> theClaimType, @Nonnull(when = NEVER) String theClaimKey) throws Exception {
        checkArgument((theToken != null) && !(theToken.trim().isEmpty()), "The Parameter token must not be null or an empty string");
        checkArgument(theClaimType != null, "The Parameter claimType must not be null.");
        checkArgument((theClaimKey != null) && !(theClaimKey.trim().isEmpty()), "The Parameter claimKey must not be null or an empty string");
        return parser()
                .json(new JacksonDeserializer<>(of(theClaimKey, theClaimType)))
                .verifyWith(this.jwtConfiguration.getSecretKey())
                .build()
                .parseSignedClaims(theToken)
                .getPayload()
                .get(theClaimKey, theClaimType);
    }

    /**
     * @param theToken
     * @return {@link GPJwtRoleClaim}
     * @throws Exception
     */
    @Override
    public GPJwtRoleClaim extractJwtRoleClain(@Nonnull(when = NEVER) String theToken) throws Exception {
        return this.extractClaim(theToken, GPJwtRoleClaim.class, this.jwtConfiguration.getJwtRoleClaimKey());
    }
}