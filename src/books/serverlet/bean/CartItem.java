package books.serverlet.bean;

import javax.persistence.criteria.CriteriaBuilder;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;

/**
 * ClassName:CartItem
 * Package:books.serverlet.bean
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1122:57
 * Version 1.0
 */
public class CartItem {
    private String imgPath;
    private String title;
    private Integer count;
    private Double price;
    private Double totalPrice;
    private Integer bookId;

    public CartItem() {
    }

    public CartItem(String imgPath, String title, Integer count, Double price, Double totalPrice, Integer bookId) {
        this.imgPath = imgPath;
        this.title = title;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.bookId = bookId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        BigDecimal bigDecimalPrice=new BigDecimal(getPrice()+"");
        BigDecimal bigDecimalCount=new BigDecimal(getCount()+"");
        totalPrice=bigDecimalPrice.multiply(bigDecimalCount).doubleValue();
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
