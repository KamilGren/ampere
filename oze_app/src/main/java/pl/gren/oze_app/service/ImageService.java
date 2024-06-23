package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gren.oze_app.model.Image;
import pl.gren.oze_app.oldrepository.ImageRepository;


@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public void saveImage(String name, byte[] image) {
        Image imageEntity = new Image();
        imageEntity.setName(name);
        imageEntity.setImage(image);
        imageRepository.save(imageEntity);
    }
}
