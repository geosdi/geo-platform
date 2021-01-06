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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point;

import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.gml.api.MultiPoint;
import org.geosdi.geoplatform.gml.api.MultiPointProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.member.MemberBuilder;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.member.MultiPointMemberBuilder;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.member.MultiPointMembersBuilder;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseMultiPointParser extends AbstractGMLBaseParser<MultiPoint, MultiPointProperty, org.locationtech.jts.geom.MultiPoint, org.geojson.MultiPoint> {

    private GMLBasePointParser pointParser;
    private final MemberBuilder pointMember;
    private final MemberBuilder pointMembers;

    /**
     * @param theGeometryFactory
     * @param theSrsParser
     * @param thePointParser
     */
    public GMLBaseMultiPointParser(@Nonnull(when = NEVER) GeometryFactory theGeometryFactory, @Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSrsParser,
            @Nonnull(when = NEVER) GMLBasePointParser thePointParser) {
        super(theGeometryFactory, theSrsParser);
        checkArgument(thePointParser != null, "The Parameter pointParser must not be null.");
        this.pointParser = thePointParser;
        this.pointMember = new MultiPointMemberBuilder(this.pointParser);
        this.pointMembers = new MultiPointMembersBuilder(this.pointParser);
    }

    /**
     * @param gmlGeometry
     * @return {@link org.locationtech.jts.geom.MultiPoint}
     * @throws ParserException
     */
    @Override
    protected org.locationtech.jts.geom.MultiPoint canParseGeometry(MultiPoint gmlGeometry) throws ParserException {
        List<Point> points = new ArrayList<>();
        this.pointMember.builMember(gmlGeometry, points);
        this.pointMembers.builMember(gmlGeometry, points);
        checkArgument(!points.isEmpty(), "PointMember and PointMembers can't be both null.");
        org.locationtech.jts.geom.MultiPoint multiPoint = geometryFactory.createMultiPoint(points.toArray(new Point[points.size()]));
        this.srsParser.parseSRS(gmlGeometry, multiPoint);
        return multiPoint;
    }

    /**
     * @param propertyType
     * @return {@link org.locationtech.jts.geom.MultiPoint}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.MultiPoint parseGeometry(MultiPointProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The MultiPoint Property must be not null.");
        if (propertyType.isSetMultiPoint()) {
            return super.parseGeometry(propertyType.getMultiPoint());
        }
        throw new ParserException("There is no GML MultiPoint to Parse.");
    }

    /**
     * @param gmlGeometry
     * @return {@link org.geojson.Polygon}
     * @throws ParserException
     */
    @Override
    protected org.geojson.MultiPoint canParseGeometryAsGeoJson(MultiPoint gmlGeometry) throws ParserException {
        List<org.geojson.Point> points = new ArrayList<>();
        this.pointMember.builMemberAsGeoJson(gmlGeometry, points);
        this.pointMembers.builMemberAsGeoJson(gmlGeometry, points);
        checkArgument(!points.isEmpty(), "PointMember and PointMembers can't be both null.");
        List<LngLatAlt> coordinates = points.stream()
                .filter(Objects::nonNull)
                .map(point -> point.getCoordinates())
                .collect(toList());
        org.geojson.MultiPoint multiPoint = new org.geojson.MultiPoint(coordinates.toArray(new LngLatAlt[coordinates.size()]));
        multiPoint.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return multiPoint;
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.Polygon}
     * @throws ParserException
     */
    @Override
    public org.geojson.MultiPoint parseGeometryAsGeoJson(MultiPointProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The MultiPoint Property must be not null.");
        if (propertyType.isSetMultiPoint()) {
            return super.parseGeometryAsGeoJson(propertyType.getMultiPoint());
        }
        throw new ParserException("There is no GML MultiPoint to Parse.");
    }
}