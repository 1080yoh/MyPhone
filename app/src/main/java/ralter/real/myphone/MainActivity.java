package ralter.real.myphone;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ralter.real.myphone.adapters.FragmentManagePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_OK = 0;
    private static final int PERMISSION_FAILED = 1;

    private FragmentManagePagerAdapter mFragmentManagePagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);

        mFragmentManagePagerAdapter = new FragmentManagePagerAdapter(getSupportFragmentManager(), this);

        mHandler = new MyHandler(this);
        checkAndRequestPermissions();

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 1) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(MainActivity.this, "Permision Read CallLog File is Granted", Toast.LENGTH_SHORT).show();
//                mHandler.sendEmptyMessage(PERMISSION_OK);
//            } else {
//                Toast.makeText(MainActivity.this, "Permision Read CallLog is Denied", Toast.LENGTH_SHORT).show();
//                mHandler.sendEmptyMessage(PERMISSION_FAILED);
//            }
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//        Log.e("RALTER", "Request code = " + requestCode);
//    }

    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.READ_CALL_LOG
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
            mHandler.sendEmptyMessage(PERMISSION_FAILED);
        } else {
            mHandler.sendEmptyMessage(PERMISSION_OK);
        }
    }

    private class MyHandler extends Handler {

        WeakReference<MainActivity> mainActivity;

        public MyHandler(MainActivity mainActivity) {
            this.mainActivity = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PERMISSION_OK:
                    Log.e("RALTER", "PERMISION ok");
                    mViewPager.setAdapter(mFragmentManagePagerAdapter);
                    mTabLayout.setupWithViewPager(mViewPager);
                    break;
                case PERMISSION_FAILED:
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        checkAndRequestPermissions();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            Log.e("RALTER", "sendpermision " + msg.what);
        }
    }
}
