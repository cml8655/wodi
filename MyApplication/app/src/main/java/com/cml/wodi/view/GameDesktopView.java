package com.cml.wodi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.cml.wodi.R;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

public class GameDesktopView extends DesktopView {

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ObjectAnimator.ofFloat(v, "x", v.getX(), center.x - childSize / 2).setDuration(2000).start();
            ObjectAnimator.ofFloat(v, "y", v.getY(), center.y - childSize / 2).setDuration(2000).start();
        }
    };

    public GameDesktopView(Context context) {
        super(context);
    }

    public GameDesktopView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameDesktopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected List<View> getChildViews() {
        List<View> list = new ArrayList<View>();

        if (adapter != null && adapter.getCount() > 0) {
            //TODO 生成view
            int len = adapter.getCount();

            LayoutInflater inflater = LayoutInflater.from(getContext());

            for (int i = 0; i < len; i++) {
                CircleImageView view = (CircleImageView) inflater.inflate(R.layout.view_game_user, null);
                view.setImageResource(R.drawable.ic_launcher);
                view.setOnClickListener(listener);
                list.add(view);
            }
        }

        return list;
    }


}
