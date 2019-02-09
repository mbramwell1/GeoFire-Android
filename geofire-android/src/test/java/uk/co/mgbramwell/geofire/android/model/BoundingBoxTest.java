package uk.co.mgbramwell.geofire.android.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoundingBoxTest {
  private static double MIN_LAT_RADIANS = 0.8988159009791253;
  private static double MIN_LNG_RADIANS = -0.0024819842473370025;
  private static double MIN_LAT_DEGREES = 51.498357685352396;
  private static double MIN_LNG_DEGREES = -0.14220722219036447;

  private static double MAX_LAT_RADIANS = 0.8991298233910038;
  private static double MAX_LNG_RADIANS = -0.0019776217161643407;
  private static double MAX_LAT_DEGREES = 51.5163441146476;
  private static double MAX_LNG_DEGREES = -0.11330937780963553;

  private static double MIN_MATCH = 25469.56217614124;
  private static double MAX_MATCH = 25472.828631258762;

  private BoundingBox boundingBox;

  /**
   * Delta of 0 used because locations MUST be precise.
   */
  @Test
  public void testThatBoundingBoxCalculatesDegreesCorrectly(){
    boundingBox = new BoundingBox(MIN_LAT_RADIANS, MIN_LNG_RADIANS, MAX_LAT_RADIANS, MAX_LNG_RADIANS);

    assertEquals(MIN_LAT_DEGREES, boundingBox.getMinimumLatitude(), 0);
    assertEquals(MIN_LNG_DEGREES, boundingBox.getMinimumLongitude(), 0);
    assertEquals(MAX_LAT_DEGREES, boundingBox.getMaximumLatitude(), 0);
    assertEquals(MAX_LNG_DEGREES, boundingBox.getMaximumLongitude(), 0);

    assertEquals(MIN_MATCH, boundingBox.getMinimumMatch(), 0);
    assertEquals(MAX_MATCH, boundingBox.getMaximumMatch(), 0);
  }
}
