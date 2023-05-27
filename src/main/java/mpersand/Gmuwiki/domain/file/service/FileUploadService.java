package mpersand.Gmuwiki.domain.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.file.exception.FileUploadFailedException;
import mpersand.Gmuwiki.domain.file.exception.InvalidFormatFileException;
import mpersand.Gmuwiki.domain.file.exception.NotAllowedFileException;
import mpersand.Gmuwiki.domain.file.presentation.dto.response.FileUploadResponse;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RollbackService
public class FileUploadService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("file/jpg", "file/png", "file/gif", "file/mp4");

    public List<FileUploadResponse> execute(List<MultipartFile> multipartFile) {

        List<FileUploadResponse> fileNameList = new ArrayList<>();

        multipartFile.forEach(file -> {

            String fileName = createFileName(file.getOriginalFilename());

            String contentType = file.getContentType();
            if (!ALLOWED_MIME_TYPES.contains(contentType)) {
                throw new NotAllowedFileException();
            }

            ObjectMetadata objectMetadata = new ObjectMetadata();

            objectMetadata.setContentLength(file.getSize());

            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {

                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

            } catch(IOException e) {

                throw new FileUploadFailedException();
            }

            String fileUrl = generateFileUrl(fileName);

            FileUploadResponse fileUploadResponse = FileUploadResponse.builder()
                    .awsUrl(fileUrl)
                    .build();

            fileNameList.add(fileUploadResponse);

        });

        return fileNameList;
    }

    public void deleteFile(String fileName) {

        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createFileName(String fileName) {

        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {

        try {

            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {

            throw new InvalidFormatFileException();
        }
    }

    private String generateFileUrl(String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }
}
