package com.preorder.model;

import java.io.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.http.*;

import com.preorderdetail.model.PreOrderDetailService;
import com.preorderdetail.model.PreOrderDetailVO;

import tools.MoneyTool;
public class PreOrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		System.out.println("------------------�i�JServlet-----------------------");
		System.out.println("PreOrder - Servlet��action�QĲ�o!");
		
		if("getOne_For_Display".equals(action)) {
			System.out.println("PreOrder - getOne_For_Display�QĲ�o!");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String po_no = req.getParameter("po_no");
				System.out.println("po_no = "+po_no);

				/*****************�}�l�d��*********************/
				PreOrderDetailService preorderdetailSvc = new PreOrderDetailService();
				List<PreOrderDetailVO> preorderdetaillist = preorderdetailSvc.getAllByPo_no(po_no);
				System.out.println("preorderdetailVO = "+preorderdetaillist);
				
				
				if(preorderdetaillist == null) {
					errorMsgs.add("�d�S���o����ƪ�...");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/preproduct/order_Success_List.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*****************�d�ߦ��\�ǳ����*********************/
				req.setAttribute("preorderdetailVO",preorderdetaillist);
				req.setAttribute("po_no",po_no);
				String url = "/frontend/preproduct/order_Detail_Page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("�L�k���o��ơA�R�A��"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/preproduct/order_Success_List.jsp");
				failureView.forward(req,res);
			}
		}
		if("cancel_order".equals(action)) {
			System.out.println("PreOrder - Servlet(�����q��)�QĲ�o!");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String formhash = req.getParameter("formhash");
				System.out.println("�����檺formhash = "+formhash);
				Set<String> formhashSession = (Set<String>) session.getAttribute("formhashSession");
				System.out.println("����session���������X = "+formhashSession);
				
				if(formhashSession == null || !formhashSession.contains(formhash)) {
					errorMsgs.add("���ƴ����o!");
					formhashSession.remove(formhash);
					session.setAttribute("formhashSession", formhashSession);
				}else {
					System.out.println("�@�����`");
				}
				
				String po_no = req.getParameter("po_no");
				System.out.println("�n�������q��s����po_no = "+po_no);
				Integer po_total = (new Integer(req.getParameter("po_total")));
				System.out.println("�n�h�ڪ����B��po_total = "+po_total);
				
				PreOrderVO preorderVO = new PreOrderVO();
				preorderVO.setPo_no(po_no);
				preorderVO.setPo_total(po_total);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("preorderVO", preorderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/preproduct/order_Success_List.jsp");
					failureView.forward(req, res);
					errorMsgs.clear();
					System.out.println("����errorMsgs.clear()");
					return;
				}
				/*************************** 2.�}�l�h�ڤΧR����� ***************************************/
				System.out.println("�ǳƨϥ�MoneyTool�u��");
				Boolean ifCheckOutSucess = MoneyTool.checkOut(session, 36, po_no, po_total);
				System.out.println("�ϥΧ�MoneyTool�u��");
				
				
				System.out.println("�i�J�R���q�涥�q");
				PreOrderService preorderSvc = new PreOrderService();
				preorderSvc.deletePreOrder(po_no);
				
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				System.out.println("�ާ@���\�A�qSession�̧�formhash�R��");
				formhashSession.remove(formhash);
				session.setAttribute("formhashSession", formhashSession);
				session.removeAttribute("formhashSession");

				System.out.println("�R������,�ǳ����url");
				String url = "/frontend/preproduct/order_Success_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				

			} catch (Exception e) {
				errorMsgs.add("�L�k���o��ơA�R�A��"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/preproduct/order_Success_List.jsp");
				failureView.forward(req,res);
			}
			
			
		}
	}
}
