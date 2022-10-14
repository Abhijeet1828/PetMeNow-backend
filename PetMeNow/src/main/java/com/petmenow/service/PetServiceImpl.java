package com.petmenow.service;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.petmenow.model.PetInformation;
import com.petmenow.repository.PetBreedMasterRepository;
import com.petmenow.repository.PetInformationRepository;
import com.petmenow.repository.PetTypeMasterRepository;
import com.petmenow.request.RegisterPetRequest;
import com.petmenow.request.UpdatePetRequest;
import com.petmenow.utilities.S3Utils;

@Service
@Transactional
public class PetServiceImpl implements PetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PetServiceImpl.class);

	private static final String LOGGER_NO_PET_ASSOCIATED = "No pet associated with id {}";

	@Autowired
	private PetInformationRepository petInformationRepository;

	@Autowired
	private PetTypeMasterRepository petTypeMasterRepository;

	@Autowired
	PetBreedMasterRepository petBreedMasterRepository;

	@Autowired
	private S3Utils s3Utils;

	@Override
	public Object registerPet(RegisterPetRequest registerPetRequest) {
		try {
			PetInformation pet = new PetInformation();

			BeanUtils.copyProperties(registerPetRequest, pet);
			pet.setCreatedTimestamp(new Date());
			pet.setUpdatedTimestamp(new Date());

			return petInformationRepository.save(pet);
		} catch (Exception e) {
			LOGGER.error("Exception in registerPet", e);
			return 1;
		}
	}

	@Override
	public int deletePet(Long id) {
		try {
			PetInformation pet = petInformationRepository.findFirstById(id);
			if (Objects.isNull(pet)) {
				LOGGER.error(LOGGER_NO_PET_ASSOCIATED, id);
				return 1;
			}

			petInformationRepository.delete(pet);
			return 2;
		} catch (Exception e) {
			LOGGER.error("Exception in deletePet", e);
			return 0;
		}
	}

	@Override
	public Object updatePet(UpdatePetRequest updatePetRequest) {
		try {
			PetInformation petInformation = petInformationRepository.findFirstById(updatePetRequest.getId());
			if (Objects.isNull(petInformation)) {
				LOGGER.error(LOGGER_NO_PET_ASSOCIATED, updatePetRequest.getId());
				return 1;
			}

			BeanUtils.copyProperties(updatePetRequest, petInformation, "id");
			petInformation.setUpdatedTimestamp(new Date());

			return petInformationRepository.save(petInformation);
		} catch (Exception e) {
			LOGGER.error("Exception in updatePet", e);
			return 0;
		}
	}

	@Override
	public Object fetchPetInformation(Long id) {
		try {
			PetInformation pet = petInformationRepository.findFirstById(id);
			if (Objects.isNull(pet)) {
				LOGGER.error(LOGGER_NO_PET_ASSOCIATED, id);
				return 1;
			}

			return pet;
		} catch (Exception e) {
			LOGGER.error("Exception in fetchPetInformation", e);
			return 0;
		}
	}

	@Override
	public Object fetchPetTypes() {
		try {
			return petTypeMasterRepository.findAll();
		} catch (Exception e) {
			LOGGER.error("Exception in fetchPetTypes", e);
			return null;
		}
	}

	@Override
	public Object searchPetBreeds(Long petTypeId, String searchField) {
		try {
			return petBreedMasterRepository.findAllByPetTypeIdAndPetBreedContainingIgnoreCase(petTypeId, searchField,
					PageRequest.of(0, 5));
		} catch (Exception e) {
			LOGGER.error("Exception in searchPetBreeds", e);
			return null;
		}
	}

	@Override
	public Object uploadPetImage(Long petId, MultipartFile multipartFile) {
		try {
			PetInformation petInfo = petInformationRepository.findFirstById(petId);
			if (Objects.isNull(petInfo)) {
				LOGGER.error(LOGGER_NO_PET_ASSOCIATED, petId);
				return 1;
			}

			String imageUrl = s3Utils.uploadFileUsingInputStream(multipartFile.getOriginalFilename(),
					multipartFile.getInputStream());

			petInfo.setImage(imageUrl);
			return petInfo;
		} catch (Exception e) {
			LOGGER.error("Exception in uploadPetImage", e);
			return 0;
		}
	}

}
