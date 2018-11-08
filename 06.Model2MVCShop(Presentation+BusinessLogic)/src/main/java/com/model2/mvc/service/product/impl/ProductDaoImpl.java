package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void insertProduct(Product product) throws Exception {

		product.setManuDate(product.getManuDate().replace("-", ""));
		sqlSession.insert("ProductMapper.addProduct", product);
		
	}

	@Override
	public Product findProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	@Override
	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList",search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		
		product.setManuDate(product.getManuDate().replace("-", ""));
		sqlSession.update("ProductMapper.updateProduct",product);	
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

}
