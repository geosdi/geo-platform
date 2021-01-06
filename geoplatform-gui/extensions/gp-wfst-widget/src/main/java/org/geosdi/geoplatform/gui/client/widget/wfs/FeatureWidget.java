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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.SplitBar;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.config.annotation.ResetButton;
import org.geosdi.geoplatform.gui.client.config.annotation.SaveButton;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.puregwt.action.event.AfterStrategyEditWFSActionEvent;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.GeocodingHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.ClearLayerEvent;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.ResetGeocodingWidgetEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.WFSGPHandlerManager;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.feature.FeatureAttributesWindowBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.EditingToolBarDialog;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableHandler;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.ScaleVisibleEvent;
import org.geosdi.geoplatform.gui.impl.map.event.ResetMapStoreEvent;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.gui.client.widget.wfs.builder.feature.FeatureAttributesWindowBuilder.RECONFIGURE_STATE;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureWidget extends GeoPlatformWindow implements IFeatureWidget, ActionEnableHandler {

    private final Button saveButton;
    private final Button resetButton;
    private final GPEventBus bus;
    private final ScaleVisibleEvent scaleVisibleEvent = new ScaleVisibleEvent();
    private final ClearLayerEvent clearLayerEvent = new ClearLayerEvent();
    private final ResetGeocodingWidgetEvent resetGeocodingWidgetEvent = new ResetGeocodingWidgetEvent();
    private final AfterStrategyEditWFSActionEvent afterStrategyEditWFSActionEvent = new AfterStrategyEditWFSActionEvent();
    @Inject
    private WFSAccordionWidget accordionWidget;
    @Inject
    private LayerSelectionWidget layerSelectionWidget;
    @Inject
    private FeatureMapWidget mapWidget;
    @Inject
    private FeatureAttributesWidget attributesWidget;
    @Inject
    private FeatureStatusBar statusBar;
    @Inject
    private BorderLayout layout;
    @Inject
    private EditingToolBarDialog editToolbarDialog;
    @Inject
    private ButtonBar featureWidgetBar;
    @Inject
    private ILayerSchemaBinder layerSchemaBinder;

    /**
     * @param theBus
     * @param theResetButton
     * @param theSaveButton
     */
    @Inject
    public FeatureWidget(GPEventBus theBus, @ResetButton Button theResetButton, @SaveButton Button theSaveButton) {
        super(TRUE);
        checkArgument(theBus != null, "The Parameter bus must not be null.");
        checkArgument(theResetButton != null, "The Parameter resetButton must not be null.");
        checkArgument(theSaveButton != null, "The Parameter saveButton must not be null.");
        this.bus = theBus;
        this.resetButton = theResetButton;
        this.saveButton = theSaveButton;
        bus.addHandler(ActionEnableEvent.TYPE, this);
    }

    @Override
    public void addComponent() {
        this.addSelectionWidget();
        this.addLayerSelectionWidget();
        this.addMapWidget();
        this.addAttributesWidget();
        this.createStatusBar();
        this.createEditingBar();
        super.setTopComponent(this.featureWidgetBar);
    }

    @Override
    public void initSize() {
        super.setSize(1200, 650);
        super.setMinHeight(560);
        super.setMinWidth(800);
        super.setHeadingHtml("GeoPlatform WFS-T Widget");
        super.setIcon(AbstractImagePrototype.create(BasicWidgetResources.ICONS.vector()));
    }

    @Override
    public void setWindowProperties() {
        super.setCollapsible(FALSE);
        super.setResizable(TRUE);
        super.setMaximizable(TRUE);
        super.setModal(TRUE);
        super.setPlain(TRUE);
        super.setLayout(layout);
    }

    @Override
    public void reset() {
        this.mapWidget.reset();
        this.attributesWidget.reset();
        this.statusBar.reset();
        this.scaleVisibleEvent.setActivate(TRUE);
        this.layerSelectionWidget.reset();
        MapHandlerManager.fireEvent(scaleVisibleEvent);
        WFSGPHandlerManager.fireEvent(new ResetMapStoreEvent());
        GeocodingHandlerManager.fireEvent(this.resetGeocodingWidgetEvent);
        GeocodingHandlerManager.fireEvent(this.clearLayerEvent);
    }

    @Override
    public void show() {
        if ((this.layerSchemaBinder.getSelectedLayer() == null)
                || (this.layerSchemaBinder.getLayerSchemaDTO() == null)) {
            throw new IllegalArgumentException("Both SchemaDTO and GPLayerBean must not be null");
        }
        super.show();
    }

    @Override
    protected void afterShow() {
        super.afterShow();
        this.scaleVisibleEvent.setActivate(FALSE);
        MapHandlerManager.fireEvent(scaleVisibleEvent);
        this.statusBar.setBusy("Loading Layer as WFS");
        this.accordionWidget.reconfigureAttributes();
        this.mapWidget.bindLayerSchema();
        this.bus.fireEvent(this.afterStrategyEditWFSActionEvent);
        this.attributesWidget.reconfigureEditorGrid();
        FeatureAttributesWindowBuilder.fireAttributesWindowEvent(RECONFIGURE_STATE);
    }

    @Override
    protected void endDrag(DragEvent de, boolean canceled) {
        super.endDrag(de, canceled);
        this.mapWidget.updateSize();
    }

    @Override
    public void onActionEnabled(ActionEnableEvent event) {
        if (event.isEnabled()) {
            enableButtons();
        } else {
            disableButtons();
        }
    }

    @Override
    public void manageWidgetsSize() {
        SplitBar bar = attributesWidget.getData("splitBar");
        if (bar != null) {
            this.attributesWidget.manageGridHeight();
            this.mapWidget.manageMapSize();
        }
    }

    private void addSelectionWidget() {
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.EAST, 300);
        layoutData.setMargins(new Margins(5, 5, 5, 5));
        layoutData.setCollapsible(true);
        super.add(this.accordionWidget, layoutData);
    }

    private void addLayerSelectionWidget() {
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.WEST, 200);
        layoutData.setMargins(new Margins(5, 5, 5, 5));
        layoutData.setCollapsible(true);
        super.add(this.layerSelectionWidget, layoutData);
    }

    private void addMapWidget() {
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.CENTER, 700);
        layoutData.setMargins(new Margins(5));
        super.add(this.mapWidget, layoutData);
    }

    private void addAttributesWidget() {
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.SOUTH, 150);
        layoutData.setMargins(new Margins(5, 5, 5, 5));
        layoutData.setCollapsible(true);
        layoutData.setSplit(true);
        layoutData.setMinSize(150);
        layoutData.setMaxSize(500);
        attributesWidget.setHeaderVisible(true);
        attributesWidget.getHeader().setText("Feature Attributes");
        attributesWidget.getHeader().setStyleAttribute("textAlign", "center");
        super.add(this.attributesWidget, layoutData);
    }

    private void createStatusBar() {
        super.setButtonAlign(Style.HorizontalAlignment.LEFT);
        super.getButtonBar().add(this.statusBar);
        super.getButtonBar().add(new FillToolItem());

        super.addButton(resetButton);
        super.addButton(saveButton);

        this.disableButtons();

        Button close = new Button(ButtonsConstants.INSTANCE.closeText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        hide();
                    }

                });
        close.setEnabled(super.isClosable());
        super.addButton(close);
    }

    private void createEditingBar() {
        super.add(editToolbarDialog);
    }

    private void disableButtons() {
        resetButton.disable();
        saveButton.disable();
    }

    private void enableButtons() {
        resetButton.enable();
        saveButton.enable();
    }
}