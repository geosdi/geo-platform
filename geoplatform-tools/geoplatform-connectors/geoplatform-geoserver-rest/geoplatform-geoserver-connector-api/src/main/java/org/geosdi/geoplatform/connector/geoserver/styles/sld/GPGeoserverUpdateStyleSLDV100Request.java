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
package org.geosdi.geoplatform.connector.geoserver.styles.sld;

import net.jcip.annotations.ThreadSafe;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.styles.base.GPGeoserverBaseUpdateStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.APPLICATION_XML;
import static org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension.SLD;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverUpdateStyleSLDV100Request extends GPGeoserverBaseUpdateStyleRequest<StyledLayerDescriptor, GeoserverUpdateStyleSLDV100Request> implements GeoserverUpdateStyleSLDV100Request {

    private final ThreadLocal<Boolean> raw;
    private final ThreadLocal<String> stringStyleBody;

    /**
     * @param theServerConnector
     */
    GPGeoserverUpdateStyleSLDV100Request(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_JAXB_XML_SUPPORT);
        this.raw = withInitial(() -> FALSE);
        this.stringStyleBody = withInitial(() -> null);
    }

    /**
     * <p>
     * When set to "true", will forgo parsing and encoding of the uploaded style content, and instead the style will be
     * streamed directly to the GeoServer configuration. Use this setting if the content and formatting of the style
     * is to be preserved exactly. May result in an invalid and unusable style if the payload is malformed.
     * Allowable values are {@link Boolean#TRUE} or {@link Boolean#FALSE} (default). Only used when uploading a style file.
     * </p>
     *
     * @param theRaw
     * @return {@link GeoserverUpdateStyleSLDV100Request}
     */
    @Override
    public GeoserverUpdateStyleSLDV100Request withRaw(@Nullable Boolean theRaw) {
        this.raw.set((theRaw != null) ? theRaw : FALSE);
        return self();
    }

    /**
     * @param theStringBody
     * @return {@link GeoserverUpdateStyleSLDV100Request}
     */
    @Override
    public GeoserverUpdateStyleSLDV100Request withStringStyleBody(@Nonnull(when = When.NEVER) String theStringBody) {
        this.stringStyleBody.set(theStringBody);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        return new URIBuilder(super.createUriPath())
                .addParameter("raw", this.raw.get().toString())
                .build().toString();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        checkArgument(this.stringStyleBody.get() != null  || this.styleBody.get() != null, "The style body must not be null");
        String geoserverStyleBodyString = this.stringStyleBody.get() != null ? this.stringStyleBody.get() : jacksonSupport.getDefaultMapper().writeValueAsString(this.styleBody.get());
        return new StringEntity(geoserverStyleBodyString, APPLICATION_XML);
    }

    /**
     * @param httpMethod
     */
    @Override
    protected void addHeaderParams(HttpUriRequest httpMethod) {
        httpMethod.addHeader("Content-Type", SLD.getContentType());
    }
}