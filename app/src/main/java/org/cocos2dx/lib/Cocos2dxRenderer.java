package org.cocos2dx.lib;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Cocos2dxRenderer
  implements GLSurfaceView.Renderer
{
  private static final long NANOSECONDSPERMICROSECOND = 1000000L;
  private static final long NANOSECONDSPERSECOND = 1000000000L;
  private static long sAnimationInterval = 16666666L;
  private long mLastTickInNanoSeconds;
  private int mScreenHeight;
  private int mScreenWidth;

//  private static native void nativeDeleteBackward();
//
//  private static native String nativeGetContentText();
//
//  private static native void nativeInit(int paramInt1, int paramInt2);
//
//  private static native void nativeInsertText(String paramString);
//
//  private static native boolean nativeKeyDown(int paramInt);
//
//  private static native void nativeOnPause();
//
//  private static native void nativeOnResume();
//
//  private static native void nativeRender();
//
//  private static native void nativeTouchesBegin(int paramInt, float paramFloat1, float paramFloat2);
//
//  private static native void nativeTouchesCancel(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);
//
//  private static native void nativeTouchesEnd(int paramInt, float paramFloat1, float paramFloat2);
//
//  private static native void nativeTouchesMove(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);

  public static void setAnimationInterval(double paramDouble)
  {
    sAnimationInterval = (1.0E9D * paramDouble);
  }

  public String getContentText()
  {
    return nativeGetContentText();
  }

  public void handleActionCancel(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    nativeTouchesCancel(paramArrayOfInt, paramArrayOfFloat1, paramArrayOfFloat2);
  }

  public void handleActionDown(int paramInt, float paramFloat1, float paramFloat2)
  {
    nativeTouchesBegin(paramInt, paramFloat1, paramFloat2);
  }

  public void handleActionMove(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    nativeTouchesMove(paramArrayOfInt, paramArrayOfFloat1, paramArrayOfFloat2);
  }

  public void handleActionUp(int paramInt, float paramFloat1, float paramFloat2)
  {
    nativeTouchesEnd(paramInt, paramFloat1, paramFloat2);
  }

  public void handleDeleteBackward() {}

  public void handleInsertText(String paramString)
  {
    nativeInsertText(paramString);
  }

  public void handleKeyDown(int paramInt)
  {
    nativeKeyDown(paramInt);
  }

  public void handleOnPause() {}

  public void handleOnResume() {}

  public void onDrawFrame(GL10 paramGL10) {}

  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2) {}

  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    nativeInit(this.mScreenWidth, this.mScreenHeight);
    this.mLastTickInNanoSeconds = System.nanoTime();
  }

  public void setScreenWidthAndHeight(int paramInt1, int paramInt2)
  {
    this.mScreenWidth = paramInt1;
    this.mScreenHeight = paramInt2;
  }
}