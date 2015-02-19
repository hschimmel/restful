<html>
<body>
	<h1>Harryt's RESTful Web Application!</h1>
	<h2>Store new name</h2>
	<form method="post" action="rest/names" target="resultFrame">
		<label for="name">Name</label> <input type="text" id="name"
			name="name" /> <input type="submit" value="add" />
	</form>
	<h2>Result</h2>
	<iframe name="resultFrame" src="rest/names" width="640px" height="480px"></iframe>
</body>
</html>
