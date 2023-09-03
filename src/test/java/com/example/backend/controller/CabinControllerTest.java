package com.example.backend.controller;

import com.example.backend.BackendApplicationTests;
import com.example.backend.model.dto.CabinDTO;
import com.example.backend.model.entity.Cabin;
import com.example.backend.repo.CabinRepo;
import com.example.backend.service.CabinService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

//@Testcontainers
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@Component
class CabinControllerTest extends BackendApplicationTests {
	@MockBean
	CabinRepo cabinRepo;

	@Autowired
	CabinController cabinController;

	@Test
	@DisplayName("Test delete cabin by id, then will receive the success result")
	void testDeleteCabinById_ThenReturnedTheSuccessResult() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

//  tao 1 gia lap
		doNothing().when(cabinRepo).deleteById(eq(1L));

		ResponseEntity<Long> responseEntity = cabinController.deleteCabinById(1L);

		// When
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	@DisplayName("Test delete cabin by id, then will receive the success result")
	void testDeleteCabinById_ThenThrowException() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		//  tao 1 gia lap
		doThrow(IllegalArgumentException.class).when(cabinRepo).deleteById(eq(1L));

		Assertions.assertThrows(NullPointerException.class, () -> cabinController.deleteCabinById(1L));

	}

	@Test
	@DisplayName("Test get all cabin then return success result")
	void testGetAllCabins_ThenReturnedTheSuccessResult(){
//		// GIVEN
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//		List<Cabin> mockCabinList = new ArrayList<>();
//		mockCabinList.add(new Cabin());
//		mockCabinList.add(new Cabin());
//
//		when(cabinRepo.getAll()).thenReturn(mockCabinList.stream());
//
//		// WHEN
//		ResponseEntity<List<CabinDTO>> resp = cabinController.getAllCabin();
//		// THEN
//		Assertions.assertEquals(resp.getStatusCode(), HttpStatus.OK);
//		Assertions.assertNotNull(resp.getBody());
//		List<CabinDTO> body = resp.getBody();
//		List<CabinDTO> expectedBody = new ArrayList<>();
//		expectedBody.add(new CabinDTO());
//		expectedBody.add(new CabinDTO());
//		Assertions.assertEquals(expectedBody, body);
//		verify(cabinRepo, times(1)).getAll();
	}
}