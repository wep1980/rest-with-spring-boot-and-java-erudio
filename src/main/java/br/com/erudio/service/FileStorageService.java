package br.com.erudio.service;

import br.com.erudio.config.FileStorageConfig;
import br.com.erudio.exception.FileNotFoundException;
import br.com.erudio.exception.FileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {

        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath()
                .toAbsolutePath().normalize();

        this.fileStorageLocation = path;
        try {
            logger.info("Criando diretórios");
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            logger.error("Não foi possível criar o diretório onde os arquivos serão armazenados!");
            throw new FileStorageException("Não foi possível criar o diretório onde os arquivos serão armazenados! ", e);
        }
    }


    public String storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                logger.error("Desculpe! O nome do arquivo contém uma sequência de caminho inválida" + fileName);
                throw new FileStorageException("Desculpe! O nome do arquivo contém uma sequência de caminho inválida " + fileName);
            }

            logger.info("Salvando o arquivo no disco");

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (Exception e) {
            logger.error("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!");
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {

        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                logger.error("Arquivo não encontrado " + fileName);
                throw new FileNotFoundException("Arquivo não encontrado " + fileName);
            }

        } catch (Exception e) {
            logger.error("Arquivo não encontrado " + fileName);
            throw new FileNotFoundException("Arquivo não encontrado " + fileName, e);
        }
    }
}
