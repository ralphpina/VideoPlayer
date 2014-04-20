package net.ralphpina.famigo.videoplayer.app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ralphpina on 4/19/14.
 */
public class VideoListAdapter extends BaseAdapter {

    private Activity mActivity;
    private LayoutInflater mInflater;
    private List<Video> videos = new ArrayList<Video>();

    private class ViewHolder {
        ImageView videoThumbnail;
        TextView videoTitle;
        TextView likesCount;
        TextView viewCount;
    }

    public VideoListAdapter(Activity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    public void addVideos(Collection<? extends Video> collection) {
        videos.addAll(collection);
    }

    @Override
    public Video getItem(int position) {
        return videos.get(position);
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.cell_video_info, null);

            viewHolder.videoThumbnail = (ImageView) convertView.findViewById(R.id.videoThumbnail);
            viewHolder.videoTitle = (TextView) convertView.findViewById(R.id.videoTitle);
            viewHolder.likesCount = (TextView) convertView.findViewById(R.id.likesTextView);
            viewHolder.viewCount = (TextView) convertView.findViewById(R.id.viewCountTextView);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mActivity).load(videos.get(position).getThumbNailUrl()).into(viewHolder.videoThumbnail);
        viewHolder.videoThumbnail.setTag(position);
        viewHolder.videoTitle.setText(videos.get(position).getTitle());
        viewHolder.videoTitle.setTag(position);
        viewHolder.likesCount.setText(videos.get(position).getLikeCount());
        viewHolder.likesCount.setTag(position);
        viewHolder.viewCount.setText(Integer.toString(videos.get(position).getViewCount()));
        viewHolder.viewCount.setTag(position);

        return convertView;
    }
}
