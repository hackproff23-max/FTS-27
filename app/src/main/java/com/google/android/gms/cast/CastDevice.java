package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.in;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CastDevice implements SafeParcelable {
    public static final Parcelable.Creator<CastDevice> CREATOR = new b();
    private final int CK;
    private String FG;
    String FH;
    private Inet4Address FI;
    private String FJ;
    private String FK;
    private String FL;
    private int FM;
    private List<WebImage> FN;
    private int FO;
    private int FP;

    private CastDevice() {
        this(3, null, null, null, null, null, -1, new ArrayList(), 0, -1);
    }

    CastDevice(int versionCode, String deviceId, String hostAddress, String friendlyName, String modelName, String deviceVersion, int servicePort, List<WebImage> icons, int capabilities, int status) {
        this.CK = versionCode;
        this.FG = deviceId;
        this.FH = hostAddress;
        if (this.FH != null) {
            try {
                InetAddress byName = InetAddress.getByName(this.FH);
                if (byName instanceof Inet4Address) {
                    this.FI = (Inet4Address) byName;
                }
            } catch (UnknownHostException e) {
                this.FI = null;
            }
        }
        this.FJ = friendlyName;
        this.FK = modelName;
        this.FL = deviceVersion;
        this.FM = servicePort;
        this.FN = icons;
        this.FO = capabilities;
        this.FP = status;
    }

    public static CastDevice getFromBundle(Bundle extras) {
        if (extras == null) {
            return null;
        }
        extras.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) extras.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        if (getDeviceId() == null) {
            return castDevice.getDeviceId() == null;
        }
        return in.a(this.FG, castDevice.FG) && in.a(this.FI, castDevice.FI) && in.a(this.FK, castDevice.FK) && in.a(this.FJ, castDevice.FJ) && in.a(this.FL, castDevice.FL) && this.FM == castDevice.FM && in.a(this.FN, castDevice.FN) && this.FO == castDevice.FO && this.FP == castDevice.FP;
    }

    public int getCapabilities() {
        return this.FO;
    }

    public String getDeviceId() {
        return this.FG;
    }

    public String getDeviceVersion() {
        return this.FL;
    }

    public String getFriendlyName() {
        return this.FJ;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0072  */
    public WebImage getIcon(int preferredWidth, int preferredHeight) {
        WebImage webImage;
        WebImage webImage2 = null;
        if (this.FN.isEmpty()) {
            return null;
        }
        if (preferredWidth <= 0 || preferredHeight <= 0) {
            return this.FN.get(0);
        }
        WebImage webImage3 = null;
        for (WebImage webImage4 : this.FN) {
            int width = webImage4.getWidth();
            int height = webImage4.getHeight();
            if (width < preferredWidth || height < preferredHeight) {
                if (width >= preferredWidth || height >= preferredHeight || (webImage2 != null && (webImage2.getWidth() >= width || webImage2.getHeight() >= height))) {
                    webImage4 = webImage2;
                    webImage = webImage3;
                } else {
                    webImage = webImage3;
                }
            } else if (webImage3 == null || (webImage3.getWidth() > width && webImage3.getHeight() > height)) {
                WebImage webImage5 = webImage2;
                webImage = webImage4;
                webImage4 = webImage5;
            } else {
                webImage4 = webImage2;
                webImage = webImage3;
            }
            webImage3 = webImage;
            webImage2 = webImage4;
        }
        if (webImage3 == null) {
            webImage3 = webImage2 != null ? webImage2 : this.FN.get(0);
        }
        return webImage3;
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.FN);
    }

    public Inet4Address getIpAddress() {
        return this.FI;
    }

    public String getModelName() {
        return this.FK;
    }

    public int getServicePort() {
        return this.FM;
    }

    public int getStatus() {
        return this.FP;
    }

    int getVersionCode() {
        return this.CK;
    }

    public boolean hasIcons() {
        return !this.FN.isEmpty();
    }

    public int hashCode() {
        if (this.FG == null) {
            return 0;
        }
        return this.FG.hashCode();
    }

    public boolean isOnLocalNetwork() {
        return !this.FG.startsWith("__cast_nearby__");
    }

    public boolean isSameDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return false;
        }
        if (getDeviceId() == null) {
            return castDevice.getDeviceId() == null;
        }
        return in.a(getDeviceId(), castDevice.getDeviceId());
    }

    public void putInBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
    }

    public String toString() {
        return String.format("\"%s\" (%s)", this.FJ, this.FG);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
