package com.charon.www.younghawkdemo.biz;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.charon.www.younghawkdemo.R;

/**
 * Created by Administrator on 2017/4/25.
 */

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child, final View directTargetChild, final View target, final int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child, final View target, final int dxConsumed, final int dyConsumed, final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("behavior", "dy" + dyConsumed + "dyu"+dyUnconsumed);
        if (dyConsumed > 0  && child.getVisibility() == View.VISIBLE){
            Log.d("behavior", "out");
            animateOut(child);
        }
        else if (dyConsumed < 0 &&  child.getVisibility() != View.VISIBLE){
            Log.d("behavior", "in");
            animateIn(child);
        }

    }

    private void animateOut(final FloatingActionButton button) {
        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer().setListener(new ViewPropertyAnimatorListener() {
                public void onAnimationStart(View view) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
                }

                public void onAnimationCancel(View view) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                }

                public void onAnimationEnd(View view) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                    view.setVisibility(View.INVISIBLE);
                }
            }).start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_out);
            anim.setInterpolator(INTERPOLATOR);
            anim.setDuration(200L);
            anim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
                }

                public void onAnimationEnd(Animation animation) {
                    ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                    button.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(final Animation animation) {
                }
            });
            button.startAnimation(anim);
        }
    }

    private void animateIn(FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setInterpolator(INTERPOLATOR).withLayer().setListener(null).start();
        } else {
            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_in);
            anim.setDuration(200L);
            anim.setInterpolator(INTERPOLATOR);
            button.startAnimation(anim);
        }
    }
}
