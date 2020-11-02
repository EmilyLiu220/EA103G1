<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.meeting.model.*"%>
<%@ page import="com.reg_inf.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.wel_record.model.*"%>
<%@ page import=" java.text.*,  java.util.*"  %>


<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	MeetingVO meetingVO = (MeetingVO) request.getAttribute("meetingVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����

	WelRecordVO welRecordVO = (WelRecordVO) request.getAttribute("welRecordVO");

	MemVO memVO = (MemVO) session.getAttribute("memVO");
	
%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 Date current = new Date();
%>
<html>
<head>

<title>���u��� - listOneMeeting.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
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
	width: 900px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<span id="time"></span>


<table id="table-1">
	<tr><td>
		 <h3>�����|��� - ListOneMeeting.jsp</h3>
		 <h4><a href="/EA103G1/frontend/meeting/listAllMeeting_front.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�����|�W��</th>
		<td>${meetingVO.mt_id}</td>
	</tr>	
		
	<tr>
		<th>�|��ID</th>
		<td>${meetingVO.mem_id}</td>
	</tr>
	
	<tr>
		<th>�H�ƤW��</th>
		<td>${meetingVO.max_num}</td>
	</tr>
		
	<tr>
		<th>�H�ƤU��</th>
		<td>${meetingVO.min_num}</td>
	</tr>
	
	<tr>
		<th>�a�I</th>
		<td>${meetingVO.mt_place}</td>
	</tr>
	
	<tr>
		<th>���W�O</th>
		<td>${meetingVO.ri_fee}</td>
	</tr>
	
	<tr>
		<th>����²��</th>
		<td>${meetingVO.mt_detail}</td>
	</tr>
	
	<tr>
		<th>���W�}�l�ɶ�</th>
		<td>${meetingVO.mt_start_time}</td> 
	</tr>
	
	<tr>
		<th>���W�I��ɶ�</th>
		<td>${meetingVO.mt_end_time}</td> 
	</tr>
	
	<tr>
		<th>���ʮɶ�</th>
		<td>${meetingVO.mt_time}</td>
	</tr>
	

	<c:if test="${meetingVO.mt_num != null}">
	<tr>
		<th>�ثe�H��</th>
		<td>${meetingVO.mt_num}</td>
	</tr>
	</c:if>
	<tr>
		<th>���W</th>
		<td>
			    
<%-- �H�U���ծɶ� --%>
<fmt:formatDate value="<%=current %>" pattern="yyyy-MM-dd" var="now_time" />
<fmt:formatDate value="${meetingVO.mt_start_time}" pattern="yyyy-MM-dd" var="mt_start_time" />
<fmt:formatDate value="${meetingVO.mt_time}" pattern="yyyy-MM-dd" var="mt_time" />
<fmt:formatDate value="${meetingVO.mt_end_time}" pattern="yyyy-MM-dd" var="mt_end_time" />
<c:set var="mt_status" scope="session" value="${meetingVO.mt_status}"/>


<c:choose> 
  <c:when test="${mt_status == 2}">
            ���ʤw����
    </c:when>
    <c:when test="${now_time gt mt_time}">
             ���ʤw����
    </c:when>
     <c:when test="${now_time gt mt_end_time}">
    <font color=red>���W�I��</font>
    </c:when>
    <c:when test="${mt_start_time gt now_time}">
              �Y�N�}��
    </c:when>
    <c:otherwise>
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/meeting/meeting.do" style="margin-bottom: 0px;">
		<input type="submit" value="���W">
        <input type="hidden" name="mt_no" value="${meetingVO.mt_no}"> 
        <input type="hidden" name="action"	value="getOne_For_Reg"></FORM>
    </c:otherwise>
</c:choose>   
</td>		
	</tr>
	


</table>




</body>
</html>