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
package org.geosdi.geoplatform.gui.server;

import com.google.common.collect.Lists;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.SessionUtility.SessionProperty;
import org.geosdi.geoplatform.gui.server.uploader.IPublisherUploader;
import org.geosdi.geoplatform.gui.server.utility.PublisherFileUtils;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = -1464439864247709647L;
    //
    private static final Logger logger = LoggerFactory.getLogger(UploadServlet.class);
    //
    private static Assertion receivedAssertion;
    //
    @Autowired
    private PublisherFileUtils publisherFileUtils;
    //
    @Autowired
    private IPublisherUploader gpPublisherUploader;

    public static Assertion getReceivedAssertion() {
        return receivedAssertion;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, GeoPlatformException {
        this.doPost(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, GeoPlatformException {
//        logger.info("Query String: " + req.getQueryString());
//        while (req.getParameterMap().keySet().iterator().hasNext()) {
//            logger.info("Parameter next: " + req.getParameterMap().keySet().iterator().next());
//        }
//        while (req.getParameterNames().hasMoreElements()) {
//            logger.info("Parameter name: " + req.getParameterNames().nextElement());
//        }
//        while (req.getAttributeNames().hasMoreElements()) {
//            logger.info("Attribute name: " + req.getAttributeNames().nextElement());
//        }
        String workspace = null;
        HttpSession session = req.getSession();
        Object accountObj = session.getAttribute(SessionProperty.LOGGED_ACCOUNT.toString());
        GPAccount account;
        if (accountObj != null && accountObj instanceof GPAccount) {
            account = (GPAccount) accountObj;
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session Timeout");
            return;
        }
        receivedAssertion = (AssertionImpl) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        // process only multipart requests
        if (ServletFileUpload.isMultipartContent(req)) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // Create a new file upload handler
           /*
             * Set the size threshold, above which content will be stored on
             * disk.
             */
            factory.setSizeThreshold(1 * 1024 * 1024); //1 MB
            ServletFileUpload upload = new ServletFileUpload(factory);
            File uploadedFile = null;
            // Parse the request
            try {
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item : items) {
                    // process only file upload - discard other form item types
                    if (item.isFormField()) {
                        logger.debug("Analyzing item field name: " + item.getFieldName());
                        logger.debug("Analyzing item string: " + item.getString());
                        //Ricevo parametro
                        if (item.getFieldName().equals("workspace")) {
                            workspace = item.getString();
                            logger.debug("Found workspace in request param: " + workspace);
                        }
                    } else {
                        String fileName = item.getName();
                        // get only the file name not whole path
                        if (fileName != null) {
                            fileName = FilenameUtils.getName(fileName);
                        }

                        try {
                            uploadedFile = this.publisherFileUtils.createFileWithUniqueName(fileName);
                            item.write(uploadedFile);
                        } catch (Exception ex) {
                            logger.info("ERRORE : " + ex);
                            resp.sendError(
                                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                                    "An error occurred writing the file: "
                                    + ex.getMessage());
                            throw new GeoPlatformException(
                                    "Error on uploading shape.");
                        }
                        resp.setStatus(HttpServletResponse.SC_CREATED);
                        resp.flushBuffer();
                    }
                }
                List<InfoPreview> infoPreviews = this.manageUploadedFilePreview(uploadedFile, session.getId(), account.getNaturalID(), workspace);
                resp.setContentType("text/x-json;charset=UTF-8");
                resp.setHeader("Cache-Control", "no-cache");
                String result = PublisherFileUtils.generateJSONObjects(infoPreviews);
                resp.getWriter().write(result);
                //geoPlatformPublishClient.publish("previews", "dataTest", infoPreview.getDataStoreName());
            } catch (FileUploadException ex) {
                logger.info("ERRORE : " + ex);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "An error occurred creating the file: "
                        + ex.getMessage());
                throw new GeoPlatformException("Error on uploading shape.");
            } finally {
                uploadedFile.delete();
                resp.getWriter().close();
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                    "Request contents type is not supported by the servlet.");
        }
    }

    private List<InfoPreview> manageUploadedFilePreview(File uploadedFile, String sessionID, String username, String workspace) {
        List<InfoPreview> previewList = null;
        String extension = uploadedFile.getAbsolutePath().substring(
                uploadedFile.getAbsolutePath().lastIndexOf(".") + 1,
                uploadedFile.getAbsolutePath().lastIndexOf(".") + 4);
        logger.info("Extension: " + extension);
        if (extension.equalsIgnoreCase(GPExtensions.ZIP.toString())) {
            try {
                previewList = this.gpPublisherUploader.analyzeZIPEPSG(sessionID, username, uploadedFile, workspace).getInfoPreviews();
            } catch (ResourceNotFoundFault ex) {
                logger.error("Error on uploading shape: " + ex);
                throw new GeoPlatformException("Error on uploading shape.");
            }
        } else if (extension.equalsIgnoreCase(GPExtensions.TIF.toString()) || extension.equalsIgnoreCase(GPExtensions.TIFF.toString())) {
            try {
                previewList = Lists.<InfoPreview>newArrayList();
                previewList.add(this.gpPublisherUploader.analyzeTIFInPreview(username, uploadedFile, true, workspace));
            } catch (ResourceNotFoundFault ex) {
                logger.error("Error on uploading shape: " + ex);
                throw new GeoPlatformException("Error on uploading shape.");
            }
        }
        return previewList;
    }
}