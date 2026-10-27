public class SmartDoorLock extends SmartDevice implements BatteryPowered {
    private boolean isLocked = true;
    private int batteryLevel;

    public SmartDoorLock(String deviceId, String roomName, int initialBattery) {
        super(deviceId, "Smart Door Lock", roomName);
        this.batteryLevel = initialBattery;
    }

    public void toggleLock() {
        if (!isOn) {
            System.out.println("[GAGAL] Sistem Door Lock sedang nonaktif (OFF). Nyalakan terlebih dahulu!");
            return;
        }
        isLocked = !isLocked;
        batteryLevel = Math.max(0, batteryLevel - 2);
        System.out.println("[DOOR LOCK] Pintu " + roomName + " sekarang: " + (isLocked ? "TERKUNCI" : "TERBUKA") + " (Baterai: " + batteryLevel + "%)");
    }

    @Override
    public void operate() {
        if (!isOn) {
            System.out.println("[INFO] " + deviceName + " sedang nonaktif.");
            return;
        }
        toggleLock();
    }

    @Override
    public int getBatteryPercentage() {
        return batteryLevel;
    }

    @Override
    public void chargeBattery() {
        this.batteryLevel = 100;
        System.out.println("[CHARGING] Baterai " + deviceName + " (" + roomName + ") telah diganti/dicas penuh (100%).");
    }

    @Override
    public double getPowerConsumptionWatts() {
        return isOn ? 2.5 : 0.5;
    }

    @Override
    public String getStatusDetail() {
        String state = isOn ? "MENYALA" : "MATI";
        String lockState = isLocked ? "Terkunci" : "Terbuka";
        return String.format("[%s] %-22s | Lokasi: %-12s | Status: %-7s | Daya: %5.1f W | Baterai: %d%% | Pintu: %s",
                deviceId, deviceName, roomName, state, getPowerConsumptionWatts(), batteryLevel, lockState);
    }
}