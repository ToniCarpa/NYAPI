package com.example.nytbrowser.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Link implements Parcelable {
    private String type;
    private String url;
    private String suggested_link_text;

    public Link(String type, String url, String suggested_link_text) {
        this.type = type;
        this.url = url;
        this.suggested_link_text = suggested_link_text;
    }

    protected Link(Parcel in) {
        type = in.readString();
        url = in.readString();
        suggested_link_text = in.readString();
    }

    public static final Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSuggested_link_text() {
        return suggested_link_text;
    }
    public void setSuggested_link_text(String suggested_link_text) {
        this.suggested_link_text = suggested_link_text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(url);
        parcel.writeString(suggested_link_text);

    }
}
