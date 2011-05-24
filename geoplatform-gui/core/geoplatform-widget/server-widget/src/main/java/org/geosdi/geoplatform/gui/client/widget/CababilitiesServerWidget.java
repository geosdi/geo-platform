/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class CababilitiesServerWidget extends Window {

    private GridLayersWidget gridLayers;
    private boolean initialized;

    /**
     *
     * @param lazy
     *       If true the component will not build in Construction Fase
     *       otherwise set to False
     */
    public CababilitiesServerWidget(boolean lazy) {
        if (!lazy) {
            init();
        }
    }

    private void init() {
        if (!isInitialized()) {
            initializeWindow();
            initComponents();
            this.initialized = true;
        }
    }

    private void initializeWindow() {
        super.setSize(600, 500);
        super.setHeading("Server Cababilities");
        setResizable(false);

        addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                gridLayers.loadServers();
            }

            @Override
            public void windowHide(WindowEvent we) {
                resetComponents();
            }
        });

        addWidgetListener(new WidgetListener() {

            @Override
            public void widgetAttached(ComponentEvent ce) {
                gridLayers.getGrid().setHeight(400);
            }
        });

        setLayout(new FitLayout());
        setModal(false);
        setCollapsible(true);
        setPlain(true);
    }

    private void initComponents() {
        this.gridLayers = new GridLayersWidget();
        super.add(this.gridLayers.getFormPanel());
    }

    private void resetComponents() {
    }

    @Override
    public void show() {
        if (!isInitialized()) {
            this.init();
        }
        super.show();
    }

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
}
