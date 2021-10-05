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

import io.reactivex.rxjava3.functions.Consumer;
import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GPGeoserverBooleanQueryParam;
import org.geosdi.geoplatform.connector.geoserver.model.uri.GeoserverRXQueryParamConsumer;
import org.geosdi.geoplatform.connector.geoserver.styles.base.GPGeoserverBaseUpdateStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.w3c.dom.Document;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.meta.When;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.rxjava3.core.Observable.fromArray;
import static java.lang.Boolean.FALSE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.create;
import static org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension.SLD;
import static org.geosdi.geoplatform.connector.geoserver.model.format.GPFormatExtension.SLD_1_1_0;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverUpdateStyleWithFileSLDRequest extends GPGeoserverBaseUpdateStyleRequest<File, GeoserverUpdateStyleWithFileSLDRequest> implements GeoserverUpdateStyleWithFileSLDRequest {

    private final ThreadLocal<GPGeoserverBooleanQueryParam> raw;
    protected final ThreadLocal<File> styleBody;

    /**
     * @param theServerConnector
     */
    GPGeoserverUpdateStyleWithFileSLDRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_JAXB_XML_SUPPORT);
        this.raw = withInitial(() -> null);
        this.styleBody = withInitial(() -> null);
    }

    /**
     * @param theRaw
     * @return {@link GeoserverCreateStyleWithFileSLDRequest}
     */
    @Override
    public GeoserverUpdateStyleWithFileSLDRequest withRaw(@Nullable Boolean theRaw) {
        this.raw.set(new GPGeoserverBooleanQueryParam("raw", theRaw));
        return self();
    }

    /**
     * @param theStyleBody
     * @return {@link GeoserverUpdateStyleWithFileSLDRequest}
     */
    @Override
    public GeoserverUpdateStyleWithFileSLDRequest withStyleBody(@Nonnull(when = When.NEVER) File theStyleBody) {
        this.styleBody.set(theStyleBody);
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
        fromArray(this.raw)
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
        File file = this.styleBody.get();
        checkArgument(file != null, "The style file must not be null");
        String contentType = this.checkSLD10Version(this.styleBody.get())  ? SLD.getContentType()  : SLD_1_1_0
                .getContentType();
        FileEntity builder = new FileEntity(file, create(contentType));
        return builder;
    }

    /**
     * @param httpMethod
     */
    @Override
    protected void addHeaderParams(HttpUriRequest httpMethod) {
        String contentType = this.checkSLD10Version(this.styleBody.get())  ? SLD.getContentType()  : SLD_1_1_0
                .getContentType();
        httpMethod.addHeader("Content-Type", contentType);
    }

    /**
     * @param fileSLD
     * @return {@link Boolean}
     */
    private Boolean checkSLD10Version(File fileSLD) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileSLD);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("//@version='1.0.0'");
            return (Boolean)expr.evaluate(doc, XPathConstants.BOOLEAN);
        } catch (Exception var6) {
            logger.error("Error parsing SLD file: " + var6);
            return FALSE;
        }
    }
}