package org.geosdi.geoplatform.connector.geowebcache.model.entry;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@ToString
@XmlAccessorType(value = XmlAccessType.FIELD)
public class GeowebcacheParameterEntry implements IGeowebcacheParameterEntry {

    private static final long serialVersionUID = 358810488942497528L;
    //
    @XmlElement(name = "string")
    private List<String> values = Lists.newArrayList();

    /**
     * @param theValue
     */
    @Override
    public void addValue(@Nonnull(when = NEVER) String theValue) {
        checkArgument((theValue != null) && !(theValue.trim().isEmpty()), "The Parameter value must not be null or an Empty String.");
        this.values.add(theValue);
    }
}