package books.serverlet.bean;

import java.awt.*;

/**
 * ClassName:Books
 * Package:tianjiao.serverlet.bean
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-0710:00
 * Version 1.0
 */
public class Books {
    private Integer id;//图书id
    private String title;//图书名字
    private String author;//书的作者
    private double price;//书的价格

    private Integer sales;//书的销售量
    private Integer stock;//书的库存
    private String imgPath;//封面路径
    public Books() {
    }

    public Books(Integer id, String title, String author, double price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
