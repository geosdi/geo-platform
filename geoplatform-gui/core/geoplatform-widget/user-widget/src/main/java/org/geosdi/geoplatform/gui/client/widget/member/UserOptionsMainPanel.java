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
package org.geosdi.geoplatform.gui.client.widget.member;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import java.util.List;
import org.geosdi.geoplatform.gui.configuration.users.options.member.GPMemberOptionType;
import org.geosdi.geoplatform.gui.configuration.users.options.member.GeoPlatformOptionsMember;
import org.geosdi.geoplatform.gui.configuration.users.options.member.IGPMemberOptionManager;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;
import org.geosdi.geoplatform.gui.impl.users.options.factory.GeoPlatformMemberFactory;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class UserOptionsMainPanel {

    private ContentPanel panelMain;
    //
    private ContentPanel panelWest;
    private ContentPanel panelCenter;
    private boolean initialized;

    public UserOptionsMainPanel() {
        this.createPanels();
    }

    private void createPanels() {
        panelMain = new ContentPanel();
        panelMain.setHeaderVisible(false);
        panelMain.setSize(590, 430);
        panelMain.setLayout(new BorderLayout());

        // West
        panelWest = new ContentPanel();
        panelWest.setHeaderVisible(false);

        VBoxLayout westLayout = new VBoxLayout();
        westLayout.setPadding(new Padding(5));
        westLayout.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);
        panelWest.setLayout(westLayout);

        BorderLayoutData west = new BorderLayoutData(LayoutRegion.WEST, 120);
        west.setMargins(new Margins(5));

        panelMain.add(panelWest, west);

        // Center
        panelCenter = new ContentPanel();
        panelCenter.setHeaderVisible(false);
        panelCenter.setLayout(new FitLayout());

        BorderLayoutData center = new BorderLayoutData(LayoutRegion.CENTER);
        center.setMargins(new Margins(5));

        panelMain.add(panelCenter, center);
    }

    public void createMembers() {
        if (!initialized) {
            VBoxLayoutData vBoxData = new VBoxLayoutData(5, 5, 5, 5);
            vBoxData.setFlex(1);

            IGPMemberOptionManager memberManager = GeoPlatformMemberFactory.getDefaultMemberManager(GPMemberOptionType.SIMPLE_PROPERTIES);
            for (GeoPlatformOptionsMember member : (List<GeoPlatformOptionsMember>) memberManager.getMembers()) {
                member.setLayoutContainer(panelCenter);
                member.setOwnUser((IGPUserManageDetail) Registry.get(UserSessionEnum.USER_MANAGE_DETAIL_IN_SESSION.toString()));
                panelWest.add(this.createToggleButton(member), vBoxData);
            }
            this.initialized = true;
            panelWest.layout();
        }
    }

    private ToggleButton createToggleButton(GeoPlatformOptionsMember member) {
        ToggleButton button = new ToggleButton(member.getName());
        button.setToggleGroup("vboxlayoutbuttons");
        button.addListener(Events.Toggle, member.getListener());
        button.setAllowDepress(false);
        return button;
    }

    public ContentPanel getPanelMain() {
        return panelMain;
    }
}
