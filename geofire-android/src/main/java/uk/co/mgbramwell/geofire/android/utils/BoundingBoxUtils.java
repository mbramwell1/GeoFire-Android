package uk.co.mgbramwell.geofire.android.utils;

import uk.co.mgbramwell.geofire.android.model.BoundingBox;
import uk.co.mgbramwell.geofire.android.model.DistanceUnit;
import uk.co.mgbramwell.geofire.android.model.QueryLocation;

public class BoundingBoxUtils {
  private DistanceUnit distanceUnit;

  private static final double EARTH_RADIUS_KM = 6371.001;
  private static final double EARTH_RADIUS_MILES = 3958.756;

  private static final double MINIMUM_LATITUDE = Math.toRadians(-90d);  // -PI/2
  private static final double MAXIMUM_LATITUDE = Math.toRadians(90d);   //  PI/2
  private static final double MINIMUM_LONGITUDE = Math.toRadians(-180d); // -PI
  private static final double MAXIMUM_LONGITUDE = Math.toRadians(180d);  //  PI

  public BoundingBoxUtils(DistanceUnit distanceUnit) {
    this.distanceUnit = distanceUnit;
  }

  /**
   * @param queryLocation the query latitude and longitude
   * @param distance the distance for the bounding box
   */
  public BoundingBox getBoundingBox(QueryLocation queryLocation, double distance) {

    if (distance < 0d) {
      throw new IllegalArgumentException();
    }

    double distanceInRadians;
    switch (distanceUnit){
      case MILES:
        distanceInRadians = distance / EARTH_RADIUS_MILES;
        break;
      case KILOMETERS:
        distanceInRadians = distance / EARTH_RADIUS_KM;
        break;
      default:
        throw new IllegalArgumentException();
    }

    double minimumLatitude = queryLocation.getLatitude() - distanceInRadians;
    double maximumLatitude = queryLocation.getLatitude() + distanceInRadians;
    double minimumLongitude;
    double maximumLongitude;

    if (minimumLatitude > MINIMUM_LATITUDE && maximumLatitude < MAXIMUM_LATITUDE) {

      double deltaLongitude = Math.asin(Math.sin(distanceInRadians) / Math.cos(queryLocation.getLatitude()));

      minimumLongitude = queryLocation.getLongitude() - deltaLongitude;
      if (minimumLongitude < MINIMUM_LONGITUDE) {
        minimumLongitude += 2d * Math.PI;
      }

      maximumLongitude = queryLocation.getLongitude() + deltaLongitude;
      if (maximumLongitude > MAXIMUM_LONGITUDE) {
        maximumLongitude -= 2d * Math.PI;
      }

    } else {
      minimumLatitude = Math.max(minimumLatitude, MINIMUM_LATITUDE);
      maximumLatitude = Math.min(maximumLatitude, MAXIMUM_LATITUDE);
      minimumLongitude = MINIMUM_LONGITUDE;
      maximumLongitude = MAXIMUM_LONGITUDE;
    }

    return new BoundingBox(minimumLatitude, minimumLongitude, maximumLatitude, maximumLongitude);
  }

}
