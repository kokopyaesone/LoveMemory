package kopyae.lovememory.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import kopyae.lovememory.com.database.AppDatabase;
import kopyae.lovememory.com.database.DatabaseInitializer;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = TestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));

        Log.d(TAG, "onCreate: "+AppDatabase.getAppDatabase(this).userDao().findByName("Ko Ko","Pyae Sone"));


    }
}
