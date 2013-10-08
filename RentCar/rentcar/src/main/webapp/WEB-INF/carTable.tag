<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="mytags" uri="mytags.tld" %>
<%@ attribute name="mark" required="true"%>
<%@ attribute name="model" required="true"%>
<%@ attribute name="id" required="true"%>

<mytags:default var="" value="" id="carTable"/>

${mark} ${model} ${id}