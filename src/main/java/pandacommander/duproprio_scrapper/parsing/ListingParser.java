package pandacommander.duproprio_scrapper.parsing;

import java.util.List;

import org.apache.commons.configuration2.Configuration;

import pandacommander.duproprio_scrapper.Listing;
import pandacommander.duproprio_scrapper.geocoding.GeoCoder;

public abstract class ListingParser {
	private Configuration options;
	private GeoCoder GeoCoder;

	public ListingParser(Configuration options, GeoCoder geoCoder) {
		super();
		this.options = options;
		GeoCoder = geoCoder;
	}
	
	public abstract List<RichListing> getParsedListings(List<Listing> listins);

	protected Configuration getOptions() {
		return options;
	}

	protected GeoCoder getGeoCoder() {
		return GeoCoder;
	}

}
