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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.publish.basic.PublishLayerPreviewRequest;
import org.geosdi.geoplatform.gui.client.command.publish.basic.PublishLayerPreviewResponse;
import org.geosdi.geoplatform.gui.client.event.timeout.GPPublishShapePreviewEvent;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.PreviewLayer;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.progressbar.PublisherProgressBar;
import org.geosdi.geoplatform.gui.client.widget.tree.store.puregwt.event.AddRasterFromPublisherEvent;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.geosdi.geoplatform.gui.utility.GPReloadURLException;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPPublisherWidget extends AbstractPublisherWidget {

    private PublishLayerPreviewRequest publishLayerRequest = GWT.<PublishLayerPreviewRequest>create(PublishLayerPreviewRequest.class);
    private GPPublishShapePreviewEvent publishShapePreviewEvent = new GPPublishShapePreviewEvent();

    private final TreePanel tree;

    public GPPublisherWidget(boolean lazy, TreePanel theTree) {
        super(lazy);
        this.tree = theTree;
    }

    @Override
    public Button getFinalizePublishButton() {
        if (super.finishButton == null) {
            super.finishButton = new Button(ButtonsConstants.INSTANCE.addOnTreeText(),
                    AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()));
            super.finishButton.addSelectionListener(
                    new SelectionListener<ButtonEvent>() {
                        @Override
                        public void componentSelected(ButtonEvent ce) {
                            if (tree.getSelectionModel().getSelectedItem() instanceof AbstractFolderTreeNode) {
                                //expander.checkNodeState();
                                List<String> layersName = Lists.<String>newArrayList();
                                for (PreviewLayer layer : layerList) {
                                    layersName.add(layer.getTitle());
                                }

                                publishLayerRequest.setLayerList(layersName);
                                String workspaceName = getSelectedWorkspace();
                                if (GPSharedUtils.isEmpty(workspaceName)) {
                                    IGPAccountDetail accountDetail = Registry.get(
                                            UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
                                    workspaceName = accountDetail.getUsername();
                                }
                                logger.info("**** Selected workspace: " + workspaceName);
                                publishLayerRequest.setWorkspace(workspaceName);

                                ClientCommandDispatcher.getInstance().execute(
                                        new GPClientCommand<PublishLayerPreviewResponse>() {
                                            private static final long serialVersionUID = -7646291858544319457L;

                                            {
                                                super.setCommandRequest(publishLayerRequest);
                                            }

                                            @Override
                                            public void onCommandSuccess(
                                                    final PublishLayerPreviewResponse response) {
                                                PublisherProgressBar.getInstance().show(
                                                        PublisherWidgetConstants.INSTANCE.
                                                                EPSGTablePanel_processingDataProgressBarText());
                                                Timer t = new Timer() {
                                                    public void run() {
                                                        LayerHandlerManager.fireEvent(
                                                                new AddRasterFromPublisherEvent(
                                                                        layerList));
                                                        reset();
                                                        logger.info("Response: " + response);
                                                        LayoutManager.getInstance().getStatusMap().
                                                                setStatus(
                                                                        PublisherWidgetConstants.INSTANCE.
                                                                                GPPublisherWidget_statusShapePublishedSuccesfullyText(),
                                                                        EnumSearchStatus.STATUS_SEARCH.
                                                                                toString());
                                                        PublisherProgressBar.getInstance().hide();

                                                    }
                                                };
                                                // Schedule the timer to run once in 5 seconds.
                                                t.schedule(5000);
                                            }

                                            @Override
                                            public void onCommandFailure(Throwable exception) {
                                                if (exception.getCause() instanceof GPSessionTimeout) {
                                                    GPHandlerManager.fireEvent(new GPLoginEvent(
                                                            publishShapePreviewEvent));
                                                } else if (exception.getCause() instanceof GPReloadURLException) {
                                                    GeoPlatformMessage.errorMessage(
                                                            PublisherWidgetConstants.INSTANCE.
                                                                    GPPublisherWidget_errorReloadClusterTitleText(),
                                                            PublisherWidgetMessages.INSTANCE.
                                                                    GPPublisherWidget_errorReloadClusterBodyMessage(
                                                                            exception.getCause().getMessage()));
                                                    LayoutManager.getInstance().getStatusMap().
                                                            setStatus(PublisherWidgetConstants.INSTANCE.
                                                                            statusErrorShapePublishingText(),
                                                                    EnumSearchStatus.STATUS_NO_SEARCH.
                                                                            toString());
                                                    System.out.println(
                                                            "Error Publishing previewed shape: " + exception.
                                                                    toString()
                                                                    + " data: " + exception.getMessage());
                                                } else {
                                                    GeoPlatformMessage.errorMessage(
                                                            PublisherWidgetConstants.INSTANCE.errorPublishingText(),
                                                            WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                                                    LayoutManager.getInstance().getStatusMap().
                                                            setStatus(
                                                                    PublisherWidgetConstants.INSTANCE.
                                                                            statusErrorShapePublishingText(),
                                                                    EnumSearchStatus.STATUS_NO_SEARCH.
                                                                            toString());
                                                    System.out.println(
                                                            "Error Publishing previewed shape: " + exception.
                                                                    toString()
                                                                    + " data: " + exception.getMessage());
                                                }
                                            }
                                        });
                            } else {
                                GeoPlatformMessage.alertMessage(
                                        PublisherWidgetConstants.INSTANCE.GPPublisherWidget_shapePreviewTitleText(),
                                        WindowsConstants.INSTANCE.warningLayerInToFolderText());
                            }
                        }
                    });
            super.finishButton.disable();
        }
        return super.finishButton;
    }
}