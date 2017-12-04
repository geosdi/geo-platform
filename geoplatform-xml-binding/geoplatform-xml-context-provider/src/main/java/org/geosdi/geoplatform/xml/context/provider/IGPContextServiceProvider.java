package org.geosdi.geoplatform.xml.context.provider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPContextServiceProvider {

    /**
     * @return {@link String}
     */
    String getContextPath();

    /**
     * @param <T>
     * @return {@link Class<T>[]}
     */
    <T extends Object> Class<T>[] getContextClasses();

    /**
     * @param <Type>
     * @return {@link Type}
     */
    <Type extends GPContextProviderType> Type getType();

    interface GPContextProviderType {

        /**
         * @return
         */
        String getType();
    }
}
