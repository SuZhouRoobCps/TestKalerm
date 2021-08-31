package com.example.service;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    private int price;
    private String name;


    public Book(int price, String name) {
        this.price = price;
        this.name = name;
    }

    protected Book(Parcel in) {
        price = in.readInt();
        name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(name);
    }


}
