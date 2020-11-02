<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reg_inf.model.*"%>
<%@ page import="com.meeting.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.wel_record.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%
    String mt_no = (String) request.getParameter("mt_no"); 
    Reg_infService reg_infSvc = new Reg_infService();
    List<Reg_infVO> list = reg_infSvc.getReg_inf_mt_no(mt_no);
    pageContext.setAttribute("list",list);   

    
    
    MeetingService meetingSvc = new MeetingService();
    MeetingVO meetingVO = meetingSvc.getOneMeeting("mt_no");
    pageContext.setAttribute("meetingSvc",meetingSvc);

%>


<html>
<head>
<title>���W��M�� -listAllReg_inf_mt_no.jsp</title>

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
  }
</style>

</head>
<body onload="checkoutAmount();" bgcolor='white'>
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
	<!-- / header -->

<table id="table-1">
	<tr><td>
		 <h3>���W��M�� -listAllReg_inf_mt_no.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/meeting/listAllMeeting_back.jsp">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���A</th>
		<th>���W��ID</th>
		<th>�����|�W��</th>
		<th>���W�H��</th>
		<th>�O��</th>
		<th>�h�O</th>		
	</tr>
		
	<%@ include file="page1.file" %> 
	<c:forEach var="reg_infVO" items="${list}"   begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

		<tr>
		    <td>	    
<c:set var="ri_status" scope="session" value="${reg_infVO.ri_status}"/>
<c:choose>
    <c:when test="${ri_status == 1}">
            ���W���\
    </c:when>
    <c:when test="${ri_status == 2}">
            �w�h�O
    </c:when>
    <c:when test="${ri_status == 3}">
            �w�����A�ݰh�O
    </c:when>
    <c:otherwise>
           ���W���\
    </c:otherwise>
</c:choose>   
</td>		    
			<td>${reg_infVO.ri_id}</td>
			<td>${meetingSvc.getOneMeeting(reg_infVO.mt_no).mt_id}</td>
		
			<td>${reg_infVO.ri_qty}</td>
			<td>${meetingSvc.getOneMeeting(reg_infVO.mt_no).ri_fee}</td>
			
			
	<td>
	<c:if test="${reg_infVO.ri_status == 1}">
	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/reg_inf/reg_inf.do" style="margin-bottom: 0px;">
			     
			     <input type="hidden" name="ri_id"  value="${reg_infVO.ri_id}">
			     <input type="hidden" name="mt_no"  value="${reg_infVO.mt_no}">
			     <input type="hidden" name="ri_qty" id="ri_qty" value="${reg_infVO.ri_qty}">
			     <input type="hidden" name="ri_fee" id="ri_fee" value="${meetingSvc.getOneMeeting(reg_infVO.mt_no).ri_fee}" >
			     <input type="hidden" name="totalAmount" id="amount2" >
			     <input type="hidden" name="mem_id"  value="${reg_infVO.mem_id}">
			     <input type="hidden" name="action"	value="cancel_back">
			     <input type="submit" value="�h�O">
			     </FORM>
		</c:if>
		</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
	<table id="table-1">
<td></td>
</table>
</body>
<script>


function checkoutAmount()
{
/*�p���`����*/

var ri_qty=document.getElementById("ri_qty").value;
var ri_fee=document.getElementById("ri_fee").value;
console.log("======================")
document.getElementById("amount2").value=ri_qty*ri_fee;
}
</script>
</html>