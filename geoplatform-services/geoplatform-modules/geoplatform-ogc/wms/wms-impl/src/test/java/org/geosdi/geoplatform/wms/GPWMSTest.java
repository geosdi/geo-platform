package org.geosdi.geoplatform.wms;


import org.geosdi.geoplatform.core.dao.GPOrganizationDAO;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.services.GPWMSService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class GPWMSTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSTest.class);


    @Resource(name = "wmsService")
    private GPWMSService wmsService;

    @Resource(name = "serverDAO")
    private GPServerDAO serverDao;

    @Resource(name = "organizationDAO")
    private GPOrganizationDAO organizationDAO;


    public void setUp() throws Exception {
        Assert.assertNotNull(this.wmsService);
    }

    @Ignore
    @Test
    public void inteserServerTest() throws Exception {
        GPOrganization organization = this.organizationDAO.find(200L);


        GeoPlatformServer server = new GeoPlatformServer();
        server.setServerUrl("https://vvf-toscana.geosdi.org/geoserver/ows?service=wms&version=1.3.0&request=GetCapabilities");
        server.setName("Test Server");
        server.setOrganization(organization);
        server.setServerType(GPCapabilityType.WMS);

        this.serverDao.persist(server);
    }
}
