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
package org.geosdi.geoplatform.experimental.el9.search.term;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el9.search.bool.IBooleanSearch;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static javax.annotation.meta.When.NEVER;

/**
 * Ricerca dei documenti in cui il campo ha esattamente uno dei valori (query terms).
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Getter
@ToString
public class GPTermsSearch implements IBooleanSearch {

    private final String field;
    private final List<Object> values;
    private final BooleanQueryType type;

    /**
     * @param theField  il campo, non vuoto
     * @param theType   la parte della query bool
     * @param theValues i valori esatti: stringhe, numeri o booleani
     */
    public GPTermsSearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) BooleanQueryType theType,
            @Nonnull(when = NEVER) Object... theValues) {
        checkArgument((theField != null) && !(theField.trim().isEmpty()), "Il parametro field non deve essere null né vuoto.");
        checkArgument(theType != null, "Il parametro type non deve essere null.");
        checkArgument(theValues != null, "Il parametro values non deve essere null.");
        this.field = theField;
        this.type = theType;
        this.values = stream(theValues).filter(Objects::nonNull).toList();
    }

    /**
     * @return {@link Query}
     */
    @Override
    public Query buildQuery() {
        List<FieldValue> fieldValues = this.values.stream().map(FieldValue::of).toList();
        return Query.of(q -> q.terms(t -> t.field(this.field).terms(v -> v.value(fieldValues))));
    }
}
