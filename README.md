DWS – Backend Challenge: Bands API

This is the test project for the DWS – Dentsu World Services recruitment process. The application is a Spring Boot backend that consumes data from an external bands API, exposes REST endpoints, and applies caching for performance optimization.

---

Technologies Used

- Java 21
- Spring Boot 4
- Spring Web – for building REST API
- Caffeine – high-performance in-memory caching
- Springdoc OpenAPI / Swagger – API documentation
- Maven – dependency management and build
- JUnit 5 + Mockito – unit testing

---

Endpoints

1. Get All Bands

GET /api/bands

- Returns all bands fetched from the external API.
- Example response:

[
    {
        "id": "bc710bcf-8815-42cf-bad2-3f1d12246aeb",
        "name": "Nickelback",
        "image": "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
        "genre": "rock",
        "biography": "Nickelback is a rock band which formed in Hanna, Alberta, Canada in 1995. Nickelback is one of the most commercially successful Canadian groups, having sold 30 million records worldwide. Nickelback ranks as the 11th best selling music act of the 2000s, and places as the 2nd best selling foreign act in the US behind The Beatles for the 2000s.\n\nThe band consists of Chad Kroeger (vocals, guitar), Ryan Peake (guitar, vocals), Mike Kroeger (bass) and Daniel Adair (drums). <a href=\"https://www.last.fm/music/Nickelback\">Read more on Last.fm</a>",
        "numPlays": 17605491,
        "albums": [
            "3c5794a0-d913-390d-ab24-6762af38c112",
            "9599042a-34a9-39f2-a52d-c903a382515c",
            "b3fad414-9b92-4d04-b703-9605d5d33bac"
        ]
    },
    {
		"id": "e2c00c56-8365-4160-9f40-a64682917633",
		"name": "Goo Goo Dolls",
		"image": "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
		"genre": "rock",
		"biography": "Goo Goo Dolls is an alternative rock band formed in 1985 in Buffalo, New York, USA by guitarist/vocalist John Rzeznik and bassist/vocalist Robby Takac initially under the name the Sex Maggots (the new name was chosen from an ad in True Detective magazine at the behest of a local club owner). They are most famous for the hit \"Iris\", featured on the soundtrack to the 1998 film City of Angels. Currently, the band consists of John Rzeznik (guitar, vocals) and Robby Takac (vocals, bass). <a href=\"https://www.last.fm/music/Goo+Goo+Dolls\">Read more on Last.fm</a>",
		"numPlays": 3057200,
		"albums": [
			"31e609d2-49aa-4b71-ad6e-57c0b0f54161"
		]
	}
]

2. Get Band by ID

GET /api/bands/{id}

- Returns a specific band by its ID.
- Example of valid response:

{
    "id": "e2c00c56-8365-4160-9f40-a64682917633",
    "name": "Goo Goo Dolls",
    "image": "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
    "genre": "rock",
    "biography": "Goo Goo Dolls is an alternative rock band formed in 1985 in Buffalo, New York, USA by guitarist/vocalist John Rzeznik and bassist/vocalist Robby Takac initially under the name the Sex Maggots (the new name was chosen from an ad in True Detective magazine at the behest of a local club owner). They are most famous for the hit \"Iris\", featured on the soundtrack to the 1998 film City of Angels. Currently, the band consists of John Rzeznik (guitar, vocals) and Robby Takac (vocals, bass). <a href=\"https://www.last.fm/music/Goo+Goo+Dolls\">Read more on Last.fm</a>",
    "numPlays": 3057200,
    "albums": [
        "31e609d2-49aa-4b71-ad6e-57c0b0f54161"
    ]
}

- Example of error response (ID not found):

"Band not found"

- Example of error response (Invalid ID):

"The given id must be in the following format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"

- Example of Bad Gateway

"Error 502: Bad Gateway"

---

Caching

Caching is implemented with Caffeine to ensure repeated calls to the external API are served quickly without overloading it.

- Cache configured to expire entries after 5 minutes.
- Applied to both the list and individual band endpoints.

---

API Documentation

Swagger documentation is available at:

http://localhost:8080/swagger-ui/index.html/

It includes success and error response examples, including invalid ID and band not found messages.

---

Setup and Running

1. Clone the repository:

git clone https://github.com/SyrusAmadeu/bands
cd bands

2. Build and run:

mvn clean install
mvn spring-boot:run

3. Access endpoints via Postman, curl, or browser:

GET http://localhost:8080/api/bands
GET http://localhost:8080/api/bands/{id}

---

Tests

Unit tests cover:

- Fetching all bands
- Fetching an existing band
- Fetching a non-existing band (throws BandNotFoundException)

Run tests:

mvn test

---

Notes

- Modular and easily scalable application.
- Focused on maintainability and performance, with Caffeine caching. High possibility to migrate to Redis
- Complete Swagger documentation including error examples.
- All endpoints work without authentication.

---

Contact

Project developed by Syrus Amadeu as part of the DWS – Dentsu World Services recruitment process.