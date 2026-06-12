package com.ironhack.blog;

import com.ironhack.blog.model.Author;
import com.ironhack.blog.model.BlogPost;
import com.ironhack.blog.repository.AuthorRepository;
import com.ironhack.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create authors
        Author author1 = new Author();
        author1.setName("Aiko Tanaka");
        author1 = authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("Jonas Schmidt");
        author2 = authorRepository.save(author2);

        Author author3 = new Author();
        author3.setName("Cas Van Dijk");
        author3 = authorRepository.save(author3);

        // Create blog posts
        BlogPost post1 = new BlogPost();
        post1.setAuthor(author1);
        post1.setTitle("Boost Your Productivity with 10 Easy Tips");
        post1.setPost("Productivity - we all want it but it seems ...");
        blogPostRepository.save(post1);

        BlogPost post2 = new BlogPost();
        post2.setAuthor(author1);
        post2.setTitle("How to Focus");
        post2.setPost("Do you ever sit down to work and find yourself ...");
        blogPostRepository.save(post2);

        BlogPost post3 = new BlogPost();
        post3.setAuthor(author2);
        post3.setTitle("Learn to Speed Read in 30 Days");
        post3.setPost("Knowledge, not ability, is the great determiner of ...");
        blogPostRepository.save(post3);

        System.out.println("Sample data loaded successfully!");
    }
}

