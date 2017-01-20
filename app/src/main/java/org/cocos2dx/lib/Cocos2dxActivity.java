package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public abstract class Cocos2dxActivity
        extends Activity
        /*implements Cocos2dxHelper.Cocos2dxHelperListene*/ {
  private static final String TAG = Cocos2dxActivity.class.getSimpleName();
  private static Context sContext = null;
//  private Cocos2dxGLSurfaceView mGLSurfaceView;
//  private Cocos2dxHandler mHandler;

  public static Context getContext() {
    return sContext;
  }

  private static final boolean isAndroidEmulator() {
    String str = Build.MODEL;
    Log.d(TAG, "model=" + str);
    str = Build.PRODUCT;
    Log.d(TAG, "product=" + str);
//    boolean bool = false;
//    if (str != null) {
//      if ((str.equals("sdk")) || (str.contains("_sdk")) || (str.contains("sdk_"))) {
//        break label114;
//      }
//    }
//    label114:
//    for (bool = false; ; bool = true) {
//      Log.d(TAG, "isEmulator=" + bool);
//      return bool;
//    }
    return false;
  }

  public void init() {
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    FrameLayout localFrameLayout = new FrameLayout(this);
    localFrameLayout.setLayoutParams(localLayoutParams);
    localLayoutParams = new ViewGroup.LayoutParams(-1, -2);
    Cocos2dxEditText localCocos2dxEditText = new Cocos2dxEditText(this);
    localCocos2dxEditText.setLayoutParams(localLayoutParams);
    localFrameLayout.addView(localCocos2dxEditText);
    this.mGLSurfaceView = onCreateView();
    localFrameLayout.addView(this.mGLSurfaceView);
    if (isAndroidEmulator()) {
      this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
    }
    this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
    this.mGLSurfaceView.setCocos2dxEditText(localCocos2dxEditText);
    setContentView(localFrameLayout);
  }

  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    sContext = this;
    this.mHandler = new Cocos2dxHandler(this);
    init();
    Cocos2dxHelper.init(this, this);
  }

  public Cocos2dxGLSurfaceView onCreateView() {
    return new Cocos2dxGLSurfaceView(this);
  }

  protected void onPause() {
    super.onPause();
    Cocos2dxHelper.onPause();
    this.mGLSurfaceView.onPause();
  }

  protected void onResume() {
    super.onResume();
    Cocos2dxHelper.onResume();
    this.mGLSurfaceView.onResume();
  }

  public void runOnGLThread(Runnable paramRunnable) {
    this.mGLSurfaceView.queueEvent(paramRunnable);
  }

  public void showDialog(String paramString1, String paramString2) {
    Message localMessage = new Message();
    localMessage.what = 1;
    localMessage.obj = new Cocos2dxHandler.DialogMessage(paramString1, paramString2);
    this.mHandler.sendMessage(localMessage);
  }

  public void showEditTextDialog(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.obj = new Cocos2dxHandler.EditBoxMessage(paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mHandler.sendMessage(localMessage);
  }
}