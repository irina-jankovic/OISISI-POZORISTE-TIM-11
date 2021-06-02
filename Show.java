package pozoriste.show;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Show implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8239670213089303161L;
	private Long id;
	private String description;
	private String name;
	private Date date;
	private boolean sold;
	private float price;
	private Map<Integer, Boolean> seats; // map for occupied seats

	public Show() {
		seats = new HashMap<Integer, Boolean>();
		// initialy all are free
		for (int i = 0; i < 30; ++i)
			seats.put(i, false);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public Map<Integer, Boolean> getSeats() {
		return seats;
	}

	public void setSeats(Map<Integer, Boolean> seats) {
		this.seats = seats;
	}

}
