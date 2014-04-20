package net.ralphpina.famigo.videoplayer.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedByteArray;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 */
public class VideoListFragmentFragment extends Fragment implements AbsListView.OnItemClickListener {

    private static final String TAG = "VideoListFragmentFragment";
    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private VideoListAdapter mAdapter;

    /**
     * Manager to hold our videos so we can share them between the Fragment
     * and the Activity playing them
     */
    private VideosManager mVideosManager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VideoListFragmentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVideosManager = VideosManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videolistfragment, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        if (mVideosManager.getVideos().size() > 0) {
            setVideoAdapter();
        } else {
            // Go get some videos
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://gdata.youtube.com") // The base API endpoint.
                    .build();
            VideoService youTube = restAdapter.create(VideoService.class);
            youTube.videos(new MyCallBack());
        }

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mVideosManager.setCurrentIndex(position);
        startActivity(new Intent(getActivity(), VideoPlayingActivity.class));
    }

    private class MyCallBack implements Callback<Response> {

        @Override
        public void success(Response response, Response response2) {

            TypedByteArray byteArray = (TypedByteArray) response.getBody();
            byte[] bodyBytes = byteArray.getBytes();
            String bodyMime = byteArray.mimeType();
            String bodyCharset = MimeUtil.parseCharset(bodyMime);

            try {
                JSONObject jsonObject = new JSONObject(new String(bodyBytes, bodyCharset));
                JSONArray responseArray = jsonObject.getJSONObject("data").getJSONArray("items");

                for (int i = 0; i < responseArray.length(); i++) {

                    JSONObject obj = responseArray.getJSONObject(i);
                    mVideosManager.addVideo(new Video(obj.getString("title"),
                                         obj.getJSONObject("thumbnail").getString("hqDefault"),
                                         obj.getJSONObject("content").getString("6"),
                                         obj.getString("likeCount"),
                                         obj.getInt("viewCount")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            setVideoAdapter();
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e(TAG, error.getMessage());
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setVideoAdapter() {
        mAdapter = new VideoListAdapter(getActivity());
        mAdapter.addVideos(mVideosManager.getVideos());

        ((AbsListView) mListView).setAdapter(mAdapter);
        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(VideoListFragmentFragment.this);
    }

}
