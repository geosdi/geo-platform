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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.ServerWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.ServerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.ServerModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.DisplayServerWidget;
import org.geosdi.geoplatform.gui.client.widget.EnumSearchServer;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemote;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemoteAsync;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AddServerWidget extends GeoPlatformFormWidget<GPServerBeanModel> {

    private final DisplayServerWidget displayServerWidget;
    private GPSecureStringTextField serverUrlTextField;
    private GPSecureStringTextField serverNameTextField;
    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final GeoPlatformOGCRemoteAsync geoPlatformOGCRemote = GeoPlatformOGCRemote.Util.getInstance();
    //
    private Button save;
    private Button cancel;
    private final PerformOperation performSaveServer;

    public AddServerWidget(DisplayServerWidget theWidget) {
        super(true);
        this.displayServerWidget = theWidget;
        this.performSaveServer = new PerformOperation();
    }

    @Override
    public void addComponentToForm() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setHeadingHtml(ServerModuleConstants.INSTANCE.serverText());
        this.fieldSet.setToolTip(ServerModuleConstants.INSTANCE.
                AddServerWidget_tooltipServerInsertionText());

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(70);
        fieldSet.setLayout(layout);

        this.serverUrlTextField = new GPSecureStringTextField();
        this.serverNameTextField = new GPSecureStringTextField();
        this.serverUrlTextField.setFieldLabel(ServerModuleConstants.INSTANCE.
                AddServerWidget_serverAddressText());
        this.serverNameTextField.setFieldLabel(ServerModuleConstants.INSTANCE.
                AddServerWidget_serverNameText());

        this.serverUrlTextField.addListener(Events.OnPaste, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (serverNameTextField.isDirty()) {
                    save.enable();
                }
            }
        });
        this.serverNameTextField.addListener(Events.OnPaste, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (serverUrlTextField.isDirty()) {
                    save.enable();
                }
            }
        });

        this.serverUrlTextField.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (serverUrlTextField.getValue() == null) {
                    if ((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                            || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                        reset();
                    }
                } else {
                    if (serverUrlTextField.getValue().length() > 15
                            && serverNameTextField.isDirty()) {
                        save.enable();
                    } else {
                        save.disable();
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == KeyCodes.KEY_ENTER
                        && serverUrlTextField.isDirty() && serverNameTextField.isDirty()
                        && serverUrlTextField.getValue().length() > 15) {
                    execute();
                }
            }
        });
        this.serverNameTextField.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (serverNameTextField.getValue() == null) {
                    if ((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                            || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                        reset();
                    }
                } else {
                    if (serverUrlTextField.getValue().length() > 15) {
                        save.enable();
                    } else {
                        save.disable();
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == KeyCodes.KEY_ENTER
                        && serverUrlTextField.isDirty()
                        && serverUrlTextField.getValue().length() > 15) {
                    execute();
                }
            }
        });

        this.fieldSet.add(this.serverNameTextField);
        this.fieldSet.add(this.serverUrlTextField);

        this.formPanel.add(this.fieldSet);

        this.saveStatus = new SaveStatus();
        this.saveStatus.setAutoWidth(true);

        this.formPanel.getButtonBar().add(this.saveStatus);

        formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        save = new Button(ButtonsConstants.INSTANCE.saveText(),
                AbstractImagePrototype.create(ServerWidgetResources.ICONS.addServer()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });

        save.setEnabled(false);

        this.formPanel.addButton(save);

        this.cancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        clearComponents();
                        clearStatusBarStatus();
                    }
                });

        this.formPanel.addButton(cancel);
    }

    @Override
    public void initSize() {
        setHeadingHtml(ServerModuleConstants.INSTANCE.addServerText());
        setSize(380, 210);
    }

    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(320, 120);
    }

    @Override
    public void execute() {
        this.saveStatus.setBusy(ServerModuleConstants.INSTANCE.
                AddServerWidget_statusAddingServerText());

        LayoutManager.getInstance().getStatusMap().setBusy(
                ServerModuleConstants.INSTANCE.savingServerText());
        this.performSaveServer.addServer();
    }

    @Override
    public void reset() {
        this.save.disable();
        this.serverUrlTextField.clear();
        this.serverNameTextField.clear();
        this.saveStatus.clearStatus("");
    }

    public void showForm() {
        if (!isInitialized()) {
            super.init();
        }
        super.show();
    }

    private void clearComponents() {
        super.hide();
    }

    private void clearStatusBarStatus() {
        LayoutManager.getInstance().getStatusMap().clearStatus("");
    }

    /**
     * Internal Class for Business Logic
     *
     */
    private class PerformOperation {

        private void addServer() {
            GPServerBeanModel server = displayServerWidget.containsServer(
                    serverUrlTextField.getValue());
            if (server != null) {
                notifyServerPresence(server);
            } else {
                saveServer();
            }
        }

        private void saveServer() {
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
                    ((HasRpcToken) geoPlatformOGCRemote).setRpcToken(token);
                    geoPlatformOGCRemote.saveServer(null,
                            serverNameTextField.getValue().trim(),
                            serverUrlTextField.getValue().trim(),
                            GPAccountLogged.getInstance().getOrganization(),
                            new AsyncCallback<GPServerBeanModel>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    setStatus(
                                            EnumSaveStatus.STATUS_NOT_SAVE.getValue(),
                                            EnumSaveStatus.STATUS_MESSAGE_NOT_SAVE.getValue());
                                    LayoutManager.getInstance().getStatusMap().setStatus(
                                            ServerModuleMessages.INSTANCE.
                                            AddServerWidget_saveServerErrorMessage(
                                                    caught.getMessage()),
                                            EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                                }

                                @Override
                                public void onSuccess(GPServerBeanModel server) {
                                    clearComponents();
                                    displayServerWidget.addServer(server);
                                }
                            });
                }
            });
        }

        private void notifyServerPresence(GPServerBeanModel server) {
            setStatus(EnumSaveStatus.STATUS_NOT_SAVE.getValue(),
                    EnumSearchServer.STATUS_MESSAGE_SERVER_EXISTING.toString());
            LayoutManager.getInstance().getStatusMap().setStatus(
                    ServerModuleConstants.INSTANCE.
                    AddServerWidget_saveServerText(),
                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
            GeoPlatformMessage.alertMessage(ServerModuleConstants.INSTANCE.
                    AddServerWidget_serverPresentText(),
                    ServerModuleMessages.INSTANCE.AddServerWidget_serverAlreadyPresentMessage(
                            serverUrlTextField.getValue(), server.getAlias()));
        }
    }
}
