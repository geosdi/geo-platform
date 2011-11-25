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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout.BoxLayoutPack;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.ui.Label;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class UserOptionsWidget extends GeoPlatformWindow {

    private ContentPanel panelWest;
    private ContentPanel panelCenter;
    private Button save;

    public UserOptionsWidget(boolean lazy) {
        super(lazy);
    }

    @Override
    public void initSize() {
        super.setSize(600, 500);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("User Options");
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        super.setModal(true);
        super.setCollapsible(false);
        super.setPlain(true);
    }

    @Override
    public void addComponent() {
        this.addPanels();
        this.addSections();
        this.addButtons();
    }

    private void addPanels() {
        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.setSize(600, 430);
        panel.setLayout(new BorderLayout());

        // West
        panelWest = new ContentPanel();
        panelWest.setHeaderVisible(false);

        VBoxLayout westLayout = new VBoxLayout();
        westLayout.setPadding(new Padding(5));
        westLayout.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);
        panelWest.setLayout(westLayout);

        BorderLayoutData west = new BorderLayoutData(LayoutRegion.WEST, 120);
        west.setMargins(new Margins(5));

        panel.add(panelWest, west);

        // Centre
        panelCenter = new ContentPanel();
        panelCenter.setHeaderVisible(false);
        panelCenter.setLayout(new FitLayout());

        BorderLayoutData center = new BorderLayoutData(LayoutRegion.CENTER);
        center.setMargins(new Margins(5));

        panel.add(panelCenter, center);

        super.add(panel);
    }

    private void addSections() {
        VBoxLayoutData vBoxData = new VBoxLayoutData(5, 5, 5, 5);
        vBoxData.setFlex(1);

        panelWest.add(this.createToggleButton("User", new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent ce) {
                if (!ce.<ToggleButton>getComponent().isPressed()) {
                    return;
                }
                LayoutContainer c = new LayoutContainer();
                VBoxLayout layout = new VBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
                layout.setPack(BoxLayoutPack.CENTER);
                c.setLayout(layout);

                VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(0, 0, 5, 0));
                c.add(new Label("Change NAME"), layoutData);
                c.add(new Label("Change EMAIL"), layoutData);
                c.add(new Label("Change PASSWORD"), layoutData);

                addToCenter(c);
            }
        }), vBoxData);

        panelWest.add(this.createToggleButton("View", new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent ce) {
                if (!ce.<ToggleButton>getComponent().isPressed()) {
                    return;
                }
                LayoutContainer c = new LayoutContainer();
                VBoxLayout layout = new VBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
                layout.setPack(BoxLayoutPack.CENTER);
                c.setLayout(layout);

                VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(0, 0, 5, 0));
                c.add(new Label("Set GARTICULA"), layoutData);
                c.add(new Label("Set SCALE"), layoutData);

                addToCenter(c);
            }
        }), vBoxData);

        panelWest.add(this.createToggleButton("Disk", new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent ce) {
                if (!ce.<ToggleButton>getComponent().isPressed()) {
                    return;
                }
                LayoutContainer c = new LayoutContainer();
                VBoxLayout layout = new VBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
                layout.setPack(BoxLayoutPack.CENTER);
                c.setLayout(layout);

                VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(0, 0, 5, 0));
                c.add(new Label("Set QUOTA"), layoutData);

                addToCenter(c);
            }
        }), vBoxData);

        panelWest.add(this.createToggleButton("Geocoding", new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent ce) {
                if (!ce.<ToggleButton>getComponent().isPressed()) {
                    return;
                }
                LayoutContainer c = new LayoutContainer();
                VBoxLayout layout = new VBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
                layout.setPack(BoxLayoutPack.CENTER);
                c.setLayout(layout);

                VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(0, 0, 5, 0));
                c.add(new Label("Set TYPE: Google or WPS"), layoutData);

                addToCenter(c);
            }
        }), vBoxData);

        panelWest.add(this.createToggleButton("Widgets", new Listener<ButtonEvent>() {

            @Override
            public void handleEvent(ButtonEvent ce) {
                if (!ce.<ToggleButton>getComponent().isPressed()) {
                    return;
                }
                LayoutContainer c = new LayoutContainer();
                VBoxLayout layout = new VBoxLayout();
                layout.setPadding(new Padding(5));
                layout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
                layout.setPack(BoxLayoutPack.CENTER);
                c.setLayout(layout);

                VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(0, 0, 5, 0));
                c.add(new Label("Active WIDGETS"), layoutData);

                addToCenter(c);
            }
        }), vBoxData);

    }

    private void addButtons() {
        save = new Button("Save", BasicWidgetResources.ICONS.done(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        save();
                    }
                });

        Button cancel = new Button("Cancel", BasicWidgetResources.ICONS.cancel(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        hide();
                    }
                });

        super.setButtonAlign(HorizontalAlignment.RIGHT);
//        super.addButton(save); // TODO Save for each panel
        super.addButton(cancel);
    }

    private void addToCenter(LayoutContainer layout) {
        panelCenter.removeAll();
        panelCenter.add(layout);
        panelCenter.layout();
    }

    private ToggleButton createToggleButton(String name, Listener<ButtonEvent> l) {
        ToggleButton button = new ToggleButton(name);
        button.setToggleGroup("vboxlayoutbuttons");
        button.addListener(Events.Toggle, l);
        button.setAllowDepress(false);
        return button;
    }

    private void save() {
        GeoPlatformMessage.infoMessage("ToDo",
                "<ul><li>Work in progress</li></ul>");
    }
}
