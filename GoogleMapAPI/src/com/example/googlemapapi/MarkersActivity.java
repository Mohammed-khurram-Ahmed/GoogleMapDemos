package com.example.googlemapapi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkersActivity extends Activity {
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_markers);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showMarker();
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		        // Other supported types include: MAP_TYPE_NORMAL,
		        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
				//map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	}
		});
		
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			}
		});
		
		
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			}
		});
		Button button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				showIndooor();
			}
		});
		
		Button button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				showCustomMarker();
			}
		});
		
		Button button7 = (Button) findViewById(R.id.button7);
		button7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				showFlatMarker();
			}
		});
		
	}

	protected void showFlatMarker() {
		LatLng mapCenter = new LatLng(41.889, -87.622);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 13));

        // Flat markers will rotate when the map is rotated,
        // and change perspective when the map is tilted.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.directly_arrow))
                .position(mapCenter));
                

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(mapCenter)
                .zoom(13)
                .bearing(90)
                .build();
        
        // Animate the change in camera view over 2 seconds
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                2000, null);
		
	}

	protected void showCustomMarker() {
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(41.889, -87.622), 16));

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps. 
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));		
	
        map.addMarker(new MarkerOptions()
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))
        .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
        .position(new LatLng(41.119, -87.642)));
	}

	protected void showIndooor() {
		// Some buildings have indoor maps. Center the camera over
        // the building, and a floor picker will automatically appear.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-33.86997, 151.2089), 18));		
	}

	private void showMarker() {
		// Get a handle to the Map Fragment
		LatLng sydney = new LatLng(-33.867, 151.206);
		// Set my location option enable
		map.setMyLocationEnabled(true);
		// Move camera on specific location

		// Add marker to particular location
		map.addMarker(new MarkerOptions().title("Sydney")
				.snippet("The most populous city in Australia.")
				.position(sydney));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

	}
}
