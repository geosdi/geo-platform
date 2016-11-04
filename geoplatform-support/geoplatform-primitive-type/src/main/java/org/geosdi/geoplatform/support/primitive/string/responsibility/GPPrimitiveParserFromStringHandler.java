package org.geosdi.geoplatform.support.primitive.string.responsibility;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPrimitiveParserFromStringHandler<Type extends Object> {

    /**
     * @param value
     * @return {@link Class<Type>}
     * @throws Exception
     */
    Class<Type> parseValue(String value) throws Exception;

    /**
     * @param <ParserType>
     * @return {@link ParserType}
     */
    <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType();

    /**
     * @return {@link Class<Type>}
     */
    Class<Type> getPrimitiveType();

    /**
     * @param theSuccessor
     * @param <Successor>
     * @return
     */
    <Successor extends GPPrimitiveParserFromStringHandler> void setSuccessor(Successor theSuccessor);

    /**
     *
     */
    interface IGPPrimitiveParserFromStringType {

        /**
         * @return {@link String}
         */
        String getType();

        enum GPPrimitiveParserFromStringType implements IGPPrimitiveParserFromStringType {
            INTEGER("Interger"),
            DOUBLE("Double"),
            TEMPORAL("Temporal"),
            STRING("String");

            private final String type;

            GPPrimitiveParserFromStringType(String theType) {
                this.type = theType;
            }


            /**
             * @return {@link String}
             */
            @Override
            public String getType() {
                return this.type;
            }

            @Override
            public String toString() {
                return this.type;
            }

            /**
             * @param theType
             * @return {@link GPPrimitiveParserFromStringType}
             */
            public static GPPrimitiveParserFromStringType forType(String theType) {
                Optional<GPPrimitiveParserFromStringType> optional = Arrays.stream(GPPrimitiveParserFromStringType.values())
                        .filter(primitiveParserStringType -> primitiveParserStringType.getType()
                                .equalsIgnoreCase(theType)).findFirst();
                return (optional.isPresent() ? optional.get() : null);
            }
        }
    }
}
