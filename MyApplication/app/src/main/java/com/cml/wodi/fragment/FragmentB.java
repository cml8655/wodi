package com.cml.wodi.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.cml.wodi.ModalActivity;
import com.cml.wodi.R;
import com.cml.wodi.parcel.ContentParcel;

public class FragmentB extends BaseFragment implements View.OnClickListener {

    private Button buttonAlert;
    private Button buttonAlert2;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_b;
    }

    @Override
    protected void init(View view) {
        buttonAlert = (Button) view.findViewById(R.id.btn_alert_ac);
        buttonAlert.setOnClickListener(this);
        buttonAlert2 = (Button) view.findViewById(R.id.btn_alert_ac2);
        buttonAlert2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), ModalActivity.class);
        ContentParcel contentParcel = new ContentParcel();

        if (v == buttonAlert) {
            contentParcel.setClassName(FragmentA.class.getName());
            contentParcel.setTitleRes(R.string.hello_world);
        } else {
            contentParcel.setClassName(FragmentB.class.getName());
            contentParcel.setTitleRes(R.string.hello_world);
        }


        intent.putExtra(ModalActivity.EXTRA_DATA, contentParcel);
        getActivity().startActivity(intent);
    }
}
