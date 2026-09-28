package Exercise3;

public class House {
    private int type;
    private double price;

    public House(int type, double price) {
        this.type = type;
        this.price = price;
    }

    public double calculatePBB() {
        if (this.type <= 36) {
            return this.price * 0.04;
        } else if (this.type <= 45) {
            return this.price * 0.06;
        } else {
            return this.price * 0.09;
        }
    }

    public int getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }
}
