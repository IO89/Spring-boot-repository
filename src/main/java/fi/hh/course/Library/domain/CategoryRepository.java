package fi.hh.course.Library.domain;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByCategoryName(String categoryName);

}