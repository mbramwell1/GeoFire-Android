package uk.co.mgbramwell.geofire.android.query;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.obfuscated.zzag;

import uk.co.mgbramwell.geofire.android.model.BoundingBox;
import uk.co.mgbramwell.geofire.android.model.Distance;
import uk.co.mgbramwell.geofire.android.model.QueryLocation;
import uk.co.mgbramwell.geofire.android.utils.GeoPointUtils;

public class GeoFireQuery {
  private Query query;

  public static class Builder {
    private Query query;
    private CollectionReference collectionReference;

    public Builder onCollection(CollectionReference collectionReference) {
      this.collectionReference = collectionReference;
      this.query = this.collectionReference;
      return this;
    }

    public Builder whereArrayContains(String field, Object value) {
      this.query = this.query.whereArrayContains(field, value);
      return this;
    }

    public Builder whereEqualTo(String field, Object value) {
      this.query = this.query.whereArrayContains(field, value);
      return this;
    }

    public Builder whereGreaterThan(String field, Object value) {
      this.query = this.query.whereGreaterThan(field, value);
      return this;
    }

    public Builder whereGreaterThanOrEqualTo(String field, Object value) {
      this.query = this.query.whereGreaterThanOrEqualTo(field, value);
      return this;
    }

    public Builder whereLessThan(String field, Object value) {
      this.query = this.query.whereLessThan(field, value);
      return this;
    }

    public Builder whereLessThanOrEqualTo(String field, Object value) {
      this.query = this.query.whereLessThanOrEqualTo(field, value);
      return this;
    }

    public Builder orderBy(String field){
      this.query = this.query.orderBy(field);
      return this;
    }

    public Builder orderBy(String field, Query.Direction direction){
      this.query = this.query.orderBy(field, direction);
      return this;
    }

    public Builder limit(long limit){
      this.query = this.query.limit(limit);
      return this;
    }

    public Builder endAt(Object... fieldValues){
      this.query = this.query.endAt(fieldValues);
      return this;
    }

    public Builder startAt(Object... fieldValues){
      this.query = this.query.startAt(fieldValues);
      return this;
    }

    public Builder endBefore(Object... fieldValues){
      this.query = this.query.endBefore(fieldValues);
      return this;
    }

    public Builder startAfter(Object... fieldValues){
      this.query = this.query.startAfter(fieldValues);
      return this;
    }

    public Builder whereNearTo(QueryLocation queryLocation, Distance distance){
      GeoPointUtils geoPointUtils = new GeoPointUtils(distance.getUnit());
      BoundingBox boundingBox = geoPointUtils.getBoundingBox(queryLocation, distance.getDistance());
      this.query = this.query
          .whereGreaterThanOrEqualTo("locationFirestoreLocation", boundingBox.getMinimumMatch())
          .whereLessThanOrEqualTo("locationFirestoreLocation", boundingBox.getMaximumMatch());
      return this;
    }

    public Query build() {
      return this.query;
    }
  }
}
