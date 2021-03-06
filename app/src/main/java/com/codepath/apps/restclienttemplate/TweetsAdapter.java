package com.codepath.apps.restclienttemplate;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    Context context;
    List<Tweet> tweets;
    private OnTweetListener onTweetListener;

    public TweetsAdapter(Context context, List<Tweet> tweets, OnTweetListener onTweetListener) {
        this.context = context;
        this.tweets = tweets;
        this.onTweetListener = onTweetListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view, onTweetListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivProfileImage;
        ImageView tweetMedia;
        TextView tvBody;
        TextView tvScreenName;
        TextView createdAt;
        OnTweetListener onTweetListener;

        public ViewHolder(@NonNull View itemView, OnTweetListener onTweetListener) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            createdAt = itemView.findViewById(R.id.createdAt);
            tweetMedia = itemView.findViewById(R.id.tweetMedia);
            this.onTweetListener = onTweetListener;
            itemView.setOnClickListener(this);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            if (tweet.hasMedia) {
                tweetMedia.setVisibility(View.VISIBLE);
                Glide.with(context).load(tweet.firstEmbeddedImage).into(tweetMedia);
            } else {
                tweetMedia.setVisibility(View.GONE);
            }
            createdAt.setText(tweet.createdAt);
        }

        @Override
        public void onClick(View v) {
            onTweetListener.onTweetClick(getAdapterPosition());
        }
    }

    public interface OnTweetListener{
        void onTweetClick(int position);
    }
}
