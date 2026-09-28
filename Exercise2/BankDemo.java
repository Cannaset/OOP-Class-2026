package Exercise2;

import java.util.Scanner;

public class BankDemo {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank();
        int pilihan;

        System.out.println("Welcome to Bank ABC");

        do {
            System.out.println();
            System.out.println("========== MENU ==========");
            System.out.println("1. Tambah customer");
            System.out.println("2. Tampilkan semua customer");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Cek saldo customer");
            System.out.println("0. Keluar");
            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahCustomer(bank);
                    break;
                case 2:
                    tampilkanCustomer(bank);
                    break;
                case 3: {
                    Customer c = pilihCustomer(bank);
                    if (c != null) {
                        int jumlah = bacaInt("Jumlah deposit: Rp. ");
                        if (jumlah <= 0) {
                            System.out.println("Jumlah harus lebih dari 0.");
                        } else {
                            c.getAccount().deposit(jumlah);
                            System.out.println("Deposit berhasil. Saldo sekarang: Rp. " + c.getAccount().getBalance());
                        }
                    }
                    break;
                }
                case 4: {
                    Customer c = pilihCustomer(bank);
                    if (c != null) {
                        int jumlah = bacaInt("Jumlah withdraw: Rp. ");
                        if (jumlah <= 0) {
                            System.out.println("Jumlah harus lebih dari 0.");
                        } else if (jumlah > c.getAccount().getBalance()) {
                            System.out.println("Saldo tidak mencukupi. Saldo saat ini: Rp. " + c.getAccount().getBalance());
                        } else {
                            c.getAccount().withdraw(jumlah);
                            System.out.println("Withdraw berhasil. Saldo sekarang: Rp. " + c.getAccount().getBalance());
                        }
                    }
                    break;
                }
                case 5: {
                    Customer c = pilihCustomer(bank);
                    if (c != null) {
                        System.out.println(c.getFirstName() + " " + c.getLastName()
                                + " - Saldo: Rp. " + c.getAccount().getBalance());
                    }
                    break;
                }
                case 0:
                    System.out.println("Terima kasih telah menggunakan Bank ABC.");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 0);

        input.close();
    }

    private static void tambahCustomer(Bank bank) {
        int sebelum = bank.getNumOfCustomers();

        System.out.print("Nama depan    : ");
        String firstName = input.nextLine();
        System.out.print("Nama belakang : ");
        String lastName = input.nextLine();

        bank.addCustomer(firstName, lastName);

        // Jika berhasil ditambahkan, buatkan akun dengan saldo awal
        if (bank.getNumOfCustomers() > sebelum) {
            int saldoAwal = bacaInt("Saldo awal    : Rp. ");
            if (saldoAwal < 0) {
                saldoAwal = 0;
            }
            bank.getCustomer(sebelum).setAccount(new Bank(saldoAwal));
            System.out.println("Customer " + firstName + " " + lastName + " berhasil ditambahkan.");
        }
    }

    private static void tampilkanCustomer(Bank bank) {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada customer.");
            return;
        }
        System.out.println("Jumlah customer: " + bank.getNumOfCustomers());
        System.out.println("----------------------------------------");
        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println((i + 1) + ". " + c.getFirstName() + " " + c.getLastName()
                    + " - Saldo: Rp. " + c.getAccount().getBalance());
        }
        System.out.println("----------------------------------------");
    }

    private static Customer pilihCustomer(Bank bank) {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada customer. Tambahkan customer terlebih dahulu.");
            return null;
        }
        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println((i + 1) + ". " + c.getFirstName() + " " + c.getLastName());
        }
        int nomor = bacaInt("Pilih nomor customer: ");
        if (nomor < 1 || nomor > bank.getNumOfCustomers()) {
            System.out.println("Nomor customer tidak valid.");
            return null;
        }
        return bank.getCustomer(nomor - 1);
    }

    private static int bacaInt(String pesan) {
        System.out.print(pesan);
        while (!input.hasNextInt()) {
            System.out.print("Input harus angka. " + pesan);
            input.next();
        }
        int nilai = input.nextInt();
        input.nextLine(); // buang sisa baris
        return nilai;
    }
}