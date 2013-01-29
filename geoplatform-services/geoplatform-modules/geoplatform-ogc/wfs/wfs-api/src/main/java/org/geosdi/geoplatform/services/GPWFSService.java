/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.codehaus.jra.Get;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.FeatureCollectionDTO;
import org.geosdi.geoplatform.gui.responce.FeatureDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

/**
 * Public interface to define the service operations mapped via REST using CXT
 * framework.
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 */
@WebService(name = "GPWFSService",
            targetNamespace = "http://services.geo-platform.org/")
public interface GPWFSService {

    @Get
    LayerSchemaDTO describeFeatureType(
            @WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName)
            throws Exception;

    @Get
    FeatureDTO getFeatureByFIDDirect(
            @WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName,
            @WebParam(name = "fid") String fid)
            throws Exception;

    @Get
    FeatureDTO getFeatureByFID(
            @WebParam(name = "layerSchema") LayerSchemaDTO layerSchema,
            @WebParam(name = "fid") String fid)
            throws Exception;

    @Get
    FeatureCollectionDTO getFeatureByBBoxDirect(
            @WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName,
            @WebParam(name = "bBox") BBox bBox)
            throws Exception;

    @Get
    FeatureCollectionDTO getFeatureByBBox(
            @WebParam(name = "layerSchema") LayerSchemaDTO layerSchema,
            @WebParam(name = "bBox") BBox bBox)
            throws Exception;

    @Get
    FeatureCollectionDTO getAllFeatureDirect(
            @WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName,
            @WebParam(name = "maxFeatures") int maxFeatures)
            throws Exception;

    @Get
    FeatureCollectionDTO getAllFeature(
            @WebParam(name = "layerSchema") LayerSchemaDTO layerSchema,
            @WebParam(name = "maxFeatures") int maxFeatures)
            throws Exception;

    @Get
    boolean transactionUpdate(
            @WebParam(name = "serverURL") String serverURL,
            @WebParam(name = "typeName") String typeName,
            @WebParam(name = "fid") String fid,
            @WebParam(name = "attributes") List<AttributeDTO> attributes)
            throws Exception;
}
