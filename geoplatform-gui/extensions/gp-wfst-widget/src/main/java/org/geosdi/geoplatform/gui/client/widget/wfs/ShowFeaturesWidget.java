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
package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.SplitBar;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.GetAllFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.GetAllFeatureResponse;
import org.geosdi.geoplatform.gui.client.config.annotation.GetAllFeaturesButton;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetMessages;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureDetail;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.ShowFeatureAttributesHandler;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class ShowFeaturesWidget extends GeoPlatformWindow implements IFeatureWidget, ShowFeatureAttributesHandler {

    private final GPEventBus bus;
    private final GetAllFeatureRequest getAllFeatureRequest = GWT.<GetAllFeatureRequest>create(GetAllFeatureRequest.class);
    @Inject
    private FeatureStatusBar statusBar;
    @Inject
    private ShowFeatureAttributesWidget attributesWidget;
    private ILayerSchemaBinder layerSchemaBinder;
    private int size = 50;
    private WFSTWidgetMessages i18n;
    private Button getAllFeatureButton;

    /**
     * @param theBus
     * @param theI18n
     * @param theLayerSchemaBinder
     * @param theGetAllFeatureButton
     */
    @Inject
    public ShowFeaturesWidget(GPEventBus theBus, WFSTWidgetMessages theI18n, ILayerSchemaBinder theLayerSchemaBinder,
            @GetAllFeaturesButton Button theGetAllFeatureButton) {
        super(true);
        this.i18n = theI18n;
        this.layerSchemaBinder = theLayerSchemaBinder;
        this.getAllFeatureButton = theGetAllFeatureButton;
        this.bus = theBus;
        this.bus.addHandler(TYPE, this);
    }

    @Override
    public void addComponent() {
        this.addAttributesWidget();
        this.createStatusBar();
    }

    @Override
    public void initSize() {
        super.setSize(1000, 400);
        super.setPosition(super.getParent().getOffsetWidth()-1000, super.getParent().getOffsetHeight()- 400);
        super.setHeadingHtml(i18n.labelShowFeature(size));
        super.setIcon(AbstractImagePrototype.create(BasicWidgetResources.ICONS.vector()));
    }

    @Override
    public void setWindowProperties() {
        super.setCollapsible(FALSE);
        super.setResizable(TRUE);
        super.setMaximizable(TRUE);
        super.setModal(FALSE);
        super.setPlain(TRUE);
        super.setLayout(new BorderLayout());
    }

    private void addAttributesWidget() {
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.CENTER, 150);
        layoutData.setMargins(new Margins(5, 5, 5, 5));
        layoutData.setCollapsible(true);
        layoutData.setSplit(true);
        layoutData.setMinSize(150);
        layoutData.setMaxSize(380);
        attributesWidget.setHeaderVisible(true);
        attributesWidget.getHeader().setStyleAttribute("textAlign", "center");
        super.add(this.attributesWidget, layoutData);
    }

    @Override
    public void reset() {
        this.attributesWidget.reset();
        this.statusBar.reset();
    }

    @Override
    public void show() {
        if ((this.layerSchemaBinder.getSelectedLayer() == null) || (this.layerSchemaBinder.getLayerSchemaDTO() == null)) {
            throw new IllegalArgumentException("Both SchemaDTO and GPLayerBean must not be null");
        }
        this.size = 50;
        super.show();
        loadFeatures();
    }

    private void createStatusBar() {
        super.setButtonAlign(Style.HorizontalAlignment.LEFT);
        super.getButtonBar().add(this.statusBar);
        super.getButtonBar().add(new FillToolItem());
        super.addButton(getAllFeatureButton);
    }

    @Override
    protected void afterShow() {
        super.afterShow();
        this.statusBar.setBusy("Loading Features");
        this.attributesWidget.reconfigureEditorGrid();
        super.setZIndex(100000000);
    }

    @Override
    protected void endDrag(DragEvent de, boolean canceled) {
        super.endDrag(de, canceled);
    }


    @Override
    public void manageWidgetsSize() {
        SplitBar bar = attributesWidget.getData("splitBar");
        if (bar != null) {
            this.attributesWidget.manageGridHeight();
        }
    }

    private void loadFeatures() {
        this.getAllFeatureButton.setEnabled(false);
        attributesWidget.maskAttributes(true);
        getAllFeatureRequest.setServerUrl(layerSchemaBinder.getLayerSchemaDTO().getScope());
        getAllFeatureRequest.setTypeName(layerSchemaBinder.getLayerSchemaDTO().getTypeName());
        getAllFeatureRequest.setMaxFeatures(size);

        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<GetAllFeatureResponse>() {

            private static final long serialVersionUID = 9028489214099941178L;

            {
                super.setCommandRequest(getAllFeatureRequest);
            }

            @Override
            public void onCommandSuccess(GetAllFeatureResponse response) {
                if (!response.getResult().isFeaturesLoaded()) {
                    String errorMessage = "Error on WFS GetFeature request";
                    GeoPlatformMessage.errorMessage("GetFeture Service Error", errorMessage + " - " + response.getResult().getErrorMessage());
                    LayoutManager.getInstance().getStatusMap().setStatus(errorMessage + " for " + layerSchemaBinder.getLayerSchemaDTO().getTypeName() + " layer.", SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                } else {
                    List<FeatureDetail> instances = Lists.<FeatureDetail>newArrayListWithCapacity(response.getResult().getFeatures().size());
                    for (FeatureDTO feature : GPSharedUtils.safeList(response.getResult().getFeatures())) {
                        Map<String, String> attributes = feature.getAttributes().getAttributesMap();
                        FeatureDetail featureDetail = new FeatureDetail(attributes, feature);
                        instances.add(featureDetail);
                    }

                    if (instances == null || instances.isEmpty()) {
                        attributesWidget.resetInstances();
                    } else {
                        attributesWidget.postInstances(instances);
                    }
                    getAllFeatureButton.setEnabled(true);

                }
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                String errorMessage = "Error on WFS GetFeature request";
                GeoPlatformMessage.errorMessage("GetFeture Service Error",
                        errorMessage + " - " + exception.getMessage());

                LayoutManager.getInstance().getStatusMap().setStatus(
                        errorMessage + " for " + layerSchemaBinder.getLayerSchemaDTO().getTypeName() +
                                " layer.", SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
            }

        });
    }

    @Override
    public void getAllFeatures() {
        reset();
        size = 1000000;
        loadFeatures();
    }
}