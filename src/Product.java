import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Product {
 private Integer productCode;
 private String name;
 private Double price;
 private Integer qty;
 private LocalDate date;

 public Product(){};

 public Product(Integer productCode, String name, Double price, Integer qty, LocalDate date){
     this.productCode = productCode;
     this.name = name;
     this.qty = qty;
     this.price = price;
     this.date = date;
 }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productCode, product.productCode) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(qty, product.qty) && Objects.equals(date, product.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, name, price, qty, date);
    }

}
