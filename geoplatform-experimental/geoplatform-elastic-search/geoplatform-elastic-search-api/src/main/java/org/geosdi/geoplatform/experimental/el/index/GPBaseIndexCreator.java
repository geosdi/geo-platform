package org.geosdi.geoplatform.experimental.el.index;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPBaseIndexCreator {

    /**
     * <p>Create Index with {@link GPIndexSettings#getIndexName()} and {@link GPIndexSettings#getIndexType()}</p>
     *
     * @throws Exception
     */
    void createIndex() throws Exception;

    /**
     * <p>Delete Index</p>
     *
     * @throws Exception
     */
    void deleteIndex() throws Exception;

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    Boolean existIndex() throws Exception;

    /**
     * @return {@link Boolean}
     * @throws Exception
     */
    Boolean existsType() throws Exception;

    /**
     * @param <IS>
     * @return {@link GPIndexSettings}
     */
    <IS extends GPIndexSettings> IS getIndexSettings();

    /**
     * <p>
     * Index Settings Interface to define both IndexName and IndexType
     * </p>
     */
    interface GPIndexSettings {

        /**
         * @return {@link String} Index Name
         */
        String getIndexName();

        /**
         * @return {@link String} Index Type
         */
        String getIndexType();

    }
}
