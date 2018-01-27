package com.alonso.reactiveprogramming.subscriber;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;

public class SubjectExecutio {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void name() throws Exception{
        PublishSubject<Integer> publishSubject = PublishSubject.create();

        Observable<Integer> observable = publishSubject.subscribeOn(Schedulers.computation());

//        observable = observable.subscribeOn(Schedulers.computation());

        observable.subscribe(integer -> System.out.println("Subs1:"+integer+" "+Thread.currentThread().getName()));
        observable.subscribe(integer -> System.out.println("Subs2:"+integer+" "+Thread.currentThread().getName()));

        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);
        publishSubject.onNext(4);

//        Thread.sleep(5000);
    }
}
