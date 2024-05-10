package com.shaka.recommendation.data.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "videos")
public class VideoDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword, docValues = false, index = false)
    private String url;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Keyword, docValues = false, index = false)
    private String thumbnailUrl;

    @Field(type = FieldType.Integer)
    private Integer year;

    @Field(type = FieldType.Keyword)
    private String region;

    @Field(type = FieldType.Keyword)
    private String genre;

    @Field(type = FieldType.Keyword)
    private String language;

    @Field(type = FieldType.Date)
    private Date time;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private List<String> actors;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private List<String> directors;

    @Field(type = FieldType.Double)
    private Double score;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;

}
