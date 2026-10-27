public class SmartAC extends SmartDevice implements TemperatureControllable {
    private int currentTemp = 24;

    public SmartAC(String deviceId, String roomName) {
        super(deviceId, "Smart AC Inverter", roomName);
    }

    @Override
    public void setTemperature(int celsius) {
        if (!isOn) {
            System.out.println("[GAGAL] Nyalakan AC terlebih dahulu sebelum mengatur suhu!");
            return;
        }
        if (celsius < 16 || celsius > 30) {
            System.out.println("[GAGAL] Suhu AC hanya bisa diatur antara 16°C hingga 30°C.");
            return;
        }
        this.currentTemp = celsius;
        System.out.println("[SUKSES] Suhu " + deviceName + " (" + roomName + ") diatur ke " + currentTemp + "°C.");
    }

    @Override
    public int getTemperature() {
        return currentTemp;
    }

    @Override
    public void operate() {
        if (!isOn) {
            System.out.println("[INFO] " + deviceName + " sedang mati.");
            return;
        }
        System.out.println("-> " + deviceName + " sedang mendinginkan " + roomName + " pada suhu " + currentTemp + "°C.");
    }

    @Override
    public double getPowerConsumptionWatts() {
        return isOn ? 650.0 : 2.0;
    }

    @Override
    public String getStatusDetail() {
        String state = isOn ? "MENYALA" : "MATI";
        return String.format("[%s] %-22s | Lokasi: %-12s | Status: %-7s | Daya: %5.1f W | Suhu: %d°C",
                deviceId, deviceName, roomName, state, getPowerConsumptionWatts(), currentTemp);
    }
}