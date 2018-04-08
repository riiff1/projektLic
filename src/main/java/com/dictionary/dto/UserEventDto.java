package com.dictionary.dto;

import java.io.Serializable;

public class UserEventDto implements Serializable {
    private static final long serialVersionUID = 2954609136435249513L;
    private long userEventId;
    private long userId;
    private long eventId;

    public long getUserEventId() {
        return userEventId;
    }

    public void setUserEventId(long userEventId) {
        this.userEventId = userEventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
