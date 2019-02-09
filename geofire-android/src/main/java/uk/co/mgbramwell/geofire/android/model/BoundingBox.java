package uk.co.mgbramwell.geofire.android.model;

public class BoundingBox {
  private double minimumLatitude;
  private double minimumLongitude;
  private double maximumLatitude;
  private double maximumLongitude;
  private double minimumMatch;
  private double maximumMatch;
  
  public BoundingBox(double minimumLatitude, double minimumLongitude, double maximumLatitude, double maximumLongitude) {
    this.minimumLatitude = Math.toDegrees(minimumLatitude);
    this.minimumLongitude = Math.toDegrees(minimumLongitude);
    this.maximumLatitude = Math.toDegrees(maximumLatitude);
    this.maximumLongitude = Math.toDegrees(maximumLongitude);
    this.minimumMatch = (this.minimumLatitude+90)*180+this.minimumLongitude;
    this.maximumMatch = (this.maximumLatitude+90)*180+this.maximumLongitude;
  }

  public double getMinimumLatitude() {
    return minimumLatitude;
  }

  public double getMinimumLongitude() {
    return minimumLongitude;
  }

  public double getMaximumLatitude() {
    return maximumLatitude;
  }

  public double getMaximumLongitude() {
    return maximumLongitude;
  }

  public double getMinimumMatch(){
    return minimumMatch;
  }

  public double getMaximumMatch(){
    return maximumMatch;
  }

  @Override
  public String toString() {
    return "BoundingBox{" +
        "minimumLatitude=" + minimumLatitude +
        ", minimumLongitude=" + minimumLongitude +
        ", maximumLatitude=" + maximumLatitude +
        ", maximumLongitude=" + maximumLongitude +
        ", minimumMatch=" + minimumMatch +
        ", maximumMatch=" + maximumMatch +
        '}';
  }
}
