package ramstalk.co.jp.project.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ramstalk.co.jp.project.data.City;
import ramstalk.co.jp.project.data.source.CityDataSource;

/**
 * Created by takuto.sugita on 2017/08/05.
 */

public class CityLocalDataSource implements CityDataSource {

    private static CityLocalDataSource INSTANCE = null;
    private AreaDbHelper dbHelper;

    private static final String TABLE = "city";
    private static final String[] COLS = {"id", "name", "prefecture_id"};

    private CityLocalDataSource(Context context) {
        this.dbHelper = new AreaDbHelper(context);
    }

    public static CityLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CityLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCity(String name, LoadCityCallback callback) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "NAME LIKE ?";
        String[] selectionArgs = { name };
        City city = null;

        Cursor cs = db.query(TABLE, COLS, selection, selectionArgs, null, null, null);

        if (cs != null && cs.getCount() > 0) {
            cs.moveToFirst();
            city = new City();
            city.setId(cs.getString(0));
            city.setName(cs.getString(1));
        }

        if (cs != null) cs.close();
        db.close();

        if (city != null) {
            callback.onCityLoaded(city);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void updateCities(List<City> cityList) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.beginTransaction();
            db.delete(TABLE, null, null);
            ContentValues cv = new ContentValues();
            int sort = 0;
            for (City city : cityList) {
                cv.put("id", city.getId());
                cv.put("name", city.getName());
                cv.put("prefecture_id", city.getPrefectureId());
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
