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
package org.geosdi.geoplatform.gui.client.widget.scale;

import java.util.List;

import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.ScaleChangeHandler;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.util.Point;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.google.common.collect.Lists;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Accessibility;
import com.google.gwt.user.client.ui.RootPanel;
import org.geosdi.geoplatform.gui.client.i18n.MapModuleConstants;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.ScaleSelectorEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.scale.GPScaleBean;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI Group
 *
 */
public class GPScaleWidget extends ContentPanel implements ScaleChangeHandler {

    private static GPScaleWidget instance = new GPScaleWidget();
    protected GPScaleConfig config;
    protected int level;
    private Size size;
    private ScaleSelectorEvent selectorEvent = new ScaleSelectorEvent();

    /**
     * Creates a new GPScaleWidget instance.
     */
    private GPScaleWidget() {
        baseStyle = "x-info";
        frame = true;
        setShadow(false);
        setLayoutOnChange(true);

        MapHandlerManager.addHandler(ScaleChangeHandler.TYPE, this);
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        if (GXT.isAriaEnabled()) {
            Accessibility.setRole(getElement(), "alert");
        }
    }

    public static void display(String title) {
        display(new GPScaleConfig(title));
    }

    public static void display(GPScaleConfig config) {
        pop().show(config);
    }

    public static void remove() {
        pop().hide();
    }

    private static GPScaleWidget pop() {
        return instance;
    }

    /**
     * Displays the ScaleBar.
     *
     * @param config the info config
     */
    public void show(GPScaleConfig config) {
        this.config = config;
        onShowInfo();
    }

    protected void onShowInfo() {
        Draggable d = new Draggable(this, this.getHeader());
        d.setContainer(LayoutManager.getInstance().getCenter());
        d.setUseProxy(false);

        RootPanel.get().add(this);
        el().makePositionable(true);

        setTitle();
        setText();

        List<GPScaleBean> scales = Lists.<GPScaleBean>newArrayList();
        scales.add(new GPScaleBean("1:1000"));
        scales.add(new GPScaleBean("1:10000"));
        scales.add(new GPScaleBean("1:100000"));
        scales.add(new GPScaleBean("1:1000000"));
        scales.add(new GPScaleBean("1:10000000"));
        scales.add(new GPScaleBean("1:100000000"));
        scales.add(new GPScaleBean("1:1000000000"));

        ListStore<GPScaleBean> scaleStore = new ListStore<GPScaleBean>();
        scaleStore.add(scales);

        ComboBox<GPScaleBean> comboScale = new ComboBox<GPScaleBean>();
        comboScale.setEmptyText(MapModuleConstants.INSTANCE.GPScaleWidget_comboScaleEmptyText());
        comboScale.setDisplayField("scale");
        comboScale.setWidth(150);
        comboScale.setStore(scaleStore);
        comboScale.setTypeAhead(true);
        comboScale.setTriggerAction(TriggerAction.ALL);

        comboScale.setEditable(true);
        comboScale.setForceSelection(false);
        comboScale.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                String result = null;
                try {
                    Long.parseLong(value.substring(2));
                } catch (NumberFormatException nfe) {
                    result = "The scale value: " + value.substring(2) + " is not a number";
                }
                if (!value.startsWith("1:")) {
                    String error = "The scale value must start with string 1:";
                    result = (result == null) ? error : (result += '\n' + error);
                }
                return result;
            }
        });

        add(comboScale);

        Point p = position(XDOM.getViewportSize());
        el().setLeftTop(p.x, p.y);
        setSize(config.width, config.height);

        comboScale.addListener(Events.Select, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent fe) {
                ComboBox cb = (ComboBox) fe.getComponent();
                GPScaleBean s = (GPScaleBean) cb.getValue();
                scaleSelectedElement(s);
            }
        });
        comboScale.addListener(Events.OnKeyPress, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent fe) {
                ComboBox cb = (ComboBox) fe.getComponent();
                if (fe.getKeyCode() == KeyCodes.KEY_ENTER && cb.validate()) {
                    GPScaleBean scaleBean = new GPScaleBean(cb.getRawValue());
                    scaleSelectedElement(scaleBean);
                }

            }
        });

        el().slideIn(Direction.DOWN, FxConfig.NONE);

    }

    private void scaleSelectedElement(GPScaleBean scaleBean) {
        selectorEvent.setValue(scaleBean);
        MapHandlerManager.fireEvent(selectorEvent);
    }

    protected Point position(Size s) {
        this.size = s;
        int left = LayoutManager.getInstance().getCenter().getWidth()
                + LayoutManager.getInstance().getCenter().getPosition(true).x - this.config.width - 10;
        int top = LayoutManager.getInstance().getCenter().getPosition(true).y
                + LayoutManager.getInstance().getCenter().getHeight() - config.height - 50;
//        int left = this.size.width - config.width - 10
//                + XDOM.getBodyScrollLeft();
//        int top = this.size.height - config.height - 54
//                - (level * (config.height + 10)) + XDOM.getBodyScrollTop();

        return new Point(left, top);
    }

    private void setTitle() {
        if (config.title != null) {
            head.setVisible(true);
            if (config.params != null) {
                config.title = Format.substitute(config.title, config.params);
            }
            setHeadingHtml(config.title);
        } else {
            head.setVisible(false);
        }
    }

    private void setText() {
        if (config.text != null) {
            if (config.params != null) {
                config.text = Format.substitute(config.text, config.params);
            }
            removeAll();
            addText(config.text);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.geosdi.geoplatform.gui.client.widget.scale.event.ScaleChangeHandler
     * #onPositionChange(com.extjs.gxt.ui.client.util.Size)
     */
    @Override
    public void onPositionChange(Size s) {
        if ((this.size != null) && (this.size != s)) {
            Point p = position(s);
            el().setLeftTop(p.x, p.y);
        }

    }

    @Override
    public void activationScaleBar(boolean activate) {
        if (activate) {
            show();
        } else {
            remove();
        }
    }

    public static boolean isScaleWidgetEnabled() {
        return pop().isVisible();
    }
}
