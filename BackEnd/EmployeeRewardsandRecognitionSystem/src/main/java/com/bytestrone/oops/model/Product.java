package com.bytestrone.oops.model;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@DynamicUpdate
@Table(name = "product_table")

public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long points;
	private String category;
	private Long quantity;
	private String termsAndCondition;
	private Date expiryDate; 
	
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

	@Column(name="image_id",insertable=false,updatable=false)
	private Long image_id;
	
	private boolean isAvailable;
	
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
