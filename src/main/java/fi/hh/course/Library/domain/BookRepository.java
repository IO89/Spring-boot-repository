package fi.hh.course.Library.domain;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository <Book,Long>{
	
}

