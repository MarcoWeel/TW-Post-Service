package com.tacticalwolves.postservice.controller;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import io.minio.messages.Item;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class PostFileController {

        @Autowired
        private MinioService minioService;
        @RolesAllowed({"ADMIN", "MEMBER"})
        @GetMapping("/files")
        public List<Item> testMinio() throws MinioException {
                return minioService.list();
        }

        @GetMapping("/files/{object}")
        public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws MinioException, IOException {
                InputStream inputStream = minioService.get(Path.of(object));
                InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

                // Set the content type and attachment header.
                response.addHeader("Content-disposition", "attachment;filename=" + object);
                response.setContentType(URLConnection.guessContentTypeFromName(object));

                // Copy the stream to the response's output stream.
                IOUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
        }
        @RolesAllowed({"ADMIN", "MEMBER"})
        @PostMapping("/files")
        public void addAttachement(@RequestParam("file") MultipartFile file) {
                Path path = Path.of(file.getOriginalFilename());
                try {
                        minioService.upload(path, file.getInputStream(), file.getContentType());
                } catch (MinioException e) {
                        throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
                } catch (IOException e) {
                        throw new IllegalStateException("The file cannot be read", e);
                }
        }
}
