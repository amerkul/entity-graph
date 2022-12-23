package com.example.entitygraph.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
@RequiredArgsConstructor
@NamedEntityGraph(
        name = "post-entity-graph-with-comment-users",
        attributeNodes = {
                @NamedAttributeNode("subject"),
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "comments", subgraph = "comments-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "comments-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("user")
                        }
                )
        }
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subject;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
