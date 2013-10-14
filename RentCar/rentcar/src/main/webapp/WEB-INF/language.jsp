<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="./js/rentcar.js">
</script>
<table class="language">
	<tr>
		<td>
			<div class="button" onclick="post_to_url_params('language.controller',{'language':'ru'},'POST')">RU</div>
		</td>
		<td>
			<div class="button" onclick="post_to_url_params('language.controller',{'language':'en'},'POST')">EN</div>
		</td>
	</tr>
</table>