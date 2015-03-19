package com.example.teamlab.myapplication.parcel;

import android.os.Parcel;
import android.os.Parcelable;

public class ContentParcel implements Parcelable {

    private Integer titleRes;
    private String className;

    @Override
    public int describeContents() {
        return 0;
    }


    private ContentParcel(Parcel in) {
        titleRes = in.readInt();
        className = in.readString();
    }

    public ContentParcel() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.titleRes);
        dest.writeString(this.className);
    }

    public static final Parcelable.Creator<ContentParcel> CREATOR = new Parcelable.Creator<ContentParcel>() {
        public ContentParcel createFromParcel(Parcel in) {
            return new ContentParcel(in);
        }

        public ContentParcel[] newArray(int size) {
            return new ContentParcel[size];
        }
    };

    public Integer getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(Integer titleRes) {
        this.titleRes = titleRes;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
