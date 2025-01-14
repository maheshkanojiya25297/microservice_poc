package com.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.util.Tuple;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ReactiveProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void workingWithMono() {
        System.out.println("Testing");

        //Mono.errror() method for throwing error
        Mono<String> errorMono = Mono.error(new RuntimeException("Error !!"));

        //Mono.just() method for creating mono object
        //mono-> publisher that have 0 and 1 items
        Mono<String> m1 = Mono.just("This is mono creation")
                .log()
                .then(errorMono);

        m1.subscribe(data -> {
            System.out.println("mono data is : " + data);
        });
        //replace lambda with method refrences here we consume the mono by subscribing
        m1.subscribe(System.out::println);
        errorMono.subscribe(System.out::println);


        Mono<String> m11 = Mono.just("first mono");
        Mono<String> m12 = Mono.just("second mono");
        Mono<Integer> m13 = Mono.just(12345);
        //Mono.zip() method for combining mono objects
        Mono<Tuple3<String, String, Integer>> combinedMono = Mono.zip(m11, m12, m13);
        combinedMono.subscribe(System.out::println);

        combinedMono.subscribe(data -> {
            System.out.println(data.getT1());
            System.out.println(data.getT2());
            System.out.println(data.getT3());
        });
        //Mono.zipWith() method for combinig only 2 mono object
        Mono<Tuple2<String,String>> zipWithMono = m11.zipWith(m12);
        zipWithMono.subscribe(data->{
           System.out.println(data.getT1());
            System.out.println(data.getT2());
        });


        //map method work based on applying sync function to perform transformation
        Mono<String> mapOut = m11.map(String::toUpperCase);
        mapOut.subscribe(System.out::println);

        //flateMap method work based on applying Asyncronous function to perform transformation
        Mono<String[]> flatmapOut = m11.flatMap(valueM1 -> Mono.just(valueM1.split(" ")));

        flatmapOut.subscribe(data -> {
            System.out.println(Arrays.toString(data));
        });

        //use here flateMapMany for flux object
        Flux<String> stringFlux = m11.flatMapMany(value1 -> Flux.just(value1.split(" "))).log().delayElements(Duration.ofMillis(2000));
        stringFlux.subscribe(data -> {
            System.out.println(data);
        });

        //concatWith method returns flux as result
        Flux<String> concatResult = m11.concatWith(m12);
        concatResult.subscribe(System.out::println);
    }
}
