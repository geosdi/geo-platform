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

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.DualListField.Mode;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.share.GetUsersToShareProjectRequest;
import org.geosdi.geoplatform.gui.client.command.share.GetUsersToShareProjectResponse;
import org.geosdi.geoplatform.gui.client.command.share.ShareProjectRequest;
import org.geosdi.geoplatform.gui.client.command.share.ShareProjectResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.MementoPersistenceConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.service.LayerRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUserKeyValue;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ShareProjectPanel extends GeoPlatformContentPanel {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final LayerRemoteAsync layerRemote = LayerRemote.Util.getInstance();
    private final static String PROJECT_NAME_LABEL = LayerModuleConstants.INSTANCE.ShareProjectPanel_projectNameLabelText() + ": ";
    private final static String OWNER_LABEL = LayerModuleConstants.INSTANCE.ShareProjectPanel_ownerLabelText() + ": ";
    private final static String ORGANIZATION_LABEL = LayerModuleConstants.INSTANCE.ShareProjectPanel_organizationLabelText() + ": ";
    private static final ShareProjectRequest shareProjectReq = new ShareProjectRequest();
    private static final GetUsersToShareProjectRequest getUsersToShare = new GetUsersToShareProjectRequest();
    //
    private ListStore<GPSimpleUser> fromStore;
    private ListStore<GPSimpleUser> toStore;
    private GPClientProject project;
    private final GPProjectManagementWidget projectManagementWidget;
    private StoreFilterField<GPSimpleUser> toFilter;
    private StoreFilterField<GPSimpleUser> fromFilter;
    private Button saveButton;
    private Label projectNameLabel;
    private Label ownerLabel;
    private Label organizationLabel;

    public ShareProjectPanel(GPProjectManagementWidget projectManagementWidget,
            boolean lazy) {
        super(lazy);
        this.projectManagementWidget = projectManagementWidget;
    }

    @Override
    public void initSize() {
        super.setSize(GPProjectManagementWidget.COMPONENT_WIDTH,
                GPProjectManagementWidget.COMPONENT_HEIGHT);
    }

    @Override
    public void addComponent() {
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing(10);
        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeadingHtml(
                LayerModuleConstants.INSTANCE.ShareProjectPanel_fieldSetHeadingText());
        fieldSet.setWidth(GPProjectManagementWidget.COMPONENT_WIDTH - 25);
        this.projectNameLabel = new Label();
        this.projectNameLabel.setStyleAttribute("font-size", "13");
        this.ownerLabel = new Label();
        this.ownerLabel.setStyleAttribute("font-size", "13");
        this.organizationLabel = new Label();
        this.organizationLabel.setStyleAttribute("font-size", "13");
        fieldSet.add(this.projectNameLabel, new MarginData(10));
        fieldSet.add(ownerLabel, new MarginData(10));
        fieldSet.add(organizationLabel, new MarginData(10));
        verticalPanel.add(fieldSet);
        //
        LayoutContainer labelListContainer = new LayoutContainer(
                new BorderLayout());
        labelListContainer.setHeight(20);
        labelListContainer.setStyleAttribute("background-color", "white");
        labelListContainer.setWidth(
                GPProjectManagementWidget.COMPONENT_WIDTH - 25);
        Label organizationUserLabel = new Label(LayerModuleConstants.INSTANCE.
                ShareProjectPanel_organizationUserLabelText() + ":");
        organizationUserLabel.setStyleAttribute("font-size", "13");
        organizationUserLabel.setStyleAttribute("font-weight", "bold");
        labelListContainer.add(organizationUserLabel, new BorderLayoutData(
                Style.LayoutRegion.WEST));
        Label projectSharedUserLabel = new Label(LayerModuleConstants.INSTANCE.
                ShareProjectPanel_projectSharedUserLabelText() + ":");
        projectSharedUserLabel.setStyleAttribute("font-size", "13");
        projectSharedUserLabel.setStyleAttribute("font-weight", "bold");
        labelListContainer.add(projectSharedUserLabel, new BorderLayoutData(
                Style.LayoutRegion.EAST));
        verticalPanel.add(labelListContainer);
        //
        final DualListField<GPSimpleUser> lists = new DualListField<GPSimpleUser>();
        lists.setMode(Mode.INSERT);
        lists.setHeight("" + GPProjectManagementWidget.COMPONENT_HEIGHT / 1.75);
        lists.setStyleAttribute("margin-left", "11px");
        lists.setHideLabel(Boolean.TRUE);

        ListField<GPSimpleUser> from = lists.getFromList();
        from.setDisplayField(GPSimpleUserKeyValue.NAME.toString());
        this.fromStore = new ListStore<GPSimpleUser>();
        from.setStore(this.fromStore);
        ListField<GPSimpleUser> to = lists.getToList();
        to.setDisplayField(GPSimpleUserKeyValue.NAME.toString());
        this.toStore = new ListStore<GPSimpleUser>();
        to.setStore(this.toStore);
        Button cancelButton = new Button(LayerModuleConstants.INSTANCE.
                ShareProjectPanel_cancelButtonText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.gear()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        ShareProjectPanel.this.reset();
                        projectManagementWidget.showSearchProjectPanel();
                    }
                });
        super.addButton(cancelButton);
        saveButton = new Button(ButtonsConstants.INSTANCE.saveText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.save()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (project.isDefaultProject()
                        && !MementoModuleInjector.MainInjector.getInstance().getMementoSave().isEmpty()) {
                            GeoPlatformMessage.confirmMessage(
                                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_unsavedOperationsText(),
                                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_unsavedOperationMessageText(),
                                    new Listener<MessageBoxEvent>() {
                                        @Override
                                        public void handleEvent(MessageBoxEvent be) {
                                            if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                                                PeekCacheEvent peekCacheEvent = new PeekCacheEvent();
                                                LayerHandlerManager.fireEvent(peekCacheEvent);
                                            } else {
                                                GeoPlatformMessage.errorMessage(
                                                LayerModuleConstants.INSTANCE.ShareProjectPanel_shareVerifySaveOperationsTitleText(),
                                                LayerModuleConstants.INSTANCE.ShareProjectPanel_shareVerifySaveOperationsMessageText());
                                            }
                                        }
                                    });
                        } else {
                            toStore.commitChanges();
                            List<Long> accountIDsProject = Lists.<Long>newArrayListWithCapacity(
                                    toStore.getModels().size());
                            IGPAccountDetail accountDetail = Registry.get(
                                    UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
                            boolean test = false;
                            for (GPSimpleUser user : toStore.getModels()) {
                                if (user.getId().equals(accountDetail.getId())) {
                                    test = true;
                                }
                                accountIDsProject.add(user.getId());
                            }
                            final boolean isShared = test && accountIDsProject.size() > 1;
                            ShareProjectPanel.this.reset();

                            shareProjectReq.setIdSharedProject(project.getId());
                            shareProjectReq.setAccountIDsProject(accountIDsProject);

                            GPClientCommandExecutor.executeCommand(
                                    new GPClientCommand<ShareProjectResponse>() {

                                        private static final long serialVersionUID = 1596346272632793993L;

                                        {
                                            super.setCommandRequest(shareProjectReq);
                                        }

                                        @Override
                                        public void onCommandSuccess(
                                                ShareProjectResponse response) {
                                                    if (project.isDefaultProject()) {
                                                        GPClientProject projInSession = Registry.get(
                                                                UserSessionEnum.CURRENT_PROJECT_ON_TREE.name());
                                                        projInSession.setProjectShared(
                                                                isShared);
                                                    }
                                                    project.setProjectShared(isShared);
                                                    loadData(project);
                                                }

                                                @Override
                                                public void onCommandFailure(
                                                        Throwable exception) {
                                                            logger.warning(
                                                                    "Error on saving user to share project");
                                                        }
                                    });
                        }
                    }
                });
        super.addButton(saveButton);
        super.add(verticalPanel);
        super.add(lists, new FormData("98%"));
        LayoutContainer filterContainer = new LayoutContainer(new BorderLayout());
        this.fromFilter = this.createServerFilter(this.fromFilter, fromStore,
                LayerModuleConstants.INSTANCE.ShareProjectPanel_fromFilterLabelText());
        filterContainer.add(this.fromFilter, new BorderLayoutData(
                Style.LayoutRegion.WEST));
        this.toFilter = this.createServerFilter(this.toFilter, toStore,
                LayerModuleConstants.INSTANCE.ShareProjectPanel_toFilterLabelText());
        filterContainer.add(this.toFilter, new BorderLayoutData(
                Style.LayoutRegion.EAST));
        filterContainer.setStyleAttribute("margin", "11px");
        super.add(filterContainer);
    }

    @Override
    public void reset() {
        this.fromStore.removeAll();
        this.toStore.removeAll();
    }

    private StoreFilterField<GPSimpleUser> createServerFilter(
            StoreFilterField<GPSimpleUser> storeFilterField,
            ListStore<GPSimpleUser> store, String filterLabel) {
        storeFilterField = new StoreFilterField<GPSimpleUser>() {

            @Override
            protected boolean doSelect(Store<GPSimpleUser> store,
                    GPSimpleUser parent,
                    GPSimpleUser record, String property, String filter) {
                String name = record.getName().toString().toLowerCase();
                if (name.contains(filter.toLowerCase())) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        };
        storeFilterField.setEmptyText(LayerModuleConstants.INSTANCE.
                ShareProjectPanel_storeFilterEmptyText());
        storeFilterField.bind(store);
        storeFilterField.setFieldLabel(filterLabel);
        return storeFilterField;
    }

    public void loadData(GPClientProject theProject) {
        super.init();
        this.reset();
        this.project = theProject;

        getUsersToShare.setProjectId(this.project.getId());

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<GetUsersToShareProjectResponse>() {

                    private static final long serialVersionUID = 8650649319305683871L;

                    {
                        super.setCommandRequest(getUsersToShare);
                    }

                    @Override
                    public void onCommandSuccess(
                            GetUsersToShareProjectResponse response) {
                                fromStore.add(response.getResult());
                            }

                            @Override
                            public void onCommandFailure(Throwable exception) {
                                System.out.println(
                                        "Failled to load Organization Users to Share Project: "
                                        + exception);
                            }
                });

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
                layerRemote.getAccountsFromSharedProject(
                        project.getId(),
                        new AsyncCallback<ArrayList<GPSimpleUser>>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                logger.warning(
                                        "Failled to load Share Project's Accounts: " + caught);
                            }

                            @Override
                            public void onSuccess(ArrayList<GPSimpleUser> result) {
                                toStore.add(result);
                            }
                        });
            }
        });

        boolean enableMenu = false;
        IGPAccountDetail accountInSession = Registry.get(
                UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        if (theProject.getOwner() == null || theProject.getOwner().getId().equals(
                accountInSession.getId())) {
            enableMenu = true;
        }
        saveButton.setEnabled(enableMenu);
        this.updateLabels();
    }

    private void updateLabels() {
        IGPAccountDetail accountDetail = Registry.get(
                UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        this.projectNameLabel.setHtml(
                PROJECT_NAME_LABEL + this.project.getName());
        if (project.getOwner() != null) {
            this.ownerLabel.setHtml(OWNER_LABEL + project.getOwner().getName());
        } else {
            this.ownerLabel.setHtml(OWNER_LABEL + accountDetail.getName());
        }
        this.organizationLabel.setHtml(
                ORGANIZATION_LABEL + accountDetail.getOrganization());
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(Boolean.FALSE);
        super.setBorders(Boolean.FALSE);
        super.setBodyBorder(Boolean.FALSE);
        super.setLayout(new FormLayout());
    }
}
