package ramstalk.co.jp.project.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ramstalk.co.jp.project.data.Prefecture;
import ramstalk.co.jp.project.data.source.PrefectureDataSouce;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public class PrefectureLoacalDataSource implements PrefectureDataSouce {

    private static PrefectureLoacalDataSource INSTANCE;
    private AreaDbHelper dbHelper;

    private static final String TABLE = "prefecture";
    private static final String[] COLS = {"id", "name"};

    public PrefectureLoacalDataSource(Context context) {
        this.dbHelper = new AreaDbHelper(context);
    }

    public static PrefectureLoacalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PrefectureLoacalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getPrefectures(LoadPrefecturesCallback callback) {

    }

    @Override
    public void savePrefectures(List<Prefecture> prefectures) {

    }

    @Override
    public void updatePrefectures(List<Prefecture> prefectures) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.beginTransaction();
            db.delete(TABLE, null, null);
            ContentValues cv = new ContentValues();
            int sort = 0;
            for (Prefecture prefecture: prefectures) {
                cv.put("id", prefecture.getId());
                cv.put("name", prefecture.getName());
                db.insert(TABLE, null, cv);
                cv.clear();
                sort++;
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

}
