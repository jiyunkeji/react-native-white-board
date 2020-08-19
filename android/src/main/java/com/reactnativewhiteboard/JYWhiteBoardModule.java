package com.reactnativewhiteboard;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.util.HashMap;
import java.util.Map;

public class JYWhiteBoardModule extends ReactContextBaseJavaModule {

    ReactApplicationContext context;

    public   static  final  String Key_Pref = "JYWB";
  public   static  final  String Value_Pref = "com.white.board";
    public JYWhiteBoardModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "JYWhiteBoardModule";
    }
     @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(Key_Pref, Value_Pref);
    return constants;
  }
    @ReactMethod
    public void init(ReadableMap options, Promise promise) {
        try {
            String appId = options.getString("appId");//ehuvwNLlEeqTIve5DWs2Gg/KheA3hZvWA8XEA
            JYWhiteBoardManager.getInstance().init(this.context,options);
            promise.resolve(appId);
        } catch (Exception exception) {
            promise.reject(exception);
        }
    }

  @ReactMethod
  public void JoinRoom(ReadableMap options, Promise promise) {
    try {
      String roomId = options.getString("roomId");
      String roomToken = options.getString("roomToken");
      JYWhiteBoardManager.getInstance().joinRoom(options);
      promise.resolve(roomId+ "-----" +roomToken);
    } catch (Exception exception) {
      promise.reject(exception);
    }
  }


}
