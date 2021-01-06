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
package org.geosdi.geoplatform.connector.parser;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.gml2.GMLReader;
import org.geojson.GeoJsonObject;
import org.geojson.Geometry;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.support.jackson.jts.GPJacksonJTSSupport;
import org.geosdi.geoplatform.support.jackson.jts.IGPJacksonJTSSupport;
import org.geosdi.geoplatform.xml.gml.v212.AbstractGeometryType;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geotools.referencing.CRS.decode;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMSGml2GeoJsonParser implements GPWMSGml2GeoJsonParser {

    private static final Logger logger = LoggerFactory.getLogger(WMSGml2GeoJsonParser.class);
    //
    private static final IGPJacksonJTSSupport JACKSON_JTS_SUPPORT = new GPJacksonJTSSupport();
    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel());
    private static final GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static final GPWMSSRSParser srsParser = new GPWMSSRSParser.WMSSRSParser();

    /**
     * @param theReader
     * @return {@link Geometry}
     * @throws Exception
     */
    @Override
    public GeoJsonObject parse(@Nonnull(when = NEVER) XMLStreamReader theReader) throws Exception {
        checkArgument(theReader != null, "The Parameter reader must not be null.");
        AbstractGeometryType gmlGeometry = jaxbContextBuilder.unmarshal(theReader, AbstractGeometryType.class);
        logger.trace("################################GML2_GEOMETRY : {}\n", gmlGeometry);
        StringWriter writer = new StringWriter();
        jaxbContextBuilder.marshal(gmlGeometry, writer);
        GMLReader gmlReader = new GMLReader();
        com.vividsolutions.jts.geom.Geometry jtsGeometry = gmlReader.read(new StringReader(writer.toString()), GEOMETRY_FACTORY);
        srsParser.parseSRS(gmlGeometry, jtsGeometry);
        return (jtsGeometry.getSRID() != 4326) && (jtsGeometry.getSRID() != 0) ? this.transform(jtsGeometry) : JACKSON_JTS_SUPPORT.convertVividisolutionGeometryToGeoJson(jtsGeometry);
    }

    /**
     * @param toTransform
     * @return {@link GeoJsonObject}
     * @throws Exception
     */
    protected final GeoJsonObject transform(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.Geometry toTransform) throws Exception {
        checkArgument(toTransform != null, "The Parameter Geometry toTransform must not be null.");
        CoordinateReferenceSystem sourceCRS = decode("EPSG:" + toTransform.getSRID(), TRUE);
        CoordinateReferenceSystem targetCRS = decode("EPSG:4326", TRUE);
        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, TRUE);
        com.vividsolutions.jts.geom.Geometry translate = JTS.transform(toTransform, transform);
        return JACKSON_JTS_SUPPORT.convertVividisolutionGeometryToGeoJson(translate);
    }
}