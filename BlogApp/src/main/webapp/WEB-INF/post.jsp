<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="/styling/styles.css" />
 
</head>
<body>
  <div class="container">
  <br>
    <form method="POST" action="/printemail">
	 <h1 id ="blogposttitle">Submit a blog post!</h1>
	 <div class="form-group">
       <label for="exampleBlogTitle1">Post Title</label>
	   <input class="form-control form-control-lg" type="text" placeholder="Title" name = "title">
	   <!--<input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">-->
	   <label for="exampleBlogContent1">Post Content</label>
	   <textarea class="form-control" id="exampleFormControlTextarea1" placeholder="Content"  name = "content" rows="6"></textarea>
	   <textarea name="usernameHolder" id="usernameHolder"></textarea>
	   <script>
	   		const queryString = window.location.search;
	   		const urlParams = new URLSearchParams(queryString);
	   		var username = urlParams.get('username');
	   		document.getElementById("usernameHolder").style.display = "none";
	   		document.getElementById("usernameHolder").value = username;
	   </script>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
   </form>
  </div>
</body>
</html>