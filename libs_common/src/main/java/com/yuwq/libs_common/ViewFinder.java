package com.yuwq.libs_common;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import kotlin.jvm.JvmStatic;

/**
 * Created by brucezz on 2016-07-27.
 * Github: https://github.com/brucezz
 * Email: im.brucezz@gmail.com
 */
public class ViewFinder {

    private static final ActivityProvider PROVIDER_ACTIVITY = new ActivityProvider();
    private static final FragmentProvider PROVIDER_FRAGMENT = new FragmentProvider();
    private static final Map<String, Finder> FINDER_MAP = new HashMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, PROVIDER_ACTIVITY);
    }

    public static void inject(Fragment fragment) {
        inject(fragment, fragment, PROVIDER_FRAGMENT);
    }

    public static void inject(Object host, Object source, Provider provider) {
        String className = host.getClass().getName();
        try {
            Finder finder = FINDER_MAP.get(className);
            if (finder == null) {
                Class<?> finderClass = Class.forName(className + "$$Finder");
                finder = (Finder) finderClass.newInstance();
                FINDER_MAP.put(className, finder);
            }
            finder.inject(host, source, provider);
        } catch (ClassNotFoundException e){
//            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject for " + className, e);
        }
    }
}