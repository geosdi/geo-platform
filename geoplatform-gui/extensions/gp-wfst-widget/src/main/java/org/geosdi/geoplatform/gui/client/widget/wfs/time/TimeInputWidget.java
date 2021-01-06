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
package org.geosdi.geoplatform.gui.client.widget.wfs.time;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.DateSelectedEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class TimeInputWidget extends GeoPlatformWindow {

    private final static String DATE_KEY_VALUE = "Date";
    private final static String TIME_KEY_VALUE = "Time";
    //
    private FormData formData;
    private LayoutContainer timeContainer;
    private GPEventBus bus;
    private DateSelectedEvent dateSelectedEvent;

    /**
     * @param theBus
     */
    public TimeInputWidget(GPEventBus theBus) {
        super(TRUE);
        checkArgument(theBus != null, "The Parameter bus must not be null.");
        this.bus = theBus;
        this.dateSelectedEvent = new DateSelectedEvent();
    }

    @Override
    public void addComponent() {
        final Button insertButton = new Button("Insert", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent be) {
                StringBuilder timeBuilder = new StringBuilder();
                if (timeContainer.isVisible()) {
                    DateField dateField = timeContainer.getData(DATE_KEY_VALUE);
                    TimeField timeField = timeContainer.getData(TIME_KEY_VALUE);
                    timeBuilder.append(DateTimeFormat.getFormat("yyyy-MM-dd").format(dateField.getValue()));
                    timeBuilder.append("T");
                    timeBuilder.append(timeField.getValue().getText());
                    timeBuilder.append(":00Z");
                }
                dateSelectedEvent.setDate(timeBuilder.toString());
                bus.fireEventFromSource(dateSelectedEvent, TimeInputWidget.this);
                TimeInputWidget.super.hide();
            }
        });
        addButton(insertButton);
        formData = new FormData("98%");
        timeContainer = new LayoutContainer(new FormLayout());
        this.addDateAndTimeToContainer(timeContainer, DATE_KEY_VALUE, TIME_KEY_VALUE);
        add(new Label("Please, select the required parameters."));
        add(timeContainer, formData);
    }

    @Override
    public void initSize() {
        super.setSize("340", "140");
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml("Time Filter Composition");
        super.setLayout(new FormLayout());
        super.setModal(true);
    }

    private void addDateAndTimeToContainer(LayoutContainer layoutContainer, String dataLabel, String timeLabel) {
        final DateField dateField = new DateField();
        dateField.setFieldLabel(dataLabel);
        dateField.setData("text", "Enter the date");
        layoutContainer.setData(dataLabel, dateField);
        final TimeField timeField = new TimeField();
        timeField.setFieldLabel(timeLabel);
        timeField.setData("text", "Enter the time");
        layoutContainer.setData(timeLabel, timeField);
        layoutContainer.add(dateField);
        layoutContainer.add(timeField);
    }
}