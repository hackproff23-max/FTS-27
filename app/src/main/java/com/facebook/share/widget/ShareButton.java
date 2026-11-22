package com.facebook.share.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.CallbackManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class ShareButton extends ShareButtonBase {
    private static final int DEFAULT_REQUEST_CODE = CallbackManagerImpl.RequestCodeOffset.Share.toRequestCode();

    public ShareButton(Context context) {
        super(context, null, 0, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public ShareButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    public ShareButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, AnalyticsEvents.EVENT_SHARE_BUTTON_CREATE, DEFAULT_REQUEST_CODE);
    }

    @Override // com.facebook.FacebookButtonBase
    protected int getDefaultStyleResource() {
        return R.style.com_facebook_button_share;
    }

    @Override // com.facebook.share.widget.ShareButtonBase
    protected View.OnClickListener getShareOnClickListener() {
        return new View.OnClickListener() { // from class: com.facebook.share.widget.ShareButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ShareDialog dialog;
                if (ShareButton.this.getFragment() != null) {
                    dialog = new ShareDialog(ShareButton.this.getFragment(), ShareButton.this.getRequestCode());
                } else {
                    dialog = new ShareDialog(ShareButton.this.getActivity(), ShareButton.this.getRequestCode());
                }
                dialog.show(ShareButton.this.getShareContent());
                ShareButton.this.callExternalOnClickListener(v);
            }
        };
    }
}
