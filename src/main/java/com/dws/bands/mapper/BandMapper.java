package com.dws.bands.mapper;

import com.dws.bands.model.Band;
import com.dws.bands.model.dto.BandDto;

public class BandMapper {

    public static Band dtoToBand(BandDto bandDto) {
        Band band = new Band();
        band.setGenre(bandDto.getGenre());
        band.setId(bandDto.getId());
        band.setAlbums(bandDto.getAlbums());
        band.setBiography(bandDto.getBiography());
        band.setImage(bandDto.getImage());
        band.setName(bandDto.getName());
        band.setNumPlays(bandDto.getNumPlays());

        return band;
    }
}
