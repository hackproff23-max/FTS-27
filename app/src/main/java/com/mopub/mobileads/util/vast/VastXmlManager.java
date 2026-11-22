package com.mopub.mobileads.util.vast;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.ads.InterstitialAd;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastAbsoluteProgressTracker;
import com.mopub.mobileads.VastFractionalProgressTracker;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes.dex */
class VastXmlManager {
    private static final String CLICK_THROUGH = "ClickThrough";
    private static final String CLICK_TRACKER = "ClickTracking";
    private static final String CLOSE = "close";
    private static final String COMPANION = "Companion";
    private static final String COMPLETE = "complete";
    private static final String EVENT = "event";
    private static final float FIRST_QUARTER_MARKER = 0.25f;
    private static final String FIRST_QUARTILE = "firstQuartile";
    private static final String HEIGHT = "height";
    private static final String IMPRESSION_TRACKER = "Impression";
    private static final String MEDIA_FILE = "MediaFile";
    private static final String MIDPOINT = "midpoint";
    private static final float MID_POINT_MARKER = 0.5f;
    private static final String MP_IMPRESSION_TRACKER = "MP_TRACKING_URL";
    private static final String OFFSET = "offset";
    private static final String PROGRESS = "progress";
    private static final String ROOT_TAG = "MPMoVideoXMLDocRoot";
    private static final String ROOT_TAG_CLOSE = "</MPMoVideoXMLDocRoot>";
    private static final String ROOT_TAG_OPEN = "<MPMoVideoXMLDocRoot>";
    private static final String START = "start";
    private static final int START_TRACKER_THRESHOLD = 2000;
    private static final float THIRD_QUARTER_MARKER = 0.75f;
    private static final String THIRD_QUARTILE = "thirdQuartile";
    private static final String VAST_AD_TAG = "VASTAdTagURI";
    private static final String VIDEO_TRACKER = "Tracking";
    private static final String WIDTH = "width";
    private Document mVastDoc;
    private static Pattern percentagePattern = Pattern.compile("((\\d{1,2})|(100))%");
    private static Pattern absolutePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(.\\d{3})?");

    VastXmlManager() {
    }

    class ImageCompanionAdXmlManager {
        private static final String COMPANION_CLICK_THROUGH = "CompanionClickThrough";
        private static final String COMPANION_STATIC_RESOURCE = "StaticResource";
        private static final String CREATIVE_TYPE = "creativeType";
        private static final String CREATIVE_VIEW = "creativeView";
        private static final String TRACKING_EVENTS = "TrackingEvents";
        private final Node mCompanionNode;

        ImageCompanionAdXmlManager(Node companionNode) throws IllegalArgumentException {
            if (companionNode == null) {
                throw new IllegalArgumentException("Companion node cannot be null");
            }
            this.mCompanionNode = companionNode;
        }

        Integer getWidth() {
            return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "width");
        }

