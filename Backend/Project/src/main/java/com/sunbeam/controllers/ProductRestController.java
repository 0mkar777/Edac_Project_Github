package com.sunbeam.controllers;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sunbeam.dtos.ProductsDto;
import com.sunbeam.entities.Products;
import com.sunbeam.model.Response;
import com.sunbeam.services.ProductService;

@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add_product")
    public ResponseEntity<?> addProduct(ProductsDto productsDto) {
        Products products = ProductsDto.toEntity(productsDto);
        products = productService.saveProduct(products, productsDto.getImage1(), productsDto.getImage2(), productsDto.getImage3());
        return Response.success(products);
    }
	
	@GetMapping("/productdetails/{id}")
	public ResponseEntity<?> findProductById(@PathVariable ("id") int id) {
        Products products = productService.findByProductId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
	
	@DeleteMapping("/deleteByPname/{pname}")
	public ResponseEntity<String> deleteProduct(@PathVariable ("pname") String pname) {
        productService.deleteProductsByPname("Samsung 192 L 2 Star Direct Cool Single Door Refrigerator");
        return new ResponseEntity<>("product deleted", HttpStatus.OK);
    }
	
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable ("id") int id) {
        productService.deleteByProductId(id);
        return new ResponseEntity<>("product deleted", HttpStatus.OK);
    }
	
	@GetMapping(path = "/category/{category}")
    public ResponseEntity<List<Products>> findByCategory(@PathVariable ("category") String category) {
		List<Products> products = productService.findByCategory(category);
		return ResponseEntity.ok(products);
    }
	
	
	@GetMapping(path = "/brand/{brand}")
    public ResponseEntity<List<Products>> findByBrand(@PathVariable ("brand") String brand) {
        List<Products> products = productService.findByBrand(brand);
        return new ResponseEntity<>(products, HttpStatus.OK);        
    }
	
	@GetMapping(path = "/orderByPriceAsc")
    public ResponseEntity<List<Products>> orderByAsc() {
        List<Products> products = productService.findByOrderByPriceAsc();
        return new ResponseEntity<>(products, HttpStatus.OK); 
    }
	
	@GetMapping(path = "/orderByPriceDesc")
    public ResponseEntity<List<Products>> orderByDesc() {
        List<Products> products = productService.findByOrderByPriceDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);     
    }
	
	
	@GetMapping(path = "/shop")
    public ResponseEntity<List<Products>> displayAll() {
		List<Products> list = productService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
//	@PostMapping(path = "/searchByName/")
//    public ResponseEntity<List<Products>> searchByName(@RequestBody String) {
//		List<Products> list = productService.findByPnameContainingIgnoreCase();
//		return new ResponseEntity<>(list, HttpStatus.OK);
//    }
	
	@GetMapping("/search")
	public ResponseEntity<?> searchByName(@RequestParam(name="q", required=false) String query) {
		if(query == null)
			query = "";
		List<Products> list = productService.findProductLikeName(query);
		Stream<ProductsDto> result = list.stream().map(product -> ProductsDto.fromEntity(product));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}	
	
	@GetMapping("/distinctCategory")
	public ResponseEntity<List<String>> findDistinctCategory() {
		List<String> list = productService.findDistinctCategory();
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("/distinctBrand")
	public ResponseEntity<List<String>> findDistinctBrand() {
		List<String> list = productService.findDistinctBrand();
		return ResponseEntity.ok(list);
	}
	
//	@PostMapping("/update/{id}")
//    public ResponseEntity<Products> updateProduct(@PathVariable int id, @RequestBody double price) {
//        Products currentProduct  = productService.findByProductId(id);
//        currentProduct.setPrice(price);
//        productService.save;
//
//        return ResponseEntity.ok(currentProduct);
//    }
	
	
//	@PutMapping("/update/{id}")
//    public ResponseEntity<Products> updateProduct(@PathVariable int id, @RequestBody Products product) {
//        Products currentProduct  = productService.findByProductId(id);
//        currentProduct.setPrice(product.getPrice());
//        currentProduct = dao.save(product);
//
//        return ResponseEntity.ok(currentProduct);
//    }
}