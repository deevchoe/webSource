package pack.order;

import java.util.Hashtable;

public class CartMgr {
	private Hashtable<String, OrderBean> hCart = new Hashtable<String, OrderBean>();
	
	public void addCart(OrderBean obean) { // 카트 담기
		String product_no = obean.getProduct_no();
		int quantity = Integer.parseInt(obean.getQuantity());
		
		if(quantity > 0) {	// 주문 수량이 일단 0보다 커야지ㅣ
			// 동일 상품 주문인 경우에는 주문 수량만 늘려주면 된다.
			if(hCart.containsKey(product_no)) {	// 이미 1회 이상 주문된 상품인 경우	// 같은 상품인 경우 작동
				OrderBean temp = hCart.get(product_no);	
				quantity += Integer.parseInt(temp.getQuantity());
				temp.setQuantity(Integer.toString(quantity));
				hCart.put(product_no, temp);
			}else {
				hCart.put(product_no, obean);	// 카트에 담기는 최초의 상품(다른 상품인 경우에 작동)		product_no가 없으니까 최초의 상품이지!!		
			}
		}
	}
	
	public Hashtable<String, OrderBean> getCartList(){ // 장바구니 내용을 확인하기 위함
		return hCart;
	}
	
	public void updateCart(OrderBean obean) {	// 장바구니에 들어있는거 수정
		String product_no = obean.getProduct_no();
		hCart.put(product_no, obean);
	}
	public void deleteCart(OrderBean obean) {	// 장바구니 삭제
		String product_no = obean.getProduct_no();
		hCart.remove(product_no); // cart는 db에 있지 않아. ram에 들어가 있어~
	}
}
