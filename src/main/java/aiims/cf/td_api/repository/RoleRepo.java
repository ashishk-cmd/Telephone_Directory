package aiims.cf.td_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aiims.cf.td_api.modal.Role;



@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

	 public Optional<Role> findByName(String name);

	public Optional<Role> findById(Long roleId);
	
	
}
