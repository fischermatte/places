package org.mattensoft.places.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.mattensoft.places.model.Place;

public interface PlacesRepository extends MongoRepository<Place, String> {
}
