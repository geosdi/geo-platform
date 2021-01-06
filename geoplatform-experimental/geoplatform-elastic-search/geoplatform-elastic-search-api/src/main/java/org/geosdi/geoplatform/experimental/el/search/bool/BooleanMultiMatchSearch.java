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
package org.geosdi.geoplatform.experimental.el.search.bool;

import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Immutable
@ToString(callSuper = true)
public class BooleanMultiMatchSearch extends IBooleanSearch.AbstractBooleanSearch {

    private final String[] fields;

    /**
     * @param theValue
     * @param theType
     * @param theOperator
     * @param theFields
     */
    public BooleanMultiMatchSearch(@Nonnull(when = NEVER) Object theValue, @Nonnull(when = NEVER) BooleanQueryType theType,
            @Nonnull(when = NEVER) Operator theOperator, @Nonnull(when = NEVER) String[] theFields) {
        super(theValue, theType, theOperator);
        checkArgument((theFields != null) && (theFields.length > 0), "The Parameter fields must not be null and must contains almost one element.");
        List<String> fieldsList = stream(theFields)
                .filter(Objects::nonNull)
                .filter(field -> !(field.trim().isEmpty()))
                .collect(toList());
        checkArgument((fieldsList != null) && !(fieldsList.isEmpty()), "The Parameter fields must not contains null values or empty string.");
        this.fields = fieldsList.stream().toArray(String[]::new);
    }

    /**
     * @return {@link QueryBuilder}
     */
    @Override
    public QueryBuilder buildQuery() {
        return multiMatchQuery(value, fields).operator(this.operator);
    }
}