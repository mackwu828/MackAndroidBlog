package com.mackwu.component.jbase.structure;

/**
 * @author MackWu
 * @since 2022/5/19 15:26
 */
public class MyMessageQueue {

    private Message head;

    public void enqueueMessage(Message msg, int when) {
        msg.when = when;
        // 如果头节点是空，则将该消息设为头节点
        if (head == null) {
            msg.next = null;
            head = msg;
            return;
        }
        // 如果新消息是非延时消息或者延时时间小于头节点的延时时间，则将该消息设为头节点, 下一个节点指向旧头节点。
        if (when == 0 || when < head.when) {
            msg.next = head;
            head = msg;
            return;
        }

        // 如果新消息的延时时间大于头节点，则遍历所有消息，插入到大于该延时时间的消息前面。
        Message temp = head;
        Message prev;
        for (; ; ) {
            prev = temp;
            temp = temp.next;
            if (temp == null || when < temp.when) {
                break;
            }
        }
        msg.next = temp;
        prev.next = msg;
    }

    public Message next() {
        Message msg = head;
        Message prev = null;
//        if (msg != null) {
//            do {
//                prev = msg;
//                msg = msg.next;
//            } while (msg != null);
//        }

        if (msg != null) {
//            if (now < msg.when) {
//
//            }
            head = msg.next;
            msg.next = null;
        }
        return msg;
    }

    public void print() {
        Message temp = head;
        while (temp != null) {
            temp.print();
            temp = temp.next;
        }
        System.out.println();
    }

    static class Message {
        // 下一个消息
        Message next;
        // 数据
        int what;
        // 时间
        int when;

        public Message(int what) {
            this.what = what;
        }

        public void print() {
            if (this.next != null) {
                System.out.print("(" + this.what + "," + this.when + "|" + this.next.what + ") -> ");
            } else {
                System.out.print("(" + this.what + "," + this.when + "|null)");
            }
        }
    }

    public static void main(String[] args) {
        MyMessageQueue messageQueue = new MyMessageQueue();
        // (3,0|2) -> (2,0|1) -> (1,0|null)
//        messageQueue.enqueueMessage(new Message(1), 0);
//        messageQueue.enqueueMessage(new Message(2), 0);
//        messageQueue.enqueueMessage(new Message(3), 0);
//        messageQueue.print();

        // (1,5|3) -> (3,8|2) -> (2,10|null)
        messageQueue.enqueueMessage(new Message(1), 5);
        messageQueue.enqueueMessage(new Message(2), 10);
        messageQueue.enqueueMessage(new Message(3), 8);
        messageQueue.print();


        Message next = messageQueue.next();
        next.print();
        System.out.println();
        messageQueue.print();
    }

}
