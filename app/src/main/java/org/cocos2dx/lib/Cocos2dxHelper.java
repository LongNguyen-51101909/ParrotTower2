package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class Cocos2dxHelper
{
  private static final String PREFS_NAME = "Cocos2dxPrefsFile";
  private static boolean sAccelerometerEnabled;
  private static AssetManager sAssetManager;
  private static Cocos2dxMusic sCocos2dMusic;
  private static Cocos2dxSound sCocos2dSound;
  private static Cocos2dxAccelerometer sCocos2dxAccelerometer;
  private static Cocos2dxHelperListener sCocos2dxHelperListener;
  private static Context sContext = null;
  private static String sFileDirectory;
  private static String sPackageName;

  public static void disableAccelerometer()
  {
    sAccelerometerEnabled = false;
    sCocos2dxAccelerometer.disable();
  }

  public static void enableAccelerometer()
  {
    sAccelerometerEnabled = true;
    sCocos2dxAccelerometer.enable();
  }

  public static void end()
  {
    sCocos2dMusic.end();
    sCocos2dSound.end();
  }

  public static AssetManager getAssetManager()
  {
    return sAssetManager;
  }

  public static float getBackgroundMusicVolume()
  {
    return sCocos2dMusic.getBackgroundVolume();
  }

  public static boolean getBoolForKey(String paramString, boolean paramBoolean)
  {
    return ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).getBoolean(paramString, paramBoolean);
  }

  public static String getCocos2dxPackageName()
  {
    return sPackageName;
  }

  public static String getCocos2dxWritablePath()
  {
    return sFileDirectory;
  }

  public static String getCurrentLanguage()
  {
    return Locale.getDefault().getLanguage();
  }

  public static int getDPI()
  {
    if (sContext != null)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      Object localObject = ((Activity)sContext).getWindowManager();
      if (localObject != null)
      {
        localObject = ((WindowManager)localObject).getDefaultDisplay();
        if (localObject != null)
        {
          ((Display)localObject).getMetrics(localDisplayMetrics);
          return (int)(localDisplayMetrics.density * 160.0F);
        }
      }
    }
    return -1;
  }

  public static String getDeviceModel()
  {
    return Build.MODEL;
  }

  public static double getDoubleForKey(String paramString, double paramDouble)
  {
    return ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).getFloat(paramString, (float)paramDouble);
  }

  public static float getEffectsVolume()
  {
    return sCocos2dSound.getEffectsVolume();
  }

  public static float getFloatForKey(String paramString, float paramFloat)
  {
    return ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).getFloat(paramString, paramFloat);
  }

  public static int getIntegerForKey(String paramString, int paramInt)
  {
    return ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).getInt(paramString, paramInt);
  }

  public static String getStringForKey(String paramString1, String paramString2)
  {
    return ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).getString(paramString1, paramString2);
  }

  public static void init(Context paramContext, Cocos2dxHelperListener paramCocos2dxHelperListener)
  {
    ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
    sContext = paramContext;
    sCocos2dxHelperListener = paramCocos2dxHelperListener;
    sPackageName = localApplicationInfo.packageName;
    sFileDirectory = paramContext.getFilesDir().getAbsolutePath();
    nativeSetApkPath(localApplicationInfo.sourceDir);
    sCocos2dxAccelerometer = new Cocos2dxAccelerometer(paramContext);
    sCocos2dMusic = new Cocos2dxMusic(paramContext);
    sCocos2dSound = new Cocos2dxSound(paramContext);
    sAssetManager = paramContext.getAssets();
    Cocos2dxBitmap.setContext(paramContext);
    Cocos2dxETCLoader.setContext(paramContext);
  }

  public static boolean isBackgroundMusicPlaying()
  {
    return sCocos2dMusic.isBackgroundMusicPlaying();
  }

  private static native void nativeSetApkPath(String paramString);

  private static native void nativeSetEditTextDialogResult(byte[] paramArrayOfByte);

  public static void onPause()
  {
    if (sAccelerometerEnabled) {
      sCocos2dxAccelerometer.disable();
    }
  }

  public static void onResume()
  {
    if (sAccelerometerEnabled) {
      sCocos2dxAccelerometer.enable();
    }
  }

  public static void pauseAllEffects()
  {
    sCocos2dSound.pauseAllEffects();
  }

  public static void pauseBackgroundMusic()
  {
    sCocos2dMusic.pauseBackgroundMusic();
  }

  public static void pauseEffect(int paramInt)
  {
    sCocos2dSound.pauseEffect(paramInt);
  }

  public static void playBackgroundMusic(String paramString, boolean paramBoolean)
  {
    sCocos2dMusic.playBackgroundMusic(paramString, paramBoolean);
  }

  public static int playEffect(String paramString, boolean paramBoolean)
  {
    return sCocos2dSound.playEffect(paramString, paramBoolean);
  }

  public static void preloadBackgroundMusic(String paramString)
  {
    sCocos2dMusic.preloadBackgroundMusic(paramString);
  }

  public static void preloadEffect(String paramString)
  {
    sCocos2dSound.preloadEffect(paramString);
  }

  public static void resumeAllEffects()
  {
    sCocos2dSound.resumeAllEffects();
  }

  public static void resumeBackgroundMusic()
  {
    sCocos2dMusic.resumeBackgroundMusic();
  }

  public static void resumeEffect(int paramInt)
  {
    sCocos2dSound.resumeEffect(paramInt);
  }

  public static void rewindBackgroundMusic()
  {
    sCocos2dMusic.rewindBackgroundMusic();
  }

  public static void setAccelerometerInterval(float paramFloat)
  {
    sCocos2dxAccelerometer.setInterval(paramFloat);
  }

  public static void setBackgroundMusicVolume(float paramFloat)
  {
    sCocos2dMusic.setBackgroundVolume(paramFloat);
  }

  public static void setBoolForKey(String paramString, boolean paramBoolean)
  {
    Editor localEditor = ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }

  public static void setDoubleForKey(String paramString, double paramDouble)
  {
    Editor localEditor = ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putFloat(paramString, (float)paramDouble);
    localEditor.commit();
  }

  public static void setEditTextDialogResult(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF8");
      sCocos2dxHelperListener.runOnGLThread(new Runnable()
      {
        public void run()
        {
          Cocos2dxHelper.nativeSetEditTextDialogResult(Cocos2dxHelper.this);
        }
      });
      return;
    }
    catch (UnsupportedEncodingException paramString) {}
  }

  public static void setEffectsVolume(float paramFloat)
  {
    sCocos2dSound.setEffectsVolume(paramFloat);
  }

  public static void setFloatForKey(String paramString, float paramFloat)
  {
    Editor localEditor = ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putFloat(paramString, paramFloat);
    localEditor.commit();
  }

  public static void setIntegerForKey(String paramString, int paramInt)
  {
    Editor localEditor = ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public static void setStringForKey(String paramString1, String paramString2)
  {
    Editor localEditor = ((Activity)sContext).getSharedPreferences("Cocos2dxPrefsFile", 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  private static void showDialog(String paramString1, String paramString2)
  {
    sCocos2dxHelperListener.showDialog(paramString1, paramString2);
  }

  private static void showEditTextDialog(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    sCocos2dxHelperListener.showEditTextDialog(paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public static void stopAllEffects()
  {
    sCocos2dSound.stopAllEffects();
  }

  public static void stopBackgroundMusic()
  {
    sCocos2dMusic.stopBackgroundMusic();
  }

  public static void stopEffect(int paramInt)
  {
    sCocos2dSound.stopEffect(paramInt);
  }

  public static void terminateProcess()
  {
    Process.killProcess(Process.myPid());
  }

  public static void unloadEffect(String paramString)
  {
    sCocos2dSound.unloadEffect(paramString);
  }

  public static abstract interface Cocos2dxHelperListener
  {
    public abstract void runOnGLThread(Runnable paramRunnable);

    public abstract void showDialog(String paramString1, String paramString2);

    public abstract void showEditTextDialog(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}