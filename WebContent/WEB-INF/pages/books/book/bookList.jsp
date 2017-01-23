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

<link href="<c:url value="/res/css/books/book/bookList.css" />"
	rel="stylesheet">
<div style="height: ${momHeight}px; color: ${user_color}">
	<fieldset style="margin-bottom: 3px;">
		<div >
			<form action='<portlet:actionURL><portlet:param name="action" value="searchForBook"></portlet:param> </portlet:actionURL>'>
				<label for="searchParameter"><b>Search: </b></label><input
					type="text" id="searchParameter" name="searchParameter"/> <label for="searchBy">
					by </label><select id="searchBy" name="searchBy">
					<option value="isbn" selected="selected">ISBN</option>
					<option value="title">Title</option>
					<option value="author">Author</option>
				</select>
				<button>Search</button>
			</form>
		</div>
	</fieldset>
	<table class="tbl">
		<tr class="tbl">
			<th class="tbl">Author</th>
			<th class="tbl">Title</th>
			<th class="tbl">Price</th>
			<th class="tbl">ISBN</th>
		</tr>
		<c:forEach items="${bookList}" var="book">
			<tr>
				<td class="tbl">${book.title}</td>
				<td class="tbl">${book.author}</td>
				<td class="tbl">${book.price}</td>
				<td class="tbl"><a
					href='<portlet:actionURL><portlet:param name="action" value="setDetails"></portlet:param><portlet:param name="isbn" value="${book.isbn}"></portlet:param></portlet:actionURL>'>${book.isbn}</a></td>
			</tr>
		</c:forEach>
	</table>
	<a
		href='<portlet:renderURL> <portlet:param name="view" value="addBook"/> </portlet:renderURL>'><button
			class="btn">Add new Book</button></a> <a
		href='<portlet:actionURL> <portlet:param name="action" value="goToDelBooks"/> </portlet:actionURL>'><button
			class="btn">Delete Books</button></a> <a
		href='<portlet:renderURL></portlet:renderURL>'><button class="btn">Back
			to Home</button></a>
</div>