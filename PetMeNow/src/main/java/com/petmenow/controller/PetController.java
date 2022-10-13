package com.petmenow.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmenow.constants.FailureConstants;
import com.petmenow.constants.SuccessConstants;
import com.petmenow.exception.CommonException;
import com.petmenow.request.RegisterPetRequest;
import com.petmenow.request.UpdatePetRequest;
import com.petmenow.service.PetService;
import com.petmenow.utilities.ResponseHelper;

@RestController
@RequestMapping(value = "/pet")
public class PetController {

	@Autowired
	private PetService petService;

	@PostMapping(value = "/register")
	public ResponseEntity<Object> registerPet(@RequestBody @Valid RegisterPetRequest registerPetRequest)
			throws CommonException {
		Object response = petService.registerPet(registerPetRequest);
		if (response instanceof Integer) {
			throw new CommonException(FailureConstants.REGISTER_PET_ERROR.getFailureCode(),
					FailureConstants.REGISTER_PET_ERROR.getFailureMsg());
		}

		return ResponseHelper.generateResponse(SuccessConstants.REGISTER_PET_SUCCESS.getSuccessCode(),
				SuccessConstants.REGISTER_PET_SUCCESS.getSuccessMsg(), response);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> deletePet(@PathVariable Long id) throws CommonException {
		int response = petService.deletePet(id);
		switch (response) {
		case 1:
			return ResponseHelper.generateResponse(FailureConstants.PET_NOT_FOUND.getFailureCode(),
					FailureConstants.PET_NOT_FOUND.getFailureMsg());
		case 2:
			return ResponseHelper.generateResponse(SuccessConstants.PET_DELETE_SUCCESS.getSuccessCode(),
					SuccessConstants.PET_DELETE_SUCCESS.getSuccessMsg());
		default:
			throw new CommonException(FailureConstants.DELETE_PET_ERROR.getFailureCode(),
					FailureConstants.DELETE_PET_ERROR.getFailureMsg());
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Object> updatePet(@RequestBody @Valid UpdatePetRequest updatePetRequest)
			throws CommonException {
		Object response = petService.updatePet(updatePetRequest);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(FailureConstants.PET_NOT_FOUND.getFailureCode(),
						FailureConstants.PET_NOT_FOUND.getFailureMsg());
			} else {
				throw new CommonException(FailureConstants.UPDATE_PET_ERROR.getFailureCode(),
						FailureConstants.UPDATE_PET_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.PET_UPDATE_SUCCESS.getSuccessCode(),
				SuccessConstants.PET_UPDATE_SUCCESS.getSuccessMsg(), response);
	}

	@GetMapping(value = "/fetch/{id}")
	public ResponseEntity<Object> fetchPetInformation(@PathVariable Long id) throws CommonException {
		Object response = petService.fetchPetInformation(id);
		if (response instanceof Integer) {
			int error = Integer.parseInt(response.toString());
			if (error == 1) {
				return ResponseHelper.generateResponse(FailureConstants.PET_NOT_FOUND.getFailureCode(),
						FailureConstants.PET_NOT_FOUND.getFailureMsg());
			} else {
				throw new CommonException(FailureConstants.FETCH_PET_ERROR.getFailureCode(),
						FailureConstants.FETCH_PET_ERROR.getFailureMsg());
			}
		}
		return ResponseHelper.generateResponse(SuccessConstants.PET_FETCH_SUCCESS.getSuccessCode(),
				SuccessConstants.PET_FETCH_SUCCESS.getSuccessMsg(), response);
	}

	@GetMapping(value = "/types")
	public ResponseEntity<Object> fetchPetTypes() throws CommonException {
		Object response = petService.fetchPetTypes();
		if (Objects.isNull(response)) {
			throw new CommonException(FailureConstants.PET_TYPE_FETCH_ERROR.getFailureCode(),
					FailureConstants.PET_TYPE_FETCH_ERROR.getFailureMsg());
		}

		return ResponseHelper.generateResponse(SuccessConstants.PET_TYPE_FETCH_SUCCESS.getSuccessCode(),
				SuccessConstants.PET_TYPE_FETCH_SUCCESS.getSuccessMsg(), response);
	}

	@GetMapping(value = "/search-breed/{type}")
	public ResponseEntity<Object> searchPetBreeds(@PathVariable Long type,
			@RequestParam(value = "search", required = true) String searchField) throws CommonException {
		Object response = petService.searchPetBreeds(type, searchField);
		if (Objects.isNull(response)) {
			throw new CommonException(FailureConstants.PET_BREED_SEARCH_ERROR.getFailureCode(),
					FailureConstants.PET_BREED_SEARCH_ERROR.getFailureMsg());
		}

		return ResponseHelper.generateResponse(SuccessConstants.PET_BREED_SEARCH_SUCCESS.getSuccessCode(),
				SuccessConstants.PET_BREED_SEARCH_SUCCESS.getSuccessMsg(), response);
	}

}
