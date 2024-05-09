package com.shaka.recommendation.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.shaka.recommendation.data.entity.VideoEntity;
import com.shaka.recommendation.data.repository.VideoRepository;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {

	@Autowired
	private VideoRepository videoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int page = 0;
		int size = 100; // adjust the size as needed

		Page<VideoEntity> videoPage;
		do {
			videoPage = videoRepository.findAll(PageRequest.of(page, size));
			List<VideoEntity> videos = videoPage.getContent();

			for (VideoEntity video : videos) {
				System.out.println(video);
			}

			page++;
		} while (videoPage.hasNext());
	}

}
