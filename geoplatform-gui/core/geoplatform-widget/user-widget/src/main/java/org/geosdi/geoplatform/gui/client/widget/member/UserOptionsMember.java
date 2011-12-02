/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.member;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout.BoxLayoutPack;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public abstract class UserOptionsMember implements IUserOptionsMember {

    private IGPUserManageDetail user;
//    private FormBinding formBinding;
    //
    private String name;
    private LayoutContainer container;
    private ContentPanel panelOption;
    private Button save;

    public UserOptionsMember(IGPUserManageDetail user, String name, LayoutContainer container) {
        this.user = user;
        this.name = name;
        this.container = container;
        this.createPanelOption();
        this.createSaveButton();
    }

    @Override
    public String getName() {
        return name;
    }

    private void createPanelOption() {
        VBoxLayout layout = new VBoxLayout();
        layout.setPadding(new Padding(5));
        layout.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
        layout.setPack(BoxLayoutPack.START);

        panelOption = new ContentPanel(layout);
        panelOption.setHeaderVisible(false);

        this.creteLayoutData(panelOption);
    }

    private void createSaveButton() {
        save = new Button("Save", BasicWidgetResources.ICONS.done(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        saveOptions();
                    }
                });

        panelOption.addButton(save);
    }

    @Override
    public final Listener<ButtonEvent> getListener() {
        return new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent ce) {
                if (!ce.<ToggleButton>getComponent().isPressed()) {
                    return;
                }
                switchPanel();
            }
        };
    }

    protected abstract void creteLayoutData(ContentPanel panel);

    protected void switchPanel() {
        container.removeAll();
        container.add(panelOption);
        container.layout();
    }

    public IGPUserManageDetail getUser() {
        return user;
    }

    public void setUser(IGPUserManageDetail user) {
        this.user = user;
    }
}
