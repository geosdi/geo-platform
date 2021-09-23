package org.geosdi.geoplatform.connector.geoserver.coveragestores;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPProjectionPolicy {
    REPROJECT_TO_DECLARED,
    FORCE_DECLARED,
    NONE;

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}