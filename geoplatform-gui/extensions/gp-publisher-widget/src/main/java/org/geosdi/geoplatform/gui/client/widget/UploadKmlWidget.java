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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.event.kml.UploadKmlEvent;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.service.PublisherRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPFileUploader;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;

/**
 * TODO This widget is not used because the relative action is not registered
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class UploadKmlWidget extends GeoPlatformWindow {

    private TreePanel tree;
    private FieldSet fieldSet;
    private GPFileUploader fileUploader;
    private Button buttonAdd;
    private UploadKmlEvent uploadKMLEvent = new UploadKmlEvent();

    public UploadKmlWidget(boolean lazy, TreePanel theTree) {
        super(lazy);
        this.tree = theTree;
    }

    @Override
    public void show() {
        if (!super.isInitialized()) {
            super.init();
        }
        super.show();
    }

    @Override
    public void initSize() {
        super.setSize(330, 170);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(PublisherWidgetConstants.INSTANCE.UploadKmlWidget_headingText());
        super.setResizable(false);
    }

    @Override
    public void reset() {
        fileUploader.getComponent().reset();
        buttonAdd.disable();
    }

    @Override
    public void addComponent() {
        fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(PublisherWidgetConstants.INSTANCE.
                UploadKmlWidget_fieldSetHeadingText());

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        fieldSet.setLayout(layout);
        this.add(fieldSet);

        fileUploader = new GPFileUploader("UploadKml", this.uploadKMLEvent, GPExtensions.KML);
        fileUploader.getButtonSubmit().setVisible(false);
        fieldSet.add(fileUploader.getComponent());

        this.addFooterButton();
    }

    private void addFooterButton() {
        buttonAdd = new Button(ButtonsConstants.INSTANCE.addText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()));
        buttonAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (tree.getSelectionModel().getSelectedItem() instanceof AbstractFolderTreeNode) {
                    //expander.checkNodeState();
                    List<String> layersName = Lists.<String>newArrayList();
//                    for (PreviewLayer layer : layerList) {
//                        layersName.add(layer.getName());
//                    }
                    PublisherRemote.Util.getInstance().publishLayerPreview(layersName, null, new AsyncCallback<String>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            if (caught.getCause() instanceof GPSessionTimeout) {
//                                GPHandlerManager.fireEvent(new GPLoginEvent(publishShapePreviewEvent));
                            } else {
                                GeoPlatformMessage.errorMessage(
                                        PublisherWidgetConstants.INSTANCE.errorPublishingText(),
                                        WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                                LayoutManager.getInstance().getStatusMap().setStatus(
                                        PublisherWidgetConstants.INSTANCE.
                                        statusErrorShapePublishingText(),
                                        EnumSearchStatus.STATUS_NO_SEARCH.toString());
                                System.out.println("Error Publishing previewed shape: " + caught.toString()
                                        + " data: " + caught.getMessage());
                            }
                        }

                        @Override
                        public void onSuccess(String result) {
//                            LayerHandlerManager.fireEvent(new AddRasterFromPublisherEvent(layerList));
                            reset();
//                            LayoutManager.getInstance().getStatusMap().setStatus(
//                                    "Shape\\s published successfully: remember to save the new tree state.",
//                                    EnumSearchStatus.STATUS_SEARCH.toString());
                        }
                    });
                } else {
                    GeoPlatformMessage.alertMessage(PublisherWidgetConstants.INSTANCE.
                            UploadKmlWidget_headingText(),
                            WindowsConstants.INSTANCE.warningLayerInToFolderText());
                }
            }
        });
        buttonAdd.disable();
        super.addButton(this.buttonAdd);
        Button resetButton = new Button(ButtonsConstants.INSTANCE.resetText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()));
        resetButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                reset();
            }
        });
        super.addButton(resetButton);
    }
}
