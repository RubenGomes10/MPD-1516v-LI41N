package transactions;

public class Transaction{

	private Trader trader;
	private int year;
	private int value;
	private Currency currency;

	public Transaction(Trader trader, int year, int value)
	{
		this.trader = trader;
		this.year = year;
		this.value = value;
		this.currency = Currency.EUR;
	}

	public Transaction(Currency currency, int value)
	{
		this.currency = currency;
		this.value = value;
	}

	public Trader getTrader(){ 
		return this.trader;
	}

	public int getYear(){
		return this.year;
	}

	public int getValue(){
		return this.value;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String toString(){
	    return "{" + this.trader + ", " +
	           "year: "+this.year+", " +
	           "value:" + this.value +"}";
	}

	public enum Currency {
		EUR, USD, JPY, GBP, CHF
	}
}