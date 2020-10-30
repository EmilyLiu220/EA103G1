package com.painter.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.lv.model.LvService;
import com.lv.model.LvVO;
import com.mem.model.MemVO;
import com.painter.model.PainterService;
import com.painter.model.PainterVO;
import com.painter_tag.model.PainterTagService;
import com.painter_tag_map.model.PainterTagMapService;
import com.painter_tag_map.model.PainterTagMapVO;

import tools.HeadphotoTool;
import tools.YclTools;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PainterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		
		if ("showPic".contentEquals(action)) {

			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();
			try {
				Integer ptr_no = Integer.valueOf(req.getParameter("ptr_no").trim());
				PainterService painterSvc = new PainterService();
				YclTools.readByteArrayFromDB(req, res, painterSvc.getPicByPtrNo(ptr_no));
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/backend/img/null.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}

		} else if("showCreatorPhoto".contentEquals(action)){
			String sid = ((String)req.getParameter("sid")).trim();
			HeadphotoTool.printHeadphotoByMemId(req, res, sid);
			
		}else {
			doPost(req, res);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// ======================================================================
		// �s�W
		// ======================================================================
		if ("insert".contentEquals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
				String mem_id = memVO.getMem_id(); // �|���s��

				String ptr_nm = req.getParameter("ptr_nm"); // �@�~�W��
				String intro = req.getParameter("intro"); // �@�~����
				Integer priv_stat = Integer.valueOf(req.getParameter("priv_stat")); // ���p�v
				String tag_desc = (String) req.getParameter("tag_desc"); // �@�~tag

				Part filePart = req.getPart("imgPath");

				if (ptr_nm == null || (ptr_nm.trim()).length() == 0) {
					errorMsgs.add("�п�J�@�~�W��");
				}

				boolean isUploadFile = YclTools.isUploadFile(filePart);
				if (!isUploadFile) {
					errorMsgs.add("�ФW�ǧ@�~�Ϥ�");
				}

				byte[] fileByteArray = YclTools.transPartToByteArray(filePart);

				/*************************** 2.�}�l�s�W��� **********************/
				PainterVO painterVO = new PainterVO();
				painterVO.setMem_id(mem_id);
				painterVO.setPtr_nm(ptr_nm);
				painterVO.setIntro(intro);
				painterVO.setPic(fileByteArray);
				painterVO.setPriv_stat(priv_stat);
				painterVO.setPtr_stat(1); // �w�]�@�~���A��1:���`
				painterVO.setLike_cnt(0);
				painterVO.setCol_cnt(0);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("painterVO", painterVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/painter/listAllPainter.jsp");
					failureView.forward(req, res);
					return;
				}

				System.out.println("====[PainterServlet]�}�l�s�W�@�~ ====");
				PainterService painterSvc = new PainterService();
				painterSvc.insert(painterVO, tag_desc);

				/*************************** 3.�s�W�����ɦ^�d�߭� ***********/
				System.out.println("====[PainterServlet]�s�W�@�~�����ɦ^�ӤH�@�~�� ====");
//				RequestDispatcher successView = req.getRequestDispatcher("/frontend/painter/listAllPainter.jsp");
//				successView.forward(req, res);
				res.sendRedirect(req.getContextPath() + "/frontend/painter/listAllPainter.jsp");
				return;

			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				System.out.println("���~�T��:" + e.fillInStackTrace().getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/painter/listAllPainter.jsp");
				failureView.forward(req, res);
			}
		}

		// ======================================================================
		// �R��
		// ======================================================================
		if ("delete".contentEquals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			Integer ptr_no = Integer.parseInt(req.getParameter("ptr_no")); // �@�~�N��

			/*************************** 2.�}�l�R����� **********************/
			System.out.println("====[PainterServlet]�}�l�R���@�~====");
			PainterService painterSvc = new PainterService();
			painterSvc.delete(ptr_no, 2); // 2:�ѳЧ@�̧R��

			/*************************** 3.�s�W�����ɦ^�ӤH�� ***********/
			System.out.println("====[PainterServlet]�R���@�~�����A�ɦ^�ӤH�@�~��====");
			res.sendRedirect(req.getContextPath() + "/frontend/painter/listAllPainter.jsp");
		}

		// ======================================================================
		// �ק�
		// ======================================================================
		if ("update".contentEquals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer ptr_no = Integer.valueOf(req.getParameter("ptr_no")); // �@�~�s��

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String ptr_nm = req.getParameter("ptr_nm"); // �@�~�W��
				String intro = req.getParameter("intro"); // �@�~����
				Integer priv_stat = Integer.valueOf(req.getParameter("priv_stat")); // ���p�v
				String tag_desc = (String) req.getParameter("tag_desc"); // �@�~tag

				if (ptr_nm == null || (ptr_nm.trim()).length() == 0) {
					errorMsgs.add("�п�J�@�~�W��");
				}

				Part filePart = req.getPart("imgPath");
				boolean isUploadFile = YclTools.isUploadFile(filePart);

				/*************************** 2.�}�l�ק��� **********************/
				System.out.println("====[PainterServlet]�}�l�ק�@�~====");
				byte[] fileByteArray = null;
				if (isUploadFile) {
					fileByteArray = YclTools.transPartToByteArray(filePart);
				} else {
					PainterService painterSvc = new PainterService();
					PainterVO painterVO = painterSvc.getOnePainter(ptr_no);
					fileByteArray = painterVO.getPic();
				}

				PainterVO painterVO = new PainterVO();
				painterVO.setPtr_no(ptr_no);
				painterVO.setPtr_nm(ptr_nm);
				painterVO.setIntro(intro);
				painterVO.setPic(fileByteArray);
				painterVO.setPriv_stat(priv_stat);
				painterVO.setPtr_stat(1); // �w�]�@�~���A��1:���`
				painterVO.setLike_cnt(0);
				painterVO.setCol_cnt(0);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("painterVO", painterVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/painter/onePainter.jsp?ptr_no" + ptr_no);
					failureView.forward(req, res);
					return;
				}

				PainterService painterSvc = new PainterService();
				painterSvc.update(painterVO, tag_desc);

				/*************************** 3.�s�W�����ɦ^�d�߭� ***********/
				System.out.println("====[PainterServlet]�ק�@�~����====");
				res.sendRedirect(req.getContextPath() + "/frontend/painter/onePainter.jsp?ptr_no=" + ptr_no);
				return;

			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				System.out.println("���~�T��:" + e.fillInStackTrace().getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/painter/onePainter.jsp?ptr_no=" + ptr_no);
				failureView.forward(req, res);
			}
		}

	}

}
