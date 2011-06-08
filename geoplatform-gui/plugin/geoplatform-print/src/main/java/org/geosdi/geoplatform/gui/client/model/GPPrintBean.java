/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model;


import java.io.Serializable;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.control.Scale;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class GPPrintBean implements Serializable {

    private PrintTemplate template;
    private String title;
    private LonLat center;
    private double scale;
    private String mapTitle;
    private String comments;
    private ImageFormat imageFormat;
    private DPI dpi;
    
    /**
	 * @return the template
	 */
	public PrintTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(PrintTemplate template) {
		this.template = template;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the center
	 */
	public LonLat getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(LonLat center) {
		this.center = center;
	}

	/**
	 * @return the scale
	 */
	public double getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}

	/**
	 * @return the mapTitle
	 */
	public String getMapTitle() {
		return mapTitle;
	}

	/**
	 * @param mapTitle
	 *            the mapTitle to set
	 */
	public void setMapTitle(String mapTitle) {
		this.mapTitle = mapTitle;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the imageFormat
	 */
	public ImageFormat getImageFormat() {
		return imageFormat;
	}

	/**
	 * @param imageFormat
	 *            the imageFormat to set
	 */
	public void setImageFormat(ImageFormat imageFormat) {
		this.imageFormat = imageFormat;
	}

	/**
	 * @return the dpi
	 */
	public DPI getDpi() {
		return dpi;
	}

	/**
	 * @param dpi
	 *            the dpi to set
	 */
	public void setDpi(DPI dpi) {
		this.dpi = dpi;
	}

	/**
	 * 
	 * @param lon
	 * @param lat
	 * @param scale
	 */
	public void setValues(String lon, String lat, String scale) {
		this.center = new LonLat(Double.parseDouble(lon),
				Double.parseDouble(lat));

		double scaleDouble = determinateScale(scale);

//		setScale(Scale.searchValue(scaleDouble));
	}

	private double determinateScale(String scale) {
		Double scaleDouble;

		if (scale.contains("K")) {
			int index = scale.indexOf("K");
			scale = scale.substring(0, index);
			scaleDouble = Double.parseDouble(scale);
			scaleDouble = scaleDouble * 1000;
		} else if (scale.contains("M")) {
			int index = scale.indexOf("M");
			scale = scale.substring(0, index);
			scaleDouble = Double.parseDouble(scale);
			scaleDouble = scaleDouble * 1000000;
		} else {
			scaleDouble = Double.parseDouble(scale);
		}
		return scaleDouble.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PrintEraBean [center=" + center + ", comments=" + comments
				+ ", dpi=" + dpi + ", imageFormat=" + imageFormat
				+ ", mapTitle=" + mapTitle + ", scale=" + scale + ", template="
				+ template + ", title=" + title + "]";
	}

//	public String buildLayers() {
//		Iterator<Entry<String, List<StratoProgetto>>> it = PrintingLayers
//				.getInstance().iterator();
//
//		String layers = "";
//		while (it.hasNext()) {
//			Entry<String, List<StratoProgetto>> entry = it.next();
//			Collections.sort(entry.getValue());
//			layers = layers.concat(buildLayersList(entry.getValue(),
//					entry.getKey()));
//		}
//
//		return layers.substring(0, layers.length() - 1);
//	}
//
//	public String buildOrderLayers() {
//		Iterator<Entry<String, List<StratoProgetto>>> it = PrintingLayers
//				.getInstance().iterator();
//
//		String layers = "";
//		List<StratoProgetto> printLayers = new ArrayList<StratoProgetto>();
//		while (it.hasNext()) {
//			Entry<String, List<StratoProgetto>> entry = it.next();
//			printLayers.addAll(entry.getValue());
//		}
//
//		Collections.sort(printLayers, Collections.reverseOrder());
//
//		for (IMapCommand layer : printLayers) {
//			layers = layers.concat(buildLayersOrderList(layer));
//		}
//
//		return layers.substring(0, layers.length() - 1);
//	}
//
//	public String buildLayersOrderList(IMapCommand layer) {
//		String start = "{\"layers\":[";
//		String apice = "\"";
//		String layerList = "";
//		String baseURL = "],\"baseURL\":\"" + layer.getURLSERVER();
//		String format = "\",\"format\":\"" + imageFormat.getValue();
//		String end = "\",\"type\":\"WMS\"},";
//		layerList = layerList.concat(start);
//		layerList = layerList
//				.concat(apice + layer.getNomeLayer() + apice + ",");
//		layerList = layerList.substring(0, layerList.length() - 1);
//
//		return layerList.concat(baseURL + format + end);
//	}
//
//	public String buildLayersList(List<StratoProgetto> layers, String urlServer) {
//		String start = "{\"layers\":[";
//		String apice = "\"";
//		String layerList = "";
//		String baseURL = "],\"baseURL\":\"" + urlServer;
//		String format = "\",\"format\":\"" + imageFormat.getValue();
//		String end = "\",\"type\":\"WMS\"},";
//		layerList = layerList.concat(start);
//		for (IMapCommand layer : layers) {
//			layerList = layerList.concat(apice + layer.getNomeLayer() + apice
//					+ ",");
//		}
//		layerList = layerList.substring(0, layerList.length() - 1);
//
//		return layerList.concat(baseURL + format + end);
//	}
//
//	public String toStringJSON() {
//		return "{\"title\":\"" + title + "\",\"pages\":[{\"center\":["
//				+ center.getLongitude() + "," + center.getLatitude()
//				+ "],\"scale\":" + String.valueOf(scale)
//				+ ",\"rotation\":0,\"mapTitle\":\"" + mapTitle
//				+ "\",\"comment\":\"" + comments + "\"}],\"layers\":["
//				+ buildOrderLayers()
//				+ "],\"layout\":\"A3 portrait\",\"srs\":\"EPSG:4326\",\"dpi\":"
//				+ dpi.getDpi() + ",\"units\":\"degrees\"}";
//	}
}
