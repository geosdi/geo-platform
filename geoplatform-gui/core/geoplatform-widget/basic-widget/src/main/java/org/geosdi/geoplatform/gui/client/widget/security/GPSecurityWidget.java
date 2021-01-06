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
package org.geosdi.geoplatform.gui.client.widget.security;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPSecurityWidget extends Dialog {

    protected GPSecureStringTextField userName;
    protected GPSecureStringTextField password;
    protected Button reset;
    protected Button login;

    public GPSecurityWidget() {
        createBasicComponents();
    }

    private void createBasicComponents() {
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(90);
        layout.setDefaultWidth(180);
        setLayout(layout);

        setButtonAlign(HorizontalAlignment.LEFT);
        setButtons("");
        setIcon(AbstractImagePrototype.create(BasicWidgetResources.ICONS.search()));
        setHeadingText(BasicWidgetConstants.INSTANCE.GPSecurityWidget_headingText());
        setModal(true);
        setBodyBorder(true);
        setBodyStyle("padding: 8px;background: none");
        setWidth(330);
        setResizable(false);
        setClosable(false);

        KeyListener keyListener = new KeyListener() {
            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == KeyCodes.KEY_ENTER && login.isEnabled()) {
                    onSubmit();
                }
            }

            @Override
            public void componentKeyUp(ComponentEvent event) {
                validate();
            }
        };
        userName = new GPSecureStringTextField();
        userName.setFieldLabel(BasicWidgetConstants.INSTANCE.GPSecurityWidget_usernameText());
//        userName.setValue("admin");
        userName.addKeyListener(keyListener);
        add(userName);

        password = new GPSecureStringTextField();
        password.setPassword(true);
        password.setFieldLabel(BasicWidgetConstants.INSTANCE.GPSecurityWidget_passwordText());
//        password.setValue("admin");
        password.addKeyListener(keyListener);
        add(password);

        setFocusWidget(userName);
    }

    @Override
    protected void createButtons() {
        super.createButtons();

        addStatusComponent();

        reset = new Button(ButtonsConstants.INSTANCE.resetText(), 
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()));
        reset.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                reset();
            }
        });

        login = new Button(ButtonsConstants.INSTANCE.loginText(), 
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.login()));
        login.disable();
        login.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                onSubmit();
            }
        });

        addButton(reset);
        addButton(login);
    }

    protected boolean hasValue(GPSecureStringTextField field) {
        return field.getValue() != null && field.getValue().length() > 0;
    }

    protected void validate() {
        login.setEnabled(hasValue(userName) && hasValue(password));
    }

    public abstract void onSubmit();

    public abstract void addStatusComponent();

    public abstract void reset();
}
