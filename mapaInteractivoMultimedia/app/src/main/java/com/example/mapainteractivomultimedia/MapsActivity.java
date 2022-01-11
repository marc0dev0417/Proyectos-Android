package com.example.mapainteractivomultimedia;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapainteractivomultimedia.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * The type Maps activity.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Marker marcadorMultimedia;
    private Marker marcadorCanvasPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


       final LatLng HUESCA = new LatLng(42.13180057387883, -0.40825168671741835);
       final LatLng TERUEL = new LatLng(40.391497282820595, -1.114983245517741);

        marcadorMultimedia = googleMap.addMarker(new MarkerOptions()
        .position(HUESCA)
        .title("Hola")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.multimedia)));

        marcadorCanvasPaint = googleMap.addMarker(new MarkerOptions()
        .position(TERUEL)
        .title("Dibujos con Paint")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cat))
        );

        mMap.addPolyline((new PolylineOptions()).add(HUESCA, TERUEL)
        .width(5)
        .color(Color.GREEN)
        .geodesic(true));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if (marker.equals(marcadorMultimedia)) {
                    startActivity(new Intent(getApplicationContext(), ActividadElegirImagen.class));
                }
                if (marker.equals(marcadorCanvasPaint)){
                    startActivity(new Intent(getApplicationContext(), ActividadCanvaPaint.class));
                }
                return true;
            }
        });
    }
}