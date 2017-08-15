var DeviceSensorLoader = function(require, exports, module) {
  var exec = require('cordova/exec');

  var intervalId;
  
  function DeviceSensor() {}

  DeviceSensor.prototype.initialize = function(success, failure) {
    exec(success, failure, 'AndroidSensorManager', 'initialize', []);
  };
      
  DeviceSensor.prototype.finish = function(success, failure) {
      exec(success, failure, 'AndroidSensorManager', 'finish', []);
  };

  DeviceSensor.prototype.watch = function(success, failure, timeOffset) {
    intervalId = setInterval(function() {
      exec(success, failure, 'AndroidSensorManager', 'getCurrent', []);
    }, timeOffset || 500);
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
