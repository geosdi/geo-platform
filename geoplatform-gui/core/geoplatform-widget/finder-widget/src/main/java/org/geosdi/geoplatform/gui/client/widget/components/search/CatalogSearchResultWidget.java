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
package org.geosdi.geoplatform.gui.client.widget.components.search;

import org.geosdi.geoplatform.gui.client.widget.components.search.tree.GPTreeLayerWidgetSupport;
import org.geosdi.geoplatform.gui.client.widget.components.search.tree.CatalogTreeLayerWidgetSupport;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.puregwt.handler.ActionTreePresenceHandler;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.RecordsContainer;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogSearchResultWidget extends LayoutContainer
        implements ActionTreePresenceHandler {

    private final RecordsContainer recordsContainer;
    private final GPEventBus bus;
    private Label resultLabel;
    private TreePanel<GPBeanTreeModel> tree;

    @Inject
    public CatalogSearchResultWidget(RecordsContainer theRecordsContainer,
            GPEventBus theBus) {
        this.recordsContainer = theRecordsContainer;
        this.bus = theBus;

        this.addHandler();
        this.addStyle();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.resultLabel = new Label(CatalogFinderConstants.INSTANCE.CatalogSearchResultWidget_resultLabelText());
        resultLabel.setStyleName("searchResult-Label");

        add(resultLabel);
        add(recordsContainer);

        if (this.tree != null) {
            this.addComponentsForTreePresence();
        }
    }

    @Override
    public void notifyTreePresence(TreePanel<GPBeanTreeModel> theTree) {
        this.tree = theTree;
    }

    private void addHandler() {
        this.bus.addHandler(CatalogSearchResultWidget.TYPE, this);
    }

    private void addStyle() {
        super.setStyleName("searchResult-Widget");
    }

    private void addComponentsForTreePresence() {
        GPTreeLayerWidgetSupport catalogTreeLayer = new CatalogTreeLayerWidgetSupport(
                tree, recordsContainer, bus);

        HorizontalPanel hp = new HorizontalPanel();
        hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        hp.setStyleName("catalogTree-Button");

        hp.add(catalogTreeLayer.getAddOnTreeButton());
        hp.add(catalogTreeLayer.getLoadWMSCapabilitiesButton());

        super.add(catalogTreeLayer.getLabel());
        super.add(hp);

        this.recordsContainer.setSelectionContainer(true);
    }

}
