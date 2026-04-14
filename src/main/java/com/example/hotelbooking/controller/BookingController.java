package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // 本来はUIのURLを指定するのがベストですが、まずは接続確認のため "*" で全て許可します
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingRepository bookingRepository;

	/**
	 * GET /api/bookings - Fetch all bookings
	 */
	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		return ResponseEntity.ok(bookings);
	}

	/**
	 * GET /api/bookings/{id} - Fetch a specific booking
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
		return bookingRepository.findById(id)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * POST /api/bookings - Create a new booking
	 */
	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		Booking savedBooking = bookingRepository.save(booking);
		return ResponseEntity.status(201).body(savedBooking);
	}

	/**
	 * PUT /api/bookings/{id} - Update a booking
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
		return bookingRepository.findById(id)
			.map(booking -> {
				booking.setCheckInDate(bookingDetails.getCheckInDate());
				booking.setCheckOutDate(bookingDetails.getCheckOutDate());
				booking.setGuestName(bookingDetails.getGuestName());
				booking.setGuestEmail(bookingDetails.getGuestEmail());
				booking.setGuestPhone(bookingDetails.getGuestPhone());
				booking.setTotalPrice(bookingDetails.getTotalPrice());
				booking.setStatus(bookingDetails.getStatus());
				return ResponseEntity.ok(bookingRepository.save(booking));
			})
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * DELETE /api/bookings/{id} - Cancel a booking
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		if (bookingRepository.existsById(id)) {
			bookingRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
