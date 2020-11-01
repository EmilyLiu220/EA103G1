package com.wel_record.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mem.model.MemVO;
import com.wel_record.model.WelRecordService;
import com.wel_record.model.WelRecordVO;

import tools.EcpayTool;
import tools.MoneyTool;

//@WebServlet("/welRecord/welRecord.do") //mark by YCL

public class WelRecordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final int WITHDRAW_LIMIT = 300000; // ��鴣��W���T�Q�U

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("���]��get");
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("���]��post");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		WelRecordService welRecordSvc = new WelRecordService();

		if ("deposit".equals(action)) {

			// ���o��U�n�J���|��VO
			MemVO memVO = new MemVO();
			HttpSession session = req.getSession();
			memVO = (MemVO) session.getAttribute("memVO");

			System.out.println("memVO.accno:" + memVO.getM_accno());

			List<String> errorMsgsForMoney = new LinkedList<String>();

			req.setAttribute("errorMsgsForMoney", errorMsgsForMoney);

			try {

				// ********************1.����ШD�Ѽƨè̿�J�榡�����~�B�z******************

				Integer amount = new Integer(req.getParameter("depositAmount").trim());

				if (amount <= 0) {
					errorMsgsForMoney.add("���B���o�p�󵥩�s");

				}
				if (!errorMsgsForMoney.isEmpty()) {
					System.out.println("���B�p��s, �ɦ^�쭶��");
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memArea.jsp");
					failureView.forward(req, res);
					return;
				}

//				//********************2.�}�l�s�W���(�ª�code�ݽT�{)******************
//
//						WelRecordService wrSrc = new WelRecordService();
//						WelRecordVO welRecordVO = new WelRecordVO();
//						MemVO updatedMemVO = new MemVO();
//						System.out.println("memVO.getMem_id():" + memVO.getMem_id());
//						System.out.println("amount:" + amount);
//
//						MemService memSrc = new MemService();
//
//						welRecordVO = wrSrc.addWelRecord(memVO.getMem_id(), 10, null, amount); // �x�Ȥ�����ӷ��N����10
//
//						updatedMemVO = memSrc.findByPrimaryKey(memVO.getMem_id());
//
//						System.out.println("�x�Ȧ��\");
//
//						session.setAttribute("memVO", updatedMemVO);// ��ssession��memVO
//
//						/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
//
//						String url = "/frontend/members/memArea.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//						successView.forward(req, res);
//
//					} catch (Exception e)
//					{
//						errorMsgsForMoney.add("�L�k���o���:" + e.getMessage());
//						RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memArea.jsp");
//						failureView.forward(req, res);
//					}
//				

//				********************2.�}�l���b******************

				Boolean ifCheckOutSucess = MoneyTool.checkOut(session, 10, null, amount);// �x�ȶǤJ����

				if (ifCheckOutSucess) {
					String request2Ecpay = EcpayTool.genAioCheckOutALL(amount, action, req);

					URL url = new URL(request2Ecpay);
					HttpURLConnection http = (HttpURLConnection) url.openConnection();
					http.setRequestMethod("POST");
					InputStream input = http.getInputStream();
					http.disconnect();
					byte[] data = new byte[1024];
					int idx = input.read(data);
					String str = new String(data, 0, idx);
					out.println(str);
					input.close();

					System.out.println("���b���\");
					String returnUrl = "/frontend/members/memArea.jsp";// ���b���\�����^��|������
					RequestDispatcher successView = req.getRequestDispatcher(returnUrl);
					successView.forward(req, res);

				} else {
					System.out.println("���b����");
					errorMsgsForMoney.add("�x�ȥ���,���ˬd�榡�O�_���T"); // ���b���ѫ᪺�B�z
				}

			} catch (Exception e) {
				errorMsgsForMoney.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memArea.jsp");
				failureView.forward(req, res);
			}

		}

		if ("withdraw".equals(action)) {

			// ���o��U�n�J���|��VO
			MemVO memVO = new MemVO();
			HttpSession session = req.getSession();
			memVO = (MemVO) session.getAttribute("memVO");

			System.out.println("memVO.accno:" + memVO.getM_accno());

			List<String> errorMsgsForMoney = new LinkedList<String>();

			req.setAttribute("errorMsgsForMoney", errorMsgsForMoney);

			try {

				// ********************1.����ШD�Ѽƨè̿�J�榡�����~�B�z******************

				Integer amount = new Integer(req.getParameter("withdrawAmount").trim());

				if (amount > WITHDRAW_LIMIT) {
					errorMsgsForMoney.add("��鴣��W�����s�x��30�U��");
				}

				if (amount <= 0) {
					errorMsgsForMoney.add("���B���o�p�󵥩�s");

				}

				if (!errorMsgsForMoney.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memArea.jsp");

					failureView.forward(req, res);
					return;
				}

//				********************2.�}�l���b******************

				Boolean ifCheckOutSucess = MoneyTool.checkOut(session, 20, null, -amount);// ���ڽжǤJ�t��

				if (ifCheckOutSucess) {

					String returnUrl = "/frontend/members/memArea.jsp";// ���b���\�����^��|������
					RequestDispatcher successView = req.getRequestDispatcher(returnUrl);
					successView.forward(req, res);

				} else {
					errorMsgsForMoney.add("���⥢��,���ˬd�l�B�O�_�R���ή榡���T"); // ���b���ѫ᪺�B�z
				}

				if (!errorMsgsForMoney.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memArea.jsp");

					failureView.forward(req, res);
					return;
				}

			} catch (Exception e) {
				errorMsgsForMoney.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memArea.jsp");
				failureView.forward(req, res);
			}

		}

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>(); // �Ыؤ@��list�ñN���~�T���s�J��list��, �A�N��list�s���req cope��
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				********************1.����ШD�Ѽƨè̿�J�榡�����~�B�z******************
				String str = req.getParameter("tns_id");
				if (str == null || (str.trim().length() == 0)) // ����J�ο�J�ť�
				{
					errorMsgs.add("�п�J����y����");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/welRecord/select_page_g1.jsp");

					failureView.forward(req, res);
					return;
				}

				Integer tns_id = null;
				try {
					tns_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�y���s���榡�����T");
				}
//				********************2.�}�l�d�߸��******************

				WelRecordVO welRecordVO = welRecordSvc.getOneWelRecord(tns_id);

				if (welRecordVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/welRecord/select_page_g1.jsp");

					failureView.forward(req, res);
					return;
				}

//				********************3.�d�ߧ���,�ǳ����******************
				req.setAttribute("welRecordVO", welRecordVO);
				String url = "/backend/welRecord/listOneWelRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

//				********************��L�i����~�B�z******************
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}

