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
	action='<portlet:actionURL name="addNewBook"><portlet:param name="action" value="addNewBook"/></portlet:actionURL>'>
	<div id="detailsTbl">
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
		</table>
		<a onclick="moreDetails()">More Details</a><br>
	</div>
	<button type="submit">Add Book</button>

</form>
<a
	href='<portlet:renderURL><portlet:param name="view" value="bookList"/></portlet:renderURL>'><button
		style="margin-top: 5px;">Return to Books</button></a>
<script type="text/javascript">
	function moreDetails() {
		var table = document.getElementById("detailsTbl");
		table.innerHTML='<table>'
						+ '<tr>'
						+ '<td><label for="title">Title</label></td>'
						+ '<td><input type="text" id="title" name="title"></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td><label for="author">Author</label></td>'
						+ '<td><input type="text" id="author" name="author"></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td><label for="price">Price</label></td>'
						+ '<td><input type="text" id="price" name="price"></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td><label for="isbn">ISBN</label></td>'
						+ '<td><input type="text" id="isbn" name="isbn"></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td><label for="genre">Genre</label></td>'
						+ '<td><input type="text" id="genre" name="genre"></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td><label for="description">Description</label></td>'
						+ '<td><input type="text" id="description" name="description"></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td><label for="linkToImg">Link to Image</label></td>'
						+ '<td><input type="text" id="linkToImg" name="linkToImg"></td>'
						+ '</tr>' + '</table>'
						+ '<a onclick="lessDetails()">Less Details</a><br>';
	}

	function lessDetails() {
		var table = document.getElementById("detailsTbl");
		table.innerHTML='<table>' + '<tr>'
				+ '<td><label for="title">Title</label></td>'
				+ '<td><input type="text" id="title" name="title"></td>'
				+ '</tr>' + '<tr>'
				+ '<td><label for="author">Author</label></td>'
				+ '<td><input type="text" id="author" name="author"></td>'
				+ '</tr>' + '<tr>'
				+ '<td><label for="price">Price</label></td>'
				+ '<td><input type="text" id="price" name="price"></td>'
				+ '</tr>' + '<tr>' + '<td><label for="isbn">ISBN</label></td>'
				+ '<td><input type="text" id="isbn" name="isbn"></td>'
				+ '</tr>' + '</table>'
				+ '<a onclick="moreDetails()">More Details</a><br>';
	}
</script>