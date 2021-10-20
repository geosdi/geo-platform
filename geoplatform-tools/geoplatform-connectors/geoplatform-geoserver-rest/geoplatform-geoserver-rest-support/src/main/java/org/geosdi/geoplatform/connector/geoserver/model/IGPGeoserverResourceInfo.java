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
package org.geosdi.geoplatform.connector.geoserver.model;

import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.keyword.IGPGeoserverKeyword;
import org.geosdi.geoplatform.connector.geoserver.model.namespace.IGPGeoserverNamespace;
import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverResponseSRS;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStoreInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoserverResourceInfo<NativeBoundingBox extends GPGeoserverBoundingBox<?>> extends Serializable {

    /**
     * @return {@link GPGeoserverStoreInfo}
     */
    GPGeoserverStoreInfo getStore();

    /**
     * @param theStore
     */
    void setStore(GPGeoserverStoreInfo theStore);

    /**
     * @return {@link String}
     */
    String getAbstractText();

    void setAbstractText(String theAbstractText);

    /**
     * @return {@link String}
     */
    String getNativeName();

    /**
     * @param theNativeName
     */
    void setNativeName(String theNativeName);

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link String}
     */
    String getTitle();

    /**
     * @param theTitle
     */
    void setTitle(String theTitle);

    /**
     * @return {@link String}
     */
    String getSrs();

    /**
     * @param theSrs
     */
    void setSrs(String theSrs);

    /**
     * @return {@link Namespace}
     */
    <Namespace extends IGPGeoserverNamespace> Namespace getNamespace();

    /**
     * @param theNamespace
     * @param <Namespace>
     */
    <Namespace extends IGPGeoserverNamespace> void setNamespace(Namespace theNamespace);

    /**
     * @return {@link Boolean}
     */
    boolean isEnabled();

    /**
     * @param theEnabled
     */
    void setEnabled(boolean theEnabled);

    /**
     * @return {@link Map<String, String>}
     */
    Map<String, String> getMetadata();

    /**
     * @param theMetadata
     */
    void setMetadata(Map<String, String> theMetadata);

    /**
     * @return {@link IGPGeoserverKeyword}
     */
    IGPGeoserverKeyword getKeywords();

    /**
     * @param theKeywords
     */
    void setKeywords(IGPGeoserverKeyword theKeywords);

    /**
     * @return {@link GPGeoserverLatLonBoundingBox}
     */
    GPGeoserverLatLonBoundingBox getLatLonBoundingBox();

    /**
     * @param theLatLonBoundingBox
     */
    void setLatLonBoundingBox(GPGeoserverLatLonBoundingBox theLatLonBoundingBox);

    /**
     * @return {@link NativeBoundingBox}
     */
    NativeBoundingBox getNativeBoundingBox();

    /**
     * @param theNativeBoundingBox
     */
    void setNativeBoundingBox(NativeBoundingBox theNativeBoundingBox);

    /**
     * @return {@link GPGeoserverResponseSRS}
     */
    GPGeoserverResponseSRS getResponseSRS();

    /**
     * @param theResponseSRS
     */
    void setResponseSRS(GPGeoserverResponseSRS theResponseSRS);
}