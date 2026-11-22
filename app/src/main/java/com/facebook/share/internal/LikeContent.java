package com.facebook.share.internal;

import android.os.Parcel;
import com.facebook.share.model.ShareModel;
import com.facebook.share.model.ShareModelBuilder;
import com.facebook.share.widget.LikeView;

/* JADX INFO: loaded from: classes.dex */
public class LikeContent implements ShareModel {
    private final String objectId;
    private final LikeView.ObjectType objectType;

    private LikeContent(Builder builder) {
        this.objectId = builder.objectId;
        this.objectType = builder.objectType;
    }

    LikeContent(Parcel in) {
        this.objectId = in.readString();
        this.objectType = LikeView.ObjectType.fromInt(in.readInt());
    }

    public String getObjectId() {
        return this.objectId;
    }

    public LikeView.ObjectType getObjectType() {
        return this.objectType;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.objectId);
        out.writeInt(this.objectType.getValue());
    }

    public static class Builder implements ShareModelBuilder<LikeContent, Builder> {
        private String objectId;
        private LikeView.ObjectType objectType = LikeView.ObjectType.UNKNOWN;

        public Builder setObjectId(String objectId) {
            this.objectId = objectId;
            return this;
        }

        public Builder setObjectType(LikeView.ObjectType objectType) {
            if (objectType == null) {
                objectType = LikeView.ObjectType.UNKNOWN;
            }
            this.objectType = objectType;
            return this;
        }

        @Override // com.facebook.share.ShareBuilder
        public LikeContent build() {
            return new LikeContent(this);
        }

        @Override // com.facebook.share.model.ShareModelBuilder
        public Builder readFrom(LikeContent content) {
            return content == null ? this : setObjectId(content.getObjectId()).setObjectType(content.getObjectType());
        }

        @Override // com.facebook.share.model.ShareModelBuilder
        public Builder readFrom(Parcel parcel) {
            return readFrom((LikeContent) parcel.readParcelable(LikeContent.class.getClassLoader()));
        }
    }
}
