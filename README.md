# training
gghb

//This is code for hello Universe
<!--test code for new task pane in word
HTML Portion of the code -->

<!Doctype html>
<html>

	<head>
		<meta charset="UTF-8"/>
		<meta http-equiv = "X-US-Compatible" content="IE=Edge"/>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
		<script src="https://appsforoffice.microsoft.com/lib/1.0/hosted/office.js"></script>
		<script src="Program.js"></script>
		<title> Task Pane Application</title>
				
	</head>
	<body>
	<nav class="navbar navbar-default">
 	 <div class="container-fluid">
    	<div class="navbar-header">
   	   <a class="navbar-brand" href="#">Office JS test</a>
   	 </div>
   	 <div>
    	  <ul class="nav navbar-nav">
        	<li class="active"><a href="#">Home</a></li>
     	   <li><a href="#">index</a></li>
      	</ul>
   	 </div>
 	 </div>
	</nav>
		<p> Hello Universe! This is a prototype office js task pane application created using a text editor. The <b>focus</b> of this application is to see if information can be written to and retrieved from the office application from the task pane application </p>
<br><button type="button" class="btn btn-success" onclick="writeData()">
 <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Writes Data</button></br>
<br><button type="button" class="btn btn-success" onclick="readData()"> <span class="glyphicon glyphicon-book" aria-hidden="true"></span> Read Selected Data</button></br>
<div style= "background-color:rgb(174,227,243);opacity:0.8" class=""panel panel-default">
	<div class="panel-body">
		results: <div id ="results"></div>
	</div>
</div>
<div style= "background-color:rgb(243,174,241);opacity:0.8" class=""panel panel-default">
	<div class="panel-body">
		Message: <div id ="message"></div>
	</div>
</div>
</body>
</html>

***************************************************************************************************
//This is the code for features
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Task Pane Application">
    <meta name="author" content="Steven Ogwuazor">
    <link rel="icon" href="../../favicon.ico">

    <title>Cover Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>


    <!-- Custom styles for this template -->
    <link href="cover.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


	<!-- making changes to bootstrap defaults-->
	<style>
	.jumbotron{
	background: none;
}
	
	</style>
	
	<title>Features</title>
  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">Cover</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li ><a href="Hello Universe2.html">Home</a></li>
                  <li class="active"><a href="features.html">Features</a></li>
                  <li><a href="https://msdn.microsoft.com/en-us/library/office/jj220082.aspx" target="_blank">Information</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Microsoft Office Word Task Pane Application - <i><b>Features</b></i></h1>

<div id="selection" class="jumbotron" >
	<div="container">		
	<p>
	<div class="list-group">
  <a href="#read" class="list-group-item active">
    <h4 class="list-group-item-heading">Reading Information</h4>
    <p class="list-group-item-text">Reads from document these include the printing of said data</p>
  </a>
  <a href="#write" class="list-group-item">
    <h4 class="list-group-item-heading">Writing Information</h4>
    <p class="list-group-item-text">Writes to document these include the printing of said data</p>
  </a>
  <a href="#detect" class="list-group-item">
    <h4 class="list-group-item-heading">Detecting Changes in Selection</h4>
    <p class="list-group-item-text">Finds text that changes</p>
  </a>
  <a href="#bindings" class="list-group-item">
    <h4 class="list-group-item-heading">Bindings</h4>
    <p class="list-group-item-text">Changing bounds of text</p>
  </a>
</div>
		
		
	</p>
	</div>
</div>	
         



<div id="write" class="container" >
	<p>
		Button will set an array of values into word document. Matrix is [['Lagos'],['Abuja'],['Ibadan']].
	</p>   

	<p class="lead">
              <button type="button" class="btn btn-success" onclick="writeData()">
 <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Writes Data</button>
        </p>
</div>



<div id="read" class="container">
	<p>This button shold allow you to read data from a selected portion on the screen</p>
		<p class="lead">
			<button type="button" class="btn btn-success" onclick="readData()"> <span class="glyphicon glyphicon-book" aria-hidden="true"></span> Read Selected Data</button>
		</p>
	<p>This button shold allow you to read data from a selected portion on the screen</p>
	<p class="lead">
			<button type="button" class="btn btn-success" onclick="readSelection()"> <span class="glyphicon 		glyphicon-book" aria-hidden="true"></span> Read Data Selection</button>
	</p>

</div>

<div id="detect" class="container">
	
	

</div>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>Cover template for Task Pane Application, by <a href="#">Steven Ogwuazor</a>.</p>
            </div>
          </div>

        </div>

      </div>

    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

****************************************************************************
//This is the code for Hello Universe 2
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Task Pane Application">
    <meta name="author" content="Steven Ogwuazor">
    <link rel="icon" href="../../favicon.ico">

    <title>Cover Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>


    <!-- Custom styles for this template -->
    <link href="cover.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">Cover</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">Home</a></li>
                  <li ><a href="features.html">Features</a></li>
                  <li><a href="#">Contact</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Microsoft Office Word Task Pane Application</h1>
            <p class="lead">Hello Universe! This is a prototype office js task pane application created using a text editor. The <b>focus</b> of this application is to see if information can be written to and retrieved from the office application from the task pane application</p>

         
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>Cover template for Task Pane Application, by <a href="#">Steven Ogwuazor</a>.</p>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

**************************************************************************************
//this is the code for program, the controller for all task pane manipulations
//initializing function for all apps
Office.initialize - function (reason) {
	$(Document).ready(function(){
		//initialization logic that might be necessary	

	});
}

