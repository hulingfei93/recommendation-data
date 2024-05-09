package com.shaka.recommendation.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

import com.shaka.recommendation.data.models.VideoDocument;
import com.shaka.recommendation.data.models.VideoEntity;
import com.shaka.recommendation.data.repository.VideoRepository;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// mapping();
		indexing();
	}

	private void mapping() {
		IndexOperations videoIndex = elasticsearchOperations.indexOps(VideoDocument.class);
		if (!videoIndex.exists()) {
			videoIndex.createWithMapping();
		}

	}

	private void indexing() {

		int page = 0;
		int size = 1000; // adjust the size as needed

		Page<VideoEntity> videoPage;
		do {
			videoPage = videoRepository.findAll(PageRequest.of(page, size));
			List<VideoEntity> videos = videoPage.getContent();

			List<VideoDocument> videoDocuments = new ArrayList<VideoDocument>();
			for (VideoEntity video : videos) {
				videoDocuments.add(VideoDocument.builder()
						.id(video.getId())
						.url(video.getUrl())
						.type(video.getType())
						.title(video.getTitle())
						.thumbnailUrl(video.getThumbnailUrl())
						.year(video.getYear())
						.region(video.getRegion())
						.genre(video.getGenre())
						.language(video.getLanguage())
						.time(video.getTime())
						.actors(video.getActors())
						.directors(video.getDirectors())
						.score(video.getScore())
						.description(video.getDescription())
						.build());
			}

			elasticsearchOperations.save(videoDocuments);

			page++;
		} while (videoPage.hasNext());

	}

}
