package books.serverlet.dao.impl;

import books.serverlet.bean.Books;
import books.serverlet.bean.Cart;
import books.serverlet.bean.CartItem;
import books.serverlet.dao.BaseDao;
import books.serverlet.dao.BooksDao;
import books.serverlet.dao.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ClassName:BooksDaoimpl
 * Package:tianjiao.serverlet.dao.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-079:52
 * Version 1.0
 */
public class BooksDaoimpl extends BaseDao implements BooksDao {
    //查询出所有的book,分页展示
    @Override
    public List<Books> selectAll(double minPrice, double maxPrice, int pageIndex, int pageSize) {
        List<Books> list=new ArrayList<>();
        try {
            String sql="select * from books where price between ? and ? limit ?,?;";
            ResultSet resultSet=query(sql,minPrice,maxPrice,(pageIndex-1)*pageSize,pageSize);
            while(resultSet.next()){
                //用基类接收
                Books books= new Books();
                books.setId(resultSet.getInt("id"));
                books.setTitle(resultSet.getString("title"));
                books.setAuthor(resultSet.getString("author"));
                books.setPrice(resultSet.getDouble("price"));
                books.setSales(resultSet.getInt("sales"));
                books.setStock(resultSet.getInt("stock"));
                books.setImgPath(resultSet.getString("img_path"));
                list.add(books);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            free();
        }
        return list;
    }
    //添加新的书

    @Override
    public boolean insert(Books books) {
        int l=0;
        try {
            String sql="insert into books values(null,?,?,?,?,?,?);";
            l=update(sql,books.getTitle(),books.getAuthor(),books.getPrice(),books.getSales(),books.getStock(),books.getImgPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            free();
        }
        return l>0?true:false;
    }
    //找到对应id=bid的书本

    @Override
    public Books getById(String bid) {
        Books books= null;
        try {
            String sql="select * from books where id=?;";
            ResultSet resultSet=query(sql,Integer.parseInt(bid));
            books = new Books();
            //用实体类接收
            while(resultSet.next()){
                books.setId(resultSet.getInt("id"));
                books.setTitle(resultSet.getString("title"));
                books.setAuthor(resultSet.getString("author"));
                books.setPrice(resultSet.getDouble("price"));
                books.setSales(resultSet.getInt("sales"));
                books.setStock(resultSet.getInt("stock"));
                books.setImgPath(resultSet.getString("img_path"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            free();
        }
        return books;
    }
    //修改books对应的对象

    @Override
    public boolean updateBook(Books books) {
        int len= 0;
        try {
            String sql="update books set title=?,author=?,price=?,sales=?,stock=? where id=?;";
            len = update(sql,books.getTitle(),books.getAuthor(),books.getPrice(),books.getSales(),books.getStock(),books.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            free();
        }
        return len>0?true:false;
    }
    //通过书本的id删除书本
    @Override
    public boolean deleteById(String bid) {
        int len= 0;
        try {
            String sql="delete from books where id=?;";
            len = update(sql,Integer.parseInt(bid));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } finally {
            free();
        }
        return len>0?true:false;
    }
    //获取书的总量
    @Override
    public int total(double minPrice,double maxPrice) {
        int tl= 0;
        try {
            String sql="select count(*) from books where price between ? and ?;";
            ResultSet resultSet=query(sql,minPrice,maxPrice);
            while(resultSet.next())
            {
                tl=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            free();
        }
        return tl;
    }

    @Override
    public int[] updateStockSales(Collection<CartItem> list) {
        int[] result=null;
        try {
            connection= JDBCUtil.getConnection();
            String sql="update books set stock=stock-?,sales=sales+? where id=?;";
            pps=connection.prepareStatement(sql);
            for(CartItem cItem:list)
            {
                pps.setObject(1,cItem.getCount());
                pps.setObject(2,cItem.getCount());
                pps.setObject(3,cItem.getBookId());
                pps.addBatch();
            }
            result=pps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("书籍的库存和销量更新失败");
        }
        return result;
    }
}
