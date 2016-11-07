package com.netease.course.service.impl;

import java.sql.Blob;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.netease.course.dao.impl.BuyListDao;
import com.netease.course.dao.impl.ProductDao;
import com.netease.course.model.Buy;
import com.netease.course.model.Product;
import com.netease.course.model.Trx;

@Service
public class ProductService {

	@Resource
	private ProductDao dao;
	@Resource
	private BuyListDao buyListdao;

	public Product insertProduct(double price, String title, Blob image, String summary, Blob detail) {
		dao.insertProductInfo((int)(price*100), title, image, summary, detail);
		int id = dao.getLastPublishId();
		return dao.getProductInfoById(id);
	}

	public List<Product> getAllProductList() {
		List<Product> productList = dao.getProudctList();
		for (Product p : productList) {
			Trx t = dao.getTrxByContentId(p.getId());
			if (t != null) {
				p.setBuyPrice(t.getPrice()/100.0);
				p.setPrice(p.getPrice()/100.0);
				p.setIsBuy(true);
				p.setBuyTime(t.getTime());
				p.setIsSell(true);
			} else {
				p.setPrice(p.getPrice()/100.0);
				p.setIsBuy(false);
				p.setIsSell(false);
			}
		}
		return productList;
	}

	public Product getProductById(int id) {
		Product product = dao.getProductInfoById(id);
		product.setBuyPrice(product.getBuyPrice()/100.0);
		product.setPrice(product.getPrice()/100.0);
		return product;
	}

	public Product getProductById(int contentId, int personId) {
		Product product = dao.getProductInfoById(contentId);
		Trx trx = dao.getTrxByContentIdAndPeronId(contentId, personId);
		if (trx != null) {
			product.setIsBuy(true);
			product.setIsSell(true);
			product.setBuyTime(trx.getTime());
			product.setBuyPrice(trx.getPrice()/100.0);
		}
		product.setPrice(product.getPrice()/100.0);
		return product;
	}

	public Product editProductInfoById(int id, double price, String title, Blob image, String summary, Blob detail) {
		dao.editProductInfo(id, (long)(price*100), title, image, summary, detail);
		Product product = dao.getProductInfoById(id);
		product.setPrice(product.getPrice()/100.0);
		return product;
	}

	public boolean deleteProductInfoById(int id) {
		return dao.deleteProductById(id);
	}

	public boolean insertPurchase(int contentId, int personId, long time) {
		Product product = dao.getProductInfoById(contentId);
		int price = (int) product.getPrice();
		return dao.insertPurchase(contentId, personId, price, time);
	}

	public List<Buy> getBuyList(int personId) {
		List<Buy> buyList = buyListdao.getBuyListByPersonId(personId);
		for (Buy b : buyList) {
			Product p = dao.getProductInfoById(b.getId());
			b.setTitle(p.getTitle());
			b.setImage(p.getImage());
			b.setBuyPrice(b.getBuyPrice()/100.0);
		}
		return buyList;
	}
}
