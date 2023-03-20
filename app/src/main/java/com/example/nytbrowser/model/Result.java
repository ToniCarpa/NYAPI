package com.example.nytbrowser.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Result implements Parcelable {
        private String display_title;
        private String mpaa_rating;
        private int critics_pick;
        private String byline;
        private String headline;
        private String summary_short;
        private String publication_date;
        private String opening_date;
        private String date_updated;
        private Link[] links;
        private Multimedia[] multimedias;
        private String image = String.valueOf(getMultiString().charAt(1));
        private String link = String.valueOf(getLinksString().charAt(0));

        public Result() {
        }

        public Result(String display_title, String mpaa_rating, int critics_pick, String byline, String headline, String summary_short, String publication_date, String opening_date, String date_updated, Link[] links, Multimedia[] multimedias) {
                this.display_title = display_title;
                this.mpaa_rating = mpaa_rating;
                this.critics_pick = critics_pick;
                this.byline = byline;
                this.headline = headline;
                this.summary_short = summary_short;
                this.publication_date = publication_date;
                this.opening_date = opening_date;
                this.date_updated = date_updated;
                this.links = links;
                this.multimedias = multimedias;
        }

        protected Result(Parcel in) {
                display_title = in.readString();
                mpaa_rating = in.readString();
                critics_pick = in.readInt();
                byline = in.readString();
                headline = in.readString();
                summary_short = in.readString();
                publication_date = in.readString();
                opening_date = in.readString();
                date_updated = in.readString();
                multimedias = in.createTypedArray(Multimedia.CREATOR);
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
                @Override
                public Result createFromParcel(Parcel in) {
                        return new Result(in);
                }

                @Override
                public Result[] newArray(int size) {
                        return new Result[size];
                }
        };

        public String getDisplay_title() {
                return display_title;
        }
        public String getByline() {
                return byline;
        }
        public String getHeadline() {
                return headline;
        }
        public String getSummary_short() {
                return summary_short;
        }
        public String getPublication_date() {
                return publication_date;
        }

        public void setDisplay_title(String display_title) {
                this.display_title = display_title;
        }
        public void setByline(String byline) {
                this.byline = byline;
        }
        public void setHeadline(String headline) {
                this.headline = headline;
        }
        public void setSummary_short(String summary_short) {
                this.summary_short = summary_short;
        }
        public void setPublication_date(String publication_date) {
                this.publication_date = publication_date;
        }

        public String getOpening_date() {
                return opening_date;
        }
        public String getDate_updated() {
                return date_updated;
        }
        public String getMpaa_rating() {
                return mpaa_rating;
        }
        public int getCritics_pick() {
                return critics_pick;
        }

        public Link[] getLinks() {
                return links;
        }
        public Multimedia[] getMultimedias() {
                return multimedias;
        }

        public void setImage(String image) {
                this.image = image;
        }

        public void setLink(String link) {
                this.link = link;
        }

        public String getLinksString() {
                String value = "";
                for (int i = 0; i < links.length; i++) {
                        if (i == 0) value = String.valueOf(links[i]);
                        else value = value + ", " +links[i];
                }
                return value;
        }
        public String getMultiString() {
                String value = "";
                for (int i = 0; i < multimedias.length; i++) {
                        if (i == 0) value = String.valueOf(multimedias[i]);
                        else value = value + ", " + multimedias[i];
                }
                return value;
        }
        public void setLinks(Link[] links) {
                this.links = links;
        }
        public void setMultimedias(Multimedia[] multimedias) {
                this.multimedias = multimedias;
        }


        @Override
        public int describeContents() {
                return 0;
        }
        @Override
        public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(display_title);
                parcel.writeString(mpaa_rating);
                parcel.writeInt(critics_pick);
                parcel.writeString(byline);
                parcel.writeString(headline);
                parcel.writeString(summary_short);
                parcel.writeString(publication_date);
                parcel.writeString(opening_date);
                parcel.writeString(date_updated);
                parcel.writeString(link);
                parcel.writeString(image);
                parcel.writeArray(links);
                parcel.writeArray(multimedias);

        }
}
