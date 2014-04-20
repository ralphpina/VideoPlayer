package net.ralphpina.famigo.videoplayer.app;

/**
 * Created by ralphpina on 4/18/14.
 */
public class Video {

    private String mTitle; //title
    private String mThumbNailUrl; // thumbnail url
    private String mContentData; // url to data
    private String mLikeCount; // video likes
    private int mViewCount; // times watched

    public Video(String title, String thumbNailUrl, String contentData, String likeCount, int viewCount) {
        mTitle = title;
        mThumbNailUrl = thumbNailUrl;
        mContentData = contentData;
        mLikeCount = likeCount;
        mViewCount = viewCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getThumbNailUrl() {
        return mThumbNailUrl;
    }

    public String getContentData() {
        return mContentData;
    }

    public String getLikeCount() {
        return mLikeCount;
    }

    public int getViewCount() {
        return mViewCount;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
