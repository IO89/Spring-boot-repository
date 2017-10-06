package fi.hh.course.Library;

import fi.hh.course.Library.domain.Category;
import fi.hh.course.Library.domain.CategoryRepository;
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
    public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository) {
        return (args) -> {

            categoryRepository.save(new Category("Sci-Fi"));
            categoryRepository.save(new Category("Detective"));
            categoryRepository.save(new Category("Horror"));

            repository.save(new Book("TitleA", "AthourA", 1999, "ISBN1", 10, categoryRepository.findByCategoryName("Sci-Fi").get(0)));
            repository.save(new Book("TitileB", "AthourB", 1990, "ISBN2", 2, categoryRepository.findByCategoryName("Sci-Fi").get(0)));



            for (Book book : repository.findAll()) {
                System.out.println(book.toString());
            }
            for (Category category : categoryRepository.findAll()) {
                System.out.println(category.toString());
            }
        };

    }
}