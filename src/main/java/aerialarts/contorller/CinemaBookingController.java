package aerialarts.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aerialarts.repository.BookingRepository;
import aerialarts.repository.CinemaShowRepository;
import aerialarts.repository.CinemaUserRepository;
import aerialarts.repository.RoleRespository;
import aerialarts.model.Booking;
import aerialarts.model.CinemaShow;

@RestController
public class CinemaBookingController {
	
	private static boolean debug = true;

	@Autowired
	RoleRespository rr;

	@Autowired
	BookingRepository br;

	@Autowired
	CinemaUserRepository ur;

	@Autowired
	CinemaShowRepository sr;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@RequestMapping("/showAvailability")
	public String book(@RequestParam("showId") int id) {

		debug("Parameter: Show: " + id);

		List<Booking> availableBookings = br.openForBooking(id);
		
		debug("Available booking size: " + availableBookings.size());
		
		CinemaShow show = sr.getOne(id);

		int availableSeats = show.getNumberOfSeat() - availableBookings.size();

		if (availableSeats > 0) {
			return "Available seats are: " + availableSeats;
		}

		return "Seats are not available";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/cinemaShows/{id}/update")
	public String updateShow(@PathVariable(value = "id") int id, @RequestParam("active") boolean active) {

		CinemaShow show = sr.findOne(id);

		show.setActive(active);

		sr.save(show);

		if (active) {
			return "true";
		}

		return "false";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/cinemaShows/update")
	public String updateAllShow(@RequestParam("active") boolean active) {

		List<CinemaShow> shows = sr.findAll();

		for (CinemaShow show : shows) {
			show.setActive(active);
			sr.save(show);
		}

		return "done";

	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@RequestMapping("/booking/{bookingId}/update")
	public String cancelShow(@PathVariable(value = "bookingId") int bookingId, @RequestParam("status") int status) {

		Booking booking = br.findOne(bookingId);

		booking.setStatus(status);

		br.save(booking);

		if (status == Booking.STATUS__BOOKED) {
			return "booked";
		}else if(status == Booking.STATUS__BOOKED_CANCELED){
			return "canceled";
		}

		return "";
	}
	
	private void debug( String debugString ) {
		
		if ( debug ) {
			System.out.println( "[DEBUG] : " + debugString );
		}

	}

}