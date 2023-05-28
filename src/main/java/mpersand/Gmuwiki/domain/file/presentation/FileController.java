package mpersand.Gmuwiki.domain.file.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.file.presentation.dto.response.FileUploadResponse;
import mpersand.Gmuwiki.domain.file.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<List<FileUploadResponse>> upload(@RequestPart(name = "file", required = false) List<MultipartFile> files) {
        List<FileUploadResponse> fileUploadResponse = fileUploadService.execute(files);
        return new ResponseEntity<>(fileUploadResponse, HttpStatus.CREATED);
    }
}