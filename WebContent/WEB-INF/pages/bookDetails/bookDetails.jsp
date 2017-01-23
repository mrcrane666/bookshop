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
<div style="height: 500px;">
	<table>
			<tr>
				<td>${book.title}</td>
			</tr>
			<tr>
				<td>${book.author}</td>
			</tr>
			<tr>
				<td>${book.price}</td>
			</tr>
			<tr>
				<td>${book.isbn}</td>
			</tr>
			<tr>
				<td>${book.genre}</td>
			</tr>
			<tr>
				<td>${book.description}</td>
			</tr>	
	</table>
	<br>
	<img style="height: 150px; width: 100px" src="${book.linkToImg}">
</div>