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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.*;
import org.geosdi.geoplatform.gui.client.command.layer.basic.CloneProjectCommandRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.CloneProjectCommandResponse;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.service.LayerRemoteAsync;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.layers.projects.event.GPDefaultProjectTreeEvent;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class CloneProjectWidget extends GeoPlatformWindow {

    private final VerticalPanel vp = new VerticalPanel();
    private GPRootTreeNode model;
    private TextField<String> projectNameField;
    private CloneProjectWidget.GPDefaultProjectSelector selector;
    private SearchStatus searchStatus;
    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final LayerRemoteAsync layerRemote = LayerRemote.Util.getInstance();
    private final GPDefaultProjectTreeEvent defaultProjectEvent = new GPDefaultProjectTreeEvent();

    private FormPanel fp;

    public CloneProjectWidget() {
        super(true);
    }

    @Override
    public void addComponent() {
        vp.setSpacing(10);
        createFormPanel();
        this.vp.add(fp);
        super.add(this.vp);

        Button close = new Button(ButtonsConstants.INSTANCE.closeText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        hide();
                    }
                });
        Button clone = new Button(ButtonsConstants.INSTANCE.cloneText(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (fp.isValid())
                            cloneProjectCommand();
                    }
                });
        this.searchStatus = new SearchStatus();
        searchStatus.setAutoWidth(true);
        super.addButton(clone);
        super.addButton(close);
        super.getButtonBar().add(searchStatus);

    }

    private void cloneProjectCommand() {

        final CloneProjectCommandRequest cloneProjectCommandRequest = GWT.
                <CloneProjectCommandRequest>create(CloneProjectCommandRequest.class);

        cloneProjectCommandRequest.setProjectID(model.getId());
        cloneProjectCommandRequest.setNameProject(projectNameField.getValue());
        cloneProjectCommandRequest.setAccountID(GPAccountLogged.getInstance().getAccountDetailID());
        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<CloneProjectCommandResponse>() {

            private static final long serialVersionUID = 4372276287420606744L;

            {
                super.setCommandRequest(cloneProjectCommandRequest);
            }

            @Override
            public void onCommandSuccess(CloneProjectCommandResponse response) {
                GeoPlatformMessage.okMessage(LayerModuleConstants.INSTANCE.
                                CloneProjectWidget_cloneSuccessTitleText(),
                        LayerModuleConstants.INSTANCE.CloneProjectWidget_cloneProjectSuccessTitleText());
                LayoutManager.getInstance().getStatusMap().setStatus(
                        LayerModuleConstants.INSTANCE.CloneProjectWidget_cloneProjectSuccessTitleText(),
                        SaveStatus.EnumSaveStatus.STATUS_SAVE.toString());
                selector = new GPDefaultProjectSelector(response.getResult());
                selector.selectDefaultProject();
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                                CloneProjectWidget_cloneProjectErrorTitleText(),
                        exception.getMessage());
                LayoutManager.getInstance().getStatusMap().setStatus(
                        LayerModuleConstants.INSTANCE.CloneProjectWidget_cloneProjectErrorTitleText(),
                        SaveStatus.EnumSaveStatus.STATUS_SAVE_ERROR.toString());
            }
        });

    }

    private void createFormPanel() {
        fp = new FormPanel();
        fp.setBorders(false);
        fp.setHeaderVisible(false);
        fp.setBodyBorder(false);
        fp.setPadding(18);

        projectNameField = new TextField<>();
        projectNameField.setFieldLabel(LayerModuleConstants.INSTANCE.
                GPProjectSearchPanel_listViewNameText());
        projectNameField.setAllowBlank(false);
        fp.add(projectNameField);
    }

    @Override
    public void initSize() {
        setWidth(400);
        setAutoHeight(true);
    }

    @Override
    public void setWindowProperties() {
        setHeadingHtml(
                LayerModuleConstants.INSTANCE.CloneProjectWidget_headingText());
        setModal(true);
        setResizable(false);
        setLayout(new FlowLayout());
    }

    public void show(GPRootTreeNode model) {
        this.model = model;
        super.show();
        projectNameField.setValue(model.getProjectName().concat("-copy"));
    }

    /**
     * Set the correct Status Icon Style
     */
    public void setSearchStatus(Enum status, Enum message) {
        this.searchStatus.setIconStyle(status.toString());
        this.searchStatus.setText(message.toString());
    }

    private class GPDefaultProjectSelector {

        private final Long projectID;

        public GPDefaultProjectSelector(Long projectID) {
            this.projectID = projectID;
        }

        private void selectDefaultProject() {
            searchStatus.setBusy(LayerModuleConstants.INSTANCE.
                    GPProjectSearchPanel_statusSettingDefaultProjectText());
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
                    ((HasRpcToken) layerRemote).setRpcToken(token);
                    layerRemote.setDefaultProject(
                            projectID,
                            new AsyncCallback<Object>() {

                                /**
                                 * TODO MANAGE FOR SESSION TIMEOUT EXCEPTION *
                                 */
                                @Override
                                public void onFailure(Throwable caught) {
                                    GeoPlatformMessage.errorMessage(
                                            LayerModuleConstants.INSTANCE.
                                                    GPProjectSearchPanel_settingDefaultProjectErrorTitleText(),
                                            caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Object result) {
/*                                    setSearchStatus(
                                            SearchStatus.EnumSearchStatus.STATUS_SEARCH,
                                            EnumProjectMessage.DEFAUTL_PROJECT_MESSAGE);*/
                                    TimeoutHandlerManager.fireEvent(
                                            defaultProjectEvent);
                                    hide();
                                }
                            });
                }
            });
        }
    }
}
