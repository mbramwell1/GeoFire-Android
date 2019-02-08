package uk.co.mgbramwell.geofire.android;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.SetOptions;

import uk.co.mgbramwell.geofire.android.listeners.SetLocationListener;
import uk.co.mgbramwell.geofire.android.query.GeoFireQuery;

import java.util.HashMap;
import java.util.Map;

public class GeoFire {
  private final CollectionReference collectionReference;
  private GeoFireQuery firebaseFirestoreQuery;

  public GeoFire(CollectionReference collectionReference){
    this.collectionReference = collectionReference;
    this.firebaseFirestoreQuery = new GeoFireQuery();
  }

  DocumentReference getDocumentReference(String documentID) {
    return this.collectionReference.document(documentID);
  }

  public void setLocation(String documentID, double latitude, double longitude) {
    this.setLocation(documentID, latitude, longitude, null);
  }

  public void setLocation(String documentId, double latitude, double longitude, SetLocationListener listener){
    if (documentId == null) {
      throw new IllegalArgumentException();
    } else {
      DocumentReference docRef = this.getDocumentReference(documentId);
      double degreeMatch = (latitude+90)*180+longitude;
      Map<String, Object> updates = new HashMap();
      updates.put("locationFirestoreLocation", degreeMatch);
      docRef.set(updates, SetOptions.merge()).addOnCompleteListener(task -> {
        if (listener != null) {
          if (task.isSuccessful()) {
            listener.onCompleted(null);
          } else {
            listener.onCompleted(task.getException());
          }
        }

      });
    }
  }
}
