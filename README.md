# commute-realtr

Simple duproprio.com scraper to get listing by commute time and distance from metro stations.

## Setup

Clone projet
Copy content of 'properties' into root
Add google map key to secret.properties

### Requirements
- Java 8+
- Installed [chromedriver](https://sites.google.com/a/chromium.org/chromedriver/) and added to PATH
- Maven

## TODO

- [x] Scrap
- [x] Load options from file
- [x] Google maps api Integration
- [x] Compute distance to nearest metro station
- [x] Compute commute time 
- [x] Save report to ~~PDF~~ HTML
- [ ] Implement Bing map api integration (More free api calls)
- [ ] Logging and error handling
- [ ] Add multiple scraping source support
- [ ] Scrap Centris.ca
- [ ] Scrap kijiji.ca
- [ ] Add rental scraping
- [x] Add multi-threading
- [ ] Database to store listings 

## Stretch
- [ ] Service server
- [ ] React Front-end
- [ ] Deploy
