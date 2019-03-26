package pandacommander.commute_realtr.parsing;

import pandacommander.commute_realtr.geocoding.AddressCoordinates;

public class MetroStation implements Comparable<MetroStation> {

	private String name;
	private AddressCoordinates coords;
	double distanceFromOriginKm;

	public MetroStation(String name, AddressCoordinates coords) {
		super();
		this.name = name;
		this.coords = coords;
	}

	protected String getName() {
		return name;
	}

	protected AddressCoordinates getCoords() {
		return coords;
	}

	protected double getDistanceFromOriginKm() {
		return distanceFromOriginKm;
	}

	protected void setDistanceFromOriginKm(double distanceFromOriginKm) {
		this.distanceFromOriginKm = distanceFromOriginKm;
	}

	public int compareTo(MetroStation metroStation) {
		return Float.compare((float) getDistanceFromOriginKm(), (float) metroStation.getDistanceFromOriginKm());
	}

}
