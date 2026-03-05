package com.dws.bands.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Band {

	@Schema(description = "Band ID", example = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
	@Pattern(regexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$", message = "The value must be in the following format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
	private String id;

	@Schema(description = "Band name", example = "Metallica")
	private String name;

	@Schema(description = "Band image URL", example = "https://example.com/images/metallica.jpg")
	private String image;

	@Schema(description = "Band's musical genre", example = "Heavy Metal")
	private String genre;

	@Schema(description = "Bands biography", example = "Heavy Metal band founded in 1981")
	private String biography;

	@Schema(description = "Band reproduction times", example = "123456")
	private Long numPlays;

	@Schema(description = "Album ID list", example = "[\"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\", \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\"]")
	private List<String> albums;
}
