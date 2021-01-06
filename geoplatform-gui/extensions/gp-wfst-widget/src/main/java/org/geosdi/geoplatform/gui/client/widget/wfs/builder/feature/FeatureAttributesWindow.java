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
package org.geosdi.geoplatform.gui.client.widget.wfs.builder.feature;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.puregwt.map.dispatcher.insert.InsertFeatureDispatcherEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.edit.WFSEdit;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.dispatcher.WFSFeatureDispatcher;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 * @email giuseppe.lascaleia@geosdi.org
 */
class FeatureAttributesWindow extends GeoPlatformWindow implements
        WfsAttributesWindow {

    private static final InsertFeatureDispatcherEvent insertFeatureEvent = WFSFeatureDispatcher.INSERT_FEATURE_EVENT;
    //
    private final ILayerSchemaBinder layerSchemaBinder;
    private final GPEventBus bus;
    private final FormData formData = new FormData("-20");
    private FormPanel attributesFormPanel;
    private List<AttributeDTO> attributeDTOs;
    private List<FeatureAttributeRow> featureAttributeRowList;
    private WFSEdit editorSource;
    private boolean fieldsState = false;
    private boolean resetEditorSource = true;

    protected FeatureAttributesWindow(GPEventBus theBus,
                                      ILayerSchemaBinder theLayerSchemaBinder) {
        super(Boolean.TRUE);

        this.bus = theBus;
        this.layerSchemaBinder = theLayerSchemaBinder;
    }

    @Override
    public void reset() {
        super.reset();
        resetFields();
        if (resetEditorSource) {
            this.editorSource.resetWFSEditing();
        }
        this.resetEditorSource = true;
        this.fieldsState = true;
    }

    protected final void bindValues() {
        for (FeatureAttributeRow featureAttributeRow : GPSharedUtils.safeList(
                featureAttributeRowList)) {
            featureAttributeRow.bindAttributeValue();
        }
        this.fireInsertFeatureEvent();
    }

    final void fireInsertFeatureEvent() {
        insertFeatureEvent.setEditorSource(editorSource);
        insertFeatureEvent.setFeatureAttributes(
                Lists.<AttributeDTO>newArrayList(this.attributeDTOs));
        WFSFeatureDispatcher.fireFeatureDispatcherEvent(insertFeatureEvent);
    }

    @Override
    public void addComponent() {
        VerticalPanel vp = new VerticalPanel();

        this.attributesFormPanel = new FormPanel();
        this.attributesFormPanel.setWidth(340);
        this.attributesFormPanel.setFieldWidth(180);
        this.attributesFormPanel.setHeaderVisible(false);

        Button saveButton = new Button(ButtonsConstants.INSTANCE.saveText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.save()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        bindValues();
                    }

                });

        Button resetButton = new Button(ButtonsConstants.INSTANCE.resetText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.reset()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        resetFields();
                    }

                });

        super.addButton(saveButton);
        super.addButton(resetButton);
        super.setButtonAlign(Style.HorizontalAlignment.CENTER);

        FormButtonBinding formButtonBinding = new FormButtonBinding(
                attributesFormPanel);
        formButtonBinding.addButton(saveButton);

        vp.add(this.attributesFormPanel);

        super.add(vp);
    }

    @Override
    public void initSize() {
        super.setSize(346, 380);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(WFSTWidgetConstants.INSTANCE.
                FeatureAttributesWindow_headingText());
        super.setScrollMode(Style.Scroll.AUTOY);

        super.setModal(true);
        super.setResizable(false);
    }

    @Override
    protected void beforeRender() {
        super.beforeRender();
        this.buildFields();
        this.fieldsState = true;
    }

    @Override
    protected void afterShow() {
        if (!fieldsState) {
            cleanFormPanel();
            buildFields();
        }
        super.afterShow();
    }

    final void cleanFormPanel() {
        this.attributesFormPanel.removeAll();
        this.featureAttributeRowList.clear();
    }

    final void resetFields() {
        for (FeatureAttributeRow featureAttributeRow : GPSharedUtils.safeList(
                featureAttributeRowList)) {
            featureAttributeRow.resetValue();
        }
    }

    /**
     * <p>
     * We must use a copy of AttributeDTO List in {@link LayerSchemaDTO}
     * </p>
     */
    final void buildFields() {
        LayerSchemaDTO layerSchemaDTO = this.layerSchemaBinder.getLayerSchemaDTO();

        assert (layerSchemaDTO != null) : "The LayerSchemaDTO must not be null.";

        this.featureAttributeRowList = Lists.<FeatureAttributeRow>newArrayListWithCapacity(
                layerSchemaDTO.getAttributes().size());

        this.attributeDTOs = layerSchemaDTO.getAttributesCopy();

        for (AttributeDTO attribute : attributeDTOs) {
            FeatureAttributeRow featureAttributeRow = new FeatureAttributeRow(
                    attribute, this.bus);
            this.featureAttributeRowList.add(featureAttributeRow);
            this.attributesFormPanel.add(
                    featureAttributeRow.getConditionAttributeField(), formData);
        }

        this.reconfigureWindowHeight();
    }

    final void reconfigureWindowHeight() {
        if (this.featureAttributeRowList.size() < 10) {
            super.setHeight(0);
            super.setAutoHeight(true);
        } else {
            super.setHeight(380);
        }

        super.doLayout();
    }

    @Override
    public void bindWFSEdit(WFSEdit theEditorSource) {
        this.editorSource = theEditorSource;

        super.show();
    }

    @Override
    public void setFieldsState(boolean fieldsState) {
        this.fieldsState = fieldsState;
    }

    @Override
    public void closeWfsAttributeWindow() {
        this.resetEditorSource = false;
        super.hide();
    }

}
