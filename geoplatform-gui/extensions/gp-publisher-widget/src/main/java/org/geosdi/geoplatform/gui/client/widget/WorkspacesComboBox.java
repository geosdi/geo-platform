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

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.geosdi.geoplatform.gui.client.command.publish.basic.GetWorkspaceListRequest;
import org.geosdi.geoplatform.gui.client.command.publish.basic.GetWorkspaceListResponse;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.GPWorkspace;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class WorkspacesComboBox extends ComboBox<GPWorkspace> {

    private GetWorkspaceListRequest getWorkspaceListRequest = GWT.
            <GetWorkspaceListRequest>create(GetWorkspaceListRequest.class);
    private ListLoader<ListLoadResult<GPWorkspace>> loader;

    public WorkspacesComboBox() {
        this.init();
    }

    private void init() {
        this.setComboBoxProperties();
        // proxy loader
        RpcProxy<List<GPWorkspace>> proxy = new RpcProxy<List<GPWorkspace>>() {
            @Override
            public void load(final Object loadConfig,
                    AsyncCallback<List<GPWorkspace>> callback) {

                ClientCommandDispatcher.getInstance().execute(
                        new GPClientCommand<GetWorkspaceListResponse>() {
                            private static final long serialVersionUID = 4372276287420606744L;

                            {
                                super.setCommandRequest(getWorkspaceListRequest);
                            }

                            @Override
                            public void onCommandSuccess(
                                    GetWorkspaceListResponse response) {
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
        loader = new BaseListLoader<ListLoadResult<GPWorkspace>>(
                proxy);
        loader.setSortField(GPWorkspace.GPWorkspaceKey.WORKSPACE_NAME.name());
        loader.setSortDir(SortDir.ASC);
        loader.setRemoteSort(true);
        // configure store
        super.store = new ListStore<GPWorkspace>(loader);
    }

    private void setComboBoxProperties() {
        super.setFieldLabel(PublisherWidgetConstants.INSTANCE.WorkspacesComboBox_fieldLabelText());
        super.setForceSelection(true);
        super.setLoadingText(WindowsConstants.INSTANCE.loadingDataText());
        super.setTriggerAction(TriggerAction.ALL);
        super.setDisplayField(GPWorkspace.GPWorkspaceKey.WORKSPACE_NAME.name());
        super.setEditable(false);
        super.setBorders(false);
        super.setUseQueryCache(false);
//        this.setValueField(GPLayerAttributes.GPAttributeKey.ATTRIBUTE_VALUE.toString());
    }
}
