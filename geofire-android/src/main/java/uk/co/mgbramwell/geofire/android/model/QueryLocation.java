package uk.co.mgbramwell.geofire.android.model;

public class QueryLocation {

  private double latitude;
  private double longitude;

  private static final double MINIMUM_LATITUDE = Math.toRadians(-90d);  // -PI/2
  private static final double MAXIMUM_LATITUDE = Math.toRadians(90d);   //  PI/2
  private static final double MINIMUM_LONGITUDE = Math.toRadians(-180d); // -PI
  private static final double MAXIMUM_LONGITUDE = Math.toRadians(180d);  //  PI

  /**
   * @param latitude the latitude in radians
   * @param longitude the longitude in radians
   */
  public static QueryLocation fromRadians(double latitude, double longitude) {
    QueryLocation queryLocation = new QueryLocation();
    queryLocation.latitude = latitude;
    queryLocation.longitude = longitude;
    queryLocation.checkBounds();
    return queryLocation;
  }

  /**
   * @param latitude the latitude in degrees.
   * @param longitude the longitude in degrees.
   */
  public static QueryLocation fromDegrees(double latitude, double longitude) {
    QueryLocation queryLocation = new QueryLocation();
    queryLocation.latitude = Math.toRadians(latitude);
    queryLocation.longitude = Math.toRadians(longitude);
    queryLocation.checkBounds();
    return queryLocation;
  }

  private void checkBounds() {
    if (latitude < MINIMUM_LATITUDE || latitude > MAXIMUM_LATITUDE ||
        longitude < MINIMUM_LONGITUDE || longitude > MAXIMUM_LONGITUDE) {
      throw new IllegalArgumentException();

    }
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

}
