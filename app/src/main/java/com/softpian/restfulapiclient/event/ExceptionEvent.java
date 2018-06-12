package com.softpian.restfulapiclient.event;

public class ExceptionEvent {

    private String mMessage;
    private Throwable mThrowable;

    public ExceptionEvent(String message, Throwable throwable) {
        mMessage = message;
        mThrowable = throwable;
    }

    public String getMessage() {
        return mMessage;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}
