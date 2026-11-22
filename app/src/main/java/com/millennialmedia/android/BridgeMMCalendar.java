package com.millennialmedia.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMCalendar extends MMJSObject {
    private static final String ADD_EVENT = "addEvent";
    private static final String TAG = BridgeMMCalendar.class.getName();
    private static final String[] mraidCreateCalendarEventDateFormats = {"yyyy-MM-dd'T'HH:mmZZZ", "yyyy-MM-dd'T'HH:mm:ssZZZ"};
    private static final SimpleDateFormat rruleUntilDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

    BridgeMMCalendar() {
    }

    @TargetApi(14)
    public MMJSResponse addEvent(Map<String, String> parameters) {
        MMLog.d(TAG, "addEvent parameters: " + parameters);
        if (Build.VERSION.SDK_INT >= 14) {
            if (parameters != null && parameters.get("parameters") != null) {
                String description = null;
                String location = null;
                Date start = null;
                Date end = null;
                String title = null;
                String status = null;
                String reminder = null;
                String recurrenceRule = null;
                String transparency = null;
                try {
                    JSONObject jsonParameters = new JSONObject(parameters.get("parameters"));
                    try {
                        title = jsonParameters.getString("description");
                    } catch (JSONException e) {
                        MMLog.e(TAG, "Unable to get calendar event description");
                    }
                    try {
                        description = jsonParameters.getString("summary");
                    } catch (JSONException e2) {
                        MMLog.d(TAG, "Unable to get calendar event description");
                    }
                    try {
                        transparency = jsonParameters.getString("transparency");
                    } catch (JSONException e3) {
                        MMLog.d(TAG, "Unable to get calendar event transparency");
                    }
                    try {
                        reminder = jsonParameters.getString("reminder");
                    } catch (JSONException e4) {
                        MMLog.d(TAG, "Unable to get calendar event reminder");
                    }
                    try {
                        location = jsonParameters.getString("location");
                    } catch (JSONException e5) {
                        MMLog.d(TAG, "Unable to get calendar event location");
                    }
                    try {
                        status = jsonParameters.getString("status");
                    } catch (JSONException e6) {
                        MMLog.d(TAG, "Unable to get calendar event status");
                    }
                    try {
                        JSONObject recurrence = jsonParameters.getJSONObject("recurrence");
                        recurrenceRule = convertRecurrence(recurrence);
                    } catch (JSONException e7) {
                        MMLog.d(TAG, "Unable to get calendar event recurrence");
                    }
                    try {
                        start = DateUtils.parseDate(jsonParameters.getString("start"), mraidCreateCalendarEventDateFormats);
                    } catch (DateParseException e8) {
                        MMLog.e(TAG, "Error parsing calendar event start date");
                    } catch (JSONException e9) {
                        MMLog.e(TAG, "Unable to get calendar event start date");
                    }
                    try {
                        end = DateUtils.parseDate(jsonParameters.getString("end"), mraidCreateCalendarEventDateFormats);
                    } catch (DateParseException e10) {
                        MMLog.e(TAG, "Error parsing calendar event end date");
                    } catch (JSONException e11) {
                        MMLog.d(TAG, "Unable to get calendar event end date");
                    }
                    MMLog.d(TAG, String.format("Creating calendar event: title: %s, location: %s, start: %s, end: %s, status: %s, summary: %s, rrule: %s", title, location, start, end, status, description, recurrenceRule));
                    if (title == null || start == null) {
                        MMLog.e(TAG, "Description and start must be provided to create calendar event.");
                        return MMJSResponse.responseWithError("Calendar Event Creation Failed.  Minimum parameters not provided");
                    }
                    Intent intent = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI);
                    if (start != null) {
                        intent.putExtra("beginTime", start.getTime());
                    }
                    if (end != null) {
                        intent.putExtra("endTime", end.getTime());
                    }
                    if (title != null) {
                        intent.putExtra("title", title);
                    }
                    if (description != null) {
                        intent.putExtra("description", description);
                    }
                    if (location != null) {
                        intent.putExtra("eventLocation", location);
                    }
                    if (recurrenceRule != null) {
                        intent.putExtra("rrule", recurrenceRule);
                    }
                    if (status != null) {
                        MMLog.w(TAG, "Calendar addEvent does not support status");
                    }
                    if (transparency != null) {
                        MMLog.w(TAG, "Calendar addEvent does not support transparency");
                    }
                    if (reminder != null) {
                        MMLog.w(TAG, "Calendar addEvent does not support reminder");
                    }
                    Context context = this.contextRef.get();
                    if (context != null) {
                        Utils.IntentUtils.startActivity(context, intent);
                        long adImplId = getAdImplId(parameters.get("PROPERTY_EXPANDING"));
                        MMSDK.Event.intentStarted(context, MMSDK.Event.INTENT_CALENDAR_EVENT, adImplId);
                        return MMJSResponse.responseWithSuccess("Calendar Event Created");
                    }
                } catch (JSONException e12) {
                    MMLog.e(TAG, "Unable to parse calendar addEvent parameters");
                    return MMJSResponse.responseWithError("Calendar Event Creation Failed.  Invalid parameters");
                }
            }
            return null;
        }
        return MMJSResponse.responseWithError("Not supported");
    }

    private String convertRecurrence(JSONObject recurrence) {
        StringBuilder rrule = new StringBuilder();
        try {
            String frequency = recurrence.getString("frequency");
            rrule.append("FREQ=").append(frequency).append(";");
        } catch (JSONException e) {
            MMLog.d(TAG, "Unable to get calendar event recurrence frequency");
        }
        try {
            String expires = rruleUntilDateFormat.format(DateUtils.parseDate(recurrence.getString("expires"), mraidCreateCalendarEventDateFormats));
            rrule.append("UNTIL=").append(expires).append(";");
        } catch (DateParseException e2) {
            MMLog.e(TAG, "Error parsing calendar event recurrence expiration date");
        } catch (JSONException e3) {
            MMLog.d(TAG, "Unable to get calendar event recurrence expiration date");
        }
        try {
            JSONArray mraidDaysInWeek = recurrence.getJSONArray("daysInWeek");
            StringBuilder daysInWeek = new StringBuilder();
            for (int i = 0; i < mraidDaysInWeek.length(); i++) {
                daysInWeek.append(convertMraidDayToRRuleDay(mraidDaysInWeek.getInt(i))).append(",");
            }
            rrule.append("BYDAY=").append((CharSequence) daysInWeek).append(";");
        } catch (JSONException e4) {
            MMLog.d(TAG, "Unable to get days in week");
        }
        try {
            String daysInMonth = recurrence.getString("daysInMonth").replaceAll("\\[", "").replaceAll("\\]", "");
            rrule.append("BYMONTHDAY=").append(daysInMonth).append(";");
        } catch (JSONException e5) {
            MMLog.d(TAG, "Unable to get days in month");
        }
        try {
            String monthsInYear = recurrence.getString("monthsInYear:").replaceAll("\\[", "").replaceAll("\\]", "");
            rrule.append("BYMONTH=").append(monthsInYear).append(";");
        } catch (JSONException e6) {
            MMLog.d(TAG, "Unable to get months in year:");
        }
        try {
            String daysInYear = recurrence.getString("daysInYear");
            rrule.append("BYYEARDAY=").append(daysInYear).append(";");
        } catch (JSONException e7) {
            MMLog.d(TAG, "Unable to get days in year");
        }
        return rrule.toString().toUpperCase();
    }

    private String convertMraidDayToRRuleDay(int mraidDay) {
        switch (mraidDay) {
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            case 6:
                return "SA";
            case 7:
                return "SU";
            default:
                return null;
        }
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if (!ADD_EVENT.equals(name)) {
            return null;
        }
        MMJSResponse response = addEvent(arguments);
        return response;
    }
}
