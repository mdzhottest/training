<%@page import = "com.gcit.training.lws.domain.Author" %>
<%@page import = "com.gcit.training.lws.service.AdministratorService" %>

<%
	String authorToEdit =  request.getParameter("authorIdToEdit");
	Author a = new AdministratorService().getAuthor(Integer.parseInt(authorToEdit));
%>

<div class="modal-body">
	<form action="editAuthor" method ="get">
		Enter Author Name: <input type="text" name = "authorName" value="<%=a.getAuthorName()%>"/>
		<input type="hidden" value = "<%=a.getAuthorId()%>" name ="authorId"/>
		<input type="submit"/>
	</form>

</div>



<!-- </div>
	<form action ="editAuthor" method="get">
		Enter Author ID: <input type="text" name= "authorId">
		Enter New Author Name:<input type="text" name ="authorName"/>
		<input type = "submit"/>
	<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../../js/lib/ng/angular.js"></script>
	<script src="../../js/lib/ng/angular-resource.js"></script>
	<script src="../../js/lib/ng/ui-bootstrap-tpls-0.12.0.min.js"></script>
	<script src="https://appsforoffice.microsoft.com/lib/1.1/hosted/office.debug.js"></script>
	<script src="../../js/lib/ng/bootstrap.min.js"></script>
	<script type="text/javascript">

	OCNamespace = {
	    appConfig: {} 
	}
	
	Office.initialize = function (reason) {
		if(reason == 'inserted'){
			OCNamespace.appConfig.documentOpen = false;
		}else{
			OCNamespace.appConfig.documentOpen = true;
		}
		_settings = Office.context.document.settings;
		/*_settings.set("OCFormTemplate", true);
		_settings.saveAsync(function(asyncResult){
		    write("Setting save status: " + asyncResult.status);
		});*/
		//_settings.set("OCFormTemplate", false);
		OCNamespace.appConfig.OCFormTemplate = _settings.get("OCFormTemplate");

		(function (){
		    var scope = angular.element(document.querySelector('body')).scope();
		    scope.$apply(function(){
		        scope.OCFormTemplate = OCNamespace.appConfig.OCFormTemplate;
			})
		})();
	}

	

	angular.module('ocform', ['ui.bootstrap', 'ngResource'])
	       .factory('FormTemplateInfoList', function($resource){
		       return $resource('/oaworkflow/rest/oc/formTemplateList');
		   }).constant("BLANK_FORM_TEMPLATE", "forms/formTemplate.dotm")
		   .factory('FormTemplateContent', function($resource){
			    return $resource('/oaworkflow/rest/oc/formTemplate/:infoId/:id');
		   }).controller('FormController', function($scope, FormTemplateInfoList, FormTemplateContent, $location, BLANK_FORM_TEMPLATE){

			   console.log("angular controller");
			   //write("angular controller");
			   if(OCNamespace.app){
				   write("OCNamespace.app.insert: " + OCNamespace.app.insert);
			   }
			   FormTemplateInfoList.query(function(data){
				    $scope.infoList = data;
			   })
			   
			   var basePath = $location.protocol() + "://" + $location.host() + ":" + $location.port() + "/";
			   var basePath1 = $location.protocol() + "://" + $location.host() + "/";
			   var path;
			   var absUrl = $location.absUrl()
			   if(absUrl.indexOf(basePath) > -1){
				   path = absUrl.replace(basePath, "");
			   }else if(absUrl.indexOf(basePath1) > -1){
				   path = absUrl.replace(basePath1, "");
			   }
			   var context = path.substring(0, path.indexOf('/'));
			   $scope.rootPath = basePath + context + "/";

			   $scope.AXOSupport = AXOSupport();

			   $scope.blankFormTemplate = BLANK_FORM_TEMPLATE;
			   
			   $scope.addForm = function(id){
				    FormTemplateContent.get({infoId:id}, function(data){
				        var template = data;
						var options = {};
						options.coercionType = 	angular.lowercase(template.info.type);
						//options.valueFormat = "formatted";
						Office.context.document.setSelectedDataAsync(template.content, options);	   	    
	                })   
			   }

			   $scope.fullURL = function(info){
				    return $scope.rootPath + info.relativeURL;
			   }

			   $scope.openWord = function(relativePath){
				   try{
					 var fullPath = $scope.rootPath + relativePath;
					 var wdApp = new ActiveXObject("Word.Application");
			         wdApp.Visible = "True";
			         wdApp.Documents.Open(fullPath);
				   }catch(e){
					   console.log(e.message);
				   }
			   }
				   
			   
		})
			
		function write(message){
		   document.getElementById('message').innerText += message;
		}

	   function openWord(path){
		    var wdApp = new ActiveXObject("Word.Application");
		    wdApp.Visible = "True";
		    wdApp.Documents.Open(path);
	   }

	   function AXOSupport(){
		    var ua = window.navigator.userAgent;
		    var msie = ua.indexOf("MSIE");
		    var trident = ua.indexOf("Trident");
		    if(msie > -1 || trident > -1){
			    return true;
			}else{
				return false;
		    }
	   }
	</script>
	
	<link rel="stylesheet" href="../../js/lib/ng/bootstrap.min.css">
    <link rel="stylesheet" href="../../js/lib/ng/bootstrap-theme.min.css">
    <link href="../../css/usptostrap.min.css" rel="stylesheet" />
	
