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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.puregwt.binding.GPTreeBindingLayerHandler;
import org.geosdi.geoplatform.gui.client.widget.tab.LayersTabWidget;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.puregwt.properties.GPWidgetSizeHandler;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LayersPropertiesWidget extends GeoPlatformWindow implements GPTreeBindingLayerHandler, GPWidgetSizeHandler {

    private LayersTabWidget layersTabWidget;
    private final VerticalPanel vp = new VerticalPanel();
    private GPLayerBean model;

    public LayersPropertiesWidget() {
        super(true);
    }

    @Override
    public void addComponent() {
        vp.setSpacing(10);
        this.layersTabWidget = new LayersTabWidget();
        this.vp.add(this.layersTabWidget);
        super.add(this.vp);
        Button close = new Button(ButtonsConstants.INSTANCE.closeText(), new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }

        });
        super.addButton(close);
    }

    @Override
    public void initSize() {
        setWidth(400);
        setAutoHeight(true);
    }

    @Override
    public void setWindowProperties() {
        setHeadingHtml(LayerModuleConstants.INSTANCE.LayersPropertiesWidget_headingText());
        setModal(true);
        setResizable(false);
        setLayout(new FlowLayout());
        setCollapsible(true);
        addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                layersTabWidget.bind(model);
            }

        });
    }

    @Override
    public final void show() {
        if (this.model == null) {
            throw new IllegalArgumentException("Don't call this method, but call bindLayer(GPLayerBean model)");
        }
        super.show();
    }

    @Override
    public void bindLayer(GPLayerBean model) {
        this.model = model;
        this.show();
    }

    @Override
    public void updateWidgetSize(int size) {
        super.setHeight(size);
        super.doLayout();
    }

    /**
     * Add Handler to manage TreeNode Binding on Widget and window adjusts its
     * height based on the tab is clicked
     */
    public void addHandlers() {
        WidgetPropertiesHandlerManager.addHandler(GPTreeBindingLayerHandler.TYPE, this);
        WidgetPropertiesHandlerManager.addHandlerToSource(GPWidgetSizeHandler.TYPE, this.vp, this);
    }

    @Override
    public void reset() {
        this.model = null;
    }
}