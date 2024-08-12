package com.example.ensayopruebabg2.platform.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ensayopruebabg2.R;
import com.example.ensayopruebabg2.presentation.presenter.SecondPresenter;
import com.example.ensayopruebabg2.presentation.view.SecondView;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class AddPostDialogFragment extends DialogFragment implements SecondView {

    @Inject
    SecondPresenter presenter;

    private EditText etUserId, etId, etTitle, etBody;
    private Button btnSave, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post_dialog, container, false);

        etUserId = view.findViewById(R.id.etUserId);
        etId = view.findViewById(R.id.etId);
        etTitle = view.findViewById(R.id.etTitle);
        etBody = view.findViewById(R.id.etBody);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(v -> {
            presenter.addNewPost(
                    etUserId.getText().toString(),
                    etId.getText().toString(),
                    etTitle.getText().toString(),
                    etBody.getText().toString()
            );
            dismiss();  // Cierra el diálogo
        });

        btnCancel.setOnClickListener(v -> dismiss());  // Cierra el diálogo

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void navigateToFragment(Fragment fragment) {
        FragmentManager manager = getParentFragmentManager();
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); //elimina todos los fragmentos del back stack antes de realizar la nueva transacción
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayoutMain,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
