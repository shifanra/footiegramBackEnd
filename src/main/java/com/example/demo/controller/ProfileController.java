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
import com.example.demo.model.Profile;
import com.example.demo.repository.ProfileRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/p2/")
public class ProfileController {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	// get all users
	@GetMapping("/profiles")
	public List<Profile> getAllProfiles(){
		return profileRepository.findAll();
	}
	
//	//create users	
	@PostMapping("/profiles")
	public Profile createProfile(@RequestBody Profile profile ) {
		return profileRepository.save(profile);
	}
//	
	//get user by id rest api
	@GetMapping("/profiles/{id}")
	public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
		
		Profile profile = profileRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));
		return ResponseEntity.ok(profile);
	}
	
	// update user rest api
	@PutMapping("/profiles/{id}")
	public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile profileDetails ){
		
		Profile profile = profileRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));
		
		profile.setwork(profileDetails.getwork());
		profile.setfrom(profileDetails.getfrom());
		profile.setlive(profileDetails.getlive());
		profile.setstatus(profileDetails.getstatus());
		
		Profile updatedProfile = profileRepository.save(profile);
		return ResponseEntity.ok(updatedProfile);
		
	}

}
