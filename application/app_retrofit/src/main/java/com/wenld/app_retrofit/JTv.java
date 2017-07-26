package com.wenld.app_retrofit;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.BounceInterpolator;
import android.widget.OverScroller;

/**
 * Created by wenld on 2017/7/24.
 */

public class JTv extends BaseViewGroup {

    private OverScroller mScroller;
    VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;

    private int mOverscrollDistance;
    private int mOverflingDistance;

    public JTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new OverScroller(context, new BounceInterpolator());

        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        mOverscrollDistance = configuration.getScaledOverscrollDistance();
        mOverflingDistance = configuration.getScaledOverflingDistance();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        /*
         * This method JUST determines whether we want to intercept the motion.
         * If we return true, onMotionEvent will be called and we do the actual
         * scrolling there.
         */

        /*
        * Shortcut the most recurring case: the user is in the dragging
        * state and he is moving his finger.  We want to intercept this
        * motion.
        */
        final int action = ev.getAction();
        Log.e("onInterceptTouchEvent", "onInterceptTouchEvent  " + action);
        if ((action == MotionEvent.ACTION_MOVE)) {
            return true;
        }

        if (super.onInterceptTouchEvent(ev)) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_MOVE: {
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                return true;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
//                /* Release the drag */
//                mIsBeingDragged = false;
//                mActivePointerId = INVALID_POINTER;
//                recycleVelocityTracker();
//                if (mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange())) {
//                    postInvalidateOnAnimation();
//                }
//                stopNestedScroll();
                break;
            case MotionEvent.ACTION_POINTER_UP:
//                onSecondaryPointerUp(ev);
                break;
        }

        /*
        * The only time we want to intercept motion events is if we are in the
        * drag mode.
        */
        return false;
    }

    int lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                direction = -1;
                initOrResetVelocityTracker();
                mVelocityTracker.addMovement(event);
                lastY = (int) event.getY();
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                int disY = (int) event.getY() - lastY;
                overScrollBy(0, -disY, 0, getScrollY(), 0, getScrollRange(), 0, 100, false);
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                final VelocityTracker velocityTracker = mVelocityTracker;
//                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int initialVelocity = (int) velocityTracker.getYVelocity();
                if (initialVelocity < 0) {
                    direction = 1;
                } else {
                    direction = -1;
                }
                if ((Math.abs(initialVelocity) > mMinimumVelocity)) {
                    fling(initialVelocity);
                } else {
//                    spingBack();
                }
//                startScroll();
                endDrag();
                break;
        }

        return true;
    }

    private void endDrag() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }


    int direction = -1;

    private void initOrResetVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
    }


    int lastDistY = 0;
    int dist = 0;

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            dist = mScroller.getCurrY() - lastDistY;
            lastDistY += dist;

            overScrollBy(0, direction * dist, 0, getScrollY(), 0, getScrollRange(), 0, 100, false);

        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        int newScrollY = scrollY + deltaY;
        final int top = -maxOverScrollY;
        final int bottom = maxOverScrollY + scrollRangeY;
        boolean clampedY = false;
        if (newScrollY > bottom) {
            newScrollY = bottom;
            clampedY = true;
        } else if (newScrollY < top) {
            newScrollY = top;
            clampedY = true;
        }

        scrollTo(0, newScrollY);
        onOverScrolled(0, newScrollY, false, clampedY);

        return clampedY;
    }

    private int getScrollRange() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            scrollRange = Math.max(0,
                    child.getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
        }
        return scrollRange;
    }

    protected void onOverScrolled(int scrollX, int scrollY,
                                  boolean clampedX, boolean clampedY) {

        if (clampedY) {
            if (!mScroller.isFinished()) {
                mScroller.abortAnimation();
            }
        }
    }

    public void spingBack() {
//        lastDistY = 0;
//        if ( mScroller.springBack(0, 0, 0, 0, 0, direction * getScrollRange())) {
//            computeScroll();
//        }
    }

    private void fling(int initialVelocity) {
        Log.e("-- fling -- ", "initialVelocity :" + initialVelocity);
        lastDistY = 0;
        mScroller.fling(0, 0, 0, Math.abs(initialVelocity), 0, 0, 0, 1000);
        invalidate();
    }

    private void startScroll() {
        mScroller.startScroll(0, 0, 0, -100);
        invalidate();
    }

}
