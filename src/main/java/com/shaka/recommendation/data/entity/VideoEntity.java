package com.shaka.recommendation.data.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "videos")
public class VideoEntity {

    @Id
    private String id;

    private String url;

    private VideoType type;

    private String title;

    private String thumbnailUrl;

    private Integer year;

    private String region;

    private String genre;

    private String language;

    private Date time;

    private List<String> actors;

    private List<String> directors;

    private Double score;

    private String description;
}

enum VideoType {
    movie, drama
}
