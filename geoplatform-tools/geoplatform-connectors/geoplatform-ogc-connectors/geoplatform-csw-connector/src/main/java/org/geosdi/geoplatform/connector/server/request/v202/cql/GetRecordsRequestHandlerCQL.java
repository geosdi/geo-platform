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
package org.geosdi.geoplatform.connector.server.request.v202.cql;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.xml.csw.ConstraintLanguage.CQL_TEXT;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class GetRecordsRequestHandlerCQL implements GPGetRecordsRequestHandlerCQL {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final org.geosdi.geoplatform.xml.filter.v110.ObjectFactory filterFactory = new org.geosdi.geoplatform.xml.filter.v110.ObjectFactory();
    protected static final org.geosdi.geoplatform.xml.gml.v311.ObjectFactory gmlFactory = new org.geosdi.geoplatform.xml.gml.v311.ObjectFactory();
    //
    private GetRecordsRequestHandlerCQL successor;

    GetRecordsRequestHandlerCQL() {
    }

    /**
     * @param request
     * @throws Exception
     */
    public void forwardGetRecordsRequest(@Nonnull(when = NEVER) CatalogGetRecordsRequest request) throws Exception {
        this.processGetRecordsRequest(request);
        if (successor != null) {
            successor.forwardGetRecordsRequest(request);
        }
    }

    /**
     * @param theSuccessor
     */
    public void setSuccessor(@Nullable GetRecordsRequestHandlerCQL theSuccessor) {
        successor = theSuccessor;
    }

    /**
     * @param request
     * @throws IllegalParameterFault
     */
    protected abstract void processGetRecordsRequest(@Nonnull(when = NEVER) CatalogGetRecordsRequest request) throws Exception;

    /**
     * @param request
     * @param followingConstraint
     */
    protected void addCQLConstraint(@Nonnull(when = NEVER) CatalogGetRecordsRequest request, @Nonnull(when = NEVER) String followingConstraint) {
        checkArgument(request != null, "The Parameter request must not be null.");
        checkArgument(((followingConstraint != null) && !(followingConstraint.trim().isEmpty())), "The Parameter followingConstraint must not be null or an empty string.");
        checkArgument(request.getConstraintLanguage() == CQL_TEXT, "Constraint Language must be CQL_TEXT.");
        String previousConstraint = request.getConstraint();
        request.setConstraint((previousConstraint == null) ? followingConstraint : previousConstraint.concat(" OR ".concat(followingConstraint)));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{\n" +
                " handlerType = " + this.getType() +
                "\n}";
    }
}