package com.yuwq.libs_common;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * Created by brucezz on 2016-08-04.
 * Github: https://github.com/brucezz
 * Email: im.brucezz@gmail.com
 */
public class FragmentProvider implements Provider {

    @Override
    public Context getContext(Object source) {
        return ((Fragment) source).requireContext();
    }

    @Override
    public View findView(Object source, int id) {
        return ((Fragment) source).requireView().findViewById(id);
    }
}