package Exercise3;

public class TaxCalculatorDemo {
    public static void main(String[] args) {
        House akbarsHouse = new House(36, 300000000);
        House bambangsHouse = new House(45, 500000000);
        House charliesHouse = new House(90, 1000000000);

        Father akbar = new Father("Akbar", akbarsHouse);
        Father bambang = new Father("Bambang", bambangsHouse);
        Father charlie = new Father("Charlie", charliesHouse);

        System.out.println("=== Laporan Pajak PBB ===");
        akbar.displayTaxInfo();
        bambang.displayTaxInfo();
        charlie.displayTaxInfo();
    }
}
