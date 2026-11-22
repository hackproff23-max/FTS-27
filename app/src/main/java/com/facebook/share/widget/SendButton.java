package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.CallbackManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SendButton extends ShareButtonBase {
    private static final int DEFAULT_REQUEST_CODE = CallbackManagerImpl.RequestCodeOffset.Message.toRequestCode();

    public SendButton(Context context) {
        super(context, null, 0, AnalyticsEvents.EVENT_SEND_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public SendButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0, AnalyticsEvents.EVENT_SEND_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public SendButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, AnalyticsEvents.EVENT_SEND_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    @Override // com.facebook.FacebookButtonBase
    protected int getDefaultStyleResource() {
        return R.style.com_facebook_button_send;
    }

    @Override // com.facebook.share.widget.ShareButtonBase
    protected View.OnClickListener getShareOnClickListener() {
        return new View.OnClickListener() { // from class: com.facebook.share.widget.SendButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MessageDialog dialog;
                if (SendButton.this.getFragment() != null) {
                    dialog = new MessageDialog(SendButton.this.getFragment(), SendButton.this.getRequestCode());
                } else {
                    dialog = new MessageDialog(SendButton.this.getActivity(), SendButton.this.getRequestCode());
                }
                dialog.show(SendButton.this.getShareContent());
                SendButton.this.callExternalOnClickListener(v);
            }
        };
    }
}
