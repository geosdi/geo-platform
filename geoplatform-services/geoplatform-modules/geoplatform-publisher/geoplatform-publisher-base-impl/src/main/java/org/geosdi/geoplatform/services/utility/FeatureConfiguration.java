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
package org.geosdi.geoplatform.services.utility;

import com.google.common.collect.Maps;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.Serializable;
import java.util.Map;

//import com.thoughtworks.xstream.XStream;
/**
 * DAO for the input / output XML file used by the action.
 */
public class FeatureConfiguration implements Cloneable {

    // feature type (schema) name
    private String typeName;
    // feature coordinate system (EPSG code): if not defined it will be read from
    // the input feature 
    private String crs;

    // feature coordinate system (cached after decoding crs)
    private transient CoordinateReferenceSystem coordinateReferenceSystem;

    // feature DataStore connection parameters
    private Map<String, Serializable> dataStore;

    /**
     *
     * @return the typeName of the feature.
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets typeName for the feature.
     *
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     *
     * @return the DataStore connection parameters.
     */
    public Map<String, Serializable> getDataStore() {
        if (this.dataStore == null) {
            this.dataStore = Maps.<String, Serializable>newHashMap();
        }
        return dataStore;
    }

    /**
     * Sets DataStore connection parameters.
     *
     * @param dataStore
     */
    public void setDataStore(Map<String, Serializable> dataStore) {
        this.dataStore = dataStore;
    }

    /**
     *
     * @return the CRS to use for the input feature
     */
    public String getCrs() {
        return crs;
    }

    /**
     * Sets the CRS to use for the input feature
     *
     * @param crs
     */
    public void setCrs(String crs) {
        this.crs = crs;
    }

    public void setCoordinateReferenceSystem(CoordinateReferenceSystem coordinateReferenceSystem) {
        this.coordinateReferenceSystem = coordinateReferenceSystem;
    }

    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        if (coordinateReferenceSystem == null && crs != null) {
            try {
                coordinateReferenceSystem = CRS.decode(crs);
            } catch (NoSuchAuthorityCodeException e) {
                throw new IllegalArgumentException("Invalid crs: " + crs);
            } catch (FactoryException e) {
                throw new IllegalArgumentException("Invalid crs: " + crs);
            }
        }
        return coordinateReferenceSystem;
    }

//	/**
//	 * Read a FeatureConfiguration xml from the given stream.
//	 * 
//	 * @param inputXML
//	 * @return
//	 */
//	public static FeatureConfiguration fromXML(InputStream inputXML) {
//		return (FeatureConfiguration) xstream.fromXML(inputXML);		
//	}
//	
//	/**
//	 * Outputs the FeatureConfiguration in XML to the given stream.
//	 * 
//	 * @param outXML
//	 */
//	public void toXML(OutputStream outXML) {
//		xstream.toXML(this, outXML);
//	}
    @Override
    public FeatureConfiguration clone() {
        try {
            FeatureConfiguration fc = (FeatureConfiguration) super.clone();
//        	fc.typeName = this.typeName ;
//        	fc.crs = this.crs;
//        	fc.coordinateReferenceSystem = this.coordinateReferenceSystem;
            if (this.dataStore != null) {
                fc.dataStore = Maps.<String, Serializable>newHashMap();
                for (Map.Entry<String, Serializable> entry : dataStore.entrySet()) {
                    fc.dataStore.put(entry.getKey(), entry.getValue());
                }
            } else {
                fc.dataStore = null;
            }

            return fc;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }
}
