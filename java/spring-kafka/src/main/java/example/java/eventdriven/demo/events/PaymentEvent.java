package example.java.eventdriven.demo.events;

import java.math.BigDecimal;

public class PaymentEvent {

	private Long orderId;
	private BigDecimal amount;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
