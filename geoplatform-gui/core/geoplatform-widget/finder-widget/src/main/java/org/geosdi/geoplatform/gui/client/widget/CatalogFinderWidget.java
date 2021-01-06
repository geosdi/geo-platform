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
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.puregwt.event.ActionTreePresenceEvent;
import org.geosdi.geoplatform.gui.client.puregwt.event.LoadFirstServersEvent;
import org.geosdi.geoplatform.gui.client.widget.components.MainViewFinderWidget;
import org.geosdi.geoplatform.gui.client.widget.components.filters.FiltersFinderWidget;
import org.geosdi.geoplatform.gui.client.widget.statusbar.GPCatalogStatusBar;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@Singleton
public class CatalogFinderWidget extends GeoPlatformWindow {

    private final FiltersFinderWidget filtersWidget;
    private final MainViewFinderWidget mainViewWidget;
    private final GPCatalogStatusBar catalogStatusBar;
    private final GPEventBus bus;
    private TreePanel<GPBeanTreeModel> tree;
    private final LoadFirstServersEvent loadFirstServers = new LoadFirstServersEvent();

    @Inject
    public CatalogFinderWidget(FiltersFinderWidget theFiltersWidget,
            MainViewFinderWidget theMainViewWidget,
            GPCatalogStatusBar theCatalogStatusBar, GPEventBus theBus) {
        super(true);
        this.filtersWidget = theFiltersWidget;
        this.mainViewWidget = theMainViewWidget;
        this.bus = theBus;
        this.catalogStatusBar = theCatalogStatusBar;
    }

    @Override
    public void addComponent() {
        addWestWidget();
        addCenterWidget();
        addStatusBar();
    }

    @Override
    public void initSize() {
        super.setSize(1000, 675);
        super.setHeadingText(CatalogFinderConstants.INSTANCE.CatalogFinderWidget_headingText());
    }

    @Override
    public void setWindowProperties() {
        super.setResizable(false);
        super.setModal(false);
        super.setCollapsible(true);
        super.setPlain(true);

        super.setLayout(new BorderLayout());
    }

    /**
     * @param tree the tree to set
     */
    public void setTree(TreePanel<GPBeanTreeModel> tree) {
        this.tree = tree;
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        if (this.tree != null) {
            this.changeWindowState();
        }
    }

    private void changeWindowState() {
        super.setClosable(true);
        this.notifyTreePresence();
    }

    /**
     * Method to add in the Catalog Finder UI the section to add Metadata on
     * GeoPlatform Tree Layers Widget
     */
    private void notifyTreePresence() {
        this.bus.fireEvent(new ActionTreePresenceEvent(this.tree));
    }

    private void addWestWidget() {
        BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 400);
        westData.setMargins(new Margins(0, 5, 0, 0));

        super.add(this.filtersWidget, westData);
    }

    private void addCenterWidget() {
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0));

        super.add(this.mainViewWidget, centerData);
    }

    private void addStatusBar() {
        this.catalogStatusBar.setAutoWidth(true);

        super.setButtonAlign(HorizontalAlignment.LEFT);
        super.getButtonBar().add(this.catalogStatusBar);
    }

    @Override
    public void reset() {
        this.filtersWidget.reset();
        this.mainViewWidget.reset();
        this.catalogStatusBar.reset();
    }

    @Override
    protected void notifyShow() {
        super.notifyShow();
        this.bus.fireEvent(loadFirstServers);
    }

}
