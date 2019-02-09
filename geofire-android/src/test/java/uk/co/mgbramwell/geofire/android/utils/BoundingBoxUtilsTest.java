package uk.co.mgbramwell.geofire.android.utils;

import org.junit.Before;
import org.junit.Test;

import uk.co.mgbramwell.geofire.android.model.BoundingBox;
import uk.co.mgbramwell.geofire.android.model.Distance;
import uk.co.mgbramwell.geofire.android.model.DistanceUnit;
import uk.co.mgbramwell.geofire.android.model.QueryLocation;

import static org.junit.Assert.assertEquals;

public class BoundingBoxUtilsTest {
  private static double LAT_DEGREES = 51.507351;
  private static double LNG_DEGREES = -0.127758;

  private BoundingBoxUtils boundingBoxUtils;
  private QueryLocation queryLocation;
  private Distance distance;

  @Before
  public void setup() {
    queryLocation = QueryLocation.fromDegrees(LAT_DEGREES, LNG_DEGREES);
  }

  /**
   * Delta of 0 used because locations MUST be precise.
   */
  @Test
  public void testBoundingBoxUtilsBuildsCorrectBoxInKilometers(){
    distance = new Distance(1.0, DistanceUnit.KILOMETERS);
    boundingBoxUtils = new BoundingBoxUtils(distance.getUnit());

    BoundingBox boundingBox = boundingBoxUtils.getBoundingBox(queryLocation, distance.getDistance());

    assertEquals(51.498357785352404, boundingBox.getMinimumLatitude(), 0);
    assertEquals(-0.1422069222220764, boundingBox.getMinimumLongitude(), 0);
    assertEquals(51.516344214647596, boundingBox.getMaximumLatitude(), 0);
    assertEquals(-0.11330907777792365, boundingBox.getMaximumLongitude(), 0);

    assertEquals(25469.562194441212, boundingBox.getMinimumMatch(), 0);
    assertEquals(25472.828649558793, boundingBox.getMaximumMatch(), 0);
  }

  /**
   * Delta of 0 used because locations MUST be precise.
   */
  @Test
  public void testBoundingBoxUtilsBuildsCorrectBoxInMiles(){
    distance = new Distance(1.0, DistanceUnit.MILES);
    boundingBoxUtils = new BoundingBoxUtils(distance.getUnit());

    BoundingBox boundingBox = boundingBoxUtils.getBoundingBox(queryLocation, distance.getDistance());

    assertEquals(51.492877822185285, boundingBox.getMinimumLatitude(), 0);
    assertEquals(-0.15101128938589106, boundingBox.getMinimumLongitude(), 0);
    assertEquals(51.52182417781472, boundingBox.getMaximumLatitude(), 0);
    assertEquals(-0.10450471061410897, boundingBox.getMaximumLongitude(), 0);

    assertEquals(25468.566996703965, boundingBox.getMinimumMatch(), 0);
    assertEquals(25473.823847296037, boundingBox.getMaximumMatch(), 0);
  }
}
