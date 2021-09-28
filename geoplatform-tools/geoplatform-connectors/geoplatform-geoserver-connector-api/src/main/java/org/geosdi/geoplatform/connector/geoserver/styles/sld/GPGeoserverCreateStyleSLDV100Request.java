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
package org.geosdi.geoplatform.connector.geoserver.styles.sld;

import net.jcip.annotations.ThreadSafe;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.styles.IGPGeoserverCreareStyleResponse;
import org.geosdi.geoplatform.connector.geoserver.styles.base.GPGeoserverBaseCreateStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_XML;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverCreateStyleSLDV100Request extends GPGeoserverBaseCreateStyleRequest<StyledLayerDescriptor, GeoserverCreateStyleSLDV100Request> implements GeoserverCreateStyleSLDV100Request {

    private final ThreadLocal<String> style;

    /**
     * @param theServerConnector
     */
    GPGeoserverCreateStyleSLDV100Request(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_JAXB_XML_SUPPORT);
        this.style = withInitial(() -> null);
    }

    /**
     * @param theStyleName
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    @Override
    public GeoserverCreateStyleSLDV100Request withStyleName(@Nonnull(when = NEVER) String theStyleName) {
        this.style.set(theStyleName);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String styleName = this.style.get();
        checkArgument((styleName != null) && !(styleName.trim().isEmpty()), "The Parameter styleName must not be null or an empty string");
        String baseURI = super.createUriPath();
        return new URIBuilder(baseURI).addParameter("name", styleName).build().toString();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        StyledLayerDescriptor geoserverStyleBody = this.styleBody.get();
        checkArgument(geoserverStyleBody != null, "The Parameter styleBody must not be null.");
        String geoserverStyleBodyString = jacksonSupport.getDefaultMapper().writeValueAsString(geoserverStyleBody);
        logger.debug("#############################STYLE_BODY : \n{}\n", geoserverStyleBodyString);
        return new StringEntity(geoserverStyleBodyString, APPLICATION_XML);
    }

    /**
     * @param httpMethod
     */
    @Override
    protected void addHeaderParams(HttpUriRequest httpMethod) {
        httpMethod.addHeader("Content-Type", "application/vnd.ogc.sld+xml");
    }
}
