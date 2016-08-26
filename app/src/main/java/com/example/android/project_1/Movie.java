package com.example.android.project_1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mitemaemmanuel on 26/09/15.
 */
public class Movie implements Parcelable {
    String Id,original_title, poster_path, plot_synopsis, user_rating;
    String[] release_date = new String[3];

    public Movie(String id, String title, String path, String plot_synp, String user_rate, String release_dte)
    {
        Id = id;
        original_title = title;
        poster_path = path;
        plot_synopsis = plot_synp;
        user_rating = user_rate +"/10";
        release_date = release_dte.split("-");
    }

    public String getId(){
        return Id;
    }
    public String getPosterpath(){
        return "http://image.tmdb.org/t/p/w185/"+poster_path;
    }

    private Movie(Parcel in){
        Id  = in.readString();
        original_title= in.readString();
        poster_path = in.readString();
        plot_synopsis = in.readString();
        user_rating = in.readString();
        release_date[0] = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(Id);
        parcel.writeString(original_title);
        parcel.writeString(poster_path);
        parcel.writeString(plot_synopsis);
        parcel.writeString(user_rating);
        parcel.writeString(release_date[0]);

    }

    public final static  Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }

    };
}
