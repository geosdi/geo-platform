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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometricAggregate;
import org.geosdi.geoplatform.gml.api.MultiGeometry;
import org.geosdi.geoplatform.gml.api.MultiGeometryProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.member.MemberBuilder;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.member.MultiGeometryMember;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.member.MultiGeometryMembers;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo.getDefaultGeometryFactory;
import static org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo.getDefaultSRSParser;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseMultiGeometryParser extends AbstractGMLBaseParser<MultiGeometry, MultiGeometryProperty, GeometryCollection, org.geojson.GeometryCollection> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final MemberBuilder multiGeometryMember = new MultiGeometryMember();
    private final MemberBuilder multiGeometryMembers = new MultiGeometryMembers();

    /**
     * @param theGeometryFactory
     * @param theSrsParser
     */
    public GMLBaseMultiGeometryParser(@Nonnull(when = NEVER) GeometryFactory theGeometryFactory, @Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSrsParser) {
        super(theGeometryFactory, theSrsParser);
    }

    public GMLBaseMultiGeometryParser() {
        this(getDefaultGeometryFactory(), getDefaultSRSParser());
    }

    /**
     * @param gmlGeometry
     * @return {@link GeometryCollection}
     * @throws ParserException
     */
    @Override
    protected GeometryCollection canParseGeometry(MultiGeometry gmlGeometry) throws ParserException {
        logger.trace("##########################Called {}#canParseGeometry.", this);
        List<Geometry> geometries = new ArrayList<>();
        this.multiGeometryMember.builMember(gmlGeometry, geometries);
        this.multiGeometryMembers.builMember(gmlGeometry, geometries);
        checkArgument(!geometries.isEmpty(), "GeometryMember and GeometryMembers can't be both null.");
        GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(geometries.toArray(new Geometry[geometries.size()]));
        this.srsParser.parseSRS(gmlGeometry, geometryCollection);
        return geometryCollection;
    }

    /**
     * @param propertyType
     * @return {@link GeometryCollection}
     * @throws ParserException
     */
    @Override
    public GeometryCollection parseGeometry(MultiGeometryProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The MultiGeometry Property must be not null.");
        logger.trace("##########################Called {}#parseGeometry.", this);
        if (propertyType.isSetGeometricAggregate()) {
            AbstractGeometricAggregate geometryAggregate = propertyType.getAbstractGeometricAggregate();
            if (geometryAggregate instanceof MultiGeometry) {
                return super.parseGeometry((MultiGeometry) geometryAggregate);
            }
        }
        throw new ParserException("There is no GML MultiGeometry to parse.");
    }

    /**
     * @param gmlGeometry
     * @return {@link org.geojson.GeometryCollection}
     * @throws ParserException
     */
    @Override
    protected org.geojson.GeometryCollection canParseGeometryAsGeoJson(MultiGeometry gmlGeometry) throws ParserException {
        logger.trace("##########################Called {}#canParseGeometryAsGeoJson.", this);
        List<GeoJsonObject> geometries = new ArrayList<>();
        this.multiGeometryMember.builMemberAsGeoJson(gmlGeometry, geometries);
        this.multiGeometryMembers.builMemberAsGeoJson(gmlGeometry, geometries);
        checkArgument(!geometries.isEmpty(), "GeometryMember and GeometryMembers can't be both null.");
        org.geojson.GeometryCollection geometryCollection = new org.geojson.GeometryCollection();
        geometryCollection.setGeometries(geometries);
        geometryCollection.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return geometryCollection;
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.GeometryCollection}
     * @throws ParserException
     */
    @Override
    public org.geojson.GeometryCollection parseGeometryAsGeoJson(MultiGeometryProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The MultiGeometry Property must be not null.");
        logger.trace("##########################Called {}#parseGeometry.", this);
        if (propertyType.isSetGeometricAggregate()) {
            AbstractGeometricAggregate geometryAggregate = propertyType.getAbstractGeometricAggregate();
            if (geometryAggregate instanceof MultiGeometry) {
                return super.parseGeometryAsGeoJson((MultiGeometry) geometryAggregate);
            }
        }
        throw new ParserException("There is no GML MultiGeometry to parse.");
    }
}