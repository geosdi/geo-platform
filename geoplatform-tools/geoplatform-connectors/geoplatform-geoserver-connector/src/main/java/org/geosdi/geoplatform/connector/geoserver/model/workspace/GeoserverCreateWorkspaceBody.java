package org.geosdi.geoplatform.connector.geoserver.model.workspace;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Getter
@XmlRootElement(name = "workspace")
@ToString
public class GeoserverCreateWorkspaceBody implements GPGeoserverCreateWorkspaceBody {

    private static final long serialVersionUID = -4977318205480633889L;
    //
    @XmlElement(name = "name")
    private final String workspaceName;

    /**
     * @param theWorkspaceName
     */
    public GeoserverCreateWorkspaceBody(@Nonnull(when = NEVER) String theWorkspaceName) {
        checkArgument((theWorkspaceName != null) && !(theWorkspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an Empty String.");
        this.workspaceName = theWorkspaceName;
    }
}