package com.baya.spring5webapp.bootstrap;

import com.baya.spring5webapp.model.Author;
import com.baya.spring5webapp.model.Book;
import com.baya.spring5webapp.model.Publisher;
import com.baya.spring5webapp.repositories.AuthorRepository;
import com.baya.spring5webapp.repositories.BookRepository;
import com.baya.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher p1 = new Publisher("CareerCup", "Bangalore");
        Publisher p2 = new Publisher("McGill", "Hyderabad");

        publisherRepository.save(p1);
        publisherRepository.save(p2);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Cracking the coding interview", "1234", p1);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE developement without EJB", "2344", p2);

        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }
}
