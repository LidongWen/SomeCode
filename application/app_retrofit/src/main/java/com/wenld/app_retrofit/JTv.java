package com.wenld.app_retrofit;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
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

    int lastY = 0;

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
        Log.e("onInterceptTouchEvent", "onInterceptTouchEvent  "+action);
        if ((action == MotionEvent.ACTION_MOVE)) {
            return true;
        }

        if (super.onInterceptTouchEvent(ev)) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_MOVE: {
//                /*
//                 * mIsBeingDragged == false, otherwise the shortcut would have caught it. Check
//                 * whether the user has moved far enough from his original down touch.
//                 */
//
//                /*
//                * Locally do absolute value. mLastMotionY is set to the y value
//                * of the down event.
//                */
//                final int activePointerId = mActivePointerId;
//                if (activePointerId == INVALID_POINTER) {
//                    // If we don't have a valid id, the touch down wasn't on content.
//                    break;
//                }
//
//                final int pointerIndex = ev.findPointerIndex(activePointerId);
//                if (pointerIndex == -1) {
//                    Log.e(TAG, "Invalid pointerId=" + activePointerId
//                            + " in onInterceptTouchEvent");
//                    break;
//                }
//
//                final int y = (int) ev.getY(pointerIndex);
//                final int yDiff = Math.abs(y - mLastMotionY);
//                if (yDiff > mTouchSlop && (getNestedScrollAxes() & SCROLL_AXIS_VERTICAL) == 0) {
//                    mIsBeingDragged = true;
//                    mLastMotionY = y;
//                    initVelocityTrackerIfNotExists();
//                    mVelocityTracker.addMovement(ev);
//                    mNestedYOffset = 0;
//                    if (mScrollStrictSpan == null) {
//                        mScrollStrictSpan = StrictMode.enterCriticalSpan("ScrollView-scroll");
//                    }
//                    final ViewParent parent = getParent();
//                    if (parent != null) {
//                        parent.requestDisallowInterceptTouchEvent(true);
//                    }
//                }
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                return true;
//                final int y = (int) ev.getY();
//                if (!inChild((int) ev.getX(), (int) y)) {
//                    mIsBeingDragged = false;
//                    recycleVelocityTracker();
//                    break;
//                }
//
//                /*
//                 * Remember location of down touch.
//                 * ACTION_DOWN always refers to pointer index 0.
//                 */
//                mLastMotionY = y;
//                mActivePointerId = ev.getPointerId(0);
//
//                initOrResetVelocityTracker();
//                mVelocityTracker.addMovement(ev);
//                /*
//                 * If being flinged and user touches the screen, initiate drag;
//                 * otherwise don't. mScroller.isFinished should be false when
//                 * being flinged. We need to call computeScrollOffset() first so that
//                 * isFinished() is correct.
//                */
//                mScroller.computeScrollOffset();
//                mIsBeingDragged = !mScroller.isFinished();
//                if (mIsBeingDragged && mScrollStrictSpan == null) {
//                    mScrollStrictSpan = StrictMode.enterCriticalSpan("ScrollView-scroll");
//                }
//                startNestedScroll(SCROLL_AXIS_VERTICAL);
//                break;
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initOrResetVelocityTracker();
                mVelocityTracker.addMovement(event);
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                int disY = (int) event.getY() - lastY;
                scrollBy(0, -disY);
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
//                mScroller.startScroll((int) getX(), (int) getY(), -(int) (getX() - startX),
//                        -(int) (getY() - startY));
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                final VelocityTracker velocityTracker = mVelocityTracker;
//                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int initialVelocity = (int) velocityTracker.getYVelocity();
                if (initialVelocity > 0) {
                    direction = -1;
                } else {
                    direction = 1;
                }
                Log.e("initialVelocity:", initialVelocity + " " + mMaximumVelocity);
                mScroller.fling(0, 0, 0, Math.abs(initialVelocity), 0, 0, 0, 500);
//                mScroller.startScroll(0, 0, 0, -100);
                invalidate();
                break;
        }

        return true;
    }

    int direction = -1;

    private void initOrResetVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
    }

    @Override
    public void computeScroll() {

        if (mScroller.computeScrollOffset()) {
            scrollBy(mScroller.getCurrX(), direction * mScroller.getCurrY());
            Log.e("fling", mScroller.getCurrX() + "   " + mScroller.getCurrY());
            invalidate();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        startX = getX();
//        startY = getY();
    }

    public void spingBack() {

        if (mScroller.springBack((int) getX(), (int) getY(), 0, (int) getX(), 0,
                (int) getY() - 100)) {
            Log.d("TAG", "getX()=" + getX() + "__getY()=" + getY());
            invalidate();
        }
    }


}
