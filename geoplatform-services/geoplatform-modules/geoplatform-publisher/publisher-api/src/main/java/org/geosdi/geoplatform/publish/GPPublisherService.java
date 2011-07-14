package org.geosdi.geoplatform.publish;
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
import org.geosdi.geoplatform.responce.PreviewElement;



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
    List<InfoPreview> uploadZIPInPreview(@WebParam(name = "fileName") File file)
            throws ResourceNotFoundFault;

    @Get
    @HttpResource(location = "/preview/uploadShpInPreview")
    @WebResult(name = "Result")
    List<InfoPreview> uploadShapeInPreview(@WebParam(name = "shpFileName") File shpFile,
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