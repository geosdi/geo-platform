package org.geosdi.geoplatform.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
@XmlAccessorType(XmlAccessType.FIELD)
public class GPAuthServer implements IGPAuthServer {

    @Column
    String username;
    @Column
    String password;

}
