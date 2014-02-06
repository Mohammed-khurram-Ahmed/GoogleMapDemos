package com.example.googlemapapi;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class AdvancePlyLineActivity extends Activity {

	private int width, height;
	GoogleMap googleMap;
	MarkerOptions marker;
	Context context;

	ArrayList<MyMarker> myMarkers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advance_ply_line);

		getSreenDimanstions();
		MapFragment mapFragment = (MapFragment) getFragmentManager()
				.findFragmentById(R.id.map);
		googleMap = mapFragment.getMap();
		googleMap.setMyLocationEnabled(true);
		webServiceCall();
	}

	private void drawRouteOnMap() {
		LatLngBounds.Builder builder = new LatLngBounds.Builder();
		PolylineOptions polylineOptions = new PolylineOptions();
		polylineOptions.geodesic(true);
		polylineOptions.width(5);
		PolylineOptions polylineOptions2 = new PolylineOptions();
		polylineOptions2.geodesic(true);
		polylineOptions2.width(5);
		// You can customize the marker image using images bundled with
		// your app, or dynamically generated bitmaps.
		for (int i = 0; i < myMarkers.size(); i++) {
			
			MyMarker markerItem = myMarkers.get(i);
			
			marker = new MarkerOptions();
			if (i == 0) {
				marker.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.startflag));
				polylineOptions.color(Color.BLUE);
				polylineOptions.geodesic(true).add(
						new LatLng(markerItem.longitude, markerItem.longitude));
				googleMap.addPolyline(polylineOptions);
				
				// Anchors the marker on the bottom left
				marker.position(new LatLng(markerItem.longitude,
						markerItem.longitude));
				marker.title("Source : "+markerItem.title);
				marker.snippet(markerItem.description);
				googleMap.addMarker(marker);
				
			} else if (i == myMarkers.size() - 2) {
				polylineOptions.color(Color.BLUE);
				polylineOptions.geodesic(true).add(
						new LatLng(markerItem.longitude, markerItem.longitude));
				googleMap.addPolyline(polylineOptions);
				marker.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.currentloc));
				polylineOptions2.color(Color.RED);
				polylineOptions2.geodesic(true).add(
						new LatLng(markerItem.longitude, markerItem.longitude));
				
				googleMap.addPolyline(polylineOptions2);
				// Anchors the marker on the bottom left
				marker.position(new LatLng(markerItem.longitude,
						markerItem.longitude));
				marker.title("Current Pos:"+markerItem.title);
				marker.snippet(markerItem.description);
				googleMap.addMarker(marker);
				
				
			} else if (i == myMarkers.size() - 1) {
				marker.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.endflag));
				polylineOptions2.color(Color.RED);
				polylineOptions2.geodesic(true).add(
						new LatLng(markerItem.longitude, markerItem.longitude));
				googleMap.addPolyline(polylineOptions2);
				
				// Anchors the marker on the bottom left
				marker.position(new LatLng(markerItem.longitude,
						markerItem.longitude));
				marker.title("Destinition : "+markerItem.title);
				marker.snippet(markerItem.description);
				googleMap.addMarker(marker);
				
				
			} else {
				marker.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.myvisits));
				polylineOptions.color(Color.BLUE);
				polylineOptions.geodesic(true).add(
						new LatLng(markerItem.longitude, markerItem.longitude));
				googleMap.addPolyline(polylineOptions);
				
				
				// Anchors the marker on the bottom left
				marker.position(new LatLng(markerItem.longitude,
						markerItem.longitude));
				marker.title("Visits : "+markerItem.title);
				marker.snippet(markerItem.description);
				googleMap.addMarker(marker);
			}
			
			builder.include(new LatLng(markerItem.longitude,
					markerItem.longitude));
		}
		// Create Bound for camera using created builder
		LatLngBounds bounds = builder.build();
		// now move camera to specif bound
		googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,
				width, height, 60));
	}

	private void webServiceCall() {
		myMarkers = new ArrayList<MyMarker>();
		for (int i = 0; i <= 10; i++) {
			MyMarker marker = new MyMarker();
			
			marker.title = "Name" + i;
			marker.description = "Description" + i;
			marker.iconRes = R.drawable.start;
			marker.latitude = 73.0000 + i;
			marker.longitude = 18.0000+1 ;
				
			
			myMarkers.add(marker);
			
		}
		drawRouteOnMap();

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void getSreenDimanstions() {
		Display display = getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
	}

}
