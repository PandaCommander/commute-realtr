package pandacommander.commute_realtr.parsing;

import java.util.List;
import org.apache.commons.configuration2.Configuration;

import pandacommander.commute_realtr.geocoding.GeoCoder;
import pandacommander.commute_realtr.listing.Listing;
import pandacommander.commute_realtr.listing.RichListing;

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
