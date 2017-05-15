/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geosdi.geoplatform.gml.api.MultiPoint;
import org.geosdi.geoplatform.gml.api.MultiPointProperty;
import org.geosdi.geoplatform.gml.api.PointArrayProperty;
import org.geosdi.geoplatform.gml.api.PointProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseMultiPointParser extends AbstractGMLBaseParser<MultiPoint, MultiPointProperty, com.vividsolutions.jts.geom.MultiPoint> {

    private GMLBasePointParser pointParser;
    private MemberBuilder pointMember = new PointMemberBuilder();
    private MemberBuilder pointMembers = new PointMembersBuilder();

    public GMLBaseMultiPointParser(GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser,
            GMLBasePointParser thePointParser) {
        super(theGeometryFactory, theSrsParser);
        this.pointParser = thePointParser;
    }

    @Override
    protected com.vividsolutions.jts.geom.MultiPoint canParseGeometry(MultiPoint gmlGeometry) throws ParserException {
        List<Point> points = new ArrayList<>();
        this.pointMember.builMember(gmlGeometry, points);
        this.pointMembers.builMember(gmlGeometry, points);
        if (points.isEmpty()) {
            throw new IllegalArgumentException("PointMember and PointMembers can't be both null.");
        }
        return geometryFactory.createMultiPoint(points.toArray(
                new Point[points.size()]));
    }

    @Override
    public com.vividsolutions.jts.geom.MultiPoint parseGeometry(MultiPointProperty propertyType)
            throws ParserException {
        Preconditions.checkNotNull(propertyType, "The MultiPoint Property must be not null.");
        if (propertyType.isSetMultiPoint()) {
            return super.parseGeometry(propertyType.getMultiPoint());
        }
        throw new ParserException("There is no GML MultiPoint to Parse.");
    }

    protected interface MemberBuilder {

        /**
         * @param gmlGeometry
         * @param points
         * @throws ParserException
         */
        void builMember(MultiPoint gmlGeometry, List<Point> points) throws ParserException;
    }

    protected class PointMemberBuilder implements MemberBuilder {

        @Override
        public void builMember(MultiPoint gmlGeometry, List<Point> points) throws ParserException {

            if (gmlGeometry.isSetPointMember()) {
                for (PointProperty property : gmlGeometry.getPointMember()) {
                    org.geosdi.geoplatform.gml.api.Point point = property.getPoint();
                    if ((gmlGeometry.isSetSrsDimension()) && !(point.isSetSrsDimension()))
                        point.setSrsDimension(gmlGeometry.getSrsDimension());
                    points.add(pointParser.parseGeometry(point));
                }
            }
        }
    }

    protected class PointMembersBuilder implements MemberBuilder {

        @Override
        public void builMember(MultiPoint gmlGeometry, List<Point> points) throws ParserException {

            if (gmlGeometry.isSetPointMembers()) {
                PointArrayProperty pointArrayProp = gmlGeometry.getPointMembers();
                for (org.geosdi.geoplatform.gml.api.Point point : pointArrayProp.getPoint()) {
                    if ((gmlGeometry.isSetSrsDimension()) && !(point.isSetSrsDimension()))
                        point.setSrsDimension(gmlGeometry.getSrsDimension());
                    points.add(pointParser.parseGeometry(point));
                }
            }
        }
    }
}
