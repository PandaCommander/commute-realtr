package pandacommander.duproprio_scrapper;

public class Listing {

	private String Id;
	private String longitude;
	private String latitude;
	private String price;
	private String address;
	private String city;
	private String description;
	private String bedrooms;
	private String bathrooms;
	private String buildingDimensions;
	private String lotDimensions;
	private String imageUrl;

	public Listing(String id, String longitude, String latitude, String price, String address, String city,
			String description, String bedrooms, String bathrooms, String buildingDimensions, String lotDimensions,
			String imageUrl) {
		super();
		Id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.price = price;
		this.address = address;
		this.city = city;
		this.description = description;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.buildingDimensions = buildingDimensions;
		this.lotDimensions = lotDimensions;
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return Id;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getPrice() {
		return price;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getDescription() {
		return description;
	}

	public String getBedrooms() {
		return bedrooms;
	}

	public String getBathrooms() {
		return bathrooms;
	}

	public String getBuildingDimensions() {
		return buildingDimensions;
	}

	public String getLotDimensions() {
		return lotDimensions;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	
	public AddressCoordinates getAddressCoordinates() {
		return new AddressCoordinates(latitude, longitude, address);
	}

	@Override
	public String toString() {
		return "Listing [Id=" + Id + ", longitude=" + longitude + ", latitude=" + latitude + ", price=" + price
				+ ", address=" + address + ", city=" + city + ", description=" + description + ", bedrooms=" + bedrooms
				+ ", bathrooms=" + bathrooms + ", buildingDimensions=" + buildingDimensions + ", lotDimensions="
				+ lotDimensions + ", imageUrl=" + imageUrl + "]";
	}

}
