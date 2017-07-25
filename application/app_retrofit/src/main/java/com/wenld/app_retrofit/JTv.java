package com.wenld.app_retrofit;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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

    int lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
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
                scrollBy(0, -disY);
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                final VelocityTracker velocityTracker = mVelocityTracker;
//                velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int initialVelocity = (int) velocityTracker.getYVelocity();
                if (initialVelocity > 0) {
                    direction = -1;
                } else {
                    direction = 1;
                }
                lastDistY = 0;
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
//            overScrollBy(mScroller.getCurrX(),direction * mScroller.getCurrY(), getScrollX(), getScrollY(), 0, 500,
//                    0, mOverflingDistance, false);
            Log.e("getCurrY()", mScroller.getCurrY() + "  getScrollY(): " + getScrollY());

            /**
             *
             */
            dist = mScroller.getCurrY() - lastDistY;
            lastDistY += dist;
//            scrollBy(mScroller.getCurrX(), direction * dist);
            overScrollBy(0, direction * dist, 0, getScrollY(), 0, 1000, 0, 100, false);
//            onScrollChanged(0, getScrollY(), 0, dist);
            invalidate();
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
        Log.e(" overScrollBy", "  scrollRangeY " + scrollRangeY);
        scrollTo(0,newScrollY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    protected void onOverScrolled(int scrollX, int scrollY,
                                  boolean clampedX, boolean clampedY) {
        Log.e(" ", "scrollX:" + scrollX + "  scrollY" + scrollY);
        // Intentionally empty.
    }

    public void spingBack() {
        Log.e("TAG", "getX()=" + getX() + "__getY()=" + getY());
        if (mScroller.springBack(0, -15, 0, 0, 10,
                100)) {
            computeScroll();
        }
    }

    private void fling(int initialVelocity) {
        mScroller.fling(0, 0, 0, Math.abs(initialVelocity), 0, 0, getScrollY(), 1000);
        invalidate();
    }

    private void startScroll() {
        mScroller.startScroll(0, 0, 0, -100);
        invalidate();
    }

}
