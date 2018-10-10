package pandacommander.duproprio_scrapper;

import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.configuration2.Configuration;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;

public class GoogleGeoCoder extends GeoCoder {

	GeoApiContext geoApiContext;

	public GoogleGeoCoder(Configuration secrets) {
		super(secrets);
		geoApiContext = new GeoApiContext.Builder().apiKey(secrets.getString("google.geocoding.token")).build();
	}

	public AddressCoordinates getAddressCoordinates(String address) {
		GeocodingResult[] results;
		AddressCoordinates coords = null;
		try {
			results = GeocodingApi.geocode(geoApiContext, address).await();

			String latitude = String.valueOf(results[0].geometry.location.lat);
			String longitude = String.valueOf(results[0].geometry.location.lng);

			coords = new AddressCoordinates(latitude, longitude, address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return coords;
	}

	public double getCommuteTimeSeconds(AddressCoordinates origin, AddressCoordinates destination) {

		Calendar date = new GregorianCalendar();
		date.add(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.HOUR_OF_DAY, 8); // TODO Unharcode
		date.set(Calendar.MINUTE, 0);// TODO Unharcode
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);

		DirectionsResult result = null;
		Instant time = date.toInstant();

		try {
			result = DirectionsApi.newRequest(geoApiContext).origin(origin.getLatitude() + "," + origin.getLongitude())
					.destination(destination.getLatitude() + "," + destination.getLongitude()).departureTime(time)
					.mode(TravelMode.TRANSIT).await();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.routes[0].legs[0].duration.inSeconds;
	}
}
