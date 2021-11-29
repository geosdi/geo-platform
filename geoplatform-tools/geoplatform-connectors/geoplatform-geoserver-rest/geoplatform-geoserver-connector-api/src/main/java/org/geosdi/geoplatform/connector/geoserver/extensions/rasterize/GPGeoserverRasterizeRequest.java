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
package org.geosdi.geoplatform.connector.geoserver.extensions.rasterize;

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.hc.core5.net.URIBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.extension.rasterize.GeoserverRamp;
import org.geosdi.geoplatform.connector.geoserver.model.extension.rasterize.GeoserverRasterizeType;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverStringQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GeoserverRXQueryParamConsumer;
import org.geosdi.geoplatform.connector.geoserver.request.extension.rasterize.GeoserverRasterizeRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@ThreadSafe
class GPGeoserverRasterizeRequest extends GPJsonGetConnectorRequest<StyledLayerDescriptor, GeoserverRasterizeRequest> implements GeoserverRasterizeRequest {

    private final ThreadLocal<String> rasterName;
    private final ThreadLocal<GPGeoserverQueryParam> geoserverRamp;
    private final ThreadLocal<GPGeoserverStringQueryParam> min;
    private final ThreadLocal<GPGeoserverStringQueryParam> max;
    private final ThreadLocal<GPGeoserverStringQueryParam> classes;
    private final ThreadLocal<GPGeoserverStringQueryParam> digits;
    private final ThreadLocal<GPGeoserverQueryParam> type;
    private final ThreadLocal<GPGeoserverStringQueryParam> startColor;
    private final ThreadLocal<GPGeoserverStringQueryParam> endColor;
    private final ThreadLocal<GPGeoserverStringQueryParam> midColor;

    GPGeoserverRasterizeRequest(@Nonnull(when = NEVER) GPServerConnector server) {
        super(server, JACKSON_JAXB_XML_SUPPORT);
        this.rasterName = ThreadLocal.withInitial(() -> null);
        this.geoserverRamp = ThreadLocal.withInitial(() -> null);
        this.min = ThreadLocal.withInitial(() -> null);
        this.max = ThreadLocal.withInitial(() -> null);
        this.classes = ThreadLocal.withInitial(() -> null);
        this.digits = ThreadLocal.withInitial(() -> null);
        this.type = ThreadLocal.withInitial(() -> null);
        this.startColor = ThreadLocal.withInitial(() -> null);
        this.endColor = ThreadLocal.withInitial(() -> null);
        this.midColor = ThreadLocal.withInitial(() -> null);
    }

    /**
     * @param theRasterName
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withRasterName(@Nonnull(when = NEVER) String theRasterName) {
        this.rasterName.set(theRasterName);
        return self();
    }

    /**
     * @param theMin
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withMin(@Nonnull(when = NEVER) Double theMin) {
        this.min.set(new GPGeoserverStringQueryParam("min", theMin != null ? theMin.toString() : "0.0"));
        return self();
    }

    /**
     * @param theMax
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withMax(@Nonnull(when = NEVER) Double theMax) {
        this.max.set(new GPGeoserverStringQueryParam("max", theMax != null ? theMax.toString() : "100.0"));
        return self();
    }

    /**
     * @param theClasses
     * @return
     */
    @Override
    public GeoserverRasterizeRequest withClasses(@Nonnull(when = NEVER) Integer theClasses) {
        this.classes.set(new GPGeoserverStringQueryParam("classes", theClasses != null ? theClasses.toString() : "100"));
        return self();
    }

    /**
     * @param theDigits
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withDigits(@Nonnull(when = NEVER) Integer theDigits) {
        this.digits.set(new GPGeoserverStringQueryParam("digits", theDigits != null ? theDigits.toString() : "5"));
        return self();
    }

    /**
     * @param theType
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withType(@Nonnull(when = NEVER) GeoserverRasterizeType theType) {
        this.type.set(theType != null ? theType : new GPGeoserverStringQueryParam("type", GeoserverRasterizeType.RAMP.name()));
        return self();
    }

    /**
     * @param theGeoserverRamp
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withGeoserverRamp(@Nonnull(when = NEVER) GeoserverRamp theGeoserverRamp) {
        this.geoserverRamp.set(theGeoserverRamp != null ? theGeoserverRamp : new GPGeoserverStringQueryParam("ramp", GeoserverRamp.red.name()));
        return self();
    }

    /**
     * @param theStartColor
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withStartColor(@Nonnull(when = NEVER) String theStartColor) {
        this.startColor.set(new GPGeoserverStringQueryParam("startColor", theStartColor));
        return self();
    }

    /**
     * @param theEndColor
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withEndColor(@Nonnull(when = NEVER) String theEndColor) {
        this.endColor.set(new GPGeoserverStringQueryParam("endColor", theEndColor));
        return self();
    }

    /**
     * @param theMidColor
     * @return {@link GeoserverRasterizeRequest}
     */
    @Override
    public GeoserverRasterizeRequest withMidColor(@Nonnull(when = NEVER) String theMidColor) {
        this.midColor.set(new GPGeoserverStringQueryParam("midColor", theMidColor));
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        String rasterName = this.rasterName.get();
        checkArgument((rasterName != null) && !(rasterName.trim().isEmpty()), "The Parameter rasterName must not be null or an empty string.");
        String path =  (baseURI.endsWith("/") ? baseURI.concat("sld/").concat(rasterName).concat("/rasterize.sld") : baseURI.concat("/sld/").concat(rasterName).concat("/rasterize.sld"));
        URIBuilder uriBuilder = new URIBuilder(path);
        Consumer<ThreadLocal> consumer = new GeoserverRXQueryParamConsumer(uriBuilder);
        fromArray(this.min, this.max, this.classes, this.digits, this.type, this.geoserverRamp, this.startColor, this.endColor, this.midColor)
                .doOnComplete(() -> logger.info("##################Uri Builder DONE.\n"))
                .filter(c-> c.get() != null)
                .subscribe(consumer, Throwable::printStackTrace);
        return uriBuilder.build().toString();
    }

    /**
     * @return {@link Class<StyledLayerDescriptor>}
     */
    @Override
    protected Class<StyledLayerDescriptor> forClass() {
        return StyledLayerDescriptor.class;
    }
}