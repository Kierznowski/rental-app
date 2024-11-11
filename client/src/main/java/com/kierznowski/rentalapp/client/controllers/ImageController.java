package com.kierznowski.rentalapp.client.controllers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    RestTemplate restTemplate;

    public ImageController() {
        restTemplate = new RestTemplate();
    }

    @GetMapping(path = "/{imageId}")
    HttpEntity<byte[]> downloadImage(@PathVariable Long imageId) throws Exception {
        ResponseEntity<byte[]> response = restTemplate.getForEntity(
                "http://localhost:8080/file-system/image/" + imageId, byte[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new HttpEntity<>(response.getBody(), headers);
    }

    @PostMapping
    ResponseEntity<List<Long>> uploadImage(@RequestParam("multipartImage")List<MultipartFile> images,
                                           @RequestParam("offerId") Long offerId) throws Exception {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        for(MultipartFile image : images) {
            body.add("multipartImage", new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            });
        }
        body.add("offerId", offerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        System.out.println(requestEntity);
        ResponseEntity<List<Long>> response = restTemplate.exchange(
                "http://localhost:8080/file-system/upload-images",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Long>>() {}
        );
        return response;
    }

}
