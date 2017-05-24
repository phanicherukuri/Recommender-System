package com.patternrecognition.rest;

public class OutputFormat {
	private long item;
	private float value;
	public long getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public OutputFormat(long item, float value) {
		this.item = item;
		this.value = value;
	}
	
	
}
