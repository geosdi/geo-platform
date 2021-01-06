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
package org.geosdi.geoplatform.sld.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ven 27 mar 2015, 13:22
 */
public class SLDValidator {

    private final static Logger logger = LoggerFactory.getLogger(SLDValidator.class);

    public SLDValidator() {
    }

    /**
     * validates against the SLD schema in the classpath
     *
     * @param xml
     * @return
     */
    public List validateSLD(InputStream xml) {
        return validateSLD(new InputSource(xml));
    }

    public static String getErrorMessage(InputStream xml, List errors) {
        return getErrorMessage(new InputStreamReader(xml), errors);
    }

    /**
     * returns a better formated error message - suitable for framing. There's a
     * more complex version in StylesEditorAction. This will kick out a VERY
     * LARGE errorMessage.
     *
     * @param xml
     * @param errors
     *
     * @return DOCUMENT ME!
     */
    public static String getErrorMessage(Reader xml, List errors) {
        BufferedReader reader = null;
        StringBuffer result = new StringBuffer();
        result.append("Your SLD is not valid.\n");
        result.append(
                "Most common problems are: \n(1) no namespaces - use <ows:GetMap>, <sld:Rule>, <ogc:Filter>, <gml:Point>  - the part before the ':' is important\n");
        result.append("(2) capitialization - use '<And>' not '<and>' \n");
        result.append("(3) Order - The order of elements is important \n");
        result.append(
                "(4) Make sure your first tag imports the correct namespaces.  ie. xmlns:sld=\"http://www.opengis.net/sld\" for EVERY NAMESPACE \n");
        result.append("\n");

        try {
            reader = new BufferedReader(xml);

            String line = reader.readLine();
            int linenumber = 1;
            int exceptionNum = 0;

            //check for lineNumber -1 errors  --> invalid XML
            if (errors.size() > 0) {
                SAXParseException sax = (SAXParseException) errors.get(0);

                if (sax.getLineNumber() < 0) {
                    result.append("   INVALID XML: " + sax.getLocalizedMessage() + "\n");
                    result.append(" \n");
                    exceptionNum = 1; // skip ahead (you only ever get one error in this case)
                }
            }

            while (line != null) {
                line.replace('\n', ' ');
                line.replace('\r', ' ');

                String header = linenumber + ": ";
                result.append(header + line + "\n"); // record the current line

                boolean keep_going = true;

                while (keep_going) {
                    if ((exceptionNum < errors.size())) {
                        SAXParseException sax = (SAXParseException) errors.get(exceptionNum);

                        if (sax.getLineNumber() <= linenumber) {
                            String head = "---------------------".substring(0, header.length() - 1);
                            String body = "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";

                            int colNum = sax.getColumnNumber(); //protect against col 0 problems

                            if (colNum < 1) {
                                colNum = 1;
                            }

                            if (colNum > body.length()) {
                                body = body + body + body + body + body + body; // make it longer (not usually required, but might be for SLD_BODY=... which is all one line)

                                if (colNum > body.length()) {
                                    colNum = body.length();
                                }
                            }

                            result.append(head + body.substring(0, colNum - 1) + "^\n");
                            result.append("       (line " + sax.getLineNumber() + ", column "
                                    + sax.getColumnNumber() + ")" + sax.getLocalizedMessage() + "\n");
                            exceptionNum++;
                        } else {
                            keep_going = false; //report later (sax.getLineNumber() > linenumber)
                        }
                    } else {
                        keep_going = false; // no more errors to report
                    }
                }

                line = reader.readLine(); //will be null at eof
                linenumber++;
            }

            for (int t = exceptionNum; t < errors.size(); t++) {
                SAXParseException sax = (SAXParseException) errors.get(t);
                result.append("       (line " + sax.getLineNumber() + ", column "
                        + sax.getColumnNumber() + ")" + sax.getLocalizedMessage() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }

    /**
     * validate a .sld against the schema
     *
     * @param xml input stream representing the .sld file
     * @param baseURL
     * @param SchemaUrl location of the schemas. Normally use
     * ".../schemas/sld/StyleLayerDescriptor.xsd"
     *
     * @return list of SAXExceptions (0 if the file's okay)
     */
    @Deprecated
    public List validateSLD(InputSource xml, String baseUrl) {
        return validateSLD(xml);
    }

    /**
     * validate a .sld against the schema
     *
     * @param xml input stream representing the .sld file
     *
     * @return list of SAXExceptions (0 if the file's okay)
     */
    public List validateSLD(InputSource xml) {
        URL schemaURL = SLDValidator.class.getResource("/schemas/sld/StyledLayerDescriptor.xsd");
        return validate(xml, schemaURL, false);
    }

    public static List validate(InputSource xml, URL schemaURL, boolean skipTargetNamespaceException) {
        StreamSource source = null;
        if (xml.getCharacterStream() != null) {
            source = new StreamSource(xml.getCharacterStream());
        } else if (xml.getByteStream() != null) {
            source = new StreamSource(xml.getByteStream());
        } else {
            throw new IllegalArgumentException("Could not turn input source to stream source");
        }
        return validate(source, schemaURL, skipTargetNamespaceException);
    }

    public static List validate(Source xml, URL schemaURL, boolean skipTargetNamespaceException) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
//        Source source = new StreamSource(SLDValidator.class.getResourceAsStream("/schemas/sld/StyledLayerDescriptor.xsd"));
        try {
            Schema schema
                    = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaURL);
            Validator v = schema.newValidator();
            Handler handler = new Handler(skipTargetNamespaceException);
            v.setErrorHandler(handler);
            v.validate(xml);
            return handler.errors;
        } catch (SAXException e) {
            return exception(e);
        } catch (IOException e) {
            return exception(e);
        }
    }

    // errors in the document will be put in "errors".
    // if errors.size() ==0  then there were no errors.
    private static class Handler extends DefaultHandler {

        public ArrayList errors = new ArrayList();

        boolean skipTargetNamespaceException;

        Handler(boolean skipTargetNamespaceExeption) {
            this.skipTargetNamespaceException = skipTargetNamespaceExeption;
        }

        public void error(SAXParseException exception)
                throws SAXException {
            if (skipTargetNamespaceException && exception.getMessage()
                    .startsWith("TargetNamespace.2: Expecting no namespace, but the schema document has a target name")) {
                return;
            }

            errors.add(exception);
        }

        public void fatalError(SAXParseException exception)
                throws SAXException {
            errors.add(exception);
        }

        public void warning(SAXParseException exception)
                throws SAXException {
            //do nothing
        }
    }

    static List exception(Exception e) {
        logger.error("**** Validation error: " + Arrays.toString(e.getStackTrace()));
        logger.error("**** Validation error: " + e.getMessage());
        return Arrays.asList(new SAXParseException(e.getLocalizedMessage(), null));
    }
}
