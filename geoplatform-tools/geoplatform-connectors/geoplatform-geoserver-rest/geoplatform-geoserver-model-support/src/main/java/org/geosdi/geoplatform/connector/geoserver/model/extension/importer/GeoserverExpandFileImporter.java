package org.geosdi.geoplatform.connector.geoserver.model.extension.importer;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GeoserverExpandFileImporter implements GPFileExpandType<String>  {

    SELF,
    ALL,
    NONE;

    public String toString() {
        return this.name().toLowerCase();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getKey() {
        return "expand";
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValue() {
        return this.toString();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String formatValue() {
        return this.getValue();
    }
}