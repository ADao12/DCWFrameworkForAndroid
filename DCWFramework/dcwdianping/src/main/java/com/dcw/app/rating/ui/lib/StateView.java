package com.dcw.app.rating.ui.lib;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dcw.app.rating.R;

public class StateView extends FrameLayout {
    private StateViewData mViewState = new StateViewData(ContentState.CONTENT);

    private View mContentView;
    private View mLoadingView;
    private View mNetworkErrorView;
    private View mGeneralErrorView;
    private View mEmptyView;
    private OnClickListener mTapToRetryClickListener;
    private OnClickListener mEmptyViewBtnClickListener;
    private StateHandler mHandler;

    public StateView(Context context) {
        this(context, null);
    }

    public StateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHandler = new StateHandler();
        parseAttrs(context, attrs);
    }

    /**
     * Parses the incoming attributes from XML inflation
     */
    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StateView, 0, 0);

        try {
            setLoadingLayoutResourceId(a.getResourceId(R.styleable.StateView_StateView_svLoadingLayout, R.layout.sv__loading));
            setGeneralErrorLayoutResourceId(a.getResourceId(R.styleable.StateView_StateView_svErrorUnknownLayout, R.layout.sv__error_unknown));
            setNetworkErrorLayoutResourceId(a.getResourceId(R.styleable.StateView_StateView_svErrorNetworkLayout, R.layout.sv__error_network));
            setEmptyLayoutResourceId(a.getResourceId(R.styleable.StateView_StateView_svEmptyLayout, R.layout.sv__empty));
            String tmpString;

            tmpString = a.getString(R.styleable.StateView_svErrorTitleNetworkStringId);

            if (tmpString == null) {
                tmpString = context.getString(R.string.error_title_network);
            }

            setNetworkErrorTitleString(tmpString);

            tmpString = a.getString(R.styleable.StateView_StateView_svErrorTitleUnknownStringId);

            if (tmpString == null) {
                tmpString = context.getString(R.string.error_title_unknown);
            }

            setGeneralErrorTitleString(tmpString);

            tmpString = a.getString(R.styleable.StateView_StateView_svErrorTapToRetryStringId);

            if (tmpString == null) {
                tmpString = context.getString(R.string.tap_to_retry);
            }

            setTapToRetryString(tmpString);

            setState(a.getInt(R.styleable.StateView_StateView_svState, ContentState.CONTENT.nativeInt));
        } finally {
            a.recycle();
        }
    }

    private void setNetworkErrorLayoutResourceId(int resourceId) {
        mViewState.networkErrorLayoutResId = resourceId;
    }

    private void setGeneralErrorLayoutResourceId(int resourceId) {
        mViewState.generalErrorLayoutResId = resourceId;
    }

    public void setEmptyLayoutResourceId(int resourceId) {
        mViewState.emptyLayoutResId = resourceId;
    }

    private void setNetworkErrorTitleString(String string) {
        mViewState.networkErrorTitleString = string;
    }

    public String getNetworkErrorTitleString() {
        return mViewState.networkErrorTitleString;
    }

    private void setGeneralErrorTitleString(String string) {
        mViewState.generalErrorTitleString = string;
    }

    public void setCustomErrorString(String string) {
        mViewState.customErrorString = string;

        if (mGeneralErrorView != null) {
            TextView view = ((TextView) mGeneralErrorView.findViewById(R.id.error_title));

            if (view != null) {
                view.setText(string);
            }
        }
    }

    public String getGeneralErrorTitleString() {
        return mViewState.generalErrorTitleString;
    }

    private void setTapToRetryString(String string) {
        mViewState.tapToRetryString = string;
    }

    public String getTapToRetryString() {
        return mViewState.tapToRetryString;
    }

    public int getLoadingLayoutResourceId() {
        return mViewState.loadingLayoutResId;
    }

    public void setLoadingLayoutResourceId(int loadingLayout) {
        this.mViewState.loadingLayoutResId = loadingLayout;
    }

    /**
     * @return the {@link ContentState} the view is currently in
     */
    public ContentState getState() {
        return mViewState.state != null ? mViewState.state : ContentState.CONTENT;
    }

    private void setState(int nativeInt) {
        setState(ContentState.getState(nativeInt));
    }

    /**
     * Configures the view to be in the given state, hiding and showing internally maintained-views as needed
     *
     * @param state
     */
    public void setState(final ContentState state) {
        if (state == mViewState.state) {
            // No change
            return;
        }

        final View contentView = getContentView();

        if (contentView == null) {
            return;
        }

        final ContentState previousState = mViewState.state;

        mHandler.removeMessages(StateHandler.MESSAGE_HIDE_PREVIOUS, state);
        mHandler.sendMessage(mHandler.obtainMessage(StateHandler.MESSAGE_HIDE_PREVIOUS, previousState));

        mViewState.state = state;

        View newStateView = getStateView(state);

        if (newStateView != null) {
            if (state == ContentState.ERROR_GENERAL) {
                TextView view = ((TextView) newStateView.findViewById(R.id.error_title));
                if (view != null) {
                    view.setText(getGeneralErrorTitleString());
                }
            }

            newStateView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Returns the given view corresponding to the specified {@link ContentState}
     *
     * @param state
     * @return
     */
    public View getStateView(ContentState state) {
        switch (state) {
            case ERROR_NETWORK:
                return getNetworkErrorView();

            case ERROR_GENERAL:
                return getGeneralErrorView();

            case LOADING:
                return getLoadingView();

            case EMPTY:
                return getEmptyView();

            case CONTENT:
                return getContentView();
        }
        return null;
    }

    /**
     * Returns the view to be displayed for the case of a network error
     *
     * @return
     */
    public View getNetworkErrorView() {
        if (mNetworkErrorView == null) {
            mNetworkErrorView = View.inflate(getContext(), mViewState.networkErrorLayoutResId, null);

            ((TextView) mNetworkErrorView.findViewById(R.id.error_title)).setText(getNetworkErrorTitleString());
            ((TextView) mNetworkErrorView.findViewById(R.id.tap_to_retry)).setText(getTapToRetryString());

            mNetworkErrorView.setOnClickListener(mTapToRetryClickListener);

            addView(mNetworkErrorView);
        }

        return mNetworkErrorView;
    }

    /**
     * Returns the view to be displayed for the case of an unknown error
     *
     * @return
     */
    public View getGeneralErrorView() {
        if (mGeneralErrorView == null) {
            mGeneralErrorView = View.inflate(getContext(), mViewState.generalErrorLayoutResId, null);

            ((TextView) mGeneralErrorView.findViewById(R.id.error_title)).setText(getGeneralErrorTitleString());
            ((TextView) mGeneralErrorView.findViewById(R.id.tap_to_retry)).setText(getTapToRetryString());

            mGeneralErrorView.setOnClickListener(mTapToRetryClickListener);

            addView(mGeneralErrorView);
        }

        return mGeneralErrorView;
    }

    /**
     * Builds the loading view if not currently built, and returns the view
     */
    public View getLoadingView() {
        if (mLoadingView == null) {
            mLoadingView = View.inflate(getContext(), mViewState.loadingLayoutResId, null);

            addView(mLoadingView);
        }

        return mLoadingView;
    }

    /**
     * Builds the empty view if not currently built, and returns the view
     * @return Empty view{@link View}
     */
    public View getEmptyView() {
        if (mEmptyView == null) {
            mEmptyView = View.inflate(getContext(), mViewState.emptyLayoutResId, null);
            if (mEmptyViewBtnClickListener != null) {
                mEmptyView.findViewById(R.id.empty_button).setOnClickListener(mEmptyViewBtnClickListener);
            }
            addView(mEmptyView);
        }
        return mEmptyView;
    }

    public void setOnTapToRetryClickListener(View.OnClickListener listener) {
        mTapToRetryClickListener = listener;

        if (mNetworkErrorView != null) {
            mNetworkErrorView.setOnClickListener(listener);
        }

        if (mGeneralErrorView != null) {
            mGeneralErrorView.setOnClickListener(listener);
        }
    }

    /**
     * set empty view button click listener
     * @param listener {@link android.view.View.OnClickListener}
     */
    public void setOnEmptyViewBtnClickListener(View.OnClickListener listener) {
        mEmptyViewBtnClickListener = listener;
        if (mEmptyView != null) {
            mEmptyView.findViewById(R.id.empty_button).setOnClickListener(listener);
        }
    }

    private void addContentView(View contentView) {
        if (mContentView != null && mContentView != contentView) {
            throw new IllegalStateException("Can't add more than one view to StateView");
        }

        setContentView(contentView);
    }

    public View getContentView() {
        return mContentView;
    }

    public void setContentView(View contentView) {
        mContentView = contentView;

        setState(mViewState.state);
    }

    private boolean isViewInternal(View view) {
        return view == mNetworkErrorView || view == mGeneralErrorView || view == mLoadingView || view == mEmptyView;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable state = super.onSaveInstanceState();

        SavedState myState = new SavedState(state);

        myState.state = mViewState;

        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState myState = (SavedState) state;

        setViewState(myState.state);

        super.onRestoreInstanceState(myState.getSuperState());
    }

    private void setViewState(StateViewData state) {
        setState(state.state);
        setTapToRetryString(state.tapToRetryString);
        setGeneralErrorTitleString(state.generalErrorTitleString);
        setNetworkErrorTitleString(state.networkErrorTitleString);
        setGeneralErrorLayoutResourceId(state.generalErrorLayoutResId);
        setNetworkErrorLayoutResourceId(state.networkErrorLayoutResId);
        setLoadingLayoutResourceId(state.loadingLayoutResId);
        setEmptyLayoutResourceId(state.emptyLayoutResId);
        setCustomErrorString(state.customErrorString);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHandler = new StateHandler(getHandler().getLooper());
    }

    @Override
    protected void onDetachedFromWindow() {
        mHandler.removeMessages(StateHandler.MESSAGE_HIDE_PREVIOUS);
        mHandler = new StateHandler();
        super.onDetachedFromWindow();
    }

    @Override
    public void addView(View child) {
        if (!isViewInternal(child)) {
            addContentView(child);
        }

        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (!isViewInternal(child)) {
            addContentView(child);
        }

        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        if (!isViewInternal(child)) {
            addContentView(child);
        }

        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, int width, int height) {
        if (!isViewInternal(child)) {
            addContentView(child);
        }

        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        if (!isViewInternal(child)) {
            addContentView(child);
        }

        super.addView(child, params);
    }


    /**
     * States of the StateView
     */
    public enum ContentState {

        CONTENT(0x00),

        LOADING(0x01),

        ERROR_NETWORK(0x02),

        ERROR_GENERAL(0x03),

        EMPTY(0x04);

        public final int nativeInt;

        private final static SparseArray<ContentState> sStates = new SparseArray<ContentState>();

        static {
            for (ContentState scaleType : values()) {
                sStates.put(scaleType.nativeInt, scaleType);
            }
        }

        public static ContentState getState(int nativeInt) {
            if (nativeInt >= 0) {
                return sStates.get(nativeInt);
            }

            return null;
        }

        private ContentState(int nativeValue) {
            this.nativeInt = nativeValue;
        }
    }

    public static class SavedState extends View.BaseSavedState {
        StateViewData state;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            state = (StateViewData) in.readParcelable(StateViewData.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeParcelable(state, flags);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    public static class StateViewData implements Parcelable {
        public String customErrorString;
        public int loadingLayoutResId;
        public int generalErrorLayoutResId;
        public int networkErrorLayoutResId;
        public int emptyLayoutResId;
        public String networkErrorTitleString;
        public String generalErrorTitleString;
        public String tapToRetryString;
        public ContentState state;

        public StateViewData(ContentState contentState) {
            state = contentState;
        }

        private StateViewData(Parcel in) {
            customErrorString = in.readString();
            loadingLayoutResId = in.readInt();
            generalErrorLayoutResId = in.readInt();
            networkErrorLayoutResId = in.readInt();
            emptyLayoutResId = in.readInt();
            networkErrorTitleString = in.readString();
            generalErrorTitleString = in.readString();
            tapToRetryString = in.readString();
            state = ContentState.valueOf(in.readString());
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(customErrorString);
            dest.writeInt(loadingLayoutResId);
            dest.writeInt(generalErrorLayoutResId);
            dest.writeInt(networkErrorLayoutResId);
            dest.writeInt(emptyLayoutResId);
            dest.writeString(networkErrorTitleString);
            dest.writeString(generalErrorTitleString);
            dest.writeString(tapToRetryString);
            dest.writeString(state.name());
        }

        public static final Parcelable.Creator<StateViewData> CREATOR = new Parcelable.Creator<StateViewData>() {
            public StateViewData createFromParcel(Parcel in) {
                return new StateViewData(in);
            }

            public StateViewData[] newArray(int size) {
                return new StateViewData[size];
            }
        };
    }

    /**
     * Handler used to hide the previous state when switching to a new state
     */
    private class StateHandler extends Handler {
        public static final int MESSAGE_HIDE_PREVIOUS = 0;

        public StateHandler() {
            super();
        }

        public StateHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_HIDE_PREVIOUS:
                    ContentState previousState = (ContentState) msg.obj;
                    View previousView = getStateView(previousState);
                    if (previousView != null) previousView.setVisibility(View.GONE);
                    break;
            }
        }
    }
}