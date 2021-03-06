package com.codepath.apps.restclienttemplate.models;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity=User.class, parentColumns="id", childColumns="userId"))
public class Tweet {

    @PrimaryKey
    @ColumnInfo
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public long userId;

    @Ignore
    public User user;

    @Ignore
    public boolean hasMedia;

    @Ignore
    public ArrayList<String> embeddedImages;

    @Ignore
    public String firstEmbeddedImage;

    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        if (jsonObject.has("full_text")) {
            tweet.body = jsonObject.getString("full_text");
        } else {
            tweet.body = jsonObject.getString("text");
        }

        tweet.createdAt = tweet.getRelativeTimeAgo(jsonObject.getString("created_at"));
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user;
        tweet.userId = tweet.user.id;

        //Check for embedded media and retrieve the first embedded image if necessary
        JSONObject entities = jsonObject.getJSONObject("entities");
        if(entities.has("media")) {
            JSONArray media = entities.getJSONArray("media");
            tweet.hasMedia = true;
            tweet.embeddedImages = new ArrayList<>();
            for (int i = 0; i < media.length(); i++) {
                tweet.embeddedImages.add(media.getJSONObject(i).getString("media_url_https"));
            }
            tweet.firstEmbeddedImage = tweet.embeddedImages.get(0);
        } else if (jsonObject.has("extended_entities")){
            JSONArray media = jsonObject.getJSONObject("extended_entities").getJSONArray("media");
            tweet.hasMedia = true;
            tweet.embeddedImages = new ArrayList<>();
            for (int i = 0; i < media.length(); i++) {
                tweet.embeddedImages.add(media.getJSONObject(i).getString("media_url_https"));
            }
            tweet.firstEmbeddedImage = tweet.embeddedImages.get(0);

        }

        else {


            tweet.hasMedia = false;
            tweet.embeddedImages = new ArrayList<>();
            tweet.firstEmbeddedImage = "";
        }

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }



    public String getRelativeTimeAgo(String rawJsonDate) {

        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        try {
            long time = sf.parse(rawJsonDate).getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + " h";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + " d";
            }
        } catch (ParseException e) {
            Log.i("Tweet.java", "getRelativeTimeAgo failed");
            e.printStackTrace();
        }

        return "";
    }

}
