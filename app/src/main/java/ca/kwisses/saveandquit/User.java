package ca.kwisses.saveandquit;

public class User {

    private int _id;

    private double cigPackCost;
    private int cigsInPack;
    private int cigsPerDay;
    private int days;

    public User(int[] array) {
        this._id = array[0];
        this.cigPackCost = array[1];
        this.cigsInPack =  array[2];
        this.cigsPerDay = array[3];
        this.days = array[4];
    }

    public User(int id, double cigPackCost, int cigsInPack, int cigsPerDay, int days) {
        this._id = id;
        this.cigPackCost = cigPackCost;
        this.cigsInPack = cigsInPack;
        this.cigsPerDay = cigsPerDay;
        this.days = days;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getCigPackCost() {
        return cigPackCost;
    }

    public void setCigPackCost(double cigPackCost) {
        this.cigPackCost = cigPackCost;
    }

    public int getCigsInPack() {
        return cigsInPack;
    }

    public void setCigsInPack(int cigsInPack) {
        this.cigsInPack = cigsInPack;
    }

    public int getCigsPerDay() {
        return cigsPerDay;
    }

    public void setCigsPerDay(int cigsPerDay) {
        this.cigsPerDay = cigsPerDay;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
