# GeoFire-Android [![Build Status](https://travis-ci.org/mbramwell1/GeoFire-Android.svg?branch=master)](https://travis-ci.org/mbramwell1/GeoFire-Android) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/uk.co.mgbramwell.geofire/geofire-android/badge.svg)](https://search.maven.org/artifact/uk.co.mgbramwell.geofire/geofire-android)

Current Production: **NA**<br>
Current Pre-Production: **0.0.2**<br>

_GeoFire-Android_ adds location based searching capability to the popular Google Firebase Firestore platform.

## Usage
GeoFire works with your exiting Firebase Firestore setup. To use it add the following line to your gradle file.
```
implementation 'uk.co.mgbramwell.geofire:geofire-android:0.0.2'
```

You'll need to set up GeoFire to use your collection:

```
private FirebaseFirestore db = FirebaseFirestore.getInstance();
private CollectionReference myCollection = db.collection("myCollection");
private GeoFire geoFire = new GeoFire(myCollection);
```

When saving your documents, in the onComplete listener you'll need to get GeoFire to update the document with its calculated location (you obviously need to replace the document object with whatever object you're working with):

```
geoFire.setLocation(document.getId(), document.getLatitude(), document.getLongitude());
```

If you want to check if the insert is successful, make your class implement SetLocationListener, and look for exceptions in the onComplete method:

```
geoFire.setLocation(document.getId(), document.getLatitude(), document.getLongitude(), this);

@Override
public void onComplete(Exception e){
 DO_STUFF_HERE
};
```

You can then use GeoFire as shown below. The line .whereNearTo is the important bit, but you can use any
other standard Firestore query language as normal:

```
QueryLocation queryLocation = QueryLocation.fromDegrees(latitude, longitude);
Distance searchDistance = new Distance(1.0, DistanceUnit.KILOMETERS);
geoFire.query()
    .whereEqualTo("title", "The Title")
    .whereNearTo(queryLocation, distance)
    .orderBy("timestamp", Query.Direction.DESCENDING)
    .limit(10)
    .build()
    .get()
    .addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        Log.i("DB", "Got Documents.");
      } else {
        Log.w("DB", "Error getting documents.", task.getException());
      }
    });
```
