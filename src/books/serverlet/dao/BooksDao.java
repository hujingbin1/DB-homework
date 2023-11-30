package books.serverlet.dao;

import books.serverlet.bean.Books;
import books.serverlet.bean.CartItem;

import java.util.Collection;
import java.util.List;

/**
 * ClassName:BooksDao
 * Package:tianjiao.serverlet.dao
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-079:52
 * Version 1.0
 */
public interface BooksDao {
    public List<Books> selectAll(double minPrice, double maxPrice, int pageIndex, int pageSize);
    public boolean insert(Books books);
    public Books getById(String bid);
    public boolean updateBook(Books books);
    public boolean deleteById(String bid);
    public int total(double minPrice,double maxPrice);
    public int[] updateStockSales(Collection<CartItem> list);
}
