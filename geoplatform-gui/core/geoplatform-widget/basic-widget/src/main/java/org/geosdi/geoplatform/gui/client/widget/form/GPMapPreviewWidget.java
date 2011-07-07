/*
 * $Header: com.digitalglobe.dgwatch.gui.client.widget.MapPreviewWidget,v. 0.1 31/ago/2010 09.10.34 created by giuseppe $
 * $Revision: 0.1 $
 * $Date: 31/ago/2010 09.10.34 $
 *
 * ====================================================================
 *
 * Copyright (C) 2010 GeoSolutions S.A.S.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. 
 *
 * ====================================================================
 *
 * This software consists of voluntary contributions made by developers
 * of GeoSolutions.  For more information on GeoSolutions, please see
 * <http://www.geo-solutions.it/>.
 *
 */
package org.geosdi.geoplatform.gui.client.widget.form;

import org.geosdi.geoplatform.gui.factory.map.DefaultMapFactory;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.layer.Layer;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class GPMapPreviewWidget {

    private DefaultMapFactory factory = new DefaultMapFactory();
    protected MapWidget mapWidget;
    protected Layer baseLayer;

    public GPMapPreviewWidget() {
        this.createMapPreview();
    }

    private void createMapPreview() {
        this.mapWidget = this.factory.createMap("100%", "100%", this.createMapPreviewOption());
        this.mapWidget.getMap().addControl(new LayerSwitcher());
        this.createBaseLayer();
    }

    public abstract void createBaseLayer();

    public abstract MapOptions createMapPreviewOption();

    /**
     * @return the mapWidget
     */
    public MapWidget getMapPreview() {
        return mapWidget;
    }
}
