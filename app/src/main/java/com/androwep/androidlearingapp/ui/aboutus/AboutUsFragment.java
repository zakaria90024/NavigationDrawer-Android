package com.androwep.androidlearingapp.ui.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.androwep.androidlearingapp.R;
import com.androwep.androidlearingapp.databinding.AboutusFragmentBinding;
import com.androwep.androidlearingapp.databinding.MainFragmentBinding;

public class AboutUsFragment extends Fragment {
    AboutusFragmentBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.aboutus_fragment, container, false);
        return mBinding.getRoot();
    }
}
