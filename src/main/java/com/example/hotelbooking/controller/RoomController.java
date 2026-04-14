package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // 本来はUIのURLを指定するのがベストですが、まずは接続確認のため "*" で全て許可します
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;

	/**
	 * GET /api/rooms - Fetch all rooms
	 */
	@GetMapping
	public ResponseEntity<List<Room>> getAllRooms() {
		List<Room> rooms = roomRepository.findAll();
		return ResponseEntity.ok(rooms);
	}

	/**
	 * GET /api/rooms/{id} - Fetch a specific room
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
		return roomRepository.findById(id)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * POST /api/rooms - Create a new room
	 */
	@PostMapping
	public ResponseEntity<Room> createRoom(@RequestBody Room room) {
		Room savedRoom = roomRepository.save(room);
		return ResponseEntity.status(201).body(savedRoom);
	}

	/**
	 * PUT /api/rooms/{id} - Update a room
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
		return roomRepository.findById(id)
			.map(room -> {
				room.setName(roomDetails.getName());
				room.setDescription(roomDetails.getDescription());
				room.setPrice(roomDetails.getPrice());
				room.setImageUrl(roomDetails.getImageUrl());
				room.setCapacity(roomDetails.getCapacity());
				return ResponseEntity.ok(roomRepository.save(room));
			})
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * DELETE /api/rooms/{id} - Delete a room
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
		if (roomRepository.existsById(id)) {
			roomRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
