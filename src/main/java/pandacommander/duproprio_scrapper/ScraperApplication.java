package pandacommander.duproprio_scrapper;

import java.util.List;

import pandacommander.duproprio_scrapper.parsing.ListingParser;
import pandacommander.duproprio_scrapper.parsing.RichListing;
import pandacommander.duproprio_scrapper.scraping.Crawler;

public class ScraperApplication {

	private Crawler crawler;
	private ListingParser parser;

	public ScraperApplication(Crawler scraper, ListingParser parser) {
		super();
		this.crawler = scraper;
		this.parser = parser;
	}

	public void scrap() {
		List<RichListing> listings = parser.parseListings(crawler.getListings());
		for (RichListing richListing : listings) {
			System.out.println(richListing.toString());
		}
	}

}
