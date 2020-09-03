<?php
require "conn.php";

$reports_name  = $_POST["reports_name"];
$pictures_id  = $_POST["pictures_id"];
$comment  = $_POST["comment"];



$sql = "INSERT INTO reports_information_1 VALUES ('5', '$reports_name', '$pictures_id', '$comment',  '');";
$result  = mysqli_query($conn, $sql);
if($result){
echo "Data Inserted";
}
else{
echo "Failed";
}
?>