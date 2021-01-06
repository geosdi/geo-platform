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
package org.geosdi.geoplatform.gui.action.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.configuration.action.GeoPlatformSecureAction;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableHandler;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPSecureButton extends Button {

    private GeoPlatformSecureAction<ButtonEvent> secureAction;

    private GPSecureButton() {
    }

    private GPSecureButton(String text) {
        super(text);
    }

    private GPSecureButton(String text, AbstractImagePrototype icon) {
        super(text, icon);
    }

    public GPSecureButton(String text, AbstractImagePrototype icon, GeoPlatformSecureAction<ButtonEvent> action) {
        super(text, icon, action);
        this.initializeSecureAction(action);
    }

    public GPSecureButton(String text, GeoPlatformSecureAction<ButtonEvent> action) {
        super(text, action);
        this.initializeSecureAction(action);
    }

    private void initializeSecureAction(GeoPlatformSecureAction<ButtonEvent> action) {
        action.addActionEnableHandler(new ActionEnableHandler() {
            @Override
            public void onActionEnabled(ActionEnableEvent event) {
                if (event.isEnabled()) {
                    GPSecureButton.super.enable();
                } else {
                    GPSecureButton.super.disable();
                }
            }
        });
        this.secureAction = action;
        this.enable();
    }

    @Override
    public void enable() {
        this.secureAction.setEnabled(Boolean.TRUE);
    }
}
