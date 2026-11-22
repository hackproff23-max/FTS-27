package com.unity3d.ads.android.campaign;

import com.google.android.vending.expansion.downloader.Constants;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsCampaign {
    private JSONObject a;
    private String[] b;
    private UnityAdsCampaignStatus c;

    public enum UnityAdsCampaignStatus {
        READY,
        VIEWED,
        PANIC;

        @Override // java.lang.Enum
        public final String toString() {
            return name().toString().toLowerCase();
        }

        public static UnityAdsCampaignStatus getValueOf(String str) {
            if (READY.toString().equals(str.toLowerCase())) {
                return READY;
            }
            if (VIEWED.toString().equals(str.toLowerCase())) {
                return VIEWED;
            }
            return PANIC;
        }
    }

    public UnityAdsCampaign() {
        this.a = null;
        this.b = new String[]{UnityAdsConstants.UNITY_ADS_CAMPAIGN_ENDSCREEN_KEY, "clickUrl", "picture", UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_DOWNLOADABLE_KEY, UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_STREAMING_KEY, "gameId", UnityAdsConstants.UNITY_ADS_CAMPAIGN_GAME_NAME_KEY, "id", UnityAdsConstants.UNITY_ADS_CAMPAIGN_TAGLINE_KEY};
        this.c = UnityAdsCampaignStatus.READY;
    }

    public UnityAdsCampaign(JSONObject jSONObject) {
        this.a = null;
        this.b = new String[]{UnityAdsConstants.UNITY_ADS_CAMPAIGN_ENDSCREEN_KEY, "clickUrl", "picture", UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_DOWNLOADABLE_KEY, UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_STREAMING_KEY, "gameId", UnityAdsConstants.UNITY_ADS_CAMPAIGN_GAME_NAME_KEY, "id", UnityAdsConstants.UNITY_ADS_CAMPAIGN_TAGLINE_KEY};
        this.c = UnityAdsCampaignStatus.READY;
        this.a = jSONObject;
    }

    public String toString() {
        return "<ID: " + getCampaignId() + ", STATUS: " + getCampaignStatus().toString() + ", URL: " + getVideoUrl() + ">";
    }

    public JSONObject toJson() {
        JSONObject jSONObject = this.a;
        try {
            jSONObject.put("status", getCampaignStatus().toString());
            return jSONObject;
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Error creating campaign JSON");
            return null;
        }
    }

    public Boolean shouldCacheVideo() {
        if (a()) {
            try {
                return Boolean.valueOf(this.a.getBoolean(UnityAdsConstants.UNITY_ADS_CAMPAIGN_CACHE_VIDEO_KEY));
            } catch (Exception e) {
                UnityAdsDeviceLog.warning("Key not found for campaign: " + getCampaignId());
            }
        }
        return false;
    }

    public Boolean allowCacheVideo() {
        if (a()) {
            try {
                return Boolean.valueOf(this.a.getBoolean(UnityAdsConstants.UNITY_ADS_CAMPAIGN_ALLOW_CACHE_KEY));
            } catch (Exception e) {
                UnityAdsDeviceLog.warning("Key not found for campaign: " + getCampaignId());
            }
        }
        return false;
    }

    public Boolean shouldBypassAppSheet() {
        if (a()) {
            try {
                return Boolean.valueOf(this.a.getBoolean("bypassAppSheet"));
            } catch (Exception e) {
                UnityAdsDeviceLog.warning("Key not found for campaign: " + getCampaignId());
            }
        }
        return false;
    }

    public String getEndScreenUrl() {
        if (a()) {
            try {
                return this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_ENDSCREEN_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getPicture() {
        if (a()) {
            try {
                return this.a.getString("picture");
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getCampaignId() {
        if (a()) {
            try {
                return this.a.getString("id");
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getGameId() {
        if (a()) {
            try {
                return this.a.getString("gameId");
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getGameName() {
        if (a()) {
            try {
                return this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_GAME_NAME_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getVideoUrl() {
        if (a()) {
            try {
                return this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_DOWNLOADABLE_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getVideoStreamUrl() {
        if (a()) {
            try {
                return this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_STREAMING_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getClickUrl() {
        if (a()) {
            try {
                return this.a.getString("clickUrl");
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getVideoFilename() {
        if (a()) {
            try {
                return getCampaignId() + Constants.FILENAME_SEQUENCE_SEPARATOR + new File(this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_DOWNLOADABLE_KEY)).getName();
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public long getVideoFileExpectedSize() {
        long j = -1;
        if (a()) {
            try {
                try {
                    j = Long.parseLong(this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_SIZE_KEY));
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Could not parse size: " + e.getMessage());
                }
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Not found, returning -1");
            }
        }
        return j;
    }

    public String getTagLine() {
        if (a()) {
            try {
                return this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TAGLINE_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getStoreId() {
        if (this.a.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_STOREID_KEY)) {
            try {
                return this.a.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_STOREID_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Was supposed to use UnityAdsConstants.UNITY_ADS_CAMPAIGN_STOREID_KEY but " + e.getMessage() + " occured");
            }
        }
        if (this.a.has("iTunesId")) {
            try {
                return this.a.getString("iTunesId");
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Was supposed to use UnityAdsConstants.UNITY_ADS_CAMPAIGN_ITUNESID_KEY but " + e2.getMessage() + " occured");
            }
        }
        return null;
    }

    public UnityAdsCampaignStatus getCampaignStatus() {
        return this.c;
    }

    public void setCampaignStatus(UnityAdsCampaignStatus unityAdsCampaignStatus) {
        this.c = unityAdsCampaignStatus;
    }

    public Boolean isViewed() {
        return this.c == UnityAdsCampaignStatus.VIEWED;
    }

    public boolean hasValidData() {
        return a();
    }

    public void clearData() {
        this.a = null;
    }

    private boolean a() {
        if (this.a == null) {
            return false;
        }
        for (String str : this.b) {
            if (!this.a.has(str)) {
                return false;
            }
        }
        return true;
    }
}
