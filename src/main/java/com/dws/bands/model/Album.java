package com.dws.bands.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private String id;
    private String name;
    private LocalDateTime releasedDate;
    private Band band;
}
