import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println();
            System.out.println("===== MENU BENTUK =====");
            System.out.println("1. Bujur Sangkar");
            System.out.println("2. Lingkaran");
            System.out.println("3. Silinder");
            System.out.println("0. Keluar");
            System.out.print("Pilih bentuk: ");

            while (!input.hasNextInt()) {
                System.out.print("Masukkan angka 0-3: ");
                input.next();
            }
            pilihan = input.nextInt();
            input.nextLine(); // buang sisa baris

            Bentuk bentuk = null;

            switch (pilihan) {
                case 1: {
                    double sisi = bacaDouble(input, "Masukkan sisi: ");
                    String warna = bacaWarna(input);
                    bentuk = new BujurSangkar(sisi, warna);
                    break;
                }
                case 2: {
                    double radius = bacaDouble(input, "Masukkan radius: ");
                    String warna = bacaWarna(input);
                    bentuk = new Lingkaran(radius, warna);
                    break;
                }
                case 3: {
                    double radius = bacaDouble(input, "Masukkan radius: ");
                    double tinggi = bacaDouble(input, "Masukkan tinggi: ");
                    String warna = bacaWarna(input);
                    bentuk = new Silinder(tinggi, radius, warna);
                    break;
                }
                case 0:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

            if (bentuk != null) {
                System.out.println("--- Hasil ---");
                bentuk.printInfo(); // memanggil versi overriding sesuai objeknya
            }
        } while (pilihan != 0);

        input.close();
    }

    private static double bacaDouble(Scanner input, String pesan) {
        System.out.print(pesan);
        while (!input.hasNextDouble()) {
            System.out.print("Input harus angka. " + pesan);
            input.next();
        }
        double nilai = input.nextDouble();
        input.nextLine(); // buang sisa baris
        return nilai;
    }

    private static String bacaWarna(Scanner input) {
        System.out.print("Masukkan warna: ");
        return input.nextLine();
    }
}