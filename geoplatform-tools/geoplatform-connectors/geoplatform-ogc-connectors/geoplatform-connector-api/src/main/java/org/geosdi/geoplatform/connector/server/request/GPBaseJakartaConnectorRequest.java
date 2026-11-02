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
package org.geosdi.geoplatform.connector.server.request;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.exception.IncorrectResponseException;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Base delle richieste che leggono la risposta con JAXB {@code jakarta.xml.bind}: è la versione jakarta di
 * {@link GPBaseConnectorRequest}, con la stessa gestione della risposta HTTP.
 *
 * @param <T> il tipo della risposta
 * @param <H> il tipo della richiesta HTTP
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPBaseJakartaConnectorRequest<T, H extends HttpUriRequest> extends GPAbstractConnectorRequest<T> implements GPJakartaConnectorRequest<T> {

    /**
     * @param theServerConnector il connettore del server
     */
    protected GPBaseJakartaConnectorRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector);
    }

    /**
     * @return {@link T}
     * @throws Exception se la richiesta non riesce
     */
    @Override
    public T getResponse() throws Exception {
        HttpUriRequest httpUriRequest = this.prepareHttpMethod();
        return this.securityConnector.secure(this, httpUriRequest, this::internalResponseAsEntity);
    }

    /**
     * @return {@link String}
     * @throws Exception se la richiesta non riesce
     */
    @Override
    public String getResponseAsString() throws Exception {
        HttpUriRequest httpUriRequest = this.prepareHttpMethod();
        return super.securityConnector.secure(this, httpUriRequest, this::internalResponseAsString);
    }

    /**
     * @return {@link InputStream}
     * @throws Exception se la richiesta non riesce
     */
    @Override
    public InputStream getResponseAsStream() throws Exception {
        HttpUriRequest httpUriRequest = this.prepareHttpMethod();
        return super.securityConnector.secure(this, httpUriRequest, this::internalResponseAsStream);
    }

    /**
     * @param streamConsumer chi legge lo stream della risposta
     * @param <R> il tipo restituito da chi legge lo stream
     * @return {@link R}
     * @throws Exception se la richiesta o la lettura non riescono
     */
    @Override
    public <R> R getResponseAsStream(GPConnectorStreamConsumer<R> streamConsumer) throws Exception {
        checkArgument(streamConsumer != null, "The Parameter streamConsumer must not be null.");
        HttpUriRequest httpUriRequest = this.prepareHttpMethod();
        return super.securityConnector.secure(this, httpUriRequest, httpResponse -> this.consumeResponseStream(httpResponse, streamConsumer));
    }

    /**
     * Legge la risposta con l'{@link Unmarshaller} jakarta della richiesta.
     *
     * @param inputStream lo stream della risposta
     * @return {@link T}
     * @throws Exception se la risposta è vuota o non è leggibile
     */
    protected T readInternal(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter inputStream must not be null.");
        Unmarshaller unmarshaller = getUnmarshaller();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, UTF_8_CHARSERT))) {
            Object content = unmarshaller.unmarshal(reader);
            if ((content == null)) { // ExceptionReport
                logger.error("#############CONTENT : {} #############\n", content);
                throw new IncorrectResponseException();
            }
            return ((content instanceof JAXBElement) ? ((JAXBElement<T>) content).getValue() : (T) content);
        } catch (JAXBException ex) {
            logger.error("######################JAXBException : {}", ex.toString(), ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link H}
     * @throws Exception se non è possibile preparare la richiesta HTTP
     */
    protected abstract H prepareHttpMethod() throws Exception;
}
