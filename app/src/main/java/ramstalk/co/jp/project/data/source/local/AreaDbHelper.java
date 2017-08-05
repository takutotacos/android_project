package ramstalk.co.jp.project.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public class AreaDbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "stolab.db";
    private static final String INTEGER = "INTEGER";
    private static final String PRIMARY_KEY = "PRIMARY KEY";
    private static final String TEXT = "TEXT";

    public AreaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 都道府県
        db.execSQL("CREATE TABLE prefecture (id " + INTEGER + " " +  PRIMARY_KEY + ", name " + TEXT + ")");

        // 市区町村
        db.execSQL("CREATE TABLE city (id " + INTEGER + " " +  PRIMARY_KEY + ", name " + TEXT + ", prefecture_id " + INTEGER + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NOP
    }
}
