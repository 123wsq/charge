package com.example.wsq.library.listener;

import android.view.View;

public interface OnRecyclerCheckboxListener {

    void onListener(int position, View view);

    void onStateChange(int position, boolean checked);
}
