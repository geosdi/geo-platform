/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.experimental.openam.support.config.placeholder;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class OpenAMConnectorResourcesLoader {

    protected Resource[] loadResources(String openAMConnectorConfigDataDir, String openAMConnectorFileProp)
            throws MalformedURLException {

        String fileToSearch = ((openAMConnectorFileProp != null) && (!openAMConnectorFileProp.isEmpty())) ?
                ((openAMConnectorFileProp.contains(".")) && (openAMConnectorFileProp.endsWith(".properties"))) ?
                        openAMConnectorFileProp :
                        (openAMConnectorFileProp.contains(".")) ?
                                openAMConnectorFileProp.substring(0, openAMConnectorFileProp.indexOf(".")) + ".properties" :
                                openAMConnectorFileProp + ".properties" :
                "openam-connector.properties";

        Resource[] resources = new Resource[]{new ClassPathResource(fileToSearch), new UrlResource(
                "file:" + openAMConnectorConfigDataDir + "/" + fileToSearch)};

        return resources;

    }

}
