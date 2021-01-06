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
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.CatalogFinderWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderMessages;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.puregwt.event.StatusWidgetEvent;
import org.geosdi.geoplatform.gui.client.service.GPCatalogFinderRemote;
import org.geosdi.geoplatform.gui.client.service.GPCatalogFinderRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.RecordsContainer;
import org.geosdi.geoplatform.gui.client.widget.statusbar.GPCatalogStatusBar.GPCatalogStatusBarType;

/**
 * Execute a CSW GetRecordById request, for view, into a new browser tab, the
 * full metadata of the record selected via the grid context menu.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class ShowFullMetadataAction extends MenuBaseAction {

    private static final GPCatalogFinderRemoteAsync catalogFinderRemote = GPCatalogFinderRemote.Util.getInstance();
    //
    private final RecordsContainer rc;
    private FullRecord record;

    public ShowFullMetadataAction(RecordsContainer rc) {
        super(CatalogFinderConstants.INSTANCE.
                ShowFullMetadataAction_titleActionText(), 
                AbstractImagePrototype.create(
                        CatalogFinderWidgetResources.ICONS.metadata()));

        this.rc = rc;
    }

    @Override
    public void componentSelected(MenuEvent e) {
        record = rc.getSelectedRecord();
        /**
         * If the record can't be selectable, retrieve the first (and only)
         * record excluded from selection.
         */
        if (record == null) {
            record = rc.getMetadataSelection().getRecordsExcluded().get(0);
        }

        this.rc.getBus().fireEvent(
                new StatusWidgetEvent(CatalogFinderMessages.INSTANCE.
                        ShowFullMetadataAction_loadingStatusBarMessage(
                                record.getTitle()),
                        GPCatalogStatusBarType.STATUS_LOADING));

        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                try {
                    throw caught;
                } catch (RpcTokenException e) {
                    // Can be thrown for several reasons:
                    //   - duplicate session cookie, which may be a sign of a cookie
                    //     overwrite attack
                    //   - XSRF token cannot be generated because session cookie isn't
                    //     present
                } catch (Throwable e) {
                    // unexpected
                }
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) catalogFinderRemote).setRpcToken(token);
                catalogFinderRemote.getRecordById(record.getIdCatalog(),
                        record.getIdentifier(), GWT.getModuleName(),
                        new AsyncCallback<String>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                System.out.println(
                                        "Error @@@@@@@@@@@@@@@@ " + caught);
                                rc.getBus().fireEvent(
                                        new StatusWidgetEvent(
                                                CatalogFinderConstants.INSTANCE.
                                                ShowFullMetadataAction_errorRecordRequestText(),
                                                GPCatalogStatusBarType.STATUS_ERROR));
                            }

                            @Override
                            public void onSuccess(String result) {
                                Window.open(
                                        GWT.getModuleBaseURL() + "csw-template/" + result,
                                        CatalogFinderConstants.INSTANCE.ShowFullMetadataAction_windowText(),
                                        "");
                                rc.getBus().fireEvent(
                                        new StatusWidgetEvent(
                                                CatalogFinderConstants.INSTANCE.
                                                ShowFullMetadataAction_recordRequestExecutedText(),
                                                GPCatalogStatusBarType.STATUS_OK));
                            }

                        });
            }
        });
    }

}
