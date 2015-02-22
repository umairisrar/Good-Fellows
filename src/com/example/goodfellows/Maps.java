package com.example.goodfellows;

import java.util.ArrayList;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class Maps implements LocationListener{

	 public ArrayList<String> getCoordinates(){
		 ArrayList<String> temp = new ArrayList<String>();
		 temp.add(latituteField);
		 temp.add(longitudeField);
		 return temp;
	 }
	 private String latituteField, longitudeField;
	  
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		 int lat = (int) (location.getLatitude());
		    int lng = (int) (location.getLongitude());
		    latituteField = (String.valueOf(lat));
		    longitudeField = (String.valueOf(lng));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	

	
}
