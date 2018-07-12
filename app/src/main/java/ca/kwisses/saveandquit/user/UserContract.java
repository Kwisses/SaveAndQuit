package ca.kwisses.saveandquit.user;

public interface UserContract {

    interface MvpView {
        int get_id();

        double getCigPackCost();

        int getCigsInPack();

        int getCigsPerDay();

        void set_id(int id);

        int getDays();

        void setCigPackCost(double cigPackCost);

        void setCigsInPack(int cigsInPack);

        void setCigsPerDay(int cigsPerDay);

        void setDays(int days);
    }

}
