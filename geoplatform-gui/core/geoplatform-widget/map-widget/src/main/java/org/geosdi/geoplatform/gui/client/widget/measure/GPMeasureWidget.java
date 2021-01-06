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
package org.geosdi.geoplatform.gui.client.widget.measure;

import java.util.ArrayList;
import java.util.Stack;

import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.MeasureChangeHandler;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.util.Point;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Accessibility;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI Group
 *
 */
public class GPMeasureWidget extends ContentPanel implements
        MeasureChangeHandler {

    private static Stack<GPMeasureWidget> infoStack = new Stack<GPMeasureWidget>();
    private static ArrayList<GPMeasureWidget> slots = Lists.<GPMeasureWidget>newArrayList();
    protected GPMeasureConfig config;
    protected int level;
    private Size size;

    /**
     * Creates a new GPScaleWidget instance.
     */
    public GPMeasureWidget() {
        baseStyle = "x-info";
        frame = true;
        setShadow(false);
        setLayoutOnChange(true);

        MapHandlerManager.addHandler(MeasureChangeHandler.TYPE, this);
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        if (GXT.isAriaEnabled()) {
            Accessibility.setRole(getElement(), "alert");
        }
    }

    public static void display(String title, String text) {
        display(new GPMeasureConfig(title, text));
    }

    public static void display(String title) {
        display(new GPMeasureConfig(title));
    }

    public static void display(GPMeasureConfig config) {
        pop().show(config);
    }

    public static void remove() {
        pop().hide();
    }

    private static GPMeasureWidget pop() {
        GPMeasureWidget info = infoStack.size() > 0
                ? (GPMeasureWidget) infoStack
                .pop() : null;
        if (info == null) {
            info = new GPMeasureWidget();
        }
        return info;
    }

    /**
     * Displays the MeasureWidget.
     *
     * @param config the info config
     */
    public void show(GPMeasureConfig config) {
        this.config = config;
        onShowInfo();
    }

    protected void onShowInfo() {
        RootPanel.get().add(this);
        el().makePositionable(true);

        setTitle();
        setText();

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

    protected Point position() {
        this.size = XDOM.getViewportSize();
        int left = this.size.width - config.width - 10
                + XDOM.getBodyScrollLeft();
        int top = this.size.height - config.height - 10
                - (level * (config.height + 10)) + XDOM.getBodyScrollTop();
        return new Point(left, top);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.client.widget.scale.event.ScaleChangeHandler#onPositionChange(com.extjs.gxt.ui.client.util.Size)
     */
    @Override
    public void onPositionChange(Size s) {
        if (this.size != s) {
            this.size = s;
            int left = this.size.width - config.width - 10
                    + XDOM.getBodyScrollLeft();
            int top = this.size.height - config.height - 10
                    - (level * (config.height + 10)) + XDOM.getBodyScrollTop();
            Point p = new Point(left, top);
            el().setLeftTop(p.x, p.y);
        }

    }

    @Override
    public void activationMeasure(boolean activate) {
        if (activate) {
            show();
        } else {
            hide();
        }

    }
}
