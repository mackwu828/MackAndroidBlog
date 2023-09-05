package com.mackwu.component.ui.view.chat;

import android.view.ViewGroup;

/**
 * @author MackWu
 * @since 2023/6/28 17:31
 */
public class ChatManager {

    private static ChatManager instance;

    private ChatManager() {

    }

    public static ChatManager getInstance() {
        if (instance == null) {
            instance = new ChatManager();
        }
        return instance;
    }

    public void bindChatContainer(ViewGroup viewGroup) {

    }

    public void showChat(ViewGroup chatContainer) {
//        chatLinkedList.add(chat);
    }

}
