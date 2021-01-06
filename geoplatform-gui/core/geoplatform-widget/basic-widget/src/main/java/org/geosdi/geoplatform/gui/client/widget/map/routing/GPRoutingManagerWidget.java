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
package org.geosdi.geoplatform.gui.client.widget.map.routing;

import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.client.widget.map.routing.control.GPRoutingEndPoint;
import org.geosdi.geoplatform.gui.client.widget.map.routing.control.GPRoutingLine;
import org.geosdi.geoplatform.gui.client.widget.map.routing.control.GPRoutingStartPoint;
import org.geosdi.geoplatform.gui.client.widget.map.routing.control.GenericRoutingPoint;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformBoxesWidget;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.puregwt.routing.RoutingHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.routing.event.RoutingActivationEventHandler;
import org.gwtopenmaps.openlayers.client.layer.Vector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GPRoutingManagerWidget implements RoutingActivationEventHandler {

    private GeoPlatformMap geoPlatformMap;
    private Vector pointsVector;
    private Vector routeVector;
    private GeoPlatformBoxesWidget box;
    private GenericRoutingPoint start;
    private GenericRoutingPoint end;
    private GPRoutingLine line;

    /**
     * @Constructor
     *
     * @param theGeoPlatformMap
     */
    public GPRoutingManagerWidget(GeoPlatformMap theGeoPlatformMap) {
        this.geoPlatformMap = theGeoPlatformMap;
        initWidget();
        RoutingHandlerManager.addHandler(RoutingActivationEventHandler.TYPE,
                this);
    }

    /**
     *
     */
    private void initWidget() {
        initBox();
        initPointsControl();
        initLineControl();
    }

    /**
     * Create Box to Limitare Search AREA
     */
    private void initBox() {
        this.box = new RoutingBoxes(geoPlatformMap);
    }

    /**
     *
     */
    private void initPointsControl() {
        this.pointsVector = new Vector("GP-Routing-Points-Vector");
        this.pointsVector.setZIndex(955);
        this.start = new GPRoutingStartPoint(this.pointsVector, this.box,
                this.geoPlatformMap);
        this.end = new GPRoutingEndPoint(this.pointsVector, this.box,
                this.geoPlatformMap);
    }

    /**
     *
     */
    private void initLineControl() {
        this.routeVector = new Vector("GP-Route-Vector");
        this.routeVector.setZIndex(956);
        this.line = new GPRoutingLine(routeVector, this.geoPlatformMap);
        this.start.setLine(this.line);
        this.end.setLine(this.line);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.puregwt.routing.event.RoutingActivationEventHandler#activate()
     */
    @Override
    public void activate() {
        GeoPlatformMessage.infoMessage(BasicWidgetConstants.INSTANCE.GPRoutingManagerWidget_infoMessageTitleText(),
                BasicWidgetConstants.INSTANCE.GPRoutingManagerWidget_infoActivateMessageBodyText());
        this.geoPlatformMap.getMap().addLayer(this.pointsVector);
        this.geoPlatformMap.getMap().addLayer(this.routeVector);
        this.box.activate();
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.puregwt.routing.event.RoutingActivationEventHandler#deactivate()
     */
    @Override
    public void deactivate() {
        GeoPlatformMessage.infoMessage(BasicWidgetConstants.INSTANCE.GPRoutingManagerWidget_infoMessageTitleText(),
                BasicWidgetConstants.INSTANCE.GPRoutingManagerWidget_infoDeactivateMessageBodyText());
        this.geoPlatformMap.getMap().removeLayer(this.pointsVector);
        this.geoPlatformMap.getMap().removeLayer(this.routeVector);
        this.box.deactivate();
    }
}
