package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.member.geojson;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.MultiGeometry;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface MemberGeoJsonBuilder extends Serializable {

    /**
     * @param gmlGeometry
     * @param geometries
     * @throws ParserException
     */
    void builMemberAsGeoJson(@Nonnull(when = NEVER) MultiGeometry gmlGeometry, @Nonnull(when = NEVER) List<GeoJsonObject> geometries) throws ParserException;
}