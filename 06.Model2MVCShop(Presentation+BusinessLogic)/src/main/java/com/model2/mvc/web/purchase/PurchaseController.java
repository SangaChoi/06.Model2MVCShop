package com.model2.mvc.web.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView(@RequestParam("prodNo") int prodNo) throws Exception{
		
		System.out.println("/addPurchaseView.do");
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("product", productService.getProduct(prodNo));
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase(@RequestParam("prodNo") int prodNo, @RequestParam("buyerId") String userId, @ModelAttribute("purchase")Purchase purchase) throws Exception{
		
		System.out.println("/addPurchase.do");
		
		purchase.setBuyer(userService.getUser(userId));
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		purchase.setTranCode("1");
		
		System.out.println("==========="+purchase);
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("product",productService.getProduct(prodNo));
		//modelAndView.addObject("user", userService.getUser(userId));
		purchaseService.addPurchase(purchase);
		modelAndView.setViewName("/purchase/addPurchase.jsp");
		
		return modelAndView;
	}

}
