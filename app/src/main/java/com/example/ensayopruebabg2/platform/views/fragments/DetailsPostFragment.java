package com.example.ensayopruebabg2.platform.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.data.entity.CommentEntity;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.data.entity.UserEntity;
import com.example.ensayopruebabg2.platform.views.activities.SecondActivity;
import com.example.ensayopruebabg2.platform.views.common.CommentPostAdapter;
import com.example.ensayopruebabg2.presentation.presenter.DetailsPostPresenter;
import com.example.ensayopruebabg2.presentation.view.DetailsPostView;

import java.util.List;

import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;

public class DetailsPostFragment extends Fragment implements DetailsPostView {

    @Inject
    DetailsPostPresenter presenter;

    private TextView tvId;
    private TextView tvTitle;
    private TextView tvBody;
    private Button btnComments;
    private Button btnUser;
    private TextView tvUserId;
    private TextView tvUserName;
    private TextView tvUserEmail;
    private TextView tvUserPhone;
    private ConstraintLayout tvUserLayout;

    private RecyclerView rvListComments;
    private RecyclerView.Adapter adapter;


    public static DetailsPostFragment newInstance(Bundle bundle) {
        DetailsPostFragment detailsPostFragment = new DetailsPostFragment();
        detailsPostFragment.setArguments(bundle);
        return detailsPostFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_post, container, false);
        initView(view);
        initViewAction();
        return view;
    }

    private void initView(View view) {
        tvId = view.findViewById(R.id.tvId);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvBody = view.findViewById(R.id.tvBody);
        btnComments = view.findViewById(R.id.btnComments);
        btnUser = view.findViewById(R.id.btnUser);

        tvUserLayout = view.findViewById(R.id.tvUserLayout);
        tvUserId = view.findViewById(R.id.tvUserId);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        tvUserPhone = view.findViewById(R.id.tvUserPhone);

        rvListComments = view.findViewById(R.id.fragmentContainer);
        rvListComments.setHasFixedSize(true);
        rvListComments.setItemAnimator(new DefaultItemAnimator());
        rvListComments.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
    }

    private void initViewAction() {
        tvUserLayout.setVisibility(View.GONE);
        PostEntity post = (PostEntity) getArguments().getSerializable("Post");
        tvId.setText(String.valueOf(post.getId()));
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());
        actionBtnComments(post.getId());
        actionBtnUser(post.getUserId());
    }

    private void actionBtnComments(int id) {
        btnComments.setOnClickListener(v -> {
            tvUserLayout.setVisibility(View.GONE);
            presenter.getListComments(id);
        });
    }

    private void actionBtnUser(int idUser) {
        btnUser.setOnClickListener(v -> {
            tvUserLayout.setVisibility(View.VISIBLE);
            rvListComments.setAdapter(null);
            presenter.getUser(idUser);
        });
    }


    @Override
    public void showListComments(List<CommentEntity> list) {
        adapter = new CommentPostAdapter( list );
        rvListComments.setAdapter(adapter);
    }

    @Override
    public void showUser(UserEntity user) {
        tvUserId.setText(String.valueOf(user.getId()));
        tvUserName.setText(user.getName());
        tvUserEmail.setText(user.getEmail());
        tvUserPhone.setText(user.getPhone());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void navigate(Fragment fragment) {
        ((SecondActivity) requireActivity()).navigateToFragment(fragment/*,name*/);
    }
}
