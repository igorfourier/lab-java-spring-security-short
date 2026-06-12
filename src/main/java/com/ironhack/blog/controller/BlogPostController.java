package com.ironhack.blog.controller;

import com.ironhack.blog.model.BlogPost;
import com.ironhack.blog.repository.AuthorRepository;
import com.ironhack.blog.repository.BlogPostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {
    
    @Autowired
    private BlogPostRepository blogPostRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    // GET - Public access
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogPost getPostById(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }
    
    // POST - Admin only
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost addPost(@Valid @RequestBody BlogPost post) {
        if (post.getAuthor() == null || post.getAuthor().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author is required");
        }
        
        // Verify author exists
        authorRepository.findById(post.getAuthor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
        
        return blogPostRepository.save(post);
    }
    
    // PUT - Admin only
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogPost updatePost(@PathVariable Long id, @Valid @RequestBody BlogPost post) {
        return blogPostRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setTitle(post.getTitle());
                    existingPost.setPost(post.getPost());
                    
                    if (post.getAuthor() != null && post.getAuthor().getId() != null) {
                        authorRepository.findById(post.getAuthor().getId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
                        existingPost.setAuthor(post.getAuthor());
                    }
                    
                    return blogPostRepository.save(existingPost);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }
    
    // DELETE - Admin only
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        blogPostRepository.deleteById(id);
    }
}

