package com.example.entitygraph.controller;

import com.example.entitygraph.dto.PostDto;
import com.example.entitygraph.model.Post;
import com.example.entitygraph.repository.PostRepository;
import com.example.entitygraph.util.ModelMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/graph")
public class PostController {

    private ModelMapperUtil mapperUtil;
    private PostRepository postRepository;

    @GetMapping(value = "/post/{id}", produces = "application/json")
    public PostDto getById(@PathVariable("id") long id) {
        return mapperUtil.dtoBuilder(postRepository.findById(id), PostDto.class);
    }

    @GetMapping(value = "/post/annotation/{id}", produces = "application/json")
    public Post getWithAnnotation(@PathVariable("id") long id) {
        return postRepository.findPostWithAnnotationEntityGraph(id);
    }

    @GetMapping(value = "/post/entityGraph/{id}", produces = "application/json")
    public Post getWithEntityGraph(@PathVariable("id") long id) {
        return postRepository.findPostWithEntityGraph(id);
    }

    @GetMapping(value = "/post/jpql/{id}", produces = "application/json")
    public Post getWithJpql(@PathVariable("id") long id) {
        return postRepository.findUsingJpql(id);
    }

    @GetMapping(value = "/post/criteria/{id}", produces = "application/json")
    public Post getWithCriteria(@PathVariable("id") long id) {
        return postRepository.findUsingCriteria(id);
    }

}
