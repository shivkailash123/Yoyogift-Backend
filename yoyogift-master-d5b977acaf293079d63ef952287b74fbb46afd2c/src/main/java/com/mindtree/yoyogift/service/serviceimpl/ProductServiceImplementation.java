package com.mindtree.yoyogift.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.yoyogift.dto.ProductDto;
import com.mindtree.yoyogift.entity.FeedBack;
import com.mindtree.yoyogift.entity.Product;
import com.mindtree.yoyogift.exception.service.EntityNotFoundException;
import com.mindtree.yoyogift.exception.service.InvalidValueException;
import com.mindtree.yoyogift.exception.service.ListEmptyException;
import com.mindtree.yoyogift.exception.service.ProductAlreadyExistsException;
import com.mindtree.yoyogift.exception.service.ServiceException;
import com.mindtree.yoyogift.exception.service.custom.ProductNotFoundException;
import com.mindtree.yoyogift.repository.FeedBackRepository;
import com.mindtree.yoyogift.repository.ProductRepository;
import com.mindtree.yoyogift.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private FeedBackRepository feedbackRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Product addProduct(Product product) throws ServiceException {
		List<Product> products = this.productRepository.findByProductNameAndDescriptionAndImageUrl(
				product.getProductName(), product.getDescription(), product.getImageUrl());
		
		String img=product.getImageUrl();
		if(img.length()>=5)
		{
		String check=img.substring(img.length()-4,img.length());
		if(check.equalsIgnoreCase(".jpg") || check.equalsIgnoreCase(".png"))
		{
		}
		else
		{
			throw new InvalidValueException("Image can be only in .jpg/.png Format");	
		}
		}
		else
		{
			throw new InvalidValueException("Invalid ImageURL");
		}
		if (products.size() != 0) {
			throw new ProductAlreadyExistsException(
					"Product Name, Description and URL are already mapped to a product in the System");
		} else {
			if (product.getPrice() <= 0) {
				throw new InvalidValueException("Price must be 1 or More");
			} else if (product.getDiscount() > 91) {
				throw new InvalidValueException("Discount Percent can not be more than 90");
			} else if (product.getDiscount() <= 0) {
				throw new InvalidValueException("Discount Percent can not be less than 1");
			}  else if (product.getStock() <= 0) {
				throw new InvalidValueException("Stock Cannot Be Zero or negative");
			} else {
				return this.productRepository.save(product);
			}
		}
	}

	@Override
	public List<ProductDto> findAll() throws ServiceException {
		List<Product> products = this.productRepository.findAllByIsDeleted(false);
		if (products.size() == 0) {
			throw new ListEmptyException("There are currently no Products in the Database");
		} else {
			List<ProductDto> dtos = new ArrayList<ProductDto>();
			products.forEach(product -> {
				ProductDto dto = new ProductDto();
				dto = modelMapper.map(product, ProductDto.class);
				List<FeedBack> feedbacks = feedbackRepository.findByProduct(product);
				if (feedbacks.size() == 0) {
					dto.setRatingAvg(0);
				} else {
					int ratingCount = feedbacks.size();
					int ratingTotal = 0;
					for (FeedBack feedback : feedbacks) {
						ratingTotal = ratingTotal + feedback.getRatingPoint();
					}
					dto.setRatingAvg(ratingTotal / ratingCount);
				}
				dtos.add(dto);
			});
			return dtos;
		}
	}

	@Override
	public ProductDto getDetails(long productId) throws ServiceException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
		if (product.isDeleted()) {
			throw new ProductNotFoundException("Product Not Found.");
		}
		int ratingPoint = 0;
		int count = 0;
		// ratingPoint =
		// feedbackRepository.findByProduct(product).stream().mapToInt(FeedBack::getRatingPoint).average().getAsDouble();
		List<FeedBack> feed = feedbackRepository.findAll();
		for (FeedBack f : feed) {
			if (f.getProduct() == product) {
				ratingPoint += f.getRatingPoint();
				count++;
			}
		}
		double avg = 0;
		if (count != 0) {
			avg = ratingPoint / count;
		}

		ProductDto productDto = new ProductDto();
		productDto = modelMapper.map(product, ProductDto.class);
		productDto.setRatingAvg(avg);
		System.out.println(productDto);
		return productDto;
	}

	@Override
	public Product updateProduct(Product product) throws ServiceException {
		// TODO Auto-generated method stub
		
		Product temp=productRepository.findById(product.getProductId()).orElseThrow(()->new ProductNotFoundException("Product Not Found"));
		String img=product.getImageUrl();
		if(img.length()>=5)
		{
		String check=img.substring(img.length()-4,img.length());
		if(check.equalsIgnoreCase(".jpg") || check.equalsIgnoreCase(".png"))
		{
		}
		else
		{
			throw new InvalidValueException("Image can be only in .jpg/.png Format");	
		}
		}
		else
		{
			throw new InvalidValueException("Invalid ImageURL");
		}
		String productName = temp.getProductName();
		for (int i = 0; i < productName.length(); i++) {
			if ((productName.charAt(i) >= 65 && productName.charAt(i) <= 90)
					|| (productName.charAt(i) >= 97 && productName.charAt(i) <= 122) || (productName.charAt(i) == 32)) {

			} else {
				throw new InvalidValueException("PRODUCT NAME MUST ONLY CONTAINS ALPHABETS");
			}
		}
		if (product.getPrice() < 0) {
			throw new InvalidValueException("Price cannot be negative");
		}
		String category = product.getCategory();
		for (int i = 0; i < category.length(); i++) {
			if ((category.charAt(i) >= 65 && category.charAt(i) <= 90)
					|| (category.charAt(i) >= 97 && category.charAt(i) <= 122) || (category.charAt(i) == 32)) {

			} else {
				throw new InvalidValueException("CATEGORY MUST ONLY CONTAINS ALPHABETS");
			}
		}
		String description = product.getDescription();
		for (int i = 0; i < description.length(); i++) {
			if ((description.charAt(i) >= 65 && description.charAt(i) <= 90)
					|| (description.charAt(i) >= 97 && description.charAt(i) <= 122) || (description.charAt(i) == 32)) {

			} else {
				throw new InvalidValueException("DESCRIPTION MUST ONLY CONTAINS ALPHABETS");
			}
		}
		if (product.getDiscount() < 0 || product.getDiscount() > 91) {
			throw new InvalidValueException("Discount cannot  more than 90%");
		}
		if (product.getStock() < 0) {
			throw new InvalidValueException("stock cannot be negative");
		}
		String vendor = product.getVendor();
		for (int i = 0; i < vendor.length(); i++) {
			if ((vendor.charAt(i) >= 65 && vendor.charAt(i) <= 90)
					|| (vendor.charAt(i) >= 97 && vendor.charAt(i) <= 122) || (vendor.charAt(i) == 32)) {

			} else {
				throw new InvalidValueException("VENDOR MUST ONLY CONTAINS ALPHABETS");
			}
		}

		return productRepository.save(product);
	}

	public Product deleteProduct(long productId) throws ServiceException {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product with ID not found"));
		if (product.isDeleted()) {
			throw new EntityNotFoundException("Product not found/Deleted.");
		} else {
			product.setDeleted(true);
			this.productRepository.save(product);
			return product;
		}
	}
}
