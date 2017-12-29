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
package org.geosdi.geoplatform.gml.api.parser.base;

import com.vividsolutions.jts.geom.Geometry;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.text.ParseException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DefaultSRSBaseParser extends AbstractGMLBaseSRSParser {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DefaultSRSBaseParser(String[] thePatterns) {
        super(thePatterns);
    }

    @Override
    public void parseSRS(AbstractGeometry gmlGeometry, Geometry jtsGeometry) throws ParserException, NullPointerException {
        checkNotNull(gmlGeometry, "The Gml Geometry must not be null");
        checkNotNull(jtsGeometry, "The JTS Geometry must not be null");
        String srsName = gmlGeometry.getSrsName();
        if (srsName != null) {
            for (String pattern : patterns) {
                try {
                    final MessageFormat format = new MessageFormat(pattern);
                    Object[] codearray = format.parse(srsName);
                    if (codearray.length > 0) {
                        jtsGeometry.setSRID(((Number) codearray[0]).intValue());
                        if (jtsGeometry.getUserData() == null) {
                            jtsGeometry.setUserData(srsName);
                            return;
                        }
                    }
                } catch (ParseException e) {
                    //only trace the ParserException and continues the cycle
                    logger.trace("DefaultSRSBaseParser - Parser "
                            + "Exception @@@@@@@@@@@@@@@@@ " + e);
                }
            }

            if (jtsGeometry.getUserData() != null) {
                throw new ParserException(MessageFormat.format(
                        "Could not parse SRS name [{0}].",
                        srsName));
            } else {
                jtsGeometry.setUserData(srsName);
            }
        }
    }
}
