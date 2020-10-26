package com.preproduct.model;

import java.io.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.discount.model.DiscountSettingService;
import com.discount.model.DiscountSettingVO;
import com.preproduct.model.PreProductService;
import com.preproduct.model.PreProductVO;

public class PreProductServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("------------------�i�JServlet-----------------------");
		System.out.println("Servlet��action�QĲ�o!");
		
		if("getOne_For_Display".equals(action)) { //�Ӧ�Select_Page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*�����ШD�Ѽ� ��J�榡�����~�B�z*/
				String str = req.getParameter("po_prod_no");/*�ιw�ʰӫ~�s���j�M*/
				if(str==null||(str.trim()).length()==0) {
					errorMsgs.add("���A�٨S��J���u�s����?! �A�̤F��?");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
					failureView.forward(req, res);
					return;
				}
				String po_prod_no=null;
				try {
					po_prod_no = new  String(str);
				}catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
					failureView.forward(req, res);
					return;
				}
				/*****************�}�l�d��*********************/
				PreProductService preproductSvc = new PreProductService();
				PreProductVO preproductVO = preproductSvc.getOnePreProduct(po_prod_no);
				if(preproductVO == null) {
					errorMsgs.add("�d�S���o����ƪ�...");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
					failureView.forward(req, res);
					return;
				}
				/*****************�d�ߦ��\�ǳ����*********************/
				req.setAttribute("preproductVO",preproductVO); /*listOnebackendindex.jsp�|����*/
				String url = "/backend/preproduct/listOnebackendindex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("�L�k���o��ơA�R�A��"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
				failureView.forward(req,res);
			}
		}
		if("getOne_For_Update".equals(action)) {
			System.out.println("Servlet - Ĳ�ogetOne_For_Update��action!");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				
				String po_prod_no = new String(req.getParameter("po_prod_no"));
				System.out.println("Servlet - ���o�Ѽ�po_prod_no = "+po_prod_no);
				
				
				
				/***************************2.�}�l�d�߸��****************************************/
				
				PreProductService preproductSvc = new PreProductService();
				System.out.println("Service - �إ�preproductSvc�}�l�d�߸��");
				PreProductVO preproductVO = preproductSvc.getOnePreProduct(po_prod_no);
				System.out.println("Service - �d�ߧ���!");
				
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				
				req.setAttribute("preproductVO", preproductVO);         // ��Ʈw���X��preproductVO����,�s�Jreq
				System.out.println("Servlet - �ǳ����hurl!");
				String url = "/backend/preproduct/update_preproduct_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_preproduct_input.jsp
				System.out.println("Servlet - �������url!");
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			}catch (Exception e) {
				errorMsgs.add("���N�S��k���o�n�諸��ƪ�:" + e.getMessage());
				System.out.println("Servlet - �o��errorMsgs01");
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
				failureView.forward(req, res);
			}
			
		}
		if("switchPreProduct".equals(action)){
			System.out.println("Servlet - Ĳ�oswitchPreProduct��action!");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);

			try{
				String po_prod_no = new String(req.getParameter("po_prod_no").trim());
				System.out.println("Servlet - �ŧiString po_prod_no =(req.getParameter("+po_prod_no+")");

				java.sql.Timestamp po_start = java.sql.Timestamp.valueOf(req.getParameter("po_start").trim());
				
				Date currentdate = new Date(System.currentTimeMillis());
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String strToday = bartDateFormat.format(currentdate);
				Date today = java.sql.Date.valueOf(strToday.toString());
				System.out.println("Servlet - �ŧipo_start =(req.getParameter("+po_start+")");
				java.sql.Timestamp po_end = java.sql.Timestamp.valueOf(req.getParameter("po_end").trim());
				System.out.println("Servlet - �ŧipo_end =(req.getParameter("+po_end+")");
			    System.out.println("����:"+today);
			    System.out.println("�w�ʶ}�l���:"+po_start);
			    System.out.println("�w�ʵ������:"+po_end);
			    
			    System.out.println("today.after(po_start)= "+today.after(po_start));
			    System.out.println("today.after(po_end)= "+today.after(po_end));
			    System.out.println("�̫�P�_=  "+(!(today.after(po_start)||(today.after(po_end)))));
			    
				
				if(today.after(po_start)&&(today.after(po_end))){
					errorMsgs.add("���w�ʰӫ~�w�L�ɴ��A�G�L�k�W�[^0^");
				}else if(today.before(po_start)&&(today.before(po_end))){
					errorMsgs.add("���w�ʰӫ~�|���}�l�A�G�L�k�U�[0w0");
				}

				PreProductVO preproductVO = new PreProductVO();
				preproductVO.setPo_prod_no(po_prod_no);
				preproductVO.setPo_start(po_start);
				preproductVO.setPo_end(po_end);
				System.out.println("Servlet - �N���ŧi����set��preproductVO��");
				try{
				if (!errorMsgs.isEmpty()) {
							req.setAttribute("preproductVO", preproductVO);// �t����J�榡���~��empVO����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
							failureView.forward(req, res);
							return; //�{�����_
						}
				}catch (Exception e) {
					errorMsgs.add("�L�k���o��ơA�R�A��"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
					failureView.forward(req,res);
				}
				/*****************�}�l�d��*********************/
				PreProductService preproductSvc = new PreProductService();
				System.out.println("Servlet - �Х�Service - preproductSvc");
				preproductVO = preproductSvc.switchPreProduct(po_prod_no,po_start,po_end);
				System.out.println("Servlet - ��Svc�I�sswitchPreProduct(po_prod_no,po_start,po_end)");
				System.out.println("Servlet - �NSvc��ipreproductVO");
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("preproductVO",preproductVO);
				String url = "/backend/preproduct/backendindex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				System.out.println("Servlet - ���url");
				successView.forward(req,res);
				/***************************��L�i�઺���~�B�z*************************************/	
				} catch (Exception e) {
						errorMsgs.add("�ק��ƥ���:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
						failureView.forward(req, res);
					}
		}
		
			if ("update".equals(action)) { // �Ӧ�update_preproduct_input.jsp���ШD
				System.out.println("Servlet - Ĳ�oupdate��action!");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String po_prod_no = new String(req.getParameter("po_prod_no").trim());
				System.out.println("���opo_prod_no = "+po_prod_no);
				Integer event_p_no = new Integer(req.getParameter("event_p_no").trim());
				System.out.println("���oevent_p_no = "+event_p_no);
				String ma_no = new String(req.getParameter("ma_no").trim());
				System.out.println("���oma_no = "+ma_no);
				java.sql.Timestamp po_start = null;
				try {
					po_start = java.sql.Timestamp.valueOf(req.getParameter("po_start").trim());
					System.out.println("���opo_start= "+po_start);
				
				} catch (IllegalArgumentException e) {
					po_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				java.sql.Timestamp po_end = null;
				try {
					po_end = java.sql.Timestamp.valueOf(req.getParameter("po_end").trim());
					System.out.println("���opo_end= "+po_end);
				} catch (IllegalArgumentException e) {
					po_end=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				Integer po_price = null;
				try {
					po_price = new Integer(req.getParameter("po_price").trim());
					System.out.println("���opo_price= "+po_price);
				} catch (NumberFormatException e) {
					po_price = 0;
					errorMsgs.add("����ж�Ʀr.");
				}
				String po_detail = (req.getParameter("po_detail").trim());
				System.out.println("���opo_detail= "+po_detail);

				PreProductVO preproductVO = new PreProductVO();
				preproductVO.setPo_prod_no(po_prod_no);
				preproductVO.setEvent_p_no(event_p_no);
				preproductVO.setMa_no(ma_no);
				preproductVO.setPo_start(po_start);
				preproductVO.setPo_end(po_end);
				preproductVO.setPo_price(po_price);
				preproductVO.setPo_detail(po_detail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("preproductVO", preproductVO);// �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/preproduct/update_preproduct_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				PreProductService preproductSvc = new PreProductService();
				preproductVO = preproductSvc.updatePreProduct(po_prod_no, event_p_no, ma_no, po_start, po_end,po_price, po_detail);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("preproductVO", preproductVO);// ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/backend/preproduct/backendindex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/preproduct/update_preproduct_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // �Ӧ�addbackendindex.jsp���ШD  
			System.out.println("-----ServletĲ�oinsert-----");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Integer event_p_no=null;
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String event_p_noStr=req.getParameter("event_p_no");
				System.out.println("���� event_p_noStr = " + event_p_noStr);
				
				if(event_p_noStr == null || event_p_noStr.trim().length() == 0)
				{
					errorMsgs.add("�п�ܧ@�~�s��!");
					System.out.println("�wadd�п�ܧ@�~�s��!");
				}else {
					try {
					event_p_no = new Integer(req.getParameter("event_p_no").trim());
					}catch(NumberFormatException e) {
						errorMsgs.add("�Ʀr�榡����");
					}
				}

				String ma_no = req.getParameter("ma_no");
				if (ma_no == null || ma_no.trim().length() == 0) {
					errorMsgs.add("�п�ܯ���!");
					System.out.println("�wadd�п�ܯ���! ma_no = " + ma_no);
				}

				java.sql.Timestamp po_start = null;
				System.out.println(req.getParameter("po_start"));
				try {
					po_start = java.sql.Timestamp.valueOf(req.getParameter("po_start")+" 00:00:00");
					System.out.println("�w�ʶ}�l���! po_start" + po_start);
				} catch (IllegalArgumentException e) {
					po_start=new java.sql.Timestamp(System.currentTimeMillis());
					System.out.println("�w�ʶ}�l����]��catch! po_start" + po_start);
					errorMsgs.add("�п�J�w�ʶ}�l���!");
					
				}
				java.sql.Timestamp po_end = null;
				try {
					po_end = java.sql.Timestamp.valueOf(req.getParameter("po_end").trim()+" 00:00:00");
					System.out.println("�w�ʵ������! po_end" + po_end);
				} catch (IllegalArgumentException e) {
					
					po_end=new java.sql.Timestamp(System.currentTimeMillis());
					System.out.println("�w�ʵ�������]��catch! po_end" + po_end);
					errorMsgs.add("�п�J�w�ʵ������!");
					
				}
				Integer po_price = null;
				try {
					po_price = new Integer(req.getParameter("po_price").trim());
				} catch (NumberFormatException e) {
					po_price = 0;
					errorMsgs.add("����ж�Ʀr");
					System.out.println("�wadd����ж�Ʀr.!");
				}
				String po_detail = (req.getParameter("po_detail").trim());
		
				PreProductVO preproductVO = new PreProductVO();
				preproductVO.setEvent_p_no(event_p_no);
				preproductVO.setMa_no(ma_no);
				preproductVO.setPo_start(po_start);
				preproductVO.setPo_end(po_end);
				preproductVO.setPo_price(po_price);
				preproductVO.setPo_detail(po_detail);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("�w�P�_!errorMsgs.isEmpty()");
					req.setAttribute("preproductVO", preproductVO); // �t����J�榡���~��preproductVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/preproduct/addPreProduct.jsp");
					System.out.println("�w�P�_failureView");
					failureView.forward(req, res);
					System.out.println("�wforward");
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				PreProductService preproductSvc = new PreProductService();
				preproductVO = preproductSvc.addPreProduct(event_p_no, ma_no, po_start, po_end, po_price, po_detail);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/backend/preproduct/backendindex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);			
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/preproduct/addbackendindex.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insertByRanking".equals(action)) {
			System.out.println("-----ServletĲ�oinsertByRanking-----");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String ma_no = req.getParameter("ma_no");
				System.out.println("���oma_no = "+ma_no);
				if(ma_no == null || ma_no.trim().length() == 0) {
					errorMsgs.add("�п�ܯ���");
				}
				java.sql.Timestamp po_start = null;
				try {
					po_start = java.sql.Timestamp.valueOf(req.getParameter("po_start").trim()+" 00:00:00");
					System.out.println("���opo_start = "+po_start);
				}catch (IllegalArgumentException e) {
					po_start=new java.sql.Timestamp(System.currentTimeMillis());
					System.out.println("po_start�]��catch�ӤF");
					errorMsgs.add("�г]�w�}�l�ɶ�");
				}
				java.sql.Timestamp po_end = null;
				try {
					po_end = java.sql.Timestamp.valueOf(req.getParameter("po_end").trim()+" 00:00:00");
					System.out.println("���opo_end = "+po_end);
				}catch (IllegalArgumentException e) {
					po_end=new java.sql.Timestamp(System.currentTimeMillis());
					System.out.println("po_end�]��catch�ӤF");
					errorMsgs.add("�г]�w�}�l�ɶ�");
				}
				Integer po_price = null;
				try {
					po_price = new Integer(req.getParameter("po_price").trim());
					System.out.println("���opo_price = "+po_price);
				}catch(NumberFormatException e) {
					po_price=0;
					errorMsgs.add("����ж�Ʀr");
					System.out.println("�wadd����ж�Ʀr");
				}
				String po_detail = (req.getParameter("po_detail").trim());
				System.out.println("���opo_detail = "+po_detail);
				
				PreProductVO preproductVO = new PreProductVO();
				preproductVO.setMa_no(ma_no);
				preproductVO.setPo_start(po_start);
				preproductVO.setPo_end(po_end);
				preproductVO.setPo_price(po_price);
				preproductVO.setPo_detail(po_detail);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("preproductVO", preproductVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/addPreProduct_ByMano.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.�}�l��q�s�W���***************************************/
				PreProductService preproductScv = new PreProductService();
				preproductVO = preproductScv.addByRanking(ma_no, po_start, po_end, po_price, po_detail);
				/***************************3.��q�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/backend/preproduct/backendindex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllbackendindex.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String po_prod_no = new String(req.getParameter("po_prod_no"));
				
				/***************************2.�}�l�R�����***************************************/
				PreProductService preproductSvc = new PreProductService();
				preproductSvc.deletePreProduct(po_prod_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/backend/preproduct/backendindex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
				failureView.forward(req, res);
			}
		}
		if("switchDiscount".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("Servlet Ĳ�oswitchDiscount ");
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String po_prod_no = req.getParameter("po_prod_no");
				System.out.println("���o�w�ʰӫ~po_prod_no = " + po_prod_no);
				Integer event_p_no = new Integer(req.getParameter("event_p_no").trim());
				System.out.println("���o���ʽs��event_p_no = " + event_p_no);
				Integer po_price = new Integer(req.getParameter("po_price").trim());
				System.out.println("���o���po_price = " + po_price);
				
				
				
				/***************************2.�}�l�R�����***************************************/
				
				DiscountSettingService discountSvc = new DiscountSettingService();
//				List<DiscountSettingVO> discountList =  discountSvc. 10/20�q�o���~��
				
				/***************************3.����,�ǳ����(Send the Success view)***********/								
				String url = "/backend/preproduct/backendindex.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/preproduct/backendindex.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
