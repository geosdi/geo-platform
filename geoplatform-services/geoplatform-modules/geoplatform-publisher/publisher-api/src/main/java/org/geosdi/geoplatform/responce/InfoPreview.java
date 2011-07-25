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
package org.geosdi.geoplatform.responce;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 * this kind of objects should contains a name of the datastore and the URL to the PNG associated to the preview of the datastore
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
    private int width = 0;
    private int height = 0;
    private String crs = "";
    private boolean isVector = true;
    private String message = "ok";

    public InfoPreview(String url, String workspace, String dataStoreName,
            double minX, double minY, double maxX, double maxY, int width,
            int height, String crs) {
        this.dataSource = url;
        this.workspace = workspace;
        this.title = dataStoreName;
        this.lowerX = minX;
        this.lowerY = minY;
        this.upperX = maxX;
        this.upperY = maxY;
        this.width = width;
        this.height = height;
        this.crs = crs;
        this.isVector = true;
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

    public boolean isIsVector() {
        return isVector;
    }

    public void setIsVector(boolean isVector) {
        this.isVector = isVector;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }
}
