package com.flurry.sdk;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.flurry.android.AdCreative;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.millennialmedia.android.MMSDK;
import com.mopub.common.AdType;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"SetJavaScriptEnabled"})
public class eb extends ec implements DialogInterface.OnKeyListener {
    private boolean A;
    private boolean B;
    private ec.a C;
    String a;
    fy<ed> b;
    private boolean c;
    private final String d;
    private dq e;
    private boolean f;
    private WebView g;
    private ImageButton h;
    private int i;
    private boolean j;
    private WebViewClient k;
    private WebChromeClient l;
    private View m;
    private int n;
    private WebChromeClient.CustomViewCallback o;
    private Dialog p;
    private FrameLayout q;
    private int r;
    private Dialog s;
    private FrameLayout t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private AlertDialog z;

    /* JADX INFO: renamed from: com.flurry.sdk.eb$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[ed.a.values().length];

        static {
            try {
                a[ed.a.SHOW_VIDEO_DIALOG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ed.a.CLOSE_AD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ed.a.DO_COLLAPSE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ed.a.DO_EXPAND.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ed.a.CALL_COMPLETE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.flurry.sdk.a aVar) {
        int iF = hn.f();
        int iG = hn.g();
        gd.a(3, this.d, "expand to width = " + iF + " height = " + iG);
        r rVarB = aVar.c().b();
        ap apVarC = aVar.c().c();
        if ((rVarB instanceof s) && ((s) rVarB).s() != null) {
            a(aw.EV_CLICKED, Collections.emptyMap(), apVarC, 0);
            a(iF, iG);
        }
        if (aVar.c().b.containsKey("url")) {
            this.a = aVar.c().b.get("url");
            apVarC.a(true);
            cu.a(getContext(), this.a, false, rVarB, true, false);
        }
    }

    @Override // com.flurry.sdk.ec
    public boolean onBackKey() {
        a(aw.EV_AD_WILL_CLOSE, Collections.emptyMap(), getAdController(), 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.flurry.sdk.a aVar) {
        int i = getCurrentAdFrame().adSpaceLayout.adWidth;
        int i2 = getCurrentAdFrame().adSpaceLayout.adHeight;
        int iB = hn.b(i);
        int iB2 = hn.b(i2);
        if (this.a != null) {
            this.a = null;
            initLayout();
        }
        r rVarB = aVar.c().b();
        if ((rVarB instanceof s) && ((s) rVarB).s() != null) {
            b(iB, iB2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ed edVar) {
        gd.a(6, this.d, "show Video dialog.");
        final com.flurry.sdk.a aVar = edVar.b;
        final int i = edVar.c;
        if (!isViewAttachedToActivity()) {
            gd.a(6, this.d, "View not attached to any window.");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String strA = aVar.a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        String strA2 = aVar.a("confirmDisplay");
        String strA3 = aVar.a("cancelDisplay");
        if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strA2) || TextUtils.isEmpty(strA3)) {
            strA = "Are you sure?";
            strA2 = "Cancel";
            strA3 = "OK";
        }
        builder.setMessage(strA);
        builder.setCancelable(false);
        builder.setPositiveButton(strA3, new DialogInterface.OnClickListener() { // from class: com.flurry.sdk.eb.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                HashMap map = new HashMap();
                map.put("sourceEvent", aVar.c().a.a());
                eb.this.a(aw.EV_USER_CONFIRMED, map, eb.this.getAdController(), i + 1);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(strA2, new DialogInterface.OnClickListener() { // from class: com.flurry.sdk.eb.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                HashMap map = new HashMap();
                map.put("sourceEvent", aVar.c().a.a());
                eb.this.a(aw.EV_USER_CANCELLED, map, eb.this.getAdController(), i + 1);
                dialogInterface.dismiss();
                if (eb.this.e != null) {
                    eb.this.e.a(eb.this.getAdController().m().a());
                }
            }
        });
        if (this.e != null && isViewAttachedToActivity()) {
            this.z = builder.create();
            this.z.show();
            this.e.c();
        }
    }

    class b extends WebViewClient {
        private b() {
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) throws Throwable {
            gd.a(3, eb.this.d, "onLoadResource: url = " + str);
            super.onLoadResource(webView, str);
            if (str != null && webView != null && webView == eb.this.g) {
                if (!str.equalsIgnoreCase(eb.this.g.getUrl())) {
                    eb.this.b();
                }
                if (!eb.this.v && Uri.parse(str).getLastPathSegment() != null) {
                    if (eb.this.w) {
                        eb.this.v = true;
                        eb.this.h();
                        if (eb.this.u) {
                            eb.this.k();
                            eb.this.n();
                            eb.this.o();
                            return;
                        }
                        return;
                    }
                    if (eb.this.u) {
                        eb.this.p();
                        if (eb.this.r() && eb.this.getCurrentBinding() == 2) {
                            eb.this.q();
                        }
                    }
                }
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            gd.a(3, eb.this.d, "onPageStarted: url = " + str);
            if (str != null && webView != null && webView == eb.this.g) {
                eb.this.c();
                eb.this.i();
                eb.this.u = false;
                eb.this.v = false;
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) throws Throwable {
            gd.a(3, eb.this.d, "onPageFinished: url = " + str + " adcontroller index: " + eb.this.getAdController().c());
            if (str != null && webView != null && webView == eb.this.g) {
                eb.this.b();
                eb.this.e();
                eb.this.dismissProgressDialog();
                if (!eb.this.a(eb.this.g) && (eb.this.getCurrentBinding() == 2 || eb.this.getCurrentBinding() == 1)) {
                    gd.a(3, eb.this.d, "adding WebView to AdUnityView");
                    eb.this.addView(eb.this.g);
                }
                eb.this.u = true;
                if (eb.this.w) {
                    if (eb.this.v) {
                        eb.this.k();
                        eb.this.n();
                        eb.this.o();
                        return;
                    }
                    return;
                }
                if (eb.this.v) {
                    aw awVarA = com.flurry.sdk.b.a("mraidAdNotSupported");
                    eb.this.b(awVarA);
                    eb.this.a(awVarA, new HashMap(), eb.this.getAdController(), 0);
                    if (eb.this.r() && eb.this.getCurrentBinding() == 2) {
                        eb.this.q();
                    }
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:42:0x01c0  */
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String strSubstring;
            boolean zB;
            gd.a(3, eb.this.d, "shouldOverrideUrlLoading: url = " + str);
            if (str == null || webView == null || webView != eb.this.g) {
                return false;
            }
            String strC = cv.c(eb.this.g.getUrl());
            if (TextUtils.isEmpty(strC) || !str.startsWith(strC)) {
                strSubstring = str;
            } else {
                strSubstring = str.substring(strC.length());
                Uri uri = Uri.parse(strSubstring);
                if (!uri.isHierarchical() || TextUtils.isEmpty(uri.getScheme()) || TextUtils.isEmpty(uri.getAuthority())) {
                    strSubstring = str;
                } else {
                    gd.a(3, eb.this.d, "shouldOverrideUrlLoading: target url = " + strSubstring);
                }
            }
            Uri uri2 = Uri.parse(strSubstring);
            gd.a(3, eb.this.d, "shouldOverrideUrlLoading: getScheme = " + uri2.getScheme());
            if (!"flurry".equals(uri2.getScheme())) {
                if (eb.this.B) {
                    eb.this.B = false;
                    eb.this.a(aw.EV_CLICKED, new HashMap(), eb.this.getAdController(), 0);
                }
                if (!eb.this.getAdController().l()) {
                    gd.a(3, eb.this.d, "shouldOverrideUrlLoading: doGenericLaunch ");
                    i.a().o().a(eb.this.getContext(), strSubstring, true, eb.this.getAdObject(), true, eb.this.c);
                    return true;
                }
                if (cv.d(strSubstring)) {
                    gd.a(3, eb.this.d, "shouldOverrideUrlLoading: isMarketUrl ");
                    zB = cu.a(eb.this.getContext(), strSubstring);
                } else {
                    zB = false;
                }
                if (!zB && cv.f(strSubstring)) {
                    gd.a(3, eb.this.d, "shouldOverrideUrlLoading: isGooglePlayUrl ");
                    zB = cu.b(eb.this.getContext(), strSubstring);
                }
                if (!zB) {
                    gd.a(3, eb.this.d, "shouldOverrideUrlLoading: loadUrl doGenericLaunch ");
                    i.a().o().a(eb.this.getContext(), strSubstring, true, eb.this.getAdObject(), true, eb.this.c);
                    return true;
                }
                eb.this.a(aw.INTERNAL_EV_APP_EXIT, Collections.emptyMap(), eb.this.getAdController(), 0);
                return true;
            }
            String queryParameter = uri2.getQueryParameter("event");
            if (TextUtils.isEmpty(queryParameter)) {
                return true;
            }
            aw awVarA = com.flurry.sdk.b.a(queryParameter);
            eb.this.a(awVarA);
            eb.this.a(awVarA, uri2);
            Map<String, String> mapH = hp.h(uri2.getEncodedQuery());
            mapH.put("requiresCallComplete", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            eb.this.a(awVarA, mapH, eb.this.getAdController(), 0);
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            gd.a(3, eb.this.d, "onReceivedError: url = " + str2);
            eb.this.dismissProgressDialog();
            Uri uri = Uri.parse(str2);
            if (MMSDK.Event.INTENT_MARKET.equals(uri.getScheme())) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(uri);
                eb.this.getContext().startActivity(intent);
                eb.this.m();
                return;
            }
            HashMap map = new HashMap();
            map.put("errorCode", Integer.toString(av.kAdDisplayError.a()));
            map.put("webViewErrorCode", Integer.toString(i));
            map.put("failingUrl", str2);
            eb.this.a(aw.EV_RENDER_FAILED, map, eb.this.getAdController(), 0);
        }
    }

    final class a extends WebChromeClient {
        private a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            gd.a(3, eb.this.d, "onShowCustomView(7)");
            if (!(eb.this.getContext() instanceof Activity)) {
                gd.a(3, eb.this.d, "no activity present");
            } else {
                onShowCustomView(view, ((Activity) eb.this.getContext()).getRequestedOrientation(), customViewCallback);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
            gd.a(3, eb.this.d, "onShowCustomView(14)");
            if (!(eb.this.getContext() instanceof Activity)) {
                gd.a(3, eb.this.d, "no activity present");
                return;
            }
            final Activity activity = (Activity) eb.this.getContext();
            if (eb.this.m == null || eb.this.l == null) {
                eb.this.m = view;
                eb.this.n = activity.getRequestedOrientation();
                eb.this.o = customViewCallback;
                eb.this.q = new FrameLayout(activity);
                eb.this.q.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                eb.this.q.addView(eb.this.m, new FrameLayout.LayoutParams(-1, -1, 17));
                ((ViewGroup) activity.getWindow().getDecorView()).addView(eb.this.q, -1, -1);
                if (eb.this.p == null) {
                    eb.this.p = new Dialog(activity, R.style.Theme.Translucent.NoTitleBar.Fullscreen) { // from class: com.flurry.sdk.eb.a.1
                        @Override // android.app.Dialog, android.view.Window.Callback
                        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
                            return activity.dispatchTouchEvent(motionEvent);
                        }

                        @Override // android.app.Dialog, android.view.Window.Callback
                        public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
                            return activity.dispatchTrackballEvent(motionEvent);
                        }
                    };
                    eb.this.p.getWindow().setType(1000);
                    eb.this.p.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.flurry.sdk.eb.a.2
                        @Override // android.content.DialogInterface.OnShowListener
                        public void onShow(DialogInterface dialogInterface) {
                            if (eb.this.s != null) {
                                eb.this.s.hide();
                            }
                        }
                    });
                    eb.this.p.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.flurry.sdk.eb.a.3
                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            gd.a(3, eb.this.d, "customViewFullScreenDialog.onDismiss()");
                            if (eb.this.m != null && eb.this.l != null) {
                                eb.this.l.onHideCustomView();
                            }
                        }
                    });
                    eb.this.p.setCancelable(true);
                    eb.this.p.show();
                }
                cn.a(activity, i, true);
                return;
            }
            eb.this.l.onHideCustomView();
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            gd.a(3, eb.this.d, "onHideCustomView()");
            if (!(eb.this.getContext() instanceof Activity)) {
                gd.a(3, eb.this.d, "no activity present");
                return;
            }
            Activity activity = (Activity) eb.this.getContext();
            if (eb.this.m != null) {
                if (eb.this.s != null) {
                    eb.this.s.show();
                }
                ((ViewGroup) activity.getWindow().getDecorView()).removeView(eb.this.q);
                eb.this.q.removeView(eb.this.m);
                if (eb.this.p != null && eb.this.p.isShowing()) {
                    eb.this.p.hide();
                    eb.this.p.setOnDismissListener(null);
                    eb.this.p.dismiss();
                }
                eb.this.p = null;
                cn.a(activity, eb.this.n);
                eb.this.o.onCustomViewHidden();
                eb.this.o = null;
                eb.this.q = null;
                eb.this.m = null;
            }
        }
    }

    private synchronized void setFlurryJsEnvInitialized(boolean z) {
        this.j = z;
    }

    private synchronized boolean a() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b() {
        if (!a()) {
            d();
            setFlurryJsEnvInitialized(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c() {
        setFlurryJsEnvInitialized(false);
    }

    private void d() {
        gd.a(3, this.d, "initializeFlurryJsEnv");
        if (this.g != null) {
            this.g.loadUrl("javascript:(function() {var Hogan={};(function(Hogan,useArrayBuffer){Hogan.Template=function(renderFunc,text,compiler,options){this.r=renderFunc||this.r;this.c=compiler;this.options=options;this.text=text||\"\";this.buf=useArrayBuffer?[]:\"\"};Hogan.Template.prototype={r:function(context,partials,indent){return\"\"},v:hoganEscape,t:coerceToString,render:function render(context,partials,indent){return this.ri([context],partials||{},indent)},ri:function(context,partials,indent){return this.r(context,partials,indent)},rp:function(name,context,partials,indent){var partial=partials[name];if(!partial){return\"\"}if(this.c&&typeof partial==\"string\"){partial=this.c.compile(partial,this.options)}return partial.ri(context,partials,indent)},rs:function(context,partials,section){var tail=context[context.length-1];if(!isArray(tail)){section(context,partials,this);return}for(var i=0;i<tail.length;i++){context.push(tail[i]);section(context,partials,this);context.pop()}},s:function(val,ctx,partials,inverted,start,end,tags){var pass;if(isArray(val)&&val.length===0){return false}if(typeof val==\"function\"){val=this.ls(val,ctx,partials,inverted,start,end,tags)}pass=(val===\"\")||!!val;if(!inverted&&pass&&ctx){ctx.push((typeof val==\"object\")?val:ctx[ctx.length-1])}return pass},d:function(key,ctx,partials,returnFound){var names=key.split(\".\"),val=this.f(names[0],ctx,partials,returnFound),cx=null;if(key===\".\"&&isArray(ctx[ctx.length-2])){return ctx[ctx.length-1]}for(var i=1;i<names.length;i++){if(val&&typeof val==\"object\"&&names[i] in val){cx=val;val=val[names[i]]}else{val=\"\"}}if(returnFound&&!val){return false}if(!returnFound&&typeof val==\"function\"){ctx.push(cx);val=this.lv(val,ctx,partials);ctx.pop()}return val},f:function(key,ctx,partials,returnFound){var val=false,v=null,found=false;for(var i=ctx.length-1;i>=0;i--){v=ctx[i];if(v&&typeof v==\"object\"&&key in v){val=v[key];found=true;break}}if(!found){return(returnFound)?false:\"\"}if(!returnFound&&typeof val==\"function\"){val=this.lv(val,ctx,partials)}return val},ho:function(val,cx,partials,text,tags){var compiler=this.c;var options=this.options;options.delimiters=tags;var text=val.call(cx,text);text=(text==null)?String(text):text.toString();this.b(compiler.compile(text,options).render(cx,partials));return false},b:(useArrayBuffer)?function(s){this.buf.push(s)}:function(s){this.buf+=s},fl:(useArrayBuffer)?function(){var r=this.buf.join(\"\");this.buf=[];return r}:function(){var r=this.buf;this.buf=\"\";return r},ls:function(val,ctx,partials,inverted,start,end,tags){var cx=ctx[ctx.length-1],t=null;if(!inverted&&this.c&&val.length>0){return this.ho(val,cx,partials,this.text.substring(start,end),tags)}t=val.call(cx);if(typeof t==\"function\"){if(inverted){return true}else{if(this.c){return this.ho(t,cx,partials,this.text.substring(start,end),tags)}}}return t},lv:function(val,ctx,partials){var cx=ctx[ctx.length-1];var result=val.call(cx);if(typeof result==\"function\"){result=coerceToString(result.call(cx));if(this.c&&~result.indexOf(\"{\\u007B\")){return this.c.compile(result,this.options).render(cx,partials)}}return coerceToString(result)}};var rAmp=/&/g,rLt=/</g,rGt=/>/g,rApos=/\\'/g,rQuot=/\\\"/g,hChars=/[&<>\\\"\\']/;function coerceToString(val){return String((val===null||val===undefined)?\"\":val)}function hoganEscape(str){str=coerceToString(str);return hChars.test(str)?str.replace(rAmp,\"&amp;\").replace(rLt,\"&lt;\").replace(rGt,\"&gt;\").replace(rApos,\"&#39;\").replace(rQuot,\"&quot;\"):str}var isArray=Array.isArray||function(a){return Object.prototype.toString.call(a)===\"[object Array]\"}})(typeof exports!==\"undefined\"?exports:Hogan);(function(Hogan){var rIsWhitespace=/\\S/,rQuot=/\\\"/g,rNewline=/\\n/g,rCr=/\\r/g,rSlash=/\\\\/g,tagTypes={\"#\":1,\"^\":2,\"/\":3,\"!\":4,\">\":5,\"<\":6,\"=\":7,_v:8,\"{\":9,\"&\":10};Hogan.scan=function scan(text,delimiters){var len=text.length,IN_TEXT=0,IN_TAG_TYPE=1,IN_TAG=2,state=IN_TEXT,tagType=null,tag=null,buf=\"\",tokens=[],seenTag=false,i=0,lineStart=0,otag=\"{{\",ctag=\"}}\";function addBuf(){if(buf.length>0){tokens.push(new String(buf));buf=\"\"}}function lineIsWhitespace(){var isAllWhitespace=true;for(var j=lineStart;j<tokens.length;j++){isAllWhitespace=(tokens[j].tag&&tagTypes[tokens[j].tag]<tagTypes._v)||(!tokens[j].tag&&tokens[j].match(rIsWhitespace)===null);if(!isAllWhitespace){return false}}return isAllWhitespace}function filterLine(haveSeenTag,noNewLine){addBuf();if(haveSeenTag&&lineIsWhitespace()){for(var j=lineStart,next;j<tokens.length;j++){if(!tokens[j].tag){if((next=tokens[j+1])&&next.tag==\">\"){next.indent=tokens[j].toString()}tokens.splice(j,1)}}}else{if(!noNewLine){tokens.push({tag:\"\\n\"})}}seenTag=false;lineStart=tokens.length}function changeDelimiters(text,index){var close=\"=\"+ctag,closeIndex=text.indexOf(close,index),delimiters=trim(text.substring(text.indexOf(\"=\",index)+1,closeIndex)).split(\" \");otag=delimiters[0];ctag=delimiters[1];return closeIndex+close.length-1}if(delimiters){delimiters=delimiters.split(\" \");otag=delimiters[0];ctag=delimiters[1]}for(i=0;i<len;i++){if(state==IN_TEXT){if(tagChange(otag,text,i)){--i;addBuf();state=IN_TAG_TYPE}else{if(text.charAt(i)==\"\\n\"){filterLine(seenTag)}else{buf+=text.charAt(i)}}}else{if(state==IN_TAG_TYPE){i+=otag.length-1;tag=tagTypes[text.charAt(i+1)];tagType=tag?text.charAt(i+1):\"_v\";if(tagType==\"=\"){i=changeDelimiters(text,i);state=IN_TEXT}else{if(tag){i++}state=IN_TAG}seenTag=i}else{if(tagChange(ctag,text,i)){tokens.push({tag:tagType,n:trim(buf),otag:otag,ctag:ctag,i:(tagType==\"/\")?seenTag-ctag.length:i+otag.length});buf=\"\";i+=ctag.length-1;state=IN_TEXT;if(tagType==\"{\"){if(ctag==\"}}\"){i++}else{cleanTripleStache(tokens[tokens.length-1])}}}else{buf+=text.charAt(i)}}}}filterLine(seenTag,true);return tokens};function cleanTripleStache(token){if(token.n.substr(token.n.length-1)===\"}\"){token.n=token.n.substring(0,token.n.length-1)}}function trim(s){if(s.trim){return s.trim()}return s.replace(/^\\s*|\\s*$/g,\"\")}function tagChange(tag,text,index){if(text.charAt(index)!=tag.charAt(0)){return false}for(var i=1,l=tag.length;i<l;i++){if(text.charAt(index+i)!=tag.charAt(i)){return false}}return true}function buildTree(tokens,kind,stack,customTags){var instructions=[],opener=null,token=null;while(tokens.length>0){token=tokens.shift();if(token.tag==\"#\"||token.tag==\"^\"||isOpener(token,customTags)){stack.push(token);token.nodes=buildTree(tokens,token.tag,stack,customTags);instructions.push(token)}else{if(token.tag==\"/\"){if(stack.length===0){throw new Error(\"Closing tag without opener: /\"+token.n)}opener=stack.pop();if(token.n!=opener.n&&!isCloser(token.n,opener.n,customTags)){throw new Error(\"Nesting error: \"+opener.n+\" vs. \"+token.n)}opener.end=token.i;return instructions}else{instructions.push(token)}}}if(stack.length>0){throw new Error(\"missing closing tag: \"+stack.pop().n)}return instructions}function isOpener(token,tags){for(var i=0,l=tags.length;i<l;i++){if(tags[i].o==token.n){token.tag=\"#\";return true}}}function isCloser(close,open,tags){for(var i=0,l=tags.length;i<l;i++){if(tags[i].c==close&&tags[i].o==open){return true}}}Hogan.generate=function(tree,text,options){var code='var _=this;_.b(i=i||\"\");'+walk(tree)+\"return _.fl();\";if(options.asString){return\"function(c,p,i){\"+code+\";}\"}return new Hogan.Template(new Function(\"c\",\"p\",\"i\",code),text,Hogan,options)};function esc(s){return s.replace(rSlash,\"\\\\\\\\\").replace(rQuot,'\\\\\"').replace(rNewline,\"\\\\n\").replace(rCr,\"\\\\r\")}function chooseMethod(s){return(~s.indexOf(\".\"))?\"d\":\"f\"}function walk(tree){var code=\"\";for(var i=0,l=tree.length;i<l;i++){var tag=tree[i].tag;if(tag==\"#\"){code+=section(tree[i].nodes,tree[i].n,chooseMethod(tree[i].n),tree[i].i,tree[i].end,tree[i].otag+\" \"+tree[i].ctag)}else{if(tag==\"^\"){code+=invertedSection(tree[i].nodes,tree[i].n,chooseMethod(tree[i].n))}else{if(tag==\"<\"||tag==\">\"){code+=partial(tree[i])}else{if(tag==\"{\"||tag==\"&\"){code+=tripleStache(tree[i].n,chooseMethod(tree[i].n))}else{if(tag==\"\\n\"){code+=text('\"\\\\n\"'+(tree.length-1==i?\"\":\" + i\"))}else{if(tag==\"_v\"){code+=variable(tree[i].n,chooseMethod(tree[i].n))}else{if(tag===undefined){code+=text('\"'+esc(tree[i])+'\"')}}}}}}}}return code}function section(nodes,id,method,start,end,tags){return\"if(_.s(_.\"+method+'(\"'+esc(id)+'\",c,p,1),c,p,0,'+start+\",\"+end+',\"'+tags+'\")){_.rs(c,p,function(c,p,_){'+walk(nodes)+\"});c.pop();}\"}function invertedSection(nodes,id,method){return\"if(!_.s(_.\"+method+'(\"'+esc(id)+'\",c,p,1),c,p,1,0,0,\"\")){'+walk(nodes)+\"};\"}function partial(tok){return'_.b(_.rp(\"'+esc(tok.n)+'\",c,p,\"'+(tok.indent||\"\")+'\"));'}function tripleStache(id,method){return\"_.b(_.t(_.\"+method+'(\"'+esc(id)+'\",c,p,0)));'}function variable(id,method){return\"_.b(_.v(_.\"+method+'(\"'+esc(id)+'\",c,p,0)));'}function text(id){return\"_.b(\"+id+\");\"}Hogan.parse=function(tokens,text,options){options=options||{};return buildTree(tokens,\"\",[],options.sectionTags||[])},Hogan.cache={};Hogan.compile=function(text,options){options=options||{};var key=text+\"||\"+!!options.asString;var t=this.cache[key];if(t){return t}t=this.generate(this.parse(this.scan(text,options.delimiters),text,options),text,options);return this.cache[key]=t}})(typeof exports!==\"undefined\"?exports:Hogan);var flurryBridgeCtor=function(w){var flurryadapter={};flurryadapter.flurryCallQueue=[];flurryadapter.flurryCallInProgress=false;flurryadapter.callComplete=function(cmd){if(this.flurryCallQueue.length==0){this.flurryCallInProgress=false;return}var adapterCall=this.flurryCallQueue.splice(0,1)[0];this.executeNativeCall(adapterCall);return\"OK\"};flurryadapter.executeCall=function(command){var adapterCall=\"flurry://flurrycall?event=\"+command;var value;for(var i=1;i<arguments.length;i+=2){value=arguments[i+1];if(value==null)continue;adapterCall+=\"&\"+arguments[i]+\"=\"+escape(value)}if(this.flurryCallInProgress)this.flurryCallQueue.push(adapterCall);else this.executeNativeCall(adapterCall)};flurryadapter.executeNativeCall=function(adapterCall){if(adapterCall.length==0)return;this.flurryCallInProgress=true;w.location=adapterCall};return flurryadapter};window.Hogan=Hogan;window.flurryadapter=flurryBridgeCtor(window);window.flurryAdapterAvailable=true;if(typeof window.FlurryAdapterReady === 'function'){window.FlurryAdapterReady();}})();");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        gd.a(3, this.d, "activateFlurryJsEnv");
        String currentContent = getCurrentContent();
        if (currentContent != null && currentContent.length() > 0 && !currentContent.equals("{}")) {
            String url = this.g.getUrl();
            String strB = cv.b(url);
            String strA = cv.a(strB, url);
            if (!TextUtils.isEmpty(strA) && !strA.equals(strB)) {
                gd.a(3, this.d, "content before {{mustached}} tags replacement = '" + currentContent + "'");
                currentContent = currentContent.replace(strB, strA);
                gd.a(3, this.d, "content after {{mustached}} tags replacement = '" + currentContent + "'");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:");
            sb.append("(function(){");
            sb.append("if(!window.Hogan){var Hogan={};(function(Hogan,useArrayBuffer){Hogan.Template=function(renderFunc,text,compiler,options){this.r=renderFunc||this.r;this.c=compiler;this.options=options;this.text=text||\"\";this.buf=useArrayBuffer?[]:\"\"};Hogan.Template.prototype={r:function(context,partials,indent){return\"\"},v:hoganEscape,t:coerceToString,render:function render(context,partials,indent){return this.ri([context],partials||{},indent)},ri:function(context,partials,indent){return this.r(context,partials,indent)},rp:function(name,context,partials,indent){var partial=partials[name];if(!partial){return\"\"}if(this.c&&typeof partial==\"string\"){partial=this.c.compile(partial,this.options)}return partial.ri(context,partials,indent)},rs:function(context,partials,section){var tail=context[context.length-1];if(!isArray(tail)){section(context,partials,this);return}for(var i=0;i<tail.length;i++){context.push(tail[i]);section(context,partials,this);context.pop()}},s:function(val,ctx,partials,inverted,start,end,tags){var pass;if(isArray(val)&&val.length===0){return false}if(typeof val==\"function\"){val=this.ls(val,ctx,partials,inverted,start,end,tags)}pass=(val===\"\")||!!val;if(!inverted&&pass&&ctx){ctx.push((typeof val==\"object\")?val:ctx[ctx.length-1])}return pass},d:function(key,ctx,partials,returnFound){var names=key.split(\".\"),val=this.f(names[0],ctx,partials,returnFound),cx=null;if(key===\".\"&&isArray(ctx[ctx.length-2])){return ctx[ctx.length-1]}for(var i=1;i<names.length;i++){if(val&&typeof val==\"object\"&&names[i] in val){cx=val;val=val[names[i]]}else{val=\"\"}}if(returnFound&&!val){return false}if(!returnFound&&typeof val==\"function\"){ctx.push(cx);val=this.lv(val,ctx,partials);ctx.pop()}return val},f:function(key,ctx,partials,returnFound){var val=false,v=null,found=false;for(var i=ctx.length-1;i>=0;i--){v=ctx[i];if(v&&typeof v==\"object\"&&key in v){val=v[key];found=true;break}}if(!found){return(returnFound)?false:\"\"}if(!returnFound&&typeof val==\"function\"){val=this.lv(val,ctx,partials)}return val},ho:function(val,cx,partials,text,tags){var compiler=this.c;var options=this.options;options.delimiters=tags;var text=val.call(cx,text);text=(text==null)?String(text):text.toString();this.b(compiler.compile(text,options).render(cx,partials));return false},b:(useArrayBuffer)?function(s){this.buf.push(s)}:function(s){this.buf+=s},fl:(useArrayBuffer)?function(){var r=this.buf.join(\"\");this.buf=[];return r}:function(){var r=this.buf;this.buf=\"\";return r},ls:function(val,ctx,partials,inverted,start,end,tags){var cx=ctx[ctx.length-1],t=null;if(!inverted&&this.c&&val.length>0){return this.ho(val,cx,partials,this.text.substring(start,end),tags)}t=val.call(cx);if(typeof t==\"function\"){if(inverted){return true}else{if(this.c){return this.ho(t,cx,partials,this.text.substring(start,end),tags)}}}return t},lv:function(val,ctx,partials){var cx=ctx[ctx.length-1];var result=val.call(cx);if(typeof result==\"function\"){result=coerceToString(result.call(cx));if(this.c&&~result.indexOf(\"{\\u007B\")){return this.c.compile(result,this.options).render(cx,partials)}}return coerceToString(result)}};var rAmp=/&/g,rLt=/</g,rGt=/>/g,rApos=/\\'/g,rQuot=/\\\"/g,hChars=/[&<>\\\"\\']/;function coerceToString(val){return String((val===null||val===undefined)?\"\":val)}function hoganEscape(str){str=coerceToString(str);return hChars.test(str)?str.replace(rAmp,\"&amp;\").replace(rLt,\"&lt;\").replace(rGt,\"&gt;\").replace(rApos,\"&#39;\").replace(rQuot,\"&quot;\"):str}var isArray=Array.isArray||function(a){return Object.prototype.toString.call(a)===\"[object Array]\"}})(typeof exports!==\"undefined\"?exports:Hogan);(function(Hogan){var rIsWhitespace=/\\S/,rQuot=/\\\"/g,rNewline=/\\n/g,rCr=/\\r/g,rSlash=/\\\\/g,tagTypes={\"#\":1,\"^\":2,\"/\":3,\"!\":4,\">\":5,\"<\":6,\"=\":7,_v:8,\"{\":9,\"&\":10};Hogan.scan=function scan(text,delimiters){var len=text.length,IN_TEXT=0,IN_TAG_TYPE=1,IN_TAG=2,state=IN_TEXT,tagType=null,tag=null,buf=\"\",tokens=[],seenTag=false,i=0,lineStart=0,otag=\"{{\",ctag=\"}}\";function addBuf(){if(buf.length>0){tokens.push(new String(buf));buf=\"\"}}function lineIsWhitespace(){var isAllWhitespace=true;for(var j=lineStart;j<tokens.length;j++){isAllWhitespace=(tokens[j].tag&&tagTypes[tokens[j].tag]<tagTypes._v)||(!tokens[j].tag&&tokens[j].match(rIsWhitespace)===null);if(!isAllWhitespace){return false}}return isAllWhitespace}function filterLine(haveSeenTag,noNewLine){addBuf();if(haveSeenTag&&lineIsWhitespace()){for(var j=lineStart,next;j<tokens.length;j++){if(!tokens[j].tag){if((next=tokens[j+1])&&next.tag==\">\"){next.indent=tokens[j].toString()}tokens.splice(j,1)}}}else{if(!noNewLine){tokens.push({tag:\"\\n\"})}}seenTag=false;lineStart=tokens.length}function changeDelimiters(text,index){var close=\"=\"+ctag,closeIndex=text.indexOf(close,index),delimiters=trim(text.substring(text.indexOf(\"=\",index)+1,closeIndex)).split(\" \");otag=delimiters[0];ctag=delimiters[1];return closeIndex+close.length-1}if(delimiters){delimiters=delimiters.split(\" \");otag=delimiters[0];ctag=delimiters[1]}for(i=0;i<len;i++){if(state==IN_TEXT){if(tagChange(otag,text,i)){--i;addBuf();state=IN_TAG_TYPE}else{if(text.charAt(i)==\"\\n\"){filterLine(seenTag)}else{buf+=text.charAt(i)}}}else{if(state==IN_TAG_TYPE){i+=otag.length-1;tag=tagTypes[text.charAt(i+1)];tagType=tag?text.charAt(i+1):\"_v\";if(tagType==\"=\"){i=changeDelimiters(text,i);state=IN_TEXT}else{if(tag){i++}state=IN_TAG}seenTag=i}else{if(tagChange(ctag,text,i)){tokens.push({tag:tagType,n:trim(buf),otag:otag,ctag:ctag,i:(tagType==\"/\")?seenTag-ctag.length:i+otag.length});buf=\"\";i+=ctag.length-1;state=IN_TEXT;if(tagType==\"{\"){if(ctag==\"}}\"){i++}else{cleanTripleStache(tokens[tokens.length-1])}}}else{buf+=text.charAt(i)}}}}filterLine(seenTag,true);return tokens};function cleanTripleStache(token){if(token.n.substr(token.n.length-1)===\"}\"){token.n=token.n.substring(0,token.n.length-1)}}function trim(s){if(s.trim){return s.trim()}return s.replace(/^\\s*|\\s*$/g,\"\")}function tagChange(tag,text,index){if(text.charAt(index)!=tag.charAt(0)){return false}for(var i=1,l=tag.length;i<l;i++){if(text.charAt(index+i)!=tag.charAt(i)){return false}}return true}function buildTree(tokens,kind,stack,customTags){var instructions=[],opener=null,token=null;while(tokens.length>0){token=tokens.shift();if(token.tag==\"#\"||token.tag==\"^\"||isOpener(token,customTags)){stack.push(token);token.nodes=buildTree(tokens,token.tag,stack,customTags);instructions.push(token)}else{if(token.tag==\"/\"){if(stack.length===0){throw new Error(\"Closing tag without opener: /\"+token.n)}opener=stack.pop();if(token.n!=opener.n&&!isCloser(token.n,opener.n,customTags)){throw new Error(\"Nesting error: \"+opener.n+\" vs. \"+token.n)}opener.end=token.i;return instructions}else{instructions.push(token)}}}if(stack.length>0){throw new Error(\"missing closing tag: \"+stack.pop().n)}return instructions}function isOpener(token,tags){for(var i=0,l=tags.length;i<l;i++){if(tags[i].o==token.n){token.tag=\"#\";return true}}}function isCloser(close,open,tags){for(var i=0,l=tags.length;i<l;i++){if(tags[i].c==close&&tags[i].o==open){return true}}}Hogan.generate=function(tree,text,options){var code='var _=this;_.b(i=i||\"\");'+walk(tree)+\"return _.fl();\";if(options.asString){return\"function(c,p,i){\"+code+\";}\"}return new Hogan.Template(new Function(\"c\",\"p\",\"i\",code),text,Hogan,options)};function esc(s){return s.replace(rSlash,\"\\\\\\\\\").replace(rQuot,'\\\\\"').replace(rNewline,\"\\\\n\").replace(rCr,\"\\\\r\")}function chooseMethod(s){return(~s.indexOf(\".\"))?\"d\":\"f\"}function walk(tree){var code=\"\";for(var i=0,l=tree.length;i<l;i++){var tag=tree[i].tag;if(tag==\"#\"){code+=section(tree[i].nodes,tree[i].n,chooseMethod(tree[i].n),tree[i].i,tree[i].end,tree[i].otag+\" \"+tree[i].ctag)}else{if(tag==\"^\"){code+=invertedSection(tree[i].nodes,tree[i].n,chooseMethod(tree[i].n))}else{if(tag==\"<\"||tag==\">\"){code+=partial(tree[i])}else{if(tag==\"{\"||tag==\"&\"){code+=tripleStache(tree[i].n,chooseMethod(tree[i].n))}else{if(tag==\"\\n\"){code+=text('\"\\\\n\"'+(tree.length-1==i?\"\":\" + i\"))}else{if(tag==\"_v\"){code+=variable(tree[i].n,chooseMethod(tree[i].n))}else{if(tag===undefined){code+=text('\"'+esc(tree[i])+'\"')}}}}}}}}return code}function section(nodes,id,method,start,end,tags){return\"if(_.s(_.\"+method+'(\"'+esc(id)+'\",c,p,1),c,p,0,'+start+\",\"+end+',\"'+tags+'\")){_.rs(c,p,function(c,p,_){'+walk(nodes)+\"});c.pop();}\"}function invertedSection(nodes,id,method){return\"if(!_.s(_.\"+method+'(\"'+esc(id)+'\",c,p,1),c,p,1,0,0,\"\")){'+walk(nodes)+\"};\"}function partial(tok){return'_.b(_.rp(\"'+esc(tok.n)+'\",c,p,\"'+(tok.indent||\"\")+'\"));'}function tripleStache(id,method){return\"_.b(_.t(_.\"+method+'(\"'+esc(id)+'\",c,p,0)));'}function variable(id,method){return\"_.b(_.v(_.\"+method+'(\"'+esc(id)+'\",c,p,0)));'}function text(id){return\"_.b(\"+id+\");\"}Hogan.parse=function(tokens,text,options){options=options||{};return buildTree(tokens,\"\",[],options.sectionTags||[])},Hogan.cache={};Hogan.compile=function(text,options){options=options||{};var key=text+\"||\"+!!options.asString;var t=this.cache[key];if(t){return t}t=this.generate(this.parse(this.scan(text,options.delimiters),text,options),text,options);return this.cache[key]=t}})(typeof exports!==\"undefined\"?exports:Hogan);window.Hogan=Hogan;}");
            sb.append("if(!window.flurryadapter){var flurryBridgeCtor=function(w){var flurryadapter={};flurryadapter.flurryCallQueue=[];flurryadapter.flurryCallInProgress=false;flurryadapter.callComplete=function(cmd){if(this.flurryCallQueue.length==0){this.flurryCallInProgress=false;return}var adapterCall=this.flurryCallQueue.splice(0,1)[0];this.executeNativeCall(adapterCall);return\"OK\"};flurryadapter.executeCall=function(command){var adapterCall=\"flurry://flurrycall?event=\"+command;var value;for(var i=1;i<arguments.length;i+=2){value=arguments[i+1];if(value==null)continue;adapterCall+=\"&\"+arguments[i]+\"=\"+escape(value)}if(this.flurryCallInProgress)this.flurryCallQueue.push(adapterCall);else this.executeNativeCall(adapterCall)};flurryadapter.executeNativeCall=function(adapterCall){if(adapterCall.length==0)return;this.flurryCallInProgress=true;w.location=adapterCall};return flurryadapter};window.flurryadapter=flurryBridgeCtor(window);}");
            sb.append("if(!window.flurryAdapterAvailable){window.flurryAdapterAvailable=true;if(typeof window.FlurryAdapterReady === 'function'){window.FlurryAdapterReady();} }");
            String strG = hp.g(currentContent);
            sb.append("var content='");
            sb.append(strG);
            sb.append("';var compiled=window.Hogan.compile(document.body.innerHTML);var rendered=compiled.render(JSON.parse(content));document.body.innerHTML=rendered;");
            sb.append("})();");
            if (this.g != null) {
                this.g.loadUrl(sb.toString());
            }
        }
    }

    private synchronized void setMraidJsEnvInitialized(boolean z) {
        this.A = z;
    }

    private synchronized boolean g() {
        return this.A;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void h() {
        if (!g()) {
            j();
            setMraidJsEnvInitialized(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void i() {
        setMraidJsEnvInitialized(false);
    }

    private void j() {
        gd.a(3, this.d, "initializeMraid");
        String str = "{useCustomClose:false,isModal:false,width:undefined,height:undefined,placementType:\"" + (r() ? AdType.INTERSTITIAL : "inline") + "\"}";
        if (this.g != null) {
            this.g.loadUrl("javascript:(function() {var mraidCtor=function(flurryBridge,initState){var mraid={};var STATES=mraid.STATES={LOADING:\"loading\",UNKNOWN:\"unknown\",DEFAULT:\"default\",EXPANDED:\"expanded\",HIDDEN:\"hidden\"};var EVENTS=mraid.EVENTS={ASSETREADY:\"assetReady\",ASSETREMOVED:\"assetRemoved\",ASSETRETIRED:\"assetRetired\",INFO:\"info\",ERROR:\"error\",ORIENTATIONCHANGE:\"orientationChange\",READY:\"ready\",STATECHANGE:\"stateChange\",VIEWABLECHANGE:\"viewableChange\"};var listeners={};var currentState=STATES.LOADING;var expandProperties={width:initState.width,height:initState.height,isModal:initState.isModal,useCustomClose:false};var collapseProperties={};var placementType=initState.placementType;var disable=false;var safeCloseEnabled=false;var closeId=\"flurry-mraid-default-close\";var imgUrl=\"http://flurry.cachefly.net/adSpaceStyles/images/bttn-close-bw.png\";var safeClose=function(){try{if(window.mraid)window.mraid.close();else if(window.flurryadapter)flurryadapter.executeCall(\"adWillClose\");else console.log(\"unable to close\")}catch(error){console.log(\"unable to close: \"+error)}};var makeDefaultClose=function(){var img=document.createElement(\"img\");img.src=imgUrl;img.id=closeId;img.style.position=\"absolute\";img.style.top=\"10px\";img.style.right=\"10px\";img.style.width=\"50px\";img.style.height=\"50px\";img.style.zIndex=1E4;return img};var updateDefaultClose=function(){if(!expandProperties.useCustomClose&&(placementType===\"interstitial\"||currentState===STATES.EXPANDED)){addDefaultClose();flurryBridge.executeCall(\"mraidCloseButtonVisible\", \"useCustomClose\", \"true\");safeCloseEnabled=true;console.log('close button added');}else {removeDefaultClose(); console.log('close button removed');}};var addDefaultClose=function(){var closeButton=document.getElementById(closeId);if(!closeButton){closeButton=makeDefaultClose();document.body.appendChild(closeButton)}};var removeDefaultClose=function(){var closeButton=document.getElementById(closeId);if(closeButton)document.body.removeChild(closeButton)};var setupDefaultCloseHandler=function(){document.body.addEventListener(\"click\",function(e){e=e||window.event;var target=e.target||e.srcElement;if(target.id===closeId)safeClose()})};var contains=function(value,obj){for(var i in obj)if(obj[i]===value)return true;return false};var stringify=function(obj){if(typeof obj==\"object\")if(obj.push){var out=[];for(var p in obj)if(obj.hasOwnProperty(p))out.push(obj[p]);return\"[\"+out.join(\",\")+\"]\"}else{var out=[];for(var p in obj)if(obj.hasOwnProperty(p))out.push(\"'\"+p+\"':\"+obj[p]);return\"{\"+out.join(\",\")+\"}\"}else return new String(obj)};var broadcastEvent=function(){var args=new Array(arguments.length);for(var i=0;i<arguments.length;i++)args[i]=arguments[i];var event=args.shift();try{if(listeners[event])for(var j=0;j<listeners[event].length;j++)if(typeof listeners[event][j]===\"function\")listeners[event][j].apply(undefined,args);else if(typeof listeners[event][j]===\"string\"&&typeof window[listeners[event][j]]===\"function\")window[listeners[event][j]].apply(undefined,args)}catch(e){console.log(e)}};mraid.disable=function(){removeDefaultClose();disable=true};mraid.addEventListener=function(event,listener){if(disable)return;if(!event||!listener)broadcastEvent(EVENTS.ERROR,\"Both event and listener are required.\",\"addEventListener\");else if(!contains(event,EVENTS))broadcastEvent(EVENTS.ERROR,\"Unknown event: \"+event,\"addEventListener\");else if(!listeners[event])listeners[event]=[listener];else listeners[event].push(listener);updateDefaultClose();flurryBridge.executeCall(\"eventListenerAdded\")};mraid.stateChange=function(newState){if(disable)return;if(currentState===newState)return;broadcastEvent(EVENTS.INFO,\"setting state to \"+stringify(newState));var oldState=currentState;currentState=newState;if(oldState===STATES.LOADING&&newState===STATES.DEFAULT){setupDefaultCloseHandler();broadcastEvent(EVENTS.READY)}else if(oldState===STATES.HIDDEN||newState===STATES.HIDDEN)broadcastEvent(EVENTS.VIEWABLECHANGE);else if(oldState===STATES.DEFAULT&&newState===STATES.EXPANDED)updateDefaultClose();else if(newState===STATES.DEFAULT&&oldState===STATES.EXPANDED)updateDefaultClose();broadcastEvent(EVENTS.STATECHANGE,currentState)};mraid.close=function(){if(disable)return;var state=mraid.getState();if(state===STATES.DEFAULT){mraid.stateChange(STATES.HIDDEN);flurryBridge.executeCall(\"adWillClose\")}else if(state===STATES.EXPANDED){mraid.stateChange(STATES.DEFAULT);flurryBridge.executeCall(\"collapse\")}else console.log(\"close() called in state \"+state)};mraid.expand=function(url){if(disable)return;var state=mraid.getState();if(state!==STATES.DEFAULT){console.log(\"expand() called in state \"+state);return}if(placementType===\"interstitial\"){console.log(\"expand() called for placement type \"+placementType);return}if(url)flurryBridge.executeCall(\"open\",\"url\",url);else flurryBridge.executeCall(\"expand\",\"width\",expandProperties.width,\"height\",expandProperties.height);mraid.stateChange(STATES.EXPANDED)};mraid.setExpandProperties=function(properties){if(disable)return;if(typeof properties.width===\"number\"&&!isNaN(properties.width))expandProperties.width=properties.width;if(typeof properties.height===\"number\"&&!isNaN(properties.height))expandProperties.height=properties.height;if(typeof properties.useCustomClose===\"boolean\"){expandProperties.useCustomClose=properties.useCustomClose;updateDefaultClose()}};mraid.getExpandProperties=function(properties){if(disable)return;var ret={};ret.width=expandProperties.width;ret.height=expandProperties.height;ret.isModal=expandProperties.isModal;ret.useCustomClose=expandProperties.useCustomClose;return ret};mraid.getPlacementType=function(){return placementType};mraid.getVersion=function(){if(disable)return\"\";return\"1.0\"};mraid.getState=function(){if(disable)return\"\";return currentState};mraid.isViewable=function(){if(disable)return false;if(mraid.getState()===\"hidden\")return false;else return true};mraid.open=function(url){if(disable)return;try{flurryBridge.executeCall(\"open\",\"url\",url)}catch(e){console.log(e)}};mraid.playVideo=function(url){if(disable){return;}try{flurryBridge.executeCall(\"playVideo\",\"url\",url);}catch(e){console.log(e);}};mraid.removeEventListener=function(event,listener){if(disable)return;if(!event)broadcastEvent(\"error\",\"Must specify an event.\",\"removeEventListener\");else if(listener&&listeners[event])for(var i=0;i<listeners[event].length;i++){if(listeners[event][i]===listener)listeners[event].splice(i,1)}else if(listeners[event])listeners[event]=[]};mraid.useCustomClose=function(use){if(disable)return;if(typeof use===\"boolean\"){expandProperties.useCustomClose=use;updateDefaultClose();if (safeCloseEnabled){flurryBridge.executeCall(\"mraidCloseButtonVisible\", \"useCustomClose\", \"true\");}else{flurryBridge.executeCall(\"mraidCloseButtonVisible\", \"useCustomClose\", use);}}};return mraid};window.mraid=mraidCtor(window.flurryadapter," + str + ");})();");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        gd.a(3, this.d, "activateMraid");
        if (this.g != null) {
            this.g.loadUrl("javascript:if(window.mraid){window.mraid.stateChange(window.mraid.STATES.DEFAULT);}");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(aw awVar) {
        if (awVar.equals(aw.EV_AD_LISTENER_ADDED)) {
            l();
        }
    }

    private void l() {
        if (!(getContext() instanceof Activity)) {
            gd.a(3, this.d, "no activity present");
            return;
        }
        Activity activity = (Activity) getContext();
        if (r()) {
            cn.a(activity, cn.a(), true);
        }
    }

    public eb(Context context, r rVar, ec.a aVar, boolean z) {
        super(context, rVar, aVar);
        this.c = false;
        this.d = eb.class.getSimpleName();
        this.y = false;
        this.a = null;
        this.B = true;
        this.b = new fy<ed>() { // from class: com.flurry.sdk.eb.1
            @Override // com.flurry.sdk.fy
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void notify(final ed edVar) {
                fp.a().a(new Runnable() { // from class: com.flurry.sdk.eb.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        switch (AnonymousClass8.a[edVar.a.ordinal()]) {
                            case 1:
                                eb.this.a(edVar);
                                break;
                            case 2:
                                eb.this.m();
                                break;
                            case 3:
                                eb.this.b(edVar.b);
                                break;
                            case 4:
                                eb.this.a(edVar.b);
                                break;
                            case 5:
                                eb.this.c(edVar.b);
                                break;
                        }
                    }
                });
            }
        };
        this.C = new ec.a() { // from class: com.flurry.sdk.eb.4
            @Override // com.flurry.sdk.ec.a
            public void a() {
                if (eb.this.getCurrentBinding() == 3 && eb.this.e != null) {
                    if (eb.this.isViewAttachedToActivity() && eb.this.a((View) eb.this.e)) {
                        eb.this.removeView(eb.this.e);
                    }
                    eb.this.e.cleanupLayout();
                    eb.this.e = null;
                }
            }

            @Override // com.flurry.sdk.ec.a
            public void b() {
                if (eb.this.getCurrentBinding() == 3 && eb.this.e != null) {
                    if (eb.this.isViewAttachedToActivity() && eb.this.a((View) eb.this.e)) {
                        eb.this.removeView(eb.this.e);
                    }
                    eb.this.e.cleanupLayout();
                    eb.this.e = null;
                }
            }

            @Override // com.flurry.sdk.ec.a
            public void c() {
                if (eb.this.getCurrentBinding() == 3 && eb.this.e != null) {
                    eb.this.e.cleanupLayout();
                    eb.this.e = null;
                }
            }
        };
        setClickable(true);
        this.c = z;
        if (getContext() instanceof Activity) {
            this.i = ((Activity) getContext()).getRequestedOrientation();
        }
        if (getAdUnit() != null) {
            this.w = getAdUnit().supportMRAID;
        } else {
            gd.a(3, this.d, "adunit is Null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        gd.a(3, this.d, "closing ad unity view");
        onViewClose();
    }

    @Override // com.flurry.sdk.ec
    public void cleanupLayout() {
        if (this.e != null) {
            this.e.cleanupLayout();
            this.e = null;
        }
        fz.a().a(this.b);
    }

    @Override // com.flurry.sdk.ec
    protected void onViewLoadTimeout() {
        HashMap map = new HashMap();
        map.put("errorCode", Integer.toString(av.kNoNetworkConnectivity.a()));
        co.a(aw.EV_AD_WILL_CLOSE, map, getContext(), getAdObject(), getAdController(), 0);
    }

    @Override // com.flurry.sdk.ec
    public void initLayout() {
        String strV;
        gd.a(3, this.d, "initLayout: ad creative layout: {width = " + getCurrentAdFrame().adSpaceLayout.adWidth + ", height = " + getCurrentAdFrame().adSpaceLayout.adHeight + ", adFrameIndex = " + getAdController().c() + ", context = " + getContext() + "}");
        cleanupLayout();
        fz.a().a("com.flurry.android.impl.ads.views.AdViewEvent", this.b);
        Context context = getContext();
        removeAllViews();
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestLayout();
        switch (getCurrentBinding()) {
            case 1:
                if (this.g == null) {
                    this.g = new WebView(context);
                    this.g.getSettings().setJavaScriptEnabled(true);
                    this.g.setVerticalScrollBarEnabled(false);
                    this.g.setHorizontalScrollBarEnabled(false);
                    this.g.setBackgroundColor(0);
                    this.g.clearCache(false);
                    this.l = new a();
                    this.g.setWebChromeClient(this.l);
                    this.k = new b();
                    this.g.setWebViewClient(this.k);
                }
                if (this.a != null) {
                    b(this.a);
                } else if (getAdFrameIndex() != 0 || (strV = getAdController().v()) == null) {
                    b(getCurrentDisplay());
                } else {
                    String strC = cv.c(getCurrentDisplay());
                    this.g.loadDataWithBaseURL(strC, strV, "text/html", "utf-8", strC);
                    a(aw.EV_RENDERED, Collections.emptyMap(), getAdController(), 0);
                }
                this.g.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                dismissProgressDialog();
                if (r()) {
                    showProgressDialog();
                }
                s();
                break;
            case 2:
                cy cyVarD = getAdController().d(getAdFrameIndex());
                if (cyVarD != null) {
                    a(cyVarD.f(), ds.VIDEO_AD_TYPE_VAST);
                } else {
                    if (this.g == null) {
                        this.g = new WebView(context);
                        this.g.getSettings().setJavaScriptEnabled(true);
                        this.g.setVerticalScrollBarEnabled(false);
                        this.g.setHorizontalScrollBarEnabled(false);
                        this.g.setBackgroundColor(0);
                        this.g.clearCache(false);
                        this.l = new a();
                        this.g.setWebChromeClient(this.l);
                        this.k = new b();
                        this.g.setWebViewClient(this.k);
                    }
                    this.g.loadDataWithBaseURL("base://url/", getCurrentDisplay(), "text/html", "utf-8", "base://url/");
                    a(aw.EV_RENDERED, Collections.emptyMap(), getAdController(), 0);
                    this.g.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                    dismissProgressDialog();
                    if (r()) {
                        showProgressDialog();
                    }
                    s();
                }
                break;
            case 3:
                a(getCurrentDisplay(), ds.VIDEO_AD_TYPE_CLIPS);
                break;
            default:
                HashMap map = new HashMap();
                map.put("errorCode", Integer.toString(av.kInvalidAdUnit.a()));
                a(aw.EV_RENDER_FAILED, map, getAdController(), 0);
                break;
        }
    }

    private void a(String str, ds dsVar) {
        if (str != null) {
            Context context = getContext();
            if (this.e == null) {
                this.e = dr.a(context, dsVar, getAdObject(), this.C);
                this.e.setVideoUri(a(str));
                this.e.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                this.e.initLayout();
            }
            addView(this.e);
        }
    }

    private Uri a(String str) {
        Uri uri = null;
        try {
            gd.a(3, this.d, "Precaching: Getting video from cache: " + str);
            File fileA = i.a().l().a(getAdObject(), str);
            if (fileA != null) {
                uri = Uri.parse("file://" + fileA.getAbsolutePath());
            }
        } catch (Exception e) {
            gd.a(3, this.d, "Precaching: Error accessing cached file.", e);
        }
        if (uri == null) {
            gd.a(3, this.d, "Precaching: Error using cached file. Loading with url: " + str);
            return Uri.parse(str);
        }
        return uri;
    }

    private void b(final String str) {
        gk gkVar = new gk();
        gkVar.a(str);
        gkVar.a(10000);
        gkVar.b(new gy());
        gkVar.a((gk.a) new gk.a<Void, String>() { // from class: com.flurry.sdk.eb.5
            @Override // com.flurry.sdk.gk.a
            public void a(gk<Void, String> gkVar2, final String str2) {
                gd.a(3, eb.this.d, "Prerender: HTTP status code is:" + gkVar2.e() + " for url: " + str);
                if (gkVar2.c()) {
                    final String strC = cv.c(str);
                    eb.this.a(aw.EV_RENDERED, Collections.emptyMap(), eb.this.getAdController(), 0);
                    fp.a().a(new hq() { // from class: com.flurry.sdk.eb.5.1
                        @Override // com.flurry.sdk.hq
                        public void safeRun() {
                            if (eb.this.g != null) {
                                eb.this.g.loadDataWithBaseURL(strC, str2, "text/html", "utf-8", strC);
                            }
                        }
                    });
                    return;
                }
                fp.a().a(new hq() { // from class: com.flurry.sdk.eb.5.2
                    @Override // com.flurry.sdk.hq
                    public void safeRun() {
                        HashMap map = new HashMap();
                        map.put("errorCode", Integer.toString(av.kPrerenderDownloadFailed.a()));
                        eb.this.a(aw.EV_RENDER_FAILED, map, eb.this.getAdController(), 0);
                    }
                });
            }
        });
        fn.a().a(this, gkVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.flurry.sdk.a aVar) {
        if (this.g != null) {
            gd.a(3, this.d, "Callcomplete " + aVar.c().a.a());
            this.g.loadUrl("javascript:flurryadapter.callComplete('" + aVar.c().a.a() + "');");
        }
    }

    private void a(int i, int i2) {
        if (!(getContext() instanceof Activity)) {
            gd.a(3, this.d, "no activity present");
            return;
        }
        Activity activity = (Activity) getContext();
        if (this.s == null) {
            gd.a(3, this.d, "expand(" + i + "," + i2 + ")");
            i.a().d().b(getContext());
            i.a().e().a(getContext());
            if (this.g != null && -1 != indexOfChild(this.g)) {
                removeView(this.g);
            }
            this.r = activity.getRequestedOrientation();
            if (this.t == null) {
                this.t = new FrameLayout(activity);
                this.t.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                if (this.g != null && this.g.getParent() == null) {
                    this.t.addView(this.g, new FrameLayout.LayoutParams(-1, -1, 17));
                }
            }
            if (this.s == null) {
                this.s = new Dialog(activity, R.style.Theme.Black.NoTitleBar.Fullscreen);
                cx.a(this.s.getWindow());
                this.s.setContentView(this.t, new ViewGroup.LayoutParams(-1, -1));
                this.s.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.flurry.sdk.eb.6
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        gd.a(3, eb.this.d, "extendedWebViewDialog.onDismiss()");
                        if (eb.this.g != null) {
                            eb.this.g.loadUrl("javascript:if(window.mraid){window.mraid.close();};");
                        }
                    }
                });
                this.s.setCancelable(true);
                this.s.show();
            }
            cn.a(activity, cn.a(), true);
        }
    }

    private void b(int i, int i2) {
        if (!(getContext() instanceof Activity)) {
            gd.a(3, this.d, "no activity present");
            return;
        }
        Activity activity = (Activity) getContext();
        i.a().d().c(getContext());
        i.a().e().b(getContext());
        if (this.s != null) {
            gd.a(3, this.d, "collapse(" + i + "," + i2 + ")");
            if (this.s != null && this.s.isShowing()) {
                this.s.hide();
                this.s.setOnDismissListener(null);
                this.s.dismiss();
            }
            this.s = null;
            cn.a(activity, this.r);
            if (this.t != null) {
                if (this.g != null && -1 != this.t.indexOfChild(this.g)) {
                    this.t.removeView(this.g);
                }
                this.t = null;
            }
            if (this.g != null && this.g.getParent() == null) {
                addView(this.g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.g.loadUrl("javascript:(function() { document.getElementById('flurry_interstitial_close').style.display = 'none'; })()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() throws Throwable {
        if (r() && !this.x) {
            this.x = true;
            this.h = new ImageButton(getContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            float f = getResources().getDisplayMetrics().density;
            layoutParams.setMargins(0, (int) (10.0f * f), (int) (10.0f * f), 0);
            layoutParams.height = (int) (50.0f * f);
            layoutParams.width = (int) (f * 50.0f);
            this.h.setLayoutParams(layoutParams);
            el elVar = new el();
            elVar.h();
            Bitmap bitmapE = elVar.e();
            this.h.setBackgroundColor(0);
            this.h.setImageBitmap(bitmapE);
            this.h.setOnClickListener(new View.OnClickListener() { // from class: com.flurry.sdk.eb.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    eb.this.a(aw.EV_AD_WILL_CLOSE, Collections.emptyMap(), eb.this.getAdController(), 0);
                }
            });
            setMraidButtonVisibility(false);
            addView(this.h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(aw awVar, Uri uri) {
        if (awVar.equals(aw.EV_MRAID_CLOSE_BUTTON_VISIBLE)) {
            String queryParameter = uri.getQueryParameter("useCustomClose");
            if (!TextUtils.isEmpty(queryParameter) && queryParameter.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                setMraidButtonVisibility(false);
            } else {
                setMraidButtonVisibility(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(aw awVar) {
        if (awVar.equals(aw.EV_MRAID_NOT_SUPPORTED)) {
            p();
        }
    }

    private void setMraidButtonVisibility(boolean z) {
        if (this.h != null) {
            if (z) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.g.loadUrl("javascript:if(window.mraid && typeof window.mraid.disable === 'function'){window.mraid.disable();}");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.g.loadUrl("javascript:var closeButtonDiv =  document.getElementById('flurry_interstitial_close');if (typeof(closeButtonDiv) == 'undefined' || closeButtonDiv == null){var newdiv = document.createElement('div');var divIdName = 'flurry_interstitial_close';newdiv.setAttribute('id',divIdName);newdiv.innerHTML = '<a href=\"flurry://flurrycall?event=adWillClose\"><div id=\"rtb_close\"><img src=\"http://cdn.flurry.com/adSpaceStyles.dev/images/bttn-close-bw.png\" alt=\"close advertisement\" /></div></a></div>';document.body.appendChild(newdiv);}");
    }

    @Override // com.flurry.sdk.ec
    @TargetApi(11)
    public void onActivityResume() {
        fz.a().a("com.flurry.android.impl.ads.views.AdViewEvent", this.b);
        if (this.g != null && Build.VERSION.SDK_INT >= 11) {
            this.f = true;
            this.g.onResume();
        }
        if (this.e != null) {
            this.e.onActivityResume();
        }
        if (this.e != null) {
            this.f = true;
        }
        this.B = true;
    }

    @Override // com.flurry.sdk.ec
    @TargetApi(11)
    public void onActivityPause() {
        if (this.g != null && Build.VERSION.SDK_INT >= 11) {
            this.g.onPause();
        }
        if (this.e != null) {
            this.e.onActivityPause();
        }
        this.f = false;
        this.B = true;
    }

    @Override // com.flurry.sdk.ec
    @TargetApi(11)
    public void onActivityStop() {
        if (this.z != null && this.z.isShowing()) {
            this.z.dismiss();
            this.z = null;
        }
        if (this.e != null) {
            this.e.onActivityStop();
        }
        dismissProgressDialog();
    }

    @Override // com.flurry.sdk.ec
    @TargetApi(11)
    public void onActivityDestroy() {
        gd.a(3, this.d, "onDestroy");
        if (this.z != null && this.z.isShowing()) {
            this.z.dismiss();
            this.z = null;
        }
        dismissProgressDialog();
        if (this.e != null) {
            this.e.onActivityDestroy();
        }
        if (this.g != null) {
            if (this.l != null) {
                this.l.onHideCustomView();
            }
            if (this.s != null) {
                b(0, 0);
            }
            this.y = false;
            removeView(this.g);
            this.g.stopLoading();
            if (Build.VERSION.SDK_INT >= 11) {
                this.g.onPause();
            }
            this.g.destroy();
            this.g = null;
        }
    }

    public void a(aw awVar, Map<String, String> map, ap apVar, int i) {
        gd.a(3, this.d, "fireEvent(event=" + awVar + ",params=" + map + ")");
        co.a(awVar, map, getContext(), getAdObject(), apVar, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCurrentBinding() {
        return getCurrentAdFrame().binding;
    }

    private String getCurrentContent() {
        return getCurrentAdFrame().content;
    }

    private String getCurrentDisplay() {
        return getCurrentAdFrame().display;
    }

    private String getCurrentFormat() {
        return getCurrentAdFrame().adSpaceLayout.format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean r() {
        return getCurrentFormat().equals(AdCreative.kFormatTakeover);
    }

    private AdFrame getCurrentAdFrame() {
        return getAdController().j();
    }

    boolean a(View view) {
        ViewParent parent = view.getParent();
        return parent != null && parent == this;
    }

    private void s() {
        int iA;
        if (getContext() instanceof Activity) {
            Activity activity = (Activity) getContext();
            if (r() && -1 != (iA = cn.a(activity, getAdUnit().screenOrientation))) {
                cn.a(activity, iA, true);
            }
        }
    }
}
