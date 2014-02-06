package com.example.googlemapapi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class PolylinesActivity extends Activity {
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_polylines);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showPolyline();
			}

		});

		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showPolyLineWithMarker();
			}
		});
	}

	protected void showPolyLineWithMarker() {
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.142,
				178.431), 2));

		
		 map.addMarker(new MarkerOptions()
         .icon(BitmapDescriptorFactory.fromResource(R.drawable.start))
         .position(new LatLng(-33.866, 151.195)));		

		 map.addMarker(new MarkerOptions()
         .icon(BitmapDescriptorFactory.fromResource(R.drawable.end))
         .position(new LatLng(37.423, -122.091)));		
                                                 
		
		// Polylines are useful for marking paths and routes on the map.
		map.addPolyline(new PolylineOptions().color(Color.RED).geodesic(true)
				.add(new LatLng(-33.866, 151.195)) // Sydney
				.add(new LatLng(-18.142, 178.431)) // Fiji
				.add(new LatLng(21.291, -157.821)) // Hawaii
				.add(new LatLng(37.423, -122.091)).width(2) // Mountain View
		);
	}

	private void showPolyline() {
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.142,
				178.431), 2));

		// Polylines are useful for marking paths and routes on the map.
		map.addPolyline(new PolylineOptions().color(Color.RED).geodesic(true)
				.add(new LatLng(-33.866, 151.195)) // Sydney
				.add(new LatLng(-18.142, 178.431)) // Fiji
				.add(new LatLng(21.291, -157.821)) // Hawaii
				.add(new LatLng(37.423, -122.091)).width(2) // Mountain View
		);

	}

}
