package com.example.testimageupload.Service;

import com.example.testimageupload.model.Image;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Component
public class ImageUpload implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    private byte[] pic;

    public void saveImage(String name) {
        try {
            pic = Files.readAllBytes(Path.of("src", "main", "resources", "static", name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        entityManager.createNativeQuery("insert into image (is_main, picture, url, name) values (:is_main, :pic, :url, :name)")
                .setParameter("is_main", false)
                .setParameter("pic", pic)
                .setParameter("url", "http")
                .setParameter("name", name)
                .executeUpdate();
    }

    public void getImage(Long id) throws IOException {
        Object[] list = entityManager.createQuery("select i.picture from Image i where i.id = :id")
                .setParameter("id", id)
                .getResultList().toArray();
        pic = (byte[]) list[0];
        Files.write(Path.of("src", "main", "resources", "static", "imageOnDB.jpg"), pic, StandardOpenOption.CREATE);
        entityManager.close();
        System.out.println("Готово");
    }

    public void saveAllImage() {
        File folder = new File("src/main/resources/static");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png") || file.getName().endsWith(".jpeg")) {
                    saveImage(file.getName());
                }
            }
        }
    }

    @Transactional
    @Override
    public void run(String... args) throws IOException {

        saveAllImage();
    }
}
