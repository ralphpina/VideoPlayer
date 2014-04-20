package net.ralphpina.famigo.videoplayer.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {

    private static final String VIDEO_LIST_FRAGMENT = "video_list_fragment";

    private VideoListFragmentFragment mVideoListFragmentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the retained fragment on activity restarts
        mVideoListFragmentFragment = (VideoListFragmentFragment) getSupportFragmentManager()
                                                                    .findFragmentByTag(VIDEO_LIST_FRAGMENT);

        // create the fragment and data the first time
        if (mVideoListFragmentFragment == null) {
            // add the fragment
            mVideoListFragmentFragment = new VideoListFragmentFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, mVideoListFragmentFragment, VIDEO_LIST_FRAGMENT).commit();
        }

    }

      // TODO we could have some options in the ActionBar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
