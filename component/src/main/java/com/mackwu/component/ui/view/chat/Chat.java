package com.mackwu.component.ui.view.chat;

/**
 * @author MackWu
 * @since 2023/6/28 17:06
 */
public class Chat {

    private String text;

    public Chat(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
