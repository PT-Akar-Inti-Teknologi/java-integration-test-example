package org.ait.project.guideline.example.modules.post.model.repository;

import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;

import java.util.List;

public interface JsonPlaceHolderPostCustomRepository {

    List<JsonPlaceHolderPost> findAll();

}
