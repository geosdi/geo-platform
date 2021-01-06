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
package org.geosdi.geoplatform.gui.impl.view;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.ScaleVisibleEvent;
import org.geosdi.geoplatform.gui.view.GeoPlatformLayoutManager;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;
import org.gwtopenmaps.openlayers.client.MapWidget;

/**
 * @author giuseppe
 *
 */
public class LayoutManager extends GeoPlatformLayoutManager {

    private static LayoutManager instance = GWT.create(LayoutManager.class);

    private LayoutManager() {
    }

    /**
     * Build Singleton Instace
     *
     * @return Instance Reference
     */
    public static LayoutManager getInstance() {
        return instance;
    }

    /**
     * Add a generic Widget to Center
     *
     * @param w
     */
    public static void addComponentToCenter(Widget w) {
        getInstance().center.removeAll();
        getInstance().center.add(w);
        getInstance().center.layout();
        MapHandlerManager.fireEvent(new ScaleVisibleEvent(
                w instanceof MapWidget ? true : false));
    }

    /**
     * Add a generic Widget to West
     *
     * @param w
     */
    public static void addComponentToWest(Widget w) {
        getInstance().west.add(w);
        getInstance().west.layout();
    }

    /**
     * Add a generic Widget to panel
     *
     * @param panel
     * @param w
     */
    public static void addComponentToPanel(int panel, Widget w) {
        switch (panel) {
            case 0:
                addComponentToWest(w);
                break;
            case 1:
                addComponentToEast(w);
                break;
        }
    }

    /**
     * Remove a generic widget from West
     *
     * @param w
     */
    public static void removeComponentFromWest(Widget w) {
        removeComponentFromPanel(getInstance().west, w);
    }

    /**
     * Remove a generic widget from East
     *
     * @param w
     */
    public static void removeComponentFromEast(Widget w) {
        removeComponentFromPanel(getInstance().east, w);
    }

    /**
     * Remove a generic widget from Panel
     *
     * @param w
     */
    public static void removeComponentFromPanel(ContentPanel cp, Widget w) {
        cp.remove(w);
        cp.layout();
    }

    /**
     * Remove a generic widget from Panel
     *
     * @param w
     */
    public static void removeComponentFromPanel(int panel, Widget w) {
        switch (panel) {
            case 0:
                removeComponentFromWest(w);
                break;
            case 1:
                removeComponentFromEast(w);
                break;
        }
    }

    public static boolean isWidgetPresentOnWest(Widget w) {
        return getInstance().west.getItems().contains(w);
    }

    public static boolean isWidgetPresentOnEast(Widget w) {
        return getInstance().east.getItems().contains(w);
    }

    public static boolean isWidgetPresentOnPanel(int panel, Widget w) {
        switch (panel) {
            case 0:
                return isWidgetPresentOnWest(w);
            case 1:
                return isWidgetPresentOnEast(w);
        }
        throw new IllegalArgumentException("The panel code passed is not valid: " + panel);
    }

    /**
     * Add a generic Widget to East
     *
     * @param w
     */
    public static void addComponentToEast(Widget w) {
        getInstance().east.add(w);
        getInstance().east.layout();
    }

    /**
     * Add a generic Widget to North
     *
     * @param w
     */
    public static void addComponentToNorth(Widget w) {
        getInstance().north.add(w);
        getInstance().north.layout();
    }

    /**
     * Add a generic Widget to South
     *
     * @param w
     */
    public static void addComponentToSouth(TabItem w) {
        getInstance().southTabPanel.add(w);
    }

    /**
     * Show or Hide a panel
     *
     * @param panel
     * @param visible
     */
    public static void managePanel(int panel, boolean visible) {
        switch (panel) {
            case 0:
                manageWest(visible);
                break;
            case 1:
                manageEast(visible);
                break;
        }
    }

    /**
     * Show or Hide West panel
     *
     * @param visible
     */
    public static void managePanel(ContentPanel cp, boolean visible) {
        if (visible) {
            cp.show();
        } else {
            cp.hide();
        }
        Dispatcher.forwardEvent(GeoPlatformEvents.UPDATE_CENTER);
    }

    /**
     * Show or Hide West panel
     *
     * @param visible
     */
    public static void manageWest(boolean visible) {
        managePanel(getInstance().west, visible);
    }

    /**
     * Show or Hide East panel
     *
     * @param visible
     */
    public static void manageEast(boolean visible) {
        managePanel(getInstance().east, visible);
    }

    /**
     * Show or Hide South panel
     *
     * @param visible
     */
    public static void manageSouth(boolean visible) {
        if (visible) {
            getInstance().south.show();
        } else {
            getInstance().south.hide();
        }
        Dispatcher.forwardEvent(GeoPlatformEvents.UPDATE_CENTER);
    }

    /**
     * Layout West Panel
     */
    public static void layoutWest() {
        getInstance().west.layout();
    }

    /**
     * Layout South Panel
     */
    public static void layoutSouth() {
        getInstance().south.layout();
    }

    /**
     * Check the Visibility of West Panel
     *
     * @return boolean
     */
    public static boolean isWestVisible() {
        return getInstance().west.isVisible();
    }

    /**
     * Check the Visibility of the Panel
     *
     * @param panel
     * @return boolean
     */
    public static boolean isPanelVisible(int panel) {
        switch (panel) {
            case 0:
                return isWestVisible();
            case 1:
                return isEastVisible();
        }
        throw new IllegalArgumentException("The panel code passed is not valid: " + panel);
    }

    /**
     * Check the Visibility of East Panel
     *
     * @return boolean
     */
    public static boolean isEastVisible() {
        return getInstance().east.isVisible();
    }

    /**
     * Check If always one Widget is visible on West
     *
     * @return boolean
     */
    public static boolean isOneWidgetVisibleAtWest() {
        return isOneWidgetVisibleAtPanel(instance.west);
    }

    /**
     * Check If always one Widget is visible on East
     *
     * @return boolean
     */
    public static boolean isOneWidgetVisibleAtEast() {
        return isOneWidgetVisibleAtPanel(instance.east);
    }

    private static boolean isOneWidgetVisibleAtPanel(ContentPanel cp) {
        for (Component c : cp.getItems()) {
            if (c.isVisible()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check If always one Widget is visible on Panel
     *
     * @param panel
     * @return boolean
     */
    public static boolean isOneWidgetVisibleAtPanel(int panel) {
        switch (panel) {
            case 0:
                return isOneWidgetVisibleAtWest();
            case 1:
                return isOneWidgetVisibleAtEast();
        }
        throw new IllegalArgumentException("The panel code passed is not valid: " + panel);
    }

    /**
     * Reset North Component Border Layout Size
     *
     */
    public static void normalizeNorthPanel() {
        if ((getInstance().north.getItemCount() == 0)
                && (getInstance().north.getTopComponent() == null)) {
            getInstance().removeNorth();
        } else if ((getInstance().north.getItemCount() == 0)
                && (getInstance().north.getTopComponent() != null)) {
            getInstance().resetNorthBorderLayout();
        }
    }
}
