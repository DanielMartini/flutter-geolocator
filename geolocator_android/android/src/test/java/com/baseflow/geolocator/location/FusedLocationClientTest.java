package com.baseflow.geolocator.location;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.pm.PackageManager;
import org.junit.Test;

public class FusedLocationClientTest {
  @Test
  public void isWearOs_returnsTrueWhenWatchFeatureIsAvailable() {
    Context context = mock(Context.class);
    PackageManager packageManager = mock(PackageManager.class);
    when(context.getPackageManager()).thenReturn(packageManager);
    when(packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)).thenReturn(true);

    assertTrue(FusedLocationClient.isWearOs(context));
  }

  @Test
  public void isWearOs_returnsFalseWhenWatchFeatureIsUnavailable() {
    Context context = mock(Context.class);
    PackageManager packageManager = mock(PackageManager.class);
    when(context.getPackageManager()).thenReturn(packageManager);
    when(packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)).thenReturn(false);

    assertFalse(FusedLocationClient.isWearOs(context));
  }
}
