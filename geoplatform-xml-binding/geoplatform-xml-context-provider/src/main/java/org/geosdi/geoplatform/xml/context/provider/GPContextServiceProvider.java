package org.geosdi.geoplatform.xml.context.provider;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPContextServiceProvider implements IGPContextServiceProvider {

    private final String contextPath;
    private final Class[] contextClasses;

    /**
     * @param theContextPath
     * @param theContextClasses
     */
    public GPContextServiceProvider(String theContextPath, Class[] theContextClasses) {
        checkArgument((theContextPath != null) && !(theContextPath.isEmpty()),
                "The Parameter contextPath must not be null or an Empty String.");
        checkArgument((theContextClasses != null) && (theContextClasses.length > 0) && (theContextClasses[0] != null),
                "The Parameter classes must not be null and must contains almost one Class.");
        this.contextPath = theContextPath;
        this.contextClasses = theContextClasses;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getContextPath() {
        return this.contextPath;
    }

    /**
     * @return {@link Class<T>[]}
     */
    @Override
    public <T> Class<T>[] getContextClasses() {
        return this.contextClasses;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  contextPath = '" + contextPath + '\'' +
                ", contextClasses = " + Arrays.toString(contextClasses) +
                "  type = '" + getType() + '\'' +
                '}';
    }
}
