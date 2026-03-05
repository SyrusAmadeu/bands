package com.dws.bands.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dws.bands.model.Band;
import com.dws.bands.service.IBandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/bands")
@Validated
@AllArgsConstructor
public class BandController {

	private final IBandService iBandService;

	@Operation(summary = "List all bands", description = "Returns all bands available.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List successfully returned"),
        @ApiResponse(responseCode = "502", description = "External API Bad Gateway", 
		content = @Content(mediaType = "application/json",
    		schema = @Schema(type = "string", example = "Error 502: Bad Gateway")))
    })
	@GetMapping
	public ResponseEntity<List<Band>> getAllBands(@RequestParam(defaultValue = "") String sort,
	        @RequestParam(defaultValue = "asc") String direction) {
		List<Band> bands = iBandService.getAllBands();
		
		return ResponseEntity.status(HttpStatus.OK).body(bands);
	}
	
	@Operation(summary = "List all bands", description = "Returns all bands available.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List successfully returned"),
        @ApiResponse(responseCode = "502", description = "External API Bad Gateway", 
		content = @Content(mediaType = "application/json",
    		schema = @Schema(type = "string", example = "Error 502: Bad Gateway")))
    })
	@GetMapping("/popularity")
	public ResponseEntity<List<Band>> getAllBandsByPopularity(
	        @RequestParam(defaultValue = "asc") String direction) {
		List<Band> bands = iBandService.getAllBands("numPlays", direction);
		
		return ResponseEntity.status(HttpStatus.OK).body(bands);
	}
	
	@Operation(summary = "List all bands", description = "Returns all bands available.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List successfully returned"),
        @ApiResponse(responseCode = "502", description = "External API Bad Gateway", 
		content = @Content(mediaType = "application/json",
    		schema = @Schema(type = "string", example = "Error 502: Bad Gateway")))
    })
	@GetMapping("/alphabetic-order")
	public ResponseEntity<List<Band>> getAllBandsByAlphabeticOrder(
	        @RequestParam(defaultValue = "asc") String direction) {
		List<Band> bands = iBandService.getAllBands("name", direction);
		
		return ResponseEntity.status(HttpStatus.OK).body(bands);
	}

	@Operation(summary = "Search a band by ID", description = "Returns band data")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Band Found"),
	        @ApiResponse(responseCode = "400", description = "Error 400: Bad Request: Incorrect ID format",
	        		content = @Content(mediaType = "application/json", 
	        			schema = @Schema(type = "string", example = "The given id must be in the following format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"))),
	        @ApiResponse(responseCode = "404", description = "Error 404: Band Not Found", 
	        		content = @Content(mediaType = "application/json",
                    	schema = @Schema(type = "string", example = "Band Not Found"))),
	        @ApiResponse(responseCode = "502", description = "External API Bad Gateway", 
	        		content = @Content(mediaType = "application/json",
                		schema = @Schema(type = "string", example = "Error 502: Bad Gateway")))
	    })
	@GetMapping("/{bandId}")
	public ResponseEntity<Band> getBand(
            @PathVariable
            @Pattern(regexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$",
					message = "The given id must be in the following format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
            String bandId) {
		Band band = iBandService.getBand(bandId);
		return ResponseEntity.status(HttpStatus.OK).body(band);
	}
	
	@Operation(summary = "Clean bands cache", description = "Clean all bands cache")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "204", description = "Cache cleaned")
	    })
	@DeleteMapping("/cache")
	public ResponseEntity<Void> clearCache() {
		iBandService.refreshCache();
	    return ResponseEntity.noContent().build();
	}
}
