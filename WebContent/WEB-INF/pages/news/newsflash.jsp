<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib
	uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"
	prefix="portlet-client-model"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<portlet-client-model:init>
	<portlet-client-model:require module="ibm.portal.xml.*" />
	<portlet-client-model:require module="ibm.portal.portlet.*" />
</portlet-client-model:init>
<portlet:defineObjects />

<portlet:resourceURL var="getNews" id="getNews">
</portlet:resourceURL>

<script type="text/javascript"
	src='<c:url value="/res/js/general/jquery-3.1.1.min.js" />'>
	
</script>



	<div style="margin-left: 5px; font-family: ${fontFamily}">
		<h3 id="newNews">
			BBC-News: "${news}" <a href='${link}' target="_blank">read more...</a>
		</h3>
		<!-- Logout url überarbeiten <a href="${logoutUrl}">Logout</a> -->
	</div>
<script type="text/javascript">
	window.onload = function() {
		setInterval (callAjax, 10000);
	}
	function callAjax() {
		$
				.ajax({
					url : '${getNews}',
					type : 'POST',
					datatype : "json",
					success : function(data) {
						var nextNews = JSON.parse(data);
						$("#newNews")
								.html(
										'BBC-News: "'
												+ nextNews.news
												+ '" <a href="'+nextNews.link+'">read more...</a>');
					}

				});

		
	}
</script>


