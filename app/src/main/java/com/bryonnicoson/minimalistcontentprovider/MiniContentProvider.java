package com.bryonnicoson.minimalistcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static java.lang.Integer.parseInt;

/**
 * Created by bryon on 3/12/18.
 */

public class MiniContentProvider extends ContentProvider {

    public final static String TAG = MiniContentProvider.class.getSimpleName();

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public String[] mData;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mData = context.getResources().getStringArray(R.array.words);
        initializeUriMatching();
        return true;
    }

    /**
     * Query construction.  The parts of a SQL query.
     * @param uri The complete URI.  This cannot be null.
     * @param projection Indicates which columns/attributes to access.
     * @param selection Indicates which rows/records of the objects to access.
     * @param selectionArgs The binding parameters to the previous selection argument.
     * @param sortOrder Whether to sort and, if so, ASC or DESC
     * @return Cursor of results from data storage (in this case, String array mData)
     */

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int id = -1;
        switch (sUriMatcher.match(uri)) {
            case 0:
                id = Contract.ALL_ITEMS;

                // todo: error-catching - here we are assuming required value is in selectionArgs
                if (selection != null){
                    id = parseInt(selectionArgs[0]);
                }
                break;

            case 1:
                id = parseInt(uri.getLastPathSegment());
                break;

            case UriMatcher.NO_MATCH:
                Log.d(TAG, "NO MATCH FOR THIS URI IN SCHEME.");
                id = -1;
                break;

            default:
                // todo: error handling
                Log.d(TAG, "INVALID URI - URI NOT RECOGNIZED.");
                id = -1;
        }
        Log.d(TAG, "query: " + id);
        return populateCursor(id);
    }

    /**
     * Method to create and return a cursor from mData using supplied id
     * @param id  the query
     * @return  the resulting cursor
     */
    private Cursor populateCursor(int id) {
        MatrixCursor cursor = new MatrixCursor(new String[] { Contract.CONTENT_PATH });
        if (id == Contract.ALL_ITEMS) {
            for (int i = 0; i < mData.length; i++) {
                String word = mData[i];
                cursor.addRow(new Object[]{word});
            }
        } else if (id >= 0){
            String word = mData[id];
            cursor.addRow(new Object[]{word});
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 0:
                return Contract.MULTIPLE_RECORD_MIME_TYPE;
            case 1:
                return Contract.SINGLE_RECORD_MIME_TYPE;
            default:
                // alternatively, throw an exception
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.e(TAG, "Not implemented: insert uri: " + uri.toString());
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e(TAG, "Not implemented: delete uri: " + uri.toString());
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString());
        return 0;
    }

    private void initializeUriMatching(){
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0);
    }
}
