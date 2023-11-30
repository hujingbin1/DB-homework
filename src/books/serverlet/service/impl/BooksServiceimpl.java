package books.serverlet.service.impl;

import books.serverlet.bean.Books;
import books.serverlet.dao.BooksDao;
import books.serverlet.dao.impl.BooksDaoimpl;
import books.serverlet.service.BooksService;
import books.serverlet.utils.PageUtil;

import java.util.List;

/**
 * ClassName:BooksServiceimpl
 * Package:tianjiao.serverlet.service.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-079:51
 * Version 1.0
 */
public class BooksServiceimpl implements BooksService {
    BooksDao booksDao=  new BooksDaoimpl();

    @Override
    public PageUtil<Books> getBypageUtil(int pIndex,double minPrice,double maxPrice) {
        PageUtil<Books> pageUtil=new PageUtil<>();
        pageUtil.setPageIndex(pIndex);
        pageUtil.setDataList(selectAll(minPrice,maxPrice,pIndex,pageUtil.getPageSize()));
        pageUtil.setMinPrice(minPrice);
        pageUtil.setMaxPrice(maxPrice);
        int total=total(minPrice,maxPrice);
        pageUtil.setTotalCount(total);
        return pageUtil;
    }

    @Override
    public boolean deleteById(String bid) {
        return booksDao.deleteById(bid);
    }

    @Override
    public boolean updateBook(Books books) {
        return booksDao.updateBook(books);
    }

    @Override
    public List<Books> selectAll(double minPrice,double maxPrice,int pageIndex,int pageSize) {
        return booksDao.selectAll(minPrice,maxPrice,pageIndex,pageSize);
    }

    @Override
    public boolean insert(Books books) {
        return booksDao.insert(books);
    }

    @Override
    public Books getById(String bid) {
        return booksDao.getById(bid);
    }

    @Override
    public int total(double minPrice,double maxPrice) {
        return booksDao.total(minPrice,maxPrice);
    }

}
