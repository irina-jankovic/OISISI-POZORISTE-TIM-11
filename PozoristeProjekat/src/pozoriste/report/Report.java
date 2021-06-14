package pozoriste.report;

import java.util.LinkedList;
import java.util.List;

public class Report {
	// kljucevi u izvestaju (id predstave za ceo izvestaj ili id karte za izvestaj
	// za predstavu)
	private List<Long> ids = new LinkedList<Long>();
	private List<Float> prices = new LinkedList<Float>();

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public List<Float> getPrices() {
		return prices;
	}

	public void setPrices(List<Float> prices) {
		this.prices = prices;
	}

}
