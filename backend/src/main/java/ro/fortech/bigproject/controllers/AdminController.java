package ro.fortech.bigproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.fortech.bigproject.entities.Product;
import ro.fortech.bigproject.entities.Role;
import ro.fortech.bigproject.entities.User;
import ro.fortech.bigproject.payload.request.AddProductRequest;
import ro.fortech.bigproject.payload.request.AddUserRequest;
import ro.fortech.bigproject.payload.request.UpdateProductRequest;
import ro.fortech.bigproject.payload.request.UpdateUserRequest;
import ro.fortech.bigproject.payload.response.MessageResponse;
import ro.fortech.bigproject.payload.response.ProductResponse;
import ro.fortech.bigproject.services.GenericService;
import ro.fortech.bigproject.services.ProductService;
import ro.fortech.bigproject.services.UserService;
import ro.fortech.bigproject.services.validators.exceptions.BlankException;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Add a new user", description = "Adds a new user in the admin page")
    @PostMapping("/addUser")
    public ResponseEntity<MessageResponse> addUser(@RequestBody AddUserRequest addUserRequest) {
        try {
        GenericService<AddUserRequest> genericService = new GenericService<>();
        genericService.checkIfBlank(addUserRequest);
        List<String> strRoles = addUserRequest.getRole();
        User user = new User(addUserRequest.getUsername(),
                addUserRequest.getEmail(),
                encoder.encode(addUserRequest.getPassword()), addUserRequest.getFirstName(), addUserRequest.getLastName(), true);
        List<Role> roles = userService.createRoles(strRoles);
        user.setRoles(roles);
        userService.createUser(user);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }catch (BlankException e) {
            return ResponseEntity.status(469).body(new MessageResponse(e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

    }

    @Operation(summary = "Update a user", description = "Updates a user in the admin page")
    @PutMapping("/updateUser")
    public ResponseEntity<MessageResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        GenericService<UpdateUserRequest> genericService = new GenericService<>();
        try {
            genericService.checkIfBlank(updateUserRequest);
        } catch (BlankException e) {
            return ResponseEntity.status(469).body(new MessageResponse(e.getMessage()));
        }
        String email = updateUserRequest.getEmail();
        userService.checkEmail(email);

        String username = updateUserRequest.getUsername();
        userService.checkUsername(username);

        User updateUser = userService.getUserById(updateUserRequest.getId());
        updateUser.setUsername(updateUserRequest.getUsername());
        updateUser.setEmail(updateUserRequest.getEmail());
        updateUser.setFirstName(updateUserRequest.getFirstName());
        updateUser.setLastName(updateUserRequest.getLastName());
        List<Role> roles = updateUser.getRoles();
        updateUser.setRoles(roles);
        userService.updateUser(updateUser);
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @Operation(summary = "Disable a user", description = "Disables a user in the admin page")
    @PutMapping("/disableUser/{id}")
    public ResponseEntity<MessageResponse> disableOrEnableUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        user.setEnabled(!user.getEnabled());
        userService.updateUser(user);
        return ResponseEntity.ok(new MessageResponse("User disabled successfully! " + id));
    }

    @Operation(summary = "Find all users", description = "Finds all users in the admin page")
    @GetMapping("/findAll")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @Operation(summary = "Add a product", description = "Adds a product in the admin page")
    @PostMapping("/addProduct")
    public ResponseEntity<MessageResponse> addProduct(@Valid @RequestBody AddProductRequest addProductRequest) {
        GenericService<AddProductRequest> genericService = new GenericService<>();
        try {
            genericService.checkIfBlank(addProductRequest);
        } catch (BlankException e) {
            return ResponseEntity.status(469).body(new MessageResponse(e.getMessage()));
        }
        Product product = new Product();
        product.setName(addProductRequest.getName());
        product.setCategory(addProductRequest.getCategory());
        product.setImage(addProductRequest.getImage());
        product.setPrice(addProductRequest.getPrice());
        product.setDescription(addProductRequest.getDescription());
        product.setAvailable(true);
        productService.addProduct(product);
        return ResponseEntity.ok(new MessageResponse("Product added successfully"));
    }

    @Operation(summary = "Update a product", description = "Updates a product in the admin page")
    @PutMapping("/updateProduct")
    public ResponseEntity<MessageResponse> updateProduct(@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        GenericService<UpdateProductRequest> genericService = new GenericService<>();
        try {
            genericService.checkIfBlank(updateProductRequest);
        } catch (BlankException e) {
            return ResponseEntity.status(469).body(new MessageResponse(e.getMessage()));
        }
        Product updatedProduct = productService.getProductById((long) updateProductRequest.getId());
        updatedProduct.setName(updateProductRequest.getName());
        updatedProduct.setCategory(updateProductRequest.getCategory());
        //updatedProduct.setImage(updateProductRequest.getImage());
        updatedProduct.setPrice(updateProductRequest.getPrice());
        updatedProduct.setDescription(updateProductRequest.getDescription());
        updatedProduct.setAvailable(updateProductRequest.isAvailable());
        productService.updateProduct(updatedProduct);
        return ResponseEntity.ok(new MessageResponse("Product updated successfully"));

    }

    @Operation(summary = "Delete a product", description = "Deletes a product in the admin page")
    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Find all products", description = "Finds all products in the admin page")
    @GetMapping("/getProducts")
    public List<ProductResponse> getProducts() {
        List<Product> products = productService.findAllProducts();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getDescription(), product.isAvailable());
            productResponses.add(productResponse);
        }
        return productResponses;
    }
}
