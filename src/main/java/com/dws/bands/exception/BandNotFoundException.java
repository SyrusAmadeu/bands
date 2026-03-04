package com.dws.bands.exception;

public class BandNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6335567552258481619L;

	public BandNotFoundException(String message) {
		super(message);
	}
}
