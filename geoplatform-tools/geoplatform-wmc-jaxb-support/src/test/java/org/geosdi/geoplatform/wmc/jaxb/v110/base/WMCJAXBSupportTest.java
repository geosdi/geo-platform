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
package org.geosdi.geoplatform.wmc.jaxb.v110.base;

import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.WMCJAXBContextV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextProviderV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextSupport;
import org.geosdi.geoplatform.xml.wmc.v110.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMCJAXBSupportTest {

    static {
        wmcJAXBContext = WMCJAXBContextSupport
                .getProvider(WMCJAXBContextProviderV110.WMC_CONTEXT_KEY_V110);
    }

    private static final Logger logger = LoggerFactory.getLogger(WMCJAXBSupportTest.class);
    //
    private final static WMCJAXBContextV110 wmcJAXBContext;
    private static File wmcFile;
    private static File wmc1File;
    private static File wmc2File;
    private static File wmcLayerListFile;
    private static File wmcGeneralTypeFile;
    private static File wmcFormatListFile;
    private static File wmcStyleListFile;
    private static File wmcServerFile;
    private static File wmcLayerTypeFile;
    private static File wmcExtensionTypeFile;

    @BeforeClass
    public static void loadFile() throws Exception {
        String wmcFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-v110.xml";
        wmcFile = new File(wmcFileString);
        String wmc1FileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-1-v110.xml";
        wmc1File = new File(wmc1FileString);
        String wmc2FileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-1-v110.xml";
        wmc2File = new File(wmc2FileString);
        String wmcLayerListFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMCLayerTypeList-v110.xml";
        wmcLayerListFile = new File(wmcLayerListFileString);
        String wmcGeneralTypeFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMCGeneralType-v110.xml";
        wmcGeneralTypeFile = new File(wmcGeneralTypeFileString);
        String wmcFormatListFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-FormatList-v110.xml";
        wmcFormatListFile = new File(wmcFormatListFileString);
        String wmcStyleListFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-StyleList-v110.xml";
        wmcStyleListFile = new File(wmcStyleListFileString);
        String wmcServerFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-Server-v110.xml";
        wmcServerFile = new File(wmcServerFileString);
        String wmcLayerTypeFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMCLayerType-v110.xml";
        wmcLayerTypeFile = new File(wmcLayerTypeFileString);
        String wmcExtensionTypeFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-Extension-v110.xml";
        wmcExtensionTypeFile = new File(wmcExtensionTypeFileString);
    }

    @Test
    public void wmcV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ViewContextType viewContextType = ((JAXBElement<ViewContextType>) unmarshaller
                .unmarshal(wmcFile)).getValue();
        logger.info("#####################{}\n", viewContextType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(viewContextType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmc1V110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ViewContextType viewContextType = ((JAXBElement<ViewContextType>) unmarshaller
                .unmarshal(wmc1File)).getValue();
        logger.info("#####################{}\n", viewContextType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(viewContextType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmc2V110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ViewContextType viewContextType = ((JAXBElement<ViewContextType>) unmarshaller
                .unmarshal(wmc2File)).getValue();
        logger.info("#####################{}\n", viewContextType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(viewContextType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcGeneralTypeV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        GeneralType generalType = (GeneralType) unmarshaller.unmarshal(wmcGeneralTypeFile);
        logger.info("#####################{}\n", generalType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(generalType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcFormatListV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        FormatListType formatListType = (FormatListType) unmarshaller.unmarshal(wmcFormatListFile);
        logger.info("#####################{}\n", formatListType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(formatListType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcStyleListV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        StyleListType styleListType = (StyleListType) unmarshaller.unmarshal(wmcStyleListFile);
        logger.info("#####################{}\n", styleListType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(styleListType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcServerV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ServerType serverType = (ServerType) unmarshaller.unmarshal(wmcServerFile);
        logger.info("#####################{}\n", serverType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(serverType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcLayerTypeV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        LayerType layerType = (LayerType) unmarshaller.unmarshal(wmcLayerTypeFile);
        logger.info("#####################{}\n", layerType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(layerType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcExtensionTypeV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ExtensionType extensionType = (ExtensionType) unmarshaller.unmarshal(wmcExtensionTypeFile);
        logger.info("#####################{}\n", extensionType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(extensionType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ViewContextType viewContextType = ((JAXBElement<ViewContextType>) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMC-v110.xml"))).getValue();
        logger.info("#####################{}\n", viewContextType);
        StringWriter writer = new StringWriter();
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        marshaller.marshal(viewContextType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmc1V110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ViewContextType viewContextType = ((JAXBElement<ViewContextType>) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMC-1-v110.xml"))).getValue();
        logger.info("#####################{}\n", viewContextType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(viewContextType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmc2V110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ViewContextType viewContextType = ((JAXBElement<ViewContextType>) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMC-2-v110.xml"))).getValue();
        logger.info("#####################{}\n", viewContextType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(viewContextType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcLayerListV110SupportTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        LayerListType layerListType = (LayerListType) unmarshaller.unmarshal(wmcLayerListFile);
        logger.info("#####################{}\n", layerListType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(layerListType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcLayerListV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        LayerListType layerListType = (LayerListType) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMCLayerTypeList-v110.xml"));
        logger.info("#####################{}\n", layerListType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(layerListType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcGeneralTypeV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        GeneralType generalType = (GeneralType) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMCGeneralType-v110.xml"));
        logger.info("#####################{}\n", generalType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(generalType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcFormatListV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        FormatListType formatListType = (FormatListType) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMC-FormatList-v110.xml"));
        logger.info("#####################{}\n", formatListType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(formatListType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcStyleListV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        StyleListType styleListType = (StyleListType) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMC-StyleList-v110.xml"));
        logger.info("#####################{}\n", styleListType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(styleListType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcServerV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ServerType serverType = (ServerType) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMC-Server-v110.xml"));
        logger.info("#####################{}\n", serverType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(serverType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcLayerTypeV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        LayerType layerType = (LayerType) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMCLayerType-v110.xml"));
        logger.info("#####################{}\n", layerType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(layerType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Test
    public void wmcExtensionTypeV110SupportURLTest() throws Exception {
        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        ExtensionType extensionType = (ExtensionType) unmarshaller
                .unmarshal(new URL("http://150.145.141.124/GONFALONI-Upload/WMC-Extension-v110.xml"));
        logger.info("#####################{}\n", extensionType);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(extensionType, writer);
        logger.info("###########################\n{}\n\n", writer);
    }
}
