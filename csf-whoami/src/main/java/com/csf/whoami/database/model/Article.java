package com.csf.whoami.database.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ARTICLE")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "SUBJECT")
	private String subject;
  
    @Column(name = "CONTENT",length = 100000000)
	private String content;
   

    @Column(name = "REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date regDate;
    @Column(name = "UPD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updDate; 
}
