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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "redeem_history")
public class RedeemHistory {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "product_id")
	    private Product product;

	    
	    @Column(name = "redeemed_date")
	    private LocalDate redeemedDate;
	    
	    @Column(name = "created_date")
	    private LocalDate createdDate;
		
		@Column(name="created_user")
		private String createdUser;
		
		public void creationDate() {
			this.createdDate=LocalDate.now();
		}
	    
	    public RedeemHistory(User user, Product product) {
			super();
			this.user = user;
			this.product = product;
			this.redeemedDate=LocalDate.now();
		}

}
