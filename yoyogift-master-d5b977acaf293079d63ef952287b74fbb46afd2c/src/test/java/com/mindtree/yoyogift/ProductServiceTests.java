package com.mindtree.yoyogift;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.entity.User;
import com.mindtree.yoyogift.exception.service.ListEmptyException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.ProductNotFoundException;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.service.serviceimpl.ProductServiceImplementation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTests {

	@Autowired
	private ProductServiceImplementation productService;
	
	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private FeedBackRepository feedbackRepository;

	@Test
	public void findAllTest() {
		List<Product> productList = new ArrayList<Product>();
		Mockito.lenient().when(productRepository.findAllByIsDeleted(false)).thenReturn(productList);
		try {
			List<ProductDto> productDtos = this.productService.findAll();
			Assertions.fail();
			System.err.println(productDtos);
		} catch (ServiceException e) {
			Assertions.assertEquals("There are currently no Products in the Database", e.getMessage());
		}
		Product prod = new Product();
		productList.add(prod);
		Mockito.lenient().when(productRepository.findAllByIsDeleted(false)).thenReturn(productList);
		List<FeedBack> feeds = new ArrayList<>();
		Mockito.lenient().when(this.feedbackRepository.findByProduct(prod)).thenReturn(feeds);
		try {
			List<ProductDto> productDtos = this.productService.findAll();
			productDtos.forEach(dto -> {
				Assertions.assertEquals(0, dto.getRatingAvg());
			});
		} catch (ServiceException e) {
			Assertions.fail();
		}
		productList = new ArrayList<>();
		int feedbackCounter = 1;
		for (int i = 1; i < 6; i++) {
			Product product = new Product(i, String.valueOf(i), String.valueOf(i), i, String.valueOf(i), i, i,
					new Date(), String.valueOf(i), String.valueOf(i), false);
			productList.add(product);
			List<FeedBack> feedbacks = new ArrayList<FeedBack>();
			for (int j = 0; j < 2; j++) {
				FeedBack feedBack = new FeedBack(feedbackCounter, feedbackCounter, new Date(),
						String.valueOf(feedbackCounter), null, product, null);
				feedbacks.add(feedBack);
				feedbackCounter++;
			}
			Mockito.lenient().when(feedbackRepository.findByProduct(product)).thenReturn(feedbacks);
		}
		Mockito.lenient().when(productRepository.findAllByIsDeleted(false)).thenReturn(productList);
		try {
			List<ProductDto> products = this.productService.findAll();
			Assertions.assertTrue(products.size() == 5);
			products.forEach(product -> {
				Assertions.assertEquals(1.0, product.getRatingAvg() % 2);
			});
		} catch (ServiceException e) {
			Assertions.assertEquals("There are currently no Products in the Database", e.getMessage());
		}
	}

	@Test
	public void deleteProductTest() {
		Product product = new Product();
		product.setProductId(1);
		product.setDeleted(false);
		Optional<Product> productOptional = Optional.of(product);
		Mockito.lenient().when(productRepository.findById(2l)).thenReturn(productOptional);
		product.setDeleted(false);
		productOptional = Optional.of(product);
		try {
			Assertions.assertEquals(true, this.productService.deleteProduct(2).isDeleted());
		} catch (ServiceException e) {
			Assertions.fail();
		}
		try {
			Mockito.lenient().when(productRepository.findById(1l)).thenReturn(productOptional);
			Product result = this.productService.deleteProduct(1l);
			Assertions.assertEquals(product, result);
			Assertions.fail();
		} catch (ServiceException e) {
			Assertions.assertEquals("Product not found/Deleted.", e.getMessage());
		}
		Mockito.lenient().when(productRepository.findById(3l))
				.thenThrow(new RuntimeException("Product with ID not found"));
		try {
			Product result = this.productService.deleteProduct(3l);
			Assertions.fail();
			System.out.println(result);
		} catch (Exception e) {
			Assertions.assertEquals("Product with ID not found", e.getMessage());
		}

	}
	
	@Test
	public void getDetailsTest() throws ProductNotFoundException
	{				
		long id=23445;
		Product product=new Product(23445, "wert", "werty", 50, "sdfgh", 10, 20, new Date(), "ertyui", "ssrtyuio", false);
		Optional<Product> productOptional = Optional.of(product);
		Mockito.lenient().when(productRepository.findById(id)).thenReturn(productOptional);
	    List<FeedBack>feedback=new ArrayList<FeedBack>();
	    FeedBack feed=new FeedBack(234, 4, new Date(), "dfghj", null, product, null);
		feedback.add(feed);
	    Mockito.lenient().when(feedbackRepository.findAll()).thenReturn(feedback);
			try 
			{
				
		     ProductDto	productdto =productService.getDetails(id);
				System.out.println(productdto);
				System.out.println("Success:");
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try 
			{
		     ProductDto	productdto =productService.getDetails(234568);
				System.out.println(productdto);
				System.out.println("Success:");
			} catch (ServiceException e) {
				Assertions.assertEquals("Product Not Found", e.getMessage());
				System.out.println("Failure");
			}
			
		} 
	
	
}
