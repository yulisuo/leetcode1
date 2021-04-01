package leetcode1;

// https://leetcode-cn.com/problems/design-parking-system/
public class ParkingSystem {

    private int big;
    private int medium;
    private int small;

    private static final int TYPE_BIG = 1;
    private static final int TYPE_MEDIUM = 2;
    private static final int TYPE_SMALL = 3;

    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        if (carType == TYPE_BIG) {
            if (big > 0) {
                big--;
                return true;
            }
            return false;
        } else if (carType == TYPE_MEDIUM) {
            if (medium > 0) {
                medium--;
                return true;
            }
            return false;
        } else if (carType == TYPE_SMALL) {
            if (small > 0) {
                small--;
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
