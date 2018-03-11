package com.bryonnicoson.minimalistcontentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextview = findViewById(R.id.textview);
    }

    public void onClickDisplayEntries(View view){

        switch(view.getId()){
            case R.id.button_display_all:
                Log.d(TAG, "Yay, " + R.id.button_display_all + " was clicked!");
                break;
            case R.id.button_display_first:
                Log.d(TAG, "Yay, " + R.id.button_display_first + " was clicked!");
                break;
            default:
                Log.wtf(TAG, "Error. This should never happen.");
        }

        mTextview.append("\nGo, Cubs, Go!");
    }
}
