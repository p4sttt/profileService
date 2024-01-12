package net.d4y2k.profileservice.utils.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    public String store(MultipartFile file) throws IOException {
        Path path = Path.of(uploadDir);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null) {
            throw new FileNameIsEmptyException();
        }

        String key = createKey(fileName);
        String fileExtension = getExtensionFromFileName(fileName);
        String finalFileName = key + fileExtension;

        Path filePath = path.resolve(finalFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return finalFileName;
    }

    private String createKey(String fileName) {
        return DigestUtils.md5DigestAsHex(fileName.getBytes());
    }

    public String getExtensionFromFileName(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }

        return "";
    }
}
