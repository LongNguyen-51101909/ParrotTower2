package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.ETC1Util;
import android.opengl.ETC1Util.ETC1Texture;
import android.util.Log;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Cocos2dxETCLoader
{
  private static final String ASSETS_PATH = "assets/";
  private static Context context;

  public static boolean loadTexture(String paramString)
  {
    if (!ETC1Util.isETC1Supported()) {}
    while (paramString.length() == 0) {
      return false;
    }
    localObject2 = paramString;
    for (;;)
    {
      try
      {
        if (paramString.charAt(0) != '/') {
          continue;
        }
        localObject2 = paramString;
        localObject3 = new FileInputStream(paramString);
        localObject2 = paramString;
        localObject1 = ETC1Util.createTexture((InputStream)localObject3);
        localObject2 = paramString;
        ((InputStream)localObject3).close();
        paramString = (String)localObject1;
      }
      catch (Exception paramString)
      {
        Object localObject3;
        Object localObject1;
        Log.d("Cocos2dx", "Unable to create texture for " + (String)localObject2);
        paramString = null;
        continue;
      }
      if (paramString == null) {
        break;
      }
      try
      {
        int i = paramString.getWidth();
        int j = paramString.getHeight();
        int k = paramString.getData().remaining();
        localObject1 = new byte[k];
        localObject2 = ByteBuffer.wrap((byte[])localObject1);
        ((ByteBuffer)localObject2).order(ByteOrder.nativeOrder());
        ((ByteBuffer)localObject2).put(paramString.getData());
        nativeSetTextureInfo(i, j, (byte[])localObject1, k);
        return true;
      }
      catch (Exception paramString)
      {
        Log.d("invoke native function error", paramString.toString());
        return false;
      }
      localObject1 = paramString;
      localObject2 = paramString;
      if (paramString.startsWith("assets/"))
      {
        localObject2 = paramString;
        localObject1 = paramString.substring("assets/".length());
      }
      localObject2 = localObject1;
      localObject3 = context.getAssets().open((String)localObject1);
      paramString = (String)localObject1;
    }
  }

  private static native void nativeSetTextureInfo(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3);

  public static void setContext(Context paramContext)
  {
    context = paramContext;
  }
}
