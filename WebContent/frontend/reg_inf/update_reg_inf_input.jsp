<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.reg_inf.model.*"%>

<%
    Reg_infVO reg_infVO = (Reg_infVO) request.getAttribute("reg_infVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�����|��ƭק� - update_reg_inf_input.jsp</title>

<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-2">
	<tr><td>
		 <h3>���W��ק� - update_reg_inf_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/frontend/meeting/listAllMeeting_front.jsp">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="reg_inf.do" name="form2">
<table>
    <tr>
		<td>���W��s��:<font color=red><b>*</b></font></td>
		<td><%=reg_infVO.getRi_id()%></td>
	</tr>


	<tr>
		<td>�����|�s��:<font color=red><b>*</b></font></td>	
		<td><%=reg_infVO.getMt_no()%></td>
	</tr>
	
	<tr>
		<td>�|��ID:<font color=red><b>*</b></font></td>
		<td><%=reg_infVO.getMem_id()%></td>
	</tr>

	<tr>
		<td>���W�H��:<font color=red><b>*</b></font></td>		
	    <td><%=reg_infVO.getRi_qty()%></td>
	</tr>
	
    <tr>
		<td>�Ƶ�:<font color=red><b>*</b></font></td>	
		<td><input type="TEXT" name="ri_note" size="45" value="<%=reg_infVO.getRi_note()%>" /></td>	 
	</tr>
	
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ri_id" value="<%=reg_infVO.getRi_id()%>">
<input type="hidden" name="mem_id" size="45" value="<%=reg_infVO.getMem_id()%>" />
<input type="hidden" name="ri_qty" size="45" value="<%=reg_infVO.getRi_qty()%>" />
<input type="hidden" name="mt_no" size="45" value="<%=reg_infVO.getMt_no()%>" />



<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


</html>