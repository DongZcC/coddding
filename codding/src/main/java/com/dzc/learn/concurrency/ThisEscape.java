package com.dzc.learn.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            public void onEvent(Event e) {
                ThisEscape.this.doSomething(e);
            }
        });
    }

    void doSomething(Event e) {
        System.out.println("do something to e");
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }


    class DefaultEventSource implements EventSource {

        List<EventListener> listenerLists = new ArrayList<>();

        @Override
        public void registerListener(EventListener e) {
            listenerLists.add(e);
        }

        public void onEvent(Event e) {
            // ...
        }
    }
}
