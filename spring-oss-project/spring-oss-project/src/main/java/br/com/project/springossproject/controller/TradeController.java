package br.com.project.springossproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.project.springossproject.model.Trade;
import br.com.project.springossproject.model.User;

import jakarta.servlet.http.HttpServletRequest;

public class TradeController {
	User user = new User();
	Map<String, Double> companies = new HashMap<String, Double>();
	Map<String, Trade> trades =new HashMap<String, Trade>(); 
	
	public TradeController() {
		companies.put("companie example 1", 300.00);
		companies.put("companie example 2", 500.00);
		companies.put("companie example 3", 299.00);
		companies.put("companie example 3", 15000.00);
	}
	
	@RequestMapping(value="/trade/do", method = RequestMethod.POST)
	@ResponseBody
	public String tradeDo(@ModelAttribute("ticker") String ticker, @ModelAttribute("quantity") int quantity, HttpServletRequest request) {
		Double price = companies.get(ticker);
		Trade trade = new Trade(ticker, price, quantity);
		double total = price*quantity;
		trade.setTotalCost(total);
		
		trades.put(ticker, trade);
		user=(User)request.getSession().getAttribute("user");
		double balance = user.getBalance()-total;
		user.setBalance(balance);
		
		return "<html><body bgcolor='coral'>Traded Successfully".concat(user.getUserid()).concat(" your balance now is:: ").concat(String.valueOf(user.getBalance())).concat(" <BR><a href='http://localhost:7070/contentTablePage.html'>Table Content</a></body></html>");
	}
	
	@RequestMapping(value="/trade/all", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Trade> getAllRegisteredUsers(){
		return trades;
	}
	
	@RequestMapping(value="/trade/{ticker}", method = RequestMethod.GET)
	@ResponseBody
	public Trade getUser(@PathVariable("ticker") String ticker) {
		return trades.get(ticker);
	}

}
