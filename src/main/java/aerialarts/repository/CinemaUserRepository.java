package aerialarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import aerialarts.model.CinemaUser;

@RepositoryRestResource
public interface CinemaUserRepository extends JpaRepository<CinemaUser, Integer> {
	
	
}
