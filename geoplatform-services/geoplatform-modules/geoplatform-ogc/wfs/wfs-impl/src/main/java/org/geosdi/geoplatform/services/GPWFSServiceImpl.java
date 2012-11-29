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
package org.geosdi.geoplatform.services;

import javax.jws.WebService;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.FeatureCollectionDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.services.feature.DescribeFeatureService;
import org.geosdi.geoplatform.services.feature.GetFeaureService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPWFSService")
public class GPWFSServiceImpl implements GPWFSService {

    @Autowired
    private DescribeFeatureService gpDescribeFeatureService;
    //
    @Autowired
    private GetFeaureService gpGetFeatureService;

    @Override
    public LayerSchemaDTO describeFeatureType(String serverURL, String typeName)
            throws ResourceNotFoundFault, IllegalParameterFault {

        return gpDescribeFeatureService.describeFeatureType(serverURL, typeName);
    }

    @Override
    public FeatureCollectionDTO getFeature(String serverURL, String typeName, BBox bBox)
            throws ResourceNotFoundFault, IllegalParameterFault {

        LayerSchemaDTO layerSchema = this.describeFeatureType(serverURL, typeName);
        return this.getFeatureFromLayerSchema(layerSchema, bBox);
    }

    @Override
    public FeatureCollectionDTO getFeatureFromLayerSchema(LayerSchemaDTO layerSchema, BBox bBox)
            throws ResourceNotFoundFault, IllegalParameterFault {

        return gpGetFeatureService.getFeature(layerSchema, bBox);
    }
}
