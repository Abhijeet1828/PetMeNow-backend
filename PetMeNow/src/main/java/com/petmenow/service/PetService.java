package com.petmenow.service;

import org.springframework.web.multipart.MultipartFile;

import com.petmenow.request.RegisterPetRequest;
import com.petmenow.request.UpdatePetRequest;

public interface PetService {

	public Object registerPet(RegisterPetRequest registerPetRequest);

	public int deletePet(Long id);

	public Object updatePet(UpdatePetRequest updatePetRequest);

	public Object fetchPetInformation(Long id);

	public Object fetchPetTypes();

	public Object searchPetBreeds(Long petTypeId, String searchField);

	public Object uploadPetImage(Long petId, MultipartFile multipartFile);

}
