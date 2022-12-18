package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductData {
    String title;
    Float price;

    public String getTitle() {
        return title;
    }
    public Float getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public ProductData(String title, Float price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductData that = (ProductData) o;
        return Objects.equals(title, that.title) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }
}

