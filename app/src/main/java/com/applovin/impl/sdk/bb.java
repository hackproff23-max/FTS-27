package com.applovin.impl.sdk;

import com.google.android.vending.expansion.downloader.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class bb {
    private static final List aF = Arrays.asList(Boolean.class, Float.class, Integer.class, Long.class, String.class);
    private static final List aG = new ArrayList();
    public static final bd a = a("is_disabled", false);
    public static final bd b = a("honor_publisher_settings", true);
    public static final bd c = a("device_id", "");
    public static final bd d = a("publisher_id", "");
    public static final bd e = a("device_token", "");
    public static final bd f = a("init_retry_count", 1);
    public static final bd g = a("submit_data_retry_count", 1);
    public static final bd h = a("vr_retry_count", 1);
    public static final bd i = a("fetch_ad_retry_count", 1);
    public static final bd j = a("is_verbose_logging", false);
    public static final bd k = a("api_endpoint", "http://d.applovin.com/");
    public static final bd l = a("adserver_endpoint", "http://a.applovin.com/2.0/");
    public static final bd m = a("next_device_init", 0L);
    public static final bd n = a("get_retry_delay", 10000L);

    @Deprecated
    public static final bd o = a("max_apps_to_send", 100);
    public static final bd p = a("is_app_list_shared", true);
    public static final bd q = a("next_app_list_update", 0L);
    public static final bd r = a("hash_algorithm", "SHA-1");
    public static final bd s = a("short_hash_size", 16);
    public static final bd t = a("http_connection_timeout", 30000);
    public static final bd u = a("fetch_ad_connection_timeout", 30000);
    public static final bd v = a("http_socket_timeout", 20000);
    public static final bd w = a("error_save_count", 15);
    public static final bd x = a("ad_session_minutes", 60);
    public static final bd y = a("ad_request_parameters", "");
    public static final bd z = a("ad_refresh_enabled", true);
    public static final bd A = a("ad_refresh_seconds", 120L);
    public static final bd B = a("mrec_ad_refresh_enabled", true);
    public static final bd C = a("mrec_ad_refresh_seconds", 120L);
    public static final bd D = a("leader_ad_refresh_enabled", true);
    public static final bd E = a("leader_ad_refresh_seconds", 120L);
    public static final bd F = a("plugin_version", "");
    public static final bd G = a("ad_preload_enabled", true);
    public static final bd H = a("ad_resource_caching_enabled", true);
    public static final bd I = a("resource_cache_prefix", "http://vid.applovin.com/,http://pdn.applovin.com/,http://img.applovin.com/,http://d.applovin.com/,http://assets.applovin.com/,http://cdnjs.cloudflare.com/");
    public static final bd J = a("ad_auto_preload_sizes", "BANNER,INTER");
    public static final bd K = a("ad_auto_preload_incent", true);
    public static final bd L = a("session_expiration_time", 300L);

    @Deprecated
    public static final bd M = a("track_installed_apps", true);
    public static final bd N = a("is_tracking_enabled", true);
    public static final bd O = a("force_back_button_enabled_always", false);
    public static final bd P = a("is_first_install", "unknown");
    public static final bd Q = a("countdown_direction", "right_to_left");
    public static final bd R = a("countdown_color", "#C8FFFFFF");
    public static final bd S = a("countdown_height", 2);
    public static final bd T = a("close_fade_in_time", Integer.valueOf(Constants.STATUS_BAD_REQUEST));
    public static final bd U = a("draw_countdown_text", true);
    public static final bd V = a("draw_countdown_bar", true);
    public static final bd W = a("show_close_on_exit", true);
    public static final bd X = a("text_incent_prompt_title", "Earn a Reward");
    public static final bd Y = a("text_incent_prompt_body", "Would you like to watch a video for a reward?");
    public static final bd Z = a("text_incent_prompt_yes_option", "Watch Now");
    public static final bd aa = a("text_incent_prompt_no_option", "No Thanks");
    public static final bd ab = a("text_incent_completion_title", "Video Reward");
    public static final bd ac = a("text_incent_completion_body_success", "You have earned a reward!");
    public static final bd ad = a("text_incent_completion_body_quota_exceeded", "You have already earned the maximum reward for today.");
    public static final bd ae = a("text_incent_completion_body_reward_rejected", "Your reward was rejected.");
    public static final bd af = a("text_incent_completion_body_network_failure", "We were unable to contact the rewards server. Please try again later.");
    public static final bd ag = a("text_incent_completion_close_option", "Okay");
    public static final bd ah = a("show_incent_prepopup", true);
    public static final bd ai = a("show_incent_postpopup", true);
    public static final bd aj = a("preload_capacity_banner", 1);
    public static final bd ak = a("preload_capacity_mrec", 1);
    public static final bd al = a("preload_capacity_inter", 1);
    public static final bd am = a("preload_capacity_leader", 1);
    public static final bd an = a("preload_capacity_incent", 2);
    public static final bd ao = a("dismiss_video_on_error", true);
    public static final bd ap = a("precache_delimiters", ")]',");
    public static final bd aq = a("close_button_size_graphic", 27);
    public static final bd ar = a("close_button_size_video", 30);
    public static final bd as = a("close_button_top_margin_graphic", 10);
    public static final bd at = a("close_button_right_margin_graphic", 10);
    public static final bd au = a("close_button_top_margin_video", 8);
    public static final bd av = a("close_button_right_margin_video", 4);
    public static final bd aw = a("force_back_button_enabled_poststitial", false);
    public static final bd ax = a("force_back_button_enabled_close_button", false);
    public static final bd ay = a("close_button_touch_area", 0);
    public static final bd az = a("is_video_skippable", false);
    public static final bd aA = a("cache_cleanup_enabled", false);
    public static final bd aB = a("cache_file_ttl_seconds", 86400L);
    public static final bd aC = a("cache_max_size_mb", -1);
    public static final bd aD = a("preload_merge_init_tasks_incent", true);
    public static final bd aE = a("preload_merge_init_tasks_inter", true);

    private static bd a(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        }
        if (!aF.contains(obj.getClass())) {
            throw new IllegalArgumentException("Unsupported value type: " + obj.getClass());
        }
        bd bdVar = new bd(str, obj);
        aG.add(bdVar);
        return bdVar;
    }

    public static Collection a() {
        return Collections.unmodifiableList(aG);
    }

    public static int b() {
        return aG.size();
    }
}
