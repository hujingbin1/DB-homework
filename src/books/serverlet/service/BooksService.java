package books.serverlet.service;

import books.serverlet.bean.Books;
import books.serverlet.utils.PageUtil;

import java.util.List;

/**
 * ClassName:BooksService
 * Package:tianjiao.serverlet.service.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-079:51
 * Version 1.0
 */
public interface BooksService {
    public boolean insert(Books books);
    public Books getById(String bid);
    public boolean updateBook(Books books);
    public boolean deleteById(String bid);
    public int total(double minPricem,double maxPrice);
    public PageUtil<Books> getBypageUtil(int pIndex,double minPrice,double maxPrice);
    public List<Books> selectAll(double minPrice,double maxPrice,int pageIndex,int pageSize);

}
