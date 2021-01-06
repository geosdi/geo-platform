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
package org.geosdi.geoplatform.connector.wfs.transaction.stax;

import org.geosdi.geoplatform.connector.server.request.v110.WFSTransactionRequestV110;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.FeatureStreamWriter;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation.INSERT;
import static org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation.UPDATE;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class FeatureStreamWriterTest {

    private static final Logger logger = LoggerFactory.getLogger(FeatureStreamWriterTest.class);
    //
    private final static QName TASMANIA_ROADS = new QName("http://www.openplans.org/topp",
            "topp:tasmania_roads", "topp");
    private static final FeatureStreamWriter featureStreamWriter = new FeatureStreamWriter();

    @Test
    public void a_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(INSERT);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        AttributeDTO att = new AttributeDTO();
        att.setName("TYPE");
        att.setValue("VALUE");
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))");
        List values = Arrays.asList(att, geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void b_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(UPDATE);
        when(request.getFID()).thenReturn("fid_test");
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        AttributeDTO att = new AttributeDTO();
        att.setName("TYPE");
        att.setValue("VALUE");
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))");
        List values = Arrays.asList(att, geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void c_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(INSERT);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        AttributeDTO att = new AttributeDTO();
        att.setName("TYPE");
        att.setValue("VALUE");
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTIPOLYGON (((37.51099000000001 -88.071564, 37.476273000000006 -88.087883, 37.442852 -88.311707," +
                " 37.40930899999999 -88.359177, 37.420292 -88.419853, 37.400757 -88.467644, 37.296852 -88.511322, 37.257782000000006" +
                " -88.501427, 37.205669 -88.450699, 37.156909999999996 -88.422516, 37.098670999999996 -88.45047, " +
                "37.072143999999994 -88.476799, 37.06818 -88.4907, 37.064769999999996 -88.517273, 37.072815000000006 -88.559273," +
                " 37.109047000000004 -88.61422, 37.13540999999999 -88.68837, 37.141182 -88.739113, 37.152107 -88.746506, " +
                "37.202194000000006 -88.863289, 37.218407 -88.932503, 37.22003599999999 -88.993172, 37.185860000000005 -89.065033, " +
                "37.112137000000004 -89.116821, 37.093185000000005 -89.146347, 37.064235999999994 -89.169548, 37.025711 -89.174332," +
                " 36.99844 -89.150246, 36.988113 -89.12986, 36.986771000000005 -89.193512, 37.02897299999999 -89.210052, " +
                "37.041732999999994 -89.237679, 37.087124 -89.264053, 37.091244 -89.284233, 37.085384000000005 -89.303291," +
                " 37.060908999999995 -89.3097, 37.027733 -89.264244, 37.008686 -89.262001, 36.999207 -89.282768, 37.009682 -89.310982, " +
                "37.049212999999995 -89.38295, 37.09908299999999 -89.37999, 37.137203 -89.423798, 37.165318 -89.440521, " +
                "37.224266 -89.468216, 37.253731 -89.465309, 37.256001 -89.489594, 37.276402000000004 -89.513885," +
                " 37.304962 -89.513885, 37.329441 -89.50058, 37.339409 -89.468742, 37.355717 -89.435738, 37.411018 -89.427574, " +
                "37.453186 -89.453621, 37.491726 -89.494781, 37.571957 -89.524971, 37.615928999999994 -89.513367," +
                " 37.650375 -89.51918, 37.67984 -89.513374, 37.694798000000006 -89.521523, 37.706103999999996 -89.581436," +
                " 37.745453 -89.666458, 37.78397 -89.675858, 37.804794 -89.691055, 37.840992 -89.728447, " +
                "37.905063999999996 -89.851715, 37.905486999999994 -89.861046, 37.891875999999996 -89.866814, " +
                "37.875904000000006 -89.900551, 37.878044 -89.937874, 37.911884 -89.978912, 37.963634 -89.958229, " +
                "37.969318 -90.010811, 37.993206 -90.041924, 38.032272000000006 -90.119339, 38.05395100000001 -90.134712, " +
                "38.08890500000001 -90.207527, 38.122169000000014 -90.254059, 38.16681700000001 -90.289635," +
                " 38.18871300000001 -90.336716, 38.23429899999999 -90.364769, 38.32355899999999 -90.369347, 38.36533 -90.358688," +
                " 38.39084600000001 -90.339607, 38.427357 -90.301842, 38.518688 -90.265785, 38.532768000000004 -90.26123, " +
                "38.562805 -90.240944, 38.61027100000001 -90.183708, 38.658772 -90.183578, 38.70036300000001 -90.20224," +
                " 38.72396499999999 -90.196571, 38.773098000000005 -90.163399, 38.785484 -90.135178, 38.80051 -90.121727," +
                " 38.830467 -90.113121, 38.85303099999999 -90.132812, 38.91450900000001 -90.243927, 38.92471699999999 -90.278931," +
                " 38.92490799999999 -90.31974, 38.96233000000001 -90.413071, 38.959179000000006 -90.469841," +
                " 38.89160899999999 -90.530426, 38.87132600000001 -90.570328, 38.880795000000006 -90.627213," +
                " 38.93525299999999 -90.668877, 39.037791999999996 -90.70607, 39.058178 -90.707588, 39.09370000000001 -90.690399," +
                " 39.14421100000001 -90.716736, 39.195873000000006 -90.718193, 39.22474700000001 -90.732338," +
                " 39.24780999999999 -90.738083, 39.29680300000001 -90.779343, 39.35045199999999 -90.850494," +
                " 39.40058500000001 -90.947891, 39.444412 -91.036339, 39.473984 -91.064384, 39.52892700000001 -91.093613, " +
                "39.552593 -91.156189, 39.600021 -91.203247, 39.68591699999999 -91.317665, 39.724639999999994 -91.367088, " +
                "39.76127199999999 -91.373421, 39.80377200000001 -91.381714, 39.86304899999999 -91.449188, " +
                "39.885242000000005 -91.450989, 39.90182899999999 -91.434052, 39.92183700000001 -91.430389," +
                " 39.94606400000001 -91.447243, 40.005753 -91.487289, 40.066711 -91.504005, 40.134544000000005 -91.516129," +
                " 40.200458999999995 -91.506546, 40.25137699999999 -91.498932, 40.309624000000014 -91.486694," +
                " 40.371902000000006 -91.448593, 40.386875 -91.418816, 40.392360999999994 -91.385757, 40.40298799999999 -91.372757, " +
                "40.44725 -91.385399, 40.50365400000001 -91.374794, 40.52849599999999 -91.382103, 40.54799299999999 -91.412872," +
                " 40.572970999999995 -91.411118, 40.60343900000001 -91.37561, 40.639545 -91.262062, 40.64381800000001 -91.214912," +
                " 40.65631099999999 -91.162498, 40.68214800000001 -91.129158, 40.70540199999999 -91.119987, 40.76154700000001 -91.092751," +
                " 40.833729000000005 -91.088905, 40.87958499999999 -91.04921, 40.92392699999999 -90.983276, 40.950503999999995 -90.960709," +
                " 41.07036199999999 -90.954651, 41.10435899999999 -90.957787, 41.14437100000001 -90.990341, 41.16582500000001 -91.018257," +
                " 41.17625799999999 -91.05632, 41.23152200000001 -91.101524, 41.267818000000005 -91.102348, 41.334895999999986 -91.07328," +
                " 41.40137899999999 -91.055786, 41.423508 -91.027489, 41.431084 -91.000694, 41.421234 -90.949654," +
                " 41.44462200000001 -90.844139, 41.449820999999986 -90.7799, 41.450062 -90.708214, 41.46231800000001 -90.658791, " +
                "41.50958600000001 -90.6007, 41.52597 -90.54084, 41.527546 -90.454994, 41.543578999999994 -90.434967," +
                " 41.567272 -90.423004, 41.586849 -90.348366, 41.60279800000001 -90.339348, 41.64909 -90.341133, 41.722736 -90.326027," +
                " 41.75646599999999 -90.304886, 41.78173799999999 -90.25531, 41.80613700000001 -90.195839, 41.93077500000001 -90.154518," +
                " 41.98396299999999 -90.14267, 42.03342799999999 -90.150536, 42.06104300000001 -90.168098, 42.103745 -90.166649, " +
                "42.12050199999999 -90.176086, 42.12268800000001 -90.191574, 42.15972099999999 -90.230934, 42.19731899999999 -90.323601, " +
                "42.21020899999999 -90.367729, 42.24264500000001 -90.407173, 42.263924 -90.417984, 42.340633 -90.427681, 42.360073 -90.441597," +
                " 42.38878299999999 -90.491043, 42.42183700000001 -90.563583, 42.46055999999999 -90.605827, 42.47564299999999 -90.648346, " +
                "42.494698 -90.651772, 42.50936100000001 -90.638329, 42.508362000000005 -90.419975, 42.504108 -89.923569, " +
                "42.50345999999999 -89.834618, 42.49749 -89.400497, 42.497906 -89.359444, 42.49086399999999 -88.939079, " +
                "42.490905999999995 -88.764954, 42.489655 -88.70652, 42.49197000000001 -88.297897, 42.48961299999999 -88.194702," +
                " 42.48913200000001 -87.79731, 42.314212999999995 -87.836945, 42.15645599999999 -87.760239, 42.059822 -87.670547," +
                " 41.847331999999994 -87.612625, 41.723591 -87.529861, 41.46971500000001 -87.532646, 41.30130399999999 -87.532448, " +
                "41.173756 -87.531731, 41.00993 -87.532021, 40.74541099999999 -87.532669, 40.494609999999994 -87.53717, " +
                "40.48324600000001 -87.535675, 40.16619499999999 -87.535339, 39.887302000000005 -87.535774, 39.609341 -87.535576, " +
                "39.47744800000001 -87.538567, 39.350525000000005 -87.540215, 39.338268 -87.597664, 39.30740399999999 -87.625237," +
                " 39.297661000000005 -87.610619, 39.281418 -87.615799, 39.258162999999996 -87.606895, 39.248752999999994 -87.584564," +
                " 39.20846599999999 -87.588593, 39.198128 -87.594208, 39.196068 -87.607925, 39.168507000000005 -87.644257," +
                " 39.146679000000006 -87.670326, 39.130652999999995 -87.659454, 39.11346800000001 -87.662262, 39.10394299999999 -87.631668, " +
                "39.08897400000001 -87.630867, 39.08460600000001 -87.612007, 39.062434999999994 -87.58532, 38.995743000000004 -87.581749," +
                " 38.99408299999999 -87.591858, 38.97707700000001 -87.547905, 38.96370300000001 -87.53347, 38.93191899999999 -87.530182," +
                " 38.90486100000001 -87.5392, 38.869811999999996 -87.559059, 38.857890999999995 -87.550507, 38.795559 -87.507889, " +
                "38.77669900000001 -87.519028, 38.769722 -87.508003, 38.73663300000001 -87.508316, 38.68597399999999 -87.543892, " +
                "38.672169 -87.588478, 38.642810999999995 -87.625191, 38.622917 -87.628647, 38.599209 -87.619827, 38.593177999999995 -87.640594, " +
                "38.573871999999994 -87.652855, 38.54742400000001 -87.672943, 38.51536899999999 -87.65139, 38.50044299999999 -87.653534," +
                " 38.50400500000001 -87.679909, 38.48153300000001 -87.692818, 38.466125000000005 -87.756096, 38.45709600000001 -87.758659," +
                " 38.44548 -87.738953, 38.41796500000001 -87.748428, 38.378124000000014 -87.784019, 38.35252399999999 -87.834503, " +
                "38.28609800000001 -87.850082, 38.28536199999999 -87.863007, 38.316788 -87.874039, 38.315552 -87.883446, " +
                "38.300658999999996 -87.888466, 38.281048 -87.914108, 38.302345 -87.913651, 38.30477099999999 -87.925919, " +
                "38.241085 -87.980019, 38.234814 -87.986008, 38.200714000000005 -87.977928, 38.171131 -87.932289," +
                " 38.15752800000001 -87.931992, 38.13691299999999 -87.950569, 38.131760000000014 -87.973503, 38.10330200000001 -88.018547," +
                " 38.09234599999999 -88.012329, 38.09674799999999 -87.964867, 38.073307 -87.975296, 38.054084999999986 -88.034729," +
                " 38.04512 -88.043091, 38.03830300000001 -88.041473, 38.03353100000001 -88.021698, 38.00823600000001 -88.029213, " +
                "37.975055999999995 -88.021706, 37.956264000000004 -88.042511, 37.934498000000005 -88.041771, 37.929783 -88.064621," +
                " 37.944 -88.078941, 37.92366 -88.084, 37.917591 -88.030441, 37.905758000000006 -88.026588, 37.896004000000005 -88.044868," +
                " 37.90617 -88.100082, 37.895306000000005 -88.101456, 37.867808999999994 -88.075737, 37.843745999999996 -88.034241, " +
                "37.827522 -88.042137, 37.831249 -88.089264, 37.817612 -88.086029, 37.805683 -88.035576, 37.735400999999996 -88.072472, " +
                "37.700745 -88.133636, 37.660686 -88.15937, 37.628479 -88.157631, 37.583572000000004 -88.134171, 37.51099000000001 -88.071564)))");
        List values = Arrays.asList(att, geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void d_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(UPDATE);
        when(request.getFID()).thenReturn("fid_test_1");
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(3)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTIPOINT ((10 40), (40 30), (20 20), (30 10))");
        values.add(geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void e_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(INSERT);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(4)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)),((20 35, 45 20, 30 5, 10 10, 10 30, 20 35)), ((0 0, 100 0, 100 100, 0 100, 0 0)))");
        values.add(geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void g_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(INSERT);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(50)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("LINEARRING (0 0, 1 0, 1 1, 0 1, 0 0)");
        values.add(geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void h_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(UPDATE);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(30)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTIPOINT ((10 40), (40 30), (20 20), (30 10))");
        values.add(geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void i_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(UPDATE);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(15)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTILINESTRING Z((146.468582 -41.241478 -41.241478, 146.574768 -41.251186 -41.251186, " +
                "146.640411 -41.255154 -41.255154, 146.766129 -41.332348 -41.332348, 146.794189 -41.34417 -41.34417," +
                " 146.822174 -41.362988 -41.362988, 146.863434 -41.380234 -41.380234, 146.899521 -41.379452 -41.379452," +
                " 146.929504 -41.378227 -41.378227, 147.008041 -41.356079 -41.356079, 147.098343 -41.362919 -41.362919))");
        values.add(geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void l_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(UPDATE);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(45)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void m_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(UPDATE);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(65)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    @Test
    public void n_featureStreamWriterTest() throws Exception {
        WFSTransactionRequestV110 request = mock(WFSTransactionRequestV110.class);
        when(request.getOperation()).thenReturn(INSERT);
        when(request.getTypeName()).thenReturn(TASMANIA_ROADS);
        List values = iterate(0, n -> n + 1)
                .limit(28)
                .boxed()
                .map(FeatureStreamWriterTest::toAttributeDTO)
                .collect(toList());
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setSrid(valueOf(4326));
        geometry.setValue("MULTILINESTRING ((-91.1716151689462 46.8441439939094, -91.1709825689471 46.8442585272425, " +
                "-91.1703163689482 46.8442359272426, -91.1680853689517 46.8445793939087), " +
                "(-91.1727139689444 46.843937927243, -91.1716151689462 46.8441439939094))");
        values.add(geometry);
        when(request.getAttributes()).thenReturn(values);
        Writer writer = new StringWriter();
        featureStreamWriter.write(request, writer);
        logger.info("#######################\n{}\n", writer.toString());
    }

    /**
     * @param value
     * @return {@link AttributeDTO}
     */
    static AttributeDTO toAttributeDTO(int value) {
        return new AttributeDTO() {
            {
                super.setName("NAME_".concat(String.valueOf(value)));
                super.setValue("VALUE_".concat(String.valueOf(value)));
            }
        };
    }
}