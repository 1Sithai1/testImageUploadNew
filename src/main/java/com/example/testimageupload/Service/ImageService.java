package com.example.testimageupload.Service;

import com.example.testimageupload.Dao.ImageDao;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    final ImageDao imageDao;

    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public void addImageOnDd() {
        imageDao.addImageOnDd();
    }

}
