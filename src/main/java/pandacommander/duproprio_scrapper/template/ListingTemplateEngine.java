package pandacommander.duproprio_scrapper.template;

import java.util.List;

import pandacommander.duproprio_scrapper.parsing.RichListing;

public interface ListingTemplateEngine {

	void processListing(List<RichListing> listings, String outputPath);

}