/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.geosdi.geoplatform.responce;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luca
 * this kind of objects should contains a name of the datastore and the URL to the PNG associated to the preview of the datastore
 */
@XmlRootElement(name = "PreviewElement")
public class PreviewElement {
    private String dataStoreName;
    private InfoPreview previewURL;

    public PreviewElement() {
    }

    public PreviewElement(String dataStoreName, InfoPreview previewURL) {
        this.dataStoreName = dataStoreName;
        this.previewURL = previewURL;
    }

    

    public String getDataStoreName() {
        return dataStoreName;
    }

    public void setDataStoreName(String dataStoreName) {
        this.dataStoreName = dataStoreName;
    }

    public InfoPreview getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(InfoPreview previewURL) {
        this.previewURL = previewURL;
    }




    

}
