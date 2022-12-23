package Dao;

import DBConnect.DBcontext;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dao {
        Connection conn = null; // kết nối đến mysql
        PreparedStatement ps = null ; // ném câu lệnh query sang mysql
        ResultSet rs = null ;// trả về kết quả

        public List<Product> getAllProduct(){
            List<Product> list = new ArrayList<>();
            String query = "SELECT * FROM `products`";
           try{
               conn = new DBcontext().getConnection();// mở kết nối với mysql
               ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
               // cuối cùng mình sẽ câu lệnh query này và sẽ trả về kết quả đó la rs
               rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
                    while (rs.next()){
                        list.add(new Product(rs.getInt(1),
                                            rs.getString(2),
                                            rs.getString(3),
                                            rs.getInt(4),
                                            rs.getString(5),
                                            rs.getString(6)));
                    }

           }catch (Exception e){

           }
            return list;
        }

    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        String query = "SELECT*FROM category";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ câu lệnh query này và sẽ trả về kết quả đó la rs
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                list.add(new Category(rs.getInt(1),
                                    rs.getString(2)));

            }

        }catch (Exception e){

        }
        return list;
    }

    public Product getLast(){ // lấy ra sản phẩm mới nhất mà sp new = id lớn nhất ,mà id lớn nhất lại nằm cuối
            String query = "SELECT * FROM `products`\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1";
        try{
            conn = new DBcontext().getConnection();// mở kết nối với mysql
            ps = conn.prepareStatement(query); // ném câu lệnh query sang mysq
            // cuối cùng mình sẽ câu lệnh query này và sẽ trả về kết quả đó la rs
            rs = ps.executeQuery(); // như vậy nó sẽ chạy câu lệnh query này
            while (rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));

            }

        }catch (Exception e){

        }
            return null;
    }

    public static void main(String[] args) {
        Dao dao = new Dao();
//        List<Product> list = dao.getAllProduct();
//        for (Product o:list){
//            System.out.println(o);
//        }
//        List<Category> listC = dao.getAllCategory();
//        for (Category o:listC){
//            System.out.println(o);
//        }
        Product p = dao.getLast();
        System.out.println(p);


    }
}
