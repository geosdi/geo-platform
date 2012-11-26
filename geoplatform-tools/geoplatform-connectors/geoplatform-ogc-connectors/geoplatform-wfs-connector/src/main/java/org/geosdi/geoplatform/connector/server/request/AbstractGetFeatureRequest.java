/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.connector.server.request;

import java.math.BigInteger;
import java.util.List;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class AbstractGetFeatureRequest<T> extends WFSRequest<T>
        implements WFSGetFeatureRequest<T> {

    protected static final String NAME_GEOMETRY = "the_geom"; // TODO name property geometry is always "the_geom"?
    //
    protected QName typeName;
    protected List<String> featureIDs;
    protected BBox bBox;
    protected String srs;
    protected String resultType;
    protected String outputFormat;
    protected BigInteger maxFeatures;

    public AbstractGetFeatureRequest(GPServerConnector server) {
        super(server);
    }

    @Override
    public QName getTypeName() {
        return typeName;
    }

    @Override
    public void setTypeName(QName typeName) {
        this.typeName = typeName;
    }

    @Override
    public List<String> getFeatureIDs() {
        return featureIDs;
    }

    @Override
    public void setFeatureIDs(List<String> featureIDs) {
        this.featureIDs = featureIDs;
    }

    @Override
    public BBox getBBox() {
        return bBox;
    }

    @Override
    public void setBBox(BBox bBox) {
        this.bBox = bBox;
    }

    @Override
    public String getSRS() {
        return srs;
    }

    @Override
    public void setSRS(String srs) {
        this.srs = srs;
    }

    @Override
    public String getResultType() {
        return resultType;
    }

    @Override
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Override
    public String getOutputFormat() {
        return outputFormat;
    }

    @Override
    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    @Override
    public BigInteger getMaxFeatures() {
        return maxFeatures;
    }

    @Override
    public void setMaxFeatures(BigInteger maxFeatures) {
        this.maxFeatures = maxFeatures;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "typeName=" + typeName
                + ", featureIDs=" + featureIDs
                + ", bBox=" + bBox
                + ", resultType=" + resultType
                + ", outputFormat=" + outputFormat
                + ", maxFeatures=" + maxFeatures + '}';
    }
}
