package green.proto.changdo.green_lib;

public class ArduinoIntent {

    public static final String ACTION_PDLC_ON   = "PDLC_ON";
    public static final String ACTION_PDLC_OFF  = "PDLC_OFF";
    public static final String ACTION_HUMIDIFIER_ON     = "HUMIDIFIER_ON";
    public static final String ACTION_HUMIDIFIER_OFF    = "HUMIDIFIER_OFF";
    public static final String ACTION_LED_ON_ALL    = "LED_ON_ALL";
    public static final String ACTION_LED_OFF_ALL   = "LED_OFF_ALL";
    public static final String ACTION_LED_ON_FL1    = "LED_ON_FL1";
    public static final String ACTION_LED_OFF_FL1   = "LED_OFF_FL1";
    public static final String ACTION_LED_ON_FL2    = "LED_ON_FL2";
    public static final String ACTION_LED_OFF_FL2   = "LED_OFF_FL2";
    public static final String ACTION_LED_ON_FL3    = "LED_ON_FL3";
    public static final String ACTION_LED_OFF_FL3   = "LED_OFF_FL3";
    public static final String ACTION_GROWTH_LED_ON_ALL     = "GROWTH_LED_ON_ALL";
    public static final String ACTION_GROWTH_LED_OFF_ALL    = "GROWTH_LED_OFF_ALL";
    public static final String ACTION_CAMERA_LED_ON_ALL     = "CAMERA_LED_ON_ALL";
    public static final String ACTION_CAMERA_LED_OFF_ALL    = "CAMERA_LED_OFF_ALL";
    public static final String ACTION_REVERT_LED_STATE      = "REVERT_LED_STATE";
    public static final String ACTION_BACKLIGHT_ON  = "BACKLIGHT_ON_ALL";
    public static final String ACTION_BACKLIGHT_OFF = "BACKLIGHT_OFF_ALL";
    public static final String ACTION_LED_INTENSITY = "LED_INTENSITY";
    public static final String ACTION_DIM_LIGHT     = "DIM_LIGHT";
    public static final String ACTION_DIM_LIGHT_BRIGHT     = "DIM_LIGHT_BRIGHT";
    public static final String ACTION_LED_ON_RGB    = "LED_RGB";
    public static final String ACTION_LED_ON_PURE_WHITE    = "LED_P_WHITE";
    public static final String ACTION_LED_ON_WARM_WHITE    = "LED_W_WHITE";

    public static final String ACTION_RECEIVED_NFC = "RECEIVED_NFC";
    public static final String ACTION_RECEIVED_TEMPERATURE = "RECEIVED_TEMPERATURE";
    public static final String ACTION_RECEIVED_HUMIDITY = "RECEIVED_HUMIDITY";
    public static final String ACTION_RECEIVED_CO2 = "RECEIVED_CO2";

    public static final String EXTRA_NFC_RESULTS = "EXTRA_RESULTS_NFC";
    public static final String EXTRA_TEMPERATURE_RESULTS = "EXTRA_RESULTS_TEMPERATURE";
    public static final String EXTRA_RESULTS_HUMIDITY = "EXTRA_RESULTS_HUMIDITY";
    public static final String EXTRA_RESULTS_CO2 = "EXTRA_RESULTS_CO2";

    public static final String EXTRA_SET_LED_COLOR = "EXTRA_SET_COLOR";
    public static final String EXTRA_SET_LED_INTENSITY = "EXTRA_SET_PWM";

    private ArduinoIntent() {
    }
}

