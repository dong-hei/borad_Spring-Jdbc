package com.sample.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostListDto {

   private int no;
   private String title;
   private String userName;
   private int readCount;
   private int commentCount;
   private Date createdDate;
 
}