//			---------------------------------�d�߿��]����by�|���s��----------------------------------

		if ("getAllbyMem_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				********************1.�����Ѽ�  �U�Ԧ����S�����~�B�z******************

				String mem_id = req.getParameter("mem_id");

//				********************2.�}�l�d�߸��******************

				List<WelRecordVO> list = welRecordSvc.getWelRecordByMemID(mem_id);

				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backend/welRecord/select_page_g1.jsp");

					failureView.forward(req, res);
					return;
				}

//				********************3.�d�ߧ���,�ǳ����******************
				req.setAttribute("set", list);
				String url = "/backend/welRecord/listAllWelRecordByMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

//				********************��L�i����~�B�z******************
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		// �d�ߥ������]����

		if ("getAllRecords".equals(action)) {

			List<WelRecordVO> list = welRecordSvc.getAll();

			JSONArray array = new JSONArray(list);
			out.print(array);

		}

		// �d�߳�@�|�����]����by�|���s���B�|���b���γ�@����y����

		if ("getOneById_Accno".equals(action)) {

			try {

				// �B�z�ШD�Ѽ�, ���o����ӷ��X
				String input = req.getParameter("input").trim();

				if (input.substring(0, 1).equals("M")) {

					System.out.println("input:" + input);
					List<WelRecordVO> list = welRecordSvc.getWelRecordByMemID(input);

					JSONArray array = new JSONArray(list);
					out.print(array);

				} else if (input.substring(0, 1).equals("4")) {

					int tnsid = Integer.parseInt(input);

					System.out.println("tnsid:" + tnsid);

					WelRecordVO welRecordVO = welRecordSvc.getOneWelRecord(tnsid);

					JSONObject obj = new JSONObject(welRecordVO);

					out.print(obj);

				} else {
					System.out.println(false);
					out.print(false);
				}

			} catch (Exception e) {
				e.getMessage();
				out.print(false);
			}
		}

		// �z�����ӷ�
		if ("filterRecords".equals(action)) {

			try {

				// �B�z�ШD�Ѽ�, ���o����ӷ��X
				String input = req.getParameter("filter").trim();

				int src = Integer.parseInt(input);

				// �}�l�d�߸��

				List<WelRecordVO> list = null;

				if (src == 10 || src == 20) { // �|���x�� or �|������

					list = welRecordSvc.getWelRecordBySrc(src);

				} else if (src == 77) { // ���x����-�q��

					list = welRecordSvc.getWelRecordAmongSrc(40, 43);

				} else if (src == 88) { // ���x����-����/�馩��

					list = welRecordSvc.getWelRecordAmongSrc(30, 34);

				} else { // ���x�h��-�q��

					list = welRecordSvc.getWelRecordAmongSrc(35, 38);

				}

				JSONArray array = new JSONArray(list);
				out.print(array);

			} catch (Exception e) {
				e.getMessage();

			}

		}

	}

}
