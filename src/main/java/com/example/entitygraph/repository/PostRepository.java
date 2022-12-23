package com.example.entitygraph.repository;

import com.example.entitygraph.model.Post;

public interface PostRepository {

    Post findById(long id);
    Post findPostWithAnnotationEntityGraph(long id);
    Post findPostWithEntityGraph(long id);
    Post findUsingJpql(long id);

    Post findUsingCriteria(long id);

}
