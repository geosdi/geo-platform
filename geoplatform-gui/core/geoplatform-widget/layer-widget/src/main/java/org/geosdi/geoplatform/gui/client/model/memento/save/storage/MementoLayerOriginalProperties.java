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
package org.geosdi.geoplatform.gui.client.model.memento.save.storage;

import java.util.ArrayList;
import java.util.ListIterator;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.GPMementoSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoLayerOriginalProperties
        extends MementoOriginalProperties<GPLayerTreeModel> {

    private static final long serialVersionUID = 2399513531544205577L;
    private float opacity;
    private ArrayList<GPStyleStringBeanModel> styleList;

    public MementoLayerOriginalProperties() {
    }

    public MementoLayerOriginalProperties(ISave saveAction) {
        super(saveAction);
    }

    @Override
    public void convertMementoToWs() {
        super.convertMementoToWs();
        super.setName(super.getRefBaseElement().getAlias());
        super.setChecked(super.getRefBaseElement().isChecked());
        if (super.getRefBaseElement() instanceof RasterTreeNode) {
            opacity = ((RasterTreeNode) super.getRefBaseElement()).getOpacity();
        }
    }

    /**
     * @return the opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the styleList
     */
    public ArrayList<GPStyleStringBeanModel> getStyleList() {
        return styleList;
    }

    /**
     * @param styleList the styleList to set
     */
    public void setStyleList(ArrayList<GPStyleStringBeanModel> styleList) {
        this.styleList = styleList;
    }

    @Override
    public boolean isChanged() {
        boolean condition = false;
        if ((super.getName() == null && super.getRefBaseElement() != null)
                || !super.getName().equals(super.getRefBaseElement().getAlias())
                || super.isChecked() != super.getRefBaseElement().isChecked()) {
            condition = true;
        } else if (super.getRefBaseElement() instanceof RasterTreeNode
                && ((RasterTreeNode) super.getRefBaseElement()).getOpacity() != this.getOpacity()
                || !((RasterTreeNode) super.getRefBaseElement()).getStyles().equals(this.getStyleList())) {
            condition = true;
        }
        return condition;
    }

    @Override
    public void copyOriginalProperties(GPBeanTreeModel bean) {
        if (bean instanceof GPLayerTreeModel) {
            GPLayerTreeModel layer = (GPLayerTreeModel) bean;
            super.setName(layer.getAlias());
//            System.out.println("Alias setted: " + memento.getName);
            super.setChecked(layer.isChecked());
//            System.out.println("Check setted: " + memento.isChecked());
            if (layer instanceof RasterTreeNode) {
                this.setOpacity(((RasterTreeNode) layer).getOpacity());
                this.setStyleList(((RasterTreeNode) layer).getStyles());
//                System.out.println("Opacity setted: " + memento.getOpacity());
            }
            super.setRefBaseElement(layer);
        } else {
            throw new IllegalArgumentException("The method copyOriginalProperties "
                    + "in MementoLayerOriginalProperties class accepts only GPLayerTreeModel instances");
        }
    }

    @Override
    public void cleanCacheFromSaveAddOperation() {
        IMemento<ISave> memento = null;
        for (ListIterator<IMemento<ISave>> it = GPMementoSaveCache.getInstance().listIterator(); it.hasNext();) {
            memento = it.next();
            if (memento instanceof MementoSaveAddedLayers
                    && ((MementoSaveAddedLayers) memento).getRefBaseElement().equals(super.getRefBaseElement())) {
                System.out.println("Ho trovato la creazione");
                GPMementoSaveCache.getInstance().remove(memento);
                break;
            }
        }
    }
}
