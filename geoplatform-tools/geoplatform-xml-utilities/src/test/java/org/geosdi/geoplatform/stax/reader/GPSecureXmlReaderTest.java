/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.stax.reader;

import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.jaxb.jakarta.GPJAXBJakartaContextBuilder;
import org.geosdi.geoplatform.jaxb.jakarta.IGPJAXBJakartaContextBuilder;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Map.of;
import static javax.xml.stream.XMLInputFactory.IS_COALESCING;
import static javax.xml.stream.XMLStreamConstants.CDATA;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder.jdkDefaultInstance;
import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderBuilder.jdkWithProp;
import static org.geosdi.geoplatform.stax.reader.builder.XmlStreamReaderStax2Builder.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Verifica che i reader StAX e le unmarshal JAXB del modulo non risolvano entità esterne (XXE), non
 * espandano entità annidate (billion laughs) e leggano ancora i documenti con un DOCTYPE esterno senza
 * scaricare la DTD.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPSecureXmlReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPSecureXmlReaderTest.class);
    private static final String SECRET = "SEGRETO-XXE-42";
    //
    @ClassRule
    public static final TemporaryFolder folder = new TemporaryFolder();
    private static File xxeFile;
    private static File billionLaughsFile;
    private static File externalDoctypeFile;

    /**
     * Elemento radice dei documenti di prova per JAXB jakarta.
     */
    @jakarta.xml.bind.annotation.XmlRootElement(name = "r")
    @jakarta.xml.bind.annotation.XmlAccessorType(jakarta.xml.bind.annotation.XmlAccessType.FIELD)
    public static class JakartaRoot {
        public String v;
    }

    /**
     * Elemento radice dei documenti di prova per JAXB javax.
     */
    @javax.xml.bind.annotation.XmlRootElement(name = "r")
    @javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
    public static class JavaxRoot {
        public String v;
    }

    /**
     * Lettura di un documento da verificare, che restituisce il testo dell'elemento {@code v}.
     */
    @FunctionalInterface
    interface XmlRead {

        /**
         * @param theFile il documento da leggere
         * @return il testo letto
         * @throws Exception se la lettura fallisce
         */
        String read(File theFile) throws Exception;
    }

    /**
     * Prepara il file segreto e i tre documenti di prova.
     *
     * @throws Exception se i file non si possono scrivere
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        File secret = folder.newFile("secret.txt");
        Files.writeString(secret.toPath(), SECRET, UTF_8);
        xxeFile = write("xxe.xml", "<?xml version=\"1.0\"?><!DOCTYPE r [<!ENTITY x SYSTEM \""
                + secret.toURI() + "\">]><r><v>&x;</v></r>");
        StringBuilder lol = new StringBuilder("<?xml version=\"1.0\"?><!DOCTYPE r [<!ENTITY l0 \"lollollollol\">");
        for (int i = 1; i <= 6; i++) {
            lol.append("<!ENTITY l").append(i).append(" \"");
            for (int j = 0; j < 10; j++) {
                lol.append("&l").append(i - 1).append(';');
            }
            lol.append("\">");
        }
        billionLaughsFile = write("lol.xml", lol.append("]><r><v>&l6;</v></r>").toString());
        // Porta 1 su localhost: se il parser provasse a scaricare la DTD, fallirebbe subito.
        externalDoctypeFile = write("doctype.xml", "<?xml version=\"1.0\"?><!DOCTYPE r SYSTEM "
                + "\"http://127.0.0.1:1/r.dtd\"><r><v>valore</v></r>");
    }

    /**
     * Nessun reader né unmarshal deve restituire il contenuto del file indicato dall'entità esterna.
     */
    @Test
    public void externalEntityMustNotBeResolved() {
        List<String> failures = new ArrayList<>();
        for (Map.Entry<String, XmlRead> entry : readers().entrySet()) {
            String text = readOrError(entry.getValue(), xxeFile);
            logger.debug("XXE {} -> {}", entry.getKey(), text);
            if (text.contains(SECRET)) {
                failures.add(entry.getKey());
            }
        }
        assertTrue("Entità esterna risolta da: " + failures, failures.isEmpty());
    }

    /**
     * Nessun reader né unmarshal deve espandere le entità annidate del billion laughs.
     */
    @Test
    public void nestedEntitiesMustNotBeExpanded() {
        List<String> failures = new ArrayList<>();
        for (Map.Entry<String, XmlRead> entry : readers().entrySet()) {
            String text = readOrError(entry.getValue(), billionLaughsFile);
            if (text.contains("lollollollol")) {
                failures.add(entry.getKey());
            }
        }
        assertTrue("Entità annidate espanse da: " + failures, failures.isEmpty());
    }

    /**
     * Un documento con un DOCTYPE esterno, come le GetCapabilities WMS 1.1.1, si legge ancora e la DTD non
     * viene scaricata.
     */
    @Test
    public void externalDoctypeMustBeIgnored() {
        for (Map.Entry<String, XmlRead> entry : readers().entrySet()) {
            assertEquals(entry.getKey(), "valore", readOrError(entry.getValue(), externalDoctypeFile));
        }
    }

    /**
     * @return i reader StAX e le unmarshal JAXB da verificare, per nome
     */
    private static Map<String, XmlRead> readers() {
        Map<String, XmlRead> readers = new LinkedHashMap<>();
        readers.put("jdkDefaultInstance", file -> stax(jdkDefaultInstance(), file));
        readers.put("jdkWithProp", file -> stax(jdkWithProp(of(IS_COALESCING, true)), file));
        readers.put("woodstoxConfigureForXmlConformance", file -> stax(woodstoxConfigureForXmlConformance(), file));
        readers.put("woodstoxConfigureForConvenience", file -> stax(woodstoxConfigureForConvenience(), file));
        readers.put("woodstoxConfigureForSpeed", file -> stax(woodstoxConfigureForSpeed(), file));
        readers.put("woodstoxConfigureForLowMemUsage", file -> stax(woodstoxConfigureForLowMemUsage(), file));
        readers.put("woodstoxConfigureForRoundTripping", file -> stax(woodstoxConfigureForRoundTripping(), file));
        readers.put("woodstoxWithProp", file -> stax(woodstoxWithProp(of(IS_COALESCING, true)), file));
        readers.put("aaltoConfigureForXmlConformance", file -> stax(aaltoConfigureForXmlConformance(), file));
        readers.put("aaltoConfigureForConvenience", file -> stax(aaltoConfigureForConvenience(), file));
        readers.put("aaltoConfigureForSpeed", file -> stax(aaltoConfigureForSpeed(), file));
        readers.put("aaltoConfigureForLowMemUsage", file -> stax(aaltoConfigureForLowMemUsage(), file));
        readers.put("aaltoConfigureForRoundTripping", file -> stax(aaltoConfigureForRoundTripping(), file));
        readers.put("aaltoWithProp", file -> stax(aaltoWithProp(of(IS_COALESCING, true)), file));
        readers.put("jakarta unmarshal(File)", file -> jakarta().unmarshal(file, JakartaRoot.class).v);
        readers.put("jakarta unmarshal(Path)", file -> jakarta().unmarshal(file.toPath(), JakartaRoot.class).v);
        readers.put("jakarta unmarshal(URL)", file -> jakarta().unmarshal(file.toURI().toURL(), JakartaRoot.class).v);
        readers.put("jakarta unmarshal(URI)", file -> jakarta().unmarshal(file.toURI(), JakartaRoot.class).v);
        readers.put("jakarta unmarshal(InputStream)", file -> {
            try (InputStream stream = new FileInputStream(file)) {
                return jakarta().unmarshal(stream, JakartaRoot.class).v;
            }
        });
        readers.put("jakarta unmarshal(Reader)", file -> {
            try (FileReader reader = new FileReader(file, UTF_8)) {
                return jakarta().unmarshal(reader, JakartaRoot.class).v;
            }
        });
        readers.put("jakarta unmarshal(StreamSource stream)", file -> {
            try (InputStream stream = new FileInputStream(file)) {
                return jakarta().unmarshal(new StreamSource(stream), JakartaRoot.class).v;
            }
        });
        readers.put("jakarta unmarshal(StreamSource systemId)",
                file -> jakarta().unmarshal(new StreamSource(file.toURI().toString()), JakartaRoot.class).v);
        readers.put("jakarta unmarshal(StreamSource percorso)",
                file -> jakarta().unmarshal(new StreamSource(file.getAbsolutePath()), JakartaRoot.class).v);
        readers.put("javax unmarshal(StreamSource percorso)",
                file -> GPJAXBContextBuilder.newInstance().unmarshal(new StreamSource(file.getAbsolutePath()), JavaxRoot.class).v);
        readers.put("javax unmarshal(File)", file -> GPJAXBContextBuilder.newInstance().unmarshal(file, JavaxRoot.class).v);
        readers.put("javax unmarshal(URL)",
                file -> GPJAXBContextBuilder.newInstance().unmarshal(file.toURI().toURL(), JavaxRoot.class).v);
        readers.put("javax unmarshal(InputStream)", file -> {
            try (InputStream stream = new FileInputStream(file)) {
                return GPJAXBContextBuilder.newInstance().unmarshal(stream, JavaxRoot.class).v;
            }
        });
        readers.put("javax unmarshal(Reader)", file -> {
            try (FileReader reader = new FileReader(file, UTF_8)) {
                return GPJAXBContextBuilder.newInstance().unmarshal(reader, JavaxRoot.class).v;
            }
        });
        return readers;
    }

    /**
     * @return il builder JAXB jakarta
     */
    private static IGPJAXBJakartaContextBuilder jakarta() {
        return GPJAXBJakartaContextBuilder.jakartaContextBuilder();
    }

    /**
     * Legge con un reader StAX il testo di tutto il documento.
     *
     * @param theBuilder il builder del reader
     * @param theFile    il documento
     * @return il testo letto
     * @throws Exception se la lettura fallisce
     */
    private static String stax(GPXmlStreamReaderBuilder theBuilder, File theFile) throws Exception {
        try (InputStream stream = new FileInputStream(theFile)) {
            XMLStreamReader reader = theBuilder.build(stream);
            StringBuilder text = new StringBuilder();
            while (reader.hasNext()) {
                int event = reader.next();
                if ((event == CHARACTERS) || (event == CDATA)) {
                    text.append(reader.getText());
                }
            }
            reader.close();
            return text.toString().trim();
        }
    }

    /**
     * Esegue la lettura e, se fallisce, restituisce il messaggio dell'errore: un documento rifiutato è un
     * esito valido, l'importante è che nel testo non compaia ciò che non deve.
     *
     * @param theRead la lettura da eseguire
     * @param theFile il documento
     * @return il testo letto o il messaggio dell'errore
     */
    private static String readOrError(XmlRead theRead, File theFile) {
        try {
            String text = theRead.read(theFile);
            return (text != null) ? text : "";
        } catch (Exception e) {
            return "ERRORE: " + e;
        }
    }

    /**
     * @param theName    il nome del file
     * @param theContent il contenuto
     * @return il file scritto nella cartella temporanea
     * @throws Exception se il file non si può scrivere
     */
    private static File write(String theName, String theContent) throws Exception {
        File file = folder.newFile(theName);
        Files.writeString(file.toPath(), theContent, UTF_8);
        return file;
    }
}
