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
package org.geosdi.geoplatform.stax.reader.factory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.Reader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static javax.annotation.meta.When.NEVER;
import static javax.xml.stream.XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES;
import static javax.xml.stream.XMLInputFactory.SUPPORT_DTD;

/**
 * Rende sicure le {@link XMLInputFactory} del modulo contro XXE (lettura di file locali e richieste verso
 * altri indirizzi tramite entità esterne) e contro l'espansione incontrollata delle entità: disattiva le DTD
 * e le entità esterne e rifiuta qualsiasi risorsa esterna richiesta dal documento.
 * <p>
 * Gli XML letti da questo modulo arrivano anche da server esterni (risposte WFS GetFeature e WMS
 * GetFeatureInfo), quindi non possono mai decidere quali risorse il parser va a leggere.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPSecureXMLInputFactory {

    /**
     * Classe di sole funzioni statiche: non si istanzia.
     */
    private GPSecureXMLInputFactory() {
    }

    /**
     * Disattiva DTD ed entità esterne sulla factory passata e imposta un {@link javax.xml.stream.XMLResolver}
     * che rifiuta ogni risorsa esterna, come seconda difesa per le implementazioni che ignorassero le due
     * proprietà.
     *
     * @param theFactory la factory da rendere sicura
     * @param <F>        il tipo della factory
     * @return la stessa factory, già configurata
     */
    public static <F extends XMLInputFactory> F secure(@Nonnull(when = NEVER) F theFactory) {
        checkArgument(theFactory != null, "Il parametro theFactory non deve essere null.");
        theFactory.setProperty(SUPPORT_DTD, FALSE);
        theFactory.setProperty(IS_SUPPORTING_EXTERNAL_ENTITIES, FALSE);
        theFactory.setXMLResolver((publicID, systemID, baseURI, namespace) -> {
            throw new XMLStreamException("Risorsa esterna rifiutata: " + systemID);
        });
        return theFactory;
    }

    /**
     * Crea la {@link XMLInputFactory} predefinita della JDK, già resa sicura con {@link #secure(XMLInputFactory)}.
     * Si crea una factory nuova a ogni chiamata perché quella della JDK non garantisce di poter creare
     * reader da più thread contemporaneamente.
     *
     * @return una nuova factory sicura
     */
    public static XMLInputFactory newSecureFactory() {
        return secure(XMLInputFactory.newDefaultFactory());
    }

    /**
     * Crea un {@link XMLStreamReader} sicuro su uno stream di byte.
     *
     * @param theSystemId il system id del documento, usato solo nei messaggi di errore; può essere null
     * @param theStream   lo stream da leggere; chiuderlo resta compito del chiamante
     * @return il reader
     * @throws XMLStreamException se il reader non si può creare
     */
    public static XMLStreamReader newSecureReader(String theSystemId, @Nonnull(when = NEVER) InputStream theStream)
            throws XMLStreamException {
        checkArgument(theStream != null, "Il parametro theStream non deve essere null.");
        return newSecureFactory().createXMLStreamReader(theSystemId, theStream);
    }

    /**
     * Crea un {@link XMLStreamReader} sicuro su uno stream di caratteri.
     *
     * @param theSystemId il system id del documento, usato solo nei messaggi di errore; può essere null
     * @param theReader   il reader da leggere; chiuderlo resta compito del chiamante
     * @return il reader
     * @throws XMLStreamException se il reader non si può creare
     */
    public static XMLStreamReader newSecureReader(String theSystemId, @Nonnull(when = NEVER) Reader theReader)
            throws XMLStreamException {
        checkArgument(theReader != null, "Il parametro theReader non deve essere null.");
        return newSecureFactory().createXMLStreamReader(theSystemId, theReader);
    }

    /**
     * Crea un {@link XMLStreamReader} sicuro da una {@link StreamSource} che contiene uno stream di byte o di
     * caratteri.
     *
     * @param theSource la sorgente da leggere
     * @return il reader
     * @throws XMLStreamException se la sorgente non ha né uno stream di byte né uno di caratteri, o se il reader
     *                            non si può creare
     */
    public static XMLStreamReader newSecureReader(@Nonnull(when = NEVER) StreamSource theSource) throws XMLStreamException {
        checkArgument(theSource != null, "Il parametro theSource non deve essere null.");
        if (theSource.getInputStream() != null) {
            return newSecureReader(theSource.getSystemId(), theSource.getInputStream());
        }
        if (theSource.getReader() != null) {
            return newSecureReader(theSource.getSystemId(), theSource.getReader());
        }
        throw new XMLStreamException("La StreamSource non contiene né un InputStream né un Reader.");
    }
}
