package com.example.notesapp.animation;

import android.app.Activity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;

public class Animation {
    private static final int ANIM_TIME = 500;

    public static void headBarShow(Activity activity) {
        ConstraintLayout headBar = activity.findViewById(R.id.headBar);
        RecyclerView notesRV = activity.findViewById(R.id.notes);
        headBar.animate()
                .setDuration(ANIM_TIME)
                .alpha(1f)
                .translationY(headBar.getHeight())
                .setUpdateListener(valueAnimator -> notesRV.setTranslationY(
                        headBar.getTranslationY()));
    }

    public static void headBarHide(Activity activity) {
        ConstraintLayout headBar = activity.findViewById(R.id.headBar);
        headBar.animate()
                .setDuration(ANIM_TIME)
                .alpha(0f)
                .translationY(headBar.getTranslationY() - headBar.getHeight());
    }

    public static void addBtnShow(Activity activity) {
        ConstraintLayout addBtnWrapper = activity.findViewById(R.id.addWrapper);
        addBtnWrapper.animate()
                .setDuration(ANIM_TIME)
                .alpha(1f)
                .translationX(addBtnWrapper.getTranslationX() - addBtnWrapper.getWidth());
    }

    public static void addBtnHide(Activity activity) {
        ConstraintLayout addBtnWrapper = activity.findViewById(R.id.addWrapper);
        addBtnWrapper.animate()
                .setDuration(ANIM_TIME)
                .alpha(0f)
                .translationX(addBtnWrapper.getWidth());
    }

    public static void deleteBtnShow(Activity activity) {
        ConstraintLayout deleteBtnWrapper = activity.findViewById(R.id.deleteWrapper);
        ConstraintLayout addBtnWrapper = activity.findViewById(R.id.addWrapper);
        deleteBtnWrapper.animate()
                .setDuration(ANIM_TIME)
                .alpha(1f)
                .translationY(-deleteBtnWrapper.getHeight())
                .setUpdateListener(valueAnimator -> addBtnWrapper.setTranslationY(
                        deleteBtnWrapper.getTranslationY()));
    }

    public static void deleteBtnHide(Activity activity) {
        ConstraintLayout deleteBtnWrapper = activity.findViewById(R.id.deleteWrapper);
        deleteBtnWrapper.animate()
                .setDuration(ANIM_TIME)
                .alpha(0f)
                .translationY(deleteBtnWrapper.getTranslationY() + deleteBtnWrapper.getHeight());
    }
}
