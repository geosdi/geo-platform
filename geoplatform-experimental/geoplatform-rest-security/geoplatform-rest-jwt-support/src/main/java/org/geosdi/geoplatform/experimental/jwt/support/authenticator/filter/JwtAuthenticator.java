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
package org.geosdi.geoplatform.experimental.jwt.support.authenticator.filter;

import org.geosdi.geoplatform.exception.NotAuthorizedFault;
import org.geosdi.geoplatform.experimental.jwt.support.annotation.GPJwtSupport;
import org.geosdi.geoplatform.experimental.jwt.support.claims.GPJwtRoleClaim;
import org.geosdi.geoplatform.experimental.jwt.support.service.IGPJwtServiceSupport;

import javax.annotation.Nonnull;
import javax.annotation.Priority;
import javax.annotation.Resource;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;
import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@GPJwtSupport
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticator extends BaseJwtAuthenticator {

    @Resource(name = "jwtServiceSupport")
    private IGPJwtServiceSupport jwtServiceSupport;
    @Context
    private ResourceInfo resourceInfo;

    public JwtAuthenticator() {
        processInjectionBasedOnCurrentContext(this);
    }

    /**
     * @param token
     * @throws Exception
     */
    @Override
    protected void validateToken(@Nonnull(when = NEVER) String token) throws Exception {
        checkArgument((token != null) && !(token.trim().isEmpty()), "The Parameter token must not be null or an empty string.");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@Validating token : {}\n", token);
        if (this.jwtServiceSupport.isTokenExpired(token)) {
            throw new NotAuthorizedFault("The Jwt Token is expired.");
        }
        String user = this.jwtServiceSupport.extractUsername(token);
        logger.trace("######################Trying to retrieve Role for User : {}\n", user);
        Method method = resourceInfo.getResourceMethod();
        GPJwtSupport jwtSecured = ((method.getAnnotation(GPJwtSupport.class) != null) ? method.getAnnotation(GPJwtSupport.class) : resourceInfo.getResourceClass().getAnnotation(GPJwtSupport.class));
        if (jwtSecured != null) {
            List<String> roles = asList(jwtSecured.roles());
            GPJwtRoleClaim roleClaim = this.jwtServiceSupport.extractJwtRoleClain(token);
            if (!(roles.isEmpty()) && (roleClaim == null)) {
                throw new NotAuthorizedFault("The Method is accesible only with a Role, but in the jwt token the jwtRoleClaim is not present.");
            }
            if (!(roles.isEmpty()) && (roleClaim != null) && !(roles.contains(roleClaim.getRole()))) {
                throw new NotAuthorizedFault("The Method is accesible only with a Role, but the Role specified in the jwtRoleClaim doesn't match any roles present on the Resource.");
            }
        }
    }
}