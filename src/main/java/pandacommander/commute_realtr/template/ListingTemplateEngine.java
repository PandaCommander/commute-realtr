package pandacommander.commute_realtr.template;

import java.util.List;

import pandacommander.commute_realtr.listing.RichListing;

public interface ListingTemplateEngine {

	void processListing(List<RichListing> listings, String outputPath);

}