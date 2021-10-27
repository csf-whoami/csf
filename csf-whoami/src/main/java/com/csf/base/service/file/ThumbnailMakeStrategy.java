package com.csf.base.service.file;

import java.io.File;

public interface ThumbnailMakeStrategy {

	public void make(File orgFile, ImageInfo imageInfo) throws Exception;

	public static class ImageInfo {

		private int width;
		private int height;
		private double scale;

		public ImageInfo(int width, int height, double scale) {
			this.width = width;
			this.height = height;
			this.scale = scale;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public double getScale() {
			return scale;
		}

		public void setScale(double scale) {
			this.scale = scale;
		}

	}
}
