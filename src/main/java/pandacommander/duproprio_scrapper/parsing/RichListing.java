package pandacommander.duproprio_scrapper.parsing;

import pandacommander.duproprio_scrapper.Listing;
import pandacommander.duproprio_scrapper.geocoding.AddressCoordinates;

public class RichListing {

	private int minuteToCommute;
	private String closestMetro;
	private int walkMinuteToMetro;
	private Listing listing;

	public RichListing(int minuteToCommute, String closestMetro, int walkMinuteToMetro, Listing listing) {
		super();
		this.minuteToCommute = minuteToCommute;
		this.closestMetro = closestMetro;
		this.walkMinuteToMetro = walkMinuteToMetro;
		this.listing = listing;
	}

	public int getMinuteToCommute() {
		return minuteToCommute;
	}

	public String getClosestMetro() {
		return closestMetro;
	}

	public int getWalkMinuteToMetro() {
		return walkMinuteToMetro;
	}

	public String getId() {
		return listing.getId();
	}

	public String getLongitude() {
		return listing.getLongitude();
	}

	public String getLatitude() {
		return listing.getLatitude();
	}

	public String getPrice() {
		return listing.getPrice();
	}

	public String getAddress() {
		return listing.getAddress();
	}

	public String getCity() {
		return listing.getCity();
	}

	public String getDescription() {
		return listing.getDescription();
	}

	public String getBedrooms() {
		return listing.getBedrooms();
	}

	public String getBathrooms() {
		return listing.getBathrooms();
	}

	public String getBuildingDimensions() {
		return listing.getBuildingDimensions();
	}

	public String getLotDimensions() {
		return listing.getLotDimensions();
	}

	public String getImageUrl() {
		return listing.getImageUrl();
	}
	
	public String getListingUrl() {
		return listing.getListingUrl();
	}

	public AddressCoordinates getAddressCoordinates() {
		return listing.getAddressCoordinates();
	}
	
	public String getAttributes() {
		String attributes = "";
		if (getBedrooms() != null && !getBedrooms().isEmpty()) {
			attributes += "Bedrooms: "+getBedrooms()+" ";
		}
		if (getBathrooms() != null && !getBathrooms().isEmpty()) {
			attributes += "Bathrooms: "+getBathrooms()+" ";
		}
		if (getBuildingDimensions() != null && !getBuildingDimensions().isEmpty()) {
			attributes += "Space: "+getBuildingDimensions()+" ";
		}
		if (getLotDimensions() != null && !getLotDimensions().isEmpty()) {
			attributes += "Lot: "+getLotDimensions()+" ";
		}
		return attributes;
	}

	@Override
	public String toString() {
		return "RichListing [minuteToCommute=" + minuteToCommute + ", closestMetro=" + closestMetro
				+ ", walkMinuteToMetro=" + walkMinuteToMetro + ", " + listing + "]";
	}

}
