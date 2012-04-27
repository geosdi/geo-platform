/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.cswconnector;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.geosdi.geoplatform.connector.jaxb.GPConnectorJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.provider.GeoPlatformJAXBContextRepository;
import org.geosdi.geoplatform.connector.protocol.GeoPlatformHTTP;
import org.geosdi.geoplatform.cswconnector.jaxb.CSWConnectorJAXBContext;
import org.geosdi.geoplatform.xml.csw.v202.AbstractRecordType;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetNameType;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordsResponseType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordsType;
import org.geosdi.geoplatform.xml.csw.v202.QueryType;
import org.geosdi.geoplatform.xml.csw.v202.ResultType;
import org.geotoolkit.csw.xml.TypeNames;
//import org.geotoolkit.csw.GetRecordsRequest;
//import org.geotoolkit.csw.xml.CSWMarshallerPool;
//import org.geotoolkit.csw.xml.ElementSetType;
//import org.geotoolkit.csw.xml.ResultType;
//import org.geotoolkit.csw.xml.v202.GetRecordsResponseType;
//import org.geotoolkit.csw.xml.v202.SearchResultsType;
//import org.geotoolkit.xml.MarshallerPool;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class CatalogGetRecordsTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final static String CSW_HOST = "150.146.160.152";
    private final static String CSW_PATH = "/geonetwork/srv/en/csw";
    private final static String CSW_HOST_TREVISO = "ows.provinciatreviso.it";
    private final static String CSW_PATH_TREVISO = "/geonetwork/srv/it/csw";
    private final static String CSW_HOST_FIRENZE = "datigis.comune.fi.it";
    private final static String CSW_PATH_FIRENZE = "/geonetwork/srv/it/csw";
    private final GPConnectorJAXBContext cswContext = GeoPlatformJAXBContextRepository.getProvider(
            CSWConnectorJAXBContext.CSW_CONTEXT_KEY);
//    private MarshallerPool pool = CSWMarshallerPool.getInstance();
//    private Unmarshaller um;

    // TODO Uncomment (commented for geotoolkit bug  - "This operation can't be applied to values of class 'CSWVersion'.")
//    @Test
//    public void testWithoutConstraint() throws Exception, JAXBException {
//        GPCSWServerConnector serverConnector = GeoPlatformCSWConnectorBuilder.newConnector().
//                withServerUrl(new URL("http://ows.provinciatreviso.it/geonetwork/srv/it/csw")).build();
//        
//        try {
//            um = pool.acquireUnmarshaller();
//            
//            GetRecordsRequest request = serverConnector.createGetRecords();
//            
//            request.setTypeNames("gmd:MD_Metadata");
//            request.setConstraintLanguage("CQL");
//            request.setConstraintLanguageVersion("1.1.0");
//            
//            request.setOutputSchema("csw:IsoRecord");
//            request.setElementSetName(ElementSetType.FULL);
//            
//            request.setResultType(ResultType.RESULTS);
//            
//            request.setStartPosition(1);
//            request.setMaxRecords(25);
//            
//            logger.info("Constraint: {}", request.getConstraint());
//            
//            InputStream is = request.getResponseStream();
//
//            // unmarshall the response
//            GetRecordsResponseType response = ((JAXBElement<GetRecordsResponseType>) um.unmarshal(
//                                               is)).getValue();
//            
//            SearchResultsType searchResult = response.getSearchResults();
//            
//            logger.info(
//                    "RECORD MATCHES @@@@@@@@@@@@@@@@@@@@@ {}", searchResult.getNumberOfRecordsMatched());
//            
//            logger.info(
//                    "RECORDS FOUND @@@@@@@@@@@@@@@@@@@@@@ {}", searchResult.getNumberOfRecordsReturned());
//            
//            logger.info(
//                    "NEXT RECORD @@@@@@@@@@@@@@@@@@@@@@ {}", searchResult.getNextRecord());
//            
//            List<Object> metadata = searchResult.getAny();
//            
//            if (!metadata.isEmpty()) {
//                logger.info(
//                        "FIRST FULL METADATA @@@@@@@@@@@@@@@@@@@@@ {}", metadata.get(0));
//            }
//            
//        } finally {
//            if (um != null) {
//                pool.release(um);
//            }
//        }
//    }
    // TODO Uncomment (commented because the "The service is not running")
