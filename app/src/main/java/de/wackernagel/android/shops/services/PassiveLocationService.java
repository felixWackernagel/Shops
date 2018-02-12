package de.wackernagel.android.shops.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import de.wackernagel.android.shops.MainActivity;
import de.wackernagel.android.shops.R;

public class PassiveLocationService extends Service {
    private static final String TAG = "PassiveLocationService";
    private LocationManager mLocationManager = null;
    private static final long LOCATION_INTERVAL_MS = 1000L * 60L; // 1 Minute
    private static final float LOCATION_DISTANCE_M = 100f; // 100 Meter

    private static class LocationListener implements android.location.LocationListener {

        private Context context;
        private Location mLastLocation;

        private int updateCount = 0;

        LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        void onAttach( final Context context ) {
            this.context = context;
        }

        void onDetach() {
            context = null;
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            Log.e(TAG, "Last lat=" + mLastLocation.getLatitude() + ", long=" + mLastLocation.getLongitude()  );

            if( context != null ) {
                updateCount++;
                final NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder( context, "shopsChannel" )
                                .setSmallIcon(R.drawable.ic_place_black_24dp)
                                .setContentTitle("Shops - New Location")
                                .setContentText( "(" + updateCount + ") Lat=" + mLastLocation.getLatitude() + ", Long=" + mLastLocation.getLongitude());
                final Intent resultIntent = new Intent( context, MainActivity.class );
                final PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                context,
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                final int mNotificationId = 1;
                final NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                if( mNotifyMgr != null ) {
                    mNotifyMgr.notify(mNotificationId, mBuilder.build());
                }
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    private final LocationListener mLocationListener = new LocationListener( LocationManager.PASSIVE_PROVIDER );

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");

        initializeLocationManager();

        mLocationListener.onAttach( this );
        requestLocation(LocationManager.NETWORK_PROVIDER);
        requestLocation(LocationManager.GPS_PROVIDER);
    }

    private void requestLocation(String provider) {
        if( mLocationManager.isProviderEnabled( provider ) ) {
            try {
                mLocationManager.requestLocationUpdates(provider, LOCATION_INTERVAL_MS, LOCATION_DISTANCE_M, mLocationListener );
                final Location lastLocation = mLocationManager.getLastKnownLocation(provider);
                if( lastLocation != null ) {
                    mLocationListener.onLocationChanged( lastLocation );
                }
            } catch (java.lang.SecurityException ex) {
                Log.i(TAG, "fail to request location update for " + provider + ", ignore", ex);
            } catch (IllegalArgumentException ex) {
                Log.d(TAG, "network provider does not exist for " + provider + ", " + ex.getMessage());
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            try {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mLocationListener.onDetach();
                mLocationManager.removeUpdates(mLocationListener);
            } catch (Exception ex) {
                Log.i(TAG, "fail to remove location listener, ignore", ex);
            }
        }
        final NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if( mNotifyMgr != null ) {
            mNotifyMgr.cancel(1);
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager - LOCATION_INTERVAL_MS: "+ LOCATION_INTERVAL_MS + " LOCATION_DISTANCE_M: " + LOCATION_DISTANCE_M);
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
}
