package aerialarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import aerialarts.model.CinemaShow;

@RepositoryRestResource
public interface CinemaShowRepository extends JpaRepository<CinemaShow, Integer> {

}
