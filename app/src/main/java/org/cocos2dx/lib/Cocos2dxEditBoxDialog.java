package org.cocos2dx.lib;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class Cocos2dxEditBoxDialog
  extends Dialog
{
  private final int kEditBoxInputFlagInitialCapsAllCharacters = 4;
  private final int kEditBoxInputFlagInitialCapsSentence = 3;
  private final int kEditBoxInputFlagInitialCapsWord = 2;
  private final int kEditBoxInputFlagPassword = 0;
  private final int kEditBoxInputFlagSensitive = 1;
  private final int kEditBoxInputModeAny = 0;
  private final int kEditBoxInputModeDecimal = 5;
  private final int kEditBoxInputModeEmailAddr = 1;
  private final int kEditBoxInputModeNumeric = 2;
  private final int kEditBoxInputModePhoneNumber = 3;
  private final int kEditBoxInputModeSingleLine = 6;
  private final int kEditBoxInputModeUrl = 4;
  private final int kKeyboardReturnTypeDefault = 0;
  private final int kKeyboardReturnTypeDone = 1;
  private final int kKeyboardReturnTypeGo = 4;
  private final int kKeyboardReturnTypeSearch = 3;
  private final int kKeyboardReturnTypeSend = 2;
  private EditText mInputEditText;
  private final int mInputFlag;
  private int mInputFlagConstraints;
  private final int mInputMode;
  private int mInputModeContraints;
  private boolean mIsMultiline;
  private final int mMaxLength;
  private final String mMessage;
  private final int mReturnType;
  private TextView mTextViewTitle;
  private final String mTitle;

  public Cocos2dxEditBoxDialog(Context paramContext, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramContext, 16973841);
    this.mTitle = paramString1;
    this.mMessage = paramString2;
    this.mInputMode = paramInt1;
    this.mInputFlag = paramInt2;
    this.mReturnType = paramInt3;
    this.mMaxLength = paramInt4;
  }

  private void closeKeyboard()
  {
    ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mInputEditText.getWindowToken(), 0);
  }

  private int convertDipsToPixels(float paramFloat)
  {
    return Math.round(paramFloat * getContext().getResources().getDisplayMetrics().density);
  }

  private void openKeyboard()
  {
    ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this.mInputEditText, 0);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().setBackgroundDrawable(new ColorDrawable(Integer.MIN_VALUE));
    paramBundle = new LinearLayout(getContext());
    paramBundle.setOrientation(1);
    LayoutParams localLayoutParams1 = new LayoutParams(-1, -1);
    this.mTextViewTitle = new TextView(getContext());
    LayoutParams localLayoutParams2 = new LayoutParams(-2, -2);
    int i = convertDipsToPixels(10.0F);
    localLayoutParams2.rightMargin = i;
    localLayoutParams2.leftMargin = i;
    this.mTextViewTitle.setTextSize(1, 20.0F);
    paramBundle.addView(this.mTextViewTitle, localLayoutParams2);
    this.mInputEditText = new EditText(getContext());
    localLayoutParams2 = new LayoutParams(-1, -2);
    i = convertDipsToPixels(10.0F);
    localLayoutParams2.rightMargin = i;
    localLayoutParams2.leftMargin = i;
    paramBundle.addView(this.mInputEditText, localLayoutParams2);
    setContentView(paramBundle, localLayoutParams1);
    getWindow().addFlags(1024);
    this.mTextViewTitle.setText(this.mTitle);
    this.mInputEditText.setText(this.mMessage);
    i = this.mInputEditText.getImeOptions();
    this.mInputEditText.setImeOptions(0x10000000 | i);
    i = this.mInputEditText.getImeOptions();
    switch (this.mInputMode)
    {
    default:
      if (this.mIsMultiline) {
        this.mInputModeContraints |= 0x20000;
      }
      this.mInputEditText.setInputType(this.mInputModeContraints | this.mInputFlagConstraints);
      switch (this.mInputFlag)
      {
      default:
        label356:
        this.mInputEditText.setInputType(this.mInputFlagConstraints | this.mInputModeContraints);
        switch (this.mReturnType)
        {
        default:
          this.mInputEditText.setImeOptions(i | 0x1);
        }
        break;
      }
      break;
    }
    for (;;)
    {
      if (this.mMaxLength > 0) {
        this.mInputEditText.setFilters(new InputFilter[] { new LengthFilter(this.mMaxLength) });
      }
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Cocos2dxEditBoxDialog.this.mInputEditText.requestFocus();
          Cocos2dxEditBoxDialog.this.mInputEditText.setSelection(Cocos2dxEditBoxDialog.this.mInputEditText.length());
          Cocos2dxEditBoxDialog.this.openKeyboard();
        }
      }, 200L);
      this.mInputEditText.setOnEditorActionListener(new OnEditorActionListener()
      {
        public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if ((paramAnonymousInt != 0) || ((paramAnonymousInt == 0) && (paramAnonymousKeyEvent != null) && (paramAnonymousKeyEvent.getAction() == 0)))
          {
            Cocos2dxHelper.setEditTextDialogResult(Cocos2dxEditBoxDialog.this.mInputEditText.getText().toString());
            Cocos2dxEditBoxDialog.this.closeKeyboard();
            Cocos2dxEditBoxDialog.this.dismiss();
            return true;
          }
          return false;
        }
      });
      return;
      this.mInputModeContraints = 131073;
      break;
      this.mInputModeContraints = 33;
      break;
      this.mInputModeContraints = 4098;
      break;
      this.mInputModeContraints = 3;
      break;
      this.mInputModeContraints = 17;
      break;
      this.mInputModeContraints = 12290;
      break;
      this.mInputModeContraints = 1;
      break;
      this.mInputFlagConstraints = 129;
      break label356;
      this.mInputFlagConstraints = 524288;
      break label356;
      this.mInputFlagConstraints = 8192;
      break label356;
      this.mInputFlagConstraints = 16384;
      break label356;
      this.mInputFlagConstraints = 4096;
      break label356;
      this.mInputEditText.setImeOptions(i | 0x1);
      continue;
      this.mInputEditText.setImeOptions(i | 0x6);
      continue;
      this.mInputEditText.setImeOptions(i | 0x4);
      continue;
      this.mInputEditText.setImeOptions(i | 0x3);
      continue;
      this.mInputEditText.setImeOptions(i | 0x2);
    }
  }
}


/* Location:              D:\AFreelancer\bak1\dex2jar-2.0\dex2jar-2.0\classes-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxEditBoxDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */