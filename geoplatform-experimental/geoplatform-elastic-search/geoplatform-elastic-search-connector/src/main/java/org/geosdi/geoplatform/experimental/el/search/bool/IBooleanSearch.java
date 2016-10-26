package org.geosdi.geoplatform.experimental.el.search.bool;

import com.google.common.base.Preconditions;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.geosdi.geoplatform.experimental.el.search.IGPQuerySearch;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IBooleanSearch extends IGPQuerySearch {

    /**
     * @return {@link BooleanQueryType}
     */
    BooleanQueryType getType();

    /**
     *
     */
    enum BooleanQueryType {
        MUST, SHOULD, MUST_NOT, FILTER;
    }

    /**
     *
     */
    enum OperatorType {
        AND, NOT;
    }

    /**
     *
     */
    abstract class AbstractBooleanSearch implements IBooleanSearch {
        protected Object value;
        protected String field;
        protected final BooleanQueryType type;
        protected MatchQueryBuilder.Operator operator;

        public AbstractBooleanSearch(String theField, BooleanQueryType theType) {
            Preconditions.checkArgument(((theField != null) && !(theField.isEmpty())),
                    "The Parameter Field must not be null or an Empty String.");
            Preconditions.checkArgument((theType != null), "The Parameter Type must not be null.");
            this.field = theField;
            this.type = theType;
        }

        public AbstractBooleanSearch(String theField, Object theValue, BooleanQueryType theType) {
            Preconditions.checkArgument(((theField != null) && !(theField.isEmpty())),
                    "The Parameter Field must not be null or an Empty String.");
            Preconditions.checkArgument(((theValue != null)), "The Parameter Value must not be null.");
            Preconditions.checkArgument((theType != null), "The Parameter Type must not be null.");
            this.field = theField;
            this.value = theValue;
            this.type = theType;
        }

        public AbstractBooleanSearch(Object theValue, BooleanQueryType theType,
                MatchQueryBuilder.Operator theOperator) {
            Preconditions.checkArgument(((theValue != null)), "The Parameter Value must not be null.");
            Preconditions.checkArgument((theType != null), "The Parameter Type must not be null.");
            Preconditions.checkArgument((theOperator != null), "The Parameter Operator must not be null.");
            this.value = theValue;
            this.type = theType;
            this.operator = theOperator;
        }

        public AbstractBooleanSearch(Object theValue, String theField, BooleanQueryType theType,
                MatchQueryBuilder.Operator theOperator) {
            Preconditions.checkArgument(((theValue != null)), "The Parameter Value must not be null.");
            Preconditions.checkArgument((theType != null), "The Parameter Type must not be null.");
            Preconditions.checkArgument((theOperator != null), "The Parameter Operator must not be null.");
            Preconditions.checkArgument(((theField != null) && !(theField.isEmpty())),
                    "The Parameter Field must not be null or an Empty String.");
            this.value = theValue;
            this.field = theField;
            this.type = theType;
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
         * @return {@link String}
         */
        public String getField() {
            return this.field;
        }

        /**
         * @return {@link Object}
         */
        public Object getValue() {
            return this.value;
        }

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
