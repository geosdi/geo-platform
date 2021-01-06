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
package org.geosdi.geoplatform.gui.client.widget.cql.enumeration;

import org.geosdi.geoplatform.gui.client.widget.cql.CQLSimpleOperator;
import org.geosdi.geoplatform.gui.client.widget.cql.CQLStringOperator;
import org.geosdi.geoplatform.gui.client.widget.cql.ICQLBasicOperator;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public enum CQLOperatorEnum implements ICQLOperator {

    EQUAL("=") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLSimpleOperator(super.symbol);
        }

        @Override
        public String getValue() {
            return super.symbol;
        }
    }, NOT_EQUAL("<>") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLSimpleOperator(super.symbol);
        }

        @Override
        public String getValue() {
            return super.symbol;
        }
    },
    LESS("<") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLSimpleOperator(super.symbol);
        }

        @Override
        public String getValue() {
            return super.symbol;
        }
    }, GREATER(">") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLSimpleOperator(super.symbol);
        }

        @Override
        public String getValue() {
            return super.symbol;
        }
    },
    LESS_OR_EQUAL("<=") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLSimpleOperator(super.symbol);
        }

        @Override
        public String getValue() {
            return super.symbol;
        }
    },
    GREATER_OR_EQUAL(">=") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLSimpleOperator(super.symbol);
        }

        @Override
        public String getValue() {
            return super.symbol;
        }
    },
    LIKE("like") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLStringOperator(super.symbol);
        }

        @Override
        public String getValue() {
            return "like";
        }
    },
    STARTS_WITH("starts with") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLStringOperator("like") {
                @Override
                public String generateExpression(String featureAttribute, String conditionValue) {
                    return super.generateExpression(featureAttribute, conditionValue + "%");
                }
            };
        }

        @Override
        public String getValue() {
            return "like";
        }
    },
    ENDS_WITH("ends with") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLStringOperator("like") {
                @Override
                public String generateExpression(String featureAttribute, String conditionValue) {
                    return super.generateExpression(featureAttribute, "%" + conditionValue);
                }
            };
        }

        @Override
        public String getValue() {
            return "like";
        }
    },
    CONTAINS("contains") {
        @Override
        public ICQLBasicOperator getOperatorInstance() {
            return new CQLStringOperator("like") {
                @Override
                public String generateExpression(String featureAttribute, String conditionValue) {
                    return super.generateExpression(featureAttribute, "%" + conditionValue + "%");
                }
            };
        }

        @Override
        public String getValue() {
            return "like";
        }
    };
    private String symbol;

    CQLOperatorEnum(String symbol) {
        this.symbol = symbol;
    }

    public abstract ICQLBasicOperator getOperatorInstance();

    public abstract String getValue();

    @Override
    public String toString() {
        return this.symbol;
    }
//(BinaryComparisonOpType) GWT.create(
}
