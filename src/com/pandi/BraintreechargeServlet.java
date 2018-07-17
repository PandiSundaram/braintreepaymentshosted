package com.pandi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.*;

import org.mortbay.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Subscription;
import com.braintreegateway.SubscriptionRequest;

@SuppressWarnings("serial")
@Controller
public class BraintreechargeServlet  {
	 
	   @RequestMapping("/hello")  
	    public ModelAndView helloWorld() {  
	        String message = "HELLO SPRING MVC HOW R U";  
	        return new ModelAndView("success", "message", message);  
	    }  
	
		@RequestMapping(value="/btChargeUsingNonce", method = RequestMethod.GET)
		public @ResponseBody HashMap<String, Object> btNonceCharge(@RequestParam(value="token")String token, HttpServletRequest request1)
		{
			HashMap<String, Object> ref = new HashMap<String, Object>();
			try{
				
				
				         //  Log.info(token);
				          //Log.info("pandi");

				        System.out.println(token);
				        BraintreeGateway gateway = new BraintreeGateway(
						   Environment.SANDBOX,
						  "qt7d7qk52zpssmqb",
						  "g2g5m2bwdnx54ny7",
						  "a594942086f68621385ef45241ee39b7"
						);
		                   gateway.getConfiguration().setTimeout(10000);
		                   
		                   
			               CustomerRequest request = new CustomerRequest()
						  .firstName("pandi")
						  .lastName("sundaram")
						  .paymentMethodNonce(token);
						   Result<Customer> result = gateway.customer().create(request);
						   
						   
						   
						   System.out.println(result);
						   
						   System.out.println(result.isSuccess());
						//result.isSuccess();

//						if(result.isSuccess()){
//							Customer customer = result.getTarget();
//							customer.getId();
//							customer.getPaymentMethods().get(0).getToken();
//
//							System.out.println(customer.getId());
//
//							SubscriptionRequest request2 = new SubscriptionRequest()
//									  .paymentMethodToken(customer.getPaymentMethods().get(0).getToken())
//									  .planId("Monthly");
//
//									Result<Subscription> result1 = gateway.subscription().create(request2);
//
//									ref.put("status", "true");
//									ref.put("message", "subscription created successfully");
//									ref.put("vaultId", customer.getId());
//
//						}
//						else{System.out.println(result.getErrors());}
			}

			catch(Exception e)
			{
				e.printStackTrace();
	 			ref.put("status", "false");
			}
			return ref;
		}
	 
}
