package aerialarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import aerialarts.model.Movie;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
