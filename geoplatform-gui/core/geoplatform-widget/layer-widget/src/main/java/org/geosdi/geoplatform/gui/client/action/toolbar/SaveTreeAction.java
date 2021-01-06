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
package org.geosdi.geoplatform.gui.client.action.toolbar;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.action.tree.ToolbarLayerTreeAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.GPPeekCacheEventHandler;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.plugin.tree.toolbar.SaveTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.puregwt.refresh.support.GPCompositeRefreshHandlerSupport;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.LayerEvents;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.observable.Observable;
import org.geosdi.geoplatform.gui.observable.Observer;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.puregwt.savecache.SaveCacheHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;

import static org.geosdi.geoplatform.gui.client.puregwt.refresh.GPCompositeRefreshHandler.LOAD_ROOT_ELEMENTS_EVENT;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class SaveTreeAction extends ToolbarLayerTreeAction implements GPPeekCacheEventHandler, Observer {

    private DisplayLayersProgressBarEvent displayEvent = new DisplayLayersProgressBarEvent(true);
    private boolean visibiltyProgressBar;
    private GwtEvent eventAfterAllSaveOperations;
    private SaveTreeToolbarPlugin savePlugin;

    /**
     * @param theTree
     * @param savePlugin
     */
    public SaveTreeAction(TreePanel theTree, SaveTreeToolbarPlugin savePlugin) {
        super(theTree, AbstractImagePrototype.create(BasicWidgetResources.ICONS.save()),
                LayerModuleConstants.INSTANCE.SaveTreeAction_tooltipText());
        displayEvent.setMessage(LayerModuleConstants.INSTANCE.savingOperationsText());
        TimeoutHandlerManager.addHandler(GPPeekCacheEventHandler.TYPE, this);
        LayerHandlerManager.addHandler(GPPeekCacheEventHandler.TYPE, this);
        this.savePlugin = savePlugin;
        Boolean permission = GPAccountLogged.getInstance().hasComponentPermission(savePlugin.getId());
        if (permission) { // Observ only if the pemission is true
            IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
            mementoSave.getObservable().addObserver(this);
        }
    }

    @Override
    public void componentSelected(ButtonEvent ce) {
        this.showProgressBar();
        this.peek();
    }

    @Override
    public void update(Observable o, Object o1) {
        //System.out.println("SaveTreeAction receive observable notify");
        if (LayerEvents.SAVE_CACHE_NOT_EMPTY == ((EventType) o1)) {
            this.savePlugin.setEnabledByStatus(TreeStatusEnum.SAVE_CACHE_NOT_EMPTY);
        } else {
            this.savePlugin.setEnabledByStatus(TreeStatusEnum.SAVE_CACHE_EMPTY);
        }
    }

    public void peek() {
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        if (mementoSave.peek() != null) {
            IMemento<ISave> memento = mementoSave.peek();
            memento.getAction().executeSave(memento);
        } else {
            this.displayEvent.setVisible(false);
            LayerHandlerManager.fireEvent(this.displayEvent);
            this.visibiltyProgressBar = false;
            if (this.eventAfterAllSaveOperations != null) {
                SaveCacheHandlerManager.fireEvent(this.eventAfterAllSaveOperations);
                this.eventAfterAllSaveOperations = null;
            }
            GPCompositeRefreshHandlerSupport.fireCompositeRefreshEvent(LOAD_ROOT_ELEMENTS_EVENT);
        }
    }

    //The GwtEvent eventAfterAllSaveOperations is the event that must be called at the
    //end of all the save operations, this event will be fired using the SaveCacheHandlerManager
    @Override
    public void peek(GwtEvent eventAfterAllSaveOperations) {
        if (eventAfterAllSaveOperations != null) {
            this.eventAfterAllSaveOperations = eventAfterAllSaveOperations;
        }
        this.showProgressBar();
        this.peek();
    }

    private void showProgressBar() {
        if (!visibiltyProgressBar) {
            this.displayEvent.setVisible(true);
            LayerHandlerManager.fireEvent(this.displayEvent);
            this.visibiltyProgressBar = true;
        }
    }
}