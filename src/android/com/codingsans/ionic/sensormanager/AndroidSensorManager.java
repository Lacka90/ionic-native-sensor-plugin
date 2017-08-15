package com.codingsans.ionic.sensormanager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.Sensor;

import android.widget.Toast;

public class AndroidSensorManager extends CordovaPlugin {
    private CordovaInterface cordova;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private CallbackContext callbackContext;
    private JSONObject data = new JSONObject();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.cordova = cordova;

        mSensorManager = (SensorManager) cordova.getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Toast.makeText((Context)cordova.getActivity(), action, Toast.LENGTH_LONG).show();

        if ("getCurrent".equals(action)) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, this.data);
            callbackContext.sendPluginResult(result);
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    public void onResume(boolean multitasking){
        Toast.makeText((Context)cordova.getActivity(), "onResume", Toast.LENGTH_LONG).show();

        mSensorManager.registerListener(listener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause(boolean multitasking) {
        Toast.makeText((Context)cordova.getActivity(), "onResume", Toast.LENGTH_LONG).show();

        mSensorManager.unregisterListener(listener);
    }

    private SensorEventListener listener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent event) {
            Toast.makeText((Context)cordova.getActivity(), String.valueOf(event.values[0]), Toast.LENGTH_SHORT).show();

            data = new JSONObject();
            try {
                data.put("x", event.values[0]);
                data.put("y", event.values[1]);
                data.put("z", event.values[2]);
            } catch(JSONException e) {}
        }
        
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // unused
        }
    };
}
