package com.mindtree.yoyogift;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.google.common.reflect.TypeToken;
import com.mindtree.yoyogift.controller.YoYoGift;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.dto.ResponseBody;
import com.mindtree.yoyogift.exception.AppException;
import com.mindtree.yoyogift.service.serviceimpl.ProductServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductControllerTests 
{

	@Autowired
	private YoYoGift controller;

	@Autowired
	private ModelMapper modelMapper;
	
	@MockBean
	private ProductServiceImplementation productService;

	@SuppressWarnings("serial")
	@Test
	public void displayTest() throws AppException {
		ProductDto products = new ProductDto();
		long id=100;
		Mockito.lenient().when(productService.getDetails(id)).thenReturn(products);
		ResponseBody<ProductDto> responseBody = new ResponseBody<ProductDto>(null, true,
				modelMapper.map(productService.getDetails(id), new TypeToken<ProductDto>() {
				}.getType()));
		ResponseEntity<ResponseBody<ProductDto>> expected = new ResponseEntity<ResponseBody<ProductDto>>(
				responseBody, HttpStatus.OK);
		ResponseEntity<?> actual = this.controller.getProductDetail(id);
		Assertions.assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
	}	
	
	
	
}

