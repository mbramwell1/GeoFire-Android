package uk.co.mgbramwell.geofire.android.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QueryLocationTest {
  private static double LAT_DEGREES = 51.507351;
  private static double LNG_DEGREES = -0.127758;
  private static double INVALID_LAT_DEGREES = 98;
  private static double INVALID_LNG_DEGREES = 190;

  private static double LAT_RADIANS = 0.8989728639303938;
  private static double LNG_RADIANS = -0.0022297977457629158;
  private static double INVALID_LAT_RADIANS = 1.7104226669544427;
  private static double INVALID_LNG_RADIANS = 3.3161255787892263;

  private QueryLocation queryLocation;

  /**
   * Delta of 0 used because locations MUST be precise.
   */
  @Test
  public void testThatFromRadiansGivesCorrectLatitudeLongitude(){
    queryLocation = QueryLocation.fromRadians(LAT_RADIANS, LNG_RADIANS);
    assertEquals(LAT_RADIANS, queryLocation.getLatitude(), 0);
    assertEquals(LNG_RADIANS, queryLocation.getLongitude(), 0);
  }

  /**
   * Delta of 0 used because locations MUST be precise.
   */
  @Test
  public void testThatFromDegreesGivesCorrectLatitudeLongitude(){
    queryLocation = QueryLocation.fromDegrees(LAT_DEGREES, LNG_DEGREES);
    assertEquals(LAT_RADIANS, queryLocation.getLatitude(), 0);
    assertEquals(LNG_RADIANS, queryLocation.getLongitude(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThatOutOfBoundsDegreesThrowsInvalidArgumentException(){
    QueryLocation.fromDegrees(INVALID_LAT_DEGREES, INVALID_LNG_DEGREES);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThatOutOfBoundsRadiansThrowsInvalidArgumentException(){
    QueryLocation.fromRadians(INVALID_LAT_RADIANS, INVALID_LNG_RADIANS);
  }
}
