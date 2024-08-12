package com.example.ensayopruebabg2.presentation.presenter;

import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.presentation.view.SelectPostsView;

public interface SelectPostsPresenter {

    void setView(SelectPostsView selectPostsView);

    void showDetails(PostEntity post);
}
