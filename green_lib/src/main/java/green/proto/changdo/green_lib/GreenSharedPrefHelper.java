package green.proto.changdo.green_lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GreenSharedPrefHelper {
    public static final String TAG = "GreenSharedPrefHelper";

    public static final String DB_PLANT = "plantName";
    public static final String DB_START_DATE = "startDate";
    public static final String DB_POT_COUNT = "potCount";
    public static final String DB_AREA_SIZE = "areaSize";

    private Context mContext = null;
    private SharedPreferences mSharedPrefName, mSharedPrefStartDate, mSharedPrefPotCount,
            mSharedPrefAreaSize;
    private HashMap<Integer, String> mPlantNameMap;
    private HashMap<String, Long> mStartDateMap;
    private HashMap<Integer, Integer> mPotCountMap;
    private HashMap<Integer, Double> mAreaSizeMap;  //TODO: create DB that saves area size for individual pots when needed
    private static GreenSharedPrefHelper sInstance = null;

    private GreenSharedPrefHelper(Context context) {
        mContext = context;
        mSharedPrefName = mContext.getSharedPreferences(DB_PLANT, Context.MODE_PRIVATE);
        mSharedPrefStartDate = mContext.getSharedPreferences(DB_START_DATE, Context.MODE_PRIVATE);
        mSharedPrefPotCount = mContext.getSharedPreferences(DB_POT_COUNT, Context.MODE_PRIVATE);
        mSharedPrefAreaSize = mContext.getSharedPreferences(DB_AREA_SIZE, Context.MODE_PRIVATE);
        loadPlantName();
        loadStartDate();
        loadPotCount();
        loadAreaSize();
    }

    public static GreenSharedPrefHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GreenSharedPrefHelper(context);
        }
        return sInstance;
    }

    private void loadPlantName() {
        mPlantNameMap = new HashMap<Integer, String>();
        Map<String, ?> entries = mSharedPrefName.getAll();
        int size = entries.size();
        if (size == 0) {
            Log.d(TAG, "loadPlantName(): No entries for " + DB_PLANT);
            return;
        }
        Set<String> keys = entries.keySet();
        for(String key : keys) {
            mPlantNameMap.put(Integer.parseInt(key), (String)entries.get(key));
        }
    }

    private void loadStartDate() {
        mStartDateMap = new HashMap<String, Long>();
        Map<String, ?> entries = mSharedPrefStartDate.getAll();
        int size = entries.size();
        if (size == 0) {
            Log.d(TAG, "loadStartDate(): No entries for " + DB_START_DATE);
            return;
        }
        Set<String> keys = entries.keySet();
        for(String key : keys) {
            mStartDateMap.put(key, (Long)entries.get(key));
        }
    }

    private void loadPotCount() {
        mPotCountMap = new HashMap<Integer, Integer>();
        Map<String, ?> entries = mSharedPrefPotCount.getAll();
        int size = entries.size();
        if (size == 0) {
            Log.d(TAG, "loadPotCount(): No entries for " + DB_POT_COUNT);
            return;
        }
        Set<String> keys = entries.keySet();
        for(String key : keys) {
            mPotCountMap.put(Integer.parseInt(key), (Integer)entries.get(key));
        }
    }

    private void loadAreaSize() {
        mAreaSizeMap = new HashMap<>();
        Map<String, ?> entries = mSharedPrefAreaSize.getAll();
        int size = entries.size();
        if (size == 0) {
            Log.d(TAG, "loadAreaSize(): No entries for " + DB_AREA_SIZE);
            return;
        }
        Set<String> keys = entries.keySet();
        for(String key : keys) {
            double val = Double.longBitsToDouble(mSharedPrefAreaSize.getLong(key,
                    Double.doubleToLongBits(0.0)));
            mAreaSizeMap.put(Integer.parseInt(key), val);
        }
    }

    public String getPlantName(int loc) {
        String name = mPlantNameMap.get(loc) == null ? "" : mPlantNameMap.get(loc);
        return name;
    }

    public long getStartDate(int loc, int potId) {
        String key = loc +"_" + potId;
        long date = mStartDateMap.get(key) == null ? 0 : mStartDateMap.get(key);
        return date;
    }

    public int getPotCount(int loc) {
        int count = mPotCountMap.get(loc) == null ? 0 : mPotCountMap.get(loc);
        return count;
    }

    public double getAreaSize(int loc) {
        double size = mAreaSizeMap.get(loc) == null ? 0.0 : mAreaSizeMap.get(loc);
        return size;
    }

    public boolean savePlantName(int loc, String plantName) {
        SharedPreferences.Editor editor = mSharedPrefName.edit();
        editor.putString("" + loc, plantName);

        if (editor.commit()) {
            loadPlantName();
            return true;
        }
        return false;
    }

    public boolean saveStartDate(int loc, int potId, long startDate) {
        SharedPreferences.Editor editor = mSharedPrefStartDate.edit();
        String key = loc +"_" + potId;
        editor.putLong(key, startDate);

        if (editor.commit()) {
            loadStartDate();
            return true;
        }
        return false;
    }

    public boolean savePotCount(int loc, int potCount) {
        SharedPreferences.Editor editor = mSharedPrefPotCount.edit();
        editor.putInt("" + loc, potCount);

        if (editor.commit()) {
            loadPotCount();
            return true;
        }
        return false;
    }

    public boolean saveAreaSize(int loc, double areaSize) {
        SharedPreferences.Editor editor = mSharedPrefAreaSize.edit();
        editor.putLong("" + loc, Double.doubleToRawLongBits(areaSize));

        if (editor.commit()) {
            loadAreaSize();
            return true;
        }
        return false;
    }
}