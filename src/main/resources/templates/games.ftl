<html>
<head></head>
<body>
<table>
<#list games as games>
<tr>
<td>${games.username}</td><td>${games.email}</td>
</tr>
</#list>
</table>
</body>
</html>