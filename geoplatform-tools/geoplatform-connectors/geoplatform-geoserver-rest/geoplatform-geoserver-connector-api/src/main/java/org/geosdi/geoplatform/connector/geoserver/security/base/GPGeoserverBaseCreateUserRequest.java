/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.security.base;

import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.security.user.GPGeoserverUserBody;
import org.geosdi.geoplatform.connector.geoserver.request.security.users.base.GeoserverBaseCreateUserRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPostConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_XML;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoserverBaseCreateUserRequest<R extends GeoserverBaseCreateUserRequest> extends GPJsonPostConnectorRequest<Boolean, R> implements GeoserverBaseCreateUserRequest<R> {

    protected final ThreadLocal<GPGeoserverUserBody> user = withInitial(() -> null);

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    protected GPGeoserverBaseCreateUserRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
    }

    /**
     * @param theUser
     * @return {@link R}
     */
    @Override
    public R withBody(@Nonnull(when = NEVER) GPGeoserverUserBody theUser) {
        this.user.set(theUser);
        return self();
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected final Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPGeoserverUserBody createUser = this.user.get();
        checkArgument(createUser != null, "The Parameter body must not be null.");
        String createUserBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(createUser);
        logger.debug("#############################USER_BODY : \n{}\n", createUserBodyString);
        return new StringEntity(createUserBodyString, APPLICATION_XML);
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 401:
                throw new UnauthorizedException();
            case 404:
                throw new IllegalStateException("The User with userName : " + this.user.get().getUserName() + " already exist");
            case 405:
                throw new IllegalStateException("Method not allowed");
        }
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}