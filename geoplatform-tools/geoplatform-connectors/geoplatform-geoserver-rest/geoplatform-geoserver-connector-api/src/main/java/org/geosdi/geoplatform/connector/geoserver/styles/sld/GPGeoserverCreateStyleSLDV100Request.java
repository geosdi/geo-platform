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
package org.geosdi.geoplatform.connector.geoserver.styles.sld;

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverBooleanQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverStringQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GeoserverRXQueryParamConsumer;
import org.geosdi.geoplatform.connector.geoserver.styles.base.GPGeoserverBaseCreateStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_XML;
import static org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension.SLD;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
class GPGeoserverCreateStyleSLDV100Request extends GPGeoserverBaseCreateStyleRequest<StyledLayerDescriptor, GeoserverCreateStyleSLDV100Request> implements GeoserverCreateStyleSLDV100Request {

    private final ThreadLocal<GPGeoserverStringQueryParam> style;
    private final ThreadLocal<GPGeoserverBooleanQueryParam> raw;
    private final ThreadLocal<String> stringStyleBody;

    /**
     * @param theServerConnector
     */
    GPGeoserverCreateStyleSLDV100Request(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_JAXB_XML_SUPPORT);
        this.style = withInitial(() -> null);
        this.raw = withInitial(() -> null);
        this.stringStyleBody = withInitial(() -> null);
    }

    /**
     * @param theStyleName
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    @Override
    public GeoserverCreateStyleSLDV100Request withStyleName(@Nonnull(when = NEVER) String theStyleName) {
        this.style.set(new GPGeoserverStringQueryParam("name", theStyleName));
        return self();
    }

    /**
     * @param theStringStyleBody
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    @Override
    public GeoserverCreateStyleSLDV100Request withStringStyleBody(@Nonnull(when = NEVER) String theStringStyleBody) {
        this.stringStyleBody.set(theStringStyleBody);
        return self();
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
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    @Override
    public GeoserverCreateStyleSLDV100Request withRaw(@Nullable Boolean theRaw) {
        this.raw.set(new GPGeoserverBooleanQueryParam("raw", theRaw));
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = super.createUriPath();
        URIBuilder uriBuilder = new URIBuilder(baseURI);
        Consumer<ThreadLocal> consumer = new GeoserverRXQueryParamConsumer(uriBuilder);
        fromArray(this.raw, this.style)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .filter(c-> c.get() != null)
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        checkArgument(this.stringStyleBody.get() != null  || this.styleBody.get() != null, "The style body must not be null");
        String geoserverStyleBodyString = this.stringStyleBody.get() != null ? this.stringStyleBody.get() : jacksonSupport.getDefaultMapper().writeValueAsString(this.styleBody.get());
        logger.debug("#############################STYLE_BODY : \n{}\n", geoserverStyleBodyString);
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