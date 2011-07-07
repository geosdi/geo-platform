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
package org.geosdi.geoplatform.gui.client.widget.fileupload;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPFileUploader {

    private FormPanel formPanel = new FormPanel();
    private FileUpload upload;
    private String htmlResult;

    public GPFileUploader(String uploadAction, GPExtensions... extensions) {
        this.createUploadComponent(uploadAction, extensions);
    }

    public FormPanel getComponent(){
        return this.formPanel;
    }
    
    private void createUploadComponent(String uploadAction, final GPExtensions... extensions) {
        formPanel = new FormPanel();
        formPanel.setAction(GWT.getModuleBaseURL() + uploadAction);
        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);
        VerticalPanel panel = new VerticalPanel();
        formPanel.setWidget(panel);
        upload = new FileUpload();
        upload.setName("uploadFormElement");
        panel.add(upload);
        panel.add(new Button("Submit", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                formPanel.submit();
                if ((upload.getFilename() != null)
                        && isValidExtensions(upload.getFilename(), extensions)) {
                    LayoutManager.getInstance().getStatusMap().setBusy("Upload in progress...");
                }
            }
        }));

        // // Add an event handler to the form.
        formPanel.addSubmitHandler(new FormPanel.SubmitHandler() {

            @Override
            public void onSubmit(SubmitEvent event) {
                // This event is fired just before the form is submitted. We can
                // take this opportunity to perform validation.
                if (!isValidExtensions(upload.getFilename(), extensions)) {
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Failed to Upload File.", EnumSearchStatus.STATUS_NO_SEARCH.toString());
                    GeoPlatformMessage.errorMessage("Upload Error", "This kind of file isn't allowed!");
                    event.cancel();
                    formPanel.reset();
                }
            }
        });

        formPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

            @Override
            public void onSubmitComplete(SubmitCompleteEvent event) {
                // When the form submission is successfully completed,
                // this
                // event is fired. Assuming the service returned a
                // response of
                // type
                // text/html, we can get the result text here (see the
                // FormPanel
                // documentation for further explanation).
                formPanel.reset();
                htmlResult = event.getResults();
//                htmlResult = htmlResult.replaceAll("<pre>", "");
//                htmlResult = htmlResult.replaceAll("</pre>", "");
//
//                htmlResult = htmlResult.replaceAll(
//                        "<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">",
//                        "");
                if ((htmlResult != null) && !(htmlResult.equals(""))) {
                    System.out.println("HTMLResult: " + htmlResult);
                    //done.enable();
                    //mapPreviewWidget.drawAoiOnMap(wkt);
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Uploaded File Succesfully", EnumSearchStatus.STATUS_SEARCH.toString());
                } else {
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Failed to Upload File.", EnumSearchStatus.STATUS_NO_SEARCH.toString());
                }
            }
        });

    }

    private boolean isValidExtensions(String fileName, GPExtensions... extensions) {
        for (GPExtensions gPExtensions : extensions) {
            if (fileName.endsWith(gPExtensions.toString())) {
                return true;
            }
        }
        return false;
    }
}
