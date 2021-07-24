package com.csf.whoami.database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;
@Entity
@Table(name = "UPLOAD_FILE")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {
    
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "FILE_NAME")
	private String fileName;
	@Column(name = "SAVE_FILE_NAME")
	private String saveFileName;
    @Column(name = "FILE_PATH")
	private String filePath;

    @Column(name = "CONTENT_TYPE")
	private String contentType;
 
    @Column(name = "SIZE")
	private long size;
 
    @Column(name = "REG_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date regDate;
}
