package org.ait.project.guideline.example.modules.post.model.repository.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.guideline.example.modules.post.model.entity.JsonPlaceHolderPost;
import org.ait.project.guideline.example.modules.post.model.repository.JsonPlaceHolderPostCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JsonPlaceHolderPostCustomRepositoryImpl implements JsonPlaceHolderPostCustomRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<JsonPlaceHolderPost> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JsonPlaceHolderPost> query = builder.createQuery(JsonPlaceHolderPost.class);
        Root<JsonPlaceHolderPost> root = query.from(JsonPlaceHolderPost.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }
}
