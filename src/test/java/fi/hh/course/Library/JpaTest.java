package fi.hh.course.Library;

import fi.hh.course.Library.domain.Book;
import fi.hh.course.Library.domain.BookRepository;
import fi.hh.course.Library.domain.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByAuthorShouldreturnBook() {
        List<Book> testBooks = repository.findByAuthor("Minh");
        assertThat(testBooks).hasSize(1);
        assertThat(testBooks.get(0).getAuthor()).isEqualTo("Minh");
    }

    @Test
    public void createNewBook() {
        Book book = new Book("TitleC", "Arthur", 1999, "ISBN1", 10, categoryRepository.findByCategoryName("Sci-Fi").get(0));
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }
    @Test
    public void deleteBook(){
        List<Book> testBooks = repository.findByAuthor("Minh");
        repository.delete(testBooks.get(0));
        assertThat(repository.findByAuthor("Minh")).hasSize(0);

    }
    @Test
    public void searchBook(){
        List<Book> testBooks = repository.findByAuthor("Minh");
        repository.findByAuthor("Minh");
        assertThat(testBooks).hasSize(1);
    }

}
