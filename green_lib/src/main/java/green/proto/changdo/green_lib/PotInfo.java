package green.proto.changdo.green_lib;


public class PotInfo {

    int mLocation = -1;
    /*
     *   <plant incubator>
     *   =======
     *   |0|1|2| -> growth chamber: cell index - 0,1,2
     *   =======
     *   |3|4|5| -> growth chamber: cell index - 3,4,5
     *   =======
     *   |6    | -> storage chamber: cell index - 6
     *   =======
     *
     *    <cell>
     *     ===
     *     |2|   -> pot index 2
     *     |1|   -> pot index 1
     *     |0|   -> pot index 0
     *     ===
     */
    String mName = "NoName";
    /*
     * Represents each pot's unique id. One unique id can exist per chamber.
     */
    int mId = -1;
    double mAreasize = -1;
    double mTemperature = -1;
    double mHumidity = -1;
    double mCO2 = -1;
    int mLightIntensity = -1;
    long mTime = -1;

    public PotInfo(int loc, String name, int id, double size, double temperature, double humidity,
                   double co2, int light, long time) {
        this.mLocation = loc;
        this.mName = name;
        this.mId = id;
        this.mAreasize = size;
        this.mTemperature = temperature;
        this.mHumidity = humidity;
        this.mCO2 = co2;
        this.mLightIntensity = light;
        this.mTime = time;
    }

    public String getName() {
        return mName;
    }

    public int getLocation() {
        return mLocation;
    }

    public int getId() {
        return mId;
    }

    public double getAreaSize() {
        return mAreasize;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public double getCO2() {
        return mCO2;
    }

    public int getLightIntensity() {
        return mLightIntensity;
    }

    public long getTime() {
        return mTime;
    }
}
