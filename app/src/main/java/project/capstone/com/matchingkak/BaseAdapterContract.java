package project.capstone.com.matchingkak;

import java.util.List;

import project.capstone.com.matchingkak.Main.OnClickListener;

/**
 * Created by amco1 on 2018-03-28.
 */

public interface BaseAdapterContract {
    interface view{
        //어뎁터에서 보여지는 리스트에 관련된 메소드들
        void notifyAdapter();
        void setOnClickListener(OnClickListener clickListener);
    }
    interface model{
        //어뎁터의 데이터를 담고 있는 리스트에 관한 메소드
///데이터 관리
        Object getItem(int position);
        void addItems(List items, boolean isUpper);
    }
}
