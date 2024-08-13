package aiims.cf.td_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aiims.cf.td_api.modal.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
