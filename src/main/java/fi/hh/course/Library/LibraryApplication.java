package fi.hh.course.Library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.course.Library.domain.Book;
import fi.hh.course.Library.domain.BookRepository;

@SpringBootApplication


public class LibraryApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(LibraryApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository){
		return (args) ->{
			repository.save(new Book("TitleA", "AthourA", 1999, "ISBN1", 10));
			repository.save(new Book("TitileB","AthourB",1990,"ISBN2",2));
			
			for (Book book: repository.findAll()){
			System.out.println(book.toString());
			}
		};
		
	}
}
