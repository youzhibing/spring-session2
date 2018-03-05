package com.yzb.lee.springsession;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import com.yzb.lee.springsession.domain.ConsumerLoanInfo;

public class SpringRestTemplateTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringRestTemplateTest.class);
	private static final String OBJ_URL = "http://localhost:8081/rest/handle";
	
	public static void main(String[] args) {
		ConsumerLoanInfo info = queryConsumerLoan();
		doPush(info);
	}
	private static ConsumerLoanInfo queryConsumerLoan() {
		ConsumerLoanInfo loanInfo = new ConsumerLoanInfo();
		loanInfo.setAffCity("深圳市");
		loanInfo.setAffProvince("广东省");
		loanInfo.setAppNo("201802499349340");
		loanInfo.setBankCardNo("66600049329049983498");
		loanInfo.setBankCellPhone("15174480311");
		loanInfo.setBankCode("23294");
		loanInfo.setBankCustId("430682199212159130");
		loanInfo.setBankCustName("李隆基");
		loanInfo.setBankName("不知道什么行");
		loanInfo.setCellPhone("15174480311");
		loanInfo.setContractLmt(new BigDecimal(20000.00));
		loanInfo.setGender("男");
		loanInfo.setIdNo("430682199212159130");
		loanInfo.setInterestRate(new BigDecimal(0.16));
		loanInfo.setLoanTerm(36);
		loanInfo.setName("李隆基");
		loanInfo.setOrderLmtAmt(new BigDecimal(0.16));
		loanInfo.setProductCd("000408");
		loanInfo.setProductName("驾费分期");
		
		return loanInfo;
	}
	/**
     * 信息推送
     * @param info 推送的信息实体
     */
    private static void doPush(ConsumerLoanInfo info) {
        AsyncRestTemplate restTemplate = new AsyncRestTemplate();
        HttpEntity<ConsumerLoanInfo> httpEntity = new HttpEntity<>(info);
        ListenableFuture<ResponseEntity<String>> forEntity = restTemplate.postForEntity(OBJ_URL, httpEntity, String.class);

        //异步调用后的回调函数
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            //调用失败
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("信息推送失败: {}", ex);
            }
            //调用成功
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                LOGGER.info("信息推送成功: {}", result.getBody());
            }
        });
        LOGGER.info("信息推送结束......");
    }
}
