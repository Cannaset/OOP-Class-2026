package Exercise3;

public class Father {
    private String name;
    private House house;

    public Father(String name, House house) {
        this.name = name;
        this.house = house;
    }

    public void displayTaxInfo() {
        if (this.house != null) {
            double tax = this.house.calculatePBB();
            System.out.println(this.name + " | Tipe Rumah: " + this.house.getType() + " | Harga: Rp " + 
            this.house.getPrice() + " | Pajak PBB: Rp " + tax);
        } else {
            System.out.println(this.name + " belum punya rumah.");
        }
    }
}
