package com.example.nytbrowser.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Multimedia implements Parcelable {
    private String type;
    private String src;
    private int width;
    private int height;

    public Multimedia(String type, String src, int width, int height) {
        this.type = type;
        this.src = src;
        this.width = width;
        this.height = height;
    }

    protected Multimedia(Parcel in) {
        type = in.readString();
        src = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    public static final Creator<Multimedia> CREATOR = new Creator<Multimedia>() {
        @Override
        public Multimedia createFromParcel(Parcel in) {
            return new Multimedia(in);
        }

        @Override
        public Multimedia[] newArray(int size) {
            return new Multimedia[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(src);
        parcel.writeInt(width);
        parcel.writeInt(height);
    }
}
