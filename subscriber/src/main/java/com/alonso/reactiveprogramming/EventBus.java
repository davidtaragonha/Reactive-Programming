package com.alonso.reactiveprogramming;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static io.reactivex.subjects.PublishSubject.*;

public class EventBus {
    private final PublishSubject<String> publishSubject;

    public EventBus() {
        this.publishSubject = create();
    }

    public Observable<String> getSubscriber(){
        return this.publishSubject;
    }

    public void publish(String event){
        this.publishSubject.onNext(event);
    }
}
