/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
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
@XmlRootElement(name = "LCL")
@XmlType(propOrder = {"NUMERO_LCL", "DATA_EMISSIONE", "DESCRIZIONE", "DATA_INIZIO_LAVORI", "DATA_FINE_LAVORI",
        "VALORE_PUNTOA", "VALORE_PUNTOB", "IMPORTO_PRESUNTO", "CODICE_GO", "DESCRIZIONE_GO", "OPERATORE_ENEL",
        "PROGETTISTA", "CAPO_UNITA", "VERSIONE_MATERIALE", "CONTRATTO", "EMITTENTE_FATTURA", "INTESTATARIO_ORDINE",
        "INTESTATARIO_CONTRATTO", "ZONA", "NOTE", "LAVORO"})
@Getter
@Setter
@ToString
public class Lcl implements Serializable {

    private static final long serialVersionUID = 3312521404618376238L;
    //
    @XmlElement(name = "NUMERO_LCL")
    private String numeroLcl;
    @XmlElement(name = "DATA_EMISSIONE")
    private String dataEmissione;
    @XmlElement(name = "DESCRIZIONE")
    private String descrizione;
    @XmlElement(name = "DATA_INIZIO_LAVORI")
    private String dataInizioLavori;
    @XmlElement(name = "DATA_FINE_LAVORI")
    private String dataFineLavori;
    @XmlElement(name = "VALORE_PUNTOA")
    private double valorePuntoA;
    @XmlElement(name = "VALORE_PUNTOB")
    private double valorePuntoB;
    @XmlElement(name = "IMPORTO_PRESUNTO")
    private double importoPresunto;
    @XmlElement(name = "CODICE_GO")
    private String codiceGO;
    @XmlElement(name = "DESCRIZIONE_GO")
    private String descrizioneGO;
    @XmlElement(name = "OPERATORE_ENEL")
    private String operatoreEnel;
    @XmlElement(name = "PROGETTISTA")
    private String progettista;
    @XmlElement(name = "CAPO_UNITA")
    private String capoUnita;
    @XmlElement(name = "VERSIONE_MATERIALE")
    private String versioneMateriale;
    @XmlElement(name = "CONTRATTO")
    private Contratto contratto;
    @XmlElement(name = "EMITTENTE_FATTURA")
    private EmittenteFattura emittenteFattura;
    @XmlElement(name = "INTESTATARIO_ORDINE")
    private IntestatarioOrdine intestatarioOrdine;
    @XmlElement(name = "INTESTATARIO_CONTRATTO")
    private IntestatarioContratto intestatarioContratto;
    @XmlElement(name = "ZONA")
    private Zona zona;
    @XmlElement(name = "NOTE")
    private String note;
    @XmlElement(name = "LAVORO")
    private List<Lavoro> lavori = Lists.newArrayList();
}