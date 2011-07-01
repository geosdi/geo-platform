package org.geosdi.publisher.services;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;

import org.geosdi.publisher.exception.ResourceNotFoundFault;

/**
 * @author Luca Paolino  - geoSDI
 *
 * Public interface to define the service operations mapped via REST
 * using CXT framework
 */
@WebService(name = "GPPublisherService", targetNamespace = "http://services.geo-platform.org/")
public interface GPPublisherService {


    @Get
    @HttpResource(location = "/preview/")
    @WebResult(name = "Result")
    boolean upload(@WebParam(name = "serverUrl") String serverUrl)
            throws ResourceNotFoundFault;
}