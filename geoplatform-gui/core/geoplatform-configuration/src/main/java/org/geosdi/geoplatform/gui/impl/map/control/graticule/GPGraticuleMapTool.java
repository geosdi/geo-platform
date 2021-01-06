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
package org.geosdi.geoplatform.gui.impl.map.control.graticule;

import org.geosdi.geoplatform.gui.configuration.map.control.GeoPlatformMapControl;

import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.gwtopenmaps.openlayers.client.control.Graticule;
import org.gwtopenmaps.openlayers.client.control.GraticuleOptions;
import org.gwtopenmaps.openlayers.client.symbolizer.LineSymbolizer;
import org.gwtopenmaps.openlayers.client.symbolizer.LineSymbolizerOptions;
import org.gwtopenmaps.openlayers.client.symbolizer.TextSymbolizer;
import org.gwtopenmaps.openlayers.client.symbolizer.TextSymbolizerOptions;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GPGraticuleMapTool implements GeoPlatformMapControl {

    private boolean initialized;
    private GeoPlatformMap geoPlatformMap;
    private Graticule graticule;

    public GPGraticuleMapTool(GeoPlatformMap geoPlatformMap) {
        this.geoPlatformMap = geoPlatformMap;
        this.createControl();
    }

    @Override
    public void createControl() {
        if (!initialized) {
            LineSymbolizerOptions lineOptions = new LineSymbolizerOptions();
            lineOptions.setStrokeColor("#333333");
            lineOptions.setStrokeOpacity(0.5);
            lineOptions.setStrokeWidth(1);

            LineSymbolizer line = new LineSymbolizer(lineOptions);

            TextSymbolizerOptions textOptions = new TextSymbolizerOptions();
            textOptions.setFontSize("9px");

            TextSymbolizer text = new TextSymbolizer(textOptions);


            final GraticuleOptions grtOptions = new GraticuleOptions();

            grtOptions.setTargetSize(200);
            grtOptions.setLabelled(true);
            grtOptions.setLineSymbolyzer(line);
            grtOptions.setLabelSymbolizer(text);
            this.graticule = new Graticule(grtOptions);

            this.graticule.setAutoActivate(false);
            this.initialized = true;
        }
    }

    @Override
    public void activateControl() {
        this.geoPlatformMap.getMap().addControl(graticule);

        this.graticule.activate();
    }

    @Override
    public void deactivateControl() {
        if (this.graticule != null) {
            this.geoPlatformMap.getMap().removeControl(graticule);
            this.graticule.deactivate();
        }
    }

    @Override
    public boolean isEnabled() {
        return this.graticule.isActive();
    }

    @Override
    public void resetControl() {
    }

}
