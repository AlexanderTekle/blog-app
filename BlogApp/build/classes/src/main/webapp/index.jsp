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
    
<div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/images/bernie2.jpg" class="d-block w-100" alt="A scary Bernie Sanders">
    </div>
    <div class="carousel-item">
      <img src="/images/bernie3.jpg" class="d-block w-100" alt="A confused Bernie Sanders">
    </div>
    <div class="carousel-item">
      <img src="/images/bernie4.jpg" class="d-block w-100" alt="A young Bernie Sanders">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleFade" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleFade" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
<br>
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
        var y = document.getElementById("postform");
	    y.style.display = "block"
        
      }
    </script>
    <script>
    	function welcomeText()
    	{
    		if(userEmail != null)  {
    			document.getElementById("welcome").innerHTML = "Welcome, " + userName + "!";
    		}
    	}
    </script>
    
    <br>
    
    <form method="GET" action="/subscription">
    	<h3>Join our mailing list!</h3>
    	<input class="form-control form-control-lg" type="text" placeholder="your-email@gmail.com" name = "email">
    	<br>
    	<button type="submit" class="btn btn-primary" name="subscribe">Subscribe</button>
    	<button type="submit" class="btn btn-primary" name="unsubscribe">Unsubscribe</button>
	 </form>
    
      <br>
      <br>
      <h2 class = "center">Recent Blog Posts</h2>
    <%
        //Actor actor = new Actor();
		String[] titles;
	    String[] contents;
	    String[] times;
	    String[] firstnames;
	    String[] lastnames;
        
        titles = (String[])request.getAttribute("titles");
        contents = (String[])request.getAttribute("contents");
        times = (String[])request.getAttribute("times");
        firstnames = (String[])request.getAttribute("firstnames");
        lastnames = (String[])request.getAttribute("lastnames");
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
			        <p>By <%= firstnames[i] + " " + lastnames[i] %></p> 
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
			        <p>By <%= firstnames[i] %> <%= lastnames[i] %></p> 
			        <p><%= contents[i] %></p> 
			        
					<% i--; %>
				<% } %>
			<% } %>
		<% } %>
		 <form method = "GET" action="/showallposts">
			 <input type="submit" value = "Show All Posts" class="btn btn-outline-primary">
		 </form>
		<div id ="postform" >
		    <form method="POST" action="/printemail">
			 <h1 id ="blogposttitle">Submit a blog post!</h1>
			 <div class="row">
			   <div class="col">
			     <input type="text" class="form-control" placeholder="First name" name="FirstName">
			   </div>
			   <div class="col">
			     <input type="text" class="form-control" placeholder="Last name" name="LastName">
			   </div>
			  </div>
			  <br>
			 <div class="form-group">
			   <input class="form-control form-control-lg" type="text" placeholder="Title" name = "title">
			   <br>
			   <!--<input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">-->
			   <textarea class="form-control" id="exampleFormControlTextarea1" placeholder="Content"  name = "content" rows="6"></textarea>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Submit</button>
		   </form>
	   </div>
	  

	 </div>
  </div> 
  
</body>

</html>
