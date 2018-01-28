package com.alonso.reactiveprogramming;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class SubjectExecution {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void replaySubject() throws Exception{
        ReplaySubject<Integer> replaySubject = ReplaySubject.create();
        replaySubject.observeOn(Schedulers.computation()).subscribe(integer -> System.out.println("Subs:"+integer+" "+Thread.currentThread().getName()));

        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        replaySubject.onNext(4);



        Thread.sleep(5000);
    }

    @Test
    public void eventBus_subscribers_computation() throws Exception{
       EventBus eventBus = new EventBus();
        Observable<String> observable = eventBus.getSubscriber().observeOn(Schedulers.computation());


        observable.subscribe(event -> System.out.println("S1:"+Thread.currentThread().getName()+" Event:"+event));
        observable.subscribe(event -> System.out.println("S2:"+Thread.currentThread().getName()+" Event:"+event));
        observable.subscribe(event -> System.out.println("S3:"+Thread.currentThread().getName()+" Event:"+event));


        eventBus.publish("event1");
        eventBus.publish("event2");
        eventBus.publish("event3");
        Thread.sleep(5000);
    }

    @Test
    public void eventBus_subscriber_computation_multithread_publish() throws Exception{
        EventBus eventBus = new EventBus();
        Observable<String> observable = eventBus.getSubscriber().observeOn(Schedulers.computation());

        observable.subscribe(event -> System.out.println("S1:"+Thread.currentThread().getName()+" Event:"+event));
        observable.subscribe(event -> System.out.println("S2:"+Thread.currentThread().getName()+" Event:"+event));

        CompletableFuture.runAsync(()->
                {
                    eventBus.publish("Aevent1");
                    eventBus.publish("Aevent2");
                    eventBus.publish("Aevent3");
                    System.out.println("A finished of publish ->"+Thread.currentThread().getName());
                }
        );

        CompletableFuture.runAsync(()->
                {
                    eventBus.publish("Bevent1");
                    eventBus.publish("Bevent2");
                    System.out.println("B finished of publish ->"+Thread.currentThread().getName());
                }
        );

        Thread.sleep(5000);
    }
}
