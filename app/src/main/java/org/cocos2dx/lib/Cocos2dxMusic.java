package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.FileInputStream;

public class Cocos2dxMusic
{
  private static final String TAG = Cocos2dxMusic.class.getSimpleName();
  private MediaPlayer mBackgroundMediaPlayer;
  private final Context mContext;
  private String mCurrentPath;
  private float mLeftVolume;
  private boolean mPaused;
  private float mRightVolume;

  public Cocos2dxMusic(Context paramContext)
  {
    this.mContext = paramContext;
    initData();
  }

  private MediaPlayer createMediaplayer(String paramString)
  {
    MediaPlayer localMediaPlayer = new MediaPlayer();
    try
    {
      if (paramString.startsWith("/"))
      {
        paramString = new FileInputStream(paramString);
        localMediaPlayer.setDataSource(paramString.getFD());
        paramString.close();
      }
      for (;;)
      {
        localMediaPlayer.prepare();
        localMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        return localMediaPlayer;
        paramString = this.mContext.getAssets().openFd(paramString);
        localMediaPlayer.setDataSource(paramString.getFileDescriptor(), paramString.getStartOffset(), paramString.getLength());
      }
      return null;
    }
    catch (Exception paramString)
    {
      Log.e(TAG, "error: " + paramString.getMessage(), paramString);
    }
  }

  private void initData()
  {
    this.mLeftVolume = 0.5F;
    this.mRightVolume = 0.5F;
    this.mBackgroundMediaPlayer = null;
    this.mPaused = false;
    this.mCurrentPath = null;
  }

  public void end()
  {
    if (this.mBackgroundMediaPlayer != null) {
      this.mBackgroundMediaPlayer.release();
    }
    initData();
  }

  public float getBackgroundVolume()
  {
    if (this.mBackgroundMediaPlayer != null) {
      return (this.mLeftVolume + this.mRightVolume) / 2.0F;
    }
    return 0.0F;
  }

  public boolean isBackgroundMusicPlaying()
  {
    if (this.mBackgroundMediaPlayer == null) {
      return false;
    }
    return this.mBackgroundMediaPlayer.isPlaying();
  }

  public void pauseBackgroundMusic()
  {
    if ((this.mBackgroundMediaPlayer != null) && (this.mBackgroundMediaPlayer.isPlaying()))
    {
      this.mBackgroundMediaPlayer.pause();
      this.mPaused = true;
    }
  }

  public void playBackgroundMusic(String paramString, boolean paramBoolean)
  {
    if (this.mCurrentPath == null)
    {
      this.mBackgroundMediaPlayer = createMediaplayer(paramString);
      this.mCurrentPath = paramString;
    }
    while (this.mBackgroundMediaPlayer == null)
    {
      Log.e(TAG, "playBackgroundMusic: background media player is null");
      return;
      if (!this.mCurrentPath.equals(paramString))
      {
        if (this.mBackgroundMediaPlayer != null) {
          this.mBackgroundMediaPlayer.release();
        }
        this.mBackgroundMediaPlayer = createMediaplayer(paramString);
        this.mCurrentPath = paramString;
      }
    }
    this.mBackgroundMediaPlayer.stop();
    this.mBackgroundMediaPlayer.setLooping(paramBoolean);
    try
    {
      this.mBackgroundMediaPlayer.prepare();
      this.mBackgroundMediaPlayer.seekTo(0);
      this.mBackgroundMediaPlayer.start();
      this.mPaused = false;
      return;
    }
    catch (Exception paramString)
    {
      Log.e(TAG, "playBackgroundMusic: error state");
    }
  }

  public void preloadBackgroundMusic(String paramString)
  {
    if ((this.mCurrentPath == null) || (!this.mCurrentPath.equals(paramString)))
    {
      if (this.mBackgroundMediaPlayer != null) {
        this.mBackgroundMediaPlayer.release();
      }
      this.mBackgroundMediaPlayer = createMediaplayer(paramString);
      this.mCurrentPath = paramString;
    }
  }

  public void resumeBackgroundMusic()
  {
    if ((this.mBackgroundMediaPlayer != null) && (this.mPaused))
    {
      this.mBackgroundMediaPlayer.start();
      this.mPaused = false;
    }
  }

  public void rewindBackgroundMusic()
  {
    if (this.mBackgroundMediaPlayer != null) {
      this.mBackgroundMediaPlayer.stop();
    }
    try
    {
      this.mBackgroundMediaPlayer.prepare();
      this.mBackgroundMediaPlayer.seekTo(0);
      this.mBackgroundMediaPlayer.start();
      this.mPaused = false;
      return;
    }
    catch (Exception localException)
    {
      Log.e(TAG, "rewindBackgroundMusic: error state");
    }
  }

  public void setBackgroundVolume(float paramFloat)
  {
    float f = paramFloat;
    if (paramFloat < 0.0F) {
      f = 0.0F;
    }
    paramFloat = f;
    if (f > 1.0F) {
      paramFloat = 1.0F;
    }
    this.mRightVolume = paramFloat;
    this.mLeftVolume = paramFloat;
    if (this.mBackgroundMediaPlayer != null) {
      this.mBackgroundMediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
    }
  }

  public void stopBackgroundMusic()
  {
    if (this.mBackgroundMediaPlayer != null)
    {
      this.mBackgroundMediaPlayer.stop();
      this.mPaused = false;
    }
  }
}