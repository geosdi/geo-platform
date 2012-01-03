/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.extjs.gxt.ui.client.data.ModelData;
import java.util.HashMap;
import java.util.Map;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.model.memento.GPCache;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.observable.Observable;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPMementoSaveCache extends GPCache<IMemento<ISave>> {

    private static final long serialVersionUID = -5458269761345444182L;
    //
    private static GPMementoSaveCache instance = new GPMementoSaveCache();
    private ObservableGPLayerSaveCache observable = new ObservableGPLayerSaveCache();
    private Map<GPBeanTreeModel, AbstractMementoOriginalProperties> modifiedLayersMap = new HashMap<GPBeanTreeModel, AbstractMementoOriginalProperties>();

    public static GPMementoSaveCache getInstance() {
        return instance;
    }

    private GPMementoSaveCache() {
        //This is necessary to manage the events associated to the saveCache
        MementoSaveCacheManager manager = new MementoSaveCacheManager();
    }

    //The properties are copied only the first time, in this way we can save
    //only the layers effectively modified from the original one
    public void copyOriginalProperties(GPBeanTreeModel element) {
        if (!this.modifiedLayersMap.containsKey(element)) {
            AbstractMementoOriginalProperties memento = MementoSaveBuilder.generateMementoOriginalProperties(element);
            memento.copyOriginalProperties(element);
            this.modifiedLayersMap.put(element, memento);
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
        boolean condition = super.add(memento);
        if (condition && super.size() == 1) {
            this.observable.setChanged();
            this.observable.notifyObservers(LayerEvents.SAVE_CACHE_NOT_EMPTY);
            /*System.out.println("Event SAVE_CACHE_NOT_EMPTY notified to "
            + this.observable.countObservers() + " observers");*/
        }
//        System.out.println("GPLayerSaveCache: added " + memento.getClass().getName());
        return condition;
    }

    @Override
    public IMemento<ISave> poll() {
        IMemento<ISave> memento = super.poll();
        this.verifyEmptyCache();
        return memento;
    }

    @Override
    public void clear() {
        super.clear();
        this.modifiedLayersMap.clear();
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

    @Override
    public boolean isEmpty() {
        boolean result = true;
        if (!super.isEmpty() || (!this.modifiedLayersMap.isEmpty()
                && this.countLayerPropertiesModified() > 0)) {
            result = false;
        }
        return result;
    }

    private int countLayerPropertiesModified() {
        int count = 0;
        for (AbstractMementoOriginalProperties memento : this.modifiedLayersMap.values()) {
            if (memento.isChanged()) {
                count++;
            }
        }
        return count;
    }
    

    private void verifyEmptyCache() {
        if (super.peek() == null) {
            if (this.modifiedLayersMap.isEmpty()) {
                this.observable.setChanged();
                this.observable.notifyObservers(LayerEvents.SAVE_CACHE_EMPTY);
                /*System.out.println("Event SAVE_CACHE_EMPTY notified to "
                + this.observable.countObservers() + " observers");*/
            } else {
                this.prepareLayerPropertiesModified();
                this.verifyEmptyCache();
            }
        }
    }

    public void cleanOperationsRefToDeletedElement(GPBeanTreeModel gpBeanTreeModel) {
        this.modifiedLayersMap.remove(gpBeanTreeModel);
        if (gpBeanTreeModel instanceof FolderTreeNode) {
            FolderTreeNode folder = (FolderTreeNode) gpBeanTreeModel;
            for (ModelData element : folder.getChildren()) {
                this.cleanOperationsRefToDeletedElement((GPBeanTreeModel) element);
            }
        }
    }

    private void prepareLayerPropertiesModified() {
        for (AbstractMementoOriginalProperties memento : this.modifiedLayersMap.values()) {
            if (memento.isChanged()) {
                this.add(memento);
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
