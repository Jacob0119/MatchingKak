package project.capstone.com.matchingkak.Main.home;

/**
 * Created by amco1 on 2018-03-21.
 */

public interface HomeContract {

    interface Presenter{

        int BANNER_ADATER=1;
        int MAIN_GAMELIST=2;
        int RECOMMAND_GAMELIST=3;


        void attachView(HomeContract.View view);
        void detachView(HomeContract.View view);
        void showFirstPage();
        void showNextPage();
        void showBanner();
        void setAdapterView(HomeAdapterContract.View view,int AdapterNumber);
        void setAdapterModel(HomeAdapterContract.Model model,int AdapterNumber);
    }

    interface View{

        void updateBanner();
        void finishUpdatePage();

    }

}
