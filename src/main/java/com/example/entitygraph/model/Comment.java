package com.example.entitygraph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "comments")
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reply;

    @JoinColumn
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
