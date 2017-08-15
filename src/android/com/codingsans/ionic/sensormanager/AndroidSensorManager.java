package com.codingsans.ionic.sensormanager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

import android.hardware.SensorManager;
import android.hardware.Sensor;

public class AndroidSensorManager extends CordovaPlugin {
    private CordovaInterface cordova;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private CallbackContext callbackContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.cordova = cordova;

        mSensorManager = (SensorManager) cordova.getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("get".equals(action)) {
            this.callbackContext = callbackContext;
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    @Override
    public void onResume(){
        mSensorManager.registerListener(this.cordova.getActivity(), mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mSensor);
    }

    public void onSensorChanged(SensorEvent event) {
        if (this.callbackContext != null) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, event.values[0]);
            this.callbackContext.sendPluginResult(result);
        }
    }
}
