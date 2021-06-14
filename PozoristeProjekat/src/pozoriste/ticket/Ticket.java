package pozoriste.ticket;

import java.io.Serializable;

import pozoriste.show.Show;
import pozoriste.user.User;

public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1557749335552983458L;
	private Long id;
	private User issuedTo;
	private float price;
	private int row;
	private Show show;
	private int column;

	public Ticket() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public User getIssuedTo() {
		return issuedTo;
	}

	public void setIssuedTo(User owner) {
		this.issuedTo = owner;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
