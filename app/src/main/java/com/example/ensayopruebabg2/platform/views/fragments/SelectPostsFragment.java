package com.example.ensayopruebabg2.platform.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.data.entity.PostEntity;
import com.example.ensayopruebabg2.platform.views.activities.SecondActivity;
import com.example.ensayopruebabg2.platform.views.common.PostSelectAdapter;
import com.example.ensayopruebabg2.presentation.presenter.SelectPostsPresenter;
import com.example.ensayopruebabg2.presentation.view.SelectPostsView;

import java.util.List;

import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;

public class SelectPostsFragment extends Fragment implements SelectPostsView {

    @Inject
    SelectPostsPresenter presenter;
    private ImageView imageAddPost;
    private RecyclerView rvPostSelect;

    private RecyclerView.Adapter adapter;

    public static SelectPostsFragment newInstance(Bundle bundle) {
        SelectPostsFragment selectPosts = new SelectPostsFragment();
        selectPosts.setArguments(bundle);
        return selectPosts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_posts, container, false);
        initView(view);
        initViewAction();
        return view;
    }


    private void initView(View view) {
        imageAddPost = view.findViewById(R.id.imageAddPost);

        rvPostSelect = view.findViewById(R.id.selectedPosts);
        rvPostSelect.setHasFixedSize(true);
        rvPostSelect.setItemAnimator(new DefaultItemAnimator());
        rvPostSelect.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
    }


    private void initViewAction() {
        if (getArguments() != null){
            List<PostEntity> listPostsEntity = (List<PostEntity>) getArguments().getSerializable("Posts");
            adapter = new PostSelectAdapter(
                    listPostsEntity,
                    getContext(),
                    (post, position) -> {
                        adapter.notifyItemChanged(position);
                        presenter.showDetails(post);
                    }
            );

//            adapter = new ProductSelectAdapter(
//                    listProductEntity,
//                    getContext(),
//                    new ProductSelectAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(ProductEntity product, int position) {
//                            adapter.notifyItemChanged(position);
//                            presenter.showDetails(product);
//                        }
//                    }
//            );
            rvPostSelect.setAdapter(adapter);
        }

        imageAddPost.setOnClickListener(v -> {
            AddPostDialogFragment dialogFragment = new AddPostDialogFragment();
            dialogFragment.show(getParentFragmentManager(), "AddPostDialogFragment");
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void navigate(Fragment fragment/*, Integer name*/) {
        ((SecondActivity) requireActivity()).navigateToFragment(fragment/*,name*/);
    }

}
