package com.dws.bands.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dws.bands.client.BandsApiClient;
import com.dws.bands.exception.BandNotFoundException;
import com.dws.bands.model.Band;
import com.dws.bands.model.dto.BandDto;
import com.dws.bands.service.impl.BandServiceImpl;

public class BandServiceImplTest {
	private BandsApiClient bandsApiClient;
	private IBandService bandService;

	@BeforeEach
	void setUp() {
		bandsApiClient = mock(BandsApiClient.class);
		bandService = new BandServiceImpl(bandsApiClient);
	}

	@Test
	void getAllBands_returnsMappedBands() {
		BandDto dto1 = new BandDto();
		dto1.setId("1");
		dto1.setName("Band A");

		when(bandsApiClient.fetchBands()).thenReturn(List.of(dto1));

		List<Band> bands = bandService.getAllBands();

		assertEquals(1, bands.size());
		assertEquals("Band A", bands.get(0).getName());

		verify(bandsApiClient, times(1)).fetchBands();
	}

	@Test
	void getBand_existingId_returnsBand() {
		BandDto dto1 = new BandDto();
		dto1.setId("1");
		dto1.setName("Band A");

		when(bandsApiClient.fetchBand("1")).thenReturn(dto1);

		Band band = bandService.getBand("1");

		assertNotNull(band);
		assertEquals("Band A", band.getName());
	}

	@Test
	void getBand_nonExistingId_throwsNotFound() {
		when(bandsApiClient.fetchBands()).thenReturn(List.of());

		assertThrows(BandNotFoundException.class, () -> bandService.getBand("999"));
	}

}
