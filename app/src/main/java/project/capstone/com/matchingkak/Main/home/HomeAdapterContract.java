package project.capstone.com.matchingkak.Main.home;

import java.util.List;

import project.capstone.com.matchingkak.Main.OnClickListener;

/**
 * Created by amco1 on 2018-03-21.
 */

public interface HomeAdapterContract {

    interface View{
       void setUpdate();
       void setOnItemClickListenter(OnClickListener listenter);
    }
    interface Model{
        void set(List data);
        void add(List data);
    }
}
