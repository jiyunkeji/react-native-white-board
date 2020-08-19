package com.reactnativewhiteboard;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.herewhite.sdk.AbstractRoomCallbacks;
import com.herewhite.sdk.CommonCallbacks;
import com.herewhite.sdk.Room;
import com.herewhite.sdk.RoomParams;
import com.herewhite.sdk.WhiteSdk;
import com.herewhite.sdk.WhiteSdkConfiguration;
import com.herewhite.sdk.WhiteboardView;
import com.herewhite.sdk.domain.MemberState;
import com.herewhite.sdk.domain.Promise;
import com.herewhite.sdk.domain.RoomPhase;
import com.herewhite.sdk.domain.RoomState;
import com.herewhite.sdk.domain.SDKError;
import com.herewhite.sdk.domain.UrlInterrupter;

public class JYWhiteBoardManager {
    private static JYWhiteBoardManager _instance;
    private  WhiteboardView boardView;

   private ReactApplicationContext context;

   private  WhiteSdk whiteSdk;
  private  Room room;
   private ReadableMap rgb;

  public ReadableMap getRgb() {
    return rgb;
  }

  public void setRgb(ReadableMap rgb) {
    this.rgb = rgb;
  }

  public WhiteboardView getBoardView() {
        return boardView;
    }

    public void setBoardView(WhiteboardView boardView) {
        this.boardView = boardView;
    }

    public static JYWhiteBoardManager getInstance() {

        if (_instance == null) {
            synchronized (JYWhiteBoardManager.class) {
                if (_instance == null) {
                    _instance = new JYWhiteBoardManager();
                }
            }
        }
        return _instance;
    }

    private JYWhiteBoardManager() {
    }
  private void emit(String name, WritableMap params) {
    this.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(JYWhiteBoardModule.Value_Pref + name,params);
     Log.e("2","emit name" +JYWhiteBoardModule.Value_Pref + name);
  }


    public void init(ReactApplicationContext context, ReadableMap options) {
//          whiteBroadView.addView(whiteboardView);
      Log.e("2","boardView"+getBoardView());
        this.context = context;
        String appId = options.getString("appId");//ehuvwNLlEeqTIve5DWs2Gg/KheA3hZvWA8XEA

        WhiteSdkConfiguration sdkConfiguration = new WhiteSdkConfiguration(appId, true);

        /*显示用户头像*/
        sdkConfiguration.setUserCursor(true);

        //动态 ppt 需要的自定义字体，如果没有使用，无需调用
//          HashMap<String, String> map = new HashMap<>();
//          map.put("宋体","https://your-cdn.com/Songti.ttf");
//          sdkConfiguration.setFonts(map);

        //图片替换 API，需要在 whiteSDKConfig 中先行调用 setHasUrlInterrupterAPI，进行设置，否则不会被回调。
         whiteSdk = new WhiteSdk(getBoardView(), context, sdkConfiguration,
                new UrlInterrupter() {
                    @Override
                    public String urlInterrupter(String sourceUrl) {
                        return sourceUrl;
                    }
                });

        /** 设置自定义全局状态，在后续回调中 GlobalState 直接进行类型转换即可 */
//          WhiteDisplayerState.setCustomGlobalStateClass(this.context);

        whiteSdk.setCommonCallbacks(new CommonCallbacks() {
            @Override
            public String urlInterrupter(String sourceUrl) {
                return sourceUrl;
            }

            @Override
            public void sdkSetupFail(SDKError error) {
                Log.e("ROOM_ERROR", error.toString());
            }

            @Override
            public void throwError(Object args) {

            }

            @Override
            public void onPPTMediaPlay() {

            }

            @Override
            public void onPPTMediaPause() {
            }
        });


    }

    public  void joinRoom(ReadableMap options){
      //如需支持用户头像，请在设置 WhiteSdkConfiguration 后，再调用 setUserPayload 方法，传入符合用户信息
      String roomId = options.getString("roomId");
      String roomToken = options.getString("roomToken");
      RoomParams roomParams = new RoomParams(roomId, roomToken);
 Log.e("2","joinRoom");
      whiteSdk.joinRoom(roomParams, new AbstractRoomCallbacks() {
        @Override
        public void onPhaseChanged(RoomPhase phase) {
          //在此处可以处理断连后的重连逻辑
//                  showToast(phase.name());
          Log.e("2",phase.name());

        }

        @Override
        public void onRoomStateChanged(RoomState modifyState) {
          Log.e("2",modifyState.toJSON().toString());
          WritableMap params = Arguments.createMap();
//          params.putString("memberState", modifyState.getMemberState().toJSON().toString());
//          params.putString("broadcastState", modifyState.getBroadcastState().toJSON().toString());
          params.putString("zoomScale", "111");
          emit("onRoomStateChanged",params);

        }
      }, new Promise<Room>() {
        @Override
        public void then(Room wRoom) {
          room = wRoom;
          if (getRgb()!=null){
            setStrokeColor(getRgb());
          }
        }

        @Override
        public void catchEx(SDKError t) {
          Log.e("2", t.getMessage());
        }
      });
    }

    public  void  setStrokeColor(ReadableMap rgb){
     setRgb(rgb);
      if (room==null){
        return;
      }
      int r = rgb.getInt("r");//ehuvwNLlEeqTIve5DWs2Gg/KheA3hZvWA8XEA
      int g = rgb.getInt("g");
      int b = rgb.getInt("b");
      Log.e("2",r +",,,,"+g+",,,,"+b);
      MemberState memberState = new MemberState();
      memberState.setStrokeColor(new int[]{r, g, b});
      room.setMemberState(memberState);
    }
}
