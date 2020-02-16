<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="/styling/styles.css" />
  
  <meta name="google-signin-scope" content="profile email">
  <!-- FIX THIS LINE --><meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
  <script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>
  <div class="container">
    <!-- Content here -->
    <h1 id = "title">Bernie Blog</h1>
    <img id = "bernie" src="/images/bernie.jpg" />
<!--  <form>
	    <div class="form-group">
	      <label for="exampleInputEmail1">Email address</label>
	      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
	    </div>
	    <div class="form-group">
	      <label for="exampleInputPassword1">Password</label>
	      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
	    </div>
	    <div class="form-check">
	    </div>
	    <button type="submit" class="btn btn-primary">Submit</button>
  	</form>
-->
	<h3 id="welcome">Welcome! Please sign in!</h3>
  	<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
    <script>
      function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        welcomeText(profile);
      }
    </script>
    <script>
    	function welcomeText(profile)
    	{
    		document.getElementById("welcome").innerHTML = "Welcome, " + profile.getName() + "!";
    	}
    </script>
    
      <br>
      <br>
	 <form method="POST" action="/printemail">
	 	<h3 id ="blogposttitle">Submit a blog post!</h3>
	   <div class="form-group">
	     <label for="exampleBlogTitle1">Post Title</label>
	     <input class="form-control form-control-lg" type="text" placeholder="Title" name = "title">
	     <!--<input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">-->
	     <label for="exampleBlogContent1">Post Content</label>
	  <textarea class="form-control" id="exampleFormControlTextarea1" placeholder="Content"  name = "content" rows="6"></textarea>
	   </div>
	
	
	   <button type="submit" class="btn btn-primary">Submit</button>
	 </form>
	 
	 <br>
	 <br>
    <%
        //Actor actor = new Actor();
		String[] titles = new String[3];
	    String[] contents = new String[3];
        
        titles = (String[])request.getAttribute("titles");
        contents = (String[])request.getAttribute("contents");
        int length;
        if (titles != null) {
	        if (titles.length > 3)
	        	length = 3;
	        else 
	        	length = titles.length;
        }

    %>
	 <div id = "blogposts">
	 	<%if (titles != null && contents != null) { %>
		  <% for (int i = 0; i < 3; ++i) { %>
		        <h3><%= titles[i] %></h3>
		        <p><%= contents[i] %></p>
		  <% } %>
		<% } %>
	 </div>
  </div>
  
</body>

</html>
