package com.bryonnicoson.minimalistcontentprovider;

import android.net.Uri;

/**
 * Created by bryon on 3/11/18.
 */

public final class Contract {

    // construct Uri for accessing content provider
    public static final String AUTHORITY = "com.bryonnicoson.minimalistcontentprovider.provider";
    public static final String CONTENT_PATH = "words";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);

    // convenience constants for returning all and single results
    static final int ALL_ITEMS = -2;      // the first lowest value not returned by a method call
    static final String WORD_ID = "id";

    // MIME types for single and multiple records
    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.bryonnicoson.provider.words";
    static final String MULTIPLE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.bryonnicoson.provider.words";

    // empty private constructor to prevent accidental instantiation
    private Contract() {}

}
