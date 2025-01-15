package com.reactive.app.services;

import com.reactive.app.entities.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookServices {

    public Mono<Book> create(Book book);

    public Flux<Book> getAll();

    public Mono<Book> get(int bookId);

    public Mono<Book> update(Book book, int bookId);

    public Mono<Void> delete(int bookId);

    Flux<Book> searchBooks(String titleKeyword);

}
