package org.geosdi.geoplatform.connector;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import java.io.File;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
public class GPConnectorFile implements IGPConnectorFile {

    private static final long serialVersionUID = -2705556053561317244L;
    //
    private final String key;
    private final File file;

    /**
     * @param theKey
     * @param theFile
     */
    public GPConnectorFile(@Nonnull(when = NEVER) String theKey, @Nonnull(when = NEVER) File theFile) {
        checkArgument((theKey != null) && !(theKey.trim().isEmpty()), "The Parameter key must not be null or an empty string.");
        checkArgument((theFile != null) && (theFile.exists()), "The Parameter file must not be null and must exist.");
        this.key = theKey;
        this.file = theFile;
    }
}