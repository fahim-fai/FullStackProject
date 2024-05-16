package com.bytestrone.oops.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "allocation_history")
public class AllocationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    private RecognitionActivity recognitionActivity;

    
    @Column(name = "allocation_date")
    private LocalDate allocationDate;
    
    @Column(name = "created_date")
    private LocalDate createdDate;
	
	@Column(name="created_user")
	private String createdUser;
	
	public void creationDate() {
		this.createdDate=LocalDate.now();
	}
    
	public AllocationHistory() {
		super();
		this.allocationDate=LocalDate.now();
		
	}

	public AllocationHistory(User user, RecognitionActivity recognitionActivity) {
		super();
		this.user = user;
		this.recognitionActivity = recognitionActivity;
		this.allocationDate=LocalDate.now();
	}
	
  
}
