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
package org.geosdi.geoplatform.gui.client.model.memento.save;

import java.util.HashMap;
import java.util.Map;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.model.memento.GPCache;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.observable.Observable;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPLayerSaveCache extends GPCache<IMemento<ISave>> {

    private static final long serialVersionUID = -5458269761345444182L;
    private static GPLayerSaveCache instance = new GPLayerSaveCache();
    private ObservableGPLayerSaveCache observable = new ObservableGPLayerSaveCache();
    private Map<GPLayerTreeModel, MementoLayerOriginalProperties> modifiedLayersMap = new HashMap<GPLayerTreeModel, MementoLayerOriginalProperties>();
    private SaveLayersPropertiesAction saveAction = new SaveLayersPropertiesAction();

    public static GPLayerSaveCache getInstance() {
        return instance;
    }

    private GPLayerSaveCache() {
    }

    //The properties are copied only the first time, in this way we can save
    //only the layers effectively modified from the original one
    public void copyOriginalLayerProperties(GPLayerTreeModel layer) {
        if (!this.modifiedLayersMap.containsKey(layer)) {
            MementoLayerOriginalProperties memento = new MementoLayerOriginalProperties(this.saveAction);
            memento.setAlias(layer.getAlias());
            System.out.println("Alias setted: " + memento.getAlias());
            memento.setChecked(layer.isChecked());
            System.out.println("Check setted: " + memento.isChecked());
            if (layer instanceof RasterTreeNode) {
                memento.setOpacity(((RasterTreeNode) layer).getOpacity());
                System.out.println("Opacity setted: " + memento.getOpacity());
            }
            memento.setRefBaseElement(layer);
            this.modifiedLayersMap.put(layer, memento);
            if (super.peek() == null) {
                this.observable.setChanged();
                this.observable.notifyObservers(LayerEvents.SAVE_CACHE_NOT_EMPTY);
                /*System.out.println("Event SAVE_CACHE_NOT_EMPTY notified to "
                + this.observable.countObservers() + " observers");*/
            }
        }
    }

    @Override
    public boolean add(IMemento<ISave> memento) {
        if (super.peek() == null) {
            this.observable.setChanged();
            this.observable.notifyObservers(LayerEvents.SAVE_CACHE_NOT_EMPTY);
            /*System.out.println("Event SAVE_CACHE_NOT_EMPTY notified to "
            + this.observable.countObservers() + " observers");*/
        }
        System.out.println("GPLayerSaveCache: added " + memento.getClass().getName());
        return super.add(memento);
    }

    @Override
    public IMemento<ISave> poll() {
        IMemento<ISave> memento = super.poll();
        this.verifyEmptyCache();
        return memento;
    }

    @Override
    public IMemento<ISave> peek() {
        this.verifyEmptyCache();
        return super.peek();
    }

    @Override
    public boolean remove(Object o) {
        boolean operation = super.remove(o);
        this.verifyEmptyCache();
        return operation;
    }

    private void verifyEmptyCache() {
        if (super.peek() == null) {
            if (this.modifiedLayersMap.isEmpty()) {
                this.observable.setChanged();
                this.observable.notifyObservers(LayerEvents.SAVE_CACHE_EMPTY);
                /*System.out.println("Event SAVE_CACHE_EMPTY notified to "
                + this.observable.countObservers() + " observers");*/
            } else {
                this.prepareLayerPropertiesModify();
                this.verifyEmptyCache();
            }
        }
    }

    public void removeModifiedLayer(GPLayerTreeModel gpLayerTreeModel) {
        this.modifiedLayersMap.remove(gpLayerTreeModel);
    }

    private boolean isChanged(GPLayerTreeModel gpLayerTreeModel) {
        MementoLayerOriginalProperties memento = this.modifiedLayersMap.get(gpLayerTreeModel);
        if (!memento.getAlias().equals(gpLayerTreeModel.getAlias())
                || memento.isChecked() != gpLayerTreeModel.isChecked()) {
            return true;
        } else if (gpLayerTreeModel instanceof RasterTreeNode
                && ((RasterTreeNode) gpLayerTreeModel).getOpacity() != memento.getOpacity()) {
            return true;
        }
        return false;
    }

    private void prepareLayerPropertiesModify() {
        for (GPLayerTreeModel gpLayerTreeModel : this.modifiedLayersMap.keySet()) {
            if (this.isChanged(gpLayerTreeModel)) {
                this.add(this.modifiedLayersMap.get(gpLayerTreeModel));
            }
        }
        this.modifiedLayersMap.clear();
    }

    public ObservableGPLayerSaveCache getObservable() {
        return this.observable;
    }

    public class ObservableGPLayerSaveCache extends Observable {

        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        public void notifyObservers(LayerEvents o) {
            super.notifyObservers(o);
        }
    }
}
