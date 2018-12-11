package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {}
