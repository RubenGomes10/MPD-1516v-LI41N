package transactions;

public class Transaction{

	private Trader trader;
	private int year;
	private int value;
	private Currency currency;


	private Transaction(Trader trader, int year, int value, Currency currency)
	{
		this.trader = trader;
		this.year = year;
		this.value = value;
		this.currency = currency;
	}

	public Transaction(Trader trader, int year, int value)
	{
		this(trader, year, value, Currency.EUR);
	}

	public Transaction(int year, int value, Currency currency)
	{
		this(null, year, value, currency);
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