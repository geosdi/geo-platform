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
package org.geosdi.geoplatform.gui.client.widget.cql.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class TimeCQLButton extends AdvancedCQLButton {

    private final static String RADIO_KEY_VALUE = "keyValue";
    private final static String DATE_KEY_VALUE = "Date";
    private final static String DATE_START_KEY_VALUE = "Start Date";
    private final static String DATE_END_KEY_VALUE = "End Date";
    private final static String TIME_KEY_VALUE = "Time";
    private final static String TIME_START_KEY_VALUE = "Start Time";
    private final static String TIME_END_KEY_VALUE = "End Time";
    private FormData formData;
    private LayoutContainer timeContainer;
    private LayoutContainer periodContainer;

    public TimeCQLButton(TextArea textArea) {
        super(textArea, LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_buttonText());
        super.setTitle(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_titleText());
    }

    @Override
    protected void initialize() {
        formData = new FormData("98%");
        final RadioGroup radioGroup = new RadioGroup(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_radioGroupText());
        radioGroup.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_radioGroupFieldLabelText());
        final Radio beforeRadioButton = new Radio();
        beforeRadioButton.setData(RADIO_KEY_VALUE, "BEFORE");
        beforeRadioButton.setBoxLabel(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_beforeRadioGroupLabelText());
        beforeRadioButton.setHideLabel(Boolean.TRUE);
        final Radio afterRadioButton = new Radio();
        afterRadioButton.setData(RADIO_KEY_VALUE, "AFTER");
        afterRadioButton.setBoxLabel(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_afterRadioGroupLabelText());
        afterRadioButton.setHideLabel(Boolean.TRUE);
        final Radio duringRadioButton = new Radio();
        duringRadioButton.setData(RADIO_KEY_VALUE, "DURING");
        duringRadioButton.setBoxLabel(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_duringRadioGroupLabelText());
        duringRadioButton.setHideLabel(Boolean.TRUE);
        final Radio beforeOrDuringRadioButton = new Radio();
        beforeOrDuringRadioButton.setData(RADIO_KEY_VALUE, "BEFORE OR DURING");
        beforeOrDuringRadioButton.setBoxLabel(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_beforeOrDuringRadioGroupLabelText());
        beforeOrDuringRadioButton.setHideLabel(Boolean.TRUE);
        final Radio duringOrAfterRadioButton = new Radio();
        duringOrAfterRadioButton.setData(RADIO_KEY_VALUE, "DURING OR AFTER");
        duringOrAfterRadioButton.setBoxLabel(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_duringOrAfterRadioGroupLabelText());
        duringOrAfterRadioButton.setHideLabel(Boolean.TRUE);
        radioGroup.add(beforeRadioButton);
        radioGroup.add(beforeOrDuringRadioButton);
        radioGroup.add(duringRadioButton);
        radioGroup.add(duringOrAfterRadioButton);
        radioGroup.add(afterRadioButton);

        timeContainer = new LayoutContainer(new FormLayout());
        timeContainer.setVisible(Boolean.FALSE);
        this.addDateAndTimeToContainer(timeContainer, DATE_KEY_VALUE, TIME_KEY_VALUE);
        periodContainer = new LayoutContainer(new FormLayout());
        periodContainer.setVisible(Boolean.FALSE);
        this.addDateAndTimeToContainer(periodContainer, DATE_START_KEY_VALUE, TIME_START_KEY_VALUE);
        this.addDateAndTimeToContainer(periodContainer, DATE_END_KEY_VALUE, TIME_END_KEY_VALUE);

        Listener<FieldEvent> timeListener = new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent fe) {
                if ((Boolean) fe.getValue()) {
                    timeContainer.setVisible(true);
                    periodContainer.setVisible(false);
                }
            }
        };
        afterRadioButton.addListener(Events.Change, timeListener);
        beforeRadioButton.addListener(Events.Change, timeListener);

        Listener<FieldEvent> periodListener = new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent fe) {
                if ((Boolean) fe.getValue()) {
                    periodContainer.setVisible(true);
                    timeContainer.setVisible(false);
                }
            }
        };
        beforeOrDuringRadioButton.addListener(Events.Change, periodListener);
        duringOrAfterRadioButton.addListener(Events.Change, periodListener);
        duringRadioButton.addListener(Events.Change, periodListener);

        final Button insertButton = new Button(ButtonsConstants.INSTANCE.insertText(),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent be) {
                        String temporalPredicate = radioGroup.getValue().getData(RADIO_KEY_VALUE);
//                System.out.println("Temporal predicate: " + temporalPredicate);
                        StringBuilder timeBuilder = new StringBuilder(temporalPredicate);
                        timeBuilder.append(" ");
                        if (timeContainer.isVisible()) {
                            DateField dateField = timeContainer.getData(DATE_KEY_VALUE);
//                    System.out.println("Data: " + DateTimeFormat.getFormat("yyyy-MM-dd").format(dateField.getValue()));
                            TimeField timeField = timeContainer.getData(TIME_KEY_VALUE);
//                    System.out.println("Time: " + timeField.getValue().getText());
                            timeBuilder.append(DateTimeFormat.getFormat("yyyy-MM-dd").format(dateField.getValue()));
                            timeBuilder.append("T");
                            timeBuilder.append(timeField.getValue().getText());
                            timeBuilder.append(":00Z");
                        } else if (periodContainer.isVisible()) {
                            DateField dateStartField = periodContainer.getData(DATE_START_KEY_VALUE);
                            TimeField timeStartField = periodContainer.getData(TIME_START_KEY_VALUE);
                            timeBuilder.append(DateTimeFormat.getFormat("yyyy-MM-dd").format(dateStartField.getValue()));
                            timeBuilder.append("T");
                            timeBuilder.append(timeStartField.getValue().getText());
                            timeBuilder.append(":00Z/");
                            DateField dateEndField = periodContainer.getData(DATE_END_KEY_VALUE);
                            TimeField timeEndField = periodContainer.getData(TIME_END_KEY_VALUE);
                            timeBuilder.append(DateTimeFormat.getFormat("yyyy-MM-dd").format(dateEndField.getValue()));
                            timeBuilder.append("T");
                            timeBuilder.append(timeEndField.getValue().getText());
                            timeBuilder.append(":00Z");
                        } else {
                            return;
                        }
                        TimeCQLButton.super.insertTextIntoFilterArea(timeBuilder.toString());
                        window.hide();
                    }
                });
        super.window = new GeoPlatformWindow(true) {
            @Override
            public void addComponent() {
                add(new Label(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_windowSelectLabelText()));
                add(radioGroup, formData);
                add(timeContainer, formData);
                add(periodContainer, formData);
                add(new Label(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_windowResultLabelText()));
                addButton(insertButton);
            }

            @Override
            public void initSize() {
                super.setSize("570", "270");
            }

            @Override
            public void setWindowProperties() {
                super.setHeadingHtml(LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_windowHeadingText());
                super.setLayout(new FormLayout());
            }
        };
        super.initialized = Boolean.TRUE;
    }

    private void addDateAndTimeToContainer(LayoutContainer layoutContainer, String dataLabel,
            String timeLabel) {
        final DateField dateField = new DateField();
        dateField.setFieldLabel(dataLabel);
        dateField.setData("text", LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_dateFieldText());
        layoutContainer.setData(dataLabel, dateField);

        final TimeField timeField = new TimeField();
        timeField.setFieldLabel(timeLabel);
        timeField.setData("text", LayerFiltersModuleConstants.INSTANCE.TimeCQLButton_timeFieldText());
        layoutContainer.setData(timeLabel, timeField);

        layoutContainer.add(dateField);
        layoutContainer.add(timeField);
    }
}
