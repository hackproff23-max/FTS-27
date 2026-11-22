package com.millennialmedia.android;

/* JADX INFO: loaded from: classes.dex */
class DTOResizeParameters {
    boolean allowOffScreen;
    String customClosePosition;
    int height;
    int offsetX;
    int offsetY;
    int width;
    int xMax;
    int yMax;

    DTOResizeParameters(float density, int widthIndependentPixels, int heightIndependentPixels, String customClosePosition, int offsetXIndependentPixels, int offsetYIndependentPixels, boolean allowOffScreen, int maxX, int maxY) {
        this.width = (int) (widthIndependentPixels * density);
        this.height = (int) (heightIndependentPixels * density);
        this.customClosePosition = customClosePosition;
        this.offsetX = (int) (offsetXIndependentPixels * density);
        this.offsetY = (int) (offsetYIndependentPixels * density);
        this.allowOffScreen = allowOffScreen;
        this.xMax = maxX;
        this.yMax = maxY;
    }

    public String toString() {
        return String.format("width[%d] height[%d] offsetX[%d] offsetY[%d] allowOffScreen[%b] customClosePosition[%s] maxX[%d] maxY[%d]", Integer.valueOf(this.width), Integer.valueOf(this.height), Integer.valueOf(this.offsetX), Integer.valueOf(this.offsetY), Boolean.valueOf(this.allowOffScreen), this.customClosePosition, Integer.valueOf(this.xMax), Integer.valueOf(this.yMax));
    }
}
