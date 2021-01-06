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
package org.geosdi.geoplatform.gui.client.model;

import java.util.List;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;
import org.geosdi.geoplatform.gui.shared.publisher.LayerPublishAction;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class EPSGLayerData extends GeoPlatformBeanModel {

    public static final String NAME = "name";
    public static final String NEW_NAME = "newName";
    public static final String CRS = "crs";
    public static final String IS_SHAPE = "isShape";
    public static final String ALREADY_EXISTS = "alreadyExists";
    public static final String STYLE_NAME = "styleName";
//    public static final String PUBLISH_ACTION_LIST = "publishActionList";
    public static final String PUBLISH_ACTION = "publishAction";
    private static final long serialVersionUID = 3153334351994515962L;

    private String fileName;
    private List<LayerPublishAction> layerPublishActions;

    public EPSGLayerData() {
    }

    public EPSGLayerData(String featureName, String epsgCode, String styleName,
            boolean isShape, List<LayerPublishAction> alreadyExists, String fileName) {
        this.setFeatureName(featureName);
        this.setEpsgCode(epsgCode);
        this.setStyleName(styleName);
        this.setIsShape(isShape);
        this.setAlreadyExists(alreadyExists);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEpsgCode() {
        return super.get(EPSGLayerData.CRS);
    }

    public final void setEpsgCode(String epsgCode) {
        super.set(EPSGLayerData.CRS, epsgCode);
    }

    public String getFeatureName() {
        return super.get(EPSGLayerData.NAME);
    }

    public final void setFeatureName(String featureName) {
        super.set(EPSGLayerData.NAME, featureName);
    }

    public Boolean isIsShape() {
        return super.get(EPSGLayerData.IS_SHAPE);
    }

    public final void setIsShape(boolean isShape) {
        super.set(EPSGLayerData.IS_SHAPE, isShape);
    }

    public List<LayerPublishAction> getAlreadyExists() {
        return super.get(EPSGLayerData.ALREADY_EXISTS);
    }

    public final void setAlreadyExists(List<LayerPublishAction> alreadyExists) {
        super.set(EPSGLayerData.ALREADY_EXISTS, alreadyExists);
        if (alreadyExists != null) {
            this.setPublishActions(alreadyExists);
        }
    }

    public String getStyleName() {
        return super.get(EPSGLayerData.STYLE_NAME);
    }

    public final void setStyleName(String styleName) {
        super.set(EPSGLayerData.STYLE_NAME, styleName);
    }

    public void setPublishActions(List<LayerPublishAction> layerPublishActions) {
        this.layerPublishActions = layerPublishActions;
    }

    public List<LayerPublishAction> getPublishActions() {
        return this.layerPublishActions;
    }

    public String getPublishAction() {
        return super.get(EPSGLayerData.PUBLISH_ACTION);
    }

    public void setPublishAction(String publishAction) {
        super.set(EPSGLayerData.PUBLISH_ACTION, publishAction);
    }

    public String getNewName() {
        return super.get(EPSGLayerData.NEW_NAME);
    }

    public void setNewName(String newName) {
        super.set(EPSGLayerData.NEW_NAME, newName);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EPSGLayerData other = (EPSGLayerData) obj;
        if ((this.fileName == null) ? (other.fileName != null) : !this.fileName.equals(other.fileName)) {
            return false;
        }
        if (this.layerPublishActions != other.layerPublishActions && (this.layerPublishActions == null || !this.layerPublishActions.equals(other.layerPublishActions))) {
            return false;
        }
        return true;
    }

}
