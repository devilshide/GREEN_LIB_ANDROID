package green.proto.changdo.green_lib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


public class PotDBHandler extends SQLiteOpenHelper {
    public static final String TAG = "PotDBHandler";

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "PlantInfoTable.db";

    public static final boolean DEBUG_PLANT_DB = true;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " ("
                    + FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FeedEntry.COLUMN_LOCATION + " INTEGER, "
                    + FeedEntry.COLUMN_NAME + " TEXT, "
                    + FeedEntry.COLUMN_POT_ID + " INTEGER, "
                    + FeedEntry.COLUMN_AREA_SIZE + " DOUBLE, "
                    + FeedEntry.COLUMN_TEMPERATURE + " DOUBLE, "
                    + FeedEntry.COLUMN_HUMIDITY + " DOUBLE, "
                    + FeedEntry.COLUMN_CO2 + " DOUBLE, "
                    + FeedEntry.COLUMN_LED_PWM + " INTEGER, "
                    + FeedEntry.COLUMN_TIME + " LONG " + ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    public PotDBHandler(Context context) {
        super(context, "/mnt/sdcard/" + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        Log.d(TAG, "onUpgrade(): oldVer = " + oldVer + ", newVer = " + newVer);
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) {
        onUpgrade(db, oldVer, newVer);
    }

    public void insertData(PotInfo info) {
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_LOCATION, info.getLocation());
        values.put(FeedEntry.COLUMN_NAME, info.getName());
        values.put(FeedEntry.COLUMN_POT_ID, info.getId());
        values.put(FeedEntry.COLUMN_AREA_SIZE, info.getAreaSize());
        values.put(FeedEntry.COLUMN_TEMPERATURE, info.getTemperature());
        values.put(FeedEntry.COLUMN_HUMIDITY, info.getHumidity());
        values.put(FeedEntry.COLUMN_CO2, info.getCO2());
        values.put(FeedEntry.COLUMN_LED_PWM, info.getLightIntensity());
        values.put(FeedEntry.COLUMN_TIME, info.getTime());
        this.getWritableDatabase().insert(FeedEntry.TABLE_NAME, null, values);
    }

    public PotInfo[] getData(String plantName) {
        Cursor cursor = this.getReadableDatabase().query(
                FeedEntry.TABLE_NAME,
                new String[] {
                        FeedEntry._ID,
                        FeedEntry.COLUMN_LOCATION,
                        FeedEntry.COLUMN_NAME,
                        FeedEntry.COLUMN_POT_ID,
                        FeedEntry.COLUMN_AREA_SIZE,
                        FeedEntry.COLUMN_TEMPERATURE,
                        FeedEntry.COLUMN_HUMIDITY,
                        FeedEntry.COLUMN_CO2,
                        FeedEntry.COLUMN_LED_PWM,
                        FeedEntry.COLUMN_TIME
                },
                FeedEntry.COLUMN_NAME + "=?",
                new String[] {plantName},
                null,
                null,
                null
        );

        return getRequestedData(cursor);
    }

    public PotInfo[] getData(int location) {
        try {
            Cursor cursor = this.getReadableDatabase().query(
                    FeedEntry.TABLE_NAME,
                    new String[]{
                            FeedEntry._ID,
                            FeedEntry.COLUMN_LOCATION,
                            FeedEntry.COLUMN_NAME,
                            FeedEntry.COLUMN_POT_ID,
                            FeedEntry.COLUMN_AREA_SIZE,
                            FeedEntry.COLUMN_TEMPERATURE,
                            FeedEntry.COLUMN_HUMIDITY,
                            FeedEntry.COLUMN_CO2,
                            FeedEntry.COLUMN_LED_PWM,
                            FeedEntry.COLUMN_TIME
                    },
                    FeedEntry.COLUMN_LOCATION + "=?",
                    new String[]{"" + location},
                    null,
                    null,
                    null
            );

            return getRequestedData(cursor);
        } catch (SQLException e) {
            return null;
        }
    }

    private PotInfo[] getRequestedData(Cursor cursor) {
        if (cursor == null) {
            Log.d(TAG, "getRequestedData(): cursor is NULL");
            return null;
        }
        cursor.moveToFirst();
        PotInfo[] plants = new PotInfo[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(FeedEntry._ID));
            int location = cursor.getInt(cursor.getColumnIndex(FeedEntry.COLUMN_LOCATION));
            String name = cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME));
            int potId = cursor.getInt(cursor.getColumnIndex(FeedEntry.COLUMN_POT_ID));
            double area = cursor.getDouble(cursor.getColumnIndex(FeedEntry.COLUMN_AREA_SIZE));
            double temperature = cursor.getDouble(cursor.getColumnIndex(FeedEntry.COLUMN_TEMPERATURE));
            double humidity = cursor.getDouble(cursor.getColumnIndex(FeedEntry.COLUMN_HUMIDITY));
            double co2 = cursor.getDouble(cursor.getColumnIndex(FeedEntry.COLUMN_CO2));
            int ledIntensity = cursor.getInt(cursor.getColumnIndex(FeedEntry.COLUMN_LED_PWM));
            long time = cursor.getLong(cursor.getColumnIndex(FeedEntry.COLUMN_TIME));

            if (DEBUG_PLANT_DB) Log.d(TAG, "getData(): id = " + id
                    + "\tloc = " + location
                    + "\tname = " + name
                    + "\tpotId = " + potId
                    + "\tarea = " + area
                    + "\ttemperature = " + temperature
                    + "\thumidity = " + humidity
                    + "\ttime = " + time);

            plants[i++] = new PotInfo(location, name, potId, area, temperature, humidity, co2,
                    ledIntensity, time);
        }
        return plants;
    }

    public void deleteData(String name) {
        this.getWritableDatabase().delete(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME + "=?", new String[] {name});
    }

    public void deleteData() {
        this.getWritableDatabase().delete(FeedEntry.TABLE_NAME, null, null);
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "PlantGrowthProgress";
        public static final String COLUMN_LOCATION = "celllocation";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_POT_ID = "potId";
        public static final String COLUMN_AREA_SIZE = "areaSize";
        public static final String COLUMN_TEMPERATURE = "temperature";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_CO2 = "humidity";
        public static final String COLUMN_LED_PWM = "ledpwm";
        public static final String COLUMN_TIME = "time";
    }
}