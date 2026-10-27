interface BatteryPowered {
    int getBatteryPercentage();
    void chargeBattery();
}

interface TemperatureControllable {
    void setTemperature(int celsius);
    int getTemperature();
}

interface Streamable {
    void startLiveStream();
}