package com.ironhack.blog.controller;

import com.ironhack.blog.model.Author;
import com.ironhack.blog.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    // GET - Public access
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }
    
    // POST - Admin only
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }
    
    // PUT - Admin only
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@PathVariable Long id, @Valid @RequestBody Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }
    
    // DELETE - Admin only
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        authorRepository.deleteById(id);
    }
}

