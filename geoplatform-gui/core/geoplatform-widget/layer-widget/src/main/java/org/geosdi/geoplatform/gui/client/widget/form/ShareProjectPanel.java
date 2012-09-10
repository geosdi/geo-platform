/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
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
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUser;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUserKeyValue;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ShareProjectPanel extends GeoPlatformContentPanel {

    private final static String OWNER_LABEL = "Project Owner: ";
    private final static String ORGANIZATION_LABEL = "Organization: ";
    private ListStore<GPSimpleUser> fromStore;
    private ListStore<GPSimpleUser> toStore;
    private GPClientProject project;
    private GPProjectManagementWidget projectManagementWidget;
    private StoreFilterField<GPSimpleUser> toFilter;
    private StoreFilterField<GPSimpleUser> fromFilter;
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
        fieldSet.setHeading("Share Project to Users");
        fieldSet.setWidth(GPProjectManagementWidget.COMPONENT_WIDTH - 25);
        this.ownerLabel = new Label();
        this.ownerLabel.setStyleAttribute("font-size", "13");
        this.organizationLabel = new Label();
        this.organizationLabel.setStyleAttribute("font-size", "13");
        fieldSet.add(ownerLabel, new MarginData(10));
        fieldSet.add(organizationLabel, new MarginData(10));
        final DualListField<GPSimpleUser> lists = new DualListField<GPSimpleUser>();
        lists.setMode(Mode.INSERT);
        lists.setHeight("" + GPProjectManagementWidget.COMPONENT_HEIGHT / 1.75);
        lists.setStyleAttribute("margin-left", "11px");
//        lists.setFieldLabel("Users");
        lists.setHideLabel(Boolean.TRUE);

        ListField<GPSimpleUser> from = lists.getFromList();
        from.setDisplayField(GPSimpleUserKeyValue.NAME.toString());
        this.fromStore = new ListStore<GPSimpleUser>();
        from.setStore(this.fromStore);
        ListField<GPSimpleUser> to = lists.getToList();
        to.setDisplayField(GPSimpleUserKeyValue.NAME.toString());
        this.toStore = new ListStore<GPSimpleUser>();
        to.setStore(this.toStore);
        Button cancelButton = new Button("Cancel", BasicWidgetResources.ICONS.delete(),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        ShareProjectPanel.this.reset();
                        projectManagementWidget.showSearchProjectPanel();
                    }
                });
        super.addButton(cancelButton);
        Button saveButton = new Button("Save", BasicWidgetResources.ICONS.save(),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        //ADD: Code to save
                        toStore.commitChanges();
                        List<Long> accountIDsProject = Lists.newArrayListWithCapacity(toStore.getModels().size());
                        for (GPSimpleUser user : toStore.getModels()) {
                            accountIDsProject.add(user.getId());
                        }
                        ShareProjectPanel.this.reset();
                        LayerRemote.Util.getInstance().shareProjectToUsers(project.getId(),
                                accountIDsProject, new AsyncCallback<Boolean>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                System.out.println("Error on saving user to share project");
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                project.setShared(Boolean.TRUE);
                                loadData(project);
                            }
                        });
                    }
                });
        super.addButton(saveButton);
        verticalPanel.add(fieldSet);
        super.add(verticalPanel);
        super.add(lists, new FormData("98%"));
        LayoutContainer filterContainer = new LayoutContainer(new BorderLayout());
        this.fromFilter = this.createServerFilter(this.fromFilter, fromStore, "Organization Users");
        filterContainer.add(this.fromFilter, new BorderLayoutData(Style.LayoutRegion.WEST));
        this.toFilter = this.createServerFilter(this.toFilter, toStore, "Sharing User List");
        filterContainer.add(this.toFilter, new BorderLayoutData(Style.LayoutRegion.EAST));
        filterContainer.setStyleAttribute("margin", "11px");
        super.add(filterContainer);
    }

    @Override
    public void reset() {
        this.fromStore.removeAll();
        this.toStore.removeAll();
//        this.fromFilter.clear();
//        this.toFilter.clear();
    }

    private StoreFilterField<GPSimpleUser> createServerFilter(StoreFilterField<GPSimpleUser> storeFilterField,
            ListStore<GPSimpleUser> store, String filterLabel) {
        storeFilterField = new StoreFilterField<GPSimpleUser>() {
            @Override
            protected boolean doSelect(Store<GPSimpleUser> store, GPSimpleUser parent,
                    GPSimpleUser record, String property, String filter) {
                String name = record.getName().toString().toLowerCase();
                if (name.contains(filter.toLowerCase())) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        };
        storeFilterField.setEmptyText("Type the user to filter");
        storeFilterField.bind(store);
        storeFilterField.setFieldLabel(filterLabel);
        return storeFilterField;
    }

    public void loadData(GPClientProject project) {
        super.init();
        this.project = project;
        LayerRemote.Util.getInstance().getOrganizationUsersToShareProject(project.getId(), new AsyncCallback<ArrayList<GPSimpleUser>>() {
            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(ArrayList<GPSimpleUser> result) {
                fromStore.add(result);
            }
        });
        LayerRemote.Util.getInstance().getAccountsFromSharedProject(
                project.getId(), new AsyncCallback<ArrayList<GPSimpleUser>>() {
            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(ArrayList<GPSimpleUser> result) {
                toStore.add(result);
            }
        });
        this.updateLabels();
    }

    private void updateLabels() {
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        if (project.getOwner() != null) {
            this.ownerLabel.setText(OWNER_LABEL + project.getOwner().getName());
        } else {
            this.ownerLabel.setText(OWNER_LABEL + accountDetail.getName());
        }
        this.organizationLabel.setText(ORGANIZATION_LABEL + accountDetail.getOrganization());
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(Boolean.FALSE);
        super.setBorders(Boolean.FALSE);
        super.setBodyBorder(Boolean.FALSE);
        super.setLayout(new FormLayout());
//        super.setStyleAttribute("margin", "10px");
    }
}
