package com.tacticalwolves.postservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private int UserId;
    private int LikeId;
    private String Title;
    private String Description;
}
