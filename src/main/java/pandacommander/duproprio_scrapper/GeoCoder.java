package pandacommander.duproprio_scrapper;

import org.apache.commons.configuration2.Configuration;

public abstract class GeoCoder {

	protected Configuration secrets;

	public GeoCoder(Configuration secrets) {
		super();
		this.secrets = secrets;
	}

	public abstract AddressCoordinates getAddressCoordinates(String address);

	public abstract double getCommuteTimeSeconds(AddressCoordinates origin, AddressCoordinates destination);
}
