package org.geosdi.publisher.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;

import org.geosdi.publisher.exception.ResourceNotFoundFault;
import org.geosdi.publisher.responce.PreviewElement;

/**
 * @author Luca Paolino  - geoSDI
 *
 * Public interface to define the service operations mapped via REST
 * using CXT framework
 */
@WebService(name = "GPPublisherService", targetNamespace = "http://services.geo-platform.org/")
public interface GPPublisherService {

    @Get
    @HttpResource(location = "/preview/uploadZipInPreview")
    @WebResult(name = "Result")
    String uploadZIPInPreview(@WebParam(name = "fileName") File file)
            throws ResourceNotFoundFault;
    
    @Get
    @HttpResource(location = "/preview/uploadShpInPreview")
    @WebResult(name = "Result")
    String uploadShapeInPreview(@WebParam(name = "shpFileName") File shpFile,
                       @WebParam(name = "dbfFileName") File dbfFile,
                       @WebParam(name = "shxFileName") File shxFile,
                       @WebParam(name = "prjFileName") File prjFile)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/getPreviewDataStores")
    @WebResult(name = "Result")
    List<PreviewElement> getPreviewDataStores()
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/publish")
    @WebResult(name = "Result")
    boolean publish(@WebParam(name = "workspace") String workspace,
                    @WebParam(name = "dataStoreName") String dataStoreName,
                    @WebParam(name = "layerName") String layerName) throws ResourceNotFoundFault, FileNotFoundException;

    @Get
    @HttpResource(location = "/preview/removeFromPreview")
    @WebResult(name = "Result")
    boolean removeFromPreview(@WebParam(name = "dataStoreName") String dataStoreName)
            throws ResourceNotFoundFault;

}