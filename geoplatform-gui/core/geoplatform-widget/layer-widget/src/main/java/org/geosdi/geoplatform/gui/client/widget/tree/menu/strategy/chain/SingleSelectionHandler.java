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
package org.geosdi.geoplatform.gui.client.widget.tree.menu.strategy.chain;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import org.geosdi.geoplatform.gui.client.config.annotation.SingleSelection;
import org.geosdi.geoplatform.gui.client.widget.tree.menu.store.keybuilder.SelectionCompositeKeyBuilder;
import org.geosdi.geoplatform.gui.client.widget.tree.menu.strategy.SingleSelectionMenuStrategy;
import org.geosdi.geoplatform.gui.configuration.composite.menu.store.StoreCompositeKey;
import org.geosdi.geoplatform.gui.impl.tree.menu.strategy.TreeMenuStrategyManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class SingleSelectionHandler extends SelectionChainHandler {

    @Inject
    private SingleSelectionMenuStrategy singleSelectionStrategy;
    private final SelectionCompositeKeyBuilder compositeKeyBuilder;

    @Inject
    public SingleSelectionHandler(MultiSelectionHandler theSuccessor,
            @SingleSelection SelectionCompositeKeyBuilder theCompositeKeyBuilder) {
        super.setSuccessor(theSuccessor);
        this.compositeKeyBuilder = theCompositeKeyBuilder;
    }

    @Override
    public Menu buildMenu(TreeMenuStrategyManager strategyManager, List<GPBeanTreeModel> selections) {
        return (selections.size() == 1) ? bindStrategy(strategyManager, selections) : super.forwardBuildMenu(strategyManager, selections);
    }

    @Override
    protected Menu bindStrategy(TreeMenuStrategyManager strategyManager, List<GPBeanTreeModel> selections) {
        strategyManager.setMenuStrategy(singleSelectionStrategy);
        return strategyManager.getMenu(super.bindSelection(selections));
    }

    @Override
    protected StoreCompositeKey buildStoreCompositeKey(List<GPBeanTreeModel> selections) {
        return this.compositeKeyBuilder.buildStoreCompositeKey(selections);
    }
}