package org.ait.project.guideline.example.modules.post.model.repository;

import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JsonPlaceHolderPostRepository extends JpaRepository<JsonPlaceHolderPost,Integer> {

    @Query("SELECT json from JsonPlaceHolderPost json where json.id = ?1")
    Optional<JsonPlaceHolderPost> findById(Integer id);

}
