/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.geosdi.geoplatform.publish;

/**
 *
 * @author Luca
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.responce.InfoPreview;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Luca Paolino - geoSDI Group
 * @email luca.paolino@geosdi.org
 * this test try to publish two shapefiles. the first only in the preview workspace, the second firstly in the preview workspace and then into the data datastore of the preview2 workspace
 * In order to execute this test you should provide two shapefiles. The first should be in one ZIP compressed file while the second should be provided provifing its shp, prj, shx and dbf uncrompressed files.
 * You should also modify the paths to access them.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PublisherWSTest extends TestCase{

    protected Logger logger = LoggerFactory.getLogger(org.geosdi.geoplatform.publish.PublisherWSTest.class);

    @Autowired
    private GPPublisherService gppublisherService;

    @Test
    public void testWS() {
        Assert.assertNotNull(gppublisherService);
        try {
            logger.info("\n **** START TEST");
            logger.info("\n **** CALL TO UPLOADSHAPEINPREVIEW ON prova.zip");
            File zipFile = new File("./src/test/resources/prova.zip");
            List<InfoPreview> infoList = gppublisherService.uploadZIPInPreview(zipFile);
            for (InfoPreview info: infoList){
                logger.info("\n **** Preview at: "+info.getWorkspace()+":"+info.getDataStoreName()+" --> "+info.getMessage());
            }
            logger.info("\n **** CALL TO PUBLISH ON zip_it_aeropo ");
            logger.info("\n **** RESULT "+gppublisherService.publish("preview2", "data", "zip_it_aeropo" ));
            logger.info("\n **** CALL TO REMOVEFROMPREVIEW it_aeropo ");
            logger.info("\n **** RESULT "+gppublisherService.removeFromPreview("zip_it_aree_meteoclimatiche"));
            File shpFile = new File("./src/test/resources/limiti_adb_4326.shp");
            File dbfFile = new File("./src/test/resources/limiti_adb_4326.dbf");
            File shxFile = new File("./src/test/resources/limiti_adb_4326.shx");
            File prjFile = new File("./src/test/resources/limiti_adb_4326.prj");

            logger.info("\n **** CALL TO UPLOADSHAPEINPREVIEW ON Limiti_AdB_4326 files");
            logger.info("\n **** RESULT: "+gppublisherService.uploadShapeInPreview(shpFile, dbfFile, shxFile, prjFile));
            logger.info("\n **** CALL TO PUBLISH ON Limiti_AdB_4326 files");
            logger.info("\n **** RESULT "+gppublisherService.publish("preview2", "data", "limiti_adb_4326"));
              logger.info("\n **** CALL TO GETPREVIEWDATASTORES");
            List<InfoPreview> infoList2 = gppublisherService.getPreviewDataStores();
             for (InfoPreview info: infoList2){
                logger.info("\n **** Preview at: "+info.getWorkspace()+":"+info.getDataStoreName()+" --> "+info.getMessage());
            }
            logger.info("\n **** END TEST");
            } catch (ResourceNotFoundFault ex) {
                logger.error("\n **** Eccezione nella pubblicazione: "+ex.getMessage());
                System.out.println("\n **** Eccezione nella pubblicazione: "+ex.getMessage());
            }
            catch (FileNotFoundException ex) {
                logger.error("\n **** File zip non trovato");
                System.out.println("\n **** Eccezione nella pubblicazione: "+ex.getMessage());
            }

    }
}
