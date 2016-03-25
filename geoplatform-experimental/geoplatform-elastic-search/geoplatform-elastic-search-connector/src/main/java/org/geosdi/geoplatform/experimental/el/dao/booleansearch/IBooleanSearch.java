package org.geosdi.geoplatform.experimental.el.dao.booleansearch;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IBooleanSearch{

    QueryBuilder buildQuery();

    BooleanQueryType getType();

    public enum BooleanQueryType {
        MUST, SHOULD,MUST_NOT;
    }

    public enum OperatorType {
        AND, NOT;
    }

    abstract class AbstractBooleanSearch implements IBooleanSearch{
        protected String value;
        protected String field;
        protected BooleanQueryType type;
        protected MatchQueryBuilder.Operator operator;

        /**
         * @return {@link String}
         */
        public String getField() {
            return field;
        }

        /**
         * @return {@link BooleanQueryType}
         */
        public BooleanQueryType getType() {
            return type;
        }

        /**
         * @return {@link String}
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         *
         * @return {@link MatchQueryBuilder.Operator}
         */
        public MatchQueryBuilder.Operator getOperator() { return operator; }

        public abstract QueryBuilder buildQuery();

        @Override
        public String toString() {
            return "BooleanExactSearch{" +
                    "field='" + field + '\'' +
                    ", value='" + value + '\'' +
                    ", type=" + type +
                    ", operator=" + operator +
                    '}';
        }

    }

}
