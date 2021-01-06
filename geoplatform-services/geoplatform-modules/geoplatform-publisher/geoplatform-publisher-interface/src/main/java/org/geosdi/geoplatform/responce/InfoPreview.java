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
package org.geosdi.geoplatform.responce;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.gui.shared.publisher.LayerPublishAction;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@XmlRootElement(name = "InfoPreview")
public class InfoPreview {

    private String dataSource = "";
    private String workspace = "";
    private String title = "";
    private double lowerX = 0.0d;
    private double lowerY = 0.0d;
    private double upperX = 0.0d;
    private double upperY = 0.0d;
    private String crs = "";
    private boolean isShape;
    private List<LayerPublishAction> alreadyExists;
    private String message = "ok";
    private String styleName;
    //
    private LayerPublishAction layerPublishAction;
    private String newName = "";
    private String fileName = "";

    public InfoPreview(String url, String workspace, String layerName,
            double minX, double minY, double maxX, double maxY, String crs,
            String styleName, boolean isShape, List<LayerPublishAction> alreadyExists) {
        this.dataSource = url;
        this.workspace = workspace;
        this.title = layerName;
        this.lowerX = minX;
        this.lowerY = minY;
        this.upperX = maxX;
        this.upperY = maxY;
        this.crs = crs;
        this.isShape = isShape;
        this.styleName = styleName;
        this.alreadyExists = alreadyExists;
    }

    public InfoPreview() {
    }

    public InfoPreview(String dataStoreName, String message) {
        this.title = dataStoreName;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return dataSource;
    }

    public void setUrl(String url) {
        this.dataSource = url;
    }

    public String getCrs() {
        return crs;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public String getDataStoreName() {
        return title;
    }

    public void setDataStoreName(String dataStoreName) {
        this.title = dataStoreName;
    }

    public boolean isIsShape() {
        return isShape;
    }

    public void setIsShape(boolean isShape) {
        this.isShape = isShape;
    }

    public List<LayerPublishAction> getAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(List<LayerPublishAction> alreadyExists) {
        this.alreadyExists = alreadyExists;
    }

    public double getMaxX() {
        return upperX;
    }

    public void setMaxX(double maxX) {
        this.upperX = maxX;
    }

    public double getMaxY() {
        return upperY;
    }

    public void setMaxY(double maxY) {
        this.upperY = maxY;
    }

    public double getMinX() {
        return lowerX;
    }

    public void setMinX(double minX) {
        this.lowerX = minX;
    }

    public double getMinY() {
        return lowerY;
    }

    public void setMinY(double minY) {
        this.lowerY = minY;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    /**
     * @return the styleName
     */
    public String getStyleName() {
        return styleName;
    }

    /**
     * @param styleName the styleName to set
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public LayerPublishAction getLayerPublishAction() {
        return layerPublishAction;
    }

    public void setLayerPublishAction(LayerPublishAction layerPublishAction) {
        this.layerPublishAction = layerPublishAction;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "InfoPreview{" + "dataSource=" + dataSource + ", workspace="
                + workspace + ", title=" + title + ", lowerX=" + lowerX
                + ", lowerY=" + lowerY + ", upperX=" + upperX + ", upperY="
                + upperY + ", crs=" + crs + ", isShape=" + isShape
                + ", alreadyExists=" + alreadyExists + ", newName=" + newName
                + ", fileName=" + fileName
                + ", layerPublishAction=" + layerPublishAction
                + ", message=" + message + ", styleName=" + styleName + '}';
    }
}
