package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/p1/")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	// get all users
	@GetMapping("/posts")
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
//	//create users	
	@PostMapping("/posts")
	public Post createPost(@RequestBody Post post ) {
		return postRepository.save(post);
	}
//	
	//get user by id rest api
	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		
		Post post = postRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));
		return ResponseEntity.ok(post);
	}
	
	// update user rest api
	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails ){
		
		Post post = postRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));
		
		post.settitle(postDetails.gettitle());
		post.setmessage(postDetails.getmessage());
		
		Post updatedPost = postRepository.save(post);
		return ResponseEntity.ok(updatedPost);
		
	}

}
