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
package org.geosdi.geoplatform.services.csw;

import org.geosdi.geoplatform.connector.GPCSWConnectorBuilder;
import org.geosdi.geoplatform.connector.GPCatalogConnectorStore;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.services.delegate.dc.DublinCoreAnalyzer;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;
import org.geosdi.geoplatform.xml.csw.v202.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBElement;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml",
        "classpath:applicationContext-Logger.xml"})
public class CSWGetRecordsEsriTest {

    @GeoPlatformLog
    private static Logger logger;
    //
    private final DublinCoreAnalyzer gpDublinCoreAnalyzer = new DublinCoreAnalyzer.GPDublinCoreAnalyzer();

    @Ignore(value = "Serve id Down")
    @Test(expected = Exception.class)
    public void testFullRecordEsri() throws Exception {
        URL url = new URL("http://www.geoportale.isprambiente.it/geoportale"
                + "/csw/discovery");
        GPCatalogConnectorStore serverConnector = GPCSWConnectorBuilder
                .newConnector().withServerUrl(url).build();

        CatalogFinderBean finderBean = new CatalogFinderBean();
        finderBean.setTextInfo(new TextInfo() {

            private static final long serialVersionUID = 1L;

            {
                super.setText("scuole");
                super.setSearchTitle(Boolean.TRUE);
                super.setSearchSubjects(Boolean.FALSE);
                super.setSearchAbstract(Boolean.FALSE);
            }

        });

        CatalogGetRecordsRequest<GetRecordsResponseType> request = serverConnector.createGetRecordsRequest();
        request.setTypeName(TypeName.RECORD_V202);
        request.setOutputSchema(OutputSchema.CSW_V202);
        request.setElementSetName(ElementSetType.FULL.value());
        request.setResultType(ResultType.RESULTS.value());

        request.setConstraintLanguage(ConstraintLanguage.FILTER);
        request.setConstraintLanguageVersion(ConstraintLanguageVersion.V110);
        request.setCatalogFinder(finderBean);

        request.setStartPosition(BigInteger.ONE);
        request.setMaxRecords(BigInteger.valueOf(25));

        logger.debug("\n\n#####################RESPONSE AS STRING : {}\n\n",
                request.getResponseAsString());

        GetRecordsResponseType response = request.getResponse();

        SearchResultsType result = response.getSearchResults();

        List<JAXBElement<? extends AbstractRecordType>> records
                = result.getAbstractRecord();
        logger.debug("\n*** Record list size: {} ***", records.size());

        processFirstResult((RecordType) records.get(1).getValue());

    }

    @Test
    public void testFullRecordGeoSDI() throws Exception {
        URL url = new URL("http://catalog.geosdi.org:80/geonetwork/srv/eng/csw");
        GPCatalogConnectorStore serverConnector = GPCSWConnectorBuilder
                .newConnector().withServerUrl(url).build();
        CatalogGetRecordsRequest<GetRecordsResponseType> request = serverConnector.createGetRecordsRequest();
        request.setTypeName(TypeName.METADATA);
        request.setOutputSchema(OutputSchema.CSW_V202);
        request.setElementSetName(ElementSetType.FULL.toString());
        request.setResultType(ResultType.RESULTS.toString());
        request.setStartPosition(BigInteger.ONE);
        request.setMaxRecords(BigInteger.valueOf(25));
        logger.debug("\n\n#####################RESPONSE AS STRING : {}\n\n", request.getResponseAsString());
        GetRecordsResponseType response = request.getResponse();
        SearchResultsType result = response.getSearchResults();
        List<JAXBElement<? extends AbstractRecordType>> metadata = result.getAbstractRecord();
        Assert.assertEquals("The Result not contains 25 elements", 25, metadata.size());
        processFirstResult((RecordType) metadata.get(0).getValue());
    }

    void processFirstResult(RecordType record) {
        this.gpDublinCoreAnalyzer.analyzeRecord(record, new FullRecordDTO());
    }
}