        Integer getHeight() {
            return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "height");
        }

        String getType() {
            Node node = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, COMPANION_STATIC_RESOURCE);
            return XmlUtils.getAttributeValue(node, CREATIVE_TYPE);
        }

        String getImageUrl() {
            Node node = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, COMPANION_STATIC_RESOURCE);
            return XmlUtils.getNodeValue(node);
        }

        String getClickThroughUrl() {
            Node node = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, COMPANION_CLICK_THROUGH);
            return XmlUtils.getNodeValue(node);
        }

        List<String> getClickTrackers() {
            List<String> companionAdClickTrackers = new ArrayList<>();
            Node node = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, TRACKING_EVENTS);
            if (node != null) {
                List<Node> trackerNodes = XmlUtils.getMatchingChildNodes(node, VastXmlManager.VIDEO_TRACKER, "event", Arrays.asList(CREATIVE_VIEW));
                for (Node trackerNode : trackerNodes) {
                    if (trackerNode.getFirstChild() != null) {
                        companionAdClickTrackers.add(trackerNode.getFirstChild().getNodeValue().trim());
                    }
                }
            }
            return companionAdClickTrackers;
        }
    }

    class MediaXmlManager {
        private static final String DELIVERY = "delivery";
        private static final String VIDEO_TYPE = "type";
        private final Node mMediaNode;

        MediaXmlManager(Node mediaNode) throws IllegalArgumentException {
            if (mediaNode == null) {
                throw new IllegalArgumentException("Media node cannot be null");
            }
            this.mMediaNode = mediaNode;
        }

        String getDelivery() {
            return XmlUtils.getAttributeValue(this.mMediaNode, DELIVERY);
        }

        Integer getWidth() {
            return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "width");
        }

        Integer getHeight() {
            return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "height");
        }

        String getType() {
            return XmlUtils.getAttributeValue(this.mMediaNode, "type");
        }

        String getMediaUrl() {
            return XmlUtils.getNodeValue(this.mMediaNode);
        }
    }

    void parseVastXml(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        String documentString = ROOT_TAG_OPEN + xmlString.replaceFirst("<\\?.*\\?>", "") + ROOT_TAG_CLOSE;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setCoalescing(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.mVastDoc = documentBuilder.parse(new InputSource(new StringReader(documentString)));
    }

    String getVastAdTagURI() {
        List<String> uriWrapper = XmlUtils.getStringDataAsList(this.mVastDoc, VAST_AD_TAG);
        if (uriWrapper.size() > 0) {
            return uriWrapper.get(0);
        }
        return null;
    }

    List<String> getImpressionTrackers() {
        List<String> impressionTrackers = XmlUtils.getStringDataAsList(this.mVastDoc, IMPRESSION_TRACKER);
        impressionTrackers.addAll(XmlUtils.getStringDataAsList(this.mVastDoc, MP_IMPRESSION_TRACKER));
        return impressionTrackers;
    }

    @NonNull
    List<VastFractionalProgressTracker> getFractionalProgressTrackers() {
        List<VastFractionalProgressTracker> percentTrackers = new ArrayList<>();
        addQuartileTrackerWithFraction(percentTrackers, getVideoTrackerByAttribute(FIRST_QUARTILE), FIRST_QUARTER_MARKER);
        addQuartileTrackerWithFraction(percentTrackers, getVideoTrackerByAttribute(MIDPOINT), MID_POINT_MARKER);
        addQuartileTrackerWithFraction(percentTrackers, getVideoTrackerByAttribute(THIRD_QUARTILE), THIRD_QUARTER_MARKER);
        List<Node> progressNodes = XmlUtils.getNodesWithElementAndAttribute(this.mVastDoc, VIDEO_TRACKER, "event", "progress");
        for (Node progressNode : progressNodes) {
            String offsetString = XmlUtils.getAttributeValue(progressNode, OFFSET).trim();
            if (isPercentageTracker(offsetString)) {
                String trackingUrl = XmlUtils.getNodeValue(progressNode).trim();
                try {
                    float trackingFraction = Float.parseFloat(offsetString.replace("%", "")) / 100.0f;
                    percentTrackers.add(new VastFractionalProgressTracker(trackingUrl, trackingFraction));
                } catch (NumberFormatException e) {
                    MoPubLog.d(String.format("Failed to parse VAST progress tracker %s", offsetString));
                }
            }
        }
        Collections.sort(percentTrackers);
        return percentTrackers;
    }

    @NonNull
    List<VastAbsoluteProgressTracker> getAbsoluteProgressTrackers() {
        List<VastAbsoluteProgressTracker> trackers = new ArrayList<>();
        List<String> startTrackers = getVideoTrackerByAttribute("start");
        for (String url : startTrackers) {
            trackers.add(new VastAbsoluteProgressTracker(url, 2000));
        }
        List<Node> progressNodes = XmlUtils.getNodesWithElementAndAttribute(this.mVastDoc, VIDEO_TRACKER, "event", "progress");
        for (Node progressNode : progressNodes) {
            String offSetString = XmlUtils.getAttributeValue(progressNode, OFFSET).trim();
            if (isAbsoluteTracker(offSetString)) {
                String trackingUrl = XmlUtils.getNodeValue(progressNode).trim();
                try {
                    Integer trackingMilliseconds = parseAbsoluteOffset(offSetString);
                    if (trackingMilliseconds != null) {
                        trackers.add(new VastAbsoluteProgressTracker(trackingUrl, trackingMilliseconds.intValue()));
                    }
                } catch (NumberFormatException e) {
                    MoPubLog.d(String.format("Failed to parse VAST progress tracker %s", offSetString));
                }
            }
        }
        Collections.sort(trackers);
        return trackers;
    }

    List<String> getVideoCompleteTrackers() {
        return getVideoTrackerByAttribute(COMPLETE);
    }

    List<String> getVideoCloseTrackers() {
        return getVideoTrackerByAttribute("close");
    }

    String getClickThroughUrl() {
        List<String> clickUrlWrapper = XmlUtils.getStringDataAsList(this.mVastDoc, CLICK_THROUGH);
        if (clickUrlWrapper.size() > 0) {
            return clickUrlWrapper.get(0);
        }
        return null;
    }

    List<String> getClickTrackers() {
        return XmlUtils.getStringDataAsList(this.mVastDoc, CLICK_TRACKER);
    }

    String getMediaFileUrl() {
        List<String> urlWrapper = XmlUtils.getStringDataAsList(this.mVastDoc, MEDIA_FILE);
        if (urlWrapper.size() > 0) {
            return urlWrapper.get(0);
        }
        return null;
    }

    List<MediaXmlManager> getMediaXmlManagers() {
        NodeList nodes = this.mVastDoc.getElementsByTagName(MEDIA_FILE);
        List<MediaXmlManager> mediaXmlManagers = new ArrayList<>(nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            mediaXmlManagers.add(new MediaXmlManager(nodes.item(i)));
        }
        return mediaXmlManagers;
    }

    List<ImageCompanionAdXmlManager> getCompanionAdXmlManagers() {
        NodeList nodes = this.mVastDoc.getElementsByTagName(COMPANION);
        List<ImageCompanionAdXmlManager> imageCompanionAdXmlManagers = new ArrayList<>(nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            imageCompanionAdXmlManagers.add(new ImageCompanionAdXmlManager(nodes.item(i)));
        }
        return imageCompanionAdXmlManagers;
    }

    private List<String> getVideoTrackerByAttribute(String attributeValue) {
        return XmlUtils.getStringDataAsList(this.mVastDoc, VIDEO_TRACKER, "event", attributeValue);
    }

    private boolean isPercentageTracker(String progressValue) {
        return !TextUtils.isEmpty(progressValue) && percentagePattern.matcher(progressValue).matches();
    }

    private boolean isAbsoluteTracker(String progressValue) {
        return !TextUtils.isEmpty(progressValue) && absolutePattern.matcher(progressValue).matches();
    }

    private Integer parseAbsoluteOffset(String progressValue) {
        String[] split = progressValue.split(InterstitialAd.SEPARATOR);
        if (split.length != 3) {
            return null;
        }
        return Integer.valueOf((Integer.parseInt(split[0]) * 60 * 60 * 1000) + (Integer.parseInt(split[1]) * 60 * 1000) + ((int) (Float.parseFloat(split[2]) * 1000.0f)));
    }

    private void addQuartileTrackerWithFraction(List<VastFractionalProgressTracker> trackers, List<String> urls, float fraction) {
        for (String url : urls) {
            trackers.add(new VastFractionalProgressTracker(url, fraction));
        }
    }
}
