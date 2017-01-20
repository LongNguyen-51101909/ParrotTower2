package org.cocos2dx.lib;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class Cocos2dxHandler
        extends Handler {
    public static final int HANDLER_SHOW_DIALOG = 1;
    public static final int HANDLER_SHOW_EDITBOX_DIALOG = 2;
    private WeakReference<Cocos2dxActivity> mActivity;

    public Cocos2dxHandler(Cocos2dxActivity paramCocos2dxActivity) {
        this.mActivity = new WeakReference(paramCocos2dxActivity);
    }

    private void showDialog(Message paramMessage) {
        Cocos2dxActivity localCocos2dxActivity = (Cocos2dxActivity) this.mActivity.get();
        paramMessage = (DialogMessage) paramMessage.obj;
        new Builder(localCocos2dxActivity).setTitle(paramMessage.titile).setMessage(paramMessage.message).setPositiveButton("Ok", new OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
            }
        }).create().show();
    }

    private void showEditBoxDialog(Message paramMessage) {
        paramMessage = (EditBoxMessage) paramMessage.obj;
        new Cocos2dxEditBoxDialog((Context) this.mActivity.get(), paramMessage.title, paramMessage.content, paramMessage.inputMode, paramMessage.inputFlag, paramMessage.returnType, paramMessage.maxLength).show();
    }

    public void handleMessage(Message paramMessage) {
        switch (paramMessage.what) {
            default:
                return;
            case 1:
                showDialog(paramMessage);
                return;
        }
        showEditBoxDialog(paramMessage);
    }

    public static class DialogMessage {
        public String message;
        public String titile;

        public DialogMessage(String paramString1, String paramString2) {
            this.titile = paramString1;
            this.message = paramString2;
        }
    }

    public static class EditBoxMessage {
        public String content;
        public int inputFlag;
        public int inputMode;
        public int maxLength;
        public int returnType;
        public String title;

        public EditBoxMessage(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
            this.content = paramString2;
            this.title = paramString1;
            this.inputMode = paramInt1;
            this.inputFlag = paramInt2;
            this.returnType = paramInt3;
            this.maxLength = paramInt4;
        }
    }
}