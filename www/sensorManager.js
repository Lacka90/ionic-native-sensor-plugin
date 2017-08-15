var DeviceSensorLoader = function(require, exports, module) {
  var exec = require('cordova/exec');

  var intervalId;
  
  function DeviceSensor() {}
      
  DeviceSensor.prototype.get = function(success, failure) {
      exec(success, failure, 'AndroidSensorManager', 'get', []);
  };

  DeviceSensor.prototype.watch = function(success, failure) {
    intervalId = setInterval(function() {
      exec(success, failure, 'AndroidSensorManager', 'get', []);
    }, 500);
  };

  DeviceSensor.prototype.clear = function(success, failure) {
    if (intervalId) {
      clearInterval(intervalId);
    }
  };
  
  var deviceSensor = new DeviceSensor();
  module.exports = deviceSensor;
};

DeviceSensorLoader(require, exports, module);

cordova.define("cordova/plugin/DeviceSensor", DeviceSensorLoader);
