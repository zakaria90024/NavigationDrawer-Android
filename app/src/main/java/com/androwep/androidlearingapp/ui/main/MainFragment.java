package com.androwep.androidlearingapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.androwep.androidlearingapp.R;
import com.androwep.androidlearingapp.databinding.MainFragmentBinding;
import com.androwep.androidlearingapp.ui.java.JavaActivity;

public class MainFragment extends Fragment {
    MainFragmentBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);

        //fragment button visible
        mBinding.javaBtnId.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), JavaActivity.class);
            startActivity(intent);
        });
        return mBinding.getRoot();

    }
}
