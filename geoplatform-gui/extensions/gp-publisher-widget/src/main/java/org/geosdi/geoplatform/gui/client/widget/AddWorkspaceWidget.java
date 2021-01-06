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

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.service.PublisherRemote;
import org.geosdi.geoplatform.gui.client.widget.form.GenericFormWidget;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class AddWorkspaceWidget extends GenericFormWidget {

    private GPSecureStringTextField workspaceNameTextField;
    private Button save;
    private Button cancel;

    public AddWorkspaceWidget(boolean lazy) {
        super(lazy);
    }

    @Override
    public void addComponentToForm() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setHeadingHtml(PublisherWidgetConstants.INSTANCE.
                AddWorkspaceWidget_fieldSetHeadingText());

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        fieldSet.setLayout(layout);

        this.workspaceNameTextField = new GPSecureStringTextField();
        this.workspaceNameTextField.setFieldLabel(PublisherWidgetConstants.INSTANCE.
                AddWorkspaceWidget_nameText());

        this.workspaceNameTextField.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (workspaceNameTextField.getValue() == null) {
                    if ((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                            || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                        reset();
                    }
                } else {
                    if (workspaceNameTextField.getValue().length() > 0) {
                        save.enable();
                    } else {
                        save.disable();
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)
                        && (workspaceNameTextField.getValue() != null)
                        && (workspaceNameTextField.getValue().length() > 0)) {
                    execute();
                }
            }

        });

        this.fieldSet.add(this.workspaceNameTextField);

        this.getFormPanel().add(this.fieldSet);

        this.saveStatus = new SaveStatus();
        this.saveStatus.setAutoWidth(true);

        this.getFormPanel().getButtonBar().add(this.saveStatus);

        getFormPanel().setButtonAlign(HorizontalAlignment.RIGHT);

        save = new Button(ButtonsConstants.INSTANCE.createText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }

                });

        save.setEnabled(false);

        this.getFormPanel().addButton(save);

        this.cancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        clearComponents();
                    }

                });

        this.getFormPanel().addButton(cancel);

        setFocusWidget(this.workspaceNameTextField);
    }

    @Override
    public void initSize() {
        setHeadingHtml(
                PublisherWidgetConstants.INSTANCE.AddWorkspaceWidget_workspaceNameText());
        setSize(330, 170);
    }

    @Override
    public void initSizeFormPanel() {
        this.getFormPanel().setHeaderVisible(false);
        this.getFormPanel().setSize(280, 120);
    }

    @Override
    public void execute() {
        PublisherRemote.Util.getInstance().createWorkspace(
                this.workspaceNameTextField.getValue(), false, new AsyncCallback<Boolean>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage(WindowsConstants.INSTANCE.errorTitleText(),
                                WindowsConstants.INSTANCE.errorSavingTitleText() + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        if (result) {
                            clearComponents();
                            AddWorkspaceWidget.super.hide();
                            GeoPlatformMessage.infoMessage(WindowsConstants.INSTANCE.infoTitleText(),
                                    WindowsConstants.INSTANCE.operationCompletedWithSuccessText());
                        } else {
                            GeoPlatformMessage.errorMessage(WindowsConstants.INSTANCE.errorTitleText(),
                                    WindowsConstants.INSTANCE.errorSavingTitleText());
                        }
                    }

                });
    }

    @Override
    public void reset() {
        this.save.disable();
        this.workspaceNameTextField.clear();
        this.saveStatus.clearStatus("");
        setFocusWidget(this.workspaceNameTextField);
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

}
