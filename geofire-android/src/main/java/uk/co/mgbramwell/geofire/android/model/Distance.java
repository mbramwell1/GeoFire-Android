package uk.co.mgbramwell.geofire.android.model;

public class Distance {
  private double distance;
  private DistanceUnit distanceUnit;

  public Distance(double distance,
      DistanceUnit distanceUnit) {
    this.distance = distance;
    this.distanceUnit = distanceUnit;
  }

  public double getDistance() {
    return distance;
  }

  public DistanceUnit getUnit() {
    return distanceUnit;
  }
}
