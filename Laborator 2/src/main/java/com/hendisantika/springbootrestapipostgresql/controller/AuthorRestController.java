package com.hendisantika.springbootrestapipostgresql.controller;

import com.hendisantika.springbootrestapipostgresql.entity.Author;
import com.hendisantika.springbootrestapipostgresql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    @Autowired
    private AuthorRepository repository;

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author savedAuthor = repository.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<Author>> getAllAuthors() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorWithId(@PathVariable Long id) {
        Optional<Author> authorOptional = repository.findById(id);
        if (authorOptional.isPresent()) {
            return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<Collection<Author>> findAuthorWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthorFromDB(@PathVariable("id") long id, @RequestBody Author author) {
        Optional<Author> currentAuthorOpt = repository.findById(id);
        if (currentAuthorOpt.isPresent()) {
            Author currentAuthor = currentAuthorOpt.get();
            currentAuthor.setName(author.getName());
            currentAuthor.setDescription(author.getDescription());
            currentAuthor.setTags(author.getTags());
            Author updatedAuthor = repository.save(currentAuthor);
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorWithId(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAuthors() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
