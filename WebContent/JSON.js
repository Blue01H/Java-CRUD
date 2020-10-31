function Register() {
	var Name = document.getElementById("name").value;
	var Username = document.getElementById("username").value;
	var Telf = document.getElementById("telf").value;
	var Id = document.getElementById("id").value;
	var Password = document.getElementById("password").value;
	
	var _json = {
			"name1" : Name,
			"username1" : Username,
			"telf1" : Telf,
			"id1" : Id,
			"password1" : Password
	}
	
	fetch("./Register",{
		method: "POST",
		headers: {
			"Content-Type":"application/json"
		},
		body: JSON.stringify(_json)
	})	
	.then(response => response.json())
	.catch(error => console.log(error))
	.then(response => JSONResponse(response))
}

function JSONResponse(handle) {
	if (handle.handle == "200") {
		alert(handle.message)
		window.location.href = "index.html"
	} 
}

function JSONLoginResponse(handle) {
	if (handle.handle == "200") {
		window.location.href = "Session.html"
		alert(handle.message)
	}
	if (handle.handle == "300") {
		alert(handle.message) 
	}
}

function Login() {
	var Username = document.getElementById("username").value;
	var Password = document.getElementById("password").value;
	
	var Login = {
			"username2" : Username,
			"password2" : Password
	}
	
	fetch("./LoginServlet",{
		method: "POST",
		headers: {
			"Content-Type":"application/json"
		},
		body: JSON.stringify(Login)
	})
	.then(response => response.json())
	.catch(error => console.log(error))
	.then(response => JSONLoginResponse(response))
}

function userInfo() {
	var name = rs.getString("name");
	var username = rs.getString("username");
	var telf = rs.getString("telf");
	var id = rs.getString("id");
	
	var info = {
			"name" : name,
			"username" : username,
			"telf" : telf,
			"id" : id
	}
	
	fetch(".LoginServlet",{
		method: "POST",
		headers: {
			"Content-Type":"application/json"
		},
		body: JSON.stringify(info)
	})
}