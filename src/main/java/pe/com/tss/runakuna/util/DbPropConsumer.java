package pe.com.tss.runakuna.util;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class DbPropConsumer implements InitializingBean {
	private final Logger logger = LoggerFactory.getLogger(DbPropConsumer.class);

	private BigDecimal serviceCharge;
	private double rebateAmount;
	private String smtpIp;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("good");
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}	

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public double getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(double rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public String getSmtpIp() {
		return smtpIp;
	}

	public void setSmtpIp(String smtpIp) {
		this.smtpIp = smtpIp;
	}

}