package kursach.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private UserForAuth user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Product product;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    public UserForAuth getUser() {
        return user;
    }

    public void setUser(UserForAuth user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
	public String toString() {
		return "Review  - " + id + ": [user=" + user + ", product=" + product +
				", rating=" + rating + ", commemt=" + comment + ", date=" + date + "]";
	}
}
