package org.geosdi.geoplatform.experimental.el.spring.jasypt.placeholder;

import java.net.MalformedURLException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class PlaceholderELResourcesLoader {

    Resource[] loadResources(String elasticSearchConfigDataDir,
            String elasticSearchFileProp) throws MalformedURLException {

        String fileToSearch = ((elasticSearchFileProp != null)
                && (!elasticSearchFileProp.isEmpty())) ? ((elasticSearchFileProp.contains(
                                "."))
                        && (elasticSearchFileProp.endsWith(".properties"))) ? elasticSearchFileProp
                                : (elasticSearchFileProp.contains(".")) ? elasticSearchFileProp.substring(
                                                0,
                                                elasticSearchFileProp.indexOf(
                                                        ".")) + ".properties"
                                        : elasticSearchFileProp + ".properties" : "gp.elastic-search.properties";

        Resource[] resources = new Resource[]{new ClassPathResource(fileToSearch),
            new UrlResource("file:" + elasticSearchConfigDataDir
            + "/" + fileToSearch)};

        return resources;
    }

}
