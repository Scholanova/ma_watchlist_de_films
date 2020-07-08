package com.mwdf.mwdf.repositories;

import com.mwdf.mwdf.models.Comment;
import com.mwdf.mwdf.models.Movie;
import com.mwdf.mwdf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserAndMovie(User user, Movie movie);
}
