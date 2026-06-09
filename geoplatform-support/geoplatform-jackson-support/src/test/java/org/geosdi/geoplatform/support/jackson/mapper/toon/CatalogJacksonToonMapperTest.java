/**
 *
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.mapper.toon;

import dev.toonformat.jtoon.DecodeOptions;
import dev.toonformat.jtoon.EncodeOptions;
import dev.toonformat.jtoon.KeyFolding;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.mapper.xml.CatalogJacksonXmlMapperTest;
import org.geosdi.geoplatform.support.jackson.model.Catalog;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import static dev.toonformat.jtoon.Delimiter.COMMA;
import static java.io.File.separator;
import static java.lang.String.join;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAKARTA;
import static org.geosdi.geoplatform.support.jackson.mapper.xml.CatalogJacksonXmlMapperTest.catalogBean;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class CatalogJacksonToonMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(CatalogJacksonToonMapperTest.class);
    //
    private static final GPJacksonToonMapper<Catalog> GP_JACKSON_TOON_MAPPER = new GPBaseJacksonToonMapper<>(
            Catalog.class, new GPJacksonSupport(JAKARTA, UNWRAP_ROOT_VALUE_DISABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE, INDENT_OUTPUT_ENABLE));

    @Test
    public void a_writeCatalogAsToonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@JTOON_STRING : \n{}\n", GP_JACKSON_TOON_MAPPER.writeAsToonString(CatalogJacksonXmlMapperTest::catalogBean,
                new EncodeOptions(2, COMMA, false, KeyFolding.OFF, 3)));
    }

    @Test
    public void b_writeCatalogAsToonStringTest() throws Exception {
        logger.info("##################JTOON_STRING : \n{}\n", GP_JACKSON_TOON_MAPPER.writeAsToonString(catalogBean(10),
                new EncodeOptions(2, COMMA, false, KeyFolding.OFF, 3)));
    }

    @Test
    public void c_readCatalogFromToonStringTest() throws Exception {
        Catalog catalog = GP_JACKSON_TOON_MAPPER.read("CD[10]{TITLE,ARTIST,COUNTRY,COMPANY,PRICE,YEAR}:\n"
                        + "  TITLE#0,ARTIST#0,COUNTRY#0,COMPANY#0,0,2026\n"
                        + "  TITLE#1,ARTIST#1,COUNTRY#1,COMPANY#1,1,2025\n"
                        + "  TITLE#2,ARTIST#2,COUNTRY#2,COMPANY#2,2,2024\n"
                        + "  TITLE#3,ARTIST#3,COUNTRY#3,COMPANY#3,3,2023\n"
                        + "  TITLE#4,ARTIST#4,COUNTRY#4,COMPANY#4,4,2022\n"
                        + "  TITLE#5,ARTIST#5,COUNTRY#5,COMPANY#5,5,2021\n"
                        + "  TITLE#6,ARTIST#6,COUNTRY#6,COMPANY#6,6,2020\n"
                        + "  TITLE#7,ARTIST#7,COUNTRY#7,COMPANY#7,7,2019\n"
                        + "  TITLE#8,ARTIST#8,COUNTRY#8,COMPANY#8,8,2018\n"
                        + "  TITLE#9,ARTIST#9,COUNTRY#9,COMPANY#9,9,2017",
                new DecodeOptions());
        logger.info("#####################CATALOG_FROM_STRING : {}\n", catalog);
    }

    @Test
    public void d_writeCatalogAsToonStringTest() throws Exception {
        Writer writer = new StringWriter();
        GP_JACKSON_TOON_MAPPER.writeAsToon(writer, catalogBean(10),
                new EncodeOptions(2, COMMA, false, KeyFolding.OFF, 3));
        logger.info("##################JTOON_STRING : \n{}\n", writer);
    }

    @Test
    public void e_writeCatalogAsToonFileTest() throws Exception {
        GP_JACKSON_TOON_MAPPER.writeAsToon(new File(join(separator, ".", "target", "Catalog.toon")),
                CatalogJacksonXmlMapperTest::catalogBean, new EncodeOptions(2, COMMA, false, KeyFolding.OFF, 3));
    }

    @Test
    public void f_readCatalogFromToonFileTest() throws Exception {
        logger.info("#####################CATALOG_FROM_FILE : {}\n", GP_JACKSON_TOON_MAPPER.read(new File(join(separator, ".", "target", "Catalog.toon")),
                new DecodeOptions()));
    }
}