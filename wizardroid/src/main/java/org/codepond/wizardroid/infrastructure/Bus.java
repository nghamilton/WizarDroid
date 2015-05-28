package org.codepond.wizardroid.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bus {
    private Map<Subscriber, ArrayList> subscribers = new HashMap<Subscriber, ArrayList>();
    private static Map<Class, Bus> wizardBuses = new HashMap<Class, Bus>();

    private Bus() {
    }

    public static Bus getInstance(Class wizardBus) {

        Bus busForWizard = wizardBuses.get(wizardBus);
        if (busForWizard==null)
        {
            busForWizard = new Bus();
            wizardBuses.put(wizardBus,busForWizard);
        }
        return busForWizard;
    }

    public void post(Object event) {
        Class messageType = event.getClass();
        for (Map.Entry<Subscriber, ArrayList> entry : subscribers.entrySet()) {
            if (entry.getValue().contains(messageType)) {
                entry.getKey().receive(event);
            }
        }
    }

    public void register(Subscriber subscriber, Class<?> eventType) {
        ArrayList<Class<?>> eventsSubscribed;

        //get the subscriber's events listened for
        if (subscribers.containsKey(subscriber)) {
            eventsSubscribed = subscribers.get(subscriber);
        }
        else
        {
            eventsSubscribed = new ArrayList<Class<?>>(1);
        }

        //if the event isnt already listened for, add it
        if (!eventsSubscribed.contains(eventType))
        {
            eventsSubscribed.add(eventType);
            subscribers.put(subscriber, eventsSubscribed);
        }
    }

    public void unregister(Subscriber subscriber) {
        if (subscribers.containsKey(subscriber)) {
            subscribers.remove(subscriber);
        }
    }
}
