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
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.member.UserOptionsMainPanel;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class UserOptionsWidget extends GeoPlatformWindow {

    private UserOptionsMainPanel optionsMainPanel = new UserOptionsMainPanel();

    public UserOptionsWidget(boolean lazy) {
        super(lazy);
    }

    @Override
    public void initSize() {
        super.setSize(600, 500);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(UserModuleConstants.INSTANCE.UserOptionsWidget_headingText());
        super.setResizable(Boolean.FALSE);
        super.setLayout(new BorderLayout());
        super.setModal(Boolean.TRUE);
        super.setCollapsible(Boolean.FALSE);
        super.setPlain(Boolean.TRUE);

        super.addListener(Events.Show, new Listener<WindowEvent>() {
            @Override
            public void handleEvent(WindowEvent be) {
                optionsMainPanel.createMembers();
            }
        });
    }

    @Override
    public void addComponent() {
        super.add(optionsMainPanel.getPanelMain());
        this.addButtons();
    }

    private void addButtons() {
        Button close = new Button(ButtonsConstants.INSTANCE.closeText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });

        super.setButtonAlign(HorizontalAlignment.RIGHT);
        super.addButton(close);
    }
}
