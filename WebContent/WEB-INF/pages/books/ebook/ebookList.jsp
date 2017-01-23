<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@taglib
	uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model"
	prefix="portlet-client-model"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<portlet-client-model:init>
	<portlet-client-model:require module="ibm.portal.xml.*" />
	<portlet-client-model:require module="ibm.portal.portlet.*" />
</portlet-client-model:init>
<portlet:defineObjects />

<link href="<c:url value="/res/css/books/ebook/ebookList.css" />"
	rel="stylesheet">
<div style="height: 500px">
	<table class="tbl">
		<tr>
			<th class="tbl">Author</th>
			<th class="tbl">Title</th>
			<th class="tbl">Price</th>
			<th class="tbl">ISBN</th>
		</tr>
		<c:forEach items="${ebookList}" var="ebook">
			<tr>
				<td class="tbl">${ebook.title}</td>
				<td class="tbl">${ebook.author}</td>
				<td class="tbl">${ebook.price}</td>
				<td class="tbl">${ebook.isbn}</td>
				<td><a href="${ebook.link}"><img class="readImg" alt="Read"
						src="<c:url value="/res/img/books/ebooks/download.png"/>"></a></td>
			</tr>
		</c:forEach>
	</table>
	<a
		href='<portlet:renderURL><portlet:param name="view" value="addEbook"/></portlet:renderURL>'><button
			class="btn">Add new E-Book</button></a> <a
		href='<portlet:renderURL> <portlet:param name="view" value="deleteEbooks"/> </portlet:renderURL>'><button
			class="btn">Delete E-Books</button></a> <a
		href='<portlet:renderURL></portlet:renderURL>'><button class="btn">Back
			to Home</button></a>
</div>
