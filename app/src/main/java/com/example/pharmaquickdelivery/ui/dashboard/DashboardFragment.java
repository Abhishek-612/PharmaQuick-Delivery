package com.example.pharmaquickdelivery.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pharmaquickdelivery.FetchURL;
import com.example.pharmaquickdelivery.MainActivity;
import com.example.pharmaquickdelivery.NavigationActivity;
import com.example.pharmaquickdelivery.R;
import com.example.pharmaquickdelivery.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DashboardFragment extends Fragment implements OnMapReadyCallback, TaskLoadedCallback {

    GoogleMap mGoogleMap;
    Context taskLoadedCallback;
    MapView mMapVIew;
    View root;
    Polyline polyline;
    MarkerOptions place1,place2;


    @Override
    public void onAttach(Context context) {
        this.taskLoadedCallback =  context;
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapVIew = root.findViewById(R.id.map);
        if(mMapVIew!=null){
            mMapVIew.onCreate(null);
            mMapVIew.onResume();
            mMapVIew.getMapAsync(this);
        }


        place1= new MarkerOptions().position((new LatLng(27.658143,85.3199503))).title("Location 1").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        place2= new MarkerOptions().position((new LatLng(27.667491,85.3208583))).title("Location 2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));


        String url = getUrl(place1.getPosition(),place2.getPosition(),"driving");

//        Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();
        new FetchURL(this,getContext()).execute(url,"driving");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mGoogleMap.addMarker(place1);
        mGoogleMap.addMarker(place2);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(27.658143,85.3199503)));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(27.658143,85.3199503), 16.0f));
    }

    private String getUrl(LatLng origin,LatLng dest,String directionMode){
        String src="origin="+origin.latitude+","+origin.longitude;
        String des="destination="+dest.latitude+","+dest.longitude;
        String mode="mode="+directionMode;
        String params=src+"&"+des+"&"+mode;
        String output="json";

        String url="https://maps.googleapis.com/maps/api/directions/"+output+"?"+params+"&key="+getString(R.string.google_maps_key);

        return url;
    }


    @Override
    public void onTaskDone(Object... values) {
        if(polyline!=null)
            polyline.remove();
        polyline= mGoogleMap.addPolyline((PolylineOptions)values[0]);
    }


}