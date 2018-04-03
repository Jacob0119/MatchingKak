package project.capstone.com.matchingkak.Main.me.presenter;

import project.capstone.com.matchingkak.BaseAdapterContract;
import project.capstone.com.matchingkak.BaseContract;
import project.capstone.com.matchingkak.Main.me.meContract;
import project.capstone.com.matchingkak.restAPI.APIAdapter;

public class mePresenter implements meContract.Presenter {
    private BaseContract.View view;

    @Override
    public void attatchView(BaseContract.View view) {
            this.view=view;

    }

    @Override
    public void detachView() {
            view=null;
    }

    @Override
    public void setAdapterModel(BaseAdapterContract.model model) {

    }

    @Override
    public void setAdapterView(BaseAdapterContract.view view) {

    }

    @Override
    public void setData(APIAdapter adapter) {

    }
}
