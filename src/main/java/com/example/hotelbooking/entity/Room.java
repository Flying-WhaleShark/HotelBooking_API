package com.example.hotelbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;

    // ここを (name = "image_url") に修正しました
    @Column(name = "image_url", length = 1000)
    private String imageUrl;

    // ここを (name = "large_image_url") に修正しました
    @Column(name = "large_image_url", length = 1000)
    private String largeImageUrl;

    @Column(nullable = false)
    private Integer capacity;

    public Room() {
    }

    public Room(String name, String description, Double price, String imageUrl, String largeImageUrl, Integer capacity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.largeImageUrl = largeImageUrl;
        this.capacity = capacity;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getLargeImageUrl() { return largeImageUrl; }
    public void setLargeImageUrl(String largeImageUrl) { this.largeImageUrl = largeImageUrl; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
