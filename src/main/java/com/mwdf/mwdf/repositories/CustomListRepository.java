package com.mwdf.mwdf.repositories;

import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomListRepository extends JpaRepository<CustomList, Long> {
    List<CustomList> findByUsers(User user);
    CustomList findByIdList(long id);
}

