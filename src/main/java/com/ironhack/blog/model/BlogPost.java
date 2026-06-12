package com.ironhack.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blog_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    
    @NotEmpty(message = "Title cannot be empty")
    @Column(nullable = false)
    private String title;
    
    @NotEmpty(message = "Post content cannot be empty")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String post;
}

