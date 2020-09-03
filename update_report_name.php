<?php
require "conn.php";

$reports_name  = $_POST["reports_name"];
$users_id  = $_POST["users_id"];
$projects_id  = $_POST["projects_id"];
$reporter  = $_POST["reporter"];
$company_id = $_POST["company_id"];


$sql = "INSERT INTO pictures_information_1 VALUES ('6', '$reports_name', '$users_id', '$projects_id', '$reporter', '$company_id', '');";
$result  = mysqli_query($conn, $sql);
if($result){
echo "Data Inserted";
}
else{
echo "Failed";
}
?>