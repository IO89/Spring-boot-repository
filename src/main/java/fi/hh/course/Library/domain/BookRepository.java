package fi.hh.course.Library.domain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookRepository extends CrudRepository <Book,Long>{
    List<Book> findByAuthor(String author);

}

