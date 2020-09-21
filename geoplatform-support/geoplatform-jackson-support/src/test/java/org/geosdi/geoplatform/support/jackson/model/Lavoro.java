/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.model;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "LAVORO")
@XmlType(propOrder = {"CODICE", "DESCRIZIONE", "IMPORTO", "CODICE_CFT", "DESC_CFT", "ORDINE_MASTER", "CODICE_PREVENTIVO",
        "NUM_PROTOCOLLO", "ATTIVITA"})
@Getter
@Setter
@ToString
public class Lavoro implements Serializable {

    private static final long serialVersionUID = 9122978894778780386L;
    //
    @XmlElement(name = "CODICE")
    private String codice;
    @XmlElement(name = "DESCRIZIONE")
    private String descrizione;
    @XmlElement(name = "IMPORTO")
    private double importo;
    @XmlElement(name = "CODICE_CFT")
    private String codiceCFT;
    @XmlElement(name = "DESC_CFT")
    private String descCFT;
    @XmlElement(name = "ORDINE_MASTER")
    private String ordineMaster;
    @XmlElement(name = "CODICE_PREVENTIVO")
    private String codicePreventivo;
    @XmlElement(name = "NUM_PROTOCOLLO")
    private String numProtocollo;
    @XmlElement(name = "ATTIVITA")
    private List<Attivita> attivita = Lists.newArrayList();
}