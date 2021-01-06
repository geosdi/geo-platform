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
package org.geosdi.geoplatform.gui.client.widget.tree.expander;

import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPTreeExpanderNotifier<T extends TreeModel> {

    protected TreePanel tree;
    protected T selectedElement;
    private Listener executor;
    private Listener<MessageBoxEvent> message;

    /**
     * @Constructor
     *
     * @param theTree
     */
    public GPTreeExpanderNotifier(TreePanel theTree) {
        this.tree = theTree;
        this.initListeners();
    }

    private void initListeners() {
        this.executor = new Listener() {
            
            @Override
            public void handleEvent(BaseEvent be) {
                execute();
                tree.removeListener(GeoPlatformEvents.GP_NODE_EXPANDED, this);
            }
        };

        this.message = new Listener<MessageBoxEvent>() {
            
            @Override
            public void handleEvent(MessageBoxEvent be) {
                if (be.getButtonClicked().getItemId().equalsIgnoreCase(Dialog.YES)) {
                    tree.addListener(GeoPlatformEvents.GP_NODE_EXPANDED, executor);
                    tree.setExpanded(selectedElement, true);
                } else {
                    tree.removeListener(GeoPlatformEvents.GP_NODE_EXPANDED, executor);
                    defineStatusBarCancelMessage(); // TODO CancelMessage .setStatus (check for GPMenuFolderExpander that haven't cancel message)
                }
            }
        };
    }

    /**
     * Check if the Selected Node is expanded or not.
     */
    public void checkNodeState() {
        this.selectedElement = (T) tree.getSelectionModel().getSelectedItem();
        if (!this.tree.isExpanded(this.selectedElement) && !checkNode()) {
            this.confirmExpandingMessage();
        } else {
            execute();
        }
    }

    /**
     * Confirm Expand Operation on the Folder Element.
     */
    private void confirmExpandingMessage() {
        GeoPlatformMessage.confirmMessage(BasicWidgetConstants.INSTANCE.GPTreeExpanderNotifier_confirmMessageTitleText(),
                BasicWidgetConstants.INSTANCE.GPTreeExpanderNotifier_confirmMessageBodyText(),
                this.message);
    }

    protected abstract boolean checkNode();

    protected abstract void execute();

    protected abstract void defineStatusBarCancelMessage(); // TODO CancelMessage return String
}
