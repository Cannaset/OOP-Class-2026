public class SmartCCTV extends SmartDevice implements Streamable, BatteryPowered {
    private int batteryLevel;

    public SmartCCTV(String deviceId, String roomName, int initialBattery) {
        super(deviceId, "Smart CCTV 360", roomName);
        this.batteryLevel = initialBattery;
    }

    @Override
    public void operate() {
        if (!isOn) {
            System.out.println("[INFO] " + deviceName + " sedang mati.");
            return;
        }
        System.out.println("-> " + deviceName + " aktif merekam pergerakan di " + roomName + ".");
    }

    @Override
    public void startLiveStream() {
        if (!isOn) {
            System.out.println("[GAGAL] Nyalakan CCTV terlebih dahulu untuk melihat Live Stream!");
            return;
        }
        if (batteryLevel <= 5) {
            System.out.println("[GAGAL] Baterai kritis (" + batteryLevel + "%). Tidak dapat melakukan streaming!");
            return;
        }
        batteryLevel -= 5;
        System.out.println("[LIVE STREAM] Menampilkan video langsung dari " + roomName + "... (Sisa baterai: " + batteryLevel + "%)");
    }

    @Override
    public int getBatteryPercentage() {
        return batteryLevel;
    }

    @Override
    public void chargeBattery() {
        this.batteryLevel = 100;
        System.out.println("[CHARGING] Baterai " + deviceName + " (" + roomName + ") telah dicas penuh (100%).");
    }

    @Override
    public double getPowerConsumptionWatts() {
        return isOn ? 15.0 : 0.5;
    }

    @Override
    public String getStatusDetail() {
        String state = isOn ? "MENYALA" : "MATI";
        return String.format("[%s] %-22s | Lokasi: %-12s | Status: %-7s | Daya: %5.1f W | Baterai: %d%%",
                deviceId, deviceName, roomName, state, getPowerConsumptionWatts(), batteryLevel);
    }
}