var MyArray= [['Lagos'],['Abuja'],['Ibadan']];

function writeData(){
	Office.context.document.setSelectedDataAsync(MyArray, { coercionType: 'matrix'});
}

function readData(){
	Office.context.document.getSelctedDataAsync("matrix", function (result){
		if (result.status === "succeeded"){
			printData(result.value);
		}else{
			printData(result.error.name +": " + err.message);
		}
	});
}

function printData(data){
	var printOut = "";
	
	for (var i=0; i<data.length; i++){
		
		for (var j=0; j< data[i].length; y++){
			printOut += data[i][j] + ", ";
		}
	}

	document.getElementById("results").innerText = printOut;
}

//reading text from selection
function readSelection(){

	Office.context.document.getSelectedDataAsync(Office.CoercionType.Text, function(asyncResult){
		if(asyncResults.status == Office.AsyncResultStatus.Failed){
			write('Action failed. Error: ' + asyncResult.error.message); 
		}
		else{
			write('Selected data: ' + asyncResult.value);
		}
		
	});

	//Function that writes to a div with id message
	function write(message){
		document.getElementByID('message').innerText += message;
	}

}

//detecting changes in selection
function detectChanges(){
	//documentSelectionChanged =  Office.EventType.DocumentSelectionChanged
	Office.context.document.addHandlerAsync("documentSelectionChanged", myHandler, function(result){});
	
	//event handler
	function myhandler(eventArgs){
		write('Document Selection changed');
	}

	
	function write(message){
		document.getElementByID('message').innerText += message;
	}
}

//stop detections
function stopDetection(){
	Office.context.document.removeHandleAsync("documentSelectionChanged", {handler: myHandler}, function(result){});
}


//add a binding to the user's current selection
function selBinding(){
	Office.context.document.bindings.addFromSelectionAsync(Office.BindingType.Text, {id: 'myBinding'}, function(asyncResult){
		if(asyncResult.status == Office.AsyncResultStatus.Failed){
			write('Action failed. Error: '+ asyncResult.error.message);
		}else{
			write('Added a new binding with type: ' + asyncResult.value.type + ' and id: ' + asyncResult.value.id);
		}
	});

	function write(message){
		document.getElementById('message').innerText += message;
	}
}


//add a binding from a prompt, in word you ca only bind to the title property of a rich text.

function bindings(){
	function bindFromPrompt(){
		office. context.document.bindings.addFromPromptAsync(Office.BindingType.Text, {id: 'myBinding'}, function (asyncResult){
		if(asyncResult.status == Office.AsyncResultStatus.failed){
			write('Action failed. Error: ' + asyncResult.error.message);
		}else{
			write('Added new binding with type: ' + asyncResult.value.type + ' and id: 'asyncResult.value.id);
		}
	}

	function write(message){
		document.getElementById('message').innerText += message;
	}

	//Word binding content control
	funcion bindContentControl(){
		Office.context.document.bindings.addFromNamedItemAsync('title of Rich text content control', Office.BindingType.Text, {id: 'title of Rich text content control' }, function(result){
			if (result.status === Office.AsyncResultStatus.Succeeded){
				write('Control bound. Binding.id: ' + result.value.id + ' Binding.tyoe: ' + result.value.type);
			}else{
				write('Error: ', result.error.message);
			}
		}
	}


}
*************************************************************************************************************************
//this is the instructions for specifying xml location in Microsoft word
Create a folder on a netwwork share. Be sure that the <SourceLocation> element of the HellowWorld.xml manifest file points to this location for the .html page of the app. Save all of the files you created into this folder.
_________________________________________________________________

Open a new document in Excel or word
_________________________________________________________________

From the File tab, choose Options then Trst Center and then Trust center Settings button.
_________________________________________________________________

Choose Trusted App catalogs
_________________________________________________________________

In the Catalog Url input box, enter the path to the network share you created in Step 1 and choose add Catalog
_________________________________________________________________

Select the show in menu check box and choose Ok
_________________________________________________________________

Close and restart the office application.
_________________________________________________________________



***************************************************************
Test the app
***************************************************************
on the Insert tab, choose My Apps
_________________________________________________________________

In the Apps for Office dialof box, choose Shared Folder.
_________________________________________________________________

Choose Hello World app, and then choose Start.
_________________________________________________________________

*************************************************************************************************
finally, this is the Hello Universe xml encoding document

<?xml version="1.0" encoding="utf-8"?>
<OfficeApp xmlns="http://schemas.microsoft.com/office/appforoffice/1.0"
	xmlns:XSI="http://www.w3.org/2001/XMLSchema-Instance"
	XSI:type=:"TaskPaneApp">
	<Version>1.0</Version>
	<ProviderName>Microsoft</ProviderName>
	<DefaultLocale>EN-US</DefaultLocale>
	<DisplayName DefaultValue="Hello Universe App"/>
	<Description DefaultValue = "My first task pane app."/>
	<IconUrl DefaultValue = "http://officeimg.vo.msecnd.net/_layouts/images/general/office_logo.jpg"/>

	<Capabilities>
		<Capability Name = "Document"/>
		<capability Name = "Workbook"/>
	</Capabilities>

	<DefaultSettings>
		<SourceLocation DefaultValue = "http://w-oa-602.etc.uspto.gov:8080/index.html#app=advisory"/>
	</DefaultSettings>
	
	<Permissions> ReadWriteDocument</Permissions>

</OfficeApp>


