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

import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.cswconnector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;
import org.geosdi.geoplatform.xml.csw.v202.AbstractRecordType;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordsResponseType;
import org.geosdi.geoplatform.xml.csw.v202.ResultType;
import org.geosdi.geoplatform.xml.csw.v202.SearchResultsType;
import org.geosdi.geoplatform.xml.csw.v202.SummaryRecordType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class CatalogGetRecordsTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testWithoutConstraint() throws Exception {
        URL url = new URL("http://ows.provinciatreviso.it/geonetwork/srv/it/csw");
        GPCSWServerConnector serverConnector = GPCSWConnectorBuilder.newConnector().
                withServerUrl(url).build();

        CatalogGetRecordsRequest request = serverConnector.createGetRecordsRequest();

        request.setTypeName(TypeName.METADATA);

        request.setOutputSchema(OutputSchema.CSW);
        request.setElementSetName(ElementSetType.SUMMARY);
        request.setResultType(ResultType.RESULTS);

        request.setStartPosition(BigInteger.ONE);
        request.setMaxRecords(BigInteger.valueOf(25));

        // TODO FIX Delete downcast
        GetRecordsResponseType response = (GetRecordsResponseType) request.getResponse();

        SearchResultsType searchResult = response.getSearchResults();

        logger.info("RECORD MATCHES @@@@@@@@@@@@@@@@@@@@@ {}",
                searchResult.getNumberOfRecordsMatched());

        logger.info("RECORDS FOUND @@@@@@@@@@@@@@@@@@@@@@ {}",
                searchResult.getNumberOfRecordsReturned());

        logger.info("NEXT RECORD @@@@@@@@@@@@@@@@@@@@@@ {}",
                searchResult.getNextRecord());

        List<JAXBElement<? extends AbstractRecordType>> metadata =
                searchResult.getAbstractRecord();
        if (!metadata.isEmpty()) {
            logger.info("FIRST FULL METADATA @@@@@@@@@@@@@@@@@@@@@ {}",
                    (SummaryRecordType) (metadata.get(0).getValue()));
        }
    }

    @Test
    public void testTemporalFilterGeomatys() throws Exception {
        URL url = new URL("http://demo.geomatys.com/mdweb-cnes-labs/WS/csw/default");
        GPCSWServerConnector serverConnector = GPCSWConnectorBuilder.newConnector().
                withServerUrl(url).build();

        CatalogGetRecordsRequest request = serverConnector.createGetRecordsRequest();

        request.setTypeName(TypeName.RECORD);

        request.setConstraintLanguage(ConstraintLanguage.CQL_TEXT);
        request.setConstraintLanguageVersion(ConstraintLanguageVersion.v1_1_0);

        // Text filter
        StringBuilder str = new StringBuilder();
        str.append("AnyText LIKE '%%'");

        // Time filter
        Calendar startCalendar = new GregorianCalendar(2000, Calendar.JANUARY, 1);
        Calendar endCalendar = new GregorianCalendar(2012, Calendar.JANUARY, 1);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        str.append(" AND ");
        str.append("TempExtent_begin AFTER ").append(formatter.format(startCalendar.getTime()));
        str.append(" AND ");
        str.append("TempExtent_end BEFORE ").append(formatter.format(endCalendar.getTime()));

        request.setConstraint(str.toString());
        logger.debug("\n@@@@@@@@@@@@@@@@ Geomatys ### Constraint: {}", request.getConstraint());

        // TODO FIX Delete downcast
        GetRecordsResponseType response = (GetRecordsResponseType) request.getResponse();

        SearchResultsType searchResult = response.getSearchResults();

        logger.info("\n@@@@@@@@@@@@@@@@ Geomatys ### RECORD MATCHES {} ###",
                searchResult.getNumberOfRecordsMatched());
    }
}
