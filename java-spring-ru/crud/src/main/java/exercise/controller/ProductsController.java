package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public List<ProductDTO> index() {
        return productRepository.findAll().stream()
                .map(productMapper::map)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Product with id %s not found", id)
                ));

        return productMapper.map(product);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO data) {
        var product = productMapper.map(data);
        productRepository.save(product);

        return productMapper.map(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable long id, @Valid @RequestBody ProductUpdateDTO data) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Product with id %s not found", id)
                ));

        productMapper.update(data, product);
        productRepository.save(product);

        return productMapper.map(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Product with id %s not found.", id)
                ));

        productRepository.delete(product);
    }
    // END
}
