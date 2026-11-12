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
package org.geosdi.geoplatform.experimental.el9.search.bool;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.MultiFieldsSearch;
import org.geosdi.geoplatform.experimental.el9.dao.GPPageableElasticSearchDAO.Page;
import org.geosdi.geoplatform.experimental.el9.search.IGPQuerySearch;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Ricerca da usare come clausola di una query bool: dice in quale parte della bool va messa.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IBooleanSearch extends IGPQuerySearch {

    /**
     * @return {@link BooleanQueryType} la parte della query bool in cui va la clausola
     */
    BooleanQueryType getType();

    /**
     * @return {@link Page} una pagina che contiene solo questa ricerca
     */
    default Page toPage() {
        return new MultiFieldsSearch(this);
    }

    /**
     * Le parti di una query bool.
     */
    enum BooleanQueryType {
        MUST, SHOULD, MUST_NOT, FILTER;
    }

    /**
     * Base delle ricerche su un campo, con valore e operatore facoltativi.
     */
    abstract class AbstractBooleanSearch implements IBooleanSearch {

        protected final BooleanQueryType type;
        protected Object value;
        protected String field;
        protected Operator operator;

        /**
         * @param theField il campo, non vuoto
         * @param theType  la parte della query bool
         */
        protected AbstractBooleanSearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) BooleanQueryType theType) {
            checkArgument((theField != null) && !(theField.trim().isEmpty()), "Il parametro field non deve essere null né vuoto.");
            checkArgument(theType != null, "Il parametro type non deve essere null.");
            this.field = theField;
            this.type = theType;
        }

        /**
         * @param theField il campo, non vuoto
         * @param theValue il valore da cercare
         * @param theType  la parte della query bool
         */
        protected AbstractBooleanSearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) Object theValue,
                @Nonnull(when = NEVER) BooleanQueryType theType) {
            this(theField, theType);
            checkArgument(theValue != null, "Il parametro value non deve essere null.");
            this.value = theValue;
        }

        /**
         * Costruttore per le ricerche su più campi, che non hanno un campo solo.
         *
         * @param theValue    il valore da cercare
         * @param theType     la parte della query bool
         * @param theOperator l'operatore tra i termini del valore
         */
        protected AbstractBooleanSearch(@Nonnull(when = NEVER) Object theValue, @Nonnull(when = NEVER) BooleanQueryType theType,
                @Nonnull(when = NEVER) Operator theOperator) {
            checkArgument(theValue != null, "Il parametro value non deve essere null.");
            checkArgument(theType != null, "Il parametro type non deve essere null.");
            checkArgument(theOperator != null, "Il parametro operator non deve essere null.");
            this.value = theValue;
            this.type = theType;
            this.operator = theOperator;
        }

        /**
         * @param theField    il campo, non vuoto
         * @param theValue    il valore da cercare
         * @param theType     la parte della query bool
         * @param theOperator l'operatore tra i termini del valore
         */
        protected AbstractBooleanSearch(@Nonnull(when = NEVER) String theField, @Nonnull(when = NEVER) Object theValue,
                @Nonnull(when = NEVER) BooleanQueryType theType, @Nonnull(when = NEVER) Operator theOperator) {
            this(theField, theValue, theType);
            checkArgument(theOperator != null, "Il parametro operator non deve essere null.");
            this.operator = theOperator;
        }

        /**
         * @return {@link BooleanQueryType}
         */
        @Override
        public BooleanQueryType getType() {
            return this.type;
        }

        /**
         * @return {@link String} il campo, null per le ricerche su più campi
         */
        public String getField() {
            return this.field;
        }

        /**
         * @return {@link Object} il valore, null per le ricerche che non ne hanno
         */
        public Object getValue() {
            return this.value;
        }

        /**
         * @return {@link Operator} l'operatore, null se la ricerca non lo usa
         */
        public Operator getOperator() {
            return this.operator;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    " field = '" + field + '\'' +
                    ", value = '" + value + '\'' +
                    ", type = " + type +
                    ", operator = " + operator +
                    '}';
        }
    }
}