</head>

<body ng-app="ocform" ng-controller="FormController">
    
    <div id="message"></div>
    
    <tabset>
        <tab ng-if="!OCFormTemplate" heading="OC Form Launcher">
            <div class="panel panel-default">
                <!-- <div class="panel-heading">Insert Form</div>  -->
                <div class="panel-body">
                    <table ng-if="AXOSupport" class="table, table-striped">
                       <tr>
                          <td><button tilte="Insert form into document" class="btn, btn-xs btn-primary" ng-click="openWord(blankFormTemplate)">Launch OC Form</button></td>
                       </tr>
                     </table>
                     <table ng-if="!AXOSupport" class="table, table-striped">
                       <tr>
                          <td><a ng-href="{{rootPath}}{{blankFormTemplate}}"><button tilte="Launch OC Form" class="btn, btn-xs btn-primary">Launch OC Form</button></a></td>
                       </tr>
                     </table>
               </div>
            </div>
        </tab>
        <tab ng-if="OCFormTemplate" heading="Insert XML">
            <div class="panel panel-default">
                <!-- <div class="panel-heading">Insert Form</div>  -->
                <div class="panel-body">
                     <div ng-repeat="info in infoList | filter : {type: 'OOXML'}">
			             <table class="table, table-striped">
			                 <tr>
			                     <td><button tilte="Insert form into document" class="btn, btn-xs btn-primary" disable ng-click="addForm(info.id)">Insert</button></td>
			                     <!-- <td>{{info.id}}</td>  -->
			                     <td>{{info.title}}</td>
			                 </tr>
			               </table>
		              </div>
               </div>
            </div>
        </tab>
        <tab ng-if="OCFormTemplate" heading="HTML">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div ng-repeat="info in infoList | filter : {type: 'HTML'}">
		               <table class="table, table-striped">
		                    <tr>
		                        <td><button tilte="Insert form into document" class="btn, btn-xs btn-primary" ng-click="addForm(info.id)">Insert</button></td>
		                        <!--  <td>{{info.id}}</td>  -->
		                        <td>{{info.title}}</td>
		                    </tr>
		               </table>
		             </div>
                </div>
            </div>
        </tab>
        <tab ng-if="OCFormTemplate" heading="TEXT">
            <div ng-repeat="info in infoList | filter : {type: 'TEXT'}">
               <table class="table, table-striped">
                    <tr>
                        <td><button tilte="Insert form into document" class="btn, btn-xs btn-primary" ng-click="addForm(info.id)">+</button></td>
                        <td>{{info.id}}</td>
                        <td>{{info.title}}</td>
                    </tr>
               </table>
            </div>
        </tab>
        <tab ng-if="OCFormTemplate && AXOSupport" heading="Open Docx">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div ng-repeat="info in infoList | filter : {type: 'DOCX'}">
                         <table class="table, table-striped">
                             <tr>
                                 <td><button tilte="Open document" class="btn, btn-xs btn-primary" ng-click="openWord(info.relativeURL)">Open</button></td>
                                 <!-- <td>{{info.id}}</td>  -->
                                 <td>{{info.title}}</td>
                             </tr>
                           </table>
                     </div>
               </div>
            </div> 
        </tab>
        <tab ng-if="OCFormTemplate && !AXOSupport" heading="Open Docx (non IE)">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div ng-repeat="info in infoList | filter : {type: 'DOCX'}">
                         <table class="table, table-striped">
                             <tr>
                                 <!--  <td><a ng-href="{{fullURL(info)}}">Open Document</a></td>-->
                                 <td><a ng-href="{{fullURL(info)}}"><button tilte="Open document" class="btn, btn-xs btn-primary">Open</button></a></td>
                                 <!-- <td>{{info.id}}</td>  -->
                                 <td>{{info.title}}</td>
                             </tr>
                           </table>
                     </div>
               </div>
            </div> 
        </tab>
        <!-- <tab heading="Open Docm">
            <button title="Open Doc" class="btn, btn-xs btn-primary" onclick="openWord('C:\\WorkArea\\OC\\FormRepository\\transfered\\docx\\sample.docm')">Open Document</button>
            <button title="Open Doc" class="btn, btn-xs btn-primary" onclick="openWord('http://localhost:7070/oaworkflow/forms/SB-07.docm')">Open Document on Server</button>
             <button title="Open Doc" class="btn, btn-xs btn-primary" onclick="openWord('forms/SB-07.docm')">Open Document on Server</button>
             <a href="http://localhost:7070/oaworkflow/forms/SB-07.docm">Open Document with link</a>
        </tab>  -->
    </tabset>

</body>
</html>
	
	</form> -->
