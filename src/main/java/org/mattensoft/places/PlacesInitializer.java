package org.mattensoft.places;

import javax.inject.Inject;
import javax.inject.Named;

import org.resthub.common.util.PostInitialize;

import org.mattensoft.places.model.Place;
import org.mattensoft.places.repository.PlacesRepository;

@Named
public class PlacesInitializer {

    @Inject
    @Named("placesRepository")
    private PlacesRepository placesRepository;

    @PostInitialize(order = 2)
    public void init() {
        placesRepository.save(new Place("Berlin"));
        placesRepository.save(new Place("Hamburg"));
        placesRepository.save(new Place("Rostock"));
    }
}
