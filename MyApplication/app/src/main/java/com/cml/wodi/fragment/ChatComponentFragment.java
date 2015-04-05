package com.cml.wodi.fragment;

import android.view.View;
import android.widget.Button;

import com.cml.wodi.R;

public class ChatComponentFragment extends BaseFragment implements View.OnClickListener {

    private Button voiceButton;
    private Button keyboardButton;
    private View pressToSpeakView;
    private View editSendmessageView;

    @Override
    protected void init(View view) {

        voiceButton = (Button) view.findViewById(R.id.btn_set_mode_voice);
        keyboardButton = (Button) view.findViewById(R.id.btn_set_mode_keyboard);
        pressToSpeakView = view.findViewById(R.id.btn_press_to_speak);
        editSendmessageView = view.findViewById(R.id.edit_sendmessage);

        voiceButton.setOnClickListener(this);
        keyboardButton.setOnClickListener(this);
        pressToSpeakView.setOnClickListener(this);
    }

    @Override
    int getLayoutResource() {
        return R.layout.fragment_chat_component;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_set_mode_voice://切换成语音
                voiceButton.setVisibility(View.GONE);
                keyboardButton.setVisibility(View.VISIBLE);
                pressToSpeakView.setVisibility(View.VISIBLE);
                editSendmessageView.setVisibility(View.GONE);
                break;

            case R.id.btn_set_mode_keyboard://切换成输入法
                voiceButton.setVisibility(View.VISIBLE);
                keyboardButton.setVisibility(View.GONE);
                pressToSpeakView.setVisibility(View.GONE);
                editSendmessageView.setVisibility(View.VISIBLE);
                break;
        }

    }
}
