package org.cocos2dx.lib;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

public class Cocos2dxGLSurfaceView
  extends GLSurfaceView
{
  private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
  private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
  private static final String TAG = Cocos2dxGLSurfaceView.class.getSimpleName();
  private static Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
  private static Cocos2dxTextInputWraper sCocos2dxTextInputWraper;
  private static Handler sHandler;
  private Cocos2dxEditText mCocos2dxEditText;
  private Cocos2dxRenderer mCocos2dxRenderer;

  public Cocos2dxGLSurfaceView(Context paramContext)
  {
    super(paramContext);
    initView();
  }

  public Cocos2dxGLSurfaceView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView();
  }

  public static void closeIMEKeyboard()
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    sHandler.sendMessage(localMessage);
  }

  private static void dumpMotionEvent(MotionEvent paramMotionEvent)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramMotionEvent.getAction();
    int j = i & 0xFF;
    localStringBuilder.append("event ACTION_").append(new String[] { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" }[j]);
    if ((j == 5) || (j == 6))
    {
      localStringBuilder.append("(pid ").append(i >> 8);
      localStringBuilder.append(")");
    }
    localStringBuilder.append("[");
    i = 0;
    for (;;)
    {
      if (i >= paramMotionEvent.getPointerCount())
      {
        localStringBuilder.append("]");
        Log.d(TAG, localStringBuilder.toString());
        return;
      }
      localStringBuilder.append("#").append(i);
      localStringBuilder.append("(pid ").append(paramMotionEvent.getPointerId(i));
      localStringBuilder.append(")=").append((int)paramMotionEvent.getX(i));
      localStringBuilder.append(",").append((int)paramMotionEvent.getY(i));
      if (i + 1 < paramMotionEvent.getPointerCount()) {
        localStringBuilder.append(";");
      }
      i += 1;
    }
  }

  private String getContentText()
  {
    return this.mCocos2dxRenderer.getContentText();
  }

  public static Cocos2dxGLSurfaceView getInstance()
  {
    return mCocos2dxGLSurfaceView;
  }

  public static void openIMEKeyboard()
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.obj = mCocos2dxGLSurfaceView.getContentText();
    sHandler.sendMessage(localMessage);
  }

  public static void queueAccelerometer(float paramFloat1, final float paramFloat2, final float paramFloat3, final long paramLong)
  {
    mCocos2dxGLSurfaceView.queueEvent(new Runnable()
    {
      public void run()
      {
        Cocos2dxAccelerometer.onSensorChanged(this.val$x, paramFloat2, paramFloat3, paramLong);
      }
    });
  }

  public void deleteBackward()
  {
    queueEvent(new Runnable()
    {
      public void run()
      {
        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleDeleteBackward();
      }
    });
  }

  public Cocos2dxEditText getCocos2dxEditText()
  {
    return this.mCocos2dxEditText;
  }

  protected void initView()
  {
    setEGLContextClientVersion(2);
    setFocusableInTouchMode(true);
    mCocos2dxGLSurfaceView = this;
    sCocos2dxTextInputWraper = new Cocos2dxTextInputWraper(this);
    sHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        }
        do
        {
          do
          {
            return;
          } while ((Cocos2dxGLSurfaceView.this.mCocos2dxEditText == null) || (!Cocos2dxGLSurfaceView.this.mCocos2dxEditText.requestFocus()));
          Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
          Cocos2dxGLSurfaceView.this.mCocos2dxEditText.setText("");
          paramAnonymousMessage = (String)paramAnonymousMessage.obj;
          Cocos2dxGLSurfaceView.this.mCocos2dxEditText.append(paramAnonymousMessage);
          Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper.setOriginText(paramAnonymousMessage);
          Cocos2dxGLSurfaceView.this.mCocos2dxEditText.addTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
          ((InputMethodManager)Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).showSoftInput(Cocos2dxGLSurfaceView.this.mCocos2dxEditText, 0);
          Log.d("GLSurfaceView", "showSoftInput");
          return;
        } while (Cocos2dxGLSurfaceView.this.mCocos2dxEditText == null);
        Cocos2dxGLSurfaceView.this.mCocos2dxEditText.removeTextChangedListener(Cocos2dxGLSurfaceView.sCocos2dxTextInputWraper);
        ((InputMethodManager)Cocos2dxGLSurfaceView.mCocos2dxGLSurfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(Cocos2dxGLSurfaceView.this.mCocos2dxEditText.getWindowToken(), 0);
        Cocos2dxGLSurfaceView.this.requestFocus();
        Log.d("GLSurfaceView", "HideSoftInput");
      }
    };
  }

  public void insertText(final String paramString)
  {
    queueEvent(new Runnable()
    {
      public void run()
      {
        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleInsertText(paramString);
      }
    });
  }

  public boolean onKeyDown(final int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default:
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    queueEvent(new Runnable()
    {
      public void run()
      {
        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleKeyDown(paramInt);
      }
    });
    return true;
  }

  public void onPause()
  {
    queueEvent(new Runnable()
    {
      public void run()
      {
        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnPause();
      }
    });
  }

  public void onResume()
  {
    super.onResume();
    queueEvent(new Runnable()
    {
      public void run()
      {
        Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleOnResume();
      }
    });
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!isInEditMode()) {
      this.mCocos2dxRenderer.setScreenWidthAndHeight(paramInt1, paramInt2);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int j = paramMotionEvent.getPointerCount();
    final int[] arrayOfInt = new int[j];
    final float[] arrayOfFloat1 = new float[j];
    final float[] arrayOfFloat2 = new float[j];
    int i = 0;
    if (i >= j) {
      switch (paramMotionEvent.getAction() & 0xFF)
      {
      }
    }
    for (;;)
    {
      return true;
      arrayOfInt[i] = paramMotionEvent.getPointerId(i);
      arrayOfFloat1[i] = paramMotionEvent.getX(i);
      arrayOfFloat2[i] = paramMotionEvent.getY(i);
      i += 1;
      break;
      i = paramMotionEvent.getAction() >> 8;
      queueEvent(new Runnable()
      {
        public void run()
        {
          Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(this.val$idPointerDown, this.val$xPointerDown, this.val$yPointerDown);
        }
      });
      continue;
      queueEvent(new Runnable()
      {
        public void run()
        {
          Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionDown(this.val$idDown, this.val$xDown, this.val$yDown);
        }
      });
      continue;
      queueEvent(new Runnable()
      {
        public void run()
        {
          Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionMove(arrayOfInt, arrayOfFloat1, arrayOfFloat2);
        }
      });
      continue;
      i = paramMotionEvent.getAction() >> 8;
      queueEvent(new Runnable()
      {
        public void run()
        {
          Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(this.val$idPointerUp, this.val$xPointerUp, this.val$yPointerUp);
        }
      });
      continue;
      queueEvent(new Runnable()
      {
        public void run()
        {
          Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionUp(this.val$idUp, this.val$xUp, this.val$yUp);
        }
      });
      continue;
      queueEvent(new Runnable()
      {
        public void run()
        {
          Cocos2dxGLSurfaceView.this.mCocos2dxRenderer.handleActionCancel(arrayOfInt, arrayOfFloat1, arrayOfFloat2);
        }
      });
    }
  }

  public void setCocos2dxEditText(Cocos2dxEditText paramCocos2dxEditText)
  {
    this.mCocos2dxEditText = paramCocos2dxEditText;
    if ((this.mCocos2dxEditText != null) && (sCocos2dxTextInputWraper != null))
    {
      this.mCocos2dxEditText.setOnEditorActionListener(sCocos2dxTextInputWraper);
      this.mCocos2dxEditText.setCocos2dxGLSurfaceView(this);
      requestFocus();
    }
  }

  public void setCocos2dxRenderer(Cocos2dxRenderer paramCocos2dxRenderer)
  {
    this.mCocos2dxRenderer = paramCocos2dxRenderer;
    setRenderer(this.mCocos2dxRenderer);
  }
}