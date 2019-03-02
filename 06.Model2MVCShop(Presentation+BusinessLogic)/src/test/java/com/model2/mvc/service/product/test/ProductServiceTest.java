package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception{
		
		Product product=new Product();
		product.setProdName("ī��");
		product.setProdDetail("�ڵ�ũ��");
		product.setManuDate("20011212");
		product.setPrice(5000);
		product.setFileName("�������ϳ���");
		
		productService.addProduct(product);
		
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		
		Product product=new Product();
		product=productService.getProduct(10161);
		
		System.out.println(product);
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product=productService.getProduct(10161);
		Assert.assertNotNull(product);
		
		product.setProdName("ī�м���");
		product.setProdDetail("ī�е����ϼ���");
		product.setManuDate("20050505");
		product.setPrice(6000);
		product.setFileName("ī�м�������");
		
		productService.updateProduct(product);
		
		product=productService.getProduct(10161);
		System.out.println(product);
	}
	
	@Test
	public void testGetProductListAll() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	}
	
	//@Test
	public void testGetProductListByProdNo() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10146");
	 	Map<String,Object> map = productService.getProductList(search);
		
	 	List<Object> list = (List<Object>)map.get("list");
	 	
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
		
	}
	
	//@Test
	 public void testGetProductListByProdName() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("��");
	 	Map<String,Object> map = productService.getProductList(search);
		
	 	List<Object> list = (List<Object>)map.get("list");
	 	
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
		
	}
	 
	//@Test
		 public void testGetProductListByPrice() throws Exception{
			
			Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("1111");
		 	Map<String,Object> map = productService.getProductList(search);
			
		 	List<Object> list = (List<Object>)map.get("list");
		 	
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
			
		}
}
