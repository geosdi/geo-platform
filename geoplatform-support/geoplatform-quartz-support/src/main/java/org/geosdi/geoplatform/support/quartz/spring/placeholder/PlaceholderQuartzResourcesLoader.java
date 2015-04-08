package org.geosdi.geoplatform.support.quartz.spring.placeholder;

import java.net.MalformedURLException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class PlaceholderQuartzResourcesLoader {

    protected Resource[] loadResources(String gpQuartzConfigDataDir,
            String gpQuartzFileProp) throws MalformedURLException {

        String fileToSearch = ((gpQuartzFileProp != null)
                && (!gpQuartzFileProp.isEmpty())) ? ((gpQuartzFileProp.contains(
                                "."))
                        && (gpQuartzFileProp.endsWith(".properties"))) ? gpQuartzFileProp
                                : (gpQuartzFileProp.contains(".")) ? gpQuartzFileProp.substring(
                                                0,
                                                gpQuartzFileProp.indexOf(
                                                        ".")) + ".properties"
                                        : gpQuartzFileProp + ".properties" : "gp-quartz.properties";

        Resource[] resources = new Resource[]{new ClassPathResource(fileToSearch),
            new UrlResource("file:" + gpQuartzConfigDataDir
            + "/" + fileToSearch)};

        return resources;
    }

}
