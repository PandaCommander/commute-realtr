package pandacommander.duproprio_scrapper.geocoding;

import org.apache.commons.configuration2.Configuration;

public abstract class GeoCoder {

	protected Configuration secrets;
	protected Configuration options;

	public GeoCoder(Configuration secrets, Configuration options) {
		super();
		this.secrets = secrets;
		this.options = options;
	}

	public abstract AddressCoordinates getAddressCoordinates(String address);

	public abstract int getTransitTimeSeconds(AddressCoordinates origin, AddressCoordinates destination);

	public abstract int getWalkTimeSeconds(AddressCoordinates origin, AddressCoordinates destination);
	
	// haversine formula from http://www.movable-type.co.uk/scripts/latlong.html
	public double getDistanceInKmBetweenEarthCoordinates(AddressCoordinates origin, AddressCoordinates destination) {
		double lat1 = Float.valueOf(origin.getLatitude());
		double lon1 = Float.valueOf(origin.getLongitude());
		double lat2 = Float.valueOf(destination.getLatitude());
		double lon2 = Float.valueOf(destination.getLongitude());
		double earthRadiusKm = 6378.1370D;

		double dLat = degreesToRadians(lat2 - lat1);
		double dLon = degreesToRadians(lon2 - lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(dLat / 2D) * Math.sin(dLat / 2D)
				+ Math.sin(dLon / 2D) * Math.sin(dLon / 2D) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
		return earthRadiusKm * c;
	}

	private double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180D;
	}

	
}
