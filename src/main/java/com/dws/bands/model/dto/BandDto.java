package com.dws.bands.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandDto {

    private String id;
    private String name;
    private String image;
    private String genre;
    private String biography;
    private Long numPlays;
    private List<String> albums;
}
