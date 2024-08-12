package com.example.ensayopruebabg2.presentation.view;

import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;

import java.util.List;

public interface DetailsPostView extends BaseFragmentView {
    void showListComments(List<CommentEntity> list);

    void showUser(UserEntity user);
}
