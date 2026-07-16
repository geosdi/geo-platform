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
package org.geosdi.geoplatform.connector.server.exception;

/**
 * Thrown when the server replies with an HTTP status outside the {@code 2xx} success family that is
 * not already mapped to a more specific exception by the request's
 * {@link org.geosdi.geoplatform.connector.server.request.GPConnectorRequest} implementation. It
 * carries both the status code and the raw response body, so callers can react to the actual server
 * error instead of a generic parsing failure.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPConnectorHttpStatusException extends RuntimeException {

    private final int statusCode;
    private final String responseBody;

    /**
     * @param theStatusCode the HTTP status code returned by the server.
     * @param theResponseBody the raw response body (may be {@code null} or empty).
     */
    public GPConnectorHttpStatusException(int theStatusCode, String theResponseBody) {
        super("Unexpected HTTP status " + theStatusCode
                + (((theResponseBody != null) && !theResponseBody.trim().isEmpty()) ? " : " + theResponseBody : ""));
        this.statusCode = theStatusCode;
        this.responseBody = theResponseBody;
    }

    /**
     * @return the HTTP status code returned by the server.
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * @return the raw response body returned by the server (may be {@code null} or empty).
     */
    public String getResponseBody() {
        return this.responseBody;
    }
}