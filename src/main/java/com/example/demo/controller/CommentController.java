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
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/c1/")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	// get all users
	@GetMapping("/comments")
	public List<Comment> getAllUsers(){
		return commentRepository.findAll();
	}
	
//	//create users
	@PostMapping("/comments")
	public Comment createComment(@RequestBody Comment comment) {
		return commentRepository.save(comment);
	}
	
	//get user by id rest api
	@GetMapping("/comments/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
		
		Comment comment = commentRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));
		return ResponseEntity.ok(comment);
	}
	
	// update user rest api
	@PutMapping("/comments/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails ){
		
		Comment comment = commentRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));
		
		comment.setcomment(commentDetails.getcomment());
		
		
		Comment updatedComment = commentRepository.save(comment);
		return ResponseEntity.ok(updatedComment);
		
	}

}
