package com.learning.journelApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="journel_entries")
@Data
@NoArgsConstructor
public class JournelEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;



}
