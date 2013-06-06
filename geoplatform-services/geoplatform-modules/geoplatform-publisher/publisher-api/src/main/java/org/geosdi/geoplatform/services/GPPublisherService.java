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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.LayerAttribute;

/**
 * Public interface to define the service operations mapped via REST using CXT
 * framework.
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@WebService(name = "GPPublisherService",
        targetNamespace = "http://services.geo-platform.org/")
public interface GPPublisherService extends IGPPublisherService {

    @Get
    @HttpResource(location = "/preview/analyzeZIPEPSG")
    @WebResult(name = "Result")
    List<InfoPreview> analyzeZIPEPSG(
            @WebParam(name = "sessionID") String sessionID,
            @WebParam(name = "username") String userName,
            @WebParam(name = "fileName") File file)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/processEPSGResult")
    @WebResult(name = "Result")
    List<InfoPreview> processEPSGResult(
            @WebParam(name = "username") String userName,
            @WebParam(name = "previewLayerList") List<InfoPreview> previewLayerList)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/loadStyle")
    @WebResult(name = "Result")
    String loadStyle(
            @WebParam(name = "layerDatasource") String layerDatasource,
            @WebParam(name = "styleName") String styleName)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/loadStyle")
    @WebResult(name = "Result")
    List<LayerAttribute> describeFeatureType(
            @WebParam(name = "layerName") String layerName)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/publishStyle")
    @WebResult(name = "Result")
    boolean publishStyle(
            @WebParam(name = "styleToPublish") String styleToPublish)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/putStyle")
    @WebResult(name = "Result")
    boolean putStyle(
            @WebParam(name = "styleToPublish") String styleToPublish,
            @WebParam(name = "styleName") String styleName)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/existsStyle")
    @WebResult(name = "Result")
    public boolean existsStyle(
            @WebParam(name = "styleName") String styleName);

    @Get
    @HttpResource(location = "/preview/uploadZipInPreview")
    @WebResult(name = "Result")
    InfoPreview analyzeTIFInPreview(
            @WebParam(name = "username") String sessionID,
            @WebParam(name = "fileName") File file,
            @WebParam(name = "overwrite") boolean overwrite)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/uploadShpInPreview")
    @WebResult(name = "Result")
    List<InfoPreview> uploadShapeInPreview(
            @WebParam(name = "sessionID") String sessionID,
            @WebParam(name = "username") String username,
            @WebParam(name = "shpFileName") File shpFile,
            @WebParam(name = "dbfFileName") File dbfFile,
            @WebParam(name = "shxFileName") File shxFile,
            @WebParam(name = "prjFileName") File prjFile,
            @WebParam(name = "sldFileName") File sldFile)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/getPreviewDataStores")
    @WebResult(name = "Result")
    List<InfoPreview> getPreviewDataStores(
            @WebParam(name = "userName") String userName)
            throws ResourceNotFoundFault;

//    @Post
//    @HttpResource(location = "/preview/createSHP")
//    @WebResult(name = "Result")
//    byte[] createSHP(@WebParam(name = "sessionID") String sessionID,
//            @WebParam(name = "featureList") List<Feature> list,
//            @WebParam(name = "shpFileName") String shpFileName)
//            throws ResourceNotFoundFault, Exception;
//
//    @Post
//    @HttpResource(location = "/preview/verifyAndDeleteSessionDir")
//    @WebResult(name = "Result")
//    boolean verifyAndDeleteSessionDir(
//            @WebParam(name = "idSessionDestroyed") String idSessionDestroyed);
//    
    @Get
    @HttpResource(location = "/preview/publish")
    @WebResult(name = "Result")
    boolean publish(@WebParam(name = "sessionID") String sessionID,
            @WebParam(name = "workspace") String workspace,
            @WebParam(name = "dataStoreName") String dataStoreName,
            @WebParam(name = "layerName") String layerName)
            throws ResourceNotFoundFault, FileNotFoundException;

    @Get
    @HttpResource(location = "/preview/publishAll")
    @WebResult(name = "Result")
    boolean publishAll(@WebParam(name = "sessionID") String sessionID,
            @WebParam(name = "workspace") String workspace,
            @WebParam(name = "dataStoreName") String dataStoreName,
            @WebParam(name = "layerName") List<String> layerNames)
            throws ResourceNotFoundFault, FileNotFoundException;

    @Get
    @HttpResource(location = "/preview/publishAllofPreview")
    @WebResult(name = "Result")
    boolean publishAllofPreview(@WebParam(name = "sessionID") String sessionID,
            @WebParam(name = "workspace") String workspace,
            @WebParam(name = "dataStoreName") String dataStoreName)
            throws ResourceNotFoundFault, FileNotFoundException;
//    
//    @Get
//    @HttpResource(location = "/preview/removeFromPreview")
//    @WebResult(name = "Result")
//    boolean removeSHPFromPreview(@WebParam(name = "userName") String userName,
//            @WebParam(name = "layerName") String layerName)
//            throws ResourceNotFoundFault;
//    
//    @Get
//    @HttpResource(location = "/preview/removeFromPreview")
//    @WebResult(name = "Result")
//    boolean removeTIFFromPreview(@WebParam(name = "userName") String userName,
//            @WebParam(name = "layerName") String layerName)
//            throws ResourceNotFoundFault;
}