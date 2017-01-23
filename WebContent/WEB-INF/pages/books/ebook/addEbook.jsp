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

<form
	action='<portlet:actionURL name="addNewEbook"><portlet:param name="action" value="addNewEbook"/></portlet:actionURL>'>
	<table>
		<tr>
			<td><label for="title">Title</label></td>
			<td><input type="text" id="title" name="title"></td>
		</tr>
		<tr>
			<td><label for="author">Author</label></td>
			<td><input type="text" id="author" name="author"></td>
		</tr>
		<tr>
			<td><label for="price">Price</label></td>
			<td><input type="text" id="price" name="price"></td>
		</tr>
		<tr>
			<td><label for="isbn">ISBN</label></td>
			<td><input type="text" id="isbn" name="isbn"></td>
		</tr>
		<tr>
			<td><label for="link">Link</label></td>
			<td><input type="text" id="link" name="link"></td>
		</tr>
	</table>
	<button type="submit">Add E-Book</button>
	<a
		href='<portlet:renderURL><portlet:param name="view" value="ebookList"/></portlet:renderURL>'><button>Return
			to E-Books</button></a>
</form>
