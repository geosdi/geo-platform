package org.geosdi.geoplatform.experimental.el.dao.store;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Map;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(value = FIELD)
@Immutable
public class PageMapStore<K, T> implements IPageMapStore<K, T> {

    private static final long serialVersionUID = 2376229993184167459L;
    //
    private final Map<K, List<T>> values;

    /**
     * @param theValues
     */
    @JsonCreator
    protected PageMapStore(@Nullable @JsonProperty(value = "values") Map<K, List<T>> theValues) {
        this.values = theValues;
    }
}