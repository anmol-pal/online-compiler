package com.knf.dev.demo.springbootdatajpagcpsqlmysqlcrud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.util.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Value("${spring.cloud.gcp.project-id}")
    private String gcpProjectId = "maps-218214";

    @Value("${gcp.bucket.name}")
    private String gcpBucketId = "cloudcompileruserstorage";

    @Value("${gcp.service.account.key.path}")
    private String gcpConfigFile;// = ".\\src\\main\\resources\\maps.json";

    private DataBucketUtil dataBucketUtil = new DataBucketUtil();

    @Autowired
    private ResourceLoader resourceLoader;

    // Resource resource = resourceLoader.getResource("classpath:maps.json");

    // public void loadJsonFile() throws IOException {
    // Resource resource = resourceLoader.getResource("classpath:maps.json");
    // InputStream inputStream = resource.getInputStream();

    // // Use the input stream to read the JSON file...
    // }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {

        return ResponseEntity.ok().body("Ok Message");
    }

    // get all user
    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @PostMapping("/users")
    public PostInputEntity createUser(@RequestBody PostInputEntity inputEntity) {
        System.out.println("\n\n" + inputEntity + "\n\n");
        System.out.println("\n\n" + inputEntity.extension + "\n\n");
        System.out.println(inputEntity.code + "\n\n");
        System.out.println(inputEntity.language + "\n\n");
        System.out.println(inputEntity.userId + "\n\n");
        String fileName = "";
        try {
            fileName = dataBucketUtil.uploadFile(inputEntity.code, inputEntity.extension);

        } catch (Exception e) {
            System.out.println(e);
        }
        User user = new User(fileName, inputEntity.userId,
                inputEntity.language,
                inputEntity.extension);
        user = userRepository.save(user);
        return inputEntity;
    }

    // get user by id rest api
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<User>> getUserById(@PathVariable String userId) {
        List<User> user = userRepository.findByUserId(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/file/{fileName}")
    public ResponseEntity<String> getFileContent(@PathVariable String fileName) {
        String fileDatta = dataBucketUtil.getFileData(fileName);

        return ResponseEntity.ok(fileDatta);
    }

    // update user rest api
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
            @RequestBody User userDetails) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        // user.setFirstName(userDetails.getFirstName());
        // user.setLastName(userDetails.getLastName());
        // user.setEmail(userDetails.getEmail());
        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }

    // delete user rest api
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
