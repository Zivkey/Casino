package Classes;

import Exceptions.ExchangeException;

/**
 * Exchange sluzi da skladisti procitane podatke sa interneta
 * On sadrzi ime valute, njenu skracenicu kao i vrednost te valute u dinarima
 */
public class Exchange {
    private String name;
    private String code;
    private double exchangeRate;

    public Exchange() {
    }

    public Exchange(String name, String code, double exchangeRate) throws ExchangeException {
        this.name = name;
        this.code = code;
        setExchangeRate(exchangeRate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) throws ExchangeException {
        if (exchangeRate > 0) {
            this.exchangeRate = exchangeRate;
        } else {
            throw new ExchangeException("Exchange rate must be bigger than 0!");
        }
    }

    @Override
    public String toString() {
        return "Exchanges{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
