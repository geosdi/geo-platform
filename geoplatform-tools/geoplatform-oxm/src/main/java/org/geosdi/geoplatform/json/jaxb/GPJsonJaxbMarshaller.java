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
package org.geosdi.geoplatform.json.jaxb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJsonJaxbMarshaller implements GenericJsonJaxbMarshaller {

    private Jaxb2Marshaller marshaller;

    @Override
    public void setMarshaller(Jaxb2Marshaller theMarshaller) {
        this.marshaller = theMarshaller;
    }

    @Override
    public void marshal(Object jaxbElement, File output) throws IOException {
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        Writer writer = new OutputStreamWriter(new FileOutputStream(output));

        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);

        this.marshaller.marshal(jaxbElement, new StAXResult(xmlStreamWriter));
    }

    @Override
    public void marshal(Object jaxbElement, Writer writer) throws IOException {
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);

        XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);

        this.marshaller.marshal(jaxbElement, new StAXResult(xmlStreamWriter));
    }

    @Override
    public Object unmarshal(File f) throws Exception {
        JSONObject obj = new JSONObject(FileUtils.readFileToString(f));
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);

        return this.marshaller.unmarshal(new StAXSource(xmlStreamReader));
    }

    @Override
    public Object unmarshal(InputStream is) throws Exception {
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer);

        JSONObject obj = new JSONObject(writer.toString());
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);

        return this.marshaller.unmarshal(new StAXSource(xmlStreamReader));
    }

    @Override
    public Object unmarshal(String s) throws Exception {
        JSONObject obj = new JSONObject(s);
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);

        return this.marshaller.unmarshal(new StAXSource(xmlStreamReader));
    }

    @Override
    public Object unmarshal(StreamSource streamSource) throws Exception {
        return this.marshaller.unmarshal(streamSource);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.marshaller == null) {
            throw new IllegalArgumentException("The Marshaller property must "
                    + "not be null");
        }
    }

}
