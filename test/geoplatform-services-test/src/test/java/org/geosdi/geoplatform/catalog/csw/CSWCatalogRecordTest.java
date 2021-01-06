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
package org.geosdi.geoplatform.catalog.csw;

import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.responce.FullRecordDTO;
import org.geosdi.geoplatform.responce.SummaryRecordDTO;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for CSW Catalog Records.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class CSWCatalogRecordTest extends CSWCatalogTest {

    @Test
    public void testGetRecordsOurCount() throws Exception {
        catalogFinder.getTextInfo().setText("limiti");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@{}\n", cswService.getRecordsCount(catalogFinder));
//        assertTrue(cswService.getRecordsCount(catalogFinder) > 0);
    }

    @Test
    public void testGetRecordsOurResultSummary() throws Exception {
        catalogFinder.getTextInfo().setText("limiti");
        List<SummaryRecordDTO> summaryRecords = cswService.searchSummaryRecords(10, 1, catalogFinder);
        this.traceCollection(summaryRecords);
        assertTrue(summaryRecords.size() > 0);
    }

    @Test
    public void testGetRecordsOurResultFull() throws Exception {
        catalogFinder.getTextInfo().setText("limiti");
        List<FullRecordDTO> records = cswService.searchFullRecords(10, 1, catalogFinder);
        this.traceCollection(records);
        assertTrue(records.size() > 0);
    }

    @Test
    public void testGetRecordsOurResultFullGetCapabilities() throws Exception {
        String title = "Protezione Civile Web Map Service";
        TextInfo textInfo = catalogFinder.getTextInfo();
        textInfo.setText(title);
        textInfo.setSearchTitle(true);
        textInfo.setSearchAbstract(false);
        textInfo.setSearchSubjects(false);
        List<FullRecordDTO> records = cswService.searchFullRecords(10, 1, catalogFinder);
        this.traceCollection(records);
        assertEquals(1, records.size());
        FullRecordDTO record = records.get(0);
        assertEquals(title, record.getTitle());
        assertEquals("d759996e6da0dee66a515e9f236da6c575cb1d83", record.getIdentifier());
        assertEquals("service", record.getType());
        assertTrue(record.getAbstractText().contains("WMS Server del Dipartimento della Protezione Civile Nazionale"));
        assertNotNull(record.getSubjects());
        assertEquals("WFS", record.getSubjects().get(0));
        assertEquals("WMS", record.getSubjects().get(1));
        assertEquals("GEOSERVER", record.getSubjects().get(2));
        assertNotNull(record.getUriMap());
        assertEquals(1, record.getUriMap().size());
    }

    @Ignore("Catalog is down")
    @Test
    public void testGetRecordsTrevisoSearchWMSText() throws Exception {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("wms");
        int num = 10;
        int recordsMatched = cswService.getRecordsCount(catalogFinder);
        assertTrue(recordsMatched > 0);
        logger.debug("\n*** Records matched: {} *** Result for page: {} ***", recordsMatched, num);
        List<SummaryRecordDTO> summaryRecords;
        int pages = (recordsMatched / num);
        int mod = recordsMatched % num;
        if (mod > 0) {
            pages++;
        }
        logger.debug("\n*** Pages: {} *** Module: {} ***", pages, mod);
        int start;
        for (int i = 1; i < pages; i++) {
            start = (num * (i - 1)) + 1;
            logger.debug("\n*** page: {} *** start: {} ***", i, start);
            summaryRecords = cswService.searchSummaryRecords(num, start, catalogFinder);
            this.traceCollection(summaryRecords);
            assertEquals(num, summaryRecords.size());
        }

        // Last page
        if (mod > 0) {
            start = (num * (pages - 1)) + 1;
            summaryRecords = cswService.searchSummaryRecords(num, start,
                    catalogFinder);
            this.traceCollection(summaryRecords);
            assertEquals(mod, summaryRecords.size());
        }
    }

    @Ignore("Catalog is down")
    @Test
    public void testGetRecordsTrevisoCountLimitiTextAny() throws Exception {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");

        assertTrue(cswService.getRecordsCount(catalogFinder) > 0);
    }

    @Ignore("Catalog is down")
    @Test
    public void testGetRecordsTrevisoCountLimitiTextTitle() throws Exception {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");
        catalogFinder.getTextInfo().setSearchTitle(true);
        catalogFinder.getTextInfo().setSearchAbstract(false);
        catalogFinder.getTextInfo().setSearchSubjects(false);

        assertTrue(cswService.getRecordsCount(catalogFinder) > 0);
    }

    @Ignore("Catalog is down")
    @Test
    public void testGetRecordsTrevisoCountLimitiTextAbstract() throws Exception {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");
        catalogFinder.getTextInfo().setSearchTitle(false);
        catalogFinder.getTextInfo().setSearchAbstract(true);
        catalogFinder.getTextInfo().setSearchSubjects(false);

        assertTrue(cswService.getRecordsCount(catalogFinder) > 0);
    }

    @Ignore("Catalog is down")
    @Test
    public void testGetRecordsTrevisoCountLimitiTextSubjects() throws Exception {
        catalogFinder.setServerID(serverTestTrevisoID);
        catalogFinder.getTextInfo().setText("limiti");
        catalogFinder.getTextInfo().setSearchTitle(false);
        catalogFinder.getTextInfo().setSearchAbstract(false);
        catalogFinder.getTextInfo().setSearchSubjects(true);

        assertEquals(0, cswService.getRecordsCount(catalogFinder));
    }

    @Test
    public void testGetRecordsOurCountAreaItaly() throws Exception {
        int tot = cswService.getRecordsCount(catalogFinder);
//        assertEquals(595, tot);
        AreaInfo areaInfo = catalogFinder.getAreaInfo();
        areaInfo.setActive(true);
        BBox bBoxItaly = new BBox(6.624, 36.6492, 18.5144, 47.0946);
        areaInfo.setBBox(bBoxItaly);
        areaInfo.setAreaSearchType(AreaInfo.AreaSearchType.ENCLOSES);
        int countEncloses = cswService.getRecordsCount(catalogFinder);
//        assertEquals(24, countEncloses);
        areaInfo.setAreaSearchType(AreaInfo.AreaSearchType.IS);
        int countIs = cswService.getRecordsCount(catalogFinder);
//        assertEquals(0, countIs);
        areaInfo.setAreaSearchType(AreaInfo.AreaSearchType.OUTSIDE);
        int countOutside = cswService.getRecordsCount(catalogFinder);
        assertEquals(39, countOutside);
        areaInfo.setAreaSearchType(AreaInfo.AreaSearchType.OVERLAP);
        int countOverlap = cswService.getRecordsCount(catalogFinder);
//        assertEquals(543, countOverlap);
        logger.info("################ TOT: {}\nENCLOSES: {}\nIS: {}\nOUTSIDE {}\nOVERLAP {}", tot, countEncloses, countIs, countOutside, countOverlap);
    }

    @Ignore("Catalog is down")
    @Test
    public void testGetRecordsGeomatysCountTimeFiltering() throws Exception {
        // Insert the server
        GeoPlatformServer server = super.createCSWServer("Geomatys",
                "http://demo.geomatys.com/mdweb-cnes-labs/WS/csw/default",
                organizationTest);
        Long serverID = cswService.insertServerCSW(server);

        assertNotNull(serverID);

        catalogFinder.setServerID(serverID);

        int count = cswService.getRecordsCount(catalogFinder);
        assertTrue(count > 0);

        Calendar startCalendar = new GregorianCalendar(2000, Calendar.JANUARY, 1);
        Calendar endCalendar = new GregorianCalendar(2012, Calendar.JANUARY, 1);
        catalogFinder.getTimeInfo().setActive(true);
        catalogFinder.getTimeInfo().setStartDate(startCalendar.getTime());
        catalogFinder.getTimeInfo().setEndDate(endCalendar.getTime());

        assertTrue(count > cswService.getRecordsCount(catalogFinder));

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverID);
        assertTrue(deleted);
    }

    @Ignore("Require to add the SNIPC certificate into default keystore")
    @Test
    public void testSecureGetRecordsSNIPC() throws Exception {
        // Insert the server
        GeoPlatformServer server = this.createCSWServer("SNIPC", snipcProvider.
                getSnipcUrl(), organizationTest);
        Long serverID = cswService.insertServerCSW(server);

        // Set catalog finder
        catalogFinder.setServerID(serverID);

        int count = cswService.getRecordsCount(catalogFinder);
        assertTrue(count > 0);

        // Delete the server
        boolean deleted = cswService.deleteServerCSW(serverID);
        assertTrue(deleted);
    }

    private void traceCollection(Collection collection) {
        for (Object object : collection) {
            logger.trace("\n*****************TRACE_COLLECTION_OBJECT " + object);
        }
    }

}
