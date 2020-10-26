package com.preproduct.model;

import java.sql.Timestamp;
import java.util.List;

public class PreProductService {
	private PreProductDAO_interface dao;
	public PreProductService() {
		System.out.println("--------------------------------------");
		System.out.println("�i�JService���غc�l");
		dao = new PreProductJDBCDAO();
		System.out.println("Service�ϥ�PreProductJDBCDAO��DAO");
	}
	
	public PreProductVO addPreProduct(Integer event_p_no,String ma_no,java.sql.Timestamp po_start,
			java.sql.Timestamp po_end,Integer po_price,String po_detail) {
		PreProductVO preproductVO = new PreProductVO();
		
		System.out.println("-------Service - �i�Jadd�ǳ�set�Ѽƭ�--------");
		preproductVO.setEvent_p_no(event_p_no);
		preproductVO.setMa_no(ma_no);
		preproductVO.setPo_start(po_start);
		preproductVO.setPo_end(po_end);
		preproductVO.setPo_price(po_price);
		preproductVO.setPo_detail(po_detail);
		
		dao.insert(preproductVO);
		System.out.println("Service - add����set�Ѽƨ�insert");
		
		return preproductVO;
	}
	
	public PreProductVO addByRanking(String ma_no,java.sql.Timestamp po_start,
			java.sql.Timestamp po_end,Integer po_price,String po_detail) {
		PreProductVO preproductVO = new PreProductVO();
		
		System.out.println("-------Service - �i�JaddByRanking�ǳ�set�Ѽƭ�--------");
		
		preproductVO.setMa_no(ma_no);
		preproductVO.setPo_start(po_start);
		preproductVO.setPo_end(po_end);
		preproductVO.setPo_price(po_price);
		preproductVO.setPo_detail(po_detail);
		
		dao.insertByRanking(preproductVO);
		System.out.println("Service - addByRanking����set�Ѽƨ�insertByRanking");
		
		return preproductVO;
	}
	
	public PreProductVO switchPreProduct(String po_prod_no,java.sql.Timestamp po_start,
			java.sql.Timestamp po_end) {
		System.out.println("-------Service - �i�JswitchPreProduct--------");
		PreProductVO preproductVO = new PreProductVO();
		System.out.println("Service - �i�Jswitch�ǳ�set�Ѽƭ�");
		preproductVO.setPo_prod_no(po_prod_no);
		System.out.println("(po_prod_no)�w�gset��VO.Po_prod_no");
		preproductVO.setPo_start(po_start);
		System.out.println("(po_start)�w�gset��VO.po_start");
		preproductVO.setPo_end(po_end);
		System.out.println("(po_end)�w�gset��VO.po_end");
		dao.switchPreProduct(preproductVO);
		System.out.println("Service - switch����set�Ѽ�");	
		System.out.println("Service - �öǤJService��switchPreProduct");	
		return preproductVO;
	}
	
	
	public PreProductVO updatePreProduct(String po_prod_no,Integer event_p_no,String ma_no,java.sql.Timestamp po_start,
			java.sql.Timestamp po_end,Integer po_price,String po_detail) {
		PreProductVO preproductVO = new PreProductVO();
		
		System.out.println("Service - �i�Jupdate�ǳ�set�Ѽƭ�");
		preproductVO.setPo_prod_no(po_prod_no);
		preproductVO.setEvent_p_no(event_p_no);
		preproductVO.setMa_no(ma_no);
		preproductVO.setPo_start(po_start);
		preproductVO.setPo_end(po_end);
		preproductVO.setPo_price(po_price);
		preproductVO.setPo_detail(po_detail);
		dao.update(preproductVO);
		System.out.println("Service - update����set�Ѽƨ�update");		
		
		return preproductVO;
	}
	
	public void deletePreProduct(String po_prod_no) {
		dao.delete(po_prod_no);
	}
	
	public PreProductVO getOnePreProduct(String po_prod_no) {
		return dao.findByPrimaryKey(po_prod_no);
	}
	
	
	public List<PreProductVO> getAll(){
		System.out.println("�w�ʰӫ~Service - �i�JgetAll()��k");
		return dao.getALL();
	}
	
	public List<PreProductVO> GET_ALLOF_PREPRODUCT(){
		System.out.println("�w�ʰӫ~Service - �i�JGET_ALLOF_PREPRODUCT()��k");
		return dao.GET_ALLOF_PREPRODUCT();
	}
	
	
}
