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
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.config.annotation.FeatureAttributeConditionFieldList;
import org.geosdi.geoplatform.gui.client.config.annotation.MatchComboField;
import org.geosdi.geoplatform.gui.client.config.annotation.QueryFeatureButton;
import org.geosdi.geoplatform.gui.client.config.annotation.SelectFeaturesButton;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureMaskAttributesEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.FeatureSelectionWidgetHandler;
import org.geosdi.geoplatform.gui.client.util.FeatureConverter;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureSelectionWidget extends GeoPlatformContentPanel implements IFeatureSelectionWidget {

    public static final String ID = WFSWidgetNames.FEATURE_SELECTION.name();
    //
    private final GPEventBus bus;
    private final List<FeatureAttributeConditionField> attributeConditions;
    private final SimpleComboBox<String> matchComboField;
    private final Button selectFeaturesButton;
    private final Button queryFeatureButton;
    //
    @Inject
    private ILayerSchemaBinder layerSchemaBinder;
    private List<AttributeDetail> attributes;
    //
    private FormPanel formPanel;
    private FieldSet matchResultSet;
    private Button addConditionButton;
    private Button resetConditionsButton;

    @Inject
    public FeatureSelectionWidget(GPEventBus theBus, @SelectFeaturesButton Button theSelectFeaturesButton,
            @QueryFeatureButton Button theQueryFeatureButton, @FeatureAttributeConditionFieldList List theAttributeConditions,
            @MatchComboField SimpleComboBox theMatchComboField) {
        super(TRUE);
        checkArgument(theBus != null, "The Parameter bus must not be null.");
        checkArgument(theSelectFeaturesButton != null, "The Parameter selectFeaturesButton must not be null.");
        checkArgument(theQueryFeatureButton != null, "The Parameter queryFeatureButton must not be null.");
        checkArgument(theAttributeConditions != null, "The Parameter attributeConditions must not be null.");
        checkArgument(theMatchComboField != null, "The Parameter matchComboField must not be null.");
        this.bus = theBus;
        this.selectFeaturesButton = theSelectFeaturesButton;
        this.queryFeatureButton = theQueryFeatureButton;
        this.attributeConditions = theAttributeConditions;
        this.matchComboField = theMatchComboField;
        bus.addHandler(FeatureSelectionWidgetHandler.TYPE, this);
    }

    @Override
    public void reconfigureAttributes() {
        this.attributes = FeatureConverter.convertDTOs(this.layerSchemaBinder.getLayerSchemaDTO().getAttributes());
        for (FeatureAttributeConditionField attCondition : attributeConditions) {
            matchResultSet.remove(attCondition);
        }
        this.attributeConditions.clear();
    }

    @Override
    public void addComponent() {
        this.createFormPanel();
        this.createMatchSelection();
        this.createSelectionButtons();
        this.createQueryButtons();
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.setId(ID);
        super.head.setText("Feature Selection");
        super.setBorders(false);
        super.setScrollMode(Style.Scroll.AUTO);
        super.setAnimCollapse(Boolean.FALSE);
    }

    @Override
    public void queryEnabled(boolean enabled) {
        selectFeaturesButton.setEnabled(enabled);
        queryFeatureButton.setEnabled(enabled);
        bus.fireEvent(new FeatureMaskAttributesEvent(!enabled));
    }

    @Override
    public void deleteCondition(FeatureAttributeConditionField field) {
        attributeConditions.remove(field);
        matchResultSet.remove(field);
    }

    private void createFormPanel() {
        this.formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setBorders(false);
        formPanel.setBodyBorder(false);
        formPanel.setLayout(new FlowLayout());
        super.add(formPanel);
    }

    private void createMatchSelection() {
        matchResultSet = new FieldSet();
        matchResultSet.setHeadingHtml("Select by condition");
        matchResultSet.setCheckboxToggle(true);
        matchResultSet.setExpanded(true);
        MultiField multiMatchField = new MultiField();
        multiMatchField.add(new LabelField("Match" + "&nbsp;"));
        multiMatchField.add(matchComboField);
        matchResultSet.add(multiMatchField, new VBoxLayoutData(0, 0, 5, 0));
        matchResultSet.addListener(Events.Collapse, new Listener<FieldSetEvent>() {

            @Override
            public void handleEvent(FieldSetEvent be) {
                selectionEnabled(false);
            }

        });
        matchResultSet.addListener(Events.Expand, new Listener<FieldSetEvent>() {

            @Override
            public void handleEvent(FieldSetEvent be) {
                selectionEnabled(true);
            }

        });
        formPanel.add(matchResultSet);
    }

    private void createSelectionButtons() {
        formPanel.setButtonAlign(Style.HorizontalAlignment.LEFT);
        this.addConditionButton = new Button("Add Condition",
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()), new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                FeatureAttributeConditionField attributeCondition = new FeatureAttributeConditionField(bus, attributes);
                attributeConditions.add(attributeCondition);

                matchResultSet.add(attributeCondition, new VBoxLayoutData(0, 0, 1, 0));
                matchResultSet.layout();

                int vScrollPosition = FeatureSelectionWidget.super.getVScrollPosition();
                FeatureSelectionWidget.super.setVScrollPosition(vScrollPosition + 30);
            }

        });
        formPanel.addButton(addConditionButton);
        this.resetConditionsButton = new Button("Reset Conditions", AbstractImagePrototype.create(BasicWidgetResources.ICONS.delete()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        for (FeatureAttributeConditionField attCondition : attributeConditions) {
                            matchResultSet.remove(attCondition);
                        }
                        attributeConditions.clear();
                    }

                });
        formPanel.addButton(resetConditionsButton);
    }

    private void createQueryButtons() {
        super.setButtonAlign(Style.HorizontalAlignment.RIGHT);
        super.addButton(this.selectFeaturesButton);
        super.addButton(queryFeatureButton);
    }

    private void selectionEnabled(boolean enabled) {
        addConditionButton.setVisible(enabled);
        resetConditionsButton.setVisible(enabled);
        queryFeatureButton.setEnabled(enabled);
    }
}