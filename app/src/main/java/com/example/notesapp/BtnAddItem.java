package com.example.notesapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

public class BtnAddItem extends AppCompatImageButton {
    private final Context context;

    public BtnAddItem(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public BtnAddItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                AnimatorSet squeeze = (AnimatorSet) AnimatorInflater.loadAnimator(
                        context,
                        R.animator.squeeze);
                squeeze.setTarget(this);
                squeeze.start();
                return true;
            case (MotionEvent.ACTION_UP):
                AnimatorSet restore = (AnimatorSet) AnimatorInflater.loadAnimator(
                        context,
                        R.animator.restore);
                restore.setTarget(this);
                restore.start();
                performClick();
                return true;
        }

        return false;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
