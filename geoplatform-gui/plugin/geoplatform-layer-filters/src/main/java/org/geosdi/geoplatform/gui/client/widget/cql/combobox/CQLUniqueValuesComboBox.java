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

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.command.filter.basic.UniqueValueResponse;
import org.geosdi.geoplatform.gui.client.command.filter.basic.UniqueValuesRequest;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.model.GPUniqueValues;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class CQLUniqueValuesComboBox extends ComboBox<GPUniqueValues> {

    private final UniqueValuesRequest uniqueValuesRequest = GWT.
            <UniqueValuesRequest>create(UniqueValuesRequest.class);
    private ListLoader<ListLoadResult<GPUniqueValues>> loader;
    private GPTreePanel<GPBeanTreeModel> treePanel;
    private String layerAttribute;
    private RpcProxy<GPUniqueValues> proxy;

    public CQLUniqueValuesComboBox(GPTreePanel<GPBeanTreeModel> treePanel) {
        this.treePanel = treePanel;
        setComboBoxProperties();
        setProxy();
    }

    private void setProxy() {
        final String layerName = ((GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem()).getName();
        uniqueValuesRequest.setLayerName(layerName);
        // proxy loader
        proxy = new RpcProxy<GPUniqueValues>() {
            @Override
            public void load(final Object loadConfig, AsyncCallback<GPUniqueValues> callback) {
                ClientCommandDispatcher.getInstance().execute(
                        new GPClientCommand<UniqueValueResponse>() {

                            private static final long serialVersionUID = -6344112951569266067L;

                            {
                                super.setCommandRequest(uniqueValuesRequest);
                            }

                            @Override
                            public void onCommandSuccess(
                                    UniqueValueResponse response) {
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
        loader = new BaseListLoader<ListLoadResult<GPUniqueValues>>(proxy);
        loader.setSortField(GPUniqueValues.GPUniqueValueKey.UNIQUE_VALUE.toString());
        loader.setSortDir(Style.SortDir.ASC);
        loader.setRemoteSort(true);
        // configure store
        super.store = new ListStore<GPUniqueValues>(loader);
    }

    public void setLayerAttribute(String layerAttribute) {
        uniqueValuesRequest.setLayerAttribute(layerAttribute);
    }

    private void setComboBoxProperties() {
        super.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.CQLLayerAttributesComboBox_fieldLabelText());
        this.setForceSelection(true);
        this.setLoadingText(LayerFiltersModuleConstants.INSTANCE.CQLLayerAttributesComboBox_loadingText());
        this.setTriggerAction(TriggerAction.ALL);
        this.setDisplayField(GPUniqueValues.GPUniqueValueKey.UNIQUE_VALUE.toString());
        this.setEditable(false);
        this.setForceSelection(true);
        this.setUseQueryCache(Boolean.FALSE);
    }

}
