/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.geosdi.publisher.responce;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 */
@XmlRootElement(name = "PreviewElement")
public class PreviewElement {
    private String dataStoreName;
    private String previewURL;

    public String getDataStoreName() {
        return dataStoreName;
    }

    public void setDataStoreName(String dataStoreName) {
        this.dataStoreName = dataStoreName;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public PreviewElement(String dataStoreName, String previewURL) {
        this.dataStoreName = dataStoreName;
        this.previewURL = previewURL;
    }

    public PreviewElement() {
    }

    

}
