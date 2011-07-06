/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.gui.client.model;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.data.BaseModel;
import java.util.List;

import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorModelConverter;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.model.tree.IGPNode;
import org.geosdi.geoplatform.gui.observable.Observable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class FolderTreeNode extends AbstractFolderTreeNode implements IGPNode{

    /**
     *
     */
    private static final long serialVersionUID = -3687415822526940729L;
    private VisitorModelConverter visitor = new VisitorModelConverter(this);
    private ObservableFolderTreeNode observable = new ObservableFolderTreeNode();
    private boolean loaded = false;
    private boolean loading = false;
    private int numberOfDescendants = 0;

    public FolderTreeNode() {
    }

    public FolderTreeNode(String label) {
        super.setLabel(label);
    }
    
    public FolderTreeNode(GPFolderClientInfo folder) {
        super(folder);
        this.numberOfDescendants = folder.getNumberOfDescendants();
        this.modelConverter(folder.getFolderElements());
    }

    /**
     *
     * @param layersClientInfo
     */
    public void modelConverter(List<IGPFolderElements> layersClientInfo) {
        for (IGPFolderElements layer : layersClientInfo) {
            layer.accept(this.visitor);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel#getIcon()
     */
    @Override
    public AbstractImagePrototype getIcon() {
        if (this.isLoading()) {
            return GXT.IMAGES.icon_wait();
        }
        return LayerResources.ICONS.layerFolder();
    }

    /**
     * @return the number of childrens
     */
    public int getNumberOfDescendants() {
        return numberOfDescendants;
    }

    /**
     * @param numberOfChildrens the numberOfChildrens to set
     */
    public void setNumberOfDescendants(int numberOfChildrens) {
        this.numberOfDescendants = numberOfChildrens;
    }

    /**
     * @return the loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * @param loaded the loaded to set
     */
    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void setId(long id) {
        super.setId(id);
        observable.setChanged();
        observable.notifyObservers(id);
    }

    public ObservableFolderTreeNode getObservable() {
        return observable;
    }

    public void setObservable(ObservableFolderTreeNode observable) {
        this.observable = observable;
    }

    public class ObservableFolderTreeNode extends Observable {

        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }
    }
}
