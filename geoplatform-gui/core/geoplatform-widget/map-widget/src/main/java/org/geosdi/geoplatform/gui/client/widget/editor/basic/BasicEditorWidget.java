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
package org.geosdi.geoplatform.gui.client.widget.editor.basic;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Point;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import java.util.List;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.configuration.widget.EditorActionTool;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;

/**
 * <p> This is a Basic Editor to manage Areas of Interest or Emergency Areas.
 * <br/> This Editor not use standard OGC WFS but simple draw Line, Point or
 * Polygon on a OL Vector Layer. With this Widget is possible : <ul> <li>
 * <strong>Rotate Feature</strong> </li> <li> <strong>Resize Feature</strong>
 * </li> <li> <strong>Drag Feature</strong> </li> <li> <strong>Delete
 * Feature</strong> </li> </ul> </p>
 *
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BasicEditorWidget extends GeoPlatformWindow {

    private BasicEditorPanel editorPanel = new BasicEditorPanel();

    public BasicEditorWidget() {
        super(true);
    }

    public void show(List<EditorActionTool> actionTools,
            GeoPlatformMap geoPlatformMap, Point p) {
        if (!super.isInitialized()) {
            this.editorPanel.injectValues(actionTools, geoPlatformMap);
            super.setPagePosition(p.x, p.y + 25);
        }

        super.show();
    }

    @Override
    public void addComponent() {
        super.add(this.editorPanel);
    }

    @Override
    public void initSize() {
        super.setAutoWidth(true);
        super.setAutoHeight(true);
    }

    @Override
    public void setWindowProperties() {
        super.setResizable(false);
        super.setLayout(new FlowLayout());
        super.setModal(false);
        super.setCollapsible(false);
        super.setPlain(true);

        super.setScrollMode(Style.Scroll.AUTOY);
    }

    @Override
    public void reset() {
        this.editorPanel.resetEditorObserver();
    }
}
