package fi.hh.course.Library;

import fi.hh.course.Library.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication


public class LibraryApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository, UserRepository userRepository) {
        return (args) -> {

            categoryRepository.save(new Category("Sci-Fi"));
            categoryRepository.save(new Category("Detective"));
            categoryRepository.save(new Category("Horror"));

            repository.save(new Book("TitleA", "Minh", 1999, "ISBN1", 10, categoryRepository.findByCategoryName("Sci-Fi").get(0)));
            repository.save(new Book("TitileB", "AthourB", 1990, "ISBN2", 2, categoryRepository.findByCategoryName("Sci-Fi").get(0)));
            //set up new user and admin

            User user1 = new User("user", "$2a$06$1kpjLI1hyCZgurfQJjchV.fvwKCd4lpvAMxRA784YgG19dofbD.aC", "USER");
            User user2 = new User("admin", "$2a$06$prcCYpL64wteme13b8CApuS.LHha1rIuKLroXyvNMEXi636cNSPw.", "ADMIN");
            userRepository.save(user1);
            userRepository.save(user2);

            for (Book book : repository.findAll()) {
                System.out.println(book.toString());
            }
            for (Category category : categoryRepository.findAll()) {
                System.out.println(category.toString());
            }
            for (User user : userRepository.findAll()) {
                System.out.println(user.toString());
            }
        };

    }
}