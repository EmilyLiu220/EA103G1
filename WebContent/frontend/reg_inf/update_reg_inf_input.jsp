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
 table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
    text-align: center;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  h4 {
  padding: 5px;
    text-align: center;
  }
  body {
    color: #666666;
    background: #fefefe;
    font-family: "Rubik", sans-serif;
    font-weight: 400;
    font-size: 14px;
    line-height: 20px;
    letter-spacing: 0.05em;
}
table#table-1 {
	background-color: #C4E1E1;
    border: 2px #ECF5FF;
    text-align: center;
    
  }
  table#table-1 h4 {
    color: #613030;
    display: block;
    margin-bottom: 10px;
  }
  
  h4 {
    color: blue;
    display: inline;
  }
  h3 {
  color: blue;
    display: inline;
</style>

</head>
<body bgcolor='white'>
<!-- header -->
	<header>
		<%@include file="/frontend/bar/frontBarTop.jsp"%>

		    <!-- header-banner -->
    <div id="header-banner">
        <div class="banner-content single-page text-center">
            <div class="banner-border">
                <div class="banner-info" id="banner-info">
                    <h1>Meeting</h1>
                </div><!-- / banner-info -->
            </div><!-- / banner-border -->
        </div><!-- / banner-content -->
    </div>
    <!-- / header-banner -->
	</header>

<table id="table-1">
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
<!-- footer -->
	<%@include file="/frontend/bar/frontBarFooter.jsp"%>
	<!-- / footer -->
	<table id="table-1">
<td></td>
</table>
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