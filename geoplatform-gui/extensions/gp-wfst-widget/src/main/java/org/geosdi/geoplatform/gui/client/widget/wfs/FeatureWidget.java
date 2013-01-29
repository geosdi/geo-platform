/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail;
import org.geosdi.geoplatform.gui.client.util.FeatureConverter;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.wfs.event.FeatureResetAttributesEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.event.FeatureSaveAttributesEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableHandler;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Singleton
public class FeatureWidget extends GeoPlatformWindow
        implements IFeatureWidget, ActionEnableHandler {

    private FeatureSelectionWidget selectionWidget;
    private FeatureMapWidget mapWidget;
    private FeatureAttributesWidget attributesWidget;
    private FeatureStatusBar statusBar;
    private Button saveButton;
    private Button resetButton;
    //
    private GPLayerBean selectedLayer;
    private LayerSchemaDTO schemaDTO;
    //
    private GPEventBus bus;
    private FeatureSaveAttributesEvent saveEvent = new FeatureSaveAttributesEvent();
    private FeatureResetAttributesEvent resetEvent = new FeatureResetAttributesEvent();

    @Inject
    public FeatureWidget(
            FeatureSelectionWidget selectionWidget,
            FeatureMapWidget mapWidget,
            FeatureAttributesWidget attributesWidget,
            FeatureStatusBar statusBar,
            GPEventBus bus) {
        super(true);
        this.selectionWidget = selectionWidget;
        this.mapWidget = mapWidget;
        this.attributesWidget = attributesWidget;
        this.statusBar = statusBar;
        this.bus = bus;
        bus.addHandler(ActionEnableEvent.TYPE, this);
    }

    @Override
    public void addComponent() {
        this.addSelectionWidget();
        this.addMapWidget();
        this.addAttributesWidget();
        this.createStatusBar();
    }

    @Override
    public void initSize() {
        super.setSize(1000, 650);
        super.setHeading("GeoPlatform WFS-T Widget");
        super.setIcon(BasicWidgetResources.ICONS.vector());
    }

    @Override
    public void setWindowProperties() {
        super.setResizable(false);
        super.setModal(true);
        super.setCollapsible(false);
        super.setPlain(true);

        super.setLayout(new BorderLayout());
    }

    private void addSelectionWidget() {
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.WEST, 300);
        layoutData.setMargins(new Margins(0, 5, 0, 0));
        layoutData.setCollapsible(true);

        super.add(this.selectionWidget, layoutData);
    }

    private void addMapWidget() {
        // The notifyShow method is called 1 times at the first show 
        // only in the center region, otherwise 2 times.        
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.CENTER, 700);
        layoutData.setMargins(new Margins(0));

        super.add(this.mapWidget, layoutData);
    }

    private void addAttributesWidget() {
        BorderLayoutData layoutData = new BorderLayoutData(LayoutRegion.SOUTH);
        layoutData.setMargins(new Margins(5, 0, 0, 0));
        layoutData.setFloatable(true);
        layoutData.setCollapsible(true);

        super.add(this.attributesWidget, layoutData);
    }

    private void createStatusBar() {
        super.setButtonAlign(Style.HorizontalAlignment.LEFT);

        super.getButtonBar().add(this.statusBar);
        super.getButtonBar().add(new FillToolItem());

        resetButton = new Button("Reset", BasicWidgetResources.ICONS.delete(),
                                 new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                bus.fireEvent(resetEvent);
            }
        });
        super.addButton(resetButton);

        this.saveButton = new Button("Save", BasicWidgetResources.ICONS.save(),
                                     new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                bus.fireEvent(saveEvent);
            }
        });
        super.addButton(saveButton);

        this.disableButtons();

        Button close = new Button("Close", BasicWidgetResources.ICONS.cancel(),
                                  new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });
        super.addButton(close);
    }

    @Override
    public void reset() {
        this.mapWidget.reset();
        this.attributesWidget.reset();
        this.statusBar.reset();
    }

    @Override
    public void show() {
        if ((this.selectedLayer == null) || (this.schemaDTO == null)) {
            throw new IllegalArgumentException(
                    "Both SchemaDTO and GPLayerBean must not be null");
        }

        super.show();
    }

    @Override
    protected void afterShow() {
        super.afterShow();

        this.statusBar.setBusy("Loading Layer as WFS");

        this.mapWidget.bind(selectedLayer, schemaDTO);
    }

    @Override
    protected void endDrag(DragEvent de, boolean canceled) {
        super.endDrag(de, canceled);

        this.mapWidget.updateSize();
    }

    @Override
    public void bind(GPLayerBean theSelectedLayer, LayerSchemaDTO theSchemaDTO) {
        this.selectedLayer = theSelectedLayer;
        this.schemaDTO = theSchemaDTO;

        if (this.selectedLayer instanceof GPVectorBean) {
            GPVectorBean vector = (GPVectorBean) this.selectedLayer;
            vector.setFeatureNameSpace(this.schemaDTO.getTargetNamespace());
            vector.setGeometryName(this.schemaDTO.getGeometry().getName());
        }

        List<AttributeDetail> attributes = FeatureConverter.convertDTOs(
                this.schemaDTO.getAttributes());

        this.attributesWidget.setAttributes(attributes);
        this.selectionWidget.setAttributes(attributes);
    }

    @Override
    public void onActionEnabled(ActionEnableEvent event) {
        if (event.isEnabled()) {
            enableButtons();
        } else {
            disableButtons();
        }
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
