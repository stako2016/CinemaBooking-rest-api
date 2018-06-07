package aerialarts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import aerialarts.model.Booking;

@RepositoryRestResource
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query(" select b from Booking b JOIN b.cinemaShow c where  c.id = ?1 ")
	List<Booking> openForBooking(int id);

}