package com.mem.controller;
import tools.YclTools;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import java.io.InputStream;

import com.mem.model.MemService;
import com.mem.model.MemVO;


@WebServlet("/frontend/members/mem.do")
@MultipartConfig
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
			String mem_id = (String)req.getParameter("mem_id").trim();
			MemService memberSvc = new MemService();
			YclTools.readByteArrayFromDB(req, res, memberSvc.getMemberPhoto(mem_id));
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/backend/img/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
		
		
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		

		if ("InsertMem".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("�|���m�W�ФŪť�");
				} else if (!name.trim().matches(nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W�п�J���B�^��r��, �B���ץ����b2��10����");
				}
				System.out.println("�q�L�|���m�W����");

				String gender = req.getParameter("gender");

				if (gender == null || gender.trim().length() == 0) {
					errorMsgs.add("�п�ܩʧO");
				}
				System.out.println(gender);
				System.out.println("�q�L�ʧO����");

				String mobile = req.getParameter("mobile").trim();
				String mobileReg = "\\d{10}";
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("����ФŪť�");
				} else if (!mobile.trim().matches(mobileReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�п�J�Q�X�Ʀr�p0912312312");
				}

				System.out.println("�q�L�������");

				String email = req.getParameter("email").trim();
				String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("�q�l�H�c�ФŪť�");
				} else if (!email.trim().matches(emailReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�п�J���T���q�l�H�c�榡");
				}

				System.out.println("�q�L�q�l�H�c����");

				String zip = req.getParameter("zip");

				System.out.println(zip);

				String city = "";
				String district = "";
				Integer zipcode = null;

				if (zip == null || zip.trim().length() == 0) {
					errorMsgs.add("�п�ܶl���ϸ�");
				} else {
					String[] zips = zip.split("\\s+");
					city = zips[1];
					district = zips[2];
					zipcode = Integer.parseInt(zips[0]);
					System.out.println(zipcode);

					System.out.println("�q�L�l���ϸ�����");
				}
				String address = req.getParameter("address").trim();

				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");

				}

				String fullAddress = city + district + address;
				System.out.println(fullAddress);

				System.out.println("�q�L�a�}����");

				String usrid = req.getParameter("usrid").trim();
				String usridReg = "^[(a-zA-Z0-9)]{6,10}$";
				if (usrid == null || usrid.trim().length() == 0) {
					errorMsgs.add("�ϥΪ̦W�ٽФŪť�");
				} else if (!usrid.trim().matches(usridReg)) {
					errorMsgs.add("�ϥΪ̦W�ٽп�J���B�^��r��, �B���ץ����b6��10����");
				}

				System.out.println("�q�L�ϥΪ̦W������");

				String psw = req.getParameter("psw").trim();
				String pswReg = "^[(a-zA-Z0-9)]{6,10}$";
				String confirmpsw = null;

				if (psw == null || psw.trim().length() == 0) {
					errorMsgs.add("�K�X �ФŪť�");
				} else if (!psw.trim().matches(pswReg)) {
					System.out.println(psw);
					errorMsgs.add("�K�X�u��O�j�p�g�^��r���B�Ʀr , �B���ץ��ݦb6��10����");
				} else {

					System.out.println("�q�L�K�X����");
					confirmpsw = req.getParameter("confirmpsw").trim();
					String confirmpswReg = "^[(a-zA-Z0-9)]{6,10}$";
					System.out.println(confirmpsw);
					if (confirmpsw == null || confirmpsw.trim().length() == 0) {
						errorMsgs.add("�T�{�K�X �ФŪť�");

					} else if (!confirmpsw.trim().matches(psw)) {
						errorMsgs.add("�P�Ĥ@����J�K�X���۲�,�Э��s��J");
					} else if (!confirmpsw.trim().matches(confirmpswReg)) {
						errorMsgs.add("�K�X�u��O�^��r���B�Ʀr , �B���ץ��ݦb6��10����");
					}
					System.out.println("�q�L�T�{�K�X����");
				}

				MemVO memVO = new MemVO();
				memVO.setM_accno(usrid);
				memVO.setM_psw(confirmpsw);
				memVO.setM_gender(gender);
				memVO.setM_name(name);
				memVO.setM_mobile(mobile);
				memVO.setM_city(city);
				memVO.setM_zip(zipcode);
				memVO.setM_addr(address);
				memVO.setM_email(email);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("memVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memRegister.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				MemService memSvc = new MemService();

				memVO = memSvc.addMem(1, usrid, confirmpsw, name, gender, null, null, mobile, zipcode, city,
						fullAddress, email, null, null, 1, 1, 1, 0, 0);

				System.out.println("�w�I�sMemService�s�W�|��");
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/frontend/members/select_page_frontend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				System.out.println("�ǳ�forward");
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/members/memRegister.jsp");
				failureView.forward(req, res);
			}
		}

		if ("checkAccount".equals(action)) {
			System.out.println("checkingAccount");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = res.getWriter();
			
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			try {
				System.out.println(req.getParameter("usrid"));
				String usrid = req.getParameter("usrid").trim();
				if (usrid == null || usrid.trim().length() == 0) {
					errorMsgs.add("�ϥΪ̦W�ٽФŪť�");

				}

				/*************************** 2.�}�l�j�M���,���b���O�_���� ********************************/

				MemService memSvc = new MemService();

				Set<String> usridSet = memSvc.getAllUsrId();

				System.out.println("usrid:" + usrid);
				if (usridSet.contains(usrid)) {

					out.print(true);

				} else {

					out.print(false);
				}
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
			}

		}

		if ("getAllMembers".equals(action)) {

			MemService memSvc = new MemService();
			List<MemVO> list = memSvc.getAll();
			PrintWriter out = res.getWriter();
			JSONArray array = new JSONArray(list);
			out.print(array);

		}

		if ("getOneByIdNameAccout".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = res.getWriter();
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String input = req.getParameter("input");

				System.out.println("input:" + input);
				String inputReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,20}$";
				if (input == null || input.trim().length() == 0) {
					errorMsgs.add("�п�J���j�M���|���b���B�W�٩νs��");
				} else if (!input.matches(inputReg)) {
					errorMsgs.add("��J�榡���~, �Э��s��J");
				}
				System.out.println("�q�L�j�M����");

				/*********************** 2.�}�l�j�M��� *************************/

				MemService memSvc = new MemService();
				List<MemVO> list = memSvc.findByPKNameAcc(input);

				for (MemVO memVO : list) {
					System.out.println("�b��:" + memVO.getM_accno());
					System.out.println("�s��:" + memVO.getMem_id());
					System.out.println("�W��:" + memVO.getM_name());
				}

				JSONArray array = new JSONArray(list);

				out.print(array);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println(e.getMessage());
			}

		}

	}
}




