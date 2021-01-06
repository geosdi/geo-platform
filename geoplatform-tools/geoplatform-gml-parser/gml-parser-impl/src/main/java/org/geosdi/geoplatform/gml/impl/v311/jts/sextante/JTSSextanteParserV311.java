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
package org.geosdi.geoplatform.gml.impl.v311.jts.sextante;

import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.GeometryProperty;
import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.gml.api.parser.base.GMLBaseVersion;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.sextante.JTSSextanteParser;
import org.geosdi.geoplatform.gml.impl.v311.jts.sextante.responsibility.JTSSextantePointHandler;
import org.locationtech.jts.geom.Geometry;

import javax.xml.bind.JAXBElement;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.geosdi.geoplatform.gml.impl.v311.jts.parameter.JTSParametersRepo.getDefaultGmlObjectFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSSextanteParserV311 extends JTSSextanteParser {

    private AbstractGMLObjectFactory gmlFactory;
    private final JTSSextantePointHandler sextantePointHandler = new JTSSextantePointHandler();

    /**
     * @param jtsGeometry
     * @return {@link AbstractGeometry}
     * @throws ParserException
     */
    @Override
    public AbstractGeometry parseGeometry(Geometry jtsGeometry) throws ParserException {
        return sextantePointHandler.parseGeometry(jtsGeometry);
    }

    /**
     * @param jtsGeometry
     * @return {@link GeometryProperty}
     * @throws ParserException
     */
    @Override
    public GeometryProperty parseProperty(Geometry jtsGeometry) throws ParserException {
        checkNotNull(jtsGeometry, "JTS Geometry must not be null.");
        GeometryProperty geometryProperty = acquireGMLFactory().createGeometryPropertyType();
        geometryProperty.setAbstractGeometry(buildJAXBElement(jtsGeometry));
        return geometryProperty;
    }

    /**
     * @param geometry
     * @return {@link JAXBElement<? extends AbstractGeometry>}
     * @throws ParserException
     */
    @Override
    public JAXBElement<? extends AbstractGeometry> buildJAXBElement(Geometry geometry) throws ParserException {
        return sextantePointHandler.buildJAXBElement(geometry);
    }

    /**
     * @return {@link GMLBaseVersion}
     */
    @Override
    public GMLBaseVersion getGMLVersion() {
        return GMLBaseVersion.V311;
    }

    /**
     * @return {@link AbstractGMLObjectFactory}
     */
    @Override
    protected AbstractGMLObjectFactory acquireGMLFactory() {
        return gmlFactory = ((gmlFactory == null) ? getDefaultGmlObjectFactory() : gmlFactory);
    }
}