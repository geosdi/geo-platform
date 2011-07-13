/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.geosdi.publisher.responce;



import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 * this kind of objects should contains a name of the datastore and the URL to the PNG associated to the preview of the datastore
 */
@XmlRootElement(name = "InfoPreview")
public class InfoPreview {
    private String url="";
    private String workspace="";
    private String dataStoreName="";
    private double minX=0.0d;
    private double minY=0.0d;
    private double maxX=0.0d;
    private double maxY=0.0d;
    private int width=0;
    private int height=0;
    private String crs="";
    private boolean isVector = true;

    public InfoPreview(String url, String workspace, String dataStoreName, double minX, double minY, double maxX, double maxY, int width, int height, String crs) {

        this.url = url;
        this.workspace = workspace;
        this.dataStoreName = dataStoreName;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.width = width;
        this.height = height;
        this.crs = crs;
        this.isVector = true;
    }

    public InfoPreview() {
    }

    public boolean isIsVector() {
        return isVector;
    }

    public void setIsVector(boolean isVector) {
        this.isVector = isVector;
    }

    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCrs() {
        return crs;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public String getDataStoreName() {
        return dataStoreName;
    }

    public void setDataStoreName(String dataStoreName) {
        this.dataStoreName = dataStoreName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
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
