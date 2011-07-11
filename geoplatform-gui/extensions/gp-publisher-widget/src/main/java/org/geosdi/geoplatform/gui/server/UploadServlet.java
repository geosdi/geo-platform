/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.geosdi.publisher.exception.ResourceNotFoundFault;
import org.geosdi.publisher.services.GPPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Service("uploadServlet")
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = -1464439864247709647L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GPPublisherService geoPlatformPublishClient;
//    private GPPublisherService publisherService = new GPPublisherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // process only multipart requests
        if (ServletFileUpload.isMultipartContent(req)) {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            File uploadedFile = null;
            // Parse the request
            try {
                List<FileItem> items = upload.parseRequest(req);

                for (FileItem item : items) {
                    // process only file upload - discard other form item types
                    if (item.isFormField()) {
                        continue;
                    }
                    String fileName = item.getName();
                    // get only the file name not whole path
                    if (fileName != null) {
                        fileName = FilenameUtils.getName(fileName);
                    }

                    uploadedFile = File.createTempFile(fileName, "");
                    // if (uploadedFile.createNewFile()) {
                    item.write(uploadedFile);
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    resp.flushBuffer();

                    // uploadedFile.delete();
                    // } else
                    // throw new IOException(
                    // "The file already exists in repository.");
                }
            } catch (Exception e) {
                resp.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "An error occurred creating the file: "
                        + e.getMessage());
            }
            //TODO: Insert call to the services
            String pngURL = null;
            System.out.println("THis.geoplatqualcosa null");
            System.out.println(this.geoPlatformPublishClient == null);
            try {
                pngURL = this.geoPlatformPublishClient.uploadZIPInPreview(uploadedFile);
            } catch (ResourceNotFoundFault ex) {
                logger.equals("Error on uploading shape: " + ex);
            }
            System.out.println("PNG URL: uguale null");
            System.out.println(pngURL == null);
//            try {
//                String wkt = calculateWKT(uploadedFile);
//                resp.getWriter().print(wkt);
//            } catch (Exception exc) {
//                logger.info("ERROR ********** " + exc);
//            }

            uploadedFile.delete();

        } else {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                    "Request contents type is not supported by the servlet.");
        }
    }

    /**
     * @param geoPlatformServiceClient the geoPlatformServiceClient to set
     */
    @Autowired
    public void setGeoPlatformPublishClient(
            @Qualifier("geoPlatformPublishClient") GPPublisherService geoPlatformPublishClient) {
        this.geoPlatformPublishClient = geoPlatformPublishClient;
    }
}
