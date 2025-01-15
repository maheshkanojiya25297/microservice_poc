package com.reactive.app.repositories;

import com.reactive.app.entities.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {


    Mono<Book> findByName(String name);

    Flux<Book> findByAuther(String auther);

    Flux<Book> findByPublisher(String publisher);

    Flux<Book> findByNameAndAuther(String name, String Auther);

    @Query("select * from book_details where  name = :name and author =:author")
    Flux<Book> getAllBooksByAuther(String name, String author);


     @Query("select * from book_details where  name like :title")
     Flux<Book> searchByTitle(String title);

}
