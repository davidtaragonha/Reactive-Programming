package com.alonso.reactiveprogramming;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class EventBus {
    private final Subject<String> publishSubject;

    public EventBus() {
        this.publishSubject = PublishSubject.<String>create().toSerialized();
    }

    public Observable<String> getSubscriber(){
        return this.publishSubject;
    }

    public void publish(String event){
        this.publishSubject.onNext(event);
    }
}
