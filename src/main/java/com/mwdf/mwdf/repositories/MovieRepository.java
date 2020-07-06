package com.mwdf.mwdf.repositories;

import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByLists(CustomList list);
}
