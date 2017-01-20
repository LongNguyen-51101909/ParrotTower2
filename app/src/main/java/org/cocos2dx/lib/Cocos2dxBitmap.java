package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.FloatMath;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;

public class Cocos2dxBitmap
{
  private static final int HORIZONTALALIGN_CENTER = 3;
  private static final int HORIZONTALALIGN_LEFT = 1;
  private static final int HORIZONTALALIGN_RIGHT = 2;
  private static final int VERTICALALIGN_BOTTOM = 2;
  private static final int VERTICALALIGN_CENTER = 3;
  private static final int VERTICALALIGN_TOP = 1;
  private static Context sContext;

  private static TextProperty computeTextProperty(String paramString, int paramInt1, int paramInt2, Paint paramPaint)
  {
    FontMetricsInt localFontMetricsInt = paramPaint.getFontMetricsInt();
    int k = (int)Math.ceil(localFontMetricsInt.bottom - localFontMetricsInt.top);
    int i = 0;
    paramString = splitString(paramString, paramInt1, paramInt2, paramPaint)[0];
    if (paramInt1 != 0)
    {
      i = paramInt1;
      return new TextProperty(i, k, paramString.split(""));
    }
    int m = paramString.length();
    paramInt2 = 0;
    for (paramInt1 = i;; paramInt1 = i)
    {
      i = paramInt1;
      if (paramInt2 >= m) {
        break;
      }
      localFontMetricsInt = paramString[paramInt2];
      int j = (int)FloatMath.ceil(paramPaint.measureText(localFontMetricsInt, 0, localFontMetricsInt.length()));
      i = paramInt1;
      if (j > paramInt1) {
        i = j;
      }
      paramInt2 += 1;
    }
  }

  private static int computeX(String paramString, int paramInt1, int paramInt2)
  {
    switch (paramInt2)
    {
    default:
      return 0;
    case 3:
      return paramInt1 / 2;
    }
    return paramInt1;
  }

  private static int computeY(FontMetricsInt paramFontMetricsInt, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = -paramFontMetricsInt.top;
    if (paramInt1 > paramInt2) {}
    switch (paramInt3)
    {
    default:
      return i;
    case 1:
      return -paramFontMetricsInt.top;
    case 3:
      return -paramFontMetricsInt.top + (paramInt1 - paramInt2) / 2;
    }
    return -paramFontMetricsInt.top + (paramInt1 - paramInt2);
  }

  public static void createTextBitmap(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    createTextBitmapShadowStroke(paramString1, paramString2, paramInt1, 1.0F, 1.0F, 1.0F, paramInt2, paramInt3, paramInt4, false, 0.0F, 0.0F, 0.0F, false, 1.0F, 1.0F, 1.0F, 1.0F);
  }

  public static void createTextBitmapShadowStroke(String paramString1, String paramString2, int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, float paramFloat4, float paramFloat5, float paramFloat6, boolean paramBoolean2, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10)
  {
    int i = paramInt2 & 0xF;
    int j = paramInt2 >> 4 & 0xF;
    paramString1 = refactorString(paramString1);
    Object localObject = newPaint(paramString2, paramInt1, i);
    ((Paint)localObject).setARGB(255, (int)(255.0D * paramFloat1), (int)(255.0D * paramFloat2), (int)(255.0D * paramFloat3));
    paramString1 = computeTextProperty(paramString1, paramInt3, paramInt4, (Paint)localObject);
    float f2;
    float f3;
    Bitmap localBitmap;
    Canvas localCanvas;
    FontMetricsInt localFontMetricsInt;
    String[] arrayOfString;
    if (paramInt4 == 0)
    {
      paramInt2 = paramString1.mTotalHeight;
      paramFloat2 = 0.0F;
      float f1 = 0.0F;
      f2 = 0.0F;
      paramFloat1 = 0.0F;
      float f4 = 0.0F;
      f3 = f4;
      if (paramBoolean1)
      {
        ((Paint)localObject).setShadowLayer(paramFloat6, paramFloat4, paramFloat5, -8553091);
        paramFloat3 = Math.abs(paramFloat4);
        paramFloat6 = Math.abs(paramFloat5);
        if (paramFloat4 < 0.0D) {
          paramFloat1 = paramFloat3;
        }
        paramFloat2 = paramFloat3;
        f1 = paramFloat6;
        f2 = paramFloat1;
        f3 = f4;
        if (paramFloat5 < 0.0D)
        {
          f3 = paramFloat6;
          f2 = paramFloat1;
          f1 = paramFloat6;
          paramFloat2 = paramFloat3;
        }
      }
      localBitmap = Bitmap.createBitmap(paramString1.mMaxWidth + (int)paramFloat2, (int)f1 + paramInt2, Config.ARGB_8888);
      localCanvas = new Canvas(localBitmap);
      localFontMetricsInt = ((Paint)localObject).getFontMetricsInt();
      paramInt3 = computeY(localFontMetricsInt, paramInt4, paramString1.mTotalHeight, j);
      arrayOfString = paramString1.mLines;
      int k = arrayOfString.length;
      paramInt2 = 0;
      label251:
      if (paramInt2 < k) {
        break label362;
      }
      if (paramBoolean2)
      {
        paramString2 = newPaint(paramString2, paramInt1, i);
        paramString2.setStyle(Style.STROKE);
        paramString2.setStrokeWidth(0.5F * paramFloat10);
        paramString2.setARGB(255, (int)paramFloat7 * 255, (int)paramFloat8 * 255, (int)paramFloat9 * 255);
        paramInt2 = computeY(localFontMetricsInt, paramInt4, paramString1.mTotalHeight, j);
        localObject = paramString1.mLines;
        paramInt3 = localObject.length;
        paramInt1 = 0;
      }
    }
    for (;;)
    {
      if (paramInt1 >= paramInt3)
      {
        initNativeObject(localBitmap);
        return;
        paramInt2 = paramInt4;
        break;
        label362:
        String str = arrayOfString[paramInt2];
        localCanvas.drawText(str, computeX(str, paramString1.mMaxWidth, i) + f2, paramInt3 + f3, (Paint)localObject);
        paramInt3 += paramString1.mHeightPerLine;
        paramInt2 += 1;
        break label251;
      }
      localFontMetricsInt = localObject[paramInt1];
      localCanvas.drawText(localFontMetricsInt, computeX(localFontMetricsInt, paramString1.mMaxWidth, i) + f2, paramInt2 + f3, paramString2);
      paramInt2 += paramString1.mHeightPerLine;
      paramInt1 += 1;
    }
  }

  private static LinkedList<String> divideStringWithMaxWidth(String paramString, int paramInt, Paint paramPaint)
  {
    int n = paramString.length();
    int j = 0;
    LinkedList localLinkedList = new LinkedList();
    int i = 1;
    if (i > n)
    {
      if (j < n) {
        localLinkedList.add(paramString.substring(j));
      }
      return localLinkedList;
    }
    int i1 = (int)FloatMath.ceil(paramPaint.measureText(paramString, j, i));
    int m = i;
    int k = j;
    if (i1 >= paramInt)
    {
      k = paramString.substring(0, i).lastIndexOf(" ");
      if ((k == -1) || (k <= j)) {
        break label148;
      }
      localLinkedList.add(paramString.substring(j, k));
      i = k + 1;
    }
    for (;;)
    {
      if (paramString.charAt(i) != ' ')
      {
        k = i;
        m = i;
        i = m + 1;
        j = k;
        break;
        label148:
        if (i1 > paramInt)
        {
          localLinkedList.add(paramString.substring(j, i - 1));
          i -= 1;
          continue;
        }
        localLinkedList.add(paramString.substring(j, i));
        continue;
      }
      i += 1;
    }
  }

  private static int getFontSizeAccordingHeight(int paramInt)
  {
    Paint localPaint = new Paint();
    Rect localRect = new Rect();
    localPaint.setTypeface(Typeface.DEFAULT);
    int j = 1;
    int i = 0;
    for (;;)
    {
      if (i != 0) {
        return j;
      }
      localPaint.setTextSize(j);
      localPaint.getTextBounds("SghMNy", 0, "SghMNy".length(), localRect);
      j += 1;
      if (paramInt - localRect.height() <= 2) {
        i = 1;
      }
      Log.d("font size", "incr size:" + j);
    }
  }

