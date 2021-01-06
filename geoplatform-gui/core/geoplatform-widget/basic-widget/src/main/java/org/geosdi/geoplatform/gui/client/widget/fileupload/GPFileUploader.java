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
import com.google.gwt.user.client.ui.Hidden;
import org.geosdi.geoplatform.gui.client.event.AbstractUploadEvent;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.progressbar.GeoPlatformProgressBar;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

import java.util.logging.Logger;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPFileUploader {

    protected final static Logger logger = Logger.getLogger("");

    private FormPanel formPanel = new FormPanel();
    private FileUpload fileUpload;
    private Button buttonSubmit;
    private String htmlResult;
    private final AbstractUploadEvent uploadEvent;
    private UploaderProgressBar uploaderProgressBar;
    private VerticalPanel verticalPanel;
    private Hidden queryParameter;

    public GPFileUploader(String uploadAction, AbstractUploadEvent uploadEvent,
            GPExtensions... extensions) {
        this.createUploadComponent(uploadAction, extensions);
        this.uploadEvent = uploadEvent;
    }

    public FormPanel getComponent() {
        return this.formPanel;
    }

    private void createUploadComponent(String uploadAction,
            final GPExtensions... extensions) {
        uploaderProgressBar = new UploaderProgressBar();
        formPanel.setAction(GWT.getModuleBaseURL() + uploadAction);
        formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
        formPanel.setMethod(FormPanel.METHOD_POST);

        this.verticalPanel = new VerticalPanel();

        this.queryParameter = new Hidden();

        this.verticalPanel.add(queryParameter);

        formPanel.setWidget(verticalPanel);

        fileUpload = new FileUpload();
        fileUpload.setName("uploadFormElement");
        verticalPanel.add(fileUpload);

        buttonSubmit = new Button(ButtonsConstants.INSTANCE.submitText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        uploaderProgressBar.show("Uploading...");
                        formPanel.submit();
                        if ((fileUpload.getFilename() != null)
                        && isValidExtensions(fileUpload.getFilename(),
                                extensions)) {
                            LayoutManager.getInstance().getStatusMap().setBusy(
                                    BasicWidgetConstants.INSTANCE.GPFileUploader_uploadInProgressText());
                        }
                    }

                });
        verticalPanel.add(buttonSubmit);

        // Add an event handler to the form.
        formPanel.addSubmitHandler(new FormPanel.SubmitHandler() {

            @Override
            public void onSubmit(SubmitEvent event) {
                // This event is fired just before the form is submitted. We can
                // take this opportunity to perform validation
                if (!isValidExtensions(fileUpload.getFilename(), extensions)) {
                    uploaderProgressBar.hide();
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            BasicWidgetConstants.INSTANCE.GPFileUploader_failedStatusText(),
                            EnumSearchStatus.STATUS_NO_SEARCH.toString());
                    GeoPlatformMessage.errorMessage(
                            BasicWidgetConstants.INSTANCE.GPFileUploader_failedErrorMessageTitleText(),
                            BasicWidgetConstants.INSTANCE.GPFileUploader_failedErrorKindFileBodyText());
                    event.cancel();
                    formPanel.reset();
                }
            }

        });

        formPanel.addSubmitCompleteHandler(
                new FormPanel.SubmitCompleteHandler() {

                    @Override
                    public void onSubmitComplete(SubmitCompleteEvent event) {
                        // When the form submission is successfully completed,
                        // this event is fired. Assuming the service returned a
                        // response of type text/html, we can get the result text here 
                        // (see the FormPanel documentation for further explanation)
                        htmlResult = event.getResults();
                        System.out.println("HTML Result: " + htmlResult);
                        //Execute this code only if the session is still alive
                        if (htmlResult.contains("Session Timeout")) {
                            GPHandlerManager.fireEvent(new GPLoginEvent(null));
                        } else if (!htmlResult.contains("HTTP ERROR")) {
                            formPanel.reset();
                            htmlResult = htmlResult.replaceAll("<pre>", "");
                            htmlResult = htmlResult.replaceAll("</pre>", "");
                            htmlResult = htmlResult.replaceAll(
                                    "<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">",
                                    "");
                            if (GPSharedUtils.isNotEmpty(htmlResult)) {
//                                logger.info("HTMLResult: " + htmlResult);
                                uploadEvent.setResult(htmlResult);
                                GPHandlerManager.fireEvent(uploadEvent);
                                //done.enable();
                                //mapPreviewWidget.drawAoiOnMap(wkt);
                                LayoutManager.getInstance().getStatusMap().setStatus(
                                        BasicWidgetConstants.INSTANCE.GPFileUploader_successStatusText(),
                                        EnumSearchStatus.STATUS_SEARCH.toString());
                            } else {
                                LayoutManager.getInstance().getStatusMap().setStatus(
                                        BasicWidgetConstants.INSTANCE.GPFileUploader_failedStatusText(),
                                        EnumSearchStatus.STATUS_NO_SEARCH.toString());
                            }
                        } else {
                            GeoPlatformMessage.errorMessage(
                                    BasicWidgetConstants.INSTANCE.GPFileUploader_failedErrorMessageTitleText(),
                                    BasicWidgetConstants.INSTANCE.GPFileUploader_failedErrorGenericBodyText());
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    BasicWidgetConstants.INSTANCE.GPFileUploader_failedStatusText(),
                                    EnumSearchStatus.STATUS_NO_SEARCH.toString());
                        }
                        uploaderProgressBar.hide();
                    }

                });
    }

    public void addParamToServletURL(String key, String value) {
        this.queryParameter.setName(key);
        this.queryParameter.setValue(value);
    }

    public boolean isValidExtensions(String fileName, GPExtensions... extensions) {
        for (GPExtensions gPExtensions : extensions) {
            if (fileName.toUpperCase().endsWith("." + gPExtensions.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the buttonSubmit
     */
    public Button getButtonSubmit() {
        return buttonSubmit;
    }

    class UploaderProgressBar extends GeoPlatformProgressBar {

        protected UploaderProgressBar() {
            super(BasicWidgetConstants.INSTANCE.GPFileUploader_progressBarText(),
                    Boolean.TRUE);
        }

    }

}
