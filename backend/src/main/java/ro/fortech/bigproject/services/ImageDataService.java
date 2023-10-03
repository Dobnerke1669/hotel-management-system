package ro.fortech.bigproject.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.bigproject.entities.ImageData;
import ro.fortech.bigproject.repository.ImageDataRepository;


@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;

    @Transactional
    public ImageData getImage(Long id) {
        return imageDataRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}

