package com.pvnc.elael.equations;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Edu on 21/01/2017.
 */

public class VInteger extends TextView implements EqFactor {
    int value;
    View parent;
    public VInteger(int value, View parent) {
        super(parent.getContext());
        this.value = value;
        this.parent = parent;
        this.setText(String.valueOf(value));
    }

    public int getValue() {
        return value;
    }
}
