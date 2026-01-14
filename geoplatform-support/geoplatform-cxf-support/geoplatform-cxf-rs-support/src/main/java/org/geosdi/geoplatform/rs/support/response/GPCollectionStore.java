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
package org.geosdi.geoplatform.rs.support.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlTransient
public abstract class GPCollectionStore<V extends Object, C extends Collection<V>> implements Serializable {

    private static final long serialVersionUID = -7307126903690919351L;
    //
    private final Long total;
    private final C values;

    /**
     * @param theTotal
     * @param theValues
     */
    protected GPCollectionStore(@Nonnull(when = NEVER) Long theTotal, @Nonnull(when = NEVER) C theValues) {
        checkArgument(theTotal != null, "The Parameter total must not be null.");
        checkArgument(theValues != null, "The Parameter values must not be null.");
        this.total = theTotal;
        this.values = theValues;
    }

    /**
     * @param theFunction
     * @param <T>
     * @return {@link GPCollectionSubStore<T, C>}
     * @throws Exception
     */
    public <T, C extends Collection<T>> GPCollectionSubStore<T, C> asCollectionSubStore(@Nonnull(when = NEVER) Function<V, T> theFunction, @Nonnull(when = NEVER) java.util.stream.Collector<T, ?, C> theCollector) throws Exception {
        checkArgument(theFunction != null, "The Parameter function must not be null.");
        checkArgument(theCollector != null, "The Parameter collector must not be null.");
        var subValues = this.values.stream()
                .filter(Objects::nonNull)
                .map(theFunction)
                .collect(theCollector);
        return new GPCollectionStore.GPCollectionSubStore<T, C>((long) subValues.size(), subValues);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GPCollectionSubStore<T, C extends Collection<T>> extends GPCollectionStore<T, C> {

        private static final long serialVersionUID = -8694126439502836978L;

        /**
         * @param theTotal
         * @param theValues
         */
        @JsonCreator
        public GPCollectionSubStore(@JsonProperty(value = "total") @Nonnull(when = NEVER) Long theTotal, @JsonProperty(value = "values") @Nonnull(when = NEVER) C theValues) {
            super(theTotal, theValues);
        }
    }
}