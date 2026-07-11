package com.baseflow.geolocator.location;

import android.app.Activity;

import android.content.Context;
import android.location.LocationManager;
import com.baseflow.geolocator.errors.ErrorCallback;

public interface LocationClient {
  void isLocationServiceEnabled(LocationServiceListener listener);

  void getLastKnownPosition(
      PositionChangedCallback positionChangedCallback, ErrorCallback errorCallback);

  boolean onActivityResult(int requestCode, int resultCode);

  void startPositionUpdates(
      Activity activity,
      PositionChangedCallback positionChangedCallback,
      ErrorCallback errorCallback);

  void stopPositionUpdates();
  
  default boolean checkLocationService(Context context) {
    if (context == null) {
      return false;
    }

    LocationManager locationManager =
        (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    return locationManager != null
        && (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
  }
}
