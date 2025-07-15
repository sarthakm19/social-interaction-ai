package io.learning.socialinteractionai.utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.List;

public class ImageCreationUtil {

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images";

    public static void saveImageToFile(String imageData,String imageUrl){

        @JsonIgnoreProperties(ignoreUnknown = true)
        record ImageResponse(List<String> images){}
        ObjectMapper objectMapper = new ObjectMapper();
        ImageResponse imageResponse = null;
        try {
            imageResponse = objectMapper.readValue(imageData, ImageResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(imageResponse !=null && !imageResponse.images().isEmpty()){
            String base64Image = imageResponse.images().getFirst();
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            Path directoryPath = Paths.get(IMAGE_DIRECTORY);
            Path filePath = Paths.get(IMAGE_DIRECTORY + "/" + imageUrl);
            if(!Files.isDirectory(directoryPath)){
                try {
                    Files.createDirectory(directoryPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Files.write(
                        filePath,
                        imageBytes,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.TRUNCATE_EXISTING
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
