package com.cml.wodi.controller;

import java.util.List;

/**
 * Created by teamlab on 2015/3/30.
 */
public class MessageController {

    private GameListener listener;

    public MessageController(GameListener listener) {
        this.listener = listener;
    }

    public void onReceiverMsg() {
        //TODO 处理业务逻辑，XXXX 控制状态
        //1 onVoted
        //2 onRevote
        //3 onVoteFinished
    }

    public static interface GameListener {

        public void onVote();

        public void onRevote(List<String> candidates);

        public void onVoteFinished(String votedUser);

        public void onYouTurn();

        public void onSpeakingFinish();
    }
}
