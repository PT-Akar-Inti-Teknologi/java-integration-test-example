package org.ait.project.guideline.example.modules.post.model.repository;

import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonPlaceHolderPostRepository extends JpaRepository<JsonPlaceHolderPost,Integer> {
}
