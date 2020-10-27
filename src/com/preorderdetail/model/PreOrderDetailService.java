package com.preorderdetail.model;

import java.util.List;



public class PreOrderDetailService {
	private PreOrderDetailDAO_interface dao;
	
	public PreOrderDetailService() {
		dao = new PreOrderDetailDAO();
	}
	
	public List<PreOrderDetailVO> getAllByPo_no(String po_no) {
		System.out.println("Service - �ǳƶi�Jdao.getAllByPo_no(po_no);");
		return dao.findByPrimaryKey(po_no);
	}
	
	public List<PreOrderDetailVO> getAll(){
		System.out.println("�w�ʭq��Service - �i�JgetAll()��k");
		return dao.getAll();
	}
	public List<PreOrderDetailVO> getAll_OrderQty(){
		System.out.println("�w�ʭq��Service - �i�JgetAll_OrderQty()��k");
		return dao.getAll_OrderQty();
	}
}
