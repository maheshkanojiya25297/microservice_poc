package com.reactive.app;

import com.reactive.app.entities.Book;
import com.reactive.app.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.discovery.predicates.IsTestClassWithTests;
import org.mockito.mock.MockName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void findMethodTest() {

        System.out.println("RepositoryTest");
        Mono<Book> result = bookRepository.findByName("MAHESH SHRIDHAR KANOJIYA");
        StepVerifier.create(result).expectNextCount(1).verifyComplete();


        Flux<Book> result1 = bookRepository.findByAuther("MAHESH");
        StepVerifier.create(result1).expectNextCount(3).verifyComplete();

        bookRepository.findByPublisher("self").as(StepVerifier::create).expectNextCount(3).verifyComplete();

        bookRepository.findByNameAndAuther("MAHESH SHRIDHAR KANOJIYA", "MAHESH")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    public void quertMethodTest() {
       /* bookRepository.getAllBooksByAuther("MAHESH SHRIDHAR KANOJIYA", "MAHESH")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        */

        bookRepository.searchByTitle("%Kevin Cena%").as(StepVerifier::create).expectNextCount(1).verifyComplete();
    }

}
