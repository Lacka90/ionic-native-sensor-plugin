package android.com.codingsans.ionic.sensormanager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;

public class SensorManager extends CordovaPlugin {
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      if ("get".equals(action)) {
          callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, "test message"));
          return true;
      }
      return false;  // Returning false results in a "MethodNotFound" error.
  }
}
