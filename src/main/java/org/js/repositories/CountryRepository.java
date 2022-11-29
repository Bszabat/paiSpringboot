package org.js.repositories;

import org.js.entities.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, String> {
    List<Country> findCountriesByContinent(String continent);
    List<Country> findCountriesByPopulationBetween(int min, int max);
    List<Country> findCountriesBySurfaceAreaBetweenAndContinent(double min, double max, String name);
}