//    @Test
//    public void testTemporalFilterGeomatys() throws MalformedURLException, JAXBException, IOException {
//        GPCSWServerConnector serverConnector = GeoPlatformCSWConnectorBuilder.newConnector().
//                withServerUrl(new URL("http://demo.geomatys.com/mdweb-cnes-labs/WS/csw/default")).build();
//        
//        try {
//            um = pool.acquireUnmarshaller();
//            
//            GetRecordsRequest request = serverConnector.createGetRecords();
//            
//            request.setTypeNames("csw:Record");
//            request.setConstraintLanguage("CQL");
//            request.setConstraintLanguageVersion("1.1.0");
//
//            // Text filter
//            StringBuilder str = new StringBuilder();
//            str.append("AnyText LIKE '%%'");
//
//            // Time filter
//            Calendar startCalendar = new GregorianCalendar(2000, Calendar.JANUARY, 1);
//            Calendar endCalendar = new GregorianCalendar(2012, Calendar.JANUARY, 1);
//            
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//            
//            str.append(" AND ");
//            str.append("TempExtent_begin AFTER ").append(formatter.format(startCalendar.getTime()));
//            str.append(" AND ");
//            str.append("TempExtent_end BEFORE ").append(formatter.format(endCalendar.getTime()));
//            
//            request.setConstraint(str.toString());
//
//            // unmarshall the response
//            logger.debug("\n@@@@@@@@@@@@@@@@ Geomatys ### Constraint: {}", request.getConstraint());
//            InputStream is = request.getResponseStream();
//            
//            Object content = um.unmarshal(is);
//            
//            if (!(content instanceof JAXBElement)) {
//                logger.error("\n@@@@@@@@@@@@@@@@ Geomatys ### {}", content); // ExceptionReport
//                Assert.fail();
//            }
//            
//            JAXBElement<GetRecordsResponseType> elementType = (JAXBElement<GetRecordsResponseType>) content;
//            
//            GetRecordsResponseType response = elementType.getValue();
//            
//            SearchResultsType searchResult = response.getSearchResults();
//            
//            logger.info(
//                    "\n@@@@@@@@@@@@@@@@ Geomatys ### RECORD MATCHES {} ###", searchResult.getNumberOfRecordsMatched());
//        } finally {
//            if (um != null) {
//                pool.release(um);
//            }
//        }
//    }
    @Test
    public void testOwnGetRecords()
            throws URISyntaxException, JAXBException,
                   UnsupportedEncodingException, IOException {

        HttpParams params = new BasicHttpParams();

        params.setParameter(GeoPlatformHTTP.CONTENT_TYPE_PARAMETER,
                GeoPlatformHTTP.CONTENT_TYPE_XML);

        HttpClient client = new DefaultHttpClient();

        URI uri = URIUtils.createURI("http", CSW_HOST_TREVISO, -1, CSW_PATH_TREVISO,
                null, null);

        HttpPost post = new HttpPost(uri);
        post.setParams(params);

        /**
         * GetRecords Request
         */
        GetRecordsType req = new GetRecordsType();

//        req.setStartPosition(BigInteger.ONE);
//        req.setMaxRecords(BigInteger.valueOf(1)); //
//        req.setMaxRecords(BigInteger.TEN);

//        req.setResultType(ResultType.HITS); // count
        // or
        req.setResultType(ResultType.RESULTS);

        req.setOutputFormat("application/xml");
        req.setOutputSchema("http://www.opengis.net/cat/csw/2.0.2"); // For obtain AbstractRecord list
//        req.setOutputSchema("http://www.isotc211.org/2005/gmd"); // For obtain Any list

        QueryType query = new QueryType();

        List<QName> typNames = new ArrayList<QName>();
//        typNames.add(TypeNames.valueOf("csw:Record")); // count
        // or
        typNames.add(TypeNames.valueOf("gmd:MD_Metadata"));
        query.setTypeNames(typNames);

        ElementSetNameType elementSetNameType = new ElementSetNameType();
//        elementSetNameType.setValue(ElementSetType.BRIEF);
        elementSetNameType.setValue(ElementSetType.SUMMARY);
//        elementSetNameType.setValue(ElementSetType.FULL);
        query.setElementSetName(elementSetNameType);

        req.setAbstractQuery(query);


        Marshaller m = cswContext.acquireMarshaller();
        Unmarshaller un = cswContext.acquireUnmarshaller();


        StringWriter w = new StringWriter();

        m.marshal(req, w);

        StringEntity entity = new StringEntity(w.toString(),
                GeoPlatformHTTP.CONTENT_TYPE_XML, HTTP.UTF_8);

        post.setEntity(entity);
        logger.debug("\n@@@@@@@@@@@@@@@@ GetRecords Request @@@@@@@@@@@@@@@@\n{}\n",
                new Scanner(post.getEntity().getContent()).useDelimiter("\\A").next());

        HttpResponse response = client.execute(post);
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            InputStream is = responseEntity.getContent();
            // TODO DON'T USE IT BECAUSE ALTER THE INPUT STREAM AND THERE IS A PARSE EXCEPTION IN UNMARSHAL PROCESS
//            logger.debug("\n@@@@@@@@@@@@@@@@\n{}\n", new Scanner(is).useDelimiter("\\A").next());

            /**
             * GetRecords Response
             */
            Object content = un.unmarshal(is);
            if (!(content instanceof JAXBElement)) {
                logger.error("\n#############\n{}", content);
                Assert.fail();
            }

            JAXBElement<GetRecordsResponseType> elementType = (JAXBElement<GetRecordsResponseType>) content;

            GetRecordsResponseType getRecords = elementType.getValue();
            logger.trace("\n@@@@@@@@@@@@@@@@ GetRecords Response @@@@@@@@@@@@@@@@\n{}\n", getRecords);

            List<JAXBElement<? extends AbstractRecordType>> records = getRecords.getSearchResults().getAbstractRecord();
            if (records != null) {
                logger.debug("\n@@@@@@@@@@@@@@@@ AbstractRecord @@@@@@@@@@@@@@@@\n{}\n", records.size());
                for (Object record : records) {
                    logger.debug("\n@@@@@@@@@@@@@@@@\n{}\n", record.getClass());
                    logger.debug("\n@@@@@@@@@@@@@@@@\n{}\n", record);
                }
            } else {
                logger.debug("\n@@@@@@@@@@@@@@@@\nNO RESULT\n");
            }

            List recordsAfter = getRecords.getSearchResults().getAny();
            if (recordsAfter != null) {
                logger.debug("\n++++++++++++++++ Any ++++++++++++++++\n{}\n", recordsAfter.size());
                for (Object record : recordsAfter) {
                    logger.debug("\n++++++++++++++++\n{}\n", record.getClass());
                    logger.debug("\n++++++++++++++++\n{}\n", record);
                }
            } else {
                logger.debug("\n++++++++++++++++\nNO RESULT\n");
            }

            EntityUtils.consume(responseEntity);
        }
    }
}
