<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meeting.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.wel_record.model.*"%>
<%@ page import="java.util.*"%>

<%
	MeetingVO meetingVO = (MeetingVO) request.getAttribute("meetingVO");
%>
<%= meetingVO==null %>

<%
	WelRecordVO welRecordVO = (WelRecordVO) request.getAttribute("welRecordVO");

	MemVO memVO = (MemVO) session.getAttribute("memVO");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



</head>


<body>
<table id="table-1">
	<tr><td>
		 <h3>�����|�s�W - addMeeting.jsp</h3></td><td>
		 <h4><a href="/EA103G1/frontend/meeting/listAllMeeting_front.jsp">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="meeting.do" name="form1" enctype="multipart/form-data">
<table>

    <tr>
		<td>�|��ID:</td>
		<td>
	     <input type="hidden" name="mem_id" value="${sessionScope.memVO.mem_id}">
	     <c:if test="${not empty sessionScope.memVO.mem_id}">
					 ${sessionScope.memVO.mem_id}
							
		 </c:if>
		 </td>
	</tr>

	<tr>
		<td>�H�ƤW��:</td>
		<td><input type="TEXT" name="max_num" size="45" 
			 value="<%= (meetingVO==null)? "60" : meetingVO.getMax_num()%>" /></td>
	</tr>

	<tr>
		<td>�H�ƤU��:</td>
		<td><input type="TEXT" name="min_num" size="45"
			 value="<%= (meetingVO==null)? "10" : meetingVO.getMin_num()%>" /></td>
	</tr>
	
    <tr>
		<td>�a�I:</td>
		<td><input type="TEXT" name="mt_place" size="45"
			 value="<%= (meetingVO==null)? "���c" : meetingVO.getMt_place()%>" /></td>
	</tr>
	
	<tr>
		<td>���W�O:</td>
		<td><input type="TEXT" name="ri_fee" size="45"
			 value="<%= (meetingVO==null)? "1000" : meetingVO.getRi_fee()%>" /></td>
	</tr>
	
	<tr>
		<td>����²��:</td>
		<td><textarea name="mt_detail"  
			 value="<%= (meetingVO==null)? "" : meetingVO.getMt_detail()%>" /></textarea></td>
	</tr>
	
	<tr>
		<td>���W�}�l�ɶ�:</td>
		<td><input type="text" name="mt_start_time" id="start_date" onfocus="this.blur()" 
			 value="<%= (meetingVO==null)? "" : meetingVO.getMt_start_time()%>" /></td>
	</tr>
	
	<tr>
		<td>���W�I��ɶ�:</td>
		<td><input type="TEXT" name="mt_end_time" id="end_date" onfocus="this.blur()"
			 value="<%= (meetingVO==null)? "" : meetingVO.getMt_end_time()%>" /></td>
	</tr>
	
	<tr>
		<td>���ʮɶ�:</td>
		<td><input type="TEXT" name="mt_time" id="mt_date" onfocus="this.blur()"
			 value="<%= (meetingVO==null)? "" : meetingVO.getMt_time()%>" /></td>
	</tr>
	
	<tr>
		<td>���ʦW��:</td>
		<td><input type="TEXT" name="mt_id" size="45"
			 value="<%= (meetingVO==null)? "�n��" : meetingVO.getMt_id()%>" /></td>
	</tr>
	

    <tr>
		<td>�Ϥ�:</td>
		<td><input type="file" accept=".png, .jpg, .jpeg" name="mt_pic" size="45"
			 value="<%= (meetingVO==null)? "0" : meetingVO.getMt_pic()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
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
 <script>

$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d H:i:s',
	  step: 1,
	  
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:true
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d H:i:s',
	  step: 1,
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:true
	 });
	 
	 $('#mt_date').datetimepicker({
		  format:'Y-m-d H:i:s',
		  step: 1,
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#end_date').val()?$('#end_date').val():false
		   })
		  },
		  timepicker:true
		 });
});
</script>



</html>