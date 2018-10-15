package pandacommander.duproprio_scrapper;

import java.util.List;

import pandacommander.duproprio_scrapper.crawler.Crawler;
import pandacommander.duproprio_scrapper.parsing.ListingParser;
import pandacommander.duproprio_scrapper.parsing.RichListing;
import pandacommander.duproprio_scrapper.template.ListingTemplateEngine;

public class ScraperApplication {

	private Crawler crawler;
	private ListingParser parser;
	private ListingTemplateEngine templateEngine;

	public ScraperApplication(Crawler scraper, ListingParser parser, ListingTemplateEngine templateEngine) {
		super();
		this.crawler = scraper;
		this.parser = parser;
		this.templateEngine = templateEngine;
	}

	public void scrap() {
		List<RichListing> listings = parser.getParsedListings(crawler.getListings());
		for (RichListing richListing : listings) {
			System.out.println(richListing.toString());
		}
		templateEngine.processListing(listings, "listings.html");
	}

}
