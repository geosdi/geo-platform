package org.geosdi.geoplatform.persistence.configuration.properties.export;

import org.hibernate.tool.schema.TargetType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPTargetTypeConfig {

    @Bean
    public static EnumSet<TargetType> gpTargetType(@Value("persistence{schema_export_target_type:@null}") String targetType) {
        return ((targetType != null) && !(targetType.isEmpty()) ? EnumSet.of(buildTargetType(targetType)) : EnumSet.of(TargetType.SCRIPT));
    }

    static TargetType buildTargetType(String source) {
        for (TargetType targetType : TargetType.values()) {
            if (targetType.name().equalsIgnoreCase(source)) {
                return targetType;
            }
        }
        return TargetType.SCRIPT;
    }
}
