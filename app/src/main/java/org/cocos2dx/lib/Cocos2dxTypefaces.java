package org.cocos2dx.lib;

import android.graphics.Typeface;
import java.util.HashMap;

public class Cocos2dxTypefaces
{
  private static final HashMap<String, Typeface> sTypefaceCache = new HashMap();

  /* Error */
  public static Typeface get(android.content.Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 16	org/cocos2dx/lib/Cocos2dxTypefaces:sTypefaceCache	Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual 24	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifne +26 -> 36
       13: aload_1
    //   14: ldc 26
    //   16: invokevirtual 32	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   19: ifeq +33 -> 52
    //   22: aload_1
    //   23: invokestatic 38	android/graphics/Typeface:createFromFile	(Ljava/lang/String;)Landroid/graphics/Typeface;
    //   26: astore_0
    //   27: getstatic 16	org/cocos2dx/lib/Cocos2dxTypefaces:sTypefaceCache	Ljava/util/HashMap;
    //   30: aload_1
    //   31: aload_0
    //   32: invokevirtual 42	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   35: pop
    //   36: getstatic 16	org/cocos2dx/lib/Cocos2dxTypefaces:sTypefaceCache	Ljava/util/HashMap;
    //   39: aload_1
    //   40: invokevirtual 45	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   43: checkcast 34	android/graphics/Typeface
    //   46: astore_0
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload_0
    //   51: areturn
    //   52: aload_0
    //   53: invokevirtual 51	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   56: aload_1
    //   57: invokestatic 55	android/graphics/Typeface:createFromAsset	(Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;
    //   60: astore_0
    //   61: goto -34 -> 27
    //   64: astore_0
    //   65: ldc 2
    //   67: monitorexit
    //   68: aload_0
    //   69: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	70	0	paramContext	android.content.Context
    //   0	70	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   3	27	64	finally
    //   27	36	64	finally
    //   36	47	64	finally
    //   52	61	64	finally
  }
}