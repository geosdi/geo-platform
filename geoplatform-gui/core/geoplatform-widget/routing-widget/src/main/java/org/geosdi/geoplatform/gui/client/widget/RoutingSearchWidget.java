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

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.FindDirectionsByCoordRequest;
import org.geosdi.geoplatform.gui.client.command.FindDirectionsByCoordResponse;
import org.geosdi.geoplatform.gui.client.i18n.RoutingModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.RoutingBean;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.IGeoPlatformLocation;
import org.geosdi.geoplatform.gui.puregwt.routing.HasCleanEvent;
import org.geosdi.geoplatform.gui.puregwt.routing.RoutingHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.routing.event.CleanComboEventHandler;
import org.geosdi.geoplatform.gui.puregwt.routing.event.TraceRoutingLineEvent;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class RoutingSearchWidget implements HasCleanEvent {

    private RoutingGridWidget gridWidget;
    private FieldSet fieldSet;
    private StartPointSearchRouting startPoint;
    private FinalPointSearchWidget finalPoint;
    private Button traceRoute;
    private Button clear;
    private TraceRoutingLineEvent event;
    private FindDirectionsByCoordRequest request = new FindDirectionsByCoordRequest();

    public RoutingSearchWidget() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setCollapsible(false);
        this.event = new TraceRoutingLineEvent();
        initWidgets();
    }

    /**
     * @param controller
     */
    private void initWidgets() {
        this.startPoint = new StartPointSearchRouting();
        this.finalPoint = new FinalPointSearchWidget();

        addCleanEventHandler(this.startPoint, this.startPoint.getComboBox());
        addCleanEventHandler(this.finalPoint, this.finalPoint.getComboBox());

        HorizontalPanel panel = new HorizontalPanel();
        panel.setHorizontalAlign(HorizontalAlignment.CENTER);

        Image img = new Image();
        img.setUrl(GWT.getModuleBaseURL() + "/gp-images/start.png");
        img.setPixelSize(20, 30);

        panel.add(img);
        panel.add(this.startPoint.getTableWidget());

        fieldSet.add(panel);

        HorizontalPanel panel1 = new HorizontalPanel();
        panel1.setHorizontalAlign(HorizontalAlignment.CENTER);

        Image img1 = new Image();
        img1.setUrl(GWT.getModuleBaseURL() + "/gp-images/end.png");
        img1.setPixelSize(20, 30);

        panel1.add(img1);
        panel1.add(this.finalPoint.getTableWidget());

        fieldSet.add(panel1);

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setSpacing(5);

        this.traceRoute = new Button(ButtonsConstants.INSTANCE.routeText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.routing()),
                new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!(startPoint.getComboBox().getRawValue().equals(""))
                        && !(finalPoint.getComboBox().getRawValue().equals(""))) {
                    findDirections(
                            startPoint.getComboBox().getSelection().get(0),
                            finalPoint.getComboBox().getSelection().get(0));
                } else {
                    GeoPlatformMessage.alertMessage(
                            RoutingModuleConstants.INSTANCE.routingText(),
                            RoutingModuleConstants.INSTANCE.
                            RoutingSearchWidget_warningInsertDataText());
                }
            }

        });

        buttonPanel.add(this.traceRoute);

        this.clear = new Button(ButtonsConstants.INSTANCE.clearText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.erase()),
                new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                startPoint.clearStatus();
                finalPoint.clearStatus();
                gridWidget.cleanUpTheStore();
            }

        });

        buttonPanel.add(this.clear);

        fieldSet.add(buttonPanel, new MarginData(5, 5, 5, 145));
    }

    /**
     *
     * @param start
     * @param end
     */
    public void findDirections(IGeoPlatformLocation start,
            IGeoPlatformLocation end) {
        this.gridWidget.cleanUpTheStore();
        this.gridWidget.maskGrid();

        this.request.setxStart(start.getLon());
        this.request.setyStart(start.getLat());
        this.request.setxStop(end.getLon());
        this.request.setyStop(end.getLat());

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<FindDirectionsByCoordResponse>() {

            private static final long serialVersionUID = 7834845836273863178L;

            {
                super.setCommandRequest(request);
            }

            @Override
            public void onCommandSuccess(FindDirectionsByCoordResponse response) {
                RoutingBean result = response.getResult();
                if (result != null) {
                    gridWidget.unMaskGrid();
                    gridWidget.fillStore(result.getDirections());
                    event.setWktLine(result.getCompleteLine());
                    RoutingHandlerManager.fireEvent(event);
                }
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                gridWidget.unMaskGrid();
                GeoPlatformMessage.errorMessage(
                        RoutingModuleConstants.INSTANCE.routingText(),
                        RoutingModuleConstants.INSTANCE.RoutingSearchWidget_errorRoutingServiceText());
            }

        });
    }

    /**
     * @return the fieldSet
     */
    public FieldSet getFieldSet() {
        return fieldSet;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.puregwt.routing.HasCleanEvent#addCleanEventHandler()
     */
    @Override
    public void addCleanEventHandler(CleanComboEventHandler handler,
            Object source) {
        RoutingHandlerManager.addHandlerToSource(CleanComboEventHandler.TYPE,
                source, handler);
    }

    /**
     * @param gridWidget the gridWidget to set
     */
    public void setGridWidget(RoutingGridWidget gridWidget) {
        this.gridWidget = gridWidget;
    }

}
