package pandacommander.duproprio_scrapper;

public class AddressCoordinates {
	public String latitude;
	public String longitude;
	public String address;

	public AddressCoordinates(String latitude, String longitude, String address) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getAddress() {
		return address;
	}

}
