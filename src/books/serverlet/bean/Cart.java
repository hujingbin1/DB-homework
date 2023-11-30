package books.serverlet.bean;

import javax.persistence.criteria.CriteriaBuilder;
import javax.security.auth.message.callback.PrivateKeyCallback;
import java.math.BigDecimal;
import java.util.*;

/**
 * ClassName:Cart
 * Package:books.serverlet.bean
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1122:59
 * Version 1.0
 */
public class Cart {
    private Integer totalCount;
    private Double cartTotalPrice;
    private Map<Integer,CartItem> cartItemMap=new HashMap<>();

    public Cart() {
    }

    public Cart(Integer totalCount, Double cartTotalPrice, Map<Integer, CartItem> cartItemMap) {
        this.totalCount = totalCount;
        this.cartTotalPrice = cartTotalPrice;
        this.cartItemMap = cartItemMap;
    }

    public Integer getTotalCount() {
        this.totalCount=0;
        for(CartItem item:cartItemMap.values())
        {
            this.totalCount+=item.getCount();
        }
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getCartTotalPrice() {
        Collection<CartItem> items=cartItemMap.values();
        BigDecimal bigDecimaltotalPrice=new BigDecimal("0.0");
        for(CartItem cartItem:items)
        {
            BigDecimal bigDecimal=new BigDecimal(cartItem.getTotalPrice()+"");
            bigDecimaltotalPrice=bigDecimaltotalPrice.add(bigDecimal);
        }
        cartTotalPrice=bigDecimaltotalPrice.doubleValue();
        return cartTotalPrice;
    }

    public void setCartTotalPrice(Double cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }
    public Collection<CartItem> getCartList()
    {
        Collection<CartItem> list=new ArrayList<>();
        list=cartItemMap.values();
        return list;
    }
    public void addBooksToCart(Books books)
    {

       if(cartItemMap.containsKey(books.getId()))
       {
           CartItem cartItem=cartItemMap.get(books.getId());
           cartItem.setCount(cartItem.getCount()+1);
           cartItem.setTotalPrice(cartItem.getCount()*books.getPrice());

       }
       else
       {
           CartItem cartItem=new CartItem();
           cartItem.setBookId(books.getId());
           cartItem.setCount(1);
           cartItem.setPrice(books.getPrice());
           cartItem.setTitle(books.getTitle());
           cartItem.setImgPath(books.getImgPath());
           cartItem.setTotalPrice(books.getPrice());
           cartItemMap.put(books.getId(),cartItem);
       }


    }
    public void updatecount(String bookId)
    {
         CartItem cartItem=cartItemMap.get(Integer.parseInt(bookId));
         cartItem.setCount(cartItem.getCount()+1);
         cartItem.setTotalPrice(cartItem.getTotalPrice()+cartItem.getPrice());
    }
    public void downcount(String bookId)
    {
        CartItem cartItem=cartItemMap.get(Integer.parseInt(bookId));
        cartItem.setCount(cartItem.getCount()-1);
        cartItem.setTotalPrice(cartItem.getTotalPrice()-cartItem.getPrice());
    }

    public void deletebookitem(String bookId)
    {
        cartItemMap.remove(Integer.parseInt(bookId));
    }
    public void changecount(String bookId,String bCount)
    {
        CartItem cartItem=cartItemMap.get(Integer.parseInt(bookId));
        cartItem.setCount(Integer.parseInt(bCount));
        cartItem.setTotalPrice(Integer.parseInt(bCount)*cartItem.getPrice());
    }


}
