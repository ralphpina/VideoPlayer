package net.ralphpina.famigo.videoplayer.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralphpina on 4/19/14.
 */
public class VideosManager {

    // list of videos to populat ListView of play
    private List<Video> mVideos = new ArrayList<Video>();
    private int mCurrentVideo = 0;

    private static VideosManager mInstance;

    private VideosManager() {
    }

    public static VideosManager getInstance() {
        if (mInstance == null) {
            mInstance =  new VideosManager();
            return mInstance;
        }

        return mInstance;
    }

    public List<Video> getVideos() {
        return mVideos;
    }

    public void setVideos(List<Video> videos) {
        this.mVideos = videos;
    }

    public void addVideo(Video video) {
        mVideos.add(video);
    }

    public Video getCurrentVideo() {
        return mVideos.get(mCurrentVideo);
    }

    public void setCurrentIndex(int currentVideo) {
        this.mCurrentVideo = currentVideo;
    }

    public Video getNextVideo() {
        if (++mCurrentVideo >= mVideos.size()) {
            mCurrentVideo = 0;
        }
        return mVideos.get(mCurrentVideo);
    }

    public Video getPreviousVideo() {
        if (--mCurrentVideo < 0) {
            mCurrentVideo = (mVideos.size() - 1);
        }
        return mVideos.get(mCurrentVideo);
    }
}
