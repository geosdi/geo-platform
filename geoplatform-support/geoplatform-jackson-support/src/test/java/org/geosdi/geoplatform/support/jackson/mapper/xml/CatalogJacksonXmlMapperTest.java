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
package org.geosdi.geoplatform.support.jackson.mapper.xml;

import org.geosdi.geoplatform.support.jackson.model.Catalog;
import org.geosdi.geoplatform.support.jackson.model.Cd;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.StringReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class CatalogJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(CatalogJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<Catalog> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(Catalog.class,
            new GPJacksonXmlSupport());

    @Test
    public void a_writeCatalogXmlTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@CATALOG_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER.writeAsString(CatalogJacksonXmlMapperTest::catalogBean));
    }

    @Test
    public void b_readCatalogXmlFromStringTest() throws Exception {
        logger.info("######################CATALOG_FROM_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<CATALOG>\n"
                + "    <CD>\n"
                + "        <TITLE>dill diya galla</TITLE>\n"
                + "        <ARTIST>Arijit singh</ARTIST>\n"
                + "        <COUNTRY>India</COUNTRY>\n"
                + "        <COMPANY>tseries</COMPANY>\n"
                + "        <PRICE>10.90</PRICE>\n"
                + "        <YEAR>2018</YEAR>\n"
                + "    </CD>\n"
                + "    <CD>\n"
                + "        <TITLE>Saiyara</TITLE>\n"
                + "        <ARTIST>Atif Aslam</ARTIST>\n"
                + "        <COUNTRY>Uk</COUNTRY>\n"
                + "        <COMPANY>Records</COMPANY>\n"
                + "        <PRICE>9.90</PRICE>\n"
                + "        <YEAR>2015</YEAR>\n"
                + "    </CD>\n"
                + "    <CD>\n"
                + "        <TITLE>Khairiyat</TITLE>\n"
                + "        <ARTIST>Sonu nigam</ARTIST>\n"
                + "        <COUNTRY>india</COUNTRY>\n"
                + "        <COMPANY>radio</COMPANY>\n"
                + "        <PRICE>9.90</PRICE>\n"
                + "        <YEAR>2019</YEAR>\n"
                + "    </CD>\n"
                + "    <CD>\n"
                + "        <TITLE>all is well</TITLE>\n"
                + "        <ARTIST>Amir Khan</ARTIST>\n"
                + "        <COUNTRY>pak</COUNTRY>\n"
                + "        <COMPANY>Virgin records</COMPANY>\n"
                + "        <PRICE>10.20</PRICE>\n"
                + "        <YEAR>2012</YEAR>\n"
                + "    </CD>\n"
                + "    <CD>\n"
                + "        <TITLE>rockstar</TITLE>\n"
                + "        <ARTIST>kk</ARTIST>\n"
                + "        <COUNTRY>india</COUNTRY>\n"
                + "        <COMPANY>Eros</COMPANY>\n"
                + "        <PRICE>9.90</PRICE>\n"
                + "        <YEAR>2014</YEAR>\n"
                + "    </CD>\n"
                + "</CATALOG>")));
    }

    @Test
    public void c_readCatalogXmlFromFileTest() throws Exception {
        Catalog catalog = GP_JACKSON_XML_MAPPER.read(new File(of("src", "test", "resources", "Catalog.xml")
                .collect(joining(separator))));
        assertNotNull(catalog);
        logger.info("#############################CATALOG_XML_FROM_FILE : {}\n", catalog);
    }

    @Test
    public void d_writeCatalogXmlTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@CATALOG_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER.writeAsString(catalogBean(20)));
    }

    @Test
    public void e_writeCatalogXmlTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@CATALOG_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER.writeAsString(catalogBean(40)));
    }

    @Test
    public void f_writeCatalogXmlFileTest() throws Exception {
        GP_JACKSON_XML_MAPPER.write(new File(of(".", "target", "Catalog.xml")
                .collect(joining(separator))), CatalogJacksonXmlMapperTest::catalogBean);
    }

    /**
     * @return {@link Catalog}
     */
    public static Catalog catalogBean() {
        return catalogBean(50);
    }

    /**
     * @return {@link Catalog}
     */
    public static Catalog catalogBean(int cdNumbers) {
        return new Catalog() {
            {
                super.setCd(CatalogJacksonXmlMapperTest.prepareCd(cdNumbers));
            }
        };
    }

    /**
     * @param numbers
     * @return {@link List<Cd>}
     */
    public static List<Cd> prepareCd(int numbers) {
        checkArgument((numbers > 0), "The Number of Cd must be greather than 0.");
        return iterate(0, n -> n + 1)
                .limit(numbers)
                .boxed()
                .map(CatalogJacksonXmlMapperTest::toCd)
                .collect(toList());
    }

    /**
     *
     * @param theValue
     * @return {@link Cd}
     */
     static Cd toCd(@Nonnull(when = NEVER) Integer theValue)  {
         checkArgument(theValue != null, "The Parameter value must not be null");
         return new Cd() {
             {
                 super.setTitle("TITLE#".concat(theValue.toString()));
                 super.setArtist("ARTIST#".concat(theValue.toString()));
                 super.setCountry("COUNTRY#".concat(theValue.toString()));
                 super.setCompany("COMPANY#".concat(theValue.toString()));
                 super.setPrice(theValue.doubleValue());
                 super.setYear(now().getYear() - theValue);
             }
         };
     }
}