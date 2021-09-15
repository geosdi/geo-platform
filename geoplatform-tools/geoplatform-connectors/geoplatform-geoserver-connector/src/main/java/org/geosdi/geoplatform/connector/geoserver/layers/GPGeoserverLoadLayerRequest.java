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
package org.geosdi.geoplatform.connector.geoserver.layers;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadLayerRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.UnauthorizedException;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverLoadLayerRequest extends GPJsonGetConnectorRequest<GeoserverLayer> implements GeoserverLoadLayerRequest {

    private final ThreadLocal<String> name;
    private final ThreadLocal<Boolean> exist = withInitial(() -> null);
    private final ThreadLocal<GeoserverLayer> response = withInitial(() -> null);

    /**
     * @param server
     * @param theJacksonSupport
     */
    GPGeoserverLoadLayerRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
        this.name = withInitial(() -> null);
    }

    /**
     * @param theName
     */
    public GeoserverLoadLayerRequest withName(@Nonnull(when = NEVER) String theName) {
        this.name.set(theName);
        this.exist.set(null);
        this.response.set(null);
        return this;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean existLayer() throws Exception {
        return (this.exist.get() != null ? this.exist.get() : this.getResponse() != null);
    }

    /**
     * @return {@link GeoserverLayer}
     * @throws Exception
     */
    @Override
    public GeoserverLayer getResponse() throws Exception {
        return  (this.response.get() != null ? this.response.get() : super.getResponse());
    }

    /**
     * @param statusCode
     * @throws Exception
     */
    @Override
    protected void checkHttpResponseStatus(int statusCode) throws Exception {
        switch (statusCode) {
            case 401:
                throw new UnauthorizedException();
        }
    }

    /**
     * @param reader
     * @return {@link GeoserverLayer}
     * @throws Exception
     */
    @Override
    protected GeoserverLayer readInternal(BufferedReader reader) throws Exception {
        try {
            if (this.response.get() == null) {
                this.response.set(super.readInternal(reader));
            }
            this.exist.set(TRUE);
            return this.response.get();
        } catch (Exception ex) {
            this.exist.set(FALSE);
            return null;
        }
    }



    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String layerName = this.name.get();
        checkArgument(((layerName != null) && !(layerName.trim().isEmpty())), "The Parameter Name must not be null or an Empty String.");
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("layers/").concat(layerName) : baseURI.concat("/layers/").concat(layerName)));
    }

    /**
     * @return {@link Class<GeoserverLayer>}
     */
    @Override
    protected Class<GeoserverLayer> forClass() {
        return GeoserverLayer.class;
    }
}