package aiims.cf.td_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aiims.cf.td_api.modal.TelephoneDirectory;

public interface TelephoneDirectoryRepo extends JpaRepository<TelephoneDirectory, Long> {

	List<TelephoneDirectory> findAllByStatus(String status);

}
