package org.geosdi.geoplatform.gui.client;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class SingleComponent {

	Label label;
	Label labelTextNews;
	Image image;
	Image icon;
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Image getIcon() {
		this.icon.setPixelSize(50, 50);
		return this.icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		this.image.setPixelSize(400, 200);
		icon = new Image(image.getUrl());
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Label getLabelTextNews() {
		return labelTextNews;
	}

	public void setLabelTextNews(Label labelTextNews) {
		this.labelTextNews = labelTextNews;
	}
}
