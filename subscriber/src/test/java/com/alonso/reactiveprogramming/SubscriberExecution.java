package com.alonso.reactiveprogramming;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubscriberExecution {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void none_subscriber() throws Exception {
        Observable<Integer> observableInts = Observable.just(1,2,3,4);

        observableInts
                .doOnSubscribe(i->  System.out.println("doOnSubscribe -> Thread Name:"+Thread.currentThread().getName()))
                .doOnNext(i -> System.out.println("doOnNext:"+i))
                .subscribe();
    }

    @Test
    public void one_subscriber_main() throws Exception {
        Observable<Integer> observableInts = Observable.just(1,2,3,4);

        observableInts
                .doOnSubscribe(i->  System.out.println("doOnSubscribe -> Thread Name:"+Thread.currentThread().getName()))
                .subscribe(integer -> System.out.println(integer+3));
    }

    @Test
    public void two_subscribers_main() throws Exception {
        Observable<Integer> observableInts = Observable.just(1,2,3,4);

        observableInts = observableInts.doOnSubscribe(i->  System.out.println("doOnSubscribe -> Thread Name:"+Thread.currentThread().getName()));

        observableInts.subscribe(integer -> System.out.println(integer+3));
        observableInts.subscribe(integer -> System.out.println(integer+3));
    }

    @Test
    public void two_subscribers_computation() throws Exception {
        Observable<Integer> observableInts = Observable.just(1,2,3,4);

        observableInts = observableInts
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(i->  System.out.println("doOnSubscribe -> Thread Name:"+Thread.currentThread().getName()));

        observableInts.subscribe(integer ->
        {
            System.out.println(integer+3);
            System.out.println("subscriber -> Thread Name:"+Thread.currentThread().getName());
        });

        observableInts.subscribe(integer ->
        {
            System.out.println(integer+3);
            System.out.println("subscriber -> Thread Name:"+Thread.currentThread().getName());
        });

        Thread.sleep(5000);
    }




    @Test
    public void name() throws Exception {
        Observable<Integer> observableInts = Observable.just(1,2,3,4);

        observableInts = observableInts
                .subscribeOn(Schedulers.computation());

        observableInts.subscribe(integer ->
        {
            System.out.println(integer+3);
            System.out.println("subscriber -> Thread Name:"+Thread.currentThread().getName());
        });

        observableInts.subscribe(integer ->
        {
            System.out.println(integer+3);
            System.out.println("subscriber -> Thread Name:"+Thread.currentThread().getName());
        });

        Thread.sleep(5000);
    }
}
