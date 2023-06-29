package mpersand.Gmuwiki.domain.file.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.file.presentation.dto.response.FileUploadResponse;
import mpersand.Gmuwiki.domain.file.service.FileUploadService;
import mpersand.Gmuwiki.global.annotation.RestRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestRequestService("/file")
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileUploadResponse> upload(@RequestPart(name = "file", required = false) MultipartFile files) {
        FileUploadResponse fileUploadResponse = fileUploadService.execute(files);
        return new ResponseEntity<>(fileUploadResponse, HttpStatus.CREATED);
    }
}