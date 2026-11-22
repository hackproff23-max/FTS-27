package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;

/* JADX INFO: loaded from: classes.dex */
public class GameEntityCreator implements Parcelable.Creator<GameEntity> {
    static void a(GameEntity gameEntity, Parcel parcel, int i) {
        int iH = b.H(parcel);
        b.a(parcel, 1, gameEntity.getApplicationId(), false);
        b.a(parcel, 2, gameEntity.getDisplayName(), false);
        b.a(parcel, 3, gameEntity.getPrimaryCategory(), false);
        b.a(parcel, 4, gameEntity.getSecondaryCategory(), false);
        b.a(parcel, 5, gameEntity.getDescription(), false);
        b.a(parcel, 6, gameEntity.getDeveloperName(), false);
        b.a(parcel, 7, (Parcelable) gameEntity.getIconImageUri(), i, false);
        b.a(parcel, 8, (Parcelable) gameEntity.getHiResImageUri(), i, false);
        b.a(parcel, 9, (Parcelable) gameEntity.getFeaturedImageUri(), i, false);
        b.a(parcel, 10, gameEntity.ky());
        b.a(parcel, 11, gameEntity.kA());
        b.a(parcel, 12, gameEntity.kB(), false);
        b.c(parcel, 13, gameEntity.kC());
        b.c(parcel, 14, gameEntity.getAchievementTotalCount());
        b.c(parcel, 15, gameEntity.getLeaderboardCount());
        b.a(parcel, 17, gameEntity.isTurnBasedMultiplayerEnabled());
        b.a(parcel, 16, gameEntity.isRealTimeMultiplayerEnabled());
        b.c(parcel, 1000, gameEntity.getVersionCode());
        b.a(parcel, 19, gameEntity.getHiResImageUrl(), false);
        b.a(parcel, 18, gameEntity.getIconImageUrl(), false);
        b.a(parcel, 21, gameEntity.isMuted());
        b.a(parcel, 20, gameEntity.getFeaturedImageUrl(), false);
        b.a(parcel, 23, gameEntity.areSnapshotsEnabled());
        b.a(parcel, 22, gameEntity.kz());
        b.a(parcel, 24, gameEntity.getThemeColor(), false);
        b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cl, reason: merged with bridge method [inline-methods] */
    public GameEntity createFromParcel(Parcel parcel) {
        int iG = a.G(parcel);
        int iG2 = 0;
        String strO = null;
        String strO2 = null;
        String strO3 = null;
        String strO4 = null;
        String strO5 = null;
        String strO6 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        boolean zC = false;
        boolean zC2 = false;
        String strO7 = null;
        int iG3 = 0;
        int iG4 = 0;
        int iG5 = 0;
        boolean zC3 = false;
        boolean zC4 = false;
        String strO8 = null;
        String strO9 = null;
        String strO10 = null;
        boolean zC5 = false;
        boolean zC6 = false;
        boolean zC7 = false;
        String strO11 = null;
        while (parcel.dataPosition() < iG) {
            int iF = a.F(parcel);
            switch (a.aH(iF)) {
                case 1:
                    strO = a.o(parcel, iF);
                    break;
                case 2:
                    strO2 = a.o(parcel, iF);
                    break;
                case 3:
                    strO3 = a.o(parcel, iF);
                    break;
                case 4:
                    strO4 = a.o(parcel, iF);
                    break;
                case 5:
                    strO5 = a.o(parcel, iF);
                    break;
                case 6:
                    strO6 = a.o(parcel, iF);
                    break;
                case 7:
                    uri = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 8:
                    uri2 = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 9:
                    uri3 = (Uri) a.a(parcel, iF, Uri.CREATOR);
                    break;
                case 10:
                    zC = a.c(parcel, iF);
                    break;
                case 11:
                    zC2 = a.c(parcel, iF);
                    break;
                case 12:
                    strO7 = a.o(parcel, iF);
                    break;
                case 13:
                    iG3 = a.g(parcel, iF);
                    break;
                case 14:
                    iG4 = a.g(parcel, iF);
                    break;
                case 15:
                    iG5 = a.g(parcel, iF);
                    break;
                case 16:
                    zC3 = a.c(parcel, iF);
                    break;
                case 17:
                    zC4 = a.c(parcel, iF);
                    break;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    strO8 = a.o(parcel, iF);
                    break;
                case 19:
                    strO9 = a.o(parcel, iF);
                    break;
                case 20:
                    strO10 = a.o(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                    zC5 = a.c(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    zC6 = a.c(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                    zC7 = a.c(parcel, iF);
                    break;
                case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                    strO11 = a.o(parcel, iF);
                    break;
                case 1000:
                    iG2 = a.g(parcel, iF);
                    break;
                default:
                    a.b(parcel, iF);
                    break;
            }
        }
        if (parcel.dataPosition() != iG) {
            throw new a.C0039a("Overread allowed size end=" + iG, parcel);
        }
        return new GameEntity(iG2, strO, strO2, strO3, strO4, strO5, strO6, uri, uri2, uri3, zC, zC2, strO7, iG3, iG4, iG5, zC3, zC4, strO8, strO9, strO10, zC5, zC6, zC7, strO11);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: dG, reason: merged with bridge method [inline-methods] */
    public GameEntity[] newArray(int i) {
        return new GameEntity[i];
    }
}
