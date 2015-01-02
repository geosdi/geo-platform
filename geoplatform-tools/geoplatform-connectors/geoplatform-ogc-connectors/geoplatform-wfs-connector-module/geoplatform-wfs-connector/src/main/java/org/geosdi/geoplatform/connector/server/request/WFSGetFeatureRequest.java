/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request;

import java.math.BigInteger;
import java.util.List;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @param <T>
 */
public interface WFSGetFeatureRequest<T> extends GPConnectorRequest<T> {

    /**
     * Gets the value of the type name query property.
     * 
     * @return QName
     */
    QName getTypeName();

    /**
     * Sets the value of the type name query property.
     * 
     * @param typeName
     */
    void setTypeName(QName typeName);

    /**
     * Gets the value of the feature ID query property.
     * 
     * @return List<String>
     */
    List<String> getFeatureIDs();

    /**
     * Sets the value of the feature ID query property.
     * 
     * @param FeatureIDs
     */
    void setFeatureIDs(List<String> FeatureIDs);

    /**
     * Gets the value of the BBox query property.
     * 
     * @return BBox
     */
    BBox getBBox();

    /**
     * Sets the value of the BBox query property.
     * 
     * @param bBox
     */
    void setBBox(BBox bBox);

    /**
     * Gets the value of the SRS query property.
     * 
     * @return srs
     */
    String getSRS();

    /**
     * Sets the value of the SRS query property.
     * 
     * @param srs
     */
    void setSRS(String srs);
    
    /**
     * Gets the value of the resultType property.
     * 
     * @return ResultType
     */
    String getResultType();

    /**
     * Sets the value of the resultType property.
     *
     * The only admissible parameters are:
     *
     * <ul> <li>results</li> <li>hits</li> </ul>
     *
     * <p>Default value is "results".</p>
     * 
     * @param resultType
     */
    void setResultType(String resultType);

    /**
     * Gets the value of the outputFormat property.
     * 
     * @return OutputFormat
     */
    String getOutputFormat();

    /**
     * Sets the value of the outputFormat property.
     *
     * <p>Default value is "text/xml; subtype=gml/3.1.1".</p>
     * 
     * @param outputFormat
     */
    void setOutputFormat(String outputFormat);

    /**
     * Gets the value of the maxFeatures property.
     * 
     * @return MaxFeatures Number
     */
    BigInteger getMaxFeatures();

    /**
     * Sets the value of the maxFeatures property.
     *
     * <p>There is no default value defined and the absence of the attribute
     * means that all feature type instances in the result should should be
     * returned to the client.</p>
     * 
     * @param value
     */
    void setMaxFeatures(BigInteger value);
}
