package com.unity3d.ads.android.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.mopub.common.GpsHelper;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsAdvertisingId {
    private static UnityAdsAdvertisingId a = null;
    private String b = null;
    private boolean c = false;

    private static UnityAdsAdvertisingId a() {
        if (a == null) {
            a = new UnityAdsAdvertisingId();
        }
        return a;
    }

    public static void init(Activity activity) {
        if (!a().fetchAdvertisingId(activity)) {
            UnityAdsAdvertisingId unityAdsAdvertisingIdA = a();
            Context applicationContext = activity.getApplicationContext();
            b bVar = new b(unityAdsAdvertisingIdA, (byte) 0);
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
            if (applicationContext.bindService(intent, bVar, 1)) {
                try {
                    GoogleAdvertisingInfo googleAdvertisingInfoCreate = GoogleAdvertisingInfo.GoogleAdvertisingInfoBinder.Create(bVar.getBinder());
                    unityAdsAdvertisingIdA.b = googleAdvertisingInfoCreate.getId();
                    unityAdsAdvertisingIdA.c = googleAdvertisingInfoCreate.getEnabled(true);
                } catch (Exception e) {
                } finally {
                    applicationContext.unbindService(bVar);
                }
            }
        }
    }

    public static String getAdvertisingTrackingId() {
        return a().b;
    }

    public static boolean getLimitedAdTracking() {
        return a().c;
    }

    public boolean fetchAdvertisingId(Activity activity) {
        boolean z;
        try {
            if (Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", Context.class).invoke(null, activity).equals(0)) {
                Object objInvoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, activity);
                Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                this.b = (String) cls.getMethod("getId", new Class[0]).invoke(objInvoke, new Object[0]);
                this.c = ((Boolean) cls.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(cls, new Object[0])).booleanValue();
                z = true;
            } else {
                UnityAdsDeviceLog.debug("Google Play Services not integrated, using fallback");
                z = false;
            }
            return z;
        } catch (Exception e) {
            UnityAdsDeviceLog.debug("Exception while trying to access Google Play Services " + e);
            return false;
        }
    }

    interface GoogleAdvertisingInfo extends IInterface {
        boolean getEnabled(boolean z);

        String getId();

        public abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {
            public static GoogleAdvertisingInfo Create(IBinder iBinder) {
                if (iBinder == null) {
                    return null;
                }
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return (GoogleAdvertisingInfo) iInterfaceQueryLocalInterface;
                }
                return new a(iBinder);
            }

            @Override // android.os.Binder
            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        String id = getId();
                        parcel2.writeNoException();
                        parcel2.writeString(id);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        boolean enabled = getEnabled(parcel.readInt() != 0);
                        parcel2.writeNoException();
                        parcel2.writeInt(enabled ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
        }
    }
}
