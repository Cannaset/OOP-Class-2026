import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi daftar perangkat (Polymorphic List)
        List<SmartDevice> devices = new ArrayList<>();
        devices.add(new SmartAC("AC-01", "Kamar Utama"));
        devices.add(new SmartAC("AC-02", "Ruang Tamu"));
        devices.add(new SmartCCTV("CAM-01", "Teras Depan", 25));
        devices.add(new SmartDoorLock("LCK-01", "Pintu Utama", 60));

        boolean running = true;

        while (running) {
            System.out.println("\n=======================================================");
            System.out.println("          DASHBOARD KONTROL NUSAHOME IoT               ");
            System.out.println("=======================================================");
            System.out.println("1. Cek Status Semua Perangkat & Total Daya Listrik");
            System.out.println("2. Pilih & Kontrol Perangkat Tertentu (On/Off/Fitur)");
            System.out.println("3. Nyalakan Semua Perangkat Sekaligus (All ON)");
            System.out.println("4. Matikan Semua Perangkat Sekaligus (Mode Hemat/Keluar)");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih menu (0-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- STATUS PERANGKAT SAAT INI ---");
                    double totalPower = 0;
                    for (int i = 0; i < devices.size(); i++) {
                        SmartDevice d = devices.get(i);
                        System.out.println((i + 1) + ". " + d.getStatusDetail());
                        totalPower += d.getPowerConsumptionWatts();
                    }
                    System.out.println("-------------------------------------------------------");
                    System.out.printf("Total Konsumsi Daya Rumah: %.1f Watt\n", totalPower);
                    break;

                case 2:
                    System.out.println("\n--- PILIH PERANGKAT ---");
                    for (int i = 0; i < devices.size(); i++) {
                        SmartDevice d = devices.get(i);
                        String badge = d.isOn() ? "[ON]" : "[OFF]";
                        System.out.println((i + 1) + ". " + badge + " " + d.getDeviceName());
                    }
                    System.out.print("Masukkan nomor perangkat (1-" + devices.size() + "): ");
                    int devIndex = scanner.nextInt() - 1;

                    if (devIndex >= 0 && devIndex < devices.size()) {
                        controlSingleDevice(scanner, devices.get(devIndex));
                    } else {
                        System.out.println("[ERROR] Nomor perangkat tidak valid!");
                    }
                    break;

                case 3:
                    System.out.println("\n--- MENYALAKAN SEMUA PERANGKAT ---");
                    for (SmartDevice d : devices) {
                        d.turnOn();
                    }
                    break;

                case 4:
                    System.out.println("\n--- MEMATIKAN SEMUA PERANGKAT ---");
                    for (SmartDevice d : devices) {
                        d.turnOff();
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan NusaHome IoT!");
                    break;

                default:
                    System.out.println("[ERROR] Pilihan tidak tersedia!");
            }
        }
        scanner.close();
    }

    private static void controlSingleDevice(Scanner scanner, SmartDevice device) {
        boolean back = false;
        while (!back) {
            System.out.println("\n-------------------------------------------------------");
            System.out.println("MENGONTROL: " + device.getStatusDetail());
            System.out.println("-------------------------------------------------------");
            System.out.println("1. " + (device.isOn() ? "Matikan Perangkat (Turn OFF)" : "Nyalakan Perangkat (Turn ON)"));
            System.out.println("2. Jalankan Fungsi Utama (Operate)");

            if (device instanceof TemperatureControllable) {
                System.out.println("3. Atur Suhu Temperatur (Fitur Khusus AC)");
            }
            if (device instanceof Streamable) {
                System.out.println("4. Tonton Live Stream (Fitur Khusus Kamera)");
            }
            if (device instanceof BatteryPowered) {
                System.out.println("5. Cas Baterai Sampai Penuh (Fitur Perangkat Berbaterai)");
            }
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih aksi: ");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    if (device.isOn()) {
                        device.turnOff();
                    } else {
                        device.turnOn();
                    }
                    break;

                case 2:
                    device.operate();
                    break;

                case 3:
                    if (device instanceof TemperatureControllable) {
                        System.out.print("Masukkan suhu baru (16 - 30 °C): ");
                        int newTemp = scanner.nextInt();
                        ((TemperatureControllable) device).setTemperature(newTemp);
                    } else {
                        System.out.println("[ERROR] Perangkat ini tidak memiliki pengatur suhu!");
                    }
                    break;

                case 4:
                    if (device instanceof Streamable) {
                        ((Streamable) device).startLiveStream();
                    } else {
                        System.out.println("[ERROR] Perangkat ini tidak mendukung Live Stream!");
                    }
                    break;

                case 5:
                    if (device instanceof BatteryPowered) {
                        ((BatteryPowered) device).chargeBattery();
                    } else {
                        System.out.println("[ERROR] Perangkat ini menggunakan listrik langsung, bukan baterai!");
                    }
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println("[ERROR] Pilihan tidak valid!");
            }
        }
    }
}