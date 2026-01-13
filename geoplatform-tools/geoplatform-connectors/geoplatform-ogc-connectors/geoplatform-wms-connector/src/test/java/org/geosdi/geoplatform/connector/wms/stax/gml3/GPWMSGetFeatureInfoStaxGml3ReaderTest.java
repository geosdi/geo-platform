/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wms.stax.gml3;

import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSGetFeatureInfoStaxGml3ReaderTest extends WMSGetFeatureInfoStaxReaderGml3Test {

    @Test
    public void a_a_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CARTOGRAGIA_TEMATICA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("CartografiaTematica.xml"))));
    }

    @Test
    public void a_b_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_COMUNI_SARDEGNA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("ComuniSardegna.xml"))));
    }

    @Test
    public void a_c_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ALBERI_MONUMENTALI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("AlberiMonumentali.xml"))));
    }

    @Test
    public void a_d_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_GEOSITI_AREE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("FreaCovid.xml"))));
    }

    @Test
    public void a_e_wmsGetFeatureInfoStaxReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STRADE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("rw8_arno_de_ott_mag.xml"))));
    }

    @Test
    public void a_e_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_STRADE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("rw8_arno_de_ott_mag.xml"))));
    }

    @Test
    public void a_f_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_OLIVETI_CATASTALI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("OlivetiCatastali.xml"))));
    }

    @Test
    public void a_g_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_VIGNETI_CATASTALI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("VignetiCatastali.xml"))));
    }

    @Test
    public void a_h_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ETTARI_COMUNE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("EttariComune.xml"))));
    }

    @Test
    public void a_i_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_EVENTI_INGV : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("EventiIngv.xml"))));
    }

    @Test
    public void a_l_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PLUVIOMETRI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("Pluviometri.xml"))));
    }

    @Test
    public void a_m_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ARCO_FERROVIARIO_SICILIA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("ArcoFerroviarioSicilia.xml"))));
    }

    @Test
    public void a_n_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ESITI_AGIBILITA_FABBRICATI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("EsitiAgibilitaFabbricati.xml"))));
    }

    @Test
    public void a_o_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CARABINIERI_SICILIA : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("CarabinieriSicilia.xml"))));
    }

    @Test
    public void a_p_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_SALINE_STORICHE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("SalineStoriche.xml"))));
    }

    @Test
    public void a_q_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CBLN_COMPRENSORIO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("CBLN_COMPRENSORIO.xml"))));
    }

    @Test
    public void a_r_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ETPI_AMBITO_TERRITORIALE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("ETPI_AMBITO_TERRITORIALE.xml"))));
    }

    @Test
    public void a_s_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ETPI_RIPOPOLAMENTO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("ETPI_RIPOPOLAMENTO.xml"))));
    }

    @Test
    public void a_t_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_PPR06_AMBITI_DI_PAESAGGIO : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("PPR06_Ambiti_di_paesaggio_Limiti_d'ambito.xml"))));
    }

    @Test
    public void a_u_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CINEMA_TEATRI : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("CinemaTeatri.xml"))));
    }

    @Test
    public void a_v_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_ACQUE_PUBBLICHE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("AcquePubbliche.xml"))));
    }

    @Test
    public void a_x_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_CEN_ABI_POLYGON : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("cen_abi_a_polygon.xml"))));
    }

    @Test
    public void b_a_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AC0104021_PROVINCE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("Ac0104021_province.xml"))));
    }

    @Test
    public void b_b_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_AC0104021_PROVINCE : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("SpecchiAcqua.xml"))));
    }

    @Test
    public void b_c_wmsGetFeatureInfoStaxGml3ReaderTest() throws Exception {
        logger.info("#######################FEATURE_COLLECTION_WS_VITO_LAYER_IMPORTER148 : {}\n", JACKSON_SUPPORT.getDefaultMapper()
                .writeValueAsString(wmsGetFeatureInfoStaxGml3Reader.read(storage.find("WS_Vito_Layer_Importer148.xml"))));
    }
}