package uk.co.mgbramwell.geofire.android.query;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.Query;

import uk.co.mgbramwell.geofire.android.model.BoundingBox;
import uk.co.mgbramwell.geofire.android.model.Distance;
import uk.co.mgbramwell.geofire.android.model.QueryLocation;
import uk.co.mgbramwell.geofire.android.utils.BoundingBoxUtils;

public class GeoFireQuery {
  private Query query;

  public GeoFireQuery onCollection(CollectionReference collectionReference) {
    this.query = collectionReference;
    return this;
  }

  public GeoFireQuery whereArrayContains(String field, Object value) {
    this.query = this.query.whereArrayContains(field, value);
    return this;
  }

  public GeoFireQuery whereArrayContains(FieldPath fieldPath, Object value) {
    this.query = this.query.whereArrayContains(fieldPath, value);
    return this;
  }

  public GeoFireQuery whereEqualTo(String field, Object value) {
    this.query = this.query.whereEqualTo(field, value);
    return this;
  }

  public GeoFireQuery whereEqualTo(FieldPath fieldPath, Object value) {
    this.query = this.query.whereEqualTo(fieldPath, value);
    return this;
  }

  public GeoFireQuery whereGreaterThan(String field, Object value) {
    this.query = this.query.whereGreaterThan(field, value);
    return this;
  }

  public GeoFireQuery whereGreaterThan(FieldPath fieldPath, Object value) {
    this.query = this.query.whereGreaterThan(fieldPath, value);
    return this;
  }

  public GeoFireQuery whereGreaterThanOrEqualTo(String field, Object value) {
    this.query = this.query.whereGreaterThanOrEqualTo(field, value);
    return this;
  }

  public GeoFireQuery whereGreaterThanOrEqualTo(FieldPath fieldPath, Object value) {
    this.query = this.query.whereGreaterThanOrEqualTo(fieldPath, value);
    return this;
  }

  public GeoFireQuery whereLessThan(String field, Object value) {
    this.query = this.query.whereLessThan(field, value);
    return this;
  }

  public GeoFireQuery whereLessThan(FieldPath fieldPath, Object value) {
    this.query = this.query.whereLessThan(fieldPath, value);
    return this;
  }

  public GeoFireQuery whereLessThanOrEqualTo(String field, Object value) {
    this.query = this.query.whereLessThanOrEqualTo(field, value);
    return this;
  }

  public GeoFireQuery whereLessThanOrEqualTo(FieldPath fieldPath, Object value) {
    this.query = this.query.whereLessThanOrEqualTo(fieldPath, value);
    return this;
  }

  public GeoFireQuery orderBy(String field) {
    this.query = this.query.orderBy(field);
    return this;
  }

  public GeoFireQuery orderBy(FieldPath fieldPath) {
    this.query = this.query.orderBy(fieldPath);
    return this;
  }

  public GeoFireQuery orderBy(String field, Query.Direction direction) {
    this.query = this.query.orderBy(field, direction);
    return this;
  }

  public GeoFireQuery orderBy(FieldPath fieldPath, Query.Direction direction) {
    this.query = this.query.orderBy(fieldPath, direction);
    return this;
  }

  public GeoFireQuery limit(long limit) {
    this.query = this.query.limit(limit);
    return this;
  }

  public GeoFireQuery endAt(Object... fieldValues) {
    this.query = this.query.endAt(fieldValues);
    return this;
  }

  public GeoFireQuery endAt(DocumentSnapshot documentSnapshot) {
    this.query = this.query.endAt(documentSnapshot);
    return this;
  }

  public GeoFireQuery startAt(Object... fieldValues) {
    this.query = this.query.startAt(fieldValues);
    return this;
  }

  public GeoFireQuery startAt(DocumentSnapshot documentSnapshot) {
    this.query = this.query.startAt(documentSnapshot);
    return this;
  }

  public GeoFireQuery endBefore(Object... fieldValues) {
    this.query = this.query.endBefore(fieldValues);
    return this;
  }

  public GeoFireQuery endBefore(DocumentSnapshot documentSnapshot) {
    this.query = this.query.endBefore(documentSnapshot);
    return this;
  }

  public GeoFireQuery startAfter(Object... fieldValues) {
    this.query = this.query.startAfter(fieldValues);
    return this;
  }

  public GeoFireQuery startAfter(DocumentSnapshot documentSnapshot) {
    this.query = this.query.startAfter(documentSnapshot);
    return this;
  }

  public GeoFireQuery whereNearTo(QueryLocation queryLocation, Distance distance) {
    BoundingBoxUtils geoPointUtils = new BoundingBoxUtils(distance.getUnit());
    BoundingBox boundingBox = geoPointUtils.getBoundingBox(queryLocation, distance.getDistance());
    System.out.println(boundingBox.toString());
    this.query = this.query
        .whereGreaterThanOrEqualTo("geoFireLocation", boundingBox.getMinimumMatch())
        .whereLessThanOrEqualTo("geoFireLocation", boundingBox.getMaximumMatch());
    return this;
  }

  public Query build() {
    return this.query;
  }
}
