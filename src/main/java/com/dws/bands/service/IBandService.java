package com.dws.bands.service;

import java.util.List;

import com.dws.bands.model.Band;

public interface IBandService {

	/**
	 * List all bands sorted from Band Client API
	 * @return a list of bands
	 */
	public List<Band> getAllBands();
	
	/**
	 * List all bands from Band Client API
	 * @param sort Sort field
	 * @param direction Sort order
	 * @return a sorted list of bands
	 */
	public List<Band> getAllBands(String sort, String direction);

	/**
	 * Fetch a band with a given ID
	 * 
	 * @param bandId Band ID
	 * @return Band Object
	 */
	public Band getBand(String bandId);
	
	/**
	 * Refresh Bands cache
	 */
	public void refreshCache();

}
