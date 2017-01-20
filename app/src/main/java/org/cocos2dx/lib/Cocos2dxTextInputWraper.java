package org.cocos2dx.lib;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Cocos2dxTextInputWraper
  implements TextWatcher, OnEditorActionListener
{
  private static final String TAG = Cocos2dxTextInputWraper.class.getSimpleName();
  private final Cocos2dxGLSurfaceView mCocos2dxGLSurfaceView;
  private String mOriginText;
  private String mText;

  public Cocos2dxTextInputWraper(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView)
  {
    this.mCocos2dxGLSurfaceView = paramCocos2dxGLSurfaceView;
  }

  private boolean isFullScreenEdit()
  {
    return ((InputMethodManager)this.mCocos2dxGLSurfaceView.getCocos2dxEditText().getContext().getSystemService("input_method")).isFullscreenMode();
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if (isFullScreenEdit()) {
      return;
    }
    int j = paramEditable.length() - this.mText.length();
    int i = j;
    if (j > 0)
    {
      String str = paramEditable.subSequence(this.mText.length(), paramEditable.length()).toString();
      this.mCocos2dxGLSurfaceView.insertText(str);
    }
    for (;;)
    {
      this.mText = paramEditable.toString();
      return;
      do
      {
        this.mCocos2dxGLSurfaceView.deleteBackward();
        i += 1;
      } while (i < 0);
    }
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mText = paramCharSequence.toString();
  }

  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    int i;
    if ((this.mCocos2dxGLSurfaceView.getCocos2dxEditText() == paramTextView) && (isFullScreenEdit())) {
      i = this.mOriginText.length();
    }
    for (;;)
    {
      if (i <= 0)
      {
        paramKeyEvent = paramTextView.getText().toString();
        paramTextView = paramKeyEvent;
        if (paramKeyEvent.compareTo("") == 0) {
          paramTextView = "\n";
        }
        paramKeyEvent = paramTextView;
        if ('\n' != paramTextView.charAt(paramTextView.length() - 1)) {
          paramKeyEvent = paramTextView + '\n';
        }
        this.mCocos2dxGLSurfaceView.insertText(paramKeyEvent);
        if (paramInt == 6) {
          this.mCocos2dxGLSurfaceView.requestFocus();
        }
        return false;
      }
      this.mCocos2dxGLSurfaceView.deleteBackward();
      i -= 1;
    }
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}

  public void setOriginText(String paramString)
  {
    this.mOriginText = paramString;
  }
}


/* Location:              D:\AFreelancer\bak1\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxTextInputWraper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */