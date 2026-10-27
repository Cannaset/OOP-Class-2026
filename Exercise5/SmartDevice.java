public abstract class SmartDevice {
    protected String deviceId;
    protected String deviceName;
    protected String roomName;
    protected boolean isOn;

    public SmartDevice(String deviceId, String deviceName, String roomName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.roomName = roomName;
        this.isOn = false;
    }

    public void turnOn() {
        if (isOn) {
            System.out.println("[INFO] " + deviceName + " di " + roomName + " sudah dalam keadaan MENYALA.");
        } else {
            this.isOn = true;
            System.out.println("[ON] " + deviceName + " di " + roomName + " berhasil DINYALAKAN.");
        }
    }

    public void turnOff() {
        if (!isOn) {
            System.out.println("[INFO] " + deviceName + " di " + roomName + " sudah dalam keadaan MATI.");
        } else {
            this.isOn = false;
            System.out.println("[OFF] " + deviceName + " di " + roomName + " berhasil DIMATIKAN.");
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public String getDeviceName() {
        return deviceName + " (" + roomName + ")";
    }

    public abstract void operate();
    public abstract double getPowerConsumptionWatts();
    public abstract String getStatusDetail();
}