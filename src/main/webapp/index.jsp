<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 这里的url才是真正的主页,利用main.jsp调转过去 -->
<c:redirect url="${pageContext.request.contextPath}/index.do">
</c:redirect>

</html>
