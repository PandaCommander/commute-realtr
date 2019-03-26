package pandacommander.commute_realtr.parsing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.configuration2.Configuration;

import pandacommander.commute_realtr.geocoding.AddressCoordinates;
import pandacommander.commute_realtr.geocoding.GeoCoder;
import pandacommander.commute_realtr.listing.Listing;
import pandacommander.commute_realtr.listing.RichListing;

public class DefaultListingParser extends ListingParser {

	public DefaultListingParser(Configuration options, GeoCoder geoCoder) {
		super(options, geoCoder);
	}

	@Override
	public List<RichListing> getParsedListings(List<Listing> listings) {
		List<RichListing> parsedListings = new ArrayList<RichListing>();
		List<MetroStation> metros = getMetroStations();
		Configuration options = getOptions();
		GeoCoder geoCoder = getGeoCoder();

		String commuteAddress = options.getString("commute.address");
		int maxCommute = options.getInt("commute.time.max", 0);
		int maxWalk = options.getInt("metro.closest.walk.time.max", 0);

		AddressCoordinates destinationAddress = geoCoder.getAddressCoordinates(commuteAddress);

		for (Listing listing : listings) {

			int commuteTimeSeconds = geoCoder.getTransitTimeSeconds(listing.getAddressCoordinates(),
					destinationAddress);

			for (MetroStation metroStation : metros) {
				double distanceInKm = geoCoder.getDistanceInKmBetweenEarthCoordinates(listing.getAddressCoordinates(),
						metroStation.getCoords());
				metroStation.setDistanceFromOriginKm(distanceInKm);
			}
			Collections.sort(metros);

			MetroStation closestMetro = metros.get(0);
			int metroWalkTimeSeconds = geoCoder.getWalkTimeSeconds(listing.getAddressCoordinates(),
					closestMetro.getCoords());

			int commuteMinutes = commuteTimeSeconds / 60;
			int walkingMinutes = metroWalkTimeSeconds / 60;

			if ((maxCommute == 0 || maxCommute > commuteMinutes) && (maxWalk == 0 || maxWalk > walkingMinutes)) {
				parsedListings.add(new RichListing(commuteMinutes, closestMetro.getName(), walkingMinutes, listing));
			}
		}
		return parsedListings;
	}

	private List<MetroStation> getMetroStations() {
		Configuration options = getOptions();
		List<String> stationNames = options.getList(String.class, "metro.stations");
		List<MetroStation> metroStations = new ArrayList<MetroStation>();
		GeoCoder geoCoder = getGeoCoder();

		for (String name : stationNames) {
			AddressCoordinates coords = geoCoder
					.getAddressCoordinates(name + " station, " + options.getString("metro.stations.city"));
			metroStations.add(new MetroStation(name, coords));
		}

		return metroStations;
	}

}
