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
package org.geosdi.geoplatform.experimental.el.search.term;

import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.QueryBuilder;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class GPTermsSearch implements IBooleanSearch {

    private final String field;
    private final Object[] value;
    private final BooleanQueryType type;

    /**
     * @param theField
     * @param theValue
     * @param theType
     */
    public GPTermsSearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) BooleanQueryType theType, @Nonnull(when = NEVER) Object... theValue) {
        checkArgument(((theField != null) && !(theField.trim().isEmpty())), "The Parameter Field must not be null or an Empty String.");
        checkArgument(((theValue != null)), "The Parameter Value must not be null.");
        checkArgument((theType != null), "The Parameter Type must not be null.");
        this.field = theField;
        this.value = theValue;
        this.type = theType;
    }

    /**
     * @return {@link BooleanQueryType}
     */
    @Override
    public BooleanQueryType getType() {
        return this.type;
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return termsQuery(this.field, this.value);
    }
}