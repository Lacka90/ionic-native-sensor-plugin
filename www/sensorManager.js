var DeviceSensorLoader = function(require, exports, module) {
  var exec = require('cordova/exec');
  
  function DeviceSensor() {}
      
  DeviceSensor.prototype.get = function(success, failure) {
      exec(success, failure, 'SensorManager', 'get', []);
  };
  
  var deviceSensor = new DeviceSensor();
  module.exports = deviceSensor;
};

DeviceSensorLoader(require, exports, module);

cordova.define("cordova/plugin/DeviceSensor", DeviceSensorLoader);
