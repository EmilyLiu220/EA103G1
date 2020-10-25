package tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.Clob;
import java.sql.SQLException;

public class YclTools {

	// ======================================================================
	// �N���|�̪�����ରInpuetSteam(JDBC���ĤT�ؽd��)
	// �ϥΪ��O��Ƭy�A���F�קK�w��譱�޽u�W������A��ƶq�j�ɥγo�ط|����n�A�N��Ƥ��y�g�JDB�Ӥ��O�@���j�q�g�J(>=100mb�ɺ��ƶq�j)
	// ======================================================================
	public static InputStream getUploadFileStream(String path) throws IOException {
		System.out.println(path);
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// ======================================================================
	// �N���|�̪�����ରByte�}�C(JDBC���ĤG�ؽd��)
	// �ϥΪ��OJDBC��ͪ���k�A�Bbyte[]�qJAVA 1.0�}�l�N���A�����U�aDB�t�Ӵ��Ѫ�driver�v�T
	// ����A�X�Φb��ƶq�p�ɨϥ�
	// ======================================================================
	public static byte[] getUploadFileByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file); // (L)�C����
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // ����Ƭy�|��write���줸��Ʀs��@�Ӥ��ت�byte[]
		byte[] buffer = new byte[8192]; // 8kb
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		fis.close();
		return baos.toByteArray(); // toByteArray()�i�H���ڭ̨��o�o�Ӹ�Ƭy���ت�byte[]
	}

	// ======================================================================
	// �N���o��Part�ରbyte�}�C
	// ======================================================================
	public static byte[] transPartToByteArray(Part filePart) throws IOException {

		InputStream fileContent = filePart.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fileContent.available()]; // �w�İϤj�p8kb
		int i;
		while ((i = fileContent.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		fileContent.close();

		return baos.toByteArray();

	}

	// ======================================================================
	// �P�_�W���ɬO�_����
	// ======================================================================
	public static boolean isUploadFile(Part filePart) {
		String fileName = (filePart.getSubmittedFileName()).trim();
		if (fileName == null || fileName.length() == 0) {
			return false;
		} else {
			return true;
		}

	}

	// ========================================================================
	// �Ndb��Ū�X��byte[]�ରStream�çe�{�󭶭�
	// ========================================================================
	public static void readByteArrayFromDB(HttpServletRequest req, HttpServletResponse res, byte[] dbData)
			throws IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dbData);
		BufferedInputStream in = new BufferedInputStream(byteArrayInputStream);
		byte[] buf = new byte[10 * 1024]; // 4K buffer
		int len;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		in.close();
	}

	// ========================================================================
	// �Ndb��Ū�X��Clob�ରString�A�å[�Wtextarea������Ÿ�&#13;&#10;
	// ========================================================================
	public static String readClobToString(Clob clob) throws SQLException, IOException {

		String result = "";

		if (clob != null) {

			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			String str;

			while ((str = br.readLine()) != null) {
				sb.append(str);
				sb.append("&#13;&#10;"); //&#13;&#10;
//				sb.append("\n");
				// https://stackoverflow.com/questions/14534767/how-to-append-a-newline-to-stringbuilder
				// sb.append(System.getProperty("line.separator"));
			}

			br.close();
			result = sb.toString();
		}

		return result;

	}

	// ========================================================================
	// �Ndb��Ū�X��VARCHAR2�ରString�A�ñN����Ÿ��ഫ��<br>
	// ========================================================================
	public static String readStringAppendBr(String text) {

		String result = "";
		if (text != null && text.length() != 0) {

			StringBuilder sb = new StringBuilder();
			String[] resultArr = text.split("\n");

			for (String str : resultArr) {
				sb.append(str);
				sb.append("<br>");
			}

			result = sb.toString();
		}

		return result;

	}

}