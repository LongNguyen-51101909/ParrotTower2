package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class Cocos2dxSound
{
  private static final int INVALID_SOUND_ID = -1;
  private static final int INVALID_STREAM_ID = -1;
  private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 5;
  private static final int SOUND_PRIORITY = 1;
  private static final int SOUND_QUALITY = 5;
  private static final float SOUND_RATE = 1.0F;
  private static final String TAG = "Cocos2dxSound";
  private final Context mContext;
  private final ArrayList<SoundInfoForLoadedCompleted> mEffecToPlayWhenLoadedArray = new ArrayList();
  private float mLeftVolume;
  private final HashMap<String, Integer> mPathSoundIDMap = new HashMap();
  private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap();
  private float mRightVolume;
  private Semaphore mSemaphore;
  private SoundPool mSoundPool;
  private int mStreamIdSyn;

  public Cocos2dxSound(Context paramContext)
  {
    this.mContext = paramContext;
    initData();
  }

  private int doPlayEffect(String paramString, int paramInt, boolean paramBoolean)
  {
    Object localObject = this.mSoundPool;
    float f1 = this.mLeftVolume;
    float f2 = this.mRightVolume;
    if (paramBoolean) {}
    for (int i = -1;; i = 0)
    {
      paramInt = ((SoundPool)localObject).play(paramInt, f1, f2, 1, i, 1.0F);
      ArrayList localArrayList = (ArrayList)this.mPathStreamIDsMap.get(paramString);
      localObject = localArrayList;
      if (localArrayList == null)
      {
        localObject = new ArrayList();
        this.mPathStreamIDsMap.put(paramString, localObject);
      }
      ((ArrayList)localObject).add(Integer.valueOf(paramInt));
      return paramInt;
    }
  }

  private void initData()
  {
    this.mSoundPool = new SoundPool(5, 3, 5);
    this.mSoundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
    this.mLeftVolume = 0.5F;
    this.mRightVolume = 0.5F;
    this.mSemaphore = new Semaphore(0, true);
  }

  public int createSoundIDFromAsset(String paramString)
  {
    int i;
    try
    {
      if (paramString.startsWith("/")) {
        i = this.mSoundPool.load(paramString, 0);
      } else {
        i = this.mSoundPool.load(this.mContext.getAssets().openFd(paramString), 0);
      }
    }
    catch (Exception paramString)
    {
      i = -1;
      Log.e("Cocos2dxSound", "error: " + paramString.getMessage(), paramString);
    }
    int j = i;
    if (i == 0) {
      j = -1;
    }
    return j;
  }

  public void end()
  {
    this.mSoundPool.release();
    this.mPathStreamIDsMap.clear();
    this.mPathSoundIDMap.clear();
    this.mEffecToPlayWhenLoadedArray.clear();
    this.mLeftVolume = 0.5F;
    this.mRightVolume = 0.5F;
    initData();
  }

  public float getEffectsVolume()
  {
    return (this.mLeftVolume + this.mRightVolume) / 2.0F;
  }

  public void pauseAllEffects()
  {
    this.mSoundPool.autoPause();
  }

  public void pauseEffect(int paramInt)
  {
    this.mSoundPool.pause(paramInt);
  }

  public int playEffect(String paramString, boolean paramBoolean)
  {
    int i = -1;
    ??? = (Integer)this.mPathSoundIDMap.get(paramString);
    if (??? != null) {
      i = doPlayEffect(paramString, ((Integer)???).intValue(), paramBoolean);
    }
    Integer localInteger;
    do
    {
      return i;
      localInteger = Integer.valueOf(preloadEffect(paramString));
    } while (localInteger.intValue() == -1);
    synchronized (this.mSoundPool)
    {
      this.mEffecToPlayWhenLoadedArray.add(new SoundInfoForLoadedCompleted(paramString, localInteger.intValue(), paramBoolean));
    }
    return -1;
  }

  public int preloadEffect(String paramString)
  {
    Integer localInteger2 = (Integer)this.mPathSoundIDMap.get(paramString);
    Integer localInteger1 = localInteger2;
    if (localInteger2 == null)
    {
      localInteger2 = Integer.valueOf(createSoundIDFromAsset(paramString));
      localInteger1 = localInteger2;
      if (localInteger2.intValue() != -1)
      {
        this.mPathSoundIDMap.put(paramString, localInteger2);
        localInteger1 = localInteger2;
      }
    }
    return localInteger1.intValue();
  }

  public void resumeAllEffects()
  {
    Iterator localIterator1;
    if (!this.mPathStreamIDsMap.isEmpty()) {
      localIterator1 = this.mPathStreamIDsMap.entrySet().iterator();
    }
    for (;;)
    {
      if (!localIterator1.hasNext()) {
        return;
      }
      Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
      while (localIterator2.hasNext())
      {
        int i = ((Integer)localIterator2.next()).intValue();
        this.mSoundPool.resume(i);
      }
    }
  }

  public void resumeEffect(int paramInt)
  {
    this.mSoundPool.resume(paramInt);
  }

  public void setEffectsVolume(float paramFloat)
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
    Iterator localIterator1;
    if (!this.mPathStreamIDsMap.isEmpty()) {
      localIterator1 = this.mPathStreamIDsMap.entrySet().iterator();
    }
    for (;;)
    {
      if (!localIterator1.hasNext()) {
        return;
      }
      Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
      while (localIterator2.hasNext())
      {
        int i = ((Integer)localIterator2.next()).intValue();
        this.mSoundPool.setVolume(i, this.mLeftVolume, this.mRightVolume);
      }
    }
  }

  public void stopAllEffects()
  {
    Iterator localIterator1;
    if (!this.mPathStreamIDsMap.isEmpty()) {
      localIterator1 = this.mPathStreamIDsMap.entrySet().iterator();
    }
    for (;;)
    {
      if (!localIterator1.hasNext())
      {
        this.mPathStreamIDsMap.clear();
        return;
      }
      Iterator localIterator2 = ((ArrayList)((Map.Entry)localIterator1.next()).getValue()).iterator();
      while (localIterator2.hasNext())
      {
        int i = ((Integer)localIterator2.next()).intValue();
        this.mSoundPool.stop(i);
      }
    }
  }

  public void stopEffect(int paramInt)
  {
    this.mSoundPool.stop(paramInt);
    Iterator localIterator = this.mPathStreamIDsMap.keySet().iterator();
    String str;
    do
    {
      if (!localIterator.hasNext()) {
        return;
      }
      str = (String)localIterator.next();
    } while (!((ArrayList)this.mPathStreamIDsMap.get(str)).contains(Integer.valueOf(paramInt)));
    ((ArrayList)this.mPathStreamIDsMap.get(str)).remove(((ArrayList)this.mPathStreamIDsMap.get(str)).indexOf(Integer.valueOf(paramInt)));
  }

  public void unloadEffect(String paramString)
  {
    Object localObject = (ArrayList)this.mPathStreamIDsMap.get(paramString);
    if (localObject != null) {
      localObject = ((ArrayList)localObject).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        this.mPathStreamIDsMap.remove(paramString);
        localObject = (Integer)this.mPathSoundIDMap.get(paramString);
        if (localObject != null)
        {
          this.mSoundPool.unload(((Integer)localObject).intValue());
          this.mPathSoundIDMap.remove(paramString);
        }
        return;
      }
      Integer localInteger = (Integer)((Iterator)localObject).next();
      this.mSoundPool.stop(localInteger.intValue());
    }
  }

  public class OnLoadCompletedListener
    implements OnLoadCompleteListener
  {
    public OnLoadCompletedListener() {}

    public void onLoadComplete(SoundPool paramSoundPool, int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0)
      {
        paramSoundPool = Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.iterator();
        if (paramSoundPool.hasNext()) {}
      }
      for (;;)
      {
        Cocos2dxSound.this.mSemaphore.release();
        return;
        SoundInfoForLoadedCompleted localSoundInfoForLoadedCompleted = (SoundInfoForLoadedCompleted)paramSoundPool.next();
        if (paramInt1 != localSoundInfoForLoadedCompleted.soundID) {
          break;
        }
        Cocos2dxSound.this.mStreamIdSyn = Cocos2dxSound.this.doPlayEffect(localSoundInfoForLoadedCompleted.path, localSoundInfoForLoadedCompleted.soundID, localSoundInfoForLoadedCompleted.isLoop);
        Cocos2dxSound.this.mEffecToPlayWhenLoadedArray.remove(localSoundInfoForLoadedCompleted);
        continue;
        Cocos2dxSound.this.mStreamIdSyn = -1;
      }
    }
  }

  public class SoundInfoForLoadedCompleted
  {
    public boolean isLoop;
    public String path;
    public int soundID;

    public SoundInfoForLoadedCompleted(String paramString, int paramInt, boolean paramBoolean)
    {
      this.path = paramString;
      this.soundID = paramInt;
      this.isLoop = paramBoolean;
    }
  }
}