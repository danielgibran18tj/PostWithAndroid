package com.example.ensayopruebabg2.presentation.presenter;

import com.example.ensayopruebabg2.domain.model.PostModel;
import com.example.ensayopruebabg2.presentation.view.SelectPostsView;

public interface SelectPostsPresenter {

    void setView(SelectPostsView selectPostsView);

    void showDetails(PostModel post);
}