  private static byte[] getPixels(Bitmap paramBitmap)
  {
    if (paramBitmap != null)
    {
      byte[] arrayOfByte = new byte[paramBitmap.getWidth() * paramBitmap.getHeight() * 4];
      ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte);
      localByteBuffer.order(ByteOrder.nativeOrder());
      paramBitmap.copyPixelsToBuffer(localByteBuffer);
      return arrayOfByte;
    }
    return null;
  }

  private static String getStringWithEllipsis(String paramString, float paramFloat1, float paramFloat2)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    TextPaint localTextPaint = new TextPaint();
    localTextPaint.setTypeface(Typeface.DEFAULT);
    localTextPaint.setTextSize(paramFloat2);
    return TextUtils.ellipsize(paramString, localTextPaint, paramFloat1, TruncateAt.END).toString();
  }

  private static void initNativeObject(Bitmap paramBitmap)
  {
    byte[] arrayOfByte = getPixels(paramBitmap);
    if (arrayOfByte == null) {
      return;
    }
    nativeInitBitmapDC(paramBitmap.getWidth(), paramBitmap.getHeight(), arrayOfByte);
  }

  private static native void nativeInitBitmapDC(int paramInt1, int paramInt2, byte[] paramArrayOfByte);

  private static Paint newPaint(String paramString, int paramInt1, int paramInt2)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(-1);
    localPaint.setTextSize(paramInt1);
    localPaint.setAntiAlias(true);
    if (paramString.endsWith(".ttf")) {}
    for (;;)
    {
      try
      {
        localPaint.setTypeface(Cocos2dxTypefaces.get(sContext, paramString));
        switch (paramInt2)
        {
        default:
          localPaint.setTextAlign(Align.LEFT);
          return localPaint;
        }
      }
      catch (Exception localException)
      {
        Log.e("Cocos2dxBitmap", "error to create ttf type face: " + paramString);
        localPaint.setTypeface(Typeface.create(paramString, 0));
        continue;
      }
      localPaint.setTypeface(Typeface.create(paramString, 0));
    }
    localPaint.setTextAlign(Align.CENTER);
    return localPaint;
    localPaint.setTextAlign(Align.RIGHT);
    return localPaint;
  }

  private static String refactorString(String paramString)
  {
    if (paramString.compareTo("") == 0) {
      return " ";
    }
    paramString = new StringBuilder(paramString);
    int i = 0;
    int j = paramString.indexOf("\n");
    if (j == -1) {}
    label103:
    for (;;)
    {
      return paramString.toString();
      if ((j == 0) || (paramString.charAt(j - 1) == '\n')) {
        paramString.insert(i, " ");
      }
      for (i = j + 2;; i = j + 1)
      {
        if ((i > paramString.length()) || (j == paramString.length())) {
          break label103;
        }
        j = paramString.indexOf("\n", i);
        break;
      }
    }
  }

  public static void setContext(Context paramContext)
  {
    sContext = paramContext;
  }

  private static String[] splitString(String paramString, int paramInt1, int paramInt2, Paint paramPaint)
  {
    paramString = paramString.split("\\n");
    Object localObject = paramPaint.getFontMetricsInt();
    int i = paramInt2 / (int)Math.ceil(((FontMetricsInt)localObject).bottom - ((FontMetricsInt)localObject).top);
    if (paramInt1 != 0)
    {
      localObject = new LinkedList();
      int j = paramString.length;
      paramInt2 = 0;
      if (paramInt2 >= j) {
        label59:
        if ((i <= 0) || (((LinkedList)localObject).size() <= i)) {}
      }
      for (;;)
      {
        if (((LinkedList)localObject).size() <= i)
        {
          paramString = new String[((LinkedList)localObject).size()];
          ((LinkedList)localObject).toArray(paramString);
          return paramString;
          String str = paramString[paramInt2];
          if ((int)FloatMath.ceil(paramPaint.measureText(str)) > paramInt1) {
            ((LinkedList)localObject).addAll(divideStringWithMaxWidth(str, paramInt1, paramPaint));
          }
          for (;;)
          {
            if ((i > 0) && (((LinkedList)localObject).size() >= i)) {
              break label165;
            }
            paramInt2 += 1;
            break;
            ((LinkedList)localObject).add(str);
          }
          label165:
          break label59;
        }
        ((LinkedList)localObject).removeLast();
      }
    }
    if ((paramInt2 != 0) && (paramString.length > i))
    {
      paramPaint = new LinkedList();
      paramInt1 = 0;
      for (;;)
      {
        if (paramInt1 >= i)
        {
          paramString = new String[paramPaint.size()];
          paramPaint.toArray(paramString);
          return paramString;
        }
        paramPaint.add(paramString[paramInt1]);
        paramInt1 += 1;
      }
    }
    return paramString;
  }

  private static class TextProperty
  {
    private final int mHeightPerLine;
    private final String[] mLines;
    private final int mMaxWidth;
    private final int mTotalHeight;

    TextProperty(int paramInt1, int paramInt2, String[] paramArrayOfString)
    {
      this.mMaxWidth = paramInt1;
      this.mHeightPerLine = paramInt2;
      this.mTotalHeight = (paramArrayOfString.length * paramInt2);
      this.mLines = paramArrayOfString;
    }
  }
}


/* Location:              D:\AFreelancer\bak1\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxBitmap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */