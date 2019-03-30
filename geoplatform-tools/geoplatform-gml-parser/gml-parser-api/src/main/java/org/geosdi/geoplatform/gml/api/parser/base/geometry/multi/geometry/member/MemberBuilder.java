package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.member;

import org.geosdi.geoplatform.gml.api.MultiGeometry;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.member.geojson.MemberGeoJsonBuilder;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Geometry;

import javax.annotation.Nonnull;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface MemberBuilder extends MemberGeoJsonBuilder {

    GMLBaseSextanteParser geometryParser = GMLBaseParametersRepo.getDefaultSextanteParser();

    /**
     * @param gmlGeometry
     * @param geometries
     * @throws ParserException
     */
    void builMember(@Nonnull(when = NEVER) MultiGeometry gmlGeometry, @Nonnull(when = NEVER) List<Geometry> geometries) throws ParserException;
}