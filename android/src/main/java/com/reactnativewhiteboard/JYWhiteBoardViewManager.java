package com.reactnativewhiteboard;

import android.graphics.Color;
import android.util.Log;
import android.widget.CheckBox;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
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

import java.util.Date;

public class JYWhiteBoardViewManager extends SimpleViewManager<JYWhiteBroadView> {

    public static final String REACT_CLASS = "JYWhiteBoardView";
    public ThemedReactContext context;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public JYWhiteBroadView createViewInstance(ThemedReactContext c) {
        this.context = c;
        JYWhiteBroadView boardView = new JYWhiteBroadView(c);
        JYWhiteBoardManager.getInstance().setBoardView(boardView.whiteboardView);
        return boardView;
//        CheckBox cb = new CheckBox(c);
//        cb.setChecked(true);
//        return  cb;
    }

      @ReactProp(name = "strokeColorRGB")
    public void setRGB(final JYWhiteBroadView whiteBroadView, ReadableMap rgb) {
        JYWhiteBoardManager.getInstance().setStrokeColor(rgb);
    }




}
