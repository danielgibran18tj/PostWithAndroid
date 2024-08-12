package com.example.ensayopruebabg2.presentation.presenter;

import com.example.ensayopruebabg2.presentation.view.DetailsPostView;

public interface DetailsPostPresenter {

    void setView(DetailsPostView detailsPostView);

    void getListComments(int id);

    void getUser(int id);
}
