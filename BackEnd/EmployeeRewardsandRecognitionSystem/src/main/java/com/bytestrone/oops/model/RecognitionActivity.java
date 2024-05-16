package com.bytestrone.oops.model;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recognition_activity")
public class RecognitionActivity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String name ;
	
	private Long points;
	
	@Column(name = "created_date")
    private LocalDate createdDate;
	
	@Column(name="created_user")
	private String createdUser;
	
	@Column(name = "modified_date")
    private LocalDate modifiedDate;
	
	@Column(name="modified_user")
	private String modifiedUser;
	
	public void creationDate() {
		this.createdDate=LocalDate.now();
	}
	public void modifiedDate() {
		this.modifiedDate=LocalDate.now();
	}
	
}
