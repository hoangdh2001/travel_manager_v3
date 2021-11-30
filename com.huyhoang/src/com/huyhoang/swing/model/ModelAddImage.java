package com.huyhoang.swing.model;

import com.huyhoang.swing.event.EventAddImage;

public class ModelAddImage {
    private Object object;
    private EventAddImage event;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public EventAddImage getEvent() {
        return event;
    }

    public void setEvent(EventAddImage event) {
        this.event = event;
    }

    public ModelAddImage(Object object, EventAddImage event) {
        this.object = object;
        this.event = event;
    }

}
