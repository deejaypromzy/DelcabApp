package delcab.delcab;


import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.GoogleMap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.ArrayList;


public class Global {

    public static void set(Context con, String key, String value){
        con.getSharedPreferences("DELCAB", Context.MODE_PRIVATE).edit()
                .putString(key, value).apply();
    }

    public static String get(Context con, String key){
        return con.getSharedPreferences("DELCAB", Context.MODE_PRIVATE)
                .getString(key, "No Shared Preference");
    }

    /*
    SharedPreferences.Editor editor = getSharedPreferences("DELCAB", MODE_PRIVATE).edit();
    editor.putString("holderName", "Frank");
    editor.apply();

    SharedPreferences getter = getSharedPreferences("DELCAB", MODE_PRIVATE);
    holderName = getter.getString("holderName","");
     */


    public static String internetStatus() throws InterruptedException, IOException {
        String status = "down";
        //this is considered the only accurate way to see is user's internet available
        //this is because they could be connected to WiFi or other network...
        //but that network has no internet access
        //so this provides the true test
        final String command = "ping -c 1 google.com";
        //only wait 0.5 seconds ... how?
        if(Runtime.getRuntime().exec(command).waitFor() == 0) status = "up";

        else status = "down";

        return status;

    }

    public static LatLng midlands(){
        return new LatLng(53.3888024,-7.8011687);
    }

    public static void goTo(GoogleMap theMap, LatLng thePlace, int zoomLevel){
        CameraPosition pos = new CameraPosition.Builder().target(thePlace).zoom(zoomLevel).build();
        theMap.animateCamera(CameraUpdateFactory.newCameraPosition(pos));
    }

    public static void goToTilt(GoogleMap theMap, LatLng thePlace, int zoomLevel) {
        CameraPosition pos = new CameraPosition.Builder()
                .target(thePlace).zoom(zoomLevel).tilt(89).bearing(20).build();
        theMap.animateCamera(CameraUpdateFactory.newCameraPosition(pos));
    }

    public static double round(double value, int places){
        return new BigDecimal(value).setScale(places, RoundingMode.DOWN).doubleValue();
    }


    public static double crowDistance(){
        return 0;
    }


    public static double timeInMins(Time start, Time now){
        return 0;
    }

}
