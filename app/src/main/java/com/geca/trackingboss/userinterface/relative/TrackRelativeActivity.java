package com.geca.trackingboss.userinterface.relative;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.LocationCallback;
import com.geca.trackingboss.R;
import com.geca.trackingboss.userinterface.BaseView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TrackRelativeActivity extends AppCompatActivity implements TrackRelativeContract.View, OnMapReadyCallback {

    private GoogleMap googleMap;
    final int LOCATION_REQUEST_CODE = 1;
    private SupportMapFragment mapFragment;

    private int relativeId;
    private GeoFire geoFire;
    private DatabaseReference dbRelativesAvailable;
    private DatabaseReference dbRelatives;
    private String relativeUid;
    private String relativeName;
    private ValueEventListener listenerRelativeValue;
    private LocationCallback locationFirstCallback;
    private LocationCallback locationCallback;
    private GeoLocation lastLocation;

    Handler handler = new Handler();
    int delay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_relative);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.relativeMap);
        mapFragment.getMapAsync(this);

        Bundle extras = this.getIntent().getExtras();
        relativeId = extras.getInt("id");
        relativeName = extras.getString("fullName");

        dbRelativesAvailable = FirebaseDatabase.getInstance().getReference("relativesAvailable");
        geoFire = new GeoFire(dbRelativesAvailable);

        dbRelatives = FirebaseDatabase.getInstance().getReference("Users").child("Relative");
        Query queryRelative = dbRelatives.orderByChild("relativeId")
                .equalTo(relativeId);

        createListeners();

        queryRelative.addListenerForSingleValueEvent(listenerRelativeValue);
    }

    @Override
    public void setPresenter(TrackRelativeContract.Presenter presenter) {

    }

    @Override
    public void openView(BaseView view) {

    }

    @Override
    public void closeView() {

    }

    @Override
    public void showErrorBadRequest() {

    }

    @Override
    public void showErrorInternalError() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void showSuccessDialog() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case LOCATION_REQUEST_CODE:{
                mapFragment.getMapAsync(this);
            }
        }
    }

    private void createListeners(){
        locationFirstCallback = new LocationCallback() {
            @Override
            public void onLocationResult(String key, GeoLocation location) {
                if(location==null){
                    return;
                }
                else {
                    lastLocation = location;
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude))
                            .title(relativeName));
                    CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(location.latitude, location.longitude));
                    CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
                    googleMap.moveCamera(center);
                    googleMap.animateCamera(zoom);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(String key, GeoLocation location) {
                if(location==null || (lastLocation.latitude == location.latitude &&
                lastLocation.longitude == location.longitude)){
                    return;
                }
                else {
                    lastLocation = location;
                    googleMap.clear();
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude))
                            .title(relativeName));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        listenerRelativeValue = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot relativeSnapshot: dataSnapshot.getChildren()) {
                    relativeUid = (String) relativeSnapshot.child("uid").getValue();
                }
                geoFire.getLocation(relativeUid, locationFirstCallback);

                handler.postDelayed(new Runnable(){
                    public void run(){
                        geoFire.getLocation(relativeUid, locationCallback);
                        handler.postDelayed(this, delay);
                    }
                }, delay);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

    }

}
