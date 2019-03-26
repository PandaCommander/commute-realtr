package pandacommander.commute_realtr.listing;

import pandacommander.commute_realtr.geocoding.AddressCoordinates;

public class Listing {

	private String id;
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
	private String listingUrl;

	public Listing(String id, String longitude, String latitude, String price, String address, String city,
			String description, String bedrooms, String bathrooms, String buildingDimensions, String lotDimensions,
			String imageUrl, String listingUrl) {
		super();
		this.id = id;
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
		this.listingUrl = listingUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}

	public String getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(String bathrooms) {
		this.bathrooms = bathrooms;
	}

	public String getBuildingDimensions() {
		return buildingDimensions;
	}

	public void setBuildingDimensions(String buildingDimensions) {
		this.buildingDimensions = buildingDimensions;
	}

	public String getLotDimensions() {
		return lotDimensions;
	}

	public void setLotDimensions(String lotDimensions) {
		this.lotDimensions = lotDimensions;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getListingUrl() {
		return listingUrl;
	}

	public void setListingUrl(String listingUrl) {
		this.listingUrl = listingUrl;
	}

	public AddressCoordinates getAddressCoordinates() {
		return new AddressCoordinates(latitude, longitude, address);
	}

	@Override
	public String toString() {
		return "Listing [Id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", price=" + price
				+ ", address=" + address + ", city=" + city + ", description=" + description + ", bedrooms=" + bedrooms
				+ ", bathrooms=" + bathrooms + ", buildingDimensions=" + buildingDimensions + ", lotDimensions="
				+ lotDimensions + ", imageUrl=" + imageUrl + "]";
	}

}
