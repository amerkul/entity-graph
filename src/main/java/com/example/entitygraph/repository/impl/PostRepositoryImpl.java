package com.example.entitygraph.repository.impl;

import com.example.entitygraph.model.Post;
import com.example.entitygraph.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private EntityManager entityManager;

    @Override
    @Transactional
    public Post findById(long id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    @Transactional
    public Post findPostWithAnnotationEntityGraph(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("post-entity-graph-with-comment-users");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        return entityManager.find(Post.class, id, properties);
    }

    @Override
    @Transactional
    public Post findPostWithEntityGraph(long id) {
        EntityGraph<Post> entityGraph = entityManager.createEntityGraph(Post.class);
        entityGraph.addAttributeNodes("subject");
        entityGraph.addAttributeNodes("user");
        entityGraph.addSubgraph("comments")
                .addAttributeNodes("user");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        return entityManager.find(Post.class, id, properties);
    }

    @Override
    @Transactional
    public Post findUsingJpql(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("post-entity-graph-with-comment-users");
        return entityManager.createQuery("Select p from Post p where p.id=:id", Post.class)
                            .setParameter("id", id)
                            .setHint("javax.persistence.fetchgraph", entityGraph)
                            .getSingleResult();
    }

    @Override
    @Transactional
    public Post findUsingCriteria(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("post-entity-graph-with-comment-users");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
        TypedQuery<Post> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        return typedQuery.getSingleResult();
    }

}
