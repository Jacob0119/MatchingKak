package project.capstone.com.matchingkak.Main.home;

/**
 * Created by amco1 on 2018-03-21.
 */

public interface HomeContract {


    interface Presenter{
        int BANNER_ADATER           =1;
        int MAIN_GAMELIST           =2;
        int RECOMMEND_GAMELIST      =3;
        int MENU_LIST               =4;



        void attachView(HomeContract.View view);
        void detachView(HomeContract.View view);
        void getData(int id);
        void showFirstPage();
        void showNextPage();
        void showBanner();
        void setAdapterView(HomeAdapterContract.View view,int AdapterNumber);
        void setAdapterModel(HomeAdapterContract.Model model,int AdapterNumber);
        void getMenu();
    }

    interface PresenterForSportMenu{


    }
    interface View{

        void updateBanner();
        void finishUpdatePage();

    }

}
