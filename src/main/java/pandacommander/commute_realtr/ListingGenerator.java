package pandacommander.commute_realtr;

import java.text.SimpleDateFormat;
import java.util.List;

import pandacommander.commute_realtr.listing.RichListing;
import pandacommander.commute_realtr.parsing.ListingParser;
import pandacommander.commute_realtr.scraping.Scraper;
import pandacommander.commute_realtr.template.ListingTemplateEngine;

public class ListingGenerator {

	private Scraper crawler;
	private ListingParser parser;
	private ListingTemplateEngine templateEngine;

	public ListingGenerator(Scraper scraper, ListingParser parser, ListingTemplateEngine templateEngine) {
		super();
		this.crawler = scraper;
		this.parser = parser;
		this.templateEngine = templateEngine;
	}

	public void GenerateListings() {
		List<RichListing> listings = parser.getParsedListings(crawler.getListings());
		for (RichListing richListing : listings) {
			System.out.println(richListing.toString());
		}
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new java.util.Date());
		templateEngine.processListing(listings, "listings-" + timeStamp + ".html");

	}

}
