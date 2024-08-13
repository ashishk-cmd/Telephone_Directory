package aiims.cf.td_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aiims.cf.td_api.modal.Helpdesk;

public interface HelpdeskRepository extends JpaRepository<Helpdesk, Long> {

	
	List<Helpdesk> findAllByStatus(String active);

}
