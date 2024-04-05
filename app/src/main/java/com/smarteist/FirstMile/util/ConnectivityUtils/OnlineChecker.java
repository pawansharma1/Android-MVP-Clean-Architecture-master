package com.smarteist.FirstMile.util.ConnectivityUtils;

/**
 * Simple interface that contains online/offline state indicator
 */

public interface OnlineChecker {

    boolean isOnline();

    void setOnNetworkStateChangedListener(OnlineCheckerListener listener);

    interface OnlineCheckerListener {

        void networkStateChanged(boolean isOnline);
    }
}