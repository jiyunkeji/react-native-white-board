package com.reactnativewhiteboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.herewhite.sdk.WhiteboardView;


public class JYWhiteBroadView extends RelativeLayout {

    private boolean visible;
   public WhiteboardView whiteboardView;
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public JYWhiteBroadView(Context context) {
        super(context);
      Log.e("2", "1 JYWhiteBroadView");
          initView(context);
    }

    public JYWhiteBroadView(Context context,  AttributeSet attrs) {
        super(context, attrs);
      Log.e("2", "2 JYWhiteBroadView");
        initView(context);
    }

    private void initView(Context context) {
      Log.e("2", "initView");
        LayoutInflater.from(context).inflate(R.layout.layout_white_board, this, true);
        whiteboardView = (WhiteboardView)findViewById(R.id.white);
      Log.e("2", "end initView");
    }


}
