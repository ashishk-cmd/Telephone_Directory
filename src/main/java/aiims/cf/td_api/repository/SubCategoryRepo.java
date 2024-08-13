package aiims.cf.td_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aiims.cf.td_api.modal.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

}
