package org.geosdi.geoplatform.services.response;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.geojson.FeatureCollection;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@NoArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class WMSGetFeatureInfoResponse implements GPWMSGetFeatureInfoResponse {

    private static final long serialVersionUID = 8513460200609874662L;
    //
    private List<FeatureCollection> features = Lists.newArrayList();

    /**
     * @param theFeatureCollection
     */
    @Override
    public void addFeatureCollection(@Nonnull(when = NEVER) FeatureCollection theFeatureCollection) {
        if (theFeatureCollection != null)
            this.features.add(theFeatureCollection);
    }
}