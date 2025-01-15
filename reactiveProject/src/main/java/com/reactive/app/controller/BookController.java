package com.reactive.app.controller;

import com.reactive.app.entities.Book;
import com.reactive.app.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServices bookServices;

    @PostMapping
    public Mono<Book> create(@RequestBody Book book) {
        return bookServices.create(book);
    }

    @GetMapping
    public Flux<Book> getAll() {
        return bookServices.getAll();
    }

    @GetMapping("/{bId}")
    public Mono<Book> get(@PathVariable("bId") int bookId) {
        return bookServices.get(bookId);
    }

    @PutMapping("/{bookId}")
    public Mono<Book> update(@RequestBody Book book, @PathVariable int bookId) {
        return bookServices.update(book, bookId);
    }

    @DeleteMapping("/{bookId}")
    public Mono<Void> delete(@PathVariable int bookId) {
        return bookServices.delete(bookId);
    }

    @GetMapping("/search")
    public Flux<Book> searchBooks(@RequestParam("query") String query) {
        return this.bookServices.searchBooks(query);
    }

}
