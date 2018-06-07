package aerialarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import aerialarts.model.Role;

@RepositoryRestResource
public interface RoleRespository extends JpaRepository<Role, Integer> {

}
