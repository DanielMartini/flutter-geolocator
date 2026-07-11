package com.baseflow.geolocator.location;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import org.junit.Test;

public class GeolocationManagerTest {
  @Test
  public void isWearOs_returnsTrueWhenWatchFeatureIsAvailable() {
    Context context = mock(Context.class);
    PackageManager packageManager = mock(PackageManager.class);
    when(context.getPackageManager()).thenReturn(packageManager);
    when(packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)).thenReturn(true);

    assertTrue(GeolocationManager.isWearOs(context));
  }

  @Test
  public void isWearOs_returnsFalseWhenWatchFeatureIsUnavailable() {
    Context context = mock(Context.class);
    PackageManager packageManager = mock(PackageManager.class);
    when(context.getPackageManager()).thenReturn(packageManager);
    when(packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)).thenReturn(false);

    assertFalse(GeolocationManager.isWearOs(context));
  }

  @Test
  public void createLocationClient_usesLocationManagerOnWatch() {
    Context context = mock(Context.class);
    PackageManager packageManager = mock(PackageManager.class);
    when(context.getPackageManager()).thenReturn(packageManager);
    when(packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)).thenReturn(true);
    when(context.getSystemService(Context.LOCATION_SERVICE)).thenReturn(mock(LocationManager.class));

    LocationClient client =
        GeolocationManager.getInstance().createLocationClient(context, false, null);

    assertTrue(client instanceof LocationManagerClient);
  }
}
