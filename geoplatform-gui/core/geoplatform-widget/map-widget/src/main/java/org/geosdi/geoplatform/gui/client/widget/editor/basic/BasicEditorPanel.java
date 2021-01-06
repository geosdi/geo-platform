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

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import java.util.List;
import org.geosdi.geoplatform.gui.action.GeoPlatformButtonObserver;
import org.geosdi.geoplatform.gui.action.IEditorMapAction;
import org.geosdi.geoplatform.gui.action.ToolbarAction;
import org.geosdi.geoplatform.gui.action.ToolbarActionRegistar;
import org.geosdi.geoplatform.gui.action.ToolbarMapAction;
import org.geosdi.geoplatform.gui.action.button.GeoPlatformButton;
import org.geosdi.geoplatform.gui.action.button.GeoPlatformToggleButton;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.configuration.WidgetGenericTool;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableHandler;
import org.geosdi.geoplatform.gui.configuration.widget.EditorActionTool;
import org.geosdi.geoplatform.gui.configuration.widget.IGeoPlatformEditor;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BasicEditorPanel extends GeoPlatformContentPanel implements
        IGeoPlatformEditor {

    private GeoPlatformMap geoPlatformMap;
    private List<EditorActionTool> actionTools;
    private VerticalPanel vp;
    private GeoPlatformButtonObserver editorObserver = new GeoPlatformButtonObserver();

    public BasicEditorPanel() {
        super(true);
        this.vp = new VerticalPanel();
    }

    @Override
    public void addComponent() {
        if ((this.actionTools == null) || (this.actionTools.isEmpty())) {
            throw new IllegalStateException("The Action Tools List "
                    + "for BasicEditoPanel must not be null or empty.");
        }

        this.addEditorButtons();
        super.add(vp);
    }

    @Override
    public void initSize() {
        this.vp.setAutoHeight(true);
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(false);
    }

    protected void addEditorButtons() {
        for (EditorActionTool tool : this.actionTools) {
            tool.buildTool(this);
        }

        super.layout();
    }

    @Override
    public void addButton(EditorActionTool tool) {
        ToolbarMapAction action = (ToolbarMapAction) this.getAction(tool.getId());

        final GeoPlatformButton button = new GeoPlatformButton();
        button.setAction(action);
        this.prepareButton(button, action, tool);

        ((IEditorMapAction) action).setEditorObserver(editorObserver);

        vp.add(button);
    }

    @Override
    public void addToogleButton(EditorActionTool tool) {
        ToolbarMapAction action = (ToolbarMapAction) this.getAction(tool.getId());

        final GeoPlatformToggleButton button = new GeoPlatformToggleButton();
        button.setAction(action);
        this.prepareButton(button, action, tool);

        ((IEditorMapAction) action).setEditorObserver(editorObserver);

        vp.add(button);
    }

    /**
     *
     * @param String id
     *
     * @return {@link ToolbarAction} associated with {@link EditorActionTool} ID
     */
    protected final ToolbarAction getAction(String id) {
        ToolbarActionRegistar toolbarRegistar = BasicGinInjector.MainInjector.
                getInstance().getToolbarActionRegistar();
        ToolbarAction action = toolbarRegistar.get(id, geoPlatformMap);
        if (action == null) {
            throw new NullPointerException(
                    "The action with ID " + id + " is non existent");
        }
        return action;
    }

    /**
     * <p>Inject in button all Action Properties and assign to the Action the
     * {@link ActionEnableHandler} Handler to Enable / Disable it. </p>
     *
     * @param button
     * @param action
     * @param tool
     */
    protected final void prepareButton(final Button button, ToolbarAction action,
            WidgetGenericTool<IGeoPlatformEditor> tool) {
        button.setId(action.getId());
        button.setToolTip(action.getTooltip());
        button.setIcon(action.getImage());
        button.addSelectionListener(action);

        action.addActionEnableHandler(new ActionEnableHandler() {
            @Override
            public void onActionEnabled(ActionEnableEvent event) {
                button.setEnabled(event.isEnabled());
            }
        });

        action.setEnabled(tool.isEnabled());
    }

    /**
     *
     * @param actionTools
     * @param theGeoPlatformMap
     */
    public void injectValues(List<EditorActionTool> actionTools,
            GeoPlatformMap theGeoPlatformMap) {
        this.actionTools = actionTools;
        this.geoPlatformMap = theGeoPlatformMap;
    }

    public void resetEditorObserver() {
        this.editorObserver.changeButtonState();
    }
}
