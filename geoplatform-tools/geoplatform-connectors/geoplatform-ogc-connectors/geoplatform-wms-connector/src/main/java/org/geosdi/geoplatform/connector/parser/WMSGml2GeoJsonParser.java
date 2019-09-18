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