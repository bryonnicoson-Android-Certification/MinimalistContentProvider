package com.bryonnicoson.minimalistcontentprovider;

import android.database.Cursor;
import android.net.Uri;
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

        // declare and assign query argument values
        String queryUri = Contract.CONTENT_URI.toString();
        String[] projection = new String[] {Contract.CONTENT_PATH};
        String selectionClause;
        String selectionArgs[];
        String sortOrder = null;

        switch (view.getId()) {
            case R.id.button_display_all:
                selectionClause = null;
                selectionArgs = null;
                break;
            case R.id.button_display_first:
                selectionClause = Contract.WORD_ID + " = ?";
                selectionArgs = new String[] {"0"};
                break;
            default:
                selectionClause = null;
                selectionArgs = null;
        }

        Cursor cursor = getContentResolver().query(Uri.parse(queryUri),
                projection, selectionClause, selectionArgs, sortOrder);

        // process cursor result
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(projection[0]);
                do {
                    String word = cursor.getString(columnIndex);
                    mTextview.append("\n" + word);
                } while (cursor.moveToNext());
            } else {
                Log.d(TAG, "onClickDisplayEntries " + getString(R.string.no_data_returned));
                mTextview.append("\n" + getString(R.string.no_data_returned));
            }
        }
    }
}
