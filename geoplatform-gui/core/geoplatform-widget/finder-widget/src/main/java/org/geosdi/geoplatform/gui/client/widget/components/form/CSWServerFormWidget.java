/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.components.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.puregwt.event.CatalogStatusBarEvent;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.components.filters.container.CSWServerPaginationContainer;
import org.geosdi.geoplatform.gui.client.widget.form.GeoPlatformFormWidget;
import org.geosdi.geoplatform.gui.client.widget.statusbar.GPCatalogStatusBar.GPCatalogStatusBarType;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.server.gwt.GPCatalogFinderRemoteImpl;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CSWServerFormWidget
        extends GeoPlatformFormWidget<GPServerBeanModel> {

    private CSWServerPaginationContainer catalogWindget;
    private FormButtonBinding formButtonBinding; // Monitors the valid state of a form and enabled / disabled all buttons
    private TextField<String> urlField;
    private TextField<String> aliasField;
    private Button saveButton;
    private String urlEncoding;
    private GPEventBus bus;

    public CSWServerFormWidget(CSWServerPaginationContainer catalogWindget,
            GPEventBus bus) {
        super(true);
        this.catalogWindget = catalogWindget;
        this.bus = bus;
    }

    @Override
    public void addComponentToForm() {
        this.createFieldSet();

        super.saveStatus = new SaveStatus();
        super.saveStatus.setAutoWidth(true);

        super.formPanel.getButtonBar().add(super.saveStatus);

        super.formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        saveButton = new Button("Save", BasicWidgetResources.ICONS.done(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });
        saveButton.setEnabled(false);
        super.formPanel.addButton(saveButton);

        Button cancelButton = new Button("Cancel",
                BasicWidgetResources.ICONS.cancel(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        hide();
                    }
                });
        super.formPanel.addButton(cancelButton);

        formButtonBinding = new FormButtonBinding(super.formPanel);
        formButtonBinding.addButton(saveButton);
    }

    private void createFieldSet() {
        fieldSet = new FieldSet();
        fieldSet.setHeading("Server");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(70);
        layout.setDefaultWidth(250);
        fieldSet.setLayout(layout);

        aliasField = new TextField<String>();
        aliasField.setFieldLabel("Alias");
        aliasField.setEmptyText("Enter a CSW server alias");
        aliasField.setToolTip("Insert a alias for the CSW server");
        aliasField.setAllowBlank(false);
        aliasField.setAutoValidate(true);

        urlField = new TextField<String>();
        urlField.setFieldLabel("Address");
        urlField.setEmptyText("Enter a CSW server URL");
        urlField.setToolTip("Insert a URL for the CSW server");
        urlField.setAllowBlank(false);
        urlField.setAutoValidate(true);
        urlField.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!value.startsWith("http://") && !value.startsWith("https://")) {
                    return "URL must be start with \"http://\" or \"https://\"";
                }

                urlEncoding = deleteQueryStringFromURL(value.trim());
                return null;
            }
        });

        fieldSet.add(aliasField);
        fieldSet.add(urlField);

        formPanel.add(fieldSet);
    }

    @Override
    public void initSize() {
        setHeading("Insert CSW Server");
        setSize(400, 210);
    }

    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(320, 120);
    }

    @Override
    public void reset() {
        this.saveButton.disable();
        this.urlField.clear();
        this.aliasField.clear();
        super.saveStatus.clearStatus("");
    }

    public void showForm() {
        if (!isInitialized()) {
            super.init();
        }
        super.show();
    }

    @Override
    public void execute() {
        super.saveStatus.setBusy("Adding CSW Server");

        GPCSWServerBeanModel server = catalogWindget.containsServer(urlEncoding);
        if (server != null) {
            setStatus(EnumSaveStatus.STATUS_NOT_SAVE.getValue(),
                    EnumSaveStatus.STATUS_MESSAGE_NOT_SAVE.getValue());

            GeoPlatformMessage.alertMessage(
                    EnumSaveStatus.STATUS_MESSAGE_NOT_SAVE.getValue(),
                    "Server already exist, with alias \"" + server.getAlias() + "\"");
        } else {
            saveServer();
        }
    }

    private void saveServer() {
        final String aliasValue = aliasField.getValue().trim();
        GPCatalogFinderRemoteImpl.Util.getInstance().saveServerCSW(aliasValue,
                urlEncoding, GPAccountLogged.getInstance().getOrganization(),
                new AsyncCallback<GPCSWServerBeanModel>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        System.out.println(
                                "\n*** Error on saving server: " + caught.getMessage()); // TODO logger
                        setStatus(EnumSaveStatus.STATUS_SAVE_ERROR.getValue(),
                                EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR.getValue());
                        bus.fireEvent(new CatalogStatusBarEvent(
                                "Error on saving server",
                                GPCatalogStatusBarType.STATUS_ERROR));
                    }

                    @Override
                    public void onSuccess(GPCSWServerBeanModel server) {
                        catalogWindget.addNewServer(server);

                        /**
                         * TODO
                         * Manage case when the user try to add a server with
                         * same alias and URL wrt a DB entry previous added.
                         * So, the server don't be added but will be returned
                         * the DB entry early saved.
                         */
                        if (aliasValue.equals(server.getAlias())) {
                            setStatus(EnumSaveStatus.STATUS_SAVE.getValue(),
                                    EnumSaveStatus.STATUS_MESSAGE_SAVE.getValue());
                            bus.fireEvent(new CatalogStatusBarEvent(
                                    "Server correctly saved",
                                    GPCatalogStatusBarType.STATUS_OK));
                        } else {
                            setStatus(EnumSaveStatus.STATUS_NOT_SAVE.getValue(),
                                    EnumSaveStatus.STATUS_MESSAGE_NOT_SAVE.getValue());
                            bus.fireEvent(new CatalogStatusBarEvent("Server already exist, with alias \""
                                    + server.getAlias() + "\"",
                                    GPCatalogStatusBarType.STATUS_NOT_OK));
                        }

                        hide();
                    }
                });
    }

    private String deleteQueryStringFromURL(String serverUrl) {
        int index = serverUrl.indexOf("?");
        if (index != -1) {
            return serverUrl.substring(0, index);
        }
        return serverUrl;
    }
}
