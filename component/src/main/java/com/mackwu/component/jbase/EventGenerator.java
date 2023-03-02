package com.mackwu.component.jbase;

/**
 * @author MackWu
 * @since 2022/6/15 16:55
 */
public class EventGenerator {

    int currentValue;
    boolean cancel;

    public int next() {
//        synchronized (this) {
            ++currentValue;
            ++currentValue;
            return currentValue;
//        }
    }

    public boolean isCancel() {
        return cancel;
    }

    public void cancel() {
        this.cancel = true;
    }

    private static class EventChecker extends Thread {

        private final EventGenerator eventGenerator;

        public EventChecker(EventGenerator eventGenerator) {
            this.eventGenerator = eventGenerator;
        }

        @Override
        public void run() {
            int nextValue;
            while (!eventGenerator.isCancel()) {
                nextValue = eventGenerator.next();
                if (nextValue % 2 != 0) {
                    System.out.println(nextValue + "不是一个偶数!");
                    eventGenerator.cancel();
                }
            }
        }
    }

    public static void main(String[] args) {
        EventGenerator eventGenerator = new EventGenerator();
        new EventChecker(eventGenerator).start();
        new EventChecker(eventGenerator).start();
    }

}
