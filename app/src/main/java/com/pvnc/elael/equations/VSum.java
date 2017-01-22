package com.pvnc.elael.equations;

import android.content.ClipData;
import android.content.ClipDescription;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Edu on 21/01/2017.
 */

public class VSum extends LinearLayout implements EqFactor {
    ArrayList<View> factors = new ArrayList<>();
    ArrayList<TextView> connectors = new ArrayList<>();
    LinearLayout view;
    View parent;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData.Item item = new ClipData.Item("");

            ClipData dragData = new ClipData("VSum", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);


            // Instantiates the drag shadow builder.
            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(this);

            // Starts the drag

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
                this.startDragAndDrop(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        this,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
            } else{
                this.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        this,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
            }

        }
        return false;
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DROP:
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                this.connectors.add(new TextView(this.getContext()));
                this.connectors.get(this.connectors.size()-1).setText("+");
                this.addView(this.connectors.get(this.connectors.size()-1));
                this.addView(view);
                this.factors.add(view);
                view.setVisibility(View.VISIBLE);
                break;


        }
        return true;
    }

    public VSum(View[] factors, View parent) {
        /*
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:orientation="horizontal"*/
        super(parent.getContext());
        for (int i = 0; i < factors.length; i++)
        for(View factor: factors)
            this.factors.add(factor);
    }

    public ArrayList<View> getValue() {
        return factors;
    }
}
