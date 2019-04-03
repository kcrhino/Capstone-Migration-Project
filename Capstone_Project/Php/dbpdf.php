<!DOCTYPE html>
<html>
<head>
<style>
<!--This file is meant for pulling the data out of the data base, storing it into a variable
	and then displaying all of the pdfs in the table we created-->
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>


<?php
// Connection variables to be passed
$servername = "Database_location";
$username = "Your_User_Name";
$password = "Your_Password";
$dbname = "Your_DB_Name";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
//Using a full Select statement to show all of the raw data
//and storing it into a variable to be displayed iteratively
$sql = "SELECT id, dir, Title, Author, KeyWords FROM pdfData";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<table><tr><th>ID</th><th>Dir</th><th>Title</th><th>Author(s)</th><th>Keywords</th></tr>";
    // output data of each row and display them in the correct format we want
	// while also staying in the correct HTML format that we want
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["id"]. 
		//This is the "download" part, to see the actual PDF to view or download the entire file
		//A problem you might run into is the scope of where the PDF's are stored
		"</td><td><a href=".$row['dir'].">Download</a></td><td>" 
		
		. $row["Title"]. "</td><td> " . $row["Author"]. "</td><td> " . $row["KeyWords"]. "</td></tr>";
    }
    echo "</table>";
} else {
    echo "0 results";
}
// EVERTYHING BEYOND THIS IS UNDER CONSTRUCTION
// This is a solid outline of how to build a key word lookup for a database
// Something that in the future I would like to finish 
$conn->close();
?> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title></title>
    </head>
    <body>
<form action="dbpdf.php" method="post">  
Search: <input type="text" name="term" /><br />  
<input type="submit" value="Submit" />  
</form> 
<?php

// Create connection

if (!empty($_REQUEST['term'])) {

$term = mysqli_real_escape_string($conn,$_REQUEST['term']);

$sql = mysqli_query("SELECT id, dir, Title, Author, Keywords FROM pdfdata WHERE Author or KeyWords LIKE '%.$term.%'") or die("Could not search"); 
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	echo 'id' .$row['id'];
    echo "<table><tr><th>ID</th><th>Dir</th><th>Title</th><th>Author(s)</th><th>Keywords</th></tr>";
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["id"]. 
		
		"</td><td><a href=".$row['dir'].">Download</a></td><td>" 
		
		. $row["Title"]. "</td><td> " . $row["Author"]. "</td><td> " . $row["KeyWords"]. "</td></tr>";
    }
    echo "</table>";
} else {
    echo "0 results";
}  }

?>
</body>
</html>