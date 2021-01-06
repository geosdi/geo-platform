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
package org.geosdi.geoplatform.gui.client.widget.cql.combobox;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.command.filter.basic.FilterDescribeFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.filter.basic.FilterDescribeFeatureResponse;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes.GPAttributeKey;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CQLLayerAttributesComboBox extends ComboBox<GPLayerAttributes> {

    private FilterDescribeFeatureRequest filterDescribeFeatureRequest = GWT.
            <FilterDescribeFeatureRequest>create(FilterDescribeFeatureRequest.class);
    private ListLoader<ListLoadResult<GPLayerAttributes>> loader;
    private GPTreePanel<GPBeanTreeModel> treePanel;

    public CQLLayerAttributesComboBox(GPTreePanel<GPBeanTreeModel> treePanel) {
        this.treePanel = treePanel;
        this.init();
    }

    private void init() {
        this.setComboBoxProperties();
        // proxy loader
        RpcProxy<List<GPLayerAttributes>> proxy = new RpcProxy<List<GPLayerAttributes>>() {
            @Override
            public void load(final Object loadConfig, AsyncCallback<List<GPLayerAttributes>> callback) {
                final String layerName = ((GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem()).getName();
                filterDescribeFeatureRequest.setLayerName(layerName);

                ClientCommandDispatcher.getInstance().execute(
                        new GPClientCommand<FilterDescribeFeatureResponse>() {
                    private static final long serialVersionUID = 4372276287420606744L;

                    {
                        super.setCommandRequest(filterDescribeFeatureRequest);
                    }

                    @Override
                    public void onCommandSuccess(
                            FilterDescribeFeatureResponse response) {
                        loader.fireEvent(Loader.Load, new LoadEvent(loader,
                                loadConfig, response.getResult()));
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        System.out.println("Errore : " + exception);
                    }
                });
            }
        };
        loader = new BaseListLoader<ListLoadResult<GPLayerAttributes>>(proxy);
        loader.setSortField(GPAttributeKey.ATTRIBUTE_VALUE.toString());
        loader.setSortDir(SortDir.ASC);
        loader.setRemoteSort(true);
        // configure store
        super.store = new ListStore<GPLayerAttributes>(loader);
    }

    private void setComboBoxProperties() {
        super.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.CQLLayerAttributesComboBox_fieldLabelText());
        this.setForceSelection(true);
        this.setLoadingText(LayerFiltersModuleConstants.INSTANCE.CQLLayerAttributesComboBox_loadingText());
        this.setTriggerAction(TriggerAction.ALL);
        this.setDisplayField(GPAttributeKey.ATTRIBUTE_VALUE.toString());
        this.setEditable(false);
        this.setForceSelection(true);
        this.setUseQueryCache(Boolean.FALSE);
//        this.setValueField(GPLayerAttributes.GPAttributeKey.ATTRIBUTE_VALUE.toString());
    }
}
