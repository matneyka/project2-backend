package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.UserComment;
import com.revature.services.UserCommentService;

// don't send back vague codes such as: 200, 300, 400, and 500 if it can be avoided.
// user plural when giving value to RequestMapping
// 201- created
@RestController
@RequestMapping(value="/user-comments")
public class UserCommentController {
	
	static {
		System.out.println("[DEBUG] - ArticleController instatiated...");
	}
	
	@Autowired
	private UserCommentService userCommentService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserComment> getAll() {
		System.out.println("[DEBUG] - In ArticleController.getAllArticles()...");
		return userCommentService.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public UserComment getUserCommentById(@PathVariable int Id) {
		System.out.println("[DEBUG] - In ArticleController.getArticleById()...");
	    UserComment userComment = userCommentService.getById(Id);	
	    
	    if(userComment == null) {
	    	return null;//throw error here
	    }
	    
	    return userComment;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserComment> addUserComment(@RequestBody UserComment newUserComment) {
		System.out.println("[DEBUG] - In ArticleController.getArticleById()...");
		UserComment userComment = userCommentService.addUserComment(newUserComment);
		return new ResponseEntity<UserComment>(userComment, HttpStatus.CREATED); 
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserComment> updateUserComment(@RequestBody UserComment updatedUserComment) {
		System.out.println("[DEBUG] - In ArticleController.updateArticle()...");
		UserComment userComment = userCommentService.updateUserComment(updatedUserComment);
		
		if (userComment == null) {
			return null;//throw error here
		}
		
		return new ResponseEntity<UserComment>(HttpStatus.OK); //code 200
	}
}