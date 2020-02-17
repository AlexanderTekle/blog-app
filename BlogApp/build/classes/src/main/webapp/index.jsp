<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="/styling/styles.css" />
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  
  <meta name="google-signin-scope" content="profile email">
  <!-- FIX THIS LINE --><meta name="google-signin-client_id" content="141574783815-8g1umfjckms4926q9c9nn1d3klhreb5i.apps.googleusercontent.com">
  <script> var userEmail = null; </script>
  <script> var userName = "Anonymous";</script>
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
  	<div id="signinButton" class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
  	<script>
  		if(userEmail != null){
  			document.getElementById("signinButton").style.display = "none";
  			welcomeText();
  		}
  	</script>
    <script>
      function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        userEmail = profile.getEmail();
        userName = profile.getName();
        welcomeText();
      }
    </script>
    <script>
    	function welcomeText()
    	{
    		if(userEmail != null)
    			document.getElementById("welcome").innerHTML = "Welcome, " + userName + "!";
    	}
    </script>
    
    <!-- subscribe to emails button -->
    <button onClick="unsubscribe()" id="unsubButton"> unsubscribe </button>
    <button onClick="subscribe()" id="subButton"> subscribe </button>
    <!-- Check if user is signed in and subscibed to email, adjust buttons accordingly -->
    <script>
    	function unsubscribe(){
    		if(userEmail != null)
    		{
    			//remove email from datastore
    		}
    	}
    	
    	function subscribe(){
    		if(userEmail != null)
    		{
    			//add email to datastore
    		}
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
		String[] titles;
	    String[] contents;
	    String[] times;
        
        titles = (String[])request.getAttribute("titles");
        contents = (String[])request.getAttribute("contents");
        times = (String[])request.getAttribute("times");
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
		  <% for (int i = titles.length-1; i > titles.length - 4 && i >=0; i--) { %>
		        <h3><%= titles[i] %></h3>
			        <p><%= times[i] %></p> 
		        <p><%= contents[i] %></p>
		  <% } %>
		<% } %>
		
		<% if (request.getAttribute("showallposts") != null) { %>
			<% boolean allposts = (Boolean)request.getAttribute("showallposts"); %>
			 <% if (allposts && titles != null && contents != null) { %>
				<% int i = titles.length-4; %>
				<% int size = (Integer) request.getAttribute("length"); %>
				<%while (i>=0) { %>
			        <h3><%= titles[i] %></h3>
			        <p><%= times[i] %></p> 
			        <p><%= contents[i] %></p> 
			        
					<% i--; %>
				<% } %>
			<% } %>
		<% } %>
		 <form method = "GET" action="/showallposts">
			 <input type="submit" value = "Show All Posts" class="btn btn-outline-primary">
		 </form>
		 
	 </div>
  </div> 
</body>

</html>
