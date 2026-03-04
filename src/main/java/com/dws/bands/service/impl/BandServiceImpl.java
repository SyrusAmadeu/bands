package com.dws.bands.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dws.bands.client.BandsApiClient;
import com.dws.bands.exception.BandNotFoundException;
import com.dws.bands.mapper.BandMapper;
import com.dws.bands.model.Band;
import com.dws.bands.model.dto.BandDto;
import com.dws.bands.service.IBandService;

@Service
public class BandServiceImpl implements IBandService {

	private static final Logger log = LoggerFactory.getLogger(BandServiceImpl.class);
	
	private BandsApiClient bandsApiClient;
	
	public BandServiceImpl(BandsApiClient bandsApiClient) {
		this.bandsApiClient = bandsApiClient;
	}

    @Override
	@Cacheable(value = "bands", unless = "#result == null || #result.isEmpty()")
    public List<Band> getAllBands() {
    	log.info(">> getAllBands() :: Fetching all bands.");
        return bandsApiClient.fetchBands()
                .stream()
                .map(BandMapper::dtoToBand)
                .toList();
    }


    @Override
	@Cacheable(value = "band", keyGenerator = "customKeyGen")
    public Band getBand(String bandId) {
    	log.info(">> getBand() :: Fetching band with ID: {}.", bandId);
    	BandDto band = bandsApiClient.fetchBand(bandId);
        if( band == null) {
        	throw new BandNotFoundException(String.format("Band Not Found. Band ID: %s",bandId));
        }
		
		return BandMapper.dtoToBand(band);
    }

    @Override
    @CacheEvict(value = {"bands", "band"}, allEntries = true)
    public void refreshCache() {
    	log.info(">> refreshCache() :: Refrshing bands cache.");
    }
    
    
}
