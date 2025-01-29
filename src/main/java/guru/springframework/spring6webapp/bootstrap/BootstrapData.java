package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book mybook = new Book();
        mybook.setTitle("Domain Driven Design");
        mybook.setIsbn("123456");


        Author ericSaved = authorRepository.save(eric);
        Book mybookSaved = bookRepository.save(mybook);


        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJBBook = new Book();
        noEJBBook.setTitle("No-EJB Book");
        noEJBBook.setIsbn("1111111");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBBookSaved = bookRepository.save(noEJBBook);

        ericSaved.getBooks().add(mybookSaved);
        rodSaved.getBooks().add(noEJBBookSaved);

        mybookSaved.getAuthors().add(ericSaved);
        noEJBBookSaved.getAuthors().add(rod);



        //publisher
        Publisher publisher = new Publisher();
        publisher.setPublisherName("");
        publisher.setAddress("");
        publisher.setCity("");
        publisher.setState("");
        publisher.setZipCode("");

        Publisher savedPublisher = publisherRepository.save(publisher);
        mybookSaved.setPublisher(publisher);
        noEJBBookSaved.setPublisher(publisher);





        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(mybookSaved);
        bookRepository.save(noEJBBookSaved);